package com.tretton37.staticdownloader;

import com.tretton37.staticdownloader.link.LinkReaderFactory;
import com.tretton37.staticdownloader.util.Constants;
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

    @Value("${link}")
    private String linkArgs;

    public static void main(String[] args){
        log.info("Starting the application");
        SpringApplication.run(SpringBootConsoleApplication.class, args);
        log.info("Application finished");
    }

    /**
     * Application entry point
     * @param args
     */
    @Override public void run(String... args) {
        Set<String> deepLinks = new HashSet<>();

        linkReaderFactory.getReader(linkArgs).deepSearchSubLinks(linkArgs, Constants.urlRegex, deepLinks);
        deepLinks.stream().forEach(System.out::println);
    }
}
