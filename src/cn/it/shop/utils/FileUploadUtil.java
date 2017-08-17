package cn.it.shop.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.it.shop.model.FileImage;

@Component("fileUpload")
public class FileUploadUtil implements FileUpload{

	@Value("#{prop.filePath}")
	private String filePath;
	
	private String getFileExt(String fileName) {
		return FilenameUtils.getExtension(fileName);
	}
	
	public void setFilePath(String filePath) {
		System.err.println(filePath);
		this.filePath = filePath;
	}
	
	/**
	 * 生成一个通用唯一标识码，作为文件名。
	 * @param fileName
	 * @return
	 */
	private String newFileName(String fileName){
		String ext = getFileExt(fileName);
		return UUID.randomUUID().toString()+"."+ext;
	}
	
	@Override
	public String uploadFile(FileImage fileImage) {
		System.err.println(fileImage.getFileName());
		String pic = newFileName(fileImage.getFileName());
		System.err.println(pic);
		try {
			FileUtils.copyFile(fileImage.getFile(), new File(filePath, pic));
			return pic;
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			fileImage.getFile().delete();
		}
		return null;
	}
}
