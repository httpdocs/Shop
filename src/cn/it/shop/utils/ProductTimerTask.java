package cn.it.shop.utils;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cn.it.shop.model.Category;
import cn.it.shop.model.Product;
import cn.it.shop.service.CategoryService;
import cn.it.shop.service.ProductService;

@Component
public class ProductTimerTask extends TimerTask{

	@Autowired
	private ProductService productService = null;

	@Autowired
	private CategoryService categoryService = null;
	
	private ServletContext servletContext = null;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	@Override
	public void run() {
		
		List<List<Product>> bigList = new ArrayList<List<Product>>();
		
		for(Category category : categoryService.queryByHot(true)){
			//将每个类中的前四个热门的产品放在list中。
			List<Product> list = productService.queryByCategoryId(category.getId());
			//将list再放进大的biglist中。
			bigList.add(list);
		}
		//将bigList放进application域中
		servletContext.setAttribute("bigList", bigList);
	
	}

}
