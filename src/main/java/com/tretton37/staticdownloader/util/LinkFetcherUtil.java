package com.tretton37.staticdownloader.util;

public class LinkFetcherUtil {

    /**
     * Get extension of any link
     *
     * @param fileName
     * @return
     */
    public static String getFileExtension(String fileName) {
        int lastIndexOf = fileName.lastIndexOf(Constants.DOT_CONST);
        if (lastIndexOf == -1) {
            return Constants.BLANK_CONST; // empty extension
        }
        return fileName.substring(lastIndexOf);
    }
}
