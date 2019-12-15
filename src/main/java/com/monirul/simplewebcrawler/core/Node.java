package com.monirul.simplewebcrawler.core;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Node {
    private String url;

    private String title;

    private List<Node> nodes;


    public void addNode(Node node){
        if(nodes == null ){
            nodes = new ArrayList<>();
        }
        nodes.add(node);
    }
}
