/**
 * 
 */
package zn.controller.picture;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import zn.service.PictureService;
import zn.service.UserService;
import zn.until.NoteResult;

/**
 * @author hq
 *
 */
@Controller  
@RequestMapping("/uploadPictures")  
public class UploadPicturesController {
	@Resource
	private PictureService pictureService;
	

	
	@RequestMapping("/login")
	@ResponseBody
	public NoteResult execute(String picture,HttpServletRequest request){
	 	String pathUrl = request.getSession().getServletContext().getRealPath("")+"/picture/"+request.getSession().getAttribute("userId");
 
		NoteResult note=pictureService.uploadPictures(picture,pathUrl);	
		
		return note;	
	}
}
