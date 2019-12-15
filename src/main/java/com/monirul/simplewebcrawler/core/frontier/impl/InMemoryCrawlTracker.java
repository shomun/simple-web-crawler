package com.monirul.simplewebcrawler.core.frontier.impl;

import com.monirul.simplewebcrawler.core.CrawlItem;
import com.monirul.simplewebcrawler.core.frontier.CrawlTracker;
import com.monirul.simplewebcrawler.core.LinkEdge;
import com.monirul.simplewebcrawler.web.task.WebPageFetchTask;
import org.apache.commons.lang3.StringUtils;
import org.jgrapht.Graph;
import org.jgrapht.graph.DirectedMultigraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InMemoryCrawlTracker implements CrawlTracker {
    private static final Logger logger = LoggerFactory.getLogger(WebPageFetchTask.class);

    private int maxUrlAllowed = 100;



    private Graph<CrawlItem, LinkEdge> vistedURLGraph =
            new DirectedMultigraph<CrawlItem,LinkEdge>(LinkEdge.class);
    @Override
    public boolean isUrlVisited(CrawlItem crawlItem) {
        return vistedURLGraph.containsVertex(crawlItem);
    }

    @Override
    public void visitedUrl(CrawlItem item) {
        String url = item.getUrl();
        String parentUrl = item.getParentUrl();
        vistedURLGraph.addVertex(item);
        if(StringUtils.isNotEmpty(parentUrl) && !url.equals(parentUrl)){
            vistedURLGraph.addEdge(item.getParentItem() ,item);
        }
    }

    @Override
    public int size() {
        return vistedURLGraph.vertexSet().size();
    }

    @Override
    public boolean canAddMore() {
        return maxUrlAllowed > size();
    }

    @Override
    public void setMaxSize(int maxSize) {
        this.maxUrlAllowed = maxSize;
    }

    public Graph<CrawlItem, LinkEdge> getVistedURLGraph(){
        return vistedURLGraph;
    }
}
