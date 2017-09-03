package cn.it.shop.utils;

import java.io.File;
import java.io.FilenameFilter;
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

	@Value("#{prop.basePath+prop.filePath}")
	private String filePath;
	
	@Value("#{prop.basePath+prop.bankImagePath}")
	private String bankImagePath;
	
	public String getBankImagePath() {
		return bankImagePath;
	}
	
	public void setBankImagePath(String bankImagePath) {
		this.bankImagePath = bankImagePath;
	}
	
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

	//获得bankImage列表
	public String[] getBankImage() {
		String[] list = new	File(getBankImagePath()).list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				System.err.println("dir:" + dir + ",name:" + name);             
                //通过后缀名来实现文件的过滤效果
                //返回真就放到list中，返回假就过滤掉
                return name.endsWith(".gif");
			}
		});
		return list;
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
