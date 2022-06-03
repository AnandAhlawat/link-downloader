package com.tretton37.staticdownloader.directory;

/**
 * Directory related operations
 */
public interface DirectoryService {

    /**
     * create new directory, If it doesn't exist
     * @param name
     */
    void createDirectory(String name);

}
