package com.jdon.mvc.converter;

import com.jdon.mvc.annotations.Convert;
import com.jdon.mvc.http.FormFile;

import java.util.List;


@Convert(List.class)
public class MultiFormFileConverter implements TypeConverter<List<FormFile>> {

	@SuppressWarnings("unchecked")
    public List<FormFile> convert(Object value) {
    	List<FormFile> formFile = (List<FormFile>)value;
        return formFile;
    }
    

}
