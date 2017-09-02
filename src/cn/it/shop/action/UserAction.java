package cn.it.shop.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.it.shop.model.User;
import cn.it.shop.service.UserService;

@Controller("userAction")
@Scope("prototype")
public class UserAction extends BaseAction<User>{

	@Autowired
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public String login(){
		
		model = userService.login(model);
		
		if(model == null){
			session.put("error", "登录失败");
			return "login";
		}else {
			session.put("user", model);
			if(session.get("goURL")==null){
				return "index";
			}else{
				return "goURL";
			}
		}
		
	}
	
}
