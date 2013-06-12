package com.jdon.mvc.converter;

import com.jdon.mvc.http.FormFile;

import java.util.List;


public class SingleFormFileConverter implements TypeConverter<FormFile> {

    public FormFile convert(Object value) {
    	List<FormFile> formFile = (List<FormFile>)value;
        return formFile.get(0);
    }
    

}
