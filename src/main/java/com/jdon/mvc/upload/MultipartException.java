package com.jdon.mvc.upload;

/**
 * User: Asion
 * Date: 13-6-9
 * Time: 下午3:33
 */
public class MultipartException extends RuntimeException {
    public MultipartException() {
        super("不是文件上传, expected: multipart/form-data");
    }
}
