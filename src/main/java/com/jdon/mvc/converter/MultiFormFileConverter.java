package com.jdon.mvc.converter;

import com.jdon.mvc.annotations.Convert;
import com.jdon.mvc.http.FormFile;

import java.util.List;

/**
 * 是list类型那个多表单文件？todo
 */

@Convert(List.class)
public class MultiFormFileConverter implements TypeConverter<List<FormFile>> {

    public List<FormFile> convert(Object value) {
    	List<FormFile> formFile = (List<FormFile>)value;
        return formFile;
    }
    

}
