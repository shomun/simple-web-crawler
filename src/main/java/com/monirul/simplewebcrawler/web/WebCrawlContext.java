package com.monirul.simplewebcrawler.web;

import com.monirul.simplewebcrawler.core.CrawlItem;
import com.monirul.simplewebcrawler.web.task.result.CrawlTaskResult;
import lombok.Data;


@Data
public class WebCrawlContext {

    private CrawlItem crawlItem;

    private CrawlTaskResult previouTaskResult;

    private WebPage page;

    private int maxLinkToVisit = 100;

}
