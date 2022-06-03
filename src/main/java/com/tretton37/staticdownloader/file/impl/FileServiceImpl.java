package com.tretton37.staticdownloader.file.impl;

import com.tretton37.staticdownloader.file.FileService;
import com.tretton37.staticdownloader.util.Utils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static com.tretton37.staticdownloader.util.Constants.FILE_DOWNLOAD_TASK_EXECUTOR;

@Service
@Slf4j
@Data
public class FileServiceImpl implements FileService {

    @Autowired
    Utils utils;

    /**
     * read the file pointed by absoluteFilePath
     * in the absoluteFilePath
     *
     * @param absoluteFilePath
     */
    @Override
    public String readFile(String absoluteFilePath) {
        StringBuffer br = new StringBuffer();
        try (Stream<String> stream = Files.lines(Paths.get(absoluteFilePath))) {
            stream.forEach(br::append);
        } catch (IOException e) {
            log.error("Error reading file, fileName : {}",absoluteFilePath);
        }
        return br.toString();
    }

    @Override
    @Async(FILE_DOWNLOAD_TASK_EXECUTOR)
    public void download(String link, String absoluteFilePath) {
        try{
            URL url = new URL(link);
            HttpURLConnection httpURLConnection = (HttpURLConnection) (url.openConnection());
            BufferedInputStream in = new BufferedInputStream(httpURLConnection.getInputStream());
            FileOutputStream fileOutputStream = new FileOutputStream(absoluteFilePath);
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            long downloadedFileSize = 0;
            long completeFileSize = httpURLConnection.getContentLength();

            long startTime = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                downloadedFileSize += bytesRead;
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                utils.printProgress(startTime,completeFileSize,downloadedFileSize);
            }
        } catch (IOException e) {
            log.error("error while downloading the fileName : {}, link : {}",absoluteFilePath,link);
        }
    }

    @Override
    public void upload(String content, String link) {
        log.info("future implementation");
    }
}
