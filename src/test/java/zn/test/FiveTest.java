/**
 * 
 */
package zn.test;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import zn.dao.UserDao;
import zn.until.EncodeUtils;

/**
 * @author hq
 *
 */
public class FiveTest {
	
	@Test
	public void test() throws UnsupportedEncodingException{
//	     ApplicationContext    ac = new ClassPathXmlApplicationContext("spring-mybatis.xml");  
//	     UserDao dao = ac.getBean("userDao",UserDao.class);
//	     List<Map<String,Object>> list=dao.seleteUserListByMonId(3);
//	     System.out.println(list.toString());
//		String a="5346425201000000030000000c000000010000000000000000000000000000000000000000000000000000000000513f00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000003f221e162b304a5dad4825290a6c3f2c";
//		String b="5346425201000000030000000C00000001000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
//		System.out.println(a.length());
		byte[] a=new byte[]{12,-87,-86};
		System.out.println(a[0]+a[1]+a[2]);
		String b=EncodeUtils.hexEncode(a);
		System.out.println(b);
		 String info = new String(a, 0,a.length,"ISO-8859-1"); 
		
		
		
		 byte[] hex;
		try {
			hex = info.getBytes("ISO-8859-1");
			 System.out.println(hex[0]+":"+hex[1]+":"+hex[2]);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		 
	}

}
