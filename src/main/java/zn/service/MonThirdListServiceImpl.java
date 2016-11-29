/**
 * 
 */
package zn.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zn.dao.MonSecondListDao;
import zn.dao.MonThirdListDao;
import zn.dao.MonitorDao;
import zn.entity.MonSecondList;
import zn.entity.MonThirdList;
import zn.until.NoteResult;

/**
 * @author hq
 *
 */
@Service("monThirdListService")
@Transactional
public class MonThirdListServiceImpl implements MonThirdListService {
	
	@Resource//ע��
	private MonitorDao monitorDao;
	
	@Resource//ע��
	private MonSecondListDao  monSecondListDao; 
	
	@Resource//ע��
	private MonThirdListDao  monThirdListDao; 

		/**
		 * ��ѯ���������б�
		 */
	public NoteResult findAllThirdList() {
		
			NoteResult note=new NoteResult();
			List<MonThirdList>		list=monThirdListDao.findAllThirdlist()	;
			note.setStatus(0);
			note.setMsg("��ѯ�ɹ�");
			note.setData(list);
			return note;

	}

	/**
	 * ��������б�
	 */
	public NoteResult addThirdList(String thirdListName, Integer secondListId) {
		
			NoteResult note=new NoteResult();
			
			 if(thirdListName==null||secondListId==null){
				 	note.setStatus(1);
					note.setMsg("��������Ϊ��");
					note.setData("");
			 } else if(monSecondListDao.selectIsExistById(secondListId)==0){
				 note.setStatus(3);
					note.setMsg("�ϼ��б�����");
					note.setData("");
			 }else if(monThirdListDao.selectIsExist(thirdListName)!=0){ 
				 	note.setStatus(2);
					note.setMsg("�б��Ѵ���");
					note.setData("");
			 }else{
				 	monThirdListDao.addThirdList(thirdListName, secondListId);
				 	note.setStatus(0);
					note.setMsg("����б�ɹ�");
					note.setData("");
			 }
		return note;	
	}

	/**
		 * ɾ�������б�
		 */
	public NoteResult deleteThirdList(Integer thirdListId) {
		

			NoteResult note=new NoteResult();	
			 if(thirdListId==null){
				 	note.setStatus(1);
					note.setMsg("��������Ϊ��");
					note.setData("");
			 }else{
				 	MonThirdList mon=monThirdListDao.selectListById(thirdListId);
				 	if(mon!=null){
				 	monitorDao.deleteMonPlace(mon.getThirdListName(), 3);
				 	}
				 	monThirdListDao.deleteThirdList(thirdListId);
					note.setStatus(0);
					note.setMsg("ɾ���ɹ�");
					note.setData("");
			 }
			return note;
	
	}

	/**
	 * ���������б�
	 */
	public NoteResult changeThirdList(String thirdListName, Integer secondListId, Integer thirdListId) {
		
	
			NoteResult note=new NoteResult();
			
			 if(thirdListName==null||secondListId==null||thirdListId==null){
				 	note.setStatus(1);
					note.setMsg("��������Ϊ��");
					note.setData("");
			 } else if(monSecondListDao.selectIsExistById(secondListId)==0){
				 note.setStatus(3);
					note.setMsg("�ϼ��б�����");
					note.setData("");
			 }else if(monThirdListDao.selectIsExist(thirdListName)!=0){ 
				 	note.setStatus(2);
					note.setMsg("�б��Ѵ���");
					note.setData("");
			 }else{
				 	MonThirdList mon=monThirdListDao.selectListById(thirdListId);
				 	if(mon!=null){
				 	monitorDao.changeMonPlace(mon.getThirdListName(), thirdListName, 3);
				 	}
				 	monThirdListDao.changeThirdList(thirdListName, secondListId, thirdListId);
				 	note.setStatus(0);
					note.setMsg("���ĳɹ�");
					note.setData("");
			 }
			return note;
	
	}

}
