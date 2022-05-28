package com.tretton37.staticdownloader.link;

import java.util.Set;

/**
 * Common implementation of the reading logic of Link
 */
public interface LinkReader {

    String read(String link);

    Set<String> searchSubLinks(String link, String regex);

    void deepSearchSubLinks(String link, String regex, Set<String> matchSubLinks);
}
