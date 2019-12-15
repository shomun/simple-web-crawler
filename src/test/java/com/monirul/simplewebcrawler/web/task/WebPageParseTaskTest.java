package com.monirul.simplewebcrawler.web.task;

import com.monirul.simplewebcrawler.http.request.HttpGetCommand;
import com.monirul.simplewebcrawler.web.WebCrawlContext;
import com.monirul.simplewebcrawler.web.task.result.WebPageFetchResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class WebPageParseTaskTest {

    private WebPageParseTask classUnderTest;


    @BeforeEach
    void setUp() {

    }

    @Test
    void should_parse_valid_htmlcontent_successfully() {
        WebCrawlContext context = new WebCrawlContext();
        WebPageFetchResult result = mock(WebPageFetchResult.class);

        classUnderTest = new WebPageParseTask(context);

        classUnderTest.call();
    }
}