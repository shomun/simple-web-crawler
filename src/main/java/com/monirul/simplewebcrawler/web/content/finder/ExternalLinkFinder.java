package com.monirul.simplewebcrawler.web.content.finder;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExternalLinkFinder extends HyperLinkFinder {


    private Pattern pattern = Pattern.compile("(?i)(?s)<\\s*?a.*?href=\"(.*?)\".*?>");


    @Override
    public List<String> find(String content) {
        Matcher matcher = pattern.matcher(content);

        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group(1));
        }

        return list;
    }
}
