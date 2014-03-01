package com.jdon.mvc.http;

import com.jdon.mvc.core.ComponentHolder;
import com.jdon.mvc.util.TypeUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 原生的request是不可变的，所以框架内部装饰一下
 */
public class WebRequest extends HttpServletRequestWrapper {

    /**
     * 是否trim表单中的值
     */
    public static final String TRIM_FORM = "trim?";

    private final RequestParameters parameters;

    private HttpServletRequest request;

    private List<FormFile> formFileList = new LinkedList<FormFile>();


    public WebRequest(HttpServletRequest request, ComponentHolder holder) {
        super(request);
        this.request = request;
        this.parameters = new RequestParameters(request.getParameterMap());

        if (TypeUtil.boolTrue(holder.getConfigItem(TRIM_FORM))) {
            parameters.trimAllParamValue();
        }
    }


    public void cleanupMultipart() {
        for (FormFile formFile : formFileList) {
            FormFileImp formFileImp = (FormFileImp) formFile;
            formFileImp.getFileItem().delete();
        }
    }

    public void setParameter(String name, String value) {
        this.parameters.put(name, value);
    }

    public void addParameterValue(String name, String value) {
        parameters.addValueToArray(name, value);
    }

    public void addFormFile(String name, FormFile formFile) {
        parameters.addFormFile(name, formFile);
        formFileList.add(formFile);
    }

    @Override
    public String getParameter(String name) {
        return parameters.getParameter(name);
    }

    @Override
    public Map getParameterMap() {
        return parameters.getParameterMap();
    }

    @Override
    public Enumeration getParameterNames() {
        return parameters.getParameterNames();
    }

    @Override
    public String[] getParameterValues(String name) {
        return parameters.getParameterValues(name);
    }

    public HttpServletRequest getRequest() {
        return request;
    }
}
