/**
 * 
 */
package zn.until;

import cn.jpush.api.JPushClient;

/**
 * @author hq
 *
 */
public class JPushClientExample {
	 //在极光注册上传应用的 appKey 和 masterSecret  
    private static final String appKey ="afe596d144e99491a66f2169";////必填，例如466f7032ac604e02fb7bda89  
    private static final String masterSecret = "45742427c5a0660930e4993a";//必填，每个应用都对应一个masterSecret  
    private static JPushClient jpush = null;  
    private static long timeToLive =  60 * 60 * 24;    
    
    public static void main(String[] args) {  
        /* 
         * Example1: 初始化,默认发送给android和ios，同时设置离线消息存活时间 
         * jpush = new JPushClient(masterSecret, appKey, timeToLive); 
         *  
         * Example2: 只发送给android         *  
         * Example3: 只发送给IOS 
         * jpush = new JPushClient(masterSecret, appKey, DeviceEnum.IOS); 
         *  
         * Example4: 只发送给android,同时设置离线消息存活时间 
         * jpush = new JPushClient(masterSecret, appKey, timeToLive, DeviceEnum.Android); 
         */  
        jpush = new JPushClient(masterSecret, appKey);  
        /*  
         * 是否启用ssl安全连接, 可选 
         * 参数：启用true， 禁用false，默认为非ssl连接 
         */  
        
  
        //测试发送消息或者通知  
        
    }  
}
