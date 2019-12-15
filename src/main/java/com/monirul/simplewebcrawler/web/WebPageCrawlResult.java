package com.monirul.simplewebcrawler.web;

import com.monirul.simplewebcrawler.core.CrawlResult;
import lombok.Data;

import java.util.List;

@Data
public class WebPageCrawlResult implements CrawlResult {

    private WebPage page;

    private List<String> hyperLinks;
}
