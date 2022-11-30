package com.thief.idea.ui;

import com.thief.idea.PersistentState;
import com.thief.idea.util.SettingUtil;

import javax.swing.*;
import java.net.Proxy;

public class SettingUi {
    public JPanel mainPanel;
    public JLabel chooseFileLabel;
    public JLabel label1;
    public JLabel label3;
    public JComboBox<Integer> fontSize;
    public JComboBox<String> fontType;
    public JLabel label5;
    public JLabel label4;
    public JTextField before;
    public JTextField next;
    public JTextField bookPathText;
    public JLabel l;
    public JLabel fontSizeLabel;
    public JLabel label6;
    public JComboBox<Integer> lineCount;
    public JComboBox<Integer> lineSpace;
    public JLabel label7;
    public JLabel chapterIndexLabel;
    public JTextField chapterIndex;
    public JLabel lineIndexLabel;
    public JTextField lineIndex;
    public JLabel serverUrlLabel;
    public JTextField serverUrl;
    public JLabel accessTokenLabel;
    public JTextField accessToken;
    public JLabel proxyLabel;
    public JComboBox<String> proxyType;
    public JTextField proxyHostname;
    public JTextField proxyPort;

    private final Integer MIN_FONT_SIZE = 11;
    private final Integer MAX_FONT_SIZE = 25;
    private final Integer MIN_LINE_COUNT = 1;
    private final Integer MAX_LINE_COUNT = 10;
    private final Integer MIN_LINE_SIZE = 0;
    private final Integer MAX_LINE_SIZE = 3;


    public SettingUi() {
        final DefaultComboBoxModel<Integer> defaultComboBoxModel1 = new DefaultComboBoxModel<>();

        for (int i = MIN_FONT_SIZE; i < MAX_FONT_SIZE; i++) {
            defaultComboBoxModel1.addElement(i);
        }

        fontSize.setModel(defaultComboBoxModel1);
        fontSize.setToolTipText("");
        final DefaultComboBoxModel<String> defaultComboBoxModel2 = new DefaultComboBoxModel<>();
        for (String font : SettingUtil.getAllFontType()) {
            defaultComboBoxModel2.addElement(font);
        }
        fontType.setModel(defaultComboBoxModel2);
        fontType.setToolTipText("");

        final DefaultComboBoxModel<Integer> defaultComboBoxModel3 = new DefaultComboBoxModel<>();
        for (int i = MIN_LINE_COUNT; i < MAX_LINE_COUNT; i++) {
            defaultComboBoxModel3.addElement(i);
        }
        lineCount.setModel(defaultComboBoxModel3);
        lineCount.setToolTipText("");

        final DefaultComboBoxModel<Integer> defaultComboBoxModel4 = new DefaultComboBoxModel<>();
        for (int i = MIN_LINE_SIZE; i < MAX_LINE_SIZE; i++) {
            defaultComboBoxModel4.addElement(i);
        }
        lineSpace.setModel(defaultComboBoxModel4);
        lineSpace.setToolTipText("");

        final DefaultComboBoxModel<String> defaultComboBoxModel5 = new DefaultComboBoxModel<>();
        defaultComboBoxModel5.addElement(Proxy.Type.DIRECT.toString());
        defaultComboBoxModel5.addElement(Proxy.Type.HTTP.toString());
        defaultComboBoxModel5.addElement(Proxy.Type.SOCKS.toString());
        proxyType.setModel(defaultComboBoxModel5);
        proxyType.setToolTipText("");
    }


    public void innit(PersistentState persistentState) {
        if (fontSize.getSelectedItem() == null) {
            fontSize.setSelectedItem(14);
        }
        serverUrl.setText(persistentState.getServerUrl());
        accessToken.setText(persistentState.getAccessToken());
        proxyType.setSelectedItem(persistentState.getProxyType());
        proxyHostname.setText(persistentState.getProxyHostname());
        proxyPort.setText(persistentState.getProxyPort().toString());
        bookPathText.setText(persistentState.getBookPathText());
        chapterIndex.setText(persistentState.getChapterIndex());
        lineIndex.setText(persistentState.getLineIndex());

        fontSize.setSelectedItem(Integer.parseInt(persistentState.getFontSize()));
        fontType.setSelectedItem(persistentState.getFontType());
        before.setText(persistentState.getBefore());
        next.setText(persistentState.getNext());
        lineCount.setSelectedItem(Integer.parseInt(persistentState.getLineCount()));
        lineSpace.setSelectedItem(Integer.parseInt(persistentState.getLineSpace()));

        before.setEditable(false);
        next.setEditable(false);
        serverUrl.setEditable(true);
        accessToken.setEditable(true);
        proxyHostname.setEditable(true);
        proxyPort.setEditable(true);
        bookPathText.setEditable(true);
        chapterIndex.setEditable(true);
        lineIndex.setEditable(true);
    }
}