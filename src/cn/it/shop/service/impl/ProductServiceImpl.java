package cn.it.shop.service.impl;

import java.util.List;
import java.util.jar.Attributes.Name;

import org.springframework.stereotype.Service;

import cn.it.shop.model.Product;
import cn.it.shop.service.ProductService;

@Service("productService")
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService{

	/**
	 * name 商品名，按照这个来模糊查询
	 * page easyUI传的分页参数
	 * size easyUI传的分页参数
	 */
	@Override
	public List<Product> queryJoinCategory(String name, int page, int size) {
		String hql = "from Product p left join fetch p.category where p.name like :name";
		return getSession().createQuery(hql)
				.setString("name", "%" + name + "%")
				.setFirstResult((page-1) * size) //从第几个开始显示
				.setMaxResults(size) //显示几个
				.list();
	}

	@Override
	public Long getCount(String name) {
		String hql = "select count(p) from Product p where p.name like :name";
		return (Long) getSession().createQuery(hql)
				.setString("name", "%"+name+"%")
				.uniqueResult();
	}

	@Override
	public void deleteByIds(String ids) {
		String hql = "delete from Product p where p.id in("+ids+")";
		getSession().createQuery(hql).executeUpdate();
	}

	
}
