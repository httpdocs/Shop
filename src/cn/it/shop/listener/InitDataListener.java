package cn.it.shop.listener;
import java.util.Timer;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import cn.it.shop.utils.ProductTimerTask;

public class InitDataListener implements ServletContextListener{

	private WebApplicationContext context = null;
	
	private ProductTimerTask productTimerTask = null;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
	
		context  = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
	
		productTimerTask =(ProductTimerTask) context.getBean("productTimerTask");
		
		productTimerTask.setServletContext(event.getServletContext());

		//一分钟更新一次。稳定后可以一小时更新一次。
		new Timer().schedule(productTimerTask, 0, 1000*60);
	}
}
