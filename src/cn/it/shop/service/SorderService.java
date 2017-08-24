package cn.it.shop.service;

import cn.it.shop.model.Forder;
import cn.it.shop.model.Product;
import cn.it.shop.model.Sorder;

public interface SorderService extends BaseService<Sorder>{

	//添加购物项，返回新的购物车
	public Forder addSorder(Forder forder,Product product);
	
	//商品转换为购物项
	public Sorder productToSorder(Product product);
	
}
