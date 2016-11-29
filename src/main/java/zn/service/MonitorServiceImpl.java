/**
 * 
 */
package zn.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

import zn.dao.MonDateDao;
import zn.dao.MonitorDao;
import zn.entity.Monitor;
import zn.entity.User;
import zn.until.NoteResult;

/**
 * @author hq
 *
 */
@Service("monitorService")
@Transactional
public class MonitorServiceImpl implements  MonitorService{
	
	@Resource//ע��
	private MonitorDao monitorDao;
	
	@Resource//ע��
	private MonDateDao monDateDao;

	/**
	 * ����豸
	 */
	public NoteResult addMon(String monStr) {
		NoteResult note=new NoteResult();
		
		try {
			Monitor mon=JSON.parseObject(monStr, Monitor.class);
			if(mon==null){
				note.setStatus(1);
				note.setMsg("����Ϊ��");
				note.setData("");
			
			}else if(mon.getMonNumber()==null){
				note.setStatus(2);
				note.setMsg("�豸���Ϊ��");
				note.setData("");
				
			}else if(monitorDao.monIsExist(mon.getMonNumber())!=0){
				note.setStatus(3);
				note.setMsg("�豸����Ѵ���");
				note.setData("");
				
			}else{
				monitorDao.addMon(mon);
				monDateDao.addMonDateId(mon.getMonId());
				note.setStatus(0);
				note.setMsg("����豸�ɹ�");
				note.setData("");
			}
			
		} catch (JSONException e) {
			note.setStatus(4);
			note.setMsg("������ʽ����");
			note.setData("");
		}catch(ClassCastException c){
			note.setStatus(4);
			note.setMsg("������ʽ����");
			note.setData("");
		} 
		return note;
	}

	/**
	 * ��ѯ�����豸��Ϣ
	 */
	public NoteResult findAllMon() {
		NoteResult note=new NoteResult();
		List<Monitor> list=monitorDao.findAllMon();
		note.setStatus(0);
		note.setMsg("��ѯ�ɹ�");
		note.setData(list);
		
		return note;
	}

	/**
	 * ɾ���豸�ɹ�
	 */
	public NoteResult deleteMon(Integer monId) {
		NoteResult note=new NoteResult();
		if(monId==null){
			note.setStatus(1);
			note.setMsg("����Ϊ��");
			note.setData("");
		}else{
		
		monitorDao.deleteMon(monId);
		note.setStatus(0);
		note.setMsg("ɾ���ɹ�");
		note.setData("");
		}
		return note;
	}

	/**
	 * �����豸��Ϣ
	 */
	public NoteResult changeMon(String monStr) {
		NoteResult note=new NoteResult();
		try {
			Monitor mon=JSON.parseObject(monStr, Monitor.class);
			if(mon==null){
				note.setStatus(1);
				note.setMsg("����Ϊ��");
				note.setData("");
			}else{
				monitorDao.changeMon(mon);
				note.setStatus(0);
				note.setMsg("�����豸��Ϣ�ɹ�");
				note.setData("");
			}
		} catch (JSONException e) {
			note.setStatus(4);
			note.setMsg("������ʽ����");
			note.setData("");
		}catch(ClassCastException c){
			note.setStatus(4);
			note.setMsg("������ʽ����");
			note.setData("");
		} 
		return note;
	}

	/**
	 * ��ָ���豸����û��б�
	 */
	public NoteResult monAddUser(String jsonStr) {
		NoteResult note=new NoteResult();
		if(jsonStr==null||"".equals(jsonStr)){
			note.setStatus(5);
			note.setMsg("����Ϊ��");
			note.setData("");
			return note;
		}
		try {
			Map<String,Object> map=JSON.parseObject(jsonStr);
		if(map==null||map.isEmpty()){				
			note.setStatus(5);
			note.setMsg("����Ϊ��");
			note.setData("");
			return note;
		}else if(map.get("monId")==null){
			note.setStatus(2);
			note.setMsg("�豸idΪ��");
			note.setData("");
			
		}else if(map.get("userList")==null||"".equals(map.get("userList"))){
			monitorDao.monDelteUser((Integer)map.get("monId"));
			note.setStatus(0);
			note.setMsg("����豸�û��б�ɹ�");
			note.setData("");
			
		}else{
			monitorDao.monDelteUser((Integer)map.get("monId"));
			monitorDao.monAddUser(map);
			note.setStatus(0);
			note.setMsg("�����ɹ�");
			note.setData("");
		}
		} catch (JSONException e) {
			note.setStatus(4);
			note.setMsg("������ʽ����");
			note.setData("");
		}catch(ClassCastException c){
			note.setStatus(4);
			note.setMsg("������ʽ����");
			note.setData("");
		}
		return note;

	}

	/**
	 * ��ѯָ���û��µ������豸
	 */
	public NoteResult seleteMonByUserId(Integer userId) {
		NoteResult note=new NoteResult();
		
		if(userId==null){
			note.setStatus(5);
			note.setMsg("����Ϊ��");
			note.setData("");
			
		}else{
		List<Monitor> list=monitorDao.seleteMonByUserId(userId);
			note.setStatus(5);
			note.setMsg("�����ɹ�");
			note.setData(list);
		}
		return note;	
	}

}
