package com.tretton37.staticdownloader.link;

import java.util.Set;

/**
 * Common implementation of the reading logic of Link
 */
public interface LinkReader {

    /**
     * read content of the link
     * @param link
     * @return
     */
    String read(String link);

    /**
     * search content in the link matching with the regex
     * @param link
     * @param regex
     * @return
     */
    Set<String> searchSubLinks(String link, String regex);

    /**
     * Deep search in the sub-links for the matches with the regex
     * @param link
     * @param regex
     * @param matchSubLinks
     */
    void deepSearchSubLinks(String link, String regex, Set<String> matchSubLinks);
}
