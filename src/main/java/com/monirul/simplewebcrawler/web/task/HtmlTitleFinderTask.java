package com.monirul.simplewebcrawler.web.task;

import com.monirul.simplewebcrawler.web.WebCrawlContext;
import com.monirul.simplewebcrawler.web.content.finder.TitleFinder;
import com.monirul.simplewebcrawler.web.task.result.WebPageParseResult;
import org.jeasy.flows.work.DefaultWorkReport;
import org.jeasy.flows.work.WorkReport;
import org.jeasy.flows.work.WorkStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HtmlTitleFinderTask extends CrawlTask {
    private static final Logger logger = LoggerFactory.getLogger(WebPageFetchTask.class);

    private TitleFinder titleFinder;

    public HtmlTitleFinderTask(WebCrawlContext context,TitleFinder titleFinder) {
        super(context);
        this.titleFinder = titleFinder;
    }

    @Override
    public WorkReport call() {
        WebPageParseResult parseResult = (WebPageParseResult) context.getPreviouTaskResult();
        logger.debug("Find Title for url :"+parseResult.getUrl());
        String title =  titleFinder.find(parseResult.getContent());
        if(title != null){
            context.getPage().setTitle(title);
        }
        return new DefaultWorkReport(WorkStatus.COMPLETED);
    }
}
