package com.monirul.simplewebcrawler.web.task;

import com.monirul.simplewebcrawler.web.WebCrawlContext;
import com.monirul.simplewebcrawler.web.WebPage;
import com.monirul.simplewebcrawler.web.task.result.WebPageFetchResult;
import com.monirul.simplewebcrawler.web.task.result.WebPageParseResult;
import org.apache.commons.io.IOUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.jeasy.flows.work.DefaultWorkReport;
import org.jeasy.flows.work.WorkReport;
import org.jeasy.flows.work.WorkStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;

public class WebPageParseTask extends  CrawlTask {
    private static final Logger logger = LoggerFactory.getLogger(WebPageFetchTask.class);

    public WebPageParseTask(WebCrawlContext context) {
        super(context);
    }

    @Override
    public WorkReport call() {

        WebPageFetchResult pageResult = (WebPageFetchResult) context.getPreviouTaskResult();
        String url = pageResult.getUrl();

        logger.debug("Parsing " + url);

        String resouceContent =  "";

        Charset charset;
        try {
            charset = ContentType.getOrDefault(pageResult.getEntity()).getCharset();
        } catch (Exception e) {
            //logger.warn("parse charset failed: {}", e.getMessage());
            charset = Charset.forName("UTF-8");
        }

        try {
            resouceContent = IOUtils.toString(pageResult.getEntity().getContent(), charset);
            context.setPreviouTaskResult(new WebPageParseResult(url, resouceContent));
            context.setPage((WebPage) context.getPreviouTaskResult().getResult());
            report = new DefaultWorkReport(WorkStatus.COMPLETED);
        } catch (IOException e) {
            report = new DefaultWorkReport(WorkStatus.FAILED,e);
        }
        return report;
    }
}
