package zn.controller.user;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import zn.entity.User;
import zn.service.UserService;
import zn.until.NoteResult;

@Controller  
@RequestMapping("/user")  
public class UserLoginController {  
	@Resource
	private UserService userService;
	

	
	@RequestMapping("/login")
	@ResponseBody
	public NoteResult execute(String telephone,String password){
		NoteResult note=userService.checkLogin(telephone, password);	
		return note;	
	}
	
}

