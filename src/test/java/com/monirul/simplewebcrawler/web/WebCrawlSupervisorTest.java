package com.monirul.simplewebcrawler.web;

import com.monirul.simplewebcrawler.core.Node;
import com.monirul.simplewebcrawler.core.ResultProcessor;
import com.monirul.simplewebcrawler.core.frontier.CrawlQueue;
import com.monirul.simplewebcrawler.core.frontier.CrawlTracker;
import com.monirul.simplewebcrawler.core.frontier.Frontier;
import com.monirul.simplewebcrawler.core.frontier.impl.InMemoryCrawlQueue;
import com.monirul.simplewebcrawler.core.frontier.impl.InMemoryCrawlTracker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WebCrawlSupervisorTest {

    private WebCrawlSupervisor classUnderTest;
    private  Frontier frontier;

    @BeforeEach
    void setUp() {
        CrawlQueue crawlQueue = new InMemoryCrawlQueue();
        CrawlTracker crawlTracker = new InMemoryCrawlTracker();
        WebCrawlResultProcessor resultProcessor = new WebCrawlResultProcessor(crawlTracker);
        frontier = new Frontier(crawlQueue,crawlTracker);

        classUnderTest = new WebCrawlSupervisor("http://crawltest1.s3-ap-southeast-2.amazonaws.com/a.html",frontier,resultProcessor);
    }

    @Test
    void process_should_success() {
        classUnderTest.start();
        Node resultNode = classUnderTest.getResult();
        assertEquals(11, frontier.getCrawlTracker().size());

    }

    @Test
    void process_max_3_links_should_success() {
        frontier.getCrawlTracker().setMaxSize(3);
        classUnderTest.start();
        Node resulNode = classUnderTest.getResult();
        assertEquals(3, frontier.getCrawlTracker().size());

    }
}