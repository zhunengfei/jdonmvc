package com.jdon.mvc.http;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 表示一个上传文件，框架自动会通过name匹配注射到action方法中
 *
 * @author oojdon
 */
public interface FormFile {

    String getContentType();

    long getFileSize();

    byte[] getFileData();

    InputStream getInputStream() throws IOException;

    File getFile();

    String getFileName();

    String getOriginalFilename();

}