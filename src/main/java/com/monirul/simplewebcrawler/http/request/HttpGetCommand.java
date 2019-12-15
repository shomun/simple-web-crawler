package com.monirul.simplewebcrawler.http.request;

import com.monirul.simplewebcrawler.http.HttpClientFactory;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HttpGetCommand implements HttpRequestCommand<String, CloseableHttpResponse> {

    private static final Logger logger = LoggerFactory.getLogger(HttpGetCommand.class);

    private RequestConfig config;

    public HttpGetCommand(RequestConfig config) {
        this.config = config;
    }

    @Override
    public CloseableHttpResponse execute(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClientFactory.getHttpClient();
        HttpGet request = new HttpGet(url);
        logger.debug("Executing HTTP GET request");
        return httpClient.execute(request);
    }
}
