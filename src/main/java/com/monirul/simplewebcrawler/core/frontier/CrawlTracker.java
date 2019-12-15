package com.monirul.simplewebcrawler.core.frontier;

import com.monirul.simplewebcrawler.core.CrawlItem;

public interface CrawlTracker {

    boolean isUrlVisited(CrawlItem item);

    void visitedUrl(CrawlItem item);

    int size();

    boolean canAddMore();

    void setMaxSize(int maxSize);
}
