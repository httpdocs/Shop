package cn.it.shop.service;
import java.util.List;

import cn.it.shop.model.Category;

public interface CategoryService extends BaseService<Category> {
	//查询类别信息，级联管理员
	public List<Category> queryJoinAccount(String type,int page,int rows);//使用类别的名称查询

	//查询总记录数
	public Long getCount(String type);

	public void deleteByIds(String ids);

	List<Category> queryByHot(boolean hot);
}

