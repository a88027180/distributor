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
//	     ApplicationContext    ac = new ClassPathXmlApplicationContext("spring-mybatis.xml");  
//	     UserDao dao = ac.getBean("userDao",UserDao.class);
//	     List<Map<String,Object>> list=dao.seleteUserListByMonId(3);
//	     System.out.println(list.toString());
		String a="5346425201000000030000000c000000010000000000000000000000000000000000000000000000000000000000513f00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000003f221e162b304a5dad4825290a6c3f2c";
		String b="5346425201000000030000000C00000001000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
		System.out.println(a.length());
	}

}
