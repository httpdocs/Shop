package cn.it.shop.action;

import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.it.shop.model.FileImage;
import cn.it.shop.service.AccountService;
import cn.it.shop.service.CategoryService;
import cn.it.shop.service.ForderService;
import cn.it.shop.service.ProductService;
import cn.it.shop.service.SorderService;
import cn.it.shop.utils.FileUpload;

/**
 * 实现action的抽取，把request,session,application以及模型，以及业务类的抽取。
 * @author john
 * @param <T>
 */

@Controller("baseAction")
@Scope("prototype")
public class BaseAction<T> extends ActionSupport implements RequestAware,SessionAware,ApplicationAware,ModelDriven<T>{
	//page和rows和分页有关，因为实体基本都会用到这两个变量，所以直接放到BaseAction中来。
	//分别生成getter setter,因为pageMap是action传给前台的，所以只需要getter即可。
	protected Integer page;
	protected Integer rows;
	protected Map<String, Object> pageMap = null;//让不同的action自己去实现

	//域对象
	protected T model;
	protected Map<String, Object> request;
	protected Map<String, Object> session;
	protected Map<String, Object> application;

	//service对象
	@Autowired
	protected AccountService accountService;	
	@Autowired
	protected CategoryService categoryService;
	@Autowired
	protected ProductService productService;
	@Autowired
	protected SorderService sorderService;
	@Autowired
	protected ForderService forderService;
	
	protected List<T> jsonList = null;
	
	protected String ids;
	
	//用来传string给前台
	protected InputStream inputStream;
	
	
	//上传类
	protected FileImage fileImage;
	
	@Autowired
	protected FileUpload fileUpload;
	
	
	public void setSorderService(SorderService sorderService) {
		this.sorderService = sorderService;
	}
	
	public void setForderService(ForderService forderService) {
		this.forderService = forderService;
	}
	
	public void setFileUpload(FileUpload fileUpload) {
		this.fileUpload = fileUpload;
	}
	
	public FileImage getFileImage() {
		return fileImage;
	}
	
	public void setFileImage(FileImage fileImage) {
		this.fileImage = fileImage;
	}
	
	public List<T> getJsonList() {
		return jsonList;
	}
	
	public String getIds() {
		return ids;
	}
	
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Map<String, Object> getPageMap() {
		return pageMap;
	}
	
	//只要前台取到就行，后台给前台只要get
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request =request;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}

	@Override
	public T getModel() {
		ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();  
        Class clazz = (Class)type.getActualTypeArguments()[0];  
        try {  
            model = (T)clazz.newInstance();  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }     
        return model;  
	}
	
	
	
	
}
