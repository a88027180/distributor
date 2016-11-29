/**
 * 
 */
package zn.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zn.dao.MonFirstListDao;
import zn.dao.MonSecondListDao;
import zn.dao.MonitorDao;
import zn.entity.MonSecondList;
import zn.until.NoteResult;

/**
 * @author hq
 *
 */
@Service("monSecondListService")
@Transactional
public class MonSecondListServiceImpl implements MonSecondListService {
	@Resource//ע��
	private MonitorDao monitorDao;
	
	@Resource//ע��
	private MonSecondListDao  monSecondListDao; 
	
	@Resource
	private MonFirstListDao  monFirstListDao;
	
	
	/**
	 * ��Ӷ����б�
	 */
	public NoteResult addSecondList(String secondListName, Integer firstListId) {
		NoteResult note=new NoteResult();
		
		 if(secondListName==null||firstListId==null){
			 	note.setStatus(1);
				note.setMsg("��������Ϊ��");
				note.setData("");
		 } else if(monFirstListDao.selectIsExistById(firstListId)==0){
			 note.setStatus(3);
				note.setMsg("�ϼ��б�����");
				note.setData("");
		 }else if(monSecondListDao.selectIsExist(secondListName)!=0){ 
			 	note.setStatus(2);
				note.setMsg("�б��Ѵ���");
				note.setData("");
		 }else{
			 	monSecondListDao.addSecondList(secondListName, firstListId);
			 	note.setStatus(0);
				note.setMsg("����б�ɹ�");
				note.setData("");
		 }
	return note;
		
	}

	/**
	 * ɾ�������б�
	 */
	public NoteResult deleteSecondList(Integer secondListId) {
		NoteResult note=new NoteResult();	
		 if(secondListId==null){
			 	note.setStatus(1);
				note.setMsg("��������Ϊ��");
				note.setData("");
		 }else{
			 	MonSecondList mon=monSecondListDao.selectListById(secondListId);
			 	if(mon!=null){
			 	monitorDao.deleteMonPlace(mon.getSecondListName(), 2);
			 	}
			 	monSecondListDao.deleteSecondList(secondListId);
				note.setStatus(0);
				note.setMsg("ɾ���ɹ�");
				note.setData("");
		 }
		return note;
	
	}

	/**
	 * ���Ķ����б�
	 */
	public NoteResult changeSecondList(String secondListName, Integer firstListId, Integer secondListId) {
		NoteResult note=new NoteResult();
		
		 if(secondListName==null||firstListId==null||secondListId==null){
			 	note.setStatus(1);
				note.setMsg("��������Ϊ��");
				note.setData("");
		 } else if(monFirstListDao.selectIsExistById(firstListId)==0){
			 note.setStatus(3);
				note.setMsg("�ϼ��б�����");
				note.setData("");
		 }else if(monSecondListDao.selectIsExist(secondListName)!=0){ 
			 	note.setStatus(2);
				note.setMsg("�б��Ѵ���");
				note.setData("");
		 }else{
			 	MonSecondList mon=monSecondListDao.selectListById(secondListId);
			 	if(mon!=null){
			 		monitorDao.changeMonPlace(mon.getSecondListName(),secondListName, 2);	
			 	}
				

			 	monSecondListDao.changeSecondList(secondListName, firstListId, secondListId);
			 	note.setStatus(0);
				note.setMsg("���ĳɹ�");
				note.setData("");
		 }
		return note;
	}

	/**
	 * ��ѯ���ж����б�
	 */
	public NoteResult findAllSecondList() {
		NoteResult note=new NoteResult();
		List<MonSecondList>		list=	monSecondListDao.findAllSecondlist();
		note.setStatus(0);
		note.setMsg("��ѯ�ɹ�");
		note.setData(list);
		return note;
	}

}
