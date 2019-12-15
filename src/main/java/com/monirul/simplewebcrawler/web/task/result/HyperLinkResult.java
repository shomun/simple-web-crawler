package com.monirul.simplewebcrawler.web.task.result;

import com.monirul.simplewebcrawler.web.task.CrawlTask;
import lombok.Data;

import java.util.List;

@Data
public class HyperLinkResult implements CrawlTaskResult<List<String>> {

    private List<String> hyperLinks;

    @Override
    public List<String> getResult() {
        return hyperLinks;
    }
}
