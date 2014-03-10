package com.jdon.mvc.http;

import com.jdon.mvc.core.Env;
import com.jdon.mvc.core.FlowContext;
import com.jdon.mvc.flow.MaxUploadSizeException;
import com.jdon.mvc.util.StringUtils;
import com.jdon.mvc.util.WebUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class ApacheCommonsFileupload implements MultiProcessor {

    private final Log LOG = LogFactory.getLog(ApacheCommonsFileupload.class);

    /**
     * 配置格式是1m,2M或者字节数量332325235
     */
    private final static String MAXUPLOADSIZE = "maxUploadSize";

    private static long sizeLimit = -1;

    private List<FileItem> fileItems;


    public void parse(FlowContext context) {

        if (ServletFileUpload.isMultipartContent(Env.req())) {
            DiskFileItemFactory factory = createFactoryForDiskBasedFileItems();
            ServletFileUpload fileUploadHandler = new ServletFileUpload(factory);

            String maxSizeSetting = context.fwContext().getConfigItem(MAXUPLOADSIZE);
            if (StringUtils.isNotEmpty(maxSizeSetting)) {
                if (maxSizeSetting.toLowerCase().indexOf("m") != -1) {
                    maxSizeSetting = maxSizeSetting.replace("m", "").replace("M", "");
                    sizeLimit = Integer.parseInt(maxSizeSetting) * 1024 * 1024;
                } else {
                    sizeLimit = Long.parseLong(maxSizeSetting);
                }
            }

            fileUploadHandler.setSizeMax(sizeLimit);
            try {
                fileItems = fileUploadHandler.parseRequest(Env.req());
            } catch (FileUploadBase.SizeLimitExceededException ex) {
                throw new MaxUploadSizeException(sizeLimit, ex);
            } catch (FileUploadException e) {
                LOG.warn("There was some problem parsing this multipart Env.req(), or someone is not sending a RFC1867 compatible multipart Env.req().", e);
            }
            LOG.debug("Found [" + fileItems.size() + "] attributes in the multipart form submission. Parsing them.");
            parseFileItem();
        }

        LOG.info("the multipart processor of " + ApacheCommonsFileupload.class.getName() + " finish");

    }


    private void parseFileItem() {
        for (FileItem item : fileItems) {
            if (item.isFormField()) {
                String encoding = Env.req().getCharacterEncoding();

                try {
                    if (encoding == null)
                        Env.req().addParameterValue(item.getFieldName(), item.getString("ISO-8859-1"));
                    else
                        Env.req().addParameterValue(item.getFieldName(), item.getString(encoding));
                } catch (UnsupportedEncodingException e) {
                    LOG.warn("can't supported the encoding,please check your filter" + e);
                }
            } else {
                if (StringUtils.isNotEmpty(item.getName())) {
                    try {
                        FormFile formFile = new FormFileImpl(item);
                        Env.req().addFormFile(item.getFieldName(), formFile);
                        LOG.info("Uploaded file: " + item.getFieldName() + " with " + formFile);
                    } catch (Exception e) {
                        LOG.error("Nasty uploaded file " + item.getName(), e);
                    }
                } else {
                    LOG.info("A file field was empty: " + item.getFieldName());
                }
            }
        }

    }


    private DiskFileItemFactory createFactoryForDiskBasedFileItems() {
        DiskFileItemFactory factory = new DiskFileItemFactory(4096 * 16, WebUtils.getTempDir(Env.ctx()));
        LOG.debug("Using repository [" + factory.getRepository() + "]");
        return factory;
    }



    static class FormFileImpl implements FormFile {
        private static final String NOT_UNIX_LIKE_SEPARATOR = "\\";

        private static final String UNIX_LIKE_SEPARATOR = "/";

        private String fileName;

        private FileItem item;


        public FormFileImpl(FileItem item) {
            this.item = item;
            String originalFilename = item.getName();

            if (originalFilename.indexOf(UNIX_LIKE_SEPARATOR) == -1) {
                this.fileName = originalFilename.substring(originalFilename
                        .lastIndexOf(NOT_UNIX_LIKE_SEPARATOR) + 1);
            } else {
                this.fileName = originalFilename.substring(originalFilename
                        .lastIndexOf(UNIX_LIKE_SEPARATOR) + 1);
            }
        }


        public final FileItem getFileItem() {
            return this.item;
        }


        public String getContentType() {
            return item.getContentType();
        }

        public long getFileSize() {
            return item.getSize();
        }

        public byte[] getFileData() {
            return item.get();
        }

        public InputStream getInputStream() throws IOException {
            return item.getInputStream();
        }

        @Override
        public String getOriginalFilename() {
            return fileName;
        }

        @Override
        public String getName() {
            return item.getFieldName();
        }

        @Override
        public void transferTo(File dest) throws IOException {
            if (!isAvailable()) {
                throw new IllegalStateException("File has already been moved - cannot be transferred again");
            }

            if (dest.exists() && !dest.delete()) {
                throw new IOException(
                        "Destination file [" + dest.getAbsolutePath() + "] already exists and could not be deleted");
            }

            try {
                this.item.write(dest);
            } catch (FileUploadException ex) {
                throw new IllegalStateException(ex.getMessage());
            } catch (IOException ex) {
                throw ex;
            } catch (Exception ex) {
                throw new IOException("Could not transfer to file: " + ex.getMessage());
            }
        }


        protected boolean isAvailable() {
            // If in memory, it's available.
            if (this.item.isInMemory()) {
                return true;
            }
            // Check actual existence of temporary file.
            if (this.item instanceof DiskFileItem) {
                return ((DiskFileItem) this.item).getStoreLocation().exists();
            }

            return false;
        }

        @Override
        public void clear() {
            this.item.delete();
        }
    }
}

