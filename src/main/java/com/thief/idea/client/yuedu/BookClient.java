package com.thief.idea.client.yuedu;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.thief.idea.pojo.param.GetBookChapterListParam;
import com.thief.idea.pojo.param.GetBookContentParam;
import com.thief.idea.pojo.vo.Book;
import com.thief.idea.pojo.vo.BookChapter;
import com.thief.idea.pojo.vo.ReturnData;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BookClient {
    private static String baseUrl = "http://book.fbi.com:4396";

    private static String getBookshelfUrl = baseUrl + "/reader3/getBookshelf";
    private static String getChapterListUrl = baseUrl + "/reader3/getChapterList";
    private static String getBookContentUrl = baseUrl + "/reader3/getBookContent";

    public static List<Book> getBookshelf(Integer refresh) {
        String resultStr = HttpUtil.get(getBookshelfUrl, Map.of("refresh", refresh));
        ReturnData<List<Book>> returnData = JSONUtil.toBean(resultStr, new TypeReference<>() {
        }, true);
        if (returnData.isSuccess()) {
            return returnData.getData();
        }
        return Collections.emptyList();
    }

    public static List<BookChapter> getChapterList(GetBookChapterListParam getBookChapterListParam) {

        String resultStr = HttpUtil.post(getChapterListUrl, JSONUtil.toJsonStr(getBookChapterListParam));
        ReturnData<List<BookChapter>> returnData = JSONUtil.toBean(resultStr, new TypeReference<>() {
        }, true);
        if (returnData.isSuccess()) {
            return returnData.getData();
        }
        return Collections.emptyList();
    }


    public static String getBookContent(GetBookContentParam getBookContentParam) {

        String resultStr = HttpUtil.post(getBookContentUrl, JSONUtil.toJsonStr(getBookContentParam));
        ReturnData<String> returnData = JSONUtil.toBean(resultStr, new TypeReference<>() {
        }, true);
        if (returnData.isSuccess()) {
            return returnData.getData();
        }
        return "获取正文失败";
    }
}
