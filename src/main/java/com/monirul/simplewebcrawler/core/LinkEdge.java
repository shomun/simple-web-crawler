package com.monirul.simplewebcrawler.core;

import org.jgrapht.graph.DefaultEdge;

public class LinkEdge extends DefaultEdge {

    public CrawlItem getCrawlItem(){
        return (CrawlItem) getTarget();
    }
}
