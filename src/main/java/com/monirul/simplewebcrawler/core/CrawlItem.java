package com.monirul.simplewebcrawler.core;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Data
public class CrawlItem {

    private String url;

    private String parentUrl;

    private  String title;

    private CrawlItem parentItem;

    public CrawlItem(String url){
        this.url = url;
        this.parentUrl = StringUtils.EMPTY;
    }

    public CrawlItem(String url, String parentUrl) {
        this.url = url;
        this.parentUrl = parentUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrawlItem crawlItem = (CrawlItem) o;
        return url.equals(crawlItem.url) &&
                parentUrl.equals(crawlItem.parentUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, parentUrl);
    }
}
