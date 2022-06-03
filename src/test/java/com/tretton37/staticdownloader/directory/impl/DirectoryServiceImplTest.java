package com.tretton37.staticdownloader.directory.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DirectoryServiceImpl.class})
public class DirectoryServiceImplTest {

    @Autowired
    DirectoryServiceImpl directoryService;

    @Test
    public void createDirectory_test() {
        directoryService.setBaseDirectory("/tmp");
        directoryService.createDirectory("test_case");
        File dir = new File("/tmp/test_case");
        Assert.assertTrue(dir.exists());
    }
}