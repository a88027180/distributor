/**
 * 
 */
package zn.controller.monalarms;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import zn.service.MonAlarmsService;
import zn.service.MonitorService;
import zn.until.NoteResult;

/**
 * @author hq
 *
 */
@Controller
@RequestMapping("/monAlarms")
public class DeleteMonAlarmsController {
	@Resource
	private MonAlarmsService monAlarmsService;
	
	@RequestMapping("/deleteMonAlarms")
	@ResponseBody
	public NoteResult execute(Integer monId){
		NoteResult note=monAlarmsService.deleteMonAlarms(monId);	
		return note;
		
	}

}
