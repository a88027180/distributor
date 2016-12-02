/**
 * 
 */
package zn.controller.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import zn.service.UserService;
import zn.until.NoteResult;

/**
 * @author hq
 *
 */
@Controller  
@RequestMapping("/user")  
public class UserGetIdController {
	@Resource
	private UserService userService;
	
	@RequestMapping("/getId")
	@ResponseBody
	public Object execute(HttpSession session){
		Object userId=  session.getAttribute("userId");
		
		return userId;	
	}
}
