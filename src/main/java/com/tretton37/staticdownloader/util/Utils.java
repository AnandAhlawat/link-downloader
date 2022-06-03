package com.tretton37.staticdownloader.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import static com.tretton37.staticdownloader.util.Constants.*;

@Slf4j
@Component
/**
 * Util methods or common code
 */
public class Utils {
    /**
     * Get extension of any link
     *
     * @param fileName
     * @return
     */
    public String getFileExtension(String fileName) {
        int lastIndexOf = fileName.lastIndexOf(Constants.DOT_CONST);
        if (lastIndexOf == -1) {
            return Constants.BLANK_CONST; // empty extension
        }
        return fileName.substring(lastIndexOf);
    }

    /**
     * Generate the path and the name of the file to be saved
     * fileMeta[0] : directory path  | fileMeta[1] : fileName
     *
     * @param link
     * @param fileMeta
     */
    public void generateAbsoluteFilePath(String link, String[] fileMeta) {

        String uri = link.substring(link.indexOf(FWD_DOUBLE_SLASH_CONST) + 2);
        if(uri.contains(HASH_CONST))
            uri = uri.substring(0,uri.indexOf(HASH_CONST));
        String hostName = uri.indexOf(FWD_SLASH_CONST) != -1 ? uri.substring(0, uri.indexOf(FWD_SLASH_CONST)) : uri;
        if (!uri.contains(FWD_SLASH_CONST)) {
            if (!uri.contains(HASH_CONST)) {
                fileMeta[0] = FWD_SLASH_CONST;
                fileMeta[1] = INDEX_HTML;
            }
        } else {
            uri = uri.substring(uri.indexOf(FWD_SLASH_CONST));
            fileMeta[1] = uri.substring(uri.lastIndexOf(FWD_SLASH_CONST) + 1);
            // No extension
            if (fileMeta[1].indexOf(DOT_CONST) == -1) {
                fileMeta[0] = uri;
                fileMeta[1] = INDEX_HTML;
            } else {
                uri = uri.substring(0, uri.lastIndexOf(FWD_SLASH_CONST)+1);
                fileMeta[0] = uri;
            }
            //log.info("fileMeta[0] : {}", fileMeta[0]);
            if (fileMeta[0].length() == 0) {
                fileMeta[0] = FWD_SLASH_CONST + fileMeta[0];
            }
            if (fileMeta[0].length() != 0 && !fileMeta[0].substring(fileMeta[0].length() - 1).equalsIgnoreCase(FWD_SLASH_CONST)) {
                fileMeta[0] = fileMeta[0] + FWD_SLASH_CONST;
            }
        }
        if(hostName.lastIndexOf(FWD_SLASH_CONST)!=hostName.length() && !fileMeta[0].substring(0,1).equalsIgnoreCase(FWD_SLASH_CONST) )
            fileMeta[0] = FWD_SLASH_CONST + hostName + FWD_SLASH_CONST + fileMeta[0];
        else
            fileMeta[0] = FWD_SLASH_CONST + hostName + fileMeta[0];
    }

    /**
     * Method to print the progress of downloading
     *
     * @param startTime
     * @param total
     * @param current
     */
    public void printProgress(long startTime, long total, long current) {
        try {
            long eta = current == 0 ? 0 :
                    (total - current) * (System.currentTimeMillis() - startTime) / current;

            String etaHms = current == 0 ? "N/A" :
                    String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(eta),
                            TimeUnit.MILLISECONDS.toMinutes(eta) % TimeUnit.HOURS.toMinutes(1),
                            TimeUnit.MILLISECONDS.toSeconds(eta) % TimeUnit.MINUTES.toSeconds(1));

            StringBuilder string = new StringBuilder(140);
            int percent = (int) (current * 100 / total);
            string
                    .append('\r')
                    .append(String.join("", Collections.nCopies(percent == 0 ? 2 : 2 - (int) (Math.log10(percent)), " ")))
                    .append(String.format(" %d%% [", percent))
                    .append(String.join("", Collections.nCopies(percent, "=")))
                    .append('>')
                    .append(String.join("", Collections.nCopies(100 - percent, " ")))
                    .append(']')
                    .append(String.join("", Collections.nCopies(current == 0 ? (int) (Math.log10(total)) : (int) (Math.log10(total)) - (int) (Math.log10(current)), " ")))
                    .append(String.format(" %d/%d, ETA: %s", current, total, etaHms));

            System.out.print(string);
        } catch (Exception e){}

    }
}
