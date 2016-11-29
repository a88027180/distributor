package zn.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

import zn.dao.LoginDao;
import zn.dao.UserDao;
import zn.entity.User;
import zn.until.NoteResult;
import zn.until.NoteUtil;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Resource//ע��
	private UserDao userDao;
	
	@Resource
	private LoginDao loginDao;
	
	/**
	 * ����û���½
	 */
	public NoteResult checkLogin(String telephone,String password){
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date((new Date()).getTime()-2*60*60*1000);
		String agoTime=format.format(date);
		loginDao.deleteOutModedCount(agoTime);//�������2Сʱ�ĵ�½��¼
		NoteResult note=new NoteResult();
		int state=userDao.checkTel(telephone);	
		if(state==0){          					//����û����˺��Ƿ���ȷ    
		note.setStatus(1);
		note.setMsg("�˺Ŵ���");
		note.setData("");
		}else{
			String pass=NoteUtil.md5(password);   //���������
			int userState=userDao.checkLogin(telephone, pass);  
			int userId=userDao.selectIdByTel(telephone);
			if(userState==0){
				Integer loginState=loginDao.countIsExist(userId);
				if(loginState==null){
					loginDao.addCount(userId);
					note.setStatus(2);
					note.setMsg("�������");
					note.setData("");
				}else if(loginState>=4){
					note.setStatus(3);
					note.setMsg("������󳬹�5��");
					note.setData("");		
				}else{
					loginDao.autoAddCount(userId);
				note.setStatus(2);
				note.setMsg("�������");
				note.setData("");	
				}
			}else{
				loginDao.deleteCountByuserId(userId);
				String lastLoadTime=format.format(new Date());
				userDao.changeUserState(userId,lastLoadTime);
				note.setStatus(1);
				note.setMsg("��½�ɹ�");
				note.setData(userId);				
			}
		}		
		return note;
	}
	
	  
	/**
	 * �����˺��Ƿ����
	 */
	public NoteResult checkTel(String checkTel) {
		NoteResult note=new NoteResult();
		int state=userDao.checkTel(checkTel);	
		if(state!=0){
		note.setStatus(1);
		note.setMsg("�˺Ŵ���");
		note.setData("");
		}else{
			note.setStatus(0);
			note.setMsg("�˺Ų�����");
			note.setData("");	

		}	
	  return note;
	}


	/**
	 * �����˺�
	 */
	public NoteResult creatUser(String userStr,Integer userId) {
		NoteResult note=new NoteResult();
	    Integer a= 	userDao.seleteUserLimitsById(userId);
		if(userId==null){
			note.setStatus(5);
			note.setMsg("����Ϊ��");
			note.setData("");
			return note;
		}else if(a!=1||a==null){
			note.setStatus(6);
			note.setMsg("Ȩ�޲���");
			note.setData("");
			return note;
		}
		try {
		User user=JSON.parseObject(userStr, User.class);
		if(user==null){
			note.setStatus(5);
			note.setMsg("����Ϊ��");
			note.setData("");
			return note;
		}
		String telephone=user.getTelephone();
		String password=user.getPassword();
		String userName=user.getUserName();
		Integer limitsId=user.getLimitsId();
		if(telephone==null){
			note.setStatus(1);
			note.setMsg("�ֻ��Ų���Ϊ��");
			note.setData("");	
		}else if(!telephone.matches("^[1]\\d{10}$")){
			note.setStatus(1);
			note.setMsg("�ֻ��Ÿ�ʽ����ȷ");
			note.setData("");	
		}else if(userDao.checkTel(telephone)!=0){
			note.setStatus(1);
			note.setMsg("�ֻ����ѱ�ע��");
			note.setData("");	
		}else if(password==null){
			note.setStatus(2);
			note.setMsg("���벻��Ϊ��");
			note.setData("");	
		}else if(!password.matches("^\\w{6,16}$")){
			note.setStatus(2);
			note.setMsg("�����ʽ����,����Ϊ����6λС��16λ�Ĵ����ֻ���ĸ");
			note.setData("");		
		}else if(limitsId==0){
			note.setStatus(5);
			note.setMsg("Ȩ��id����Ϊ��");
			note.setData("");	
		}else if(userName==null){
			note.setStatus(3);
			note.setMsg("�û�������Ϊ��");
			note.setData("");	
		}else{
				
			user.setPassword(NoteUtil.md5(password));
			userDao.addUserAndGetId(user);
			userDao.addUserAndOrg(user);
			userDao.userDeleteLimits(user.getUserId());
			userDao.userAddLimits(user.getLimitsId(), user.getUserId());
			note.setStatus(0);
			note.setMsg("�����û��ɹ�");
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
	 * �����û���Ϣ.
	 */
	public NoteResult changeUserInfo( String information, String userName,Integer userId,Integer orgId) {
		NoteResult note=new NoteResult();
		if(userName==null||userId==null||orgId==null){
			note.setStatus(1);
			note.setMsg("��������Ϊ��");
			note.setData("");	
		}else{
		User user=new User();
		user.setUserId(userId);
		user.setOrgId(orgId);
		userDao.addUserAndOrg(user);
		userDao.changeUserInfo(information, userName,userId);
		note.setStatus(0);
		note.setMsg("���ĳɹ�");
		note.setData("");
		}
		return note;
	}


	/**
	 * ������֯��ѯ�û�
	 */
	public NoteResult selectUserByOrg(Integer orgId) {
		NoteResult note=new NoteResult();
		if(orgId==0){
			note.setStatus(1);
			note.setMsg("��������Ϊ��");
			note.setData("");	
		}else{
		List<User> list=userDao.selectUserByOrg(orgId);
			note.setStatus(0);
			note.setMsg("��ѯ�ɹ�");
			note.setData(list);	
		}
		return note;
	}


	/**
	 * �����û�id��ѯ�û�
	 */
	public NoteResult selectUserById(Integer userId) {
		NoteResult note=new NoteResult();
		if(userId==null){
			note.setStatus(1);
			note.setMsg("��������Ϊ��");
			note.setData("");	
		}else{
		User list=userDao.selectUserById(userId);
			note.setStatus(0);
			note.setMsg("��ѯ�ɹ�");
			note.setData(list);	
		}
		return note;
	}


	/**
	 * �����û�����
	 */
	public NoteResult changePassword(String oldPassword,String nowFirstPassword,String nowTwoPassword,Integer userId) {
		NoteResult note=new NoteResult();
		User  user=userDao.selectUserById(userId);
		if(oldPassword==null||userId==null||nowFirstPassword==null||nowTwoPassword==null){
			note.setStatus(1);
			note.setMsg("��������Ϊ��");
			note.setData("");	
		}else if(user.getLimitsId()!=1){
			note.setStatus(6);
			note.setMsg("Ȩ�޲���");
			note.setData("");
		}else if(!user.getPassword().equals(NoteUtil.md5(oldPassword))){
			note.setStatus(3);
			note.setMsg("ԭ�����������");
			note.setData("");	
		}else if((!nowFirstPassword.matches("^\\w{6,16}$"))||(!nowTwoPassword.matches("^\\w{6,16}$"))){
			note.setStatus(2);
			note.setMsg("�����ʽ����,����Ϊ����6λС��16λ�Ĵ����ֻ���ĸ");
			note.setData("");
		}else if(!nowFirstPassword.equals(nowTwoPassword)){
			note.setStatus(4);
			note.setMsg("�����������벻һ��");
			note.setData("");
		}else if(oldPassword.equals(nowTwoPassword)){
			note.setStatus(5);
			note.setMsg("�޸�����ͳ�ʼ������ͬ");
			note.setData("");
		}
		else{
			userDao.changePassword(NoteUtil.md5(nowFirstPassword), userId);
			note.setStatus(0);
			note.setMsg("��������ɹ�");
			note.setData("");
		}
		return note;
	}


	/**
	 * ����idɾ���û�
	 */
	public NoteResult deleteUser(Integer userId) {
		NoteResult note=new NoteResult();
		if(userId==null){
			note.setStatus(1);
			note.setMsg("��������Ϊ��");
			note.setData("");	
		}else{
			userDao.deleteUser(userId);
			note.setStatus(0);
			note.setMsg("ɾ���û��ɹ�");
			note.setData("");	
		}
		return note;
	}


	/**
	 * �û�����豸����
	 */
	public NoteResult userAddMon(String jsonStr) {
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
		}else if(map.get("userId")==null){
			note.setStatus(2);
			note.setMsg("�û�idΪ��");
			note.setData("");
			
		}else if(map.get("monList")==null||"".equals(map.get("monList"))){
			userDao.userDelteMon((Integer)map.get("userId"));
			note.setStatus(0);
			note.setMsg("����û��豸�б�ɹ�");
			note.setData("");
			
		}else{
			userDao.userDelteMon((Integer)map.get("userId"));
			userDao.userAddMon(map);
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
	 * ��ѯָ���豸�µ������û�
	 */
	public NoteResult seleteUserByMonId(Integer monId) {
		NoteResult note=new NoteResult();
		
		if(monId==null){
			note.setStatus(5);
			note.setMsg("����Ϊ��");
			note.setData("");
			
		}else{
		List<User> list=userDao.seleteUserByMonId(monId);
			note.setStatus(5);
			note.setMsg("�����ɹ�");
			note.setData(list);
		}
		return note;
	}
	
	
	
}
