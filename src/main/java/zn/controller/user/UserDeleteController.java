/**
 * 
 */
package zn.controller.user;

import javax.annotation.Resource;

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
public class UserDeleteController {
	@Resource
	private UserService userService;
	
	@RequestMapping("/delete")
	@ResponseBody
	public NoteResult execute(Integer userId){
		NoteResult note=userService.deleteUser(userId);	
		return note;	
	}
}
