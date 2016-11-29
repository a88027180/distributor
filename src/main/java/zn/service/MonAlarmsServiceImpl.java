/**
 * 
 */
package zn.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import zn.dao.MonAlarmsDao;
import zn.entity.MonAlarms;
import zn.until.NoteResult;

/**
 * @author hq
 *
 */
@Service("monAlarmsService")
@Transactional
public class MonAlarmsServiceImpl implements MonAlarmsService {
	
	
	@Resource//注入
	private MonAlarmsDao  monAlarmsDao; 
	/**
	 * 查询所有设备警示信息
	 */
	public NoteResult selectAllMonAlarms() {
		NoteResult note=new NoteResult();
		List<MonAlarms>    list= monAlarmsDao.selectAllMonAlarms();
		note.setStatus(0);
		note.setMsg("查询成功");
		note.setData(list);
		return note;
	}

	
	public NoteResult deleteMonAlarms(Integer monId) {
		NoteResult note=new NoteResult();
		if(monId==null){
			note.setStatus(1);
			note.setMsg("参数不能为空");
			note.setData("");
		
		}else{
			monAlarmsDao.deleteMonAlarms(monId);
			note.setStatus(0);
			note.setMsg("删除成功");
			note.setData("");
			return note;
		}
		return note;
	}

}
