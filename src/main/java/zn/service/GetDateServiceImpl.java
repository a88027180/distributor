/**
 * 
 */
package zn.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

import zn.dao.MonDateDao;
import zn.dao.MonListDao;
import zn.dao.MonitorDao;
import zn.entity.MonDate;
import zn.entity.MonList;
import zn.entity.MonSon;
import zn.entity.Monitor;
import zn.entity.XmlMonList;
import zn.entity.XmlMonSon;
import zn.entity.XmlMonitor;
import zn.until.NoteResult;
import zn.until.WebService;

/**
 * @author hq
 *
 */

@Service("getDateService") 
@Transactional
public class GetDateServiceImpl implements GetDateService{
	
	@Resource//注入
	private MonitorDao monitorDao;
	
	@Resource//注入
	private MonDateDao monDateDao;
	
	@Resource//注入
	private MonListDao monListdDao;
	

	 
	 /**
	  * 同步数据
	  * @Title: getDate 
	  * @Description: TODO
	  * @return   
	  * @throws
	  */
	 public NoteResult getDate(){
		 NoteResult note=new NoteResult();
		 DeviceInfoGetForJson();
		 RegionInfoGetForJson();
		 ITPCDeviceChannelInfoGetByIDForJson();
		 	note.setStatus(0);
			note.setMsg("同步成功");
			note.setData("");
		 return note;
	 }
	 
	 
	 public void ITPCDeviceChannelInfoGetByIDForJson(){
		 List<String>  monNumberList=monitorDao.selectAllmonNumber();
		
		 if(monNumberList.isEmpty()){
			 return ;
		 }
		 
		 for(String monNumber: monNumberList){
			
		 String xmlListStr=new WebService().ITPCDeviceChannelInfoGetByIDForJson(monNumber);
	
		 if("".equals(xmlListStr)||xmlListStr==null){
			 return ;
		 }
		 List<XmlMonSon> xmlMonList=(List<XmlMonSon>)JSON.parse(xmlListStr);
		   if(!xmlMonList.isEmpty()){
		  for(XmlMonSon monSon:xmlMonList){
			 
			  MonDate monDate=new MonDate();
			  monDate.setMonId(monitorDao.selectMonByNum(monNumber).getMonId());
			  if (monSon.getTDHM()==1) {
				monDate.setName1(monSon.getTDMC());
			}else if(monSon.getTDHM()==2) {
				monDate.setName2(monSon.getTDMC());
			}else if(monSon.getTDHM()==3) {
				monDate.setName3(monSon.getTDMC());
			}else if(monSon.getTDHM()==4) {
				monDate.setName4(monSon.getTDMC());
			}else if(monSon.getTDHM()==5) {
				monDate.setName5(monSon.getTDMC());
			}else if(monSon.getTDHM()==6) {
				monDate.setName6(monSon.getTDMC());
			}else if(monSon.getTDHM()==7) {
				monDate.setName7(monSon.getTDMC());
			}else if(monSon.getTDHM()==8) {
				monDate.setName8(monSon.getTDMC());
			}else if(monSon.getTDHM()==9) {
				monDate.setName9(monSon.getTDMC());
			}else if(monSon.getTDHM()==10) {
				monDate.setName10(monSon.getTDMC());
			}else if(monSon.getTDHM()==11) {
				monDate.setName11(monSon.getTDMC());
			}else if(monSon.getTDHM()==12) {
				monDate.setName12(monSon.getTDMC());
			}
			  
			  monDateDao.addMonDate(monDate);
		  }
		  }
	 }
	 }
	 
	 /**
	  * 获取所有列表信息
	  * @Title: RegionInfoGetForJson 
	  * @Description: TODO   
	  * @throws
	  */
	 public void RegionInfoGetForJson(){
		 String xmlListStr=new WebService().RegionInfoGetForJson();
		 if("".equals(xmlListStr)||xmlListStr==null){
			 return ;
		 }
		 List<XmlMonList> xmlMonList=(List<XmlMonList>)JSON.parse(xmlListStr);
		 List<MonList> monList=new ArrayList<MonList>();
		
		
		 monListdDao.deleteAllMonList();
		 if(!xmlMonList.isEmpty()){
			 for(XmlMonList x:xmlMonList){
				 MonList mon=new MonList();
				 mon.setListLevel(x.getQYPX());
				 mon.setMonListId(x.getQYBH());
				 mon.setMonListName(x.getQYMC());
				 mon.setSuperiorListId(x.getSJQY());
				 monList.add(mon);
			 }
		 monListdDao.addAllMonList(monList);
		 
		 }
	 }
	 
	 
	 /**
	  * 获取所有设备信息
	  * @Title: DeviceInfoGetForJson 
	  * @Description: TODO   
	  * @throws
	  */
	 public void DeviceInfoGetForJson(){
		 String xmlListStr=new WebService().DeviceInfoGetForJson();
		 if("".equals(xmlListStr)||xmlListStr==null){
			 return ;
		 }
		 List<XmlMonitor>  xmlList= (List<XmlMonitor>) JSON.parseObject(xmlListStr);
		 List<String> wen=new ArrayList<String>();
		 for(XmlMonitor x:xmlList){
				wen.add(x.getSBBH());
			}	
			List<String> wen2=new ArrayList<String>(wen);
			List<String> wen3=new ArrayList<String>(wen);
			
			List<String>  wen4=monitorDao.selectAllmonNumber();
			List<Monitor>  list3=new ArrayList<Monitor>();
			wen.retainAll(wen4);			
			wen2.removeAll(wen4);		
			wen4.removeAll(wen3);
		
			for(XmlMonitor x:xmlList){
				if(wen.contains(x.getSBBH())){
					Monitor monitor=new Monitor();
					monitor.setMonNumber(x.getSBBH());
					monitor.setMonName(x.getSBMC());
					monitor.setMonIP(x.getSBIP());
					monitor.setMonPlace(x.getSBQY());
					monitor.setMonType(x.getSBLX());
					monitor.setMonInstall(x.getAZWZ());
					monitor.setMonPlaceName(x.getSBQYMC());
					if(x.getSBZT()!=null){
						monitor.setMonState(getMonState(x.getSBZT()));
					}
					monitorDao.changeMonByMonNumber(monitor);
				}
				if(wen2.contains(x.getSBBH())){
					Monitor monitor=new Monitor();
					monitor.setMonNumber(x.getSBBH());
					monitor.setMonName(x.getSBMC());
					monitor.setMonIP(x.getSBIP());
					monitor.setMonPlace(x.getSBQY());
					monitor.setMonType(x.getSBLX());
					monitor.setMonInstall(x.getAZWZ());
					monitor.setMonPlaceName(x.getSBQYMC());
					if(x.getSBZT()!=null){
						
						monitor.setMonState(getMonState(x.getSBZT()));	
							
					}
					list3.add(monitor);
				}
			}
			if(!list3.isEmpty()){
			monitorDao.addMoreMon(list3);
			List<Integer> list4  =monitorDao.selectMonIdByMonNumber(wen2);
			if(!list4.isEmpty()){
			monDateDao.addMonDateIdList(list4);
			}
			}
			if(!wen4.isEmpty()){
			monitorDao.deleteMonByMonNumber(wen4);
			}
		}
	
	 

	 public int  getMonState(String monState){
	 if ("在线".equals(monState)) {
			return 1;
			}else if("离线".equals(monState)){
				return 2;	
			}else if("故障".equals(monState)){
				return 3;		
			}else if("报警".equals(monState)){
				return 4;	
			}
	 		return 0;
	}
}
