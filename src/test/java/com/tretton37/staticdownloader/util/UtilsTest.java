package com.tretton37.staticdownloader.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Utils.class})
public class UtilsTest {

    @Autowired
    Utils utils;

    @Test
    public void generateAbsoluteFilePath_link_variations() {

        String link = "https://tretton37.com/assets/i/privacy-policy.jpg";
        String[] fileMeta = new String[2];
        utils.generateAbsoluteFilePath(link,fileMeta);
        Assert.assertEquals(fileMeta[0],"/tretton37.com/assets/i/");
        Assert.assertEquals(fileMeta[1],"privacy-policy.jpg");

        link = "https://www.flickr.com/photos/tretton37/albums";
        fileMeta = new String[2];
        utils.generateAbsoluteFilePath(link,fileMeta);
        Assert.assertEquals(fileMeta[0],"/www.flickr.com/photos/tretton37/albums/");
        Assert.assertEquals(fileMeta[1],"index.html");

        link = "https://unpkg.com/aos@2.3.1/dist/aos.css";
        fileMeta = new String[2];
        utils.generateAbsoluteFilePath(link,fileMeta);
        Assert.assertEquals(fileMeta[0],"/unpkg.com/aos@2.3.1/dist/");
        Assert.assertEquals(fileMeta[1],"aos.css");

        link = "https://tretton37.com#intro";
        fileMeta = new String[2];
        utils.generateAbsoluteFilePath(link,fileMeta);
        Assert.assertEquals(fileMeta[0],"/tretton37.com/");
        Assert.assertEquals(fileMeta[1],"index.html");

        link = "https://tretton37.com/jobs/1130611-ux-designer";
        fileMeta = new String[2];
        utils.generateAbsoluteFilePath(link,fileMeta);
        Assert.assertEquals(fileMeta[0],"/tretton37.com/jobs/1130611-ux-designer/");
        Assert.assertEquals(fileMeta[1],"index.html");

        link = "https://tretton37.com/";
        fileMeta = new String[2];
        utils.generateAbsoluteFilePath(link,fileMeta);
        Assert.assertEquals(fileMeta[0],"/tretton37.com/");
        Assert.assertEquals(fileMeta[1],"index.html");

        link = "https://tretton37.com/humans.txt";
        fileMeta = new String[2];
        utils.generateAbsoluteFilePath(link,fileMeta);
        Assert.assertEquals(fileMeta[0],"/tretton37.com/");
        Assert.assertEquals(fileMeta[1],"humans.txt");
    }
}