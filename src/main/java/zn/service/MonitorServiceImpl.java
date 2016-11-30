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
	
	@Resource//注入
	private MonitorDao monitorDao;
	
	@Resource//注入
	private MonDateDao monDateDao;

	/**
	 * 添加设备
	 */
	public NoteResult addMon(String monStr) {
		NoteResult note=new NoteResult();
		
		try {
			Monitor mon=JSON.parseObject(monStr, Monitor.class);
			if(mon==null){
				note.setStatus(1);
				note.setMsg("参数为空");
				note.setData("");
			
			}else if(mon.getMonNumber()==null){
				note.setStatus(2);
				note.setMsg("设备编号为空");
				note.setData("");
				
			}else if(monitorDao.monIsExist(mon.getMonNumber())!=0){
				note.setStatus(3);
				note.setMsg("设备编号已存在");
				note.setData("");
				
			}else{
				monitorDao.addMon(mon);
				monDateDao.addMonDateId(mon.getMonId());
				note.setStatus(0);
				note.setMsg("添加设备成功");
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
	 * 查询所有设备信息
	 */
	public NoteResult findAllMon() {
		NoteResult note=new NoteResult();
		List<Monitor> list=monitorDao.findAllMon();
		note.setStatus(0);
		note.setMsg("查询成功");
		note.setData(list);
		
		return note;
	}

	/**
	 * 删除设备成功
	 */
	public NoteResult deleteMon(Integer monId) {
		NoteResult note=new NoteResult();
		if(monId==null){
			note.setStatus(1);
			note.setMsg("参数为空");
			note.setData("");
		}else{
		
		monitorDao.deleteMon(monId);
		note.setStatus(0);
		note.setMsg("删除成功");
		note.setData("");
		}
		return note;
	}

	/**
	 * 更改设备信息
	 */
	public NoteResult changeMon(String monStr) {
		NoteResult note=new NoteResult();
		try {
			Monitor mon=JSON.parseObject(monStr, Monitor.class);
			if(mon==null){
				note.setStatus(1);
				note.setMsg("参数为空");
				note.setData("");
			}else if(mon.getMonNumber()==null){
				note.setStatus(2);
				note.setMsg("设备编号为空");
				note.setData("");
				
			}else if(monitorDao.monIsExist(mon.getMonNumber())!=0){
				note.setStatus(3);
				note.setMsg("设备编号已存在");
				note.setData("");
				
			}else{
				monitorDao.changeMon(mon);
				note.setStatus(0);
				note.setMsg("更改设备信息成功");
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
	 * 给指定设备添加用户列表
	 */
	public NoteResult monAddUser(String jsonStr) {
		NoteResult note=new NoteResult();
		if(jsonStr==null||"".equals(jsonStr)){
			note.setStatus(5);
			note.setMsg("参数为空");
			note.setData("");
			return note;
		}
		try {
			Map<String,Object> map=JSON.parseObject(jsonStr);
		if(map==null||map.isEmpty()){				
			note.setStatus(5);
			note.setMsg("参数为空");
			note.setData("");
			return note;
		}else if(map.get("monId")==null){
			note.setStatus(2);
			note.setMsg("设备id为空");
			note.setData("");
			
		}else if(map.get("userList")==null||"".equals(map.get("userList"))){
			monitorDao.monDelteUser((Integer)map.get("monId"));
			note.setStatus(0);
			note.setMsg("清空设备用户列表成功");
			note.setData("");
			
		}else{
			monitorDao.monDelteUser((Integer)map.get("monId"));
			monitorDao.monAddUser(map);
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
	 * 查询指定用户下的所有设备
	 */
	public NoteResult seleteMonByUserId(Integer userId) {
		NoteResult note=new NoteResult();
		
		if(userId==null){
			note.setStatus(5);
			note.setMsg("参数为空");
			note.setData("");
			
		}else{
		List<Monitor> list=monitorDao.seleteMonByUserId(userId);
			note.setStatus(5);
			note.setMsg("操作成功");
			note.setData(list);
		}
		return note;	
	}

}
