package com.tretton37.staticdownloader.link;

import com.tretton37.staticdownloader.link.impl.FtpLinkReader;
import com.tretton37.staticdownloader.link.impl.HttpLinkReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.tretton37.staticdownloader.util.Constants.*;

@Service
public class LinkReaderFactory {

    @Autowired
    HttpLinkReader httpLinkReader;
    @Autowired
    FtpLinkReader ftpLinkReader;

    public LinkReader getReader(String link){
        LinkReader reader = null;
        String protocol = link.substring(0,link.indexOf(DOUBLE_SLASH)-1);
        if(protocol.equalsIgnoreCase(HTTP_CONST) || protocol.equalsIgnoreCase(HTTPS_CONST)){
            httpLinkReader.setBaseLink(link);
            reader = httpLinkReader;
        } else if(protocol.equalsIgnoreCase(FTP_CONST)){
            ftpLinkReader.setBaseLink(link);
            reader = ftpLinkReader;
        }
        return reader;
    }
}
