package com.thief.idea.client.yuedu;


import com.thief.idea.pojo.param.GetBookChapterListParam;
import org.junit.Test;

public class BookClientTest {

    @Test
    public void getChapterList() {
        GetBookChapterListParam getBookChapterListParam = new GetBookChapterListParam();
        getBookChapterListParam.setUrl("https://69shuba.cx/book/52898.htm");
        getBookChapterListParam.setRefresh(0);
        System.out.println(BookClient.getChapterList(getBookChapterListParam));
    }
}