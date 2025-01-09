package com.thief.idea;

import com.thief.idea.client.yuedu.BookClient;
import org.junit.Test;


public class YueDuServiceTest {
    @Test
    public void init() {
        BookClient.setBaseUrl("https://reader.awnlzw.com");
        BookClient.setAccessToken("");
        BookClient.setProxy(null);
        YueDuService yueDuService = new YueDuService("https://69shuba.cx/book/52898.htm", "564", "0");
        System.out.println(yueDuService.refreshBookContentLine());
    }


}