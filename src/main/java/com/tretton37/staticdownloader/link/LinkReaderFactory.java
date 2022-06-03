package com.tretton37.staticdownloader.link;

import com.tretton37.staticdownloader.link.impl.FtpLinkReader;
import com.tretton37.staticdownloader.link.impl.HttpLinkReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.tretton37.staticdownloader.util.Constants.*;

@Service
/**
 * Factory implementation of the reader logic
 */
public class LinkReaderFactory {

    @Autowired
    HttpLinkReader httpLinkReader;

    @Autowired
    FtpLinkReader ftpLinkReader;

    /**
     * Get the correct reader, depending upon the type of link provided
     *
     * @param link
     * @return
     */
    public LinkReader getReader(String link) {
        LinkReader reader = null;
        String protocol = link.substring(0, link.indexOf(FWD_DOUBLE_SLASH_CONST) - 1);
        if (protocol.equalsIgnoreCase(HTTP_CONST) || protocol.equalsIgnoreCase(HTTPS_CONST)) {
            httpLinkReader.setBaseLink(link);
            reader = httpLinkReader;
        } else if (protocol.equalsIgnoreCase(FTP_CONST)) {
            ftpLinkReader.setBaseLink(link);
            reader = ftpLinkReader;
        }
        return reader;
    }
}
