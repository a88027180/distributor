package zn.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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

	@Resource//注入
	private UserDao userDao;
	
	@Resource
	HttpSession session;
	
	
	
	@Resource
	private LoginDao loginDao;
	
	/**
	 * 用户登陆
	 */
	public NoteResult checkLogin(String telephone,String password){
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date((new Date()).getTime()-2*60*60*1000);
		String agoTime=format.format(date);
		loginDao.deleteOutModedCount(agoTime);//清楚超过2小时的登陆记录
		NoteResult note=new NoteResult();
		int state=userDao.checkTel(telephone);	
		if(state==0){          					//检查用户的账号是否正确    
		note.setStatus(1);
		note.setMsg("账号错误");
		note.setData("");
		}else{
			String pass=NoteUtil.md5(password);   //对密码加密
			int userState=userDao.checkLogin(telephone, pass);  
			int userId=userDao.selectIdByTel(telephone);
			if(userState==0){
				Integer loginState=loginDao.countIsExist(userId);
				if(loginState==null){
					loginDao.addCount(userId);
					note.setStatus(2);
					note.setMsg("密码错误");
					note.setData("");
				}else if(loginState>=4){
					note.setStatus(3);
					note.setMsg("密码错误超过5次");
					note.setData("");		
				}else{
					loginDao.autoAddCount(userId);
				note.setStatus(2);
				note.setMsg("密码错误");
				note.setData("");	
				}
			}else{
				loginDao.deleteCountByuserId(userId);
				String lastLoadTime=format.format(new Date());
				userDao.changeUserState(userId,lastLoadTime);
				
				note.setStatus(0);
				note.setMsg("登陆成功");
				note.setData(userId);
				session.setAttribute("telephone", telephone);
				session.setAttribute("userId", userId+"");
			
			}
		}		
		return note;
	}
	
	  
	/**
	 * 检验账号是否存在
	 */
	public NoteResult checkTel(String checkTel) {
		NoteResult note=new NoteResult();
		int state=userDao.checkTel(checkTel);	
		if(state!=0){
		note.setStatus(1);
		note.setMsg("账号存在");
		note.setData("");
		}else{
			note.setStatus(0);
			note.setMsg("账号不存在");
			note.setData("");	

		}	
	  return note;
	}


	/**
	 * 创建账号
	 */
	public NoteResult creatUser(String userStr,Integer userId) {
		NoteResult note=new NoteResult();
	    Integer a= 	userDao.seleteUserLimitsById(userId);
		if(userId==null){
			note.setStatus(5);
			note.setMsg("参数为空");
			note.setData("");
			return note;
		}else if(a!=1||a==null){
			note.setStatus(6);
			note.setMsg("权限不足");
			note.setData("");
			return note;
		}
		try {
		User user=JSON.parseObject(userStr, User.class);
		if(user==null){
			note.setStatus(5);
			note.setMsg("参数为空");
			note.setData("");
			return note;
		}
		String telephone=user.getTelephone();
		String password=user.getPassword();
		String userName=user.getUserName();
		Integer limitsId=user.getLimitsId();
		if(telephone==null){
			note.setStatus(1);
			note.setMsg("手机号不能为空");
			note.setData("");	
		}else if(!telephone.matches("^[1]\\d{10}$")){
			note.setStatus(1);
			note.setMsg("手机号格式不正确");
			note.setData("");	
		}else if(userDao.checkTel(telephone)!=0){
			note.setStatus(1);
			note.setMsg("手机号已被注册");
			note.setData("");	
		}else if(password==null){
			note.setStatus(2);
			note.setMsg("密码不能为空");
			note.setData("");	
		}else if(!password.matches("^\\w{6,16}$")){
			note.setStatus(2);
			note.setMsg("密码格式不对,密码为大于6位小于16位的纯数字或字母");
			note.setData("");		
		}else if(limitsId==0){
			note.setStatus(5);
			note.setMsg("权限id不能为空");
			note.setData("");	
		}else if(userName==null){
			note.setStatus(3);
			note.setMsg("用户名不能为空");
			note.setData("");	
		}else{
				
			user.setPassword(NoteUtil.md5(password));
			userDao.addUserAndGetId(user);
			userDao.addUserAndOrg(user);
			userDao.userDeleteLimits(user.getUserId());
			userDao.userAddLimits(user.getLimitsId(), user.getUserId());
			note.setStatus(0);
			note.setMsg("创建用户成功");
			note.setData("");
		}	
		
		} catch (JSONException e) {
			note.setStatus(4);
			note.setMsg("参数格式错误");
			note.setData("");
		}catch(ClassCastException c){
			note.setStatus(4);
			note.setMsg("参数格式错误");
			note.setData("");
		}
		return note;
	}


	/**
	 * 更改用户信息.
	 */
	public NoteResult changeUserInfo( String information, String userName,Integer userId,Integer orgId) {
		NoteResult note=new NoteResult();
		if(userName==null||userId==null||orgId==null){
			note.setStatus(1);
			note.setMsg("参数不能为空");
			note.setData("");	
		}else{
		User user=new User();
		user.setUserId(userId);
		user.setOrgId(orgId);
		userDao.deleteUserAndOrg(userId);
		userDao.addUserAndOrg(user);
		userDao.changeUserInfo(information, userName,userId);
		note.setStatus(0);
		note.setMsg("更改成功");
		note.setData("");
		}
		return note;
	}


	/**
	 * 根据组织查询用户
	 */
	public NoteResult selectUserByOrg(Integer orgId) {
		NoteResult note=new NoteResult();
		if(orgId==0){
			note.setStatus(1);
			note.setMsg("参数不能为空");
			note.setData("");	
		}else{
		List<User> list=userDao.selectUserByOrg(orgId);
			note.setStatus(0);
			note.setMsg("查询成功");
			note.setData(list);	
		}
		return note;
	}
	
	public NoteResult selectAllUser(){
		NoteResult note=new NoteResult();
		List<User> list=userDao.selectAllUser();
		note.setStatus(0);
		note.setMsg("查询成功");
		note.setData(list);	
		return note;
	}


	/**
	 * 根据用户id查询用户
	 */
	public NoteResult selectUserById(Integer userId) {
		NoteResult note=new NoteResult();
		if(userId==null){
			note.setStatus(1);
			note.setMsg("参数不能为空");
			note.setData("");	
		}else{
		User list=userDao.selectUserById(userId);
			note.setStatus(0);
			note.setMsg("查询成功");
			note.setData(list);	
		}
		return note;
	}


	/**
	 * 更改用户密码
	 */
	public NoteResult changePassword(String oldPassword,String nowFirstPassword,String nowTwoPassword,Integer userId) {
		NoteResult note=new NoteResult();
		User  user=userDao.selectUserById(userId);
		if(oldPassword==null||userId==null||nowFirstPassword==null||nowTwoPassword==null){
				note.setStatus(1);
				note.setMsg("参数不能为空");
				note.setData("");	
		}else if(user.getLimitsId()!=1){
			note.setStatus(6);
			note.setMsg("权限不足");
			note.setData("");
		}else if(!user.getPassword().equals(NoteUtil.md5(oldPassword))){
			note.setStatus(3);
			note.setMsg("原密码输入错误");
			note.setData("");	
		}else if((!nowFirstPassword.matches("^\\w{6,16}$"))||(!nowTwoPassword.matches("^\\w{6,16}$"))){
			note.setStatus(2);
			note.setMsg("密码格式不对,密码为大于6位小于16位的纯数字或字母");
			note.setData("");
		}else if(!nowFirstPassword.equals(nowTwoPassword)){
			note.setStatus(4);
			note.setMsg("两次输入密码不一致");
			note.setData("");
		}else if(oldPassword.equals(nowTwoPassword)){
			note.setStatus(5);
			note.setMsg("修改密码和初始密码相同");
			note.setData("");
		}
		else{
			userDao.changePassword(NoteUtil.md5(nowFirstPassword), userId);
			note.setStatus(0);
			note.setMsg("更改密码成功");
			note.setData("");
		}
		return note;
	}


	/**
	 * 根据id删除用户
	 */
	public NoteResult deleteUser(Integer userId) {
		NoteResult note=new NoteResult();
		if(userId==null){
			note.setStatus(1);
			note.setMsg("参数不能为空");
			note.setData("");	
		}else{
			userDao.deleteUser(userId);
			note.setStatus(0);
			note.setMsg("删除用户成功");
			note.setData("");	
		}
		return note;
	}


	/**
	 * 用户添加设备关联
	 */
	public NoteResult userAddMon(String jsonStr) {
		NoteResult note=new NoteResult();
		if(jsonStr==null||"".equals(jsonStr)){
			note.setStatus(1);
			note.setMsg("参数不能为空");
			note.setData("");
			return note;
		}
		try {
			Map<String,Object> map=JSON.parseObject(jsonStr);
		if(map==null||map.isEmpty()){				
			note.setStatus(1);
			note.setMsg("参数不能为空");
			note.setData("");
			return note;
		}else if(map.get("userId")==null){
			note.setStatus(2);
			note.setMsg("用户id为空");
			note.setData("");
					
		}else if(map.get("monList")==null||"".equals(map.get("monList"))||"[]".equals(map.get("monList"))){
			userDao.userDelteMon((Integer)map.get("userId"));
			note.setStatus(0);
			note.setMsg("清空用户设备列表成功");
			note.setData("");
			
		}else{
			userDao.userDelteMon((Integer)map.get("userId"));
			userDao.userAddMon(map);
			note.setStatus(0);
			note.setMsg("操作成功");
			note.setData("");
		}
		} catch (JSONException e) {
			note.setStatus(4);
			note.setMsg("参数格式错误");
			note.setData("");
		}catch(ClassCastException c){
			note.setStatus(4);
			note.setMsg("参数格式错误");
			note.setData("");
		}
		return note;
	}


	/**
	 * 查询指定设备下的所有用户
	 */
	public NoteResult seleteUserByMonId(Integer monId) {
		NoteResult note=new NoteResult();

		
		if(monId==null){
			note.setStatus(1);
			note.setMsg("参数不能为空");
			note.setData("");
			
		}else{
		List<User> list=userDao.seleteUserByMonId(monId);
			note.setStatus(0);
			note.setMsg("操作成功");
			note.setData(list);
		}
		return note;
	}

	public NoteResult   seleteUserListByMonId(Integer monId){
	
		NoteResult note=new NoteResult();

		
		if(monId==null){
			note.setStatus(1);
			note.setMsg("参数不能为空");
			note.setData("");
			
		}else{
			List<Map<String,Object>> list=userDao.seleteUserListByMonId(monId);;
			note.setStatus(0);
			note.setMsg("操作成功");
			note.setData(list);
		}
		return note;

	

	}
	
	
}
