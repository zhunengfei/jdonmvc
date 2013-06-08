package com.jdon.mvc.scan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class FileIterator implements StreamIterator {

    private ArrayList files;

    private int index = 0;

    public FileIterator(File file, Filter filter) {
        files = new ArrayList();
        try {
            create(file, filter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void create(File dir, Filter filter) throws Exception {
        File[] fileList = dir.listFiles();
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isDirectory()) {
                create(fileList[i], filter);
            } else {
                if (filter == null || filter.accepts(fileList[i].getAbsolutePath())) {
                    files.add(fileList[i]);
                }
            }
        }
    }

    public InputStream next() {
        if (index >= files.size()) return null;
        File fp = (File) files.get(index++);
        try {
            return new FileInputStream(fp);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
    }
}
