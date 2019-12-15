package com.monirul.simplewebcrawler.api;

import com.google.gson.Gson;
import com.monirul.simplewebcrawler.core.frontier.Frontier;
import com.monirul.simplewebcrawler.core.Node;
import com.monirul.simplewebcrawler.web.WebCrawlResultProcessor;
import com.monirul.simplewebcrawler.web.WebCrawlSupervisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrawlController {

    @Autowired
    private Frontier frontier;

    @Autowired
    private WebCrawlResultProcessor webCrawlResultProcessor;

    @GetMapping("/crawl")
    public String crawl(@RequestParam String url, int maxLinks){
        frontier.getCrawlTracker().setMaxSize(maxLinks);
        WebCrawlSupervisor manager = new WebCrawlSupervisor(url, frontier, webCrawlResultProcessor);
        manager.start();
        Node node = manager.getResult();
        Gson gson = new Gson();
        return gson.toJson(node);
    }
}
