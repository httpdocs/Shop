package cn.it.shop.action;

import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.it.shop.model.Forder;
import cn.it.shop.model.Product;
import cn.it.shop.model.Sorder;

@Controller("sorderAction")
@Scope("prototype")
public class SorderAction extends BaseAction<Sorder>{

	public String addSorder(){
		Product product = productService.get(model.getProduct().getId());
		
		if(session.get("forder")==null){
			session.put("forder", new Forder(new ArrayList<Sorder>()));
		}
		
		Forder forder = (Forder)session.get("forder");
		forder = sorderService.addSorder(forder, product);
		
		forder.setTotal(forderService.cluTotal(forder));

		session.put("forder", forder);
		return "showCart";
		
	}
	
}
