package com.tretton37.staticdownloader.file;

/**
 * File related operations
 */
public interface FileService {

    /**
     * read the file pointed by absoluteFilePath
     * in the absoluteFilePath
     * @param absoluteFilePath
     */
    String readFile(String absoluteFilePath);

    /**
     * download the link in the file provided
     * in the absoluteFilePath
     * @param link
     * @param absoluteFilePath
     */
    void download(String link, String absoluteFilePath);

    /**
     * Upload the contents to the link
     * @param content
     * @param link
     */
    void upload(String content, String link);

}
