package com.tretton37.staticdownloader.directory.impl;

import com.tretton37.staticdownloader.directory.DirectoryService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.File;

@Data
@Service
public class DirectoryServiceImpl implements DirectoryService {

    private String baseDirectory;

    @Override
    public void createDirectory(String name) {
        File dir = new File(baseDirectory + "/" + name);
        if (!dir.exists())
            dir.mkdirs();
    }
}
