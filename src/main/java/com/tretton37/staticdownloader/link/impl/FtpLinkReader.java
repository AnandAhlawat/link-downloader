package com.tretton37.staticdownloader.link.impl;

import com.tretton37.staticdownloader.link.LinkReader;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Data
public class FtpLinkReader implements LinkReader {

    private String baseLink;

    @Override
    public String read(String link) {
        return null;
    }

    @Override
    public Set<String> searchSubLinks(String link, String regex) {
        return null;
    }

    @Override
    public void deepSearchSubLinks(String link, String regex, Set<String> matchSubLinks) {

    }
}
