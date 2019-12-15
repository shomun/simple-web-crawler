package com.monirul.simplewebcrawler.web;

import com.monirul.simplewebcrawler.core.CrawlItem;
import com.monirul.simplewebcrawler.core.Crawler;
import com.monirul.simplewebcrawler.core.frontier.Frontier;
import com.monirul.simplewebcrawler.http.request.HttpGetCommand;
import com.monirul.simplewebcrawler.web.content.finder.ExternalLinkFinder;
import com.monirul.simplewebcrawler.web.content.finder.HyperLinkFinder;
import com.monirul.simplewebcrawler.web.content.finder.TitleFinder;
import com.monirul.simplewebcrawler.web.task.*;
import org.jeasy.flows.work.WorkReport;
import org.jeasy.flows.work.WorkStatus;
import org.jeasy.flows.workflow.SequentialFlow;

import java.util.ArrayList;
import java.util.List;

public class WebPageCrawler implements Crawler{


    private CrawlItem crawlItem;

    private Frontier frontier;

    public WebPageCrawler(Frontier frontier) {
        this.frontier = frontier;
    }

    public WebPageCrawler(CrawlItem crawlItem, Frontier frontier) {
        this.crawlItem = crawlItem;
        this.frontier = frontier;
    }

    @Override
    public void crawl() throws Exception {
        WebCrawlContext context = new WebCrawlContext();
        context.setCrawlItem(crawlItem);
        List<HyperLinkFinder> hyperLinkFinders = new ArrayList<>();
        hyperLinkFinders.add(new ExternalLinkFinder());
        SequentialFlow crawlUnitOfWork = SequentialFlow.Builder.aNewSequentialFlow()
                .execute(new WebPageFetchTask(context, new HttpGetCommand(null)))
                .then(new WebPageParseTask(context))
                .then(new HtmlTitleFinderTask(context, new TitleFinder()))
                .then(new HtmlHyperLinkFinderTask(context,hyperLinkFinders))
                .then(new QueueNewUrlsTask(context, frontier))
                .build();
        WorkReport taskReport = crawlUnitOfWork.call();
        if(taskReport.getStatus() == WorkStatus.FAILED){
              throw new Exception(taskReport.getError());
        }
    }


}
