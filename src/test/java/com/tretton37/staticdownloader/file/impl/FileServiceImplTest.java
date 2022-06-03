package com.tretton37.staticdownloader.file.impl;

import com.tretton37.staticdownloader.util.Utils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {FileServiceImpl.class, Utils.class})
public class FileServiceImplTest {

    @Autowired
    FileServiceImpl fileService;

    @Test
    public void download_file_success() {
        fileService.download("https://tretton37.com/humans.txt","/tmp/humans.txt");
        String contents = fileService.readFile("/tmp/humans.txt");
        Assert.assertNotNull(contents);
        Assert.assertNotEquals(contents,"");
    }
}