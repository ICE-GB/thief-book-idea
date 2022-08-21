package com.thief.idea;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.thief.idea.client.yuedu.BookClient;
import com.thief.idea.pojo.param.GetBookChapterListParam;
import com.thief.idea.pojo.param.GetBookContentParam;
import com.thief.idea.pojo.vo.Book;
import com.thief.idea.pojo.vo.BookChapter;

import java.util.*;

public class YueDuService {
    private Book book = null;

    private Integer lineIndex = 0;
    private Integer chapterIndex = 0;

    private List<BookChapter> bookChapterList;
    private String bookContent = "";

    private final Map<Integer, List<String>> bookContentMap = new HashMap<>();
    private List<String> bookContentLineList = Collections.emptyList();

    private PersistentState persistentState = null;
    /**
     * 读取每页行数设置
     **/
    private Integer lineCount = 1;

    /**
     * 读取行距设置
     **/
    private Integer lineSpace = 0;

    public Integer getCurrentLine() {
        return lineIndex + 1;
    }

    public Integer getTotalLine() {
        return bookContentLineList.size();
    }

    public YueDuService(PersistentState persistentState) {
        this.persistentState = persistentState;
        this.lineCount = Integer.parseInt(persistentState.getLineCount());
        this.lineSpace = Integer.parseInt(persistentState.getLineSpace());

        init(persistentState.getBookPathText(), persistentState.getChapterIndex(), persistentState.getLineIndex());
    }

    public YueDuService(String bookUrl, String chapterIndex, String lineIndex) {
        init(bookUrl, chapterIndex, lineIndex);
    }

    public YueDuService(String bookUrl, String chapterIndex, String lineIndex, String lineCount, String lineSpace) {
        this.lineCount = Integer.valueOf(lineCount);
        this.lineSpace = Integer.valueOf(lineSpace);

        init(bookUrl, chapterIndex, lineIndex);
    }

    private void init(String bookUrl, String chapterIndex, String lineIndex) {
        Book book = new Book();
        book.setBookUrl(bookUrl);
        book.setDurChapterIndex(Integer.valueOf(chapterIndex));
        book.setDurChapterPos(Integer.valueOf(lineIndex));
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
            loadBookChapter(this.chapterIndex);
        }
    }

    private void saveProcess() {
        if (!ObjectUtil.isEmpty(persistentState)) {
            persistentState.setChapterIndex(chapterIndex.toString());
            persistentState.setLineIndex(lineIndex.toString());
        }
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
        bookContent = bookContent
                .replaceAll("\\u00a0+", "")
                .replaceAll("\\u2003+", "")
                .replaceAll("\\u2002+", "");
        List<String> tmpList = new ArrayList<>(
                Arrays.stream(bookContent.split("\\n"))
                        .map(String::trim)
                        .map(x -> (x.replaceAll("^[　*| *| *|//*]*", "").replaceAll("[　*| *| *|//*]*$", "")))
                        .filter(x -> !x.isBlank())
                        .toList()
        );
        bookContentLineList = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < tmpList.size() + 1; i++) {
            if (i % lineCount == 0) {
                stringBuilder.append(tmpList.get(i - 1));
                bookContentLineList.add(stringBuilder.toString());
                stringBuilder.setLength(0);
            } else {
                stringBuilder.append(tmpList.get(i - 1));
                stringBuilder.append("\n".repeat(Math.max(0, lineSpace + 1)));
            }
        }

        bookContentLineList.set(0, "【" + bookChapterList.get(chapterIndex).getTitle() + "】" + bookContentLineList.get(0));
    }

    public static void main(String[] args) {
        String bookUrl = "https://www.biqugexx.com/120_120641/";
        String chapterIndex = "0";
        String lineIndex = "0";
        String lineCount = "2";
        String lineSpace = "1";
        YueDuService yueDuService = new YueDuService(bookUrl, chapterIndex, lineIndex, lineCount, lineSpace);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(yueDuService.refreshBookContentLine());

        for (int i = 0; i < 10; i++) {
            stringBuilder.append("\\n");
            stringBuilder.append(yueDuService.getBookContentNextLine());
        }

        System.out.println(stringBuilder);
    }
}
