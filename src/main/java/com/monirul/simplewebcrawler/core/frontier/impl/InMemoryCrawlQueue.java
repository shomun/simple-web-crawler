package com.monirul.simplewebcrawler.core.frontier.impl;

import com.monirul.simplewebcrawler.core.CrawlItem;
import com.monirul.simplewebcrawler.core.frontier.CrawlQueue;
import com.monirul.simplewebcrawler.web.task.WebPageFetchTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class InMemoryCrawlQueue implements CrawlQueue<CrawlItem> {

    private static final Logger logger = LoggerFactory.getLogger(WebPageFetchTask.class);

    private Queue<CrawlItem> urlsToCrawl = new LinkedBlockingQueue<>();

    @Override
    public void queue(CrawlItem item) {
        logger.debug("Adding url to crawl : "+ item.getUrl());
        urlsToCrawl.offer(item);
    }

    @Override
    public void queue(List<CrawlItem> items) {
        urlsToCrawl.addAll(items);
    }

    @Override
    public CrawlItem dequeue() {
        return urlsToCrawl.poll();
    }

    @Override
    public List<CrawlItem> dequeue(int numberOfItem) {
        return null;
    }

    @Override
    public int size() {
        return urlsToCrawl.size();
    }

    @Override
    public boolean hasItem() {
        return urlsToCrawl.size()>0;
    }


}
