/**
 * 
 */
package zn.until;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;

import cn.jpush.api.push.model.PushPayload;

/**
 * @author hq
 *
 */
public class JPushClientExample {
	 //在极光注册上传应用的 appKey 和 masterSecret  
    private static final String appKey ="afe596d144e99491a66f2169";////必填，例如466f7032ac604e02fb7bda89  
    private static final String masterSecret = "45742427c5a0660930e4993a";//必填，每个应用都对应一个masterSecret  
    private static JPushClient jpushClient = null;  
       
    //发送消息
    public static void jpush(String  message ) {  
    	  PushPayload payload = buildPushObject_all_all_alert(message);
    	  
    	 
    	        try {
    	        	 jpushClient=new JPushClient(masterSecret,appKey);
    	        	 jpushClient.sendPush(payload);
					
				} catch (APIConnectionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (APIRequestException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	       
 	   
    }  
    
   // 快捷地构建推送对象：所有平台，所有设备，内容为  message的通知
    private static PushPayload buildPushObject_all_all_alert(String message) {
        return PushPayload.alertAll(message);
    }
    
    
    
}
