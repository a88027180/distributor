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
	
	
	@Resource//ע��
	private MonAlarmsDao  monAlarmsDao; 
	/**
	 * ��ѯ�����豸��ʾ��Ϣ
	 */
	public NoteResult selectAllMonAlarms() {
		NoteResult note=new NoteResult();
		List<MonAlarms>    list= monAlarmsDao.selectAllMonAlarms();
		note.setStatus(0);
		note.setMsg("��ѯ�ɹ�");
		note.setData(list);
		return note;
	}

	
	public NoteResult deleteMonAlarms(Integer monId) {
		NoteResult note=new NoteResult();
		if(monId==null){
			note.setStatus(1);
			note.setMsg("��������Ϊ��");
			note.setData("");
		
		}else{
			monAlarmsDao.deleteMonAlarms(monId);
			note.setStatus(0);
			note.setMsg("ɾ���ɹ�");
			note.setData("");
			return note;
		}
		return note;
	}

}
