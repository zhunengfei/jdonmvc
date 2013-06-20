package com.jdon.mvc.http;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * http协议的请求体
 * 如果是浏览器表单post请求，就是表单的参数对
 * 如果是get请求，这个body的内容就是空的
 * 其他的如Put,Delete，则都取的payload
 * User: Asion
 * Date: 13-6-19
 * Time: 上午10:38
 */
public class RequestBody {

    /**
     * 内容
     */
    private String content = "";

    /**
     * 类型
     */
    private String type = "";


    public <T> T json2Object(Class<T> clazz) throws IOException {
        return new ObjectMapper().readValue(content, clazz);
    }



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
