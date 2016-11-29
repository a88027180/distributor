/**
 * 
 */
package zn.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zn.dao.LimitsDao;
import zn.entity.Limits;
import zn.entity.User;
import zn.until.NoteResult;

/**
 * @author hq
 *
 */
@Service("limitsListService")
@Transactional
public class LimitsServiceImpl implements LimitsService {
	
	
	@Resource//ע��
	private LimitsDao  limitsDao;  
	
	/**
	 * Ϊ�û����Ȩ��
	 */
	public NoteResult userAddLimits(Integer limitsId, Integer userId) {
		NoteResult note=new NoteResult();
		if(limitsId==null||userId==null){
			note.setStatus(1);
			note.setMsg("��������Ϊ��");
			note.setData("");
		
		}else{
			limitsDao.userDeleteLimits(userId);
			limitsDao.userAddLimits(limitsId, userId);
			note.setStatus(0);
			note.setMsg("�����ɹ�");
			note.setData("");
		}
			return note;
	}

	
	/**
	 * ��ѯָ��Ȩ���µ��û���Ϣ
	 */
	public NoteResult selectUserByLimitsId(Integer limitsId) {
		NoteResult note=new NoteResult();
		if(limitsId==null){
			note.setStatus(1);
			note.setMsg("��������Ϊ��");
			note.setData("");
		
		}else{
			List<User> list=  limitsDao.selectUserByLimitsId(limitsId);
			note.setStatus(0);
			note.setMsg("�����ɹ�");
			note.setData(list);
		}
		return note;
	}

	/**
	 * ��ȡȨ���б�
	 */
	public NoteResult selectLimitsList() {
		NoteResult note=new NoteResult();
	
			List<Limits> list=  limitsDao.selectLimitsList();
			note.setStatus(0);
			note.setMsg("�����ɹ�");
			note.setData(list);
		
		return note;
	
	}

}
