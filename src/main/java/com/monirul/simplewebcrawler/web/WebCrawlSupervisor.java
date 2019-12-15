package com.monirul.simplewebcrawler.web;

import com.monirul.simplewebcrawler.core.*;
import com.monirul.simplewebcrawler.core.frontier.Frontier;

import java.util.concurrent.ForkJoinPool;

public class WebCrawlSupervisor implements CrawlSupervisor {

    private CrawlItem rootCrawlItem;
    private Frontier frontier;

    private ResultProcessor<Node> resultProcessor;


    public WebCrawlSupervisor(String rootUrl, Frontier frontier, WebCrawlResultProcessor resultProcessor) {
        this.rootCrawlItem = new CrawlItem(rootUrl);
        this.frontier = frontier;
        this.resultProcessor = resultProcessor;
    }





    private void doCrawl(CrawlItem crawlItem) {
        WebPageCrawler crawler = new WebPageCrawler(crawlItem,frontier);
        try {
            Thread.sleep(200);
            crawler.crawl();
            System.out.println(" SUCCESS TO CRAWL #####     "+crawlItem.getUrl());
       } catch (Exception e) {
            System.out.println(" FAILED TO CRAWL #####     "+crawlItem.getUrl());
            e.printStackTrace();
        }

    }

    private void crawlTask() {
        CrawlItem crawlItem = null;

        do{
            if(!frontier.getCrawlTracker().canAddMore()) break;
            crawlItem = frontier.getCrawlQueue().dequeue();
            if(crawlItem != null){
                if(frontier.getCrawlTracker().isUrlVisited(crawlItem)){
                    frontier.getCrawlTracker().visitedUrl(crawlItem);
                    continue;
                }
                doCrawl(crawlItem);
            }
        }while(crawlItem  !=  null);
    }

    @Override
    public boolean start() {
        frontier.getCrawlQueue().queue(rootCrawlItem);
        crawlTask();

        return true;
    }

    @Override
    public Node getResult() {
        return resultProcessor.getResult();
    }
}
