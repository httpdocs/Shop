package cn.it.shop.action;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.it.shop.model.Category;

@Controller("categoryAction")
@Scope("prototype")
public class CategoryAcion extends BaseAction<Category>{
	private static final long serialVersionUID = 1L;

	public String  update() {
		System.out.println("----update-----");
		System.out.println(categoryService);
		categoryService.update(model);
		return "index";
	}

	public String save(){
		System.out.println("----save----");
		categoryService.save(model);
		System.out.println(categoryService);
		return "index";
	}
	
    public String query() { 
    	System.out.println("------query-----");
        request.put("categoryList", categoryService.query());   
        session.put("categoryList", categoryService.query());   
        application.put("categoryList", categoryService.query());   
        return "index";  
    }  
    
    public String queryJoinAccount(){
    	System.out.println("--------queryJoinAccount---------");
    	 request.put("categoryList", categoryService.queryJoinAccount("",1,1));   
         session.put("categoryList", categoryService.queryJoinAccount("",1,1));   
         application.put("categoryList", categoryService.queryJoinAccount("",1,1));   
         return "index";  
    }

}
