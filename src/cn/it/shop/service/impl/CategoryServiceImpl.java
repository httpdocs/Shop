package cn.it.shop.service.impl;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.it.shop.model.Category;
import cn.it.shop.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {

	@Override
	public List<Category> queryJoinAccount(String type,int page,int size) {
		String hql = "from Category c left outer join fetch c.account  where c.type like :type";
		return getSession().createQuery(hql)
				.setString("type", "%"+type+"%")
				.setFirstResult((page-1)*size)
				.setMaxResults(size)
				.list();
	}

	@Override
	public Long getCount(String type) {
		String hql = "select count(c) from Category c where c.type like :type";
		return (Long)getSession().createQuery(hql)
				.setString("type", "%"+type+"%")
				.uniqueResult();//返回一条记录，总记录数
	}

	@Override
	public void deleteByIds(String ids) {
		// TODO 按照ids来删除
		String hql= "delete from Category c where c.id in("+ids+")";
		getSession().createQuery(hql).executeUpdate();
	}

	@Override
	public List<Category> queryByHot(boolean hot) {
		String hql = "from Category c where c.hot=:hot";
		return getSession().createQuery(hql)
				.setBoolean("hot", hot)
				.list();
	}
	
}