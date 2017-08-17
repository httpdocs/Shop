package cn.it.shop.action;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.it.shop.model.Category;
import cn.it.shop.utils.ReqUtil;

@Controller("categoryAction")
@Scope("prototype")
public class CategoryAcion extends BaseAction<Category>{
	private static final long serialVersionUID = 1L;

	public String queryJoinAccount(){
		//用来存储分页的数据，返回给前台
		pageMap = new HashMap<String, Object>();
		System.out.println(page);
		System.out.println(rows);
		List<Category> categorieList = categoryService.queryJoinAccount(model.getType(), page, rows);
		pageMap.put("rows", categorieList);//存放在pageMap中，后面会转为json格式。

		Long total = categoryService.getCount(model.getType());
		pageMap.put("total", total);//总记录数存放在pageMap中，后面也会转为json。
		HttpServletRequest request = ServletActionContext.getRequest();
		//打印请求的一些调试信息。
		ReqUtil.showParams(request);
		return "jsonMap";

	}
	
	public String deleteByIds(){
		System.out.println(ids);
		ReqUtil.showParams(ServletActionContext.getRequest());
		categoryService.deleteByIds(ids);
		inputStream = new ByteArrayInputStream("true".getBytes());
		return "stream";
	}
	
	public void save(){
		System.out.println(model);
		categoryService.save(model);
	}
	
	public void update(){
		System.err.println(model);
		categoryService.update(model);
	}
	
	public String query(){
		jsonList = categoryService.query();
		return "jsonList";
	}

}
