package com.thief.idea;

import cn.hutool.core.collection.CollectionUtil;
import com.thief.idea.client.yuedu.BookClient;
import com.thief.idea.pojo.param.GetBookChapterListParam;
import com.thief.idea.pojo.param.GetBookContentParam;
import com.thief.idea.pojo.vo.Book;
import com.thief.idea.pojo.vo.BookChapter;

import java.util.*;

public class YueDuService {
    private Book book;

    private Integer lineIndex;
    private Integer chapterIndex;

    private List<BookChapter> bookChapterList;
    private String bookContent = "";

    private Map<Integer, List<String>> bookContentMap = new HashMap<>();
    private List<String> bookContentLineList = Collections.emptyList();

    private PersistentState persistentState;

    public Integer getCurrentLine() {
        return lineIndex + 1;
    }

    public Integer getTotalLine() {
        return bookContentLineList.size();
    }

    public YueDuService(PersistentState persistentState) {
        Book book = new Book();
        book.setBookUrl(persistentState.getBookPathText());
        book.setDurChapterIndex(Integer.valueOf(persistentState.getChapterIndex()));
        book.setDurChapterPos(Integer.valueOf(persistentState.getLineIndex()));
        this.persistentState = persistentState;
        this.chapterIndex = book.getDurChapterIndex();
        this.lineIndex = book.getDurChapterPos();
        this.book = book;
        GetBookChapterListParam getBookChapterListParam = new GetBookChapterListParam();
        getBookChapterListParam.setUrl(book.getBookUrl());
        getBookChapterListParam.setRefresh(0);
        this.bookChapterList = BookClient.getChapterList(getBookChapterListParam);
        if (bookChapterList.isEmpty()) {
            System.out.println("获取目录为空");
        } else {
            this.book.setTotalChapterNum(bookChapterList.size());
            loadBookChapter(chapterIndex);
        }
    }

    private void saveProcess() {
        persistentState.setChapterIndex(chapterIndex.toString());
        persistentState.setLineIndex(lineIndex.toString());
    }


    public String getBookContentLastLine() {
        lineIndex = lineIndex - 1;
        if (lineIndex < 0) {
            if (!loadBookLastChapter()) {
                return "【当前为第一章】" + bookContentLineList.get(this.lineIndex);
            }
        }
        saveProcess();
        return bookContentLineList.get(this.lineIndex);
    }

    public String getBookContentNextLine() {
        lineIndex = lineIndex + 1;
        if (this.lineIndex + 1 > bookContentLineList.size()) {
            if (!loadBookNextChapter()) {
                return bookContentLineList.get(this.lineIndex) + "【当前为最后一章】";
            }
        }
        saveProcess();
        return bookContentLineList.get(this.lineIndex);
    }

    public String refreshBookContentLine() {
        bookContentMap.clear();
        loadBookChapter(chapterIndex);
        return getBookContentLine(lineIndex);
    }

    public String getBookContentLine(Integer lineIndex) {
        this.lineIndex = lineIndex;
        if (this.lineIndex < 0) {
            if (!loadBookLastChapter()) {
                return "当前为第一章";
            }
            saveProcess();
            return bookContentLineList.get(this.lineIndex);
        }
        if (this.lineIndex + 1 > bookContentLineList.size()) {
            if (!loadBookNextChapter()) {
                return "当前为最后一章";
            }
            saveProcess();
            return bookContentLineList.get(this.lineIndex);
        }
        saveProcess();
        return bookContentLineList.get(this.lineIndex);
    }

    private Boolean loadBookLastChapter() {
        chapterIndex = chapterIndex - 1;
        if (chapterIndex < 0) {
            chapterIndex = 0;
            lineIndex = 0;
            return false;
        }
        loadBookChapter(chapterIndex);
        lineIndex = bookContentLineList.size() - 1;
        return true;
    }

    private Boolean loadBookNextChapter() {
        chapterIndex = chapterIndex + 1;
        if (chapterIndex > book.getTotalChapterNum() - 1) {
            chapterIndex = chapterIndex - 1;
            lineIndex = lineIndex - 1;
            return false;
        }
        loadBookChapter(chapterIndex);
        lineIndex = 0;
        return true;
    }

    private void loadBookChapter(Integer chapterIndex) {
        this.chapterIndex = chapterIndex;
        if (CollectionUtil.isEmpty(bookContentMap.get(chapterIndex))) {
            GetBookContentParam getBookContentParam = new GetBookContentParam();
            getBookContentParam.setUrl(book.getBookUrl());
            getBookContentParam.setIndex(this.chapterIndex);
            bookContent = BookClient.getBookContent(getBookContentParam);
            refreshBookContentLinkList();
            bookContentMap.put(chapterIndex, bookContentLineList);
        } else {
            bookContentLineList = bookContentMap.get(chapterIndex);
        }
    }

    private void refreshBookContentLinkList() {
        bookContent = bookContent.replaceAll("\\u00A0+", "");
        bookContentLineList = new ArrayList<>(Arrays.stream(bookContent.split("\\n")).map(String::trim).filter(x -> !x.isBlank()).toList());
        bookContentLineList.set(0, "【" + bookChapterList.get(chapterIndex).getTitle() + "】" + bookContentLineList.get(0));
    }
}
