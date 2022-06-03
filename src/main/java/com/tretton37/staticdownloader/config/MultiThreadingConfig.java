package com.tretton37.staticdownloader.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;

import java.util.concurrent.Executors;

@Configuration
@ConditionalOnProperty("file-downloader.multi-threading.enabled")
@EnableAsync(proxyTargetClass = true)
public class MultiThreadingConfig {

    @Value("${file-downloader.thread-pool.size}")
    private int threadPoolSize;

    @Bean(name="fileDownloadTaskExecutor")
    public TaskExecutor fileDownloadTaskExecutor(){
        return new ConcurrentTaskExecutor(Executors.newFixedThreadPool(threadPoolSize));
    }
}
