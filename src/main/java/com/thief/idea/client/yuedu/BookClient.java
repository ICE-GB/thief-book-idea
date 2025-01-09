package com.thief.idea.client.yuedu;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.thief.idea.pojo.param.GetBookChapterListParam;
import com.thief.idea.pojo.param.GetBookContentParam;
import com.thief.idea.pojo.vo.Book;
import com.thief.idea.pojo.vo.BookChapter;
import com.thief.idea.pojo.vo.ReturnData;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.ssl.SSLContextBuilder;

import javax.net.ssl.SSLContext;
import java.net.Proxy;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BookClient {
    private static String baseUrl = "https://reader.awnlzw.com";

    private static final String getBookshelfUrl = "/reader3/getBookshelf";
    private static final String getChapterListUrl = "/reader3/getChapterList";
    private static final String getBookContentUrl = "/reader3/getBookContent";

    private static String accessToken = "";
    private static Proxy proxy = null;

    public static void setBaseUrl(String newBaseUrl) {
        BookClient.baseUrl = newBaseUrl;
    }

    public static void setAccessToken(String newAccessToken) {
        BookClient.accessToken = newAccessToken;
    }

    public static void setProxy(Proxy newProxy) {
        BookClient.proxy = newProxy;
    }

    static {
        SSLContext sslContext;
        try {
            sslContext = SSLContextBuilder.create()
                    .loadTrustMaterial(new TrustSelfSignedStrategy())
                    .build();
        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
            throw new RuntimeException(e);
        }
        SSLContext.setDefault(sslContext);
    }

    public static List<Book> getBookshelf(Integer refresh) {
        String resultStr = execute(HttpRequest.get(baseUrl + getBookshelfUrl).form(Map.of("refresh", refresh)));
        ReturnData<List<Book>> returnData = JSONUtil.toBean(resultStr, new TypeReference<>() {
        }, true);
        if (returnData.isSuccess()) {
            return returnData.getData();
        }
        return Collections.emptyList();
    }

    public static List<BookChapter> getChapterList(GetBookChapterListParam getBookChapterListParam) {
        String resultStr = execute(HttpRequest.post(baseUrl + getChapterListUrl).body(JSONUtil.toJsonStr(getBookChapterListParam)));
        ReturnData<List<BookChapter>> returnData = JSONUtil.toBean(resultStr, new TypeReference<>() {
        }, true);
        if (returnData.isSuccess()) {
            return returnData.getData();
        }
        throw new RuntimeException("获取章节列表失败！请求为：" + baseUrl + getChapterListUrl + " | " + JSONUtil.toJsonStr(getBookChapterListParam));
    }


    public static String getBookContent(GetBookContentParam getBookContentParam) {
        String resultStr = execute(HttpRequest.post(baseUrl + getBookContentUrl).body(JSONUtil.toJsonStr(getBookContentParam)));
        ReturnData<String> returnData = JSONUtil.toBean(resultStr, new TypeReference<>() {
        }, true);
        if (returnData.isSuccess()) {
            return returnData.getData();
        }
        return "获取正文失败";
    }

    private static String execute(HttpRequest request) {
        request.timeout(5000);
        addProxy(request);
        addAccessToken(request);
        String resultStr;
        try (HttpResponse response = request.execute()) {
            resultStr = response.body();
        }
        return resultStr;
    }

    private static void addProxy(HttpRequest request) {
        if (null != proxy) {
            request.setProxy(proxy);
        }
    }

    private static void addAccessToken(HttpRequest request) {
        if (!(accessToken == null || accessToken.trim().isEmpty())) {
            Map<String, Object> paramMap = Map.of("accessToken", accessToken, "v", System.currentTimeMillis());
            request.setUrl(request.getUrl() + "?" + HttpUtil.toParams(paramMap));
        }
    }
}
