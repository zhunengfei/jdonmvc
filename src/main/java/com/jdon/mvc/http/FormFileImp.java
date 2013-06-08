package com.jdon.mvc.http;

import org.apache.commons.fileupload.FileItem;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class FormFileImp implements FormFile {
	
	private static final String NOT_UNIX_LIKE_SEPARATOR = "\\";

	private static final String UNIX_LIKE_SEPARATOR = "/";
	
	private File file;

	private String fileName;

	private String originalFilename;
	
	private FileItem item;


	public FormFileImp(File file, FileItem item) {
		this.item = item;
		this.file = file;	
		this.originalFilename = item.getName();

		if (originalFilename.indexOf(UNIX_LIKE_SEPARATOR) == -1) {
			this.fileName = originalFilename.substring(originalFilename
					.lastIndexOf(NOT_UNIX_LIKE_SEPARATOR) + 1);
		} else {
			this.fileName = originalFilename.substring(originalFilename
					.lastIndexOf(UNIX_LIKE_SEPARATOR) + 1);
		}
	}


	public String getContentType() {
		return item.getContentType();
	}
	
	public long getFileSize(){
		return item.getSize();
	}
	
	public byte[] getFileData() {
		return item.get();
	}

	public InputStream getInputStream() throws IOException {
		return item.getInputStream();
	}

	public File getFile() {
		return this.file;
	}

	
	public String getFileName() {
		return this.fileName;
	}


	public String getOriginalFilename() {
		return this.originalFilename;
	}
	
	public String toString() {
		return "[uploadedFile location=" + this.file + " uploadedCompleteName="
				+ this.originalFilename + " uploadedName=" + this.fileName
				+ " contentType=" + this.getContentType() + "";
	}
}
