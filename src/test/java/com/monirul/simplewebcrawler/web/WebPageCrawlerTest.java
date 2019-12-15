package com.monirul.simplewebcrawler.web;

import com.monirul.simplewebcrawler.core.CrawlItem;
import com.monirul.simplewebcrawler.core.frontier.Frontier;
import com.monirul.simplewebcrawler.core.frontier.impl.InMemoryCrawlQueue;
import com.monirul.simplewebcrawler.core.frontier.impl.InMemoryCrawlTracker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WebPageCrawlerTest {

    private WebPageCrawler classUnderTest;

    private Frontier frontier;

    @BeforeEach
    void setUp() {
        frontier = new Frontier(new InMemoryCrawlQueue(), new InMemoryCrawlTracker());

    }

    @Test
    void carwl_should_success() throws Exception {
        CrawlItem crawlItem = new CrawlItem("http://www.google.com","");
        classUnderTest = new WebPageCrawler(crawlItem,frontier);
        classUnderTest.crawl();
        WebCrawlResultProcessor resultProcessor = new WebCrawlResultProcessor((InMemoryCrawlTracker)frontier.getCrawlTracker());

        assertEquals(19,frontier.getCrawlQueue().size());
    }
}