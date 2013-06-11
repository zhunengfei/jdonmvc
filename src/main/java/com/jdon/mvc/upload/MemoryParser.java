package com.jdon.mvc.upload;

import com.jdon.mvc.http.FormFile;

import java.util.Collections;
import java.util.List;

/**
 * User: Asion
 * Date: 13-6-9
 * Time: 下午5:00
 */
public class MemoryParser extends Parser {

    @Override
    public List<FormFile> parse(TextFieldCallBack callBack) {
        return Collections.EMPTY_LIST;
    }
}
