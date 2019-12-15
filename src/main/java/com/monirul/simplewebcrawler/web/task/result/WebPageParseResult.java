package com.monirul.simplewebcrawler.web.task.result;

import com.monirul.simplewebcrawler.web.WebPage;
import lombok.Data;

@Data
public class WebPageParseResult implements CrawlTaskResult<WebPage>{

    private String url;

    private String title;

    private String content;

    public WebPageParseResult(String url, String content) {
        this.url = url;
        this.content = content;
    }


    @Override
    public WebPage getResult() {
        return new WebPage(url,content);
    }
}
