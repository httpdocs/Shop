package cn.it.shop.model;

import java.io.File;

public class FileImage {

	private File file;

	private String contentType;
	
	private String fileName;

	public File getFile() {
		return file;
	}

	public String getContentType() {
		return contentType;
	}

	public String getFileName() {
		return fileName;
	}
	
	public void setUploadContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setUpload(File upload) {
		this.file = upload;
	}
	
	public void setUploadFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
