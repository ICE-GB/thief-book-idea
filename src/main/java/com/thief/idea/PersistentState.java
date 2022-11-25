package com.thief.idea;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.apache.commons.lang.StringUtils;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


@State(
        name = "PersistentState",
        storages = {@Storage(
                value = "thief-book.xml"
        )}
)
public class PersistentState implements PersistentStateComponent<Element> {

    private static PersistentState persistentState;

    private String serverUrl;

    private String accessToken;

    private String bookPathText;

    private String showFlag;

    private String fontSize;

    private String fontType;

    private String before;

    private String next;

    private String currentLine;

    private String lineCount;

    private String lineSpace;

    private String chapterIndex;

    private String lineIndex;


    public PersistentState() {
    }

    public static PersistentState getInstance() {
        if (persistentState == null) {
            persistentState = ApplicationManager.getApplication().getService(PersistentState.class);
        }
        return persistentState;
    }

    public static PersistentState getInstanceForce() {
        return ApplicationManager.getApplication().getService(PersistentState.class);
    }


    @Nullable
    @Override
    public Element getState() {
        Element element = new Element("PersistentState");
        element.setAttribute("serverUrl", this.getServerUrl());
        element.setAttribute("accessToken", this.getAccessToken());
        element.setAttribute("bookPath", this.getBookPathText());
        element.setAttribute("showFlag", this.getShowFlag());
        element.setAttribute("fontSize", this.getFontSize());
        element.setAttribute("before", this.getBefore());
        element.setAttribute("next", this.getNext());
        element.setAttribute("currentLine", this.getCurrentLine());
        element.setAttribute("fontType", this.getFontType());
        element.setAttribute("lineCount", this.getLineCount());
        element.setAttribute("lineSpace", this.getLineSpace());
        element.setAttribute("chapterIndex", this.getChapterIndex());
        element.setAttribute("lineIndex", this.getLineIndex());
        return element;
    }

    @Override
    public void loadState(@NotNull Element state) {
        this.setServerUrl(state.getAttributeValue("serverUrl"));
        this.setAccessToken(state.getAttributeValue("accessToken"));
        this.setBookPathText(state.getAttributeValue("bookPath"));
        this.setShowFlag(state.getAttributeValue("showFlag"));
        this.setFontSize(state.getAttributeValue("fontSize"));
        this.setBefore(state.getAttributeValue("before"));
        this.setNext(state.getAttributeValue("next"));
        this.setCurrentLine(state.getAttributeValue("currentLine"));
        this.setFontType(state.getAttributeValue("fontType"));
        this.setLineCount(state.getAttributeValue("lineCount"));
        this.setLineSpace(state.getAttributeValue("lineSpace"));
        this.setChapterIndex(state.getAttributeValue("chapterIndex"));
        this.setLineIndex(state.getAttributeValue("lineIndex"));

    }

    @Override
    public void noStateLoaded() {

    }

    public String getServerUrl() {
        return StringUtils.isEmpty(serverUrl) ? "http://book.fbi.com:4396" : this.serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getAccessToken() {
        return StringUtils.isEmpty(accessToken) ? "" : this.accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getBookPathText() {
        return StringUtils.isEmpty(bookPathText) ? "README.md" : this.bookPathText;
    }

    public void setBookPathText(String bookPathText) {
        this.bookPathText = bookPathText;
    }

    public String getShowFlag() {
        return StringUtils.isEmpty(showFlag) ? "0" : this.showFlag;
    }

    public void setShowFlag(String showFlag) {
        this.showFlag = showFlag;
    }

    public String getBefore() {
        return StringUtils.isEmpty(before) ? "Shift + ←" : this.before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getNext() {
        return StringUtils.isEmpty(next) ? "Shift + →" : this.next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getCurrentLine() {
        return StringUtils.isEmpty(currentLine) ? "0" : this.currentLine;
    }

    public void setCurrentLine(String currentLine) {
        this.currentLine = currentLine;
    }

    public String getFontSize() {
        return StringUtils.isEmpty(fontSize) ? "14" : this.fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public String getFontType() {
        return StringUtils.isEmpty(fontType) ? "微软雅黑" : this.fontType;
    }

    public void setFontType(String fontType) {
        this.fontType = fontType;
    }

    public String getLineCount() {
        return this.lineCount = StringUtils.isEmpty(lineCount) ? "1" : lineCount;
    }

    public void setLineCount(String lineCount) {
        this.lineCount = lineCount;
    }

    public String getLineSpace() {
        return this.lineSpace = StringUtils.isEmpty(lineSpace) ? "0" : lineSpace;
    }

    public void setLineSpace(String lineSpace) {
        this.lineSpace = lineSpace;
    }

    public String getChapterIndex() {
        return this.chapterIndex = StringUtils.isEmpty(chapterIndex) ? "0" : chapterIndex;
    }

    public void setChapterIndex(String chapterIndex) {
        this.chapterIndex = chapterIndex;
    }

    public String getLineIndex() {
        return this.lineIndex = StringUtils.isEmpty(lineIndex) ? "0" : lineIndex;
    }

    public void setLineIndex(String lineIndex) {
        this.lineIndex = lineIndex;
    }
}
