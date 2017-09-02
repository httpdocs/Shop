package cn.it.shop.action;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.it.shop.model.Forder;
import cn.it.shop.model.Status;
import cn.it.shop.model.User;

@Controller("forderAction")
@Scope("prototype")
public class ForderAction extends BaseAction<Forder>{

	public Forder getModel() {
		model = (Forder) session.get("forder");
		return model;
	};

	public String save(){
		model.setUser((User)session.get("user"));  
		model.setDate(new Date());
		forderService.save(model);  
        session.put("oldForder", session.get("forder"));  
        session.put("forder", new Forder());
		return "bank";  
	}

}
