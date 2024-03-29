package com.monirul.simplewebcrawler.core.frontier;


import com.monirul.simplewebcrawler.core.CrawlItem;
import lombok.Data;

/**
 * Maintain crawling history, urls to crawl .
 */
@Data
public class Frontier {

    private CrawlQueue<CrawlItem> crawlQueue;

    private CrawlTracker crawlTracker;

    public Frontier(CrawlQueue<CrawlItem> crawlQueue, CrawlTracker crawlTracker) {
        this.crawlQueue = crawlQueue;
        this.crawlTracker = crawlTracker;
    }


}
