package com.monirul.simplewebcrawler;

import com.monirul.simplewebcrawler.core.Node;
import com.monirul.simplewebcrawler.core.frontier.*;
import com.monirul.simplewebcrawler.core.frontier.impl.InMemoryCrawlQueue;
import com.monirul.simplewebcrawler.core.frontier.impl.InMemoryCrawlTracker;
import com.monirul.simplewebcrawler.web.WebCrawlResultProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {


    @Bean("webCrawlResultProcessor")
    public WebCrawlResultProcessor getWebCrawlResultProcessor(CrawlTracker crawlTracker){
        return new WebCrawlResultProcessor(crawlTracker);
    }

    @Bean("frontier")
    public Frontier getFrontier(CrawlQueue crawlQueue, CrawlTracker crawlTracker){
        return new Frontier(crawlQueue,crawlTracker);
    }

    @Bean("crawlQueue")
    public CrawlQueue getCrawlQueue(){
        return new InMemoryCrawlQueue();
    }

    @Bean("crawlTracker")
    public CrawlTracker getCrawlTracker(){
        return new InMemoryCrawlTracker();
    }
}
