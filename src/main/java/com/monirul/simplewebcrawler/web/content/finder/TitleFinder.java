package com.monirul.simplewebcrawler.web.content.finder;

import com.monirul.simplewebcrawler.core.content.ContentFinder;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TitleFinder implements ContentFinder<String, String> {

    private Pattern pattern = Pattern.compile("<title.*>(.*)<\\/title>");
    @Override
    public String find(String content) {
        String title = "";
        Matcher matcher = pattern.matcher(content);

        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            title = matcher.group(1);
        }
        return title;

    }
}
