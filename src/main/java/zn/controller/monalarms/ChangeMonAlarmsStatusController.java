/**
 * 
 */
package zn.controller.monalarms;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import zn.service.MonAlarmsService;
import zn.until.NoteResult;

/**
 * @author hq
 *
 */
@Controller
@RequestMapping("/monAlarms")
public class ChangeMonAlarmsStatusController {
	@Resource
	private MonAlarmsService monAlarmsService;
	
	@RequestMapping("/changeMonAlarmsStatus")
	@ResponseBody
	public NoteResult execute(Integer alarmsId){
		NoteResult note=monAlarmsService.changeMonAlarmsStatus(alarmsId);	
		return note;
		
	}

}
