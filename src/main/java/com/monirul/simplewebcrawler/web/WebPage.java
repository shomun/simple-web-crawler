package com.monirul.simplewebcrawler.web;

import lombok.Data;

import java.util.List;

@Data
public class WebPage {

    private String url;

    private String title;

    private String content;

    private List<String> hyperLinks;

    public WebPage(String url, String content) {
        this.url = url;
        this.content = content;
    }




//    @Override
//    public Content getContent() {
//        return content;
//    }
}
