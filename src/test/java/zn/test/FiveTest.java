/**
 * 
 */
package zn.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import zn.dao.UserDao;

/**
 * @author hq
 *
 */
public class FiveTest {
	
	@Test
	public void test(){
	     ApplicationContext    ac = new ClassPathXmlApplicationContext("spring-mybatis.xml");  
	     UserDao dao = ac.getBean("userDao",UserDao.class);
	     List<Map<String,Object>> list=dao.seleteUserListByMonId(3);
	     System.out.println(list.toString());
	}

}
