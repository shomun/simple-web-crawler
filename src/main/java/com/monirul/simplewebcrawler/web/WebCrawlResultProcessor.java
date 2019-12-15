package com.monirul.simplewebcrawler.web;

import com.monirul.simplewebcrawler.core.CrawlItem;
import com.monirul.simplewebcrawler.core.LinkEdge;
import com.monirul.simplewebcrawler.core.Node;
import com.monirul.simplewebcrawler.core.ResultProcessor;
import com.monirul.simplewebcrawler.core.frontier.CrawlTracker;
import com.monirul.simplewebcrawler.core.frontier.impl.InMemoryCrawlTracker;
import org.jgrapht.traverse.DepthFirstIterator;

import java.util.Iterator;


/**
 * A Result processor to output the urls graph in tree representation as node
 * @param <N>
 */
public class WebCrawlResultProcessor implements ResultProcessor<Node> {

    private CrawlTracker crawlTracker;

    public WebCrawlResultProcessor(CrawlTracker crawlTracker) {
        this.crawlTracker = crawlTracker;
    }

    @Override
    public Node getResult() {
        InMemoryCrawlTracker crawlTracker = (InMemoryCrawlTracker) this.crawlTracker;
        Iterator<CrawlItem> iter = new DepthFirstIterator<>(crawlTracker.getVistedURLGraph());
        Node root = null;

        while (iter.hasNext()) {
            CrawlItem vertex = iter.next();
            if(crawlTracker.getVistedURLGraph().incomingEdgesOf(vertex).size() == 0){
                root = new Node();
                root.setUrl(vertex.getUrl());
                root.setTitle(vertex.getTitle());
                Iterator children = crawlTracker.getVistedURLGraph().outgoingEdgesOf(vertex).iterator();
                createNodeStructure(root,children ,crawlTracker );
                break;
            }
        }
        iter = new DepthFirstIterator<>( crawlTracker.getVistedURLGraph());
        while (iter.hasNext()) {
            CrawlItem vertex = iter.next();
            System.out.println(
                    "Vertex " + vertex + " is connected to: "
                            + crawlTracker.getVistedURLGraph().outgoingEdgesOf(vertex).toString());
        }
        return root;
    }

    private void  createNodeStructure(Node parentNode, Iterator<LinkEdge> vertexList, InMemoryCrawlTracker crawlTracker){
        while(vertexList.hasNext()){
            LinkEdge edge = vertexList.next();
            Node child = new Node();
            CrawlItem crawlItem = edge.getCrawlItem();
            child.setUrl(crawlItem.getUrl());
            child.setTitle(crawlItem.getTitle());
            parentNode.addNode(child);
            Iterator children = crawlTracker.getVistedURLGraph().outgoingEdgesOf(crawlItem).iterator();
            createNodeStructure(child,children ,crawlTracker);
        }

    }
}
