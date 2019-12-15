package com.monirul.simplewebcrawler.web;

import com.monirul.simplewebcrawler.core.CrawlItem;
import com.monirul.simplewebcrawler.core.CrawlSupervisor;
import com.monirul.simplewebcrawler.core.Node;
import com.monirul.simplewebcrawler.core.ResultProcessor;
import com.monirul.simplewebcrawler.core.frontier.Frontier;


/**
 * Supervise a crawl request. It takes a root URL and start crawling by creating a WebPageCrawling task
 * and mange the flow to create new task when more URLs are available
 */
public class WebCrawlSupervisor implements CrawlSupervisor {

    private CrawlItem rootCrawlItem;
    private Frontier frontier;

    private ResultProcessor<Node> resultProcessor;


    /**
     *
     * @param rootUrl
     * @param frontier
     * @param resultProcessor
     */
    public WebCrawlSupervisor(String rootUrl, Frontier frontier, WebCrawlResultProcessor resultProcessor) {
        this.rootCrawlItem = new CrawlItem(rootUrl);
        this.frontier = frontier;
        this.resultProcessor = resultProcessor;
    }


    /**
     * Start a crawl task
     * @param crawlItem
     */
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

    /**
     * manage crawl tasks, get new URLs from queue to process and also skip visiting URLs for already visited URLs
     */
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
