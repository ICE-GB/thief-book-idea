package com.thief.idea;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainUi implements ToolWindowFactory {

    private PersistentState persistentState = PersistentState.getInstance();

    /**
     * 缓存文件页数所对应的seek，避免搜索指针的时候每次从头读取文件
     **/
    private final Map<Integer, Long> seekDictionary = new LinkedHashMap<>();

    /**
     * 缓存文件页数所对应seek的间隔
     * 该值越小，跳页时间越短，但对应的内存会增大
     **/
    private final int cacheInterval = 200;

    /**
     * 读取文件路径
     **/
    private String bookFile = persistentState.getBookPathText();

    /**
     * 读取字体设置
     **/
    private String type = persistentState.getFontType();

    /**
     * 读取字号设置
     **/
    private String size = persistentState.getFontSize();

    /**
     * 读取每页行数设置
     **/
    private Integer lineCount = Integer.parseInt(persistentState.getLineCount());

    /**
     * 读取行距设置
     **/
    private Integer lineSpace = Integer.parseInt(persistentState.getLineSpace());

    /**
     * 正文内容显示
     **/
    private JTextArea textArea;

    /**
     * 当前阅读页&跳页输入框
     **/
    private JTextField current;

    /**
     * 显示总页数
     **/
    private JLabel total = new JLabel();

    /**
     * 读取文件的指针
     **/
    private long seek = 0;

    /**
     * 当前文件总页数
     **/
    private int totalLine = 0;

    /**
     * 当前正在阅读页数
     **/
    private int currentPage = 0;

    /**
     * 缓存文字
     **/
    private String temp = "Stopping memory leak detection....";

    /**
     * 是否隐藏界面
     **/
    private boolean hide = false;

    private YueDuService yueDuService;

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        try {
            JPanel panel = initPanel();
            ContentFactory contentFactory = ContentFactory.getInstance();
            Content content = contentFactory.createContent(panel, "Memory Leak Detection", false);
            toolWindow.getContentManager().addContent(content);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化整体面板
     **/
    private JPanel initPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        textArea = initTextArea();
        panel.add(textArea, BorderLayout.CENTER);
        panel.add(initOperationPanel(), BorderLayout.EAST);
        return panel;
    }

    /**
     * 正文区域初始化
     **/
    private JTextArea initTextArea() {
        JTextArea textArea = new JTextArea();
        // 初始化显示文字
        String welcome = "Memory leak detection has started....";
        textArea.setText(welcome);
        textArea.setOpaque(false);
        textArea.setTabSize(4);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font(type, Font.PLAIN, Integer.parseInt(size)));
        textArea.setBorder(JBUI.Borders.empty(10, 30));
        return textArea;
    }

    /**
     * 初始化操作面板
     **/
    private JPanel initOperationPanel() {
        // 当前行
        current = initTextField();
        // 总行数
        total.setText("/" + (totalLine % lineCount == 0 ? totalLine / lineCount : totalLine / lineCount + 1));

        JPanel panelRight = new JPanel();
        panelRight.setBorder(JBUI.Borders.empty(0, 20));
        panelRight.setPreferredSize(new Dimension(280, 30));
        panelRight.add(current, BorderLayout.EAST);
        panelRight.add(total, BorderLayout.EAST);
        // 加载按钮
        JButton fresh = initFreshButton();
        panelRight.add(fresh, BorderLayout.EAST);
        // 上一页
        JButton up = initUpButton();
        panelRight.add(up, BorderLayout.EAST);
        // 下一页
        JButton down = initDownButton();
        panelRight.add(down, BorderLayout.EAST);
        // 老板键
        JButton boss = initBossButton(new JButton[]{fresh, up, down});
        panelRight.add(boss, BorderLayout.SOUTH);
        return panelRight;
    }

    /**
     * 跳页输入框
     **/
    private JTextField initTextField() {
        JTextField current = new JTextField("current line:");
        current.setPreferredSize(new Dimension(50, 30));
        current.setOpaque(false);
        current.setBorder(JBUI.Borders.empty(0));
        current.setText(currentPage / lineCount + "");
        current.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // 判断按下的键是否是回车键
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        String input = current.getText();
                        String inputCurrent = input.split("/")[0].trim();
                        int i = Integer.parseInt(inputCurrent);
                        i = i - 1;
                        if (i < 0) {
                            i = 0;
                        }
                        textArea.setText(yueDuService.getBookContentLine(i));
                        current.setText(String.valueOf(yueDuService.getCurrentLine()));
                        total.setText(String.valueOf(yueDuService.getTotalLine()));
                    } catch (NumberFormatException e2) {
                        textArea.setText("请输入数字");
                    }

                }
            }
        });
        return current;
    }

    /**
     * 刷新按钮
     **/
    private JButton initFreshButton() {
        JButton refresh = new JButton(IconLoader.getIcon("/actions/refresh_dark.svg", MainUi.class));
        refresh.setPreferredSize(new Dimension(20, 20));
        refresh.setContentAreaFilled(false);
        refresh.setBorderPainted(false);
        refresh.addActionListener(e -> {
            yueDuService = new YueDuService(persistentState);
            textArea.setText(yueDuService.refreshBookContentLine());
            current.setText(String.valueOf(yueDuService.getCurrentLine()));
            total.setText(String.valueOf(yueDuService.getTotalLine()));
        });
        return refresh;
    }

    /**
     * 向上翻页按钮
     **/
    private JButton initUpButton() {
        JButton afterB = new JButton(IconLoader.getIcon("/actions/previousOccurence_dark.svg", MainUi.class));
        afterB.setPreferredSize(new Dimension(20, 20));
        afterB.setContentAreaFilled(false);
        afterB.setBorderPainted(false);
        afterB.addActionListener(e -> {
            textArea.setText(yueDuService.getBookContentLastLine());
            current.setText(String.valueOf(yueDuService.getCurrentLine()));
            total.setText(String.valueOf(yueDuService.getTotalLine()));
        });
        afterB.registerKeyboardAction(afterB.getActionListeners()[0]
                , KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, InputEvent.SHIFT_DOWN_MASK)
                , JComponent.WHEN_IN_FOCUSED_WINDOW);
        return afterB;
    }

    /**
     * 向下翻页按钮
     **/
    private JButton initDownButton() {
        JButton nextB = new JButton(IconLoader.getIcon("/actions/nextOccurence_dark.svg", MainUi.class));
        nextB.setPreferredSize(new Dimension(20, 20));
        nextB.setContentAreaFilled(false);
        nextB.setBorderPainted(false);
        nextB.addActionListener(e -> {
            textArea.setText(yueDuService.getBookContentNextLine());
            current.setText(String.valueOf(yueDuService.getCurrentLine()));
            total.setText(String.valueOf(yueDuService.getTotalLine()));
        });
        nextB.registerKeyboardAction(nextB.getActionListeners()[0]
                , KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, InputEvent.SHIFT_DOWN_MASK)
                , JComponent.WHEN_IN_FOCUSED_WINDOW);
        return nextB;
    }

    /**
     * 隐藏按钮
     **/
    private JButton initBossButton(JButton[] buttons) {
        // 老板键
        JButton bossB = new JButton(" ");
//        JButton bossB = new JButton(IconLoader.getIcon("/actions/show_dark.svg", MainUi.class));
        bossB.setPreferredSize(new Dimension(12, 12));
        bossB.setContentAreaFilled(false);
        bossB.setBorderPainted(false);
        bossB.addActionListener(e -> {
            if (hide) {
                for (JButton b : buttons) {
                    b.setVisible(true);
                }
                current.setVisible(true);
                total.setVisible(true);
                textArea.setText(temp);
                hide = false;
            } else {
                for (JButton b : buttons) {
                    b.setVisible(false);
                }
                current.setVisible(false);
                total.setVisible(false);
                temp = textArea.getText();
                textArea.setText("Memory leak detection....");
                hide = true;
            }
        });
        bossB.registerKeyboardAction(bossB.getActionListeners()[0], KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        bossB.registerKeyboardAction(bossB.getActionListeners()[0], KeyStroke.getKeyStroke(KeyEvent.VK_UP, InputEvent.SHIFT_DOWN_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        return bossB;
    }
}
