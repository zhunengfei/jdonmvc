package com.jdon.mvc.upload;

import com.jdon.mvc.core.Env;

import java.util.HashMap;

/**
 * 上传主要读取几个信息
 * <p/>
 * 1,Content-Type:multipart/form-data; boundary=----WebKitFormBoundaryLnYwLTPxPztmyDex 读取boundary
 * 2,Content-Disposition: form-data; name="token" 普通域
 * 3,Content-Disposition: form-data; name="uploadFile"; filename="note"
 * Content-Type: application/octet-stream            上传域
 * <p/>
 * 各个域被boundary隔断
 * <p/>
 * User: Asion
 * Date: 13-6-9
 * Time: 下午1:52
 */
public class UploadUtil {

    /**
     * HTTP content disposition header name.
     */
    public static final String CONTENT_DISPOSITION = "Content-disposition";

    /**
     * Content-disposition value for form data.
     */
    public static final String FORM_DATA = "form-data";


    /**
     * Content-disposition value for file attachment.
     */
    public static final String ATTACHMENT = "attachment";

    private final static String _ENCTYPE = "multipart/form-data";

    private final static String _HEADER_CONTENT_TYPE = "Content-type";

    private final String _BOUNDARY_PREFIX = "--";

    private final String _DEFALT_ENCODING = "iso-8859-1";

    private static final String _FILE_NAME_KEY = "filename";

    private static final String _NAME_KEY = "name";

    /**
     * 判断是否是文件上传
     *
     * @return
     */
    public static boolean isMultiPart() {
        if (!"post".equals(Env.req().getMethod().toLowerCase())) {
            return false;
        }
        String[] content = Env.req().getHeader(_HEADER_CONTENT_TYPE).split(";");
        if (content.length > 1) {
            return _ENCTYPE.equalsIgnoreCase(content[0]);
        } else {
            return false;
        }
    }

    /**
     * 获取边界
     *
     * @return
     */
    public byte[] getBoundary() {
        String[] content = Env.req().getHeader(_HEADER_CONTENT_TYPE).split(";");
        if (content.length > 1) {
            if (!_ENCTYPE.equalsIgnoreCase(content[0])) {
                throw new MultipartException();
            }
            return (_BOUNDARY_PREFIX + content[1].split("=")[1]).getBytes();
        } else {
            throw new MultipartException();
        }
    }

    /**
     * 分析上传中的信息行，主要是用分号拆分，然后取等号两边的数据
     *
     * @param line
     * @return
     */
    public HashMap<String, String> parseLine(String line) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        String[] sections = line.split(";\\s");
        for (String sec : sections) {
            String[] vals = sec.split(":\\s|(=\")");
            if (vals.length > 1)
                hashMap.put(vals[0].trim(), vals[1].trim().replaceAll("\"", ""));
        }
        return hashMap;
    }
}
