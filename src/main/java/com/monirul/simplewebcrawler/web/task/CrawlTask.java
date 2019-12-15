package com.monirul.simplewebcrawler.web.task;

import com.monirul.simplewebcrawler.web.WebCrawlContext;
import org.jeasy.flows.work.Work;
import org.jeasy.flows.work.WorkReport;

public  abstract class CrawlTask implements Work {

    protected WebCrawlContext context;

    protected String taskName;

    protected  WorkReport report =null;

    protected CrawlTask(WebCrawlContext context){
        this.context = context;
    }

    @Override
    public String getName() {
        return taskName;
    }
}
