/**
 * 
 */
package zn.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zn.dao.MonFirstListDao;
import zn.dao.MonitorDao;
import zn.entity.MonFirstList;
import zn.until.NoteResult;

/**
 * @author hq
 *
 */

@Service("monFirstListService")
@Transactional
public class MonFirstListServiceImpl implements MonFirstListService {
	
	
	@Resource//ע��
	private MonFirstListDao  monFirstListDao; 
	
	@Resource//ע��
	private MonitorDao monitorDao;
	
	/**
	 * ���һ���б�
	 */
	public NoteResult addFirstList(String firstListName) {
			NoteResult note=new NoteResult();
		
			 if(firstListName==null){
				 	note.setStatus(1);
					note.setMsg("��������Ϊ��");
					note.setData("");
			 } else if(monFirstListDao.selectIsExist(firstListName)!=0){
				 	note.setStatus(2);
					note.setMsg("�б��Ѵ���");
					note.setData("");
			 }else{
				 	monFirstListDao.addFirstList(firstListName);
				 	note.setStatus(0);
					note.setMsg("����б�ɹ�");
					note.setData("");
			 }
		return note;
	}

	
	/**
	 * ����һ���б�
	 */
	public NoteResult changeFirstList(String firstListName, Integer firstListId) {
		NoteResult note=new NoteResult();
		
		 if(firstListName==null||firstListId==null){
			 	note.setStatus(1);
				note.setMsg("��������Ϊ��");
				note.setData("");
		 }else if(monFirstListDao.selectIsExist(firstListName)!=0){
			 	note.setStatus(2);
				note.setMsg("�б��Ѵ���");
				note.setData("");
		 }else{
			 MonFirstList mon=monFirstListDao.selectListById(firstListId);
			 	if(mon!=null){
				monitorDao.changeMonPlace(mon.getFirstListName(), firstListName, 1);
			 	}
			 	monFirstListDao.changeFirstList(firstListName, firstListId);
			 	note.setStatus(0);
				note.setMsg("���ĳɹ�");
				note.setData("");
		 }
		return note;
	}

	/**
	 * ɾ��һ���б�
	 */
	public NoteResult deleteFirstList(Integer firstListId) {
		NoteResult note=new NoteResult();	
		 if(firstListId==null){
			 	note.setStatus(1);
				note.setMsg("��������Ϊ��");
				note.setData("");
		 }else{
			 MonFirstList mon=monFirstListDao.selectListById(firstListId);
			 	if(mon!=null){
			 	monitorDao.deleteMonPlace(mon.getFirstListName(),1);
		 }
			 	monFirstListDao.deleteFirstList(firstListId);
				note.setStatus(0);
				note.setMsg("ɾ���ɹ�");
				note.setData("");
		 }
		return note;
	}

	/**
	 * ��ѯ����һ���б�
	 */
	public NoteResult findAllFirstlist() {
		NoteResult note=new NoteResult();
		List<MonFirstList> list  =monFirstListDao.findAllFirstlist();
		note.setStatus(0);
		note.setMsg("��ѯ�ɹ�");
		note.setData(list);
		return note;
	}

}
