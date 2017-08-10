package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.it.shop.model.Category;
import cn.it.shop.service.CategoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:beans.xml")
public class TestSSH {
	@Autowired
	private CategoryService categoryService;
	
//	@Test
//	public void testQueryJoinAccount(){
//		for(Category c:categoryService.queryJoinAccount("")){
//			System.out.println(c+","+c.getAccount());
//		}
//	}
	
}
