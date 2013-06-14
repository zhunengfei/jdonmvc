package com.jdon.mvc.http;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * User: Asion
 * Date: 13-6-14
 * Time: 上午9:42
 */
public class FormFileImp implements FormFile {

    private static final String NOT_UNIX_LIKE_SEPARATOR = "\\";

    private static final String UNIX_LIKE_SEPARATOR = "/";

    private File file;

    private String fileName;

    private String originalFilename;

    private FileItem item;


    public FormFileImp(File file, FileItem item) {
        this.item = item;
        this.file = file;
        this.originalFilename = item.getName();

        if (originalFilename.indexOf(UNIX_LIKE_SEPARATOR) == -1) {
            this.fileName = originalFilename.substring(originalFilename
                    .lastIndexOf(NOT_UNIX_LIKE_SEPARATOR) + 1);
        } else {
            this.fileName = originalFilename.substring(originalFilename
                    .lastIndexOf(UNIX_LIKE_SEPARATOR) + 1);
        }
    }


    public String getContentType() {
        return item.getContentType();
    }

    public long getFileSize(){
        return item.getSize();
    }

    public byte[] getFileData() {
        return item.get();
    }

    public InputStream getInputStream() throws IOException {
        return item.getInputStream();
    }


    public String getFileName() {
        return this.fileName;
    }


    public String getOriginalFilename() {
        return this.originalFilename;
    }

    @Override
    public String getName() {
        return item.getFieldName();
    }

    @Override
    public void transferTo(File dest) throws IOException {
        if (!isAvailable()) {
            throw new IllegalStateException("File has already been moved - cannot be transferred again");
        }

        if (dest.exists() && !dest.delete()) {
            throw new IOException(
                    "Destination file [" + dest.getAbsolutePath() + "] already exists and could not be deleted");
        }

        try {
            this.item.write(dest);
        }
        catch (FileUploadException ex) {
            throw new IllegalStateException(ex.getMessage());
        }
        catch (IOException ex) {
            throw ex;
        }
        catch (Exception ex) {
            throw new IOException("Could not transfer to file: " + ex.getMessage());
        }
    }

    public String toString() {
        return "[uploadedFile location=" + this.file + " uploadedCompleteName="
                + this.originalFilename + " uploadedName=" + this.fileName
                + " contentType=" + this.getContentType() + "";
    }


    protected boolean isAvailable() {
        // If in memory, it's available.
        if (this.item.isInMemory()) {
            return true;
        }
        // Check actual existence of temporary file.
        if (this.item instanceof DiskFileItem) {
            return ((DiskFileItem) this.item).getStoreLocation().exists();
        }

        return false;
    }

}
