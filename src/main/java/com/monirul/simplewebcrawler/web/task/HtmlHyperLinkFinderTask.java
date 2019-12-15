package com.monirul.simplewebcrawler.web.task;

import com.monirul.simplewebcrawler.web.content.finder.HyperLinkFinder;
import com.monirul.simplewebcrawler.web.WebCrawlContext;
import com.monirul.simplewebcrawler.web.task.result.HyperLinkResult;
import com.monirul.simplewebcrawler.web.task.result.WebPageParseResult;
import org.jeasy.flows.work.DefaultWorkReport;
import org.jeasy.flows.work.WorkReport;
import org.jeasy.flows.work.WorkStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class HtmlHyperLinkFinderTask extends CrawlTask {
    private static final Logger logger = LoggerFactory.getLogger(WebPageFetchTask.class);

    private List<HyperLinkFinder> linkFinders;

    public HtmlHyperLinkFinderTask(WebCrawlContext context, List<HyperLinkFinder> linkFinders) {
        super(context);
        this.linkFinders = linkFinders;
    }

    @Override
    public WorkReport call() {
        WebPageParseResult parseResult = (WebPageParseResult) context.getPreviouTaskResult();
        logger.debug("Find Hyperlinks for url :"+parseResult.getUrl());
        List<String> allLinks = new ArrayList<>();

        linkFinders.forEach(finder-> {
               allLinks.addAll(finder.find(parseResult.getContent()));
            }
        ) ;


        HyperLinkResult hyperLinkResult = new HyperLinkResult();
        hyperLinkResult.setHyperLinks(allLinks);
        context.setPreviouTaskResult(hyperLinkResult);
        if(context.getPage() != null){
            context.getPage().setHyperLinks(allLinks);
        }
        return new DefaultWorkReport(WorkStatus.COMPLETED);
    }
}
