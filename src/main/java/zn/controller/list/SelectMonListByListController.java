/**
 * 
 */
package zn.controller.list;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import zn.service.MonListService;
import zn.until.NoteResult;

/**
 * @author hq
 *
 */
@Controller
@RequestMapping("/monList")
public class SelectMonListByListController {
	@Resource
	private MonListService  monListService;
	
	@RequestMapping("/selectMonListByList")
	@ResponseBody
	public NoteResult execute(String superiorListId){
		NoteResult note= monListService.selectMonListByList(superiorListId);
		return note;
		
	}

}
