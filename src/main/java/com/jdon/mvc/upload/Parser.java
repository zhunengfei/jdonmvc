package com.jdon.mvc.upload;

import com.jdon.mvc.http.FormFile;

import java.util.List;

/**
 * 抽象parser，有流式，和内存两种模式
 * User: Asion
 * Date: 13-6-9
 * Time: 下午4:55
 */
public abstract class Parser {


    public abstract List<FormFile> parse(TextFieldCallBack callBack);

}
