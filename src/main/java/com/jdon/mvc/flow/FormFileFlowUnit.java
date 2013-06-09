package com.jdon.mvc.flow;

import com.jdon.mvc.core.Env;
import com.jdon.mvc.core.FlowContext;
import com.jdon.mvc.http.FormFile;
import com.jdon.mvc.upload.MemoryParser;
import com.jdon.mvc.upload.Parser;
import com.jdon.mvc.upload.TextFieldCallBack;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 文件上传解析拦截
 *
 * @author oojdon
 */

public class FormFileFlowUnit implements FlowUnit {

    public void process(FlowContext context) {
        Parser parser = new MemoryParser();
        List<FormFile> fileList = parser.parse(new TextFieldCallBack() {
            @Override
            public void apply(String name, String value) {
                String encoding = Env.req().getCharacterEncoding();
                try {
                    if (encoding == null)
                        Env.req().addParameterValue(name, new String(value.getBytes(), "ISO-8859-1"));
                    else
                        Env.req().addParameterValue(name, new String(value.getBytes(), encoding));
                } catch (UnsupportedEncodingException e) {
                    // todo
                }
            }
        });

        for (FormFile formFile : fileList) {
            Env.req().addFormFile(formFile.getFileName(), formFile);
        }
    }
}
