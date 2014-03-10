package com.jdon.mvc.http;

import java.io.IOException;
import java.io.InputStream;

/**
 * 表示一个上传文件，框架自动会通过name匹配注射到action方法中
 *
 * @author oojdon
 */
public interface FormFile {

    /**
     * 类型
     *
     * @return
     */
    String getContentType();

    /**
     * 大小
     *
     * @return
     */
    long getFileSize();

    /**
     * 字节数组
     *
     * @return
     */
    byte[] getFileData();

    /**
     * 流，其实也可以直接把流写进字节数组，上面直接提供自己数组的方法
     *
     * @return
     * @throws IOException
     */
    InputStream getInputStream() throws IOException;

    /**
     * 返回上传域的参数名
     *
     * @return
     */
    String getName();

    /**
     * 原始文件名
     *
     * @return
     */
    String getOriginalFilename();

    /**
     * 直接写到某个文件
     *
     * @param file
     */
    void transferTo(java.io.File file) throws IOException;


    void clear();

}