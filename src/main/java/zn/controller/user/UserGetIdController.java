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
	
	
	@RequestMapping("/getId")
	@ResponseBody
	public Object execute(HttpSession session){
		NoteResult note=new NoteResult();
		
		
		Object userId=  session.getAttribute("userId");
		note.setStatus(0);
		note.setMsg("查询成功");
		note.setData(userId);
		return userId;	
	}
}
