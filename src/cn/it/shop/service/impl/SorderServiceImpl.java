package cn.it.shop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.it.shop.model.Forder;
import cn.it.shop.model.Product;
import cn.it.shop.model.Sorder;
import cn.it.shop.service.SorderService;


@Service("sorderService")
public class SorderServiceImpl extends BaseServiceImpl<Sorder> implements SorderService {

	@Override
	public Forder addSorder(Forder forder, Product product) {
		boolean isHave = false;
		Sorder sorder = productToSorder(product);
		for(Sorder old : forder.getSorders()){
			if(old.getProduct().getId().equals(sorder.getProduct().getId())){
				old.setNumber(old.getNumber()+sorder.getNumber());
				isHave = true;
				break;
			}
		}
		if(!isHave){
			sorder.setForder(forder);
			forder.getSorders().add(sorder);
		}
		return forder;
	}

	@Override
	public Sorder productToSorder(Product product) {
		Sorder sorder = new Sorder();
		sorder.setName(product.getName());
		sorder.setNumber(1);
		sorder.setPrice(product.getPrice());
		sorder.setProduct(product);
		return sorder;
	}




}
