package com.jdon.mvc.converter;

import com.jdon.mvc.annotations.Convert;
import com.jdon.mvc.http.FormFile;

import java.util.List;


@Convert(FormFile.class)
public class SingleFormFileConverter implements TypeConverter<FormFile> {

	@SuppressWarnings("unchecked")
    public FormFile convert(Object value) {
    	List<FormFile> formFile = (List<FormFile>)value;
        return formFile.get(0);
    }
    

}
