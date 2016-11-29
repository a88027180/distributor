/**
 * 
 */
package zn.service;

import javax.annotation.Resource;
import javax.sound.midi.SysexMessage;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zn.dao.MonAlarmsDao;
import zn.dao.MonDateDao;
import zn.dao.MonitorDao;
import zn.entity.MonAlarms;
import zn.entity.MonDate;
import zn.entity.Monitor;
import zn.until.EncodeUtils;
import zn.until.UdpServerSocket;


/**
 * @author hq
 *
 */
@Service("analysisService")
public class AnalysisServiceImpl implements AnalysisService{
			
	
	@Resource//ע��
	private	MonAlarmsDao monAlarmsDao; 
	
	@Resource//ע��
	private	MonDateDao monDateDao; 
	
	@Resource//ע��
	private	MonitorDao monitorDao; 
	
	@Async
	public void  analysisMon(UdpServerSocket udpServerSocket){
		
		  try {
			 
			  while(true){
				 

				  String be=udpServerSocket.receive();
				  String bex=be.replaceAll("\\s", "");
				    byte[] hex=bex.getBytes();
				    String monNumber=bex.substring(bex.length()-32, bex.length());
	            analysisHex(hex,monNumber);
	                
			  }
		  
		  } catch (Exception e) {
			  
			e.printStackTrace();
		}    
	}
	
	/**
	 * ����������hex�ļ�
	 * @Title: analysisHex 
	 * @Description: TODO
	 * @param hex
	 * @return   
	 * @throws
	 */
	@Async
	public void analysisHex(byte[] hex,String monNumber){
		MonDate mon=new MonDate();
		byte[] mes=hex;
	
		
		Monitor monit=monitorDao.selectMonByNum(monNumber.toUpperCase());
		if(monit==null){
			return ;
		}
		mon.setMonId(monit.getMonId());
		

		
	//��ȡ��ѹ����ʵʱֵ
	if(mes[8]==3){	
		if(mes[12]==6){	//��·����6·
			//A���ܵ�ѹ
			mon.setAllAV(EncodeUtils.byte2float(mes, 16)+"");
			//B���ܵ�ѹ
			mon.setAllBV(EncodeUtils.byte2float(mes, 20)+"");
			//C���ܵ�ѹ
			mon.setAllCV(EncodeUtils.byte2float(mes, 24)+"");
			//A���ܵ���
			mon.setAllAA(EncodeUtils.byte2float(mes, 28)+"");
			//B���ܵ���
			mon.setAllBA(EncodeUtils.byte2float(mes, 32)+"");
			//C���ܵ���
			mon.setAllCA(EncodeUtils.byte2float(mes, 36)+"");
			//ʪ��ֵ
			mon.setHumidity(EncodeUtils.byte2float(mes, 40)+"");
			//��һ·����
			mon.setA1(EncodeUtils.byte2float(mes, 44)+","+EncodeUtils.byte2float(mes, 48)+","+EncodeUtils.byte2float(mes, 52)+","+EncodeUtils.byte2float(mes, 116));
			//�ڶ�·����
			mon.setA2(EncodeUtils.byte2float(mes, 56)+","+EncodeUtils.byte2float(mes, 60)+","+EncodeUtils.byte2float(mes, 64)+","+EncodeUtils.byte2float(mes, 120));
			//����·����
			mon.setA3(EncodeUtils.byte2float(mes, 68)+","+EncodeUtils.byte2float(mes, 72)+","+EncodeUtils.byte2float(mes, 76)+","+EncodeUtils.byte2float(mes, 124));
			//����·����
			mon.setA4(EncodeUtils.byte2float(mes, 80)+","+EncodeUtils.byte2float(mes, 84)+","+EncodeUtils.byte2float(mes, 88)+","+EncodeUtils.byte2float(mes, 128));
			//����·����
			mon.setA5(EncodeUtils.byte2float(mes, 92)+","+EncodeUtils.byte2float(mes, 96)+","+EncodeUtils.byte2float(mes, 100)+","+EncodeUtils.byte2float(mes, 132));
			//����·����
			mon.setA6(EncodeUtils.byte2float(mes, 104)+","+EncodeUtils.byte2float(mes, 108)+","+EncodeUtils.byte2float(mes, 112)+","+EncodeUtils.byte2float(mes, 136));				
		}else if(mes[12]==12){       //12·
			//����·����
			mon.setA7(EncodeUtils.byte2float(mes, 20)+","+EncodeUtils.byte2float(mes, 24)+","+EncodeUtils.byte2float(mes, 28)+","+EncodeUtils.byte2float(mes, 92));
			//�ڰ˵���
			mon.setA8(EncodeUtils.byte2float(mes, 32)+","+EncodeUtils.byte2float(mes, 36)+","+EncodeUtils.byte2float(mes, 40)+","+EncodeUtils.byte2float(mes, 96));
			//�ھ�·����
			mon.setA9(EncodeUtils.byte2float(mes, 44)+","+EncodeUtils.byte2float(mes, 48)+","+EncodeUtils.byte2float(mes, 52)+","+EncodeUtils.byte2float(mes, 100));
			//��ʮ·����
			mon.setA10(EncodeUtils.byte2float(mes, 56)+","+EncodeUtils.byte2float(mes, 60)+","+EncodeUtils.byte2float(mes, 64)+","+EncodeUtils.byte2float(mes, 104));
			//��ʮһ·����
			mon.setA11(EncodeUtils.byte2float(mes, 68)+","+EncodeUtils.byte2float(mes, 72)+","+EncodeUtils.byte2float(mes, 76)+","+EncodeUtils.byte2float(mes, 108));
			//��ʮ��·����
			mon.setA12(EncodeUtils.byte2float(mes, 80)+","+EncodeUtils.byte2float(mes, 84)+","+EncodeUtils.byte2float(mes, 88)+","+EncodeUtils.byte2float(mes, 112));					
		}		
	}else if(mes[8]==10){
           String T1="";
           String T2="";
           String T3="";
           String T4=""; 
           String T5="";
           String T6="";
           String T7="";
           String T8="";
           String T9="";
           String T10="";
           String T11="";
           String T12="";  
           for(int i=16;i<mes.length-32;i=i+8){
        	   if(mes[i]==1&&mes[i+3]==0){
        		   if(mes[i+1]==1){
        			  
        			   T1=T1+pathT(mes[i+2])+EncodeUtils.byte2float(mes, i+4)+",";
        		   }else if(mes[i+1]==2){		
        			   T2=T2+pathT(mes[i+2])+EncodeUtils.byte2float(mes, i+4)+",";
        		   }else if(mes[i+1]==3){
        			   T3=T3+pathT(mes[i+2])+EncodeUtils.byte2float(mes, i+4)+",";
        		   }else if(mes[i+1]==4){
        			   T4=T4+pathT(mes[i+2])+EncodeUtils.byte2float(mes, i+4)+",";
        		   }else if(mes[i+1]==5){
        			   T5=T5+pathT(mes[i+2])+EncodeUtils.byte2float(mes, i+4)+",";
        		   }else if(mes[i+1]==6){
        			   T6=T6+pathT(mes[i+2])+EncodeUtils.byte2float(mes, i+4)+",";
        		   }else if(mes[i+1]==7){
        			   T7=T7+pathT(mes[i+2])+EncodeUtils.byte2float(mes, i+4)+",";
        		   }else if(mes[i+1]==8){
        			   T8=T8+pathT(mes[i+2])+EncodeUtils.byte2float(mes, i+4)+",";
        		   }else if(mes[i+1]==9){
        			   T9=T9+pathT(mes[i+2])+EncodeUtils.byte2float(mes, i+4)+",";
        		   }else if(mes[i+1]==10){
        			   T10=T10+pathT(mes[i+2])+EncodeUtils.byte2float(mes, i+4)+",";
        		   }else if(mes[i+1]==11){
        			   T11=T11+pathT(mes[i+2])+EncodeUtils.byte2float(mes, i+4)+",";
        		   }else if(mes[i+1]==12){
        			   T12=T12+pathT(mes[i+2])+EncodeUtils.byte2float(mes, i+4)+",";
        		   }
		  
        	   }else if(mes[i]==2&&mes[i+3]==0){
        		   mon.setMonInT(EncodeUtils.byte2float(mes, i+4)+"");
        	   }
           }

           mon.setT1(T1);
           mon.setT2(T2);
           mon.setT3(T3);
           mon.setT4(T4);
           mon.setT5(T5);
           mon.setT6(T6);
           mon.setT7(T7);
           mon.setT8(T8);
           mon.setT9(T9);
           mon.setT10(T10);
           mon.setT11(T11);
           mon.setT12(T12);
    
        
	}else if(mes[8]==2){
	 String  states=EncodeUtils.byte2bits(mes[18])+EncodeUtils.byte2bits(mes[19]);
	 
	 mon.setMonSwitch(states.substring(states.length()-12, states.length()));
		
	}else if(mes[8]==30){
		MonAlarms monAlarms=analysisWarningHex(mes);
		
		monAlarms.setMonId(monit.getMonId());
		if(monAlarmsDao.selectAlarmsDateIsExist(monit.getMonId())==0){
		monAlarmsDao.addMonAlarms(monAlarms);	
		}else{
			monAlarmsDao.changeMonAlarms(monAlarms);
		}
		return;
	}
	
	if(monDateDao.selectMonDateIsExist(monit.getMonId())==0){
		
		monDateDao.addMonDate(mon);
	}else{
		monDateDao.changeMonDate(mon);
	}
	
	}
	
	public String pathT(int path){
		if(path==3){
			return "\"singleT\":";
		}else if(path==16){
			return "\"AT\":";
		}else if(path==17){
			return "\"BT\":";
		}else if(path==18){
			return "\"CT\":";
		}else if(path==19){
			return "\"NT\":";
		}
		return "";
	}
	
	
	
	/**
	 * ����������Ϣ
	 * @Title: analysisWarningHex 
	 * @Description: TODO
	 * @param  byte[]����
	 * @return   MonAlarms
	 * @throws
	 */
	@Async
	public MonAlarms  analysisWarningHex(byte[] mes){
		MonAlarms mon=new MonAlarms();
		String monAlarmsTM=EncodeUtils.getLong(mes, 16)+"-"+EncodeUtils.getLong(mes, 20)+"-"+EncodeUtils.getLong(mes, 24)+" "+EncodeUtils.getLong(mes, 28)+":"+EncodeUtils.getLong(mes, 32)+":"+EncodeUtils.getLong(mes, 36);
		mon.setMonAlarmsTM(monAlarmsTM);
		mon.setMonAlarmsType(Long.toHexString(EncodeUtils.getLong(mes, 12)));
		//�����ϴ��¶ȸ澯��Ϣ
		if(mes[12]==16){	
			mon.setMonAlarmsInfo("�����¶ȹ��߸澯,�¶�ֵΪ"+EncodeUtils.byte2float(mes, 56));					
		}else if(mes[12]==19){             //����������Ϣ				
			long monV=EncodeUtils.getLong(mes, 44);
			long p=EncodeUtils.getLong(mes, 48);
			String mov="";
			if(monV==16){
				mov="220V";
			}else if(monV==17){
				mov="380V";
			}
			String pv="";
			if(p==16){
				pv="A";
			}else if(p==17){
				pv="B";					
			}else if(p==18){
				pv="C";
			}	
			mon.setMonAlarmsInfo("ͨ��"+EncodeUtils.getLong(mes, 40)+"�����ϴ��������ر���,��·��ѹΪ"+mov+","+pv+"��,����ֵΪ"+EncodeUtils.byte2float(mes, 56));			
		}else if(mes[12]==21){
			String pv="";
			long p=EncodeUtils.getLong(mes, 48);
			if(p==16){
				pv="A";
			}else if(p==17){
				pv="B";					
			}else if(p==18){
				pv="C";
			}
			mon.setMonAlarmsInfo("�����ϴ��ܵ�ѹ���߸澯,"+pv+"��,��ѹֵΪ"+EncodeUtils.byte2float(mes, 56));
		}else if(mes[12]==20){
			String pv="";
			long p=EncodeUtils.getLong(mes, 48);
			if(p==16){
				pv="A";
			}else if(p==17){
				pv="B";					
			}else if(p==18){
				pv="C";
			}
			mon.setMonAlarmsInfo("�����ϴ��ܵ������ظ澯,"+pv+"��,����ֵΪ"+EncodeUtils.byte2float(mes, 56));
		}else if(mes[12]==22){
			String pv="";
			long p=EncodeUtils.getLong(mes, 48);
			if(p==16){
				pv="A";
			}else if(p==17){
				pv="B";					
			}else if(p==18){
				pv="C";
			}
			mon.setMonAlarmsInfo("�����ϴ��ܵ�ѹ���͸澯,"+pv+"��,��ѹֵΪ"+EncodeUtils.byte2float(mes, 56));
		}else if(mes[12]==24){
			mon.setMonAlarmsInfo("�����ϴ�ʪ�ȹ��߸澯,ʪ��ֵΪ"+EncodeUtils.byte2float(mes, 56));
		}else if(mes[12]==55){
			mon.setMonAlarmsInfo("ͣ�籨��");				
		}else if(mes[12]==25){
			long monV=EncodeUtils.getLong(mes, 44);
			
			String mov="";
			if(monV==16){
				mov="220V";
			}else if(monV==17){
				mov="380V";
			}
				
			mon.setMonAlarmsInfo("ͨ��"+EncodeUtils.getLong(mes, 40)+"�����ϴ���·©�籨��,��·��ѹΪ"+mov+",©����ֵΪ"+EncodeUtils.byte2float(mes, 56));			
		}else if(mes[12]==32){
			long monV=EncodeUtils.getLong(mes, 44);
			long p=EncodeUtils.getLong(mes, 48);
			long mT=EncodeUtils.getLong(mes, 40);
			String mov="";
			if(monV==16){
				mov="����";
			}else if(monV==17){
				mov="����";
			}
			String pv="";
			if(p==16){
				pv="A��,";
			}else if(p==17){
				pv="B��,";					
			}else if(p==18){
				pv="C��,";
			}else if(p==19){
				pv="����,";
			}
			String T="";
			if(mT==4294967295L){
				T="������";
			}else{
				T="ͨ��"+mT;
			}
			mon.setMonAlarmsInfo("ͨ��"+T+"�����ϴ���·���¶ȹ��߱���,��·Ϊ"+mov+","+pv+"�¶�ֵΪ"+EncodeUtils.byte2float(mes, 56));	
		}else if(mes[12]==33){
			long monV=EncodeUtils.getLong(mes, 44);
			long p=EncodeUtils.getLong(mes, 48);
			String mov="";
			if(monV==16){
				mov="220V";
			}else if(monV==17){
				mov="380V";
			}
			String pv="";
			if(p==16){
				pv="A";
			}else if(p==17){
				pv="B";					
			}else if(p==18){
				pv="C";
			}	
			mon.setMonAlarmsInfo("ͨ��"+EncodeUtils.getLong(mes, 40)+"��𱨾�,��·��ѹΪ"+mov+","+pv+"��");			
		}else if(mes[12]==34){
			long monV=EncodeUtils.getLong(mes, 44);
			long p=EncodeUtils.getLong(mes, 48);
			String mov="";
			if(monV==16){
				mov="220V";
			}else if(monV==17){
				mov="380V";
			}
			String pv="";
			if(p==16){
				pv="A";
			}else if(p==17){
				pv="B";					
			}else if(p==18){
				pv="C";
			}	
			mon.setMonAlarmsInfo("ͨ��"+EncodeUtils.getLong(mes, 40)+"��·�������߱���,��·��ѹΪ"+mov+","+pv+"��,����ֵΪ"+EncodeUtils.byte2float(mes, 56));			
			
		}else if(mes[12]==35){
			long monV=EncodeUtils.getLong(mes, 44);
			long p=EncodeUtils.getLong(mes, 48);
			long mT=EncodeUtils.getLong(mes, 40);
			String mov="";
			if(monV==16){
				mov="����";
			}else if(monV==17){
				mov="����";
			}
			String pv="";
			if(p==16){
				pv="A��,";
			}else if(p==17){
				pv="B��,";					
			}else if(p==18){
				pv="C��,";
			}else if(p==19){
				pv="����,";
			}
			String T="";
			if(mT==4294967295L){
				T="������";
			}else{
				T="ͨ��"+mT;
			}
			mon.setMonAlarmsInfo(T+"��·���¶ȹ��߱���,��·Ϊ"+mov+","+pv+"�¶�ֵΪ"+EncodeUtils.byte2float(mes, 56));		
		}		
		return mon;
	}
	
}
