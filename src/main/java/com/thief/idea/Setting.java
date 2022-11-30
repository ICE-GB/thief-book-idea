package com.thief.idea;

import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.project.Project;
import com.thief.idea.ui.SettingUi;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Objects;

public class Setting implements SearchableConfigurable {


    private SettingUi settingUi;

    private final PersistentState persistentState = PersistentState.getInstance();


    @SuppressWarnings("FieldCanBeLocal")
    private final Project project;


    public Setting(@NotNull Project project) {
        this.project = project;
    }

    @NotNull
    @Override
    public String getId() {
        return "thief.id";
    }

    @Nullable
    @Override
    public Runnable enableSearch(String option) {
        return null;
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "thief-book-config";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return null;
    }

    @Nullable
    @Override
    public JComponent createComponent() {

        if (settingUi == null) {
            settingUi = new SettingUi();
        }
        settingUi.innit(persistentState);

        return settingUi.mainPanel;

    }

    @Override
    public boolean isModified() {
        return !StringUtils.equals(persistentState.getServerUrl(), settingUi.serverUrl.getText())
                || !StringUtils.equals(persistentState.getAccessToken(), settingUi.accessToken.getText())
                || !StringUtils.equals(persistentState.getProxyType(), Objects.requireNonNull(settingUi.proxyType.getSelectedItem()).toString())
                || !StringUtils.equals(persistentState.getProxyHostname(), settingUi.proxyHostname.getText())
                || !StringUtils.equals(String.valueOf(persistentState.getProxyPort()), settingUi.proxyPort.getText())
                || !StringUtils.equals(persistentState.getBookPathText(), settingUi.bookPathText.getText())
                || !StringUtils.equals(persistentState.getChapterIndex(), settingUi.chapterIndex.getText())
                || !StringUtils.equals(persistentState.getLineIndex(), settingUi.lineIndex.getText())
                || !StringUtils.equals(persistentState.getFontSize(), Objects.requireNonNull(settingUi.fontSize.getSelectedItem()).toString())
                || !StringUtils.equals(persistentState.getBefore(), settingUi.before.getText())
                || !StringUtils.equals(persistentState.getNext(), settingUi.next.getText())
                || !StringUtils.equals(persistentState.getLineCount(), Objects.requireNonNull(settingUi.lineCount.getSelectedItem()).toString())
                || !StringUtils.equals(persistentState.getLineSpace(), Objects.requireNonNull(settingUi.lineSpace.getSelectedItem()).toString())
                || !StringUtils.equals(persistentState.getFontType(), Objects.requireNonNull(settingUi.fontType.getSelectedItem()).toString());

    }

    @Override
    public void apply() {
        persistentState.setServerUrl(settingUi.serverUrl.getText());
        persistentState.setAccessToken(settingUi.accessToken.getText());
        persistentState.setProxyType(Objects.requireNonNull(settingUi.proxyType.getSelectedItem()).toString());
        persistentState.setProxyHostname(settingUi.proxyHostname.getText());
        persistentState.setProxyPort(Integer.valueOf(settingUi.proxyPort.getText()));
        persistentState.setBookPathText(settingUi.bookPathText.getText());
        persistentState.setChapterIndex(settingUi.chapterIndex.getText());
        persistentState.setLineIndex(settingUi.lineIndex.getText());
        persistentState.setFontSize(Objects.requireNonNull(settingUi.fontSize.getSelectedItem()).toString());
        persistentState.setBefore(settingUi.before.getText());
        persistentState.setNext(settingUi.next.getText());
        persistentState.setLineCount(Objects.requireNonNull(settingUi.lineCount.getSelectedItem()).toString());
        persistentState.setFontType(Objects.requireNonNull(settingUi.fontType.getSelectedItem()).toString());
        persistentState.setLineSpace(Objects.requireNonNull(settingUi.lineSpace.getSelectedItem()).toString());

    }

    @Override
    public void reset() {
//        settingUi.bookPathText.setText("");
//        settingUi.showFlag.setSelected(false);
//        settingUi.fontSize.setSelectedItem("5");
//        settingUi.before.setText("");
//        settingUi.next.setText("");
    }

    @Override
    public void disposeUIResources() {

    }
}