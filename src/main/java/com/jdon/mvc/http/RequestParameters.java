package com.jdon.mvc.http;

import java.util.*;


class RequestParameters {

    private final Map<String, Object> parameters = new HashMap<String, Object>();

    public RequestParameters(Map<String, Object> parameterMap) {
        if (parameterMap != null) {
            parameters.putAll(parameterMap);
        }
    }

    public void addValueToArray(String name, String value) {
        if (parameters.containsKey(name)) {
            Object currentValue = parameters.get(name);
            if (currentValue.getClass().isArray()) {
                String[] currentArray = (String[]) currentValue;
                String[] newArray = new String[currentArray.length + 1];
                for (int i = 0; i < currentArray.length; i++) {
                    newArray[i] = currentArray[i];
                }
                newArray[currentArray.length] = value;
                parameters.put(name, newArray);
            } else if (currentValue instanceof String) {
                parameters.put(name, new String[]{(String) currentValue, value});
            } else {
                throw new IllegalStateException("can't add more values because current is neither String nor Array");
            }
        } else {
            parameters.put(name, value);
        }
    }

    //将上传文件也放到这里
    public void addFormFile(String name, FormFile formFile) {
        if (parameters.containsKey(name)) {
            List<FormFile> formFiles = (List<FormFile>) parameters.get(name);
            formFiles.add(formFile);
        } else {
            List<FormFile> formFiles = new ArrayList<FormFile>();
            formFiles.add(formFile);
            parameters.put(name, formFiles);
        }
    }

    public void put(String name, String value) {
        parameters.put(name, value);
    }

    public Map<String, Object> getParameterMap() {
        return parameters;
    }

    public String getParameter(String name) {
        Object value = parameters.get(name);
        if (value == null) {
            return null;
        }
        if (value.getClass().isArray()) {
            return ((String[]) value)[0];
        }
        return (String) value;
    }

    public Enumeration getParameterNames() {
        return new Enumeration() {

            Iterator it = parameters.keySet().iterator();

            public boolean hasMoreElements() {
                return it.hasNext();
            }

            public Object nextElement() {
                return it.next();
            }

        };
    }

    public String[] getParameterValues(String name) {
        Object value = parameters.get(name);
        if (value == null) {
            return null;
        }
        if (value.getClass().isArray()) {
            return ((String[]) value);
        }
        return new String[]{(String) value};
    }

}
