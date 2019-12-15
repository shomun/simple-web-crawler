package com.monirul.simplewebcrawler.core.content;

public interface ContentFinder<T,R> {

    R find(T resource);
}
