package cn.it.shop.action;
import java.util.HashMap;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.it.shop.model.Category;

@Controller("categoryAction")
@Scope("prototype")
public class CategoryAcion extends BaseAction<Category>{
	private static final long serialVersionUID = 1L;
    
    public String queryJoinAccount(){
    	//用来存储分页的数据，返回给前台
    	pageMap = new HashMap<String, Object>();
    	System.err.println(page);
    	System.err.println(rows);
    	System.err.println(model.toString());
    	List<Category> categorieList = categoryService.queryJoinAccount(model.getType(), page, rows);
    	pageMap.put("rows", categorieList);//存放在pageMap中，后面会转为json格式。
    	
    	Long total = categoryService.getCount(model.getType());
    	pageMap.put("total", total);//总记录数存放在pageMap中，后面也会转为json。
    	
    	return "jsonMap";
    	
    }

}
