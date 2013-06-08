package com.jdon.mvc.scan;


import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class IteratorFactory {

    public static StreamIterator create(URL url, Filter filter) throws IOException {
        String urlString = url.toString();
        if (urlString.endsWith("!/")) {
            urlString = urlString.substring(4);
            urlString = urlString.substring(0, urlString.length() - 2);
            url = new URL(urlString);
        }

        if (!urlString.endsWith("/")) {
            return new JarIterator(url.openStream(), filter);
        } else {
            File f = new File(url.getPath());
            if (f.isDirectory()) {
                return new FileIterator(f, filter);
            } else {
                return new JarIterator(url.openStream(), filter);
            }
        }
    }
}
