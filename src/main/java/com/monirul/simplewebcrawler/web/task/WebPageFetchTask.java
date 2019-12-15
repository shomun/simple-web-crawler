package com.monirul.simplewebcrawler.web.task;

import com.monirul.simplewebcrawler.http.HttpStatus;
import com.monirul.simplewebcrawler.http.request.HttpGetCommand;
import com.monirul.simplewebcrawler.web.WebCrawlContext;
import com.monirul.simplewebcrawler.web.WebPage;
import com.monirul.simplewebcrawler.web.task.result.WebPageFetchResult;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.jeasy.flows.work.DefaultWorkReport;
import org.jeasy.flows.work.WorkReport;
import org.jeasy.flows.work.WorkStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class WebPageFetchTask extends CrawlTask {

    private static final Logger logger = LoggerFactory.getLogger(WebPageFetchTask.class);

    private HttpGetCommand httpGetRequest;


    public WebPageFetchTask(WebCrawlContext context,HttpGetCommand httpGetRequest) {
        super(context);
        this.httpGetRequest = httpGetRequest;
    }

    @Override
    public WorkReport call() {
        WebPage page = null;

        CloseableHttpResponse response = null;
        try {
            String url = context.getCrawlItem().getUrl();
            logger.debug("Fetching url : "+ url);
            response = httpGetRequest.execute(url);
            final HttpStatus status = HttpStatus.fromHttpCode(response.getStatusLine().getStatusCode());

            if (HttpStatus.OK.equals(status)) {
                context.setPreviouTaskResult(new WebPageFetchResult(url,response.getEntity()));
            }
            report = new DefaultWorkReport(WorkStatus.COMPLETED);
        } catch (IOException e) {
            report = new DefaultWorkReport(WorkStatus.FAILED,e);
        }
        return report;
    }
}
