/**
 * 
 */
package zn.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

import zn.dao.MonDateDao;
import zn.dao.MonitorDao;
import zn.entity.Monitor;
import zn.entity.XmlMonitor;
import zn.until.WebService;

/**
 * @author hq
 *
 */
@Component
@Transactional
public class GetDateServiceImpl {
	
	@Resource//注入
	private MonitorDao monitorDao;
	
	@Resource//注入
	private MonDateDao monDateDao;
	
	 @Scheduled(cron ="0 49 12 * * ?") //每天凌晨两点执行
     public    void doSomethingWith(){
      
       
    
	 }
	 
	 
	 public void getDate(){
		 String xmlListStr=WebService.DeviceInfoGetForJson();
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
					if(x.getSBZT()!=null){
						if ("在线".equals(x.getSBZT())) {
						monitor.setMonState(1);	
						}else if("离线".equals(x.getSBZT())){
						monitor.setMonState(2);		
						}else if("故障".equals(x.getSBZT())){
						monitor.setMonState(3);		
						}else if("报警".equals(x.getSBZT())){
						monitor.setMonState(4);		
						}
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
					if(x.getSBZT()!=null){
						if ("在线".equals(x.getSBZT())) {
						monitor.setMonState(1);	
						}else if("离线".equals(x.getSBZT())){
						monitor.setMonState(2);		
						}else if("故障".equals(x.getSBZT())){
						monitor.setMonState(3);		
						}else if("报警".equals(x.getSBZT())){
						monitor.setMonState(4);		
						}			
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
	 
	 

}
