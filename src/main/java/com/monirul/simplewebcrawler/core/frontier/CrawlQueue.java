package com.monirul.simplewebcrawler.core.frontier;

import java.util.List;

public interface CrawlQueue<T> {

    void queue(T item);

    void queue(List<T> items);

    T dequeue();

    List<T> dequeue(int numberOfItem);

    int size();

    boolean hasItem();

}
