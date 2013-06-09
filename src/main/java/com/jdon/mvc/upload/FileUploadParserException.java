package com.jdon.mvc.upload;

/**
 * User: Asion
 * Date: 13-6-9
 * Time: 下午3:33
 */
public class FileUploadParserException extends RuntimeException {
    public FileUploadParserException() {
        super("incorrect encripty type, expected: multipart/form-data");
    }
}
