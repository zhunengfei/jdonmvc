package com.jdon.mvc.flow;

/**
 * 上传文件大小超过设定限制发生的异常
 * User: Asion
 * Date: 13-6-24
 * Time: 上午10:35
 */
public class MaxUploadSizeException extends RuntimeException {

    private final long maxUploadSize;


    public MaxUploadSizeException(long maxUploadSize, Throwable ex) {
        super("Maximum upload size of " + maxUploadSize + " bytes exceeded", ex);
        this.maxUploadSize = maxUploadSize;
    }


    public long getMaxUploadSize() {
        return maxUploadSize;
    }


}
