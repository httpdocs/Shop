package cn.it.shop.action;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import cn.it.shop.model.Product;
import cn.it.shop.utils.ReqUtil;

@Controller("productAction")
public class ProductAction extends BaseAction<Product>{
	public String queryJoinCategory(){
		pageMap = new HashMap<String, Object>();
		List<Product> productList = productService.queryJoinCategory(model.getName(), page, rows);
		pageMap.put("rows", productList);
		Long total = productService.getCount(model.getName());
		pageMap.put("total", total);
		return "jsonMap";
	}

	public String deleteByIds(){
		System.out.println(ids);
		ReqUtil.showParams(ServletActionContext.getRequest());
		productService.deleteByIds(ids);
		inputStream = new ByteArrayInputStream("true".getBytes());
		return "stream";
	}

	public void save() throws Exception {  
		String pic = fileUpload.uploadFile(fileImage);
		model.setPic(pic);
		model.setDate(new Date()); //设置一下当前时间，因为前台没有把时间字段传进来，这里自己设置一下即可  
		System.out.println(model);  
		//商品信息入库  
		productService.save(model);  
	}  

	public void update() throws Exception {  
		String pic = fileUpload.uploadFile(fileImage);
		model.setPic(pic);
		model.setDate(new Date()); //设置一下当前时间，因为前台没有把时间字段传进来，这里自己设置一下即可  
		System.out.println(model);  
		//更新商品  
		productService.update(model);  
	}  
}
