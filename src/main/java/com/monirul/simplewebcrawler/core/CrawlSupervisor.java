package com.monirul.simplewebcrawler.core;

public interface CrawlSupervisor {

    /**
     * start crawling given url and find and crawl child urls
     * @return
     */
    boolean start();

    Node getResult();
}
