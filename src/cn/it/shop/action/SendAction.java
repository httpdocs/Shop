package cn.it.shop.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * TODO(此action只用来完成web-inf中jsp与jsp请求转发功能，此action不处理任何的逻辑)
 * @author john
 *
 */

@Controller("sendAction")
@Scope("prototype")
public class SendAction extends ActionSupport{
	
	private static final long serialVersionUID = 3216020063402220031L;

	/**
	 * 直接请求转发。
	 */
	@Override
	public String execute() throws Exception {
		return "send";
	}
	
}
