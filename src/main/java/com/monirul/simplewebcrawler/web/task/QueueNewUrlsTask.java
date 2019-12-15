package com.monirul.simplewebcrawler.web.task;

import com.monirul.simplewebcrawler.core.CrawlItem;
import com.monirul.simplewebcrawler.core.frontier.Frontier;
import com.monirul.simplewebcrawler.web.WebCrawlContext;
import com.monirul.simplewebcrawler.web.task.result.HyperLinkResult;
import org.jeasy.flows.work.DefaultWorkReport;
import org.jeasy.flows.work.WorkReport;
import org.jeasy.flows.work.WorkStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueueNewUrlsTask extends CrawlTask {

    private static final Logger logger = LoggerFactory.getLogger(WebPageFetchTask.class);

    private Frontier frontier;

    public QueueNewUrlsTask(WebCrawlContext context,Frontier frontier) {
        super(context);
        this.frontier = frontier;
    }

    @Override
    public WorkReport call() {
        HyperLinkResult prevTaskResult = (HyperLinkResult) context.getPreviouTaskResult();
        logger.debug("Adding to queue : "+ context.getPage().getUrl());
        CrawlItem crawlItem = context.getCrawlItem();
        crawlItem.setTitle(context.getPage().getTitle());
        if(frontier.getCrawlTracker().canAddMore()){
            frontier.getCrawlTracker().visitedUrl(crawlItem);
            prevTaskResult.getHyperLinks().forEach(l-> {
                CrawlItem item = new CrawlItem(l,crawlItem.getUrl());
                item.setParentItem(crawlItem);
                frontier.getCrawlQueue().queue(item);
            });
        }


        return new DefaultWorkReport(WorkStatus.COMPLETED);
    }
}
