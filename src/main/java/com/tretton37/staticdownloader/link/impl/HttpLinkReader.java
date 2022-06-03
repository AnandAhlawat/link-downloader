package com.tretton37.staticdownloader.link.impl;

import com.tretton37.staticdownloader.link.LinkReader;
import com.tretton37.staticdownloader.util.Utils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.tretton37.staticdownloader.util.Constants.*;

@Slf4j
@Service
@Data
public class HttpLinkReader implements LinkReader {

    @Autowired
    Utils utils;

    private String baseLink;

    @Override
    public String read(String link) {
        StringBuffer sb = new StringBuffer();
        try {
            URL url = new URL(link);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String htmlLine;
            while ((htmlLine = br.readLine()) != null) {
                sb.append(htmlLine);
            }
        } catch (IOException e) {
            log.error("exception while reading content of link : {}, exception : {}", link, e);
        }
        return sb.toString();
    }

    @Override
    public Set<String> searchSubLinks(String link, String regex) {
        Set<String> links = new HashSet<>();
        StringBuffer sb = new StringBuffer();
        Pattern linkPattern = Pattern.compile(regex);
        Matcher matcher;
        try {
            URL url = new URL(link);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String htmlLine;
            while ((htmlLine = br.readLine()) != null) {
                matcher = linkPattern.matcher(htmlLine);
				while (matcher.find()) {
					link = htmlLine.substring(matcher.start() + 6, matcher.end());
					links.add(link);
				}
            }
        } catch (IOException e) {
            log.error("exception while searching regex in content of link : {}, exception : {}", link, e);
        }
        return links;
    }

    public void deepSearchSubLinks(String link, String regex, Set<String> matchSubLinks) {
        try {
            URL url = link.startsWith(HTTP_CONST) ? new URL(link) : new URL(baseLink + link);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String htmlLine;
            Pattern linkPattern = Pattern.compile(regex);
            Matcher matcher;
            while ((htmlLine = br.readLine()) != null) {
                matcher = linkPattern.matcher(htmlLine);
                while (matcher.find()) {
                    link = htmlLine.substring(matcher.start() + 6, matcher.end());
                    link = link.startsWith(HTTP_CONST) ? link : baseLink + link;
                    matchSubLinks.add(link);
                    if (!matchSubLinks.contains(link) && (utils.getFileExtension(link).equals(HTML_CONST) || utils.getFileExtension(link).equals(BLANK_CONST)))
                        deepSearchSubLinks(link, regex, matchSubLinks);
                }
            }
        } catch (IOException e) {
            log.error("exception while deep searching regex in content of link : {}, exception : {}", link, e);
        }
    }
}
