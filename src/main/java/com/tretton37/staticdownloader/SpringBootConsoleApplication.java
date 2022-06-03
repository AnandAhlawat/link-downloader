package com.tretton37.staticdownloader;

import com.tretton37.staticdownloader.directory.impl.DirectoryServiceImpl;
import com.tretton37.staticdownloader.file.impl.FileServiceImpl;
import com.tretton37.staticdownloader.link.LinkReaderFactory;
import com.tretton37.staticdownloader.util.Constants;
import com.tretton37.staticdownloader.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

/**
 * Startup class for the console application
 */
@Slf4j
@SpringBootApplication
public class SpringBootConsoleApplication implements CommandLineRunner {

    @Autowired
    private LinkReaderFactory linkReaderFactory;

    @Autowired
    private FileServiceImpl fileService;

    @Autowired
    private DirectoryServiceImpl directoryService;

    @Autowired
    private Utils utils;

    @Value("${link}")
    private String linkArgs;

    @Value("${base.dir}")
    private String baseDir;

    public static void main(String[] args) {
        log.info("Starting the application");
        SpringApplication.run(SpringBootConsoleApplication.class, args);
        log.info("Application finished");
    }

    /**
     * Application entry point
     *
     * @param args
     */
    @Override
    public void run(String... args) {
        Set<String> deepLinks = new HashSet<>();
        String[] fileMeta = new String[2];

        linkReaderFactory.getReader(linkArgs).deepSearchSubLinks(linkArgs, Constants.urlRegex, deepLinks);
        deepLinks.stream().forEach(link -> {
                    log.info("starting download , file : {}", link);
                    utils.generateAbsoluteFilePath(link, fileMeta);
                    directoryService.setBaseDirectory(baseDir);
                    directoryService.createDirectory(fileMeta[0]);
                    log.info("downloading file at location : {}",directoryService.getBaseDirectory()+fileMeta[0] + fileMeta[1]);
                    fileService.download(link, directoryService.getBaseDirectory()+fileMeta[0] + fileMeta[1]);
                }
        );
    }
}
