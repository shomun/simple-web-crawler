package com.monirul.simplewebcrawler.http.request;

import java.io.IOException;

public interface HttpRequestCommand<T, R> {

    R execute(T request) throws IOException;
}
