package cn.it.shop.utils;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class ReqUtil {

	//打印请求的问题
	public static void showParams(HttpServletRequest request) {

		String url = request.getScheme()+"://"+ request.getServerName()+request.getRequestURI()+"?"+request.getQueryString();
		System.out.println("获取全路径（协议类型：//域名/项目名/命名空间/action名称?其他参数）url="+url);
		String url2=request.getScheme()+"://"+ request.getServerName();//+request.getRequestURI();
		System.out.println("协议名：//域名="+url2);
		System.out.println("当前服务器的ip及端口号：" + request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		System.out.println("获取项目名="+request.getContextPath());
		System.out.println("获取参数="+request.getQueryString());
		System.out.println("获取全路径="+request.getRequestURL());
		System.out.println("获取所有参数：");

		Enumeration pNames=request.getParameterNames();
		while(pNames.hasMoreElements()){
			String name=(String)pNames.nextElement();
			String value=request.getParameter(name);
			System.out.println(name + "=" + value);
		}
	}
}
