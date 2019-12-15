package com.monirul.simplewebcrawler.web.task;

import com.monirul.simplewebcrawler.core.CrawlItem;
import com.monirul.simplewebcrawler.http.request.HttpGetCommand;
import com.monirul.simplewebcrawler.web.WebCrawlContext;
import com.monirul.simplewebcrawler.web.task.result.WebPageFetchResult;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.jeasy.flows.work.WorkReport;
import org.jeasy.flows.work.WorkStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WebPageFetchTaskTest {

    private HttpGetCommand httpRequestMock;
    private WebPageFetchTask classUnderTest;
    private StatusLine statusLineMock;

    @BeforeEach
    void setUp() {
        httpRequestMock = mock(HttpGetCommand.class);

    }

    @Test
    void should_crawl_fetch_page_content_successfully() throws IOException {
        WebCrawlContext context = new WebCrawlContext();
        context.setCrawlItem(new CrawlItem("http://www.google.com"));
        CloseableHttpResponse responseMock  = mock(CloseableHttpResponse.class);;
        StatusLine statusLineMock = mock(StatusLine.class);
        classUnderTest = new WebPageFetchTask(context, httpRequestMock);



        when(httpRequestMock.execute(context.getCrawlItem().getUrl())).thenReturn(responseMock);
        when(responseMock.getStatusLine()).thenReturn(statusLineMock);
        when(statusLineMock.getStatusCode()).thenReturn(200);

        WorkReport report = classUnderTest.call();

        assertEquals(WorkStatus.COMPLETED, report.getStatus());
        assertEquals("http://www.google.com", ((WebPageFetchResult)context.getPreviouTaskResult()).getUrl() );
    }
}