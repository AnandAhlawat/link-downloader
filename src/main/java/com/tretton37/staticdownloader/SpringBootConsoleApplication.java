package com.tretton37.staticdownloader;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

/**
 * Startup class for the console application
 */
@Slf4j
public class SpringBootConsoleApplication implements CommandLineRunner {
    /**
     * Application entry point
     * @param args
     */
    public static void main(String[] args){
        log.info("Starting the application");
        SpringApplication.run(SpringBootConsoleApplication.class, args);
        log.info("Application finished");
    }

    @Override public void run(String... args) throws Exception {

    }
}
