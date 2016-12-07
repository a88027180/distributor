/**
 * 
 */
package zn.until;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.Notification;

/**
 * @author hq
 *
 */
public class JPushClientExample {
	 //在极光注册上传应用的 appKey 和 masterSecret  
    private static final String appKey ="afe596d144e99491a66f2169";////必填，例如466f7032ac604e02fb7bda89  
    private static final String masterSecret = "45742427c5a0660930e4993a";//必填，每个应用都对应一个masterSecret  
    private static JPushClient jpushClient = null;  
    private static  int maxRetryTimes = 1;    
    
    public static void main(String[] args) {  
    	  PushPayload payload = buildPushObject_ios_audienceMore_messageWithExtras();

    	   
    	        try {
					PushResult result = jpushClient.sendPush(payload);
					
				} catch (APIConnectionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (APIRequestException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	       

    	   
        
        /*  
         * 是否启用ssl安全连接, 可选 
         * 参数：启用true， 禁用false，默认为非ssl连接 
         */  
        
    	   
    }  
    public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
                        .addAudienceTarget(AudienceTarget.tag("tag1", "tag2"))
                        .addAudienceTarget(AudienceTarget.alias("alias1", "alias2"))
                        .build())
                .setMessage(Message.newBuilder()
                        .setMsgContent("")
                        .addExtra("from", "JPush")
                        .build())
                .build();
    }
}
