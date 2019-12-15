package com.monirul.simplewebcrawler.web.task.result;

import com.monirul.simplewebcrawler.http.HttpStatus;
import com.monirul.simplewebcrawler.web.WebPage;
import lombok.Data;
import org.apache.http.HttpEntity;

@Data
public class WebPageFetchResult implements CrawlTaskResult<HttpEntity> {

    private String url;

    private HttpEntity entity;

    private HttpStatus status;

    public WebPageFetchResult(String url, HttpEntity entity) {
        this.url = url;
        this.entity = entity;
    }

    @Override
    public HttpEntity getResult() {
        return entity;
    }
}
