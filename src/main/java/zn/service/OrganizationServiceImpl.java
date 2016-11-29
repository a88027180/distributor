/**
 * 
 */
package zn.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zn.dao.OrganizationDao;
import zn.dao.UserDao;
import zn.entity.Organization;
import zn.until.NoteResult;

/**
 * @author hq
 *
 */
@Service("organizationService")
@Transactional
public class OrganizationServiceImpl implements OrganizationService {

	
	@Resource//ע��
	private OrganizationDao organizationDao;
	
	
	
	
	/**
	 * �����֯
	 */
	public NoteResult addOrg(String orgName) {
		NoteResult note=new NoteResult();
		if(null==orgName){
			note.setStatus(2);
			note.setMsg("��֯���Ʋ���Ϊ��");
			note.setData("");
			return note;
		}
		int a=  organizationDao.OrgIsExist(orgName);
		if(a==0){
			organizationDao.addOrg(orgName);                             
			note.setStatus(0);
			note.setMsg("�����֯�ɹ�");
			note.setData(""); 
		}else{
			note.setStatus(1);
			note.setMsg("��֯�Ѵ���");
			note.setData(""); 	
		}
		            
		return note;
	}

		/**
		 * �ı���֯����
		 */
	public NoteResult changeOrgName(String oldOrgName, String nowOrgName) {
		NoteResult note=new NoteResult();
		if(null==oldOrgName||null==nowOrgName){
			note.setStatus(2);
			note.setMsg("��֯���Ʋ���Ϊ��");
			note.setData("");
			return note;
		}
		organizationDao.changeOrgName(oldOrgName, nowOrgName);
		
		note.setStatus(0);
		note.setMsg("������֯���Ƴɹ�");
		note.setData(""); 
		return note;
	}

	/**
	 * ɾ����֯
	 */
	public NoteResult deleteOrg(String orgName) {
		
		NoteResult note=new NoteResult();
		if(null==orgName){
			note.setStatus(2);
			note.setMsg("��֯���Ʋ���Ϊ��");
			note.setData("");
			return note;
		}
		organizationDao.deleteOrg(orgName);
		note.setStatus(0);
		note.setMsg("ɾ����֯�ɹ�");
		note.setData(""); 
		return note;
	}


	/**
	 * ��ѯ��֯�б�
	 */
	public NoteResult selectOrg() {
		NoteResult note=new NoteResult();
	    List<Organization> list=organizationDao.selectOrg();
	    note.setStatus(0);
		note.setMsg("��ѯ�ɹ�");
		note.setData(list); 
		return note;
		
	}

}
