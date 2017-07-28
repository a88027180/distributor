/**
 * 
 */
package zn.until;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.text.DecimalFormat;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.soap.SOAP12Constants;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.springframework.format.datetime.joda.MillisecondInstantPrinter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import zn.listener.AnalysisInfoListener;

/**
 * @author hq
 *
 */
public class WebService {
	
	private static EndpointReference targetEPR = new EndpointReference(loadAddress());
	
	
	
	public static  String loadAddress(){
		InputStream inStream = AnalysisInfoListener.class.getClassLoader().getResourceAsStream("./udp.properties");
		Properties prop = new Properties();
		try {
			prop.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String  address= prop.getProperty("WebService");
		return address;
	}
	
	
	/**
	 * 修改用户名字
	 * @Title: ModifyUserName 
	 * @Description: TODO
	 * @param userNumber
	 * @param userName   
	 * @throws
	 */
	public String ModifyUserName(String userNumber,String userName){
		 String   resultStr="";
		try {
			OMFactory fac = OMAbstractFactory.getOMFactory();

		    OMNamespace omNs = fac.createOMNamespace(
		            "SFBR_Web_API", "tns");// 命名空间

		    // 请求参数设置
		    Options options = new Options();
		    options.setTo(targetEPR);// 设定webservice地址
		    options.setTransportInProtocol(Constants.TRANSPORT_HTTP);// 设定传输协议
		    options.setSoapVersionURI(SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI);// 设定SOAP版本soap1.2

		    // 客户端绑定参数设置
		    ServiceClient sender = new ServiceClient();
		    sender.setOptions(options);

		    // 设定访问的接口方法

		    OMElement method = fac.createOMElement("ModifyUserName", omNs);// 要调用的接口方法名称
		    
		    OMElement value1 = fac.createOMElement("strUserCode", omNs);// 方法的第一个参数名称
		    value1.addChild(fac.createOMText(value1, userNumber));// 设定参数的值
		    method.addChild(value1);// 方法设置参数
		    
		    OMElement value2 = fac.createOMElement("strUserName", omNs);// 方法的第一个参数名称
		    value2.addChild(fac.createOMText(value2, userName));// 设定参数的值
		    method.addChild(value2);// 方法设置参数

		    OMElement result = sender.sendReceive(method);// 调用接口方法

		   resultStr=result.toString();

			}catch (Exception e) {
		    e.printStackTrace();
			}	
		return parse(resultStr);
	}
	
	/**
	 * 修改用户密码
	 * @Title: ModifyUserPass 
	 * @Description: TODO
	 * @param userNumber
	 * @param password   
	 * @throws
	 */
	public String ModifyUserPass(String userNumber,String password){
		String resultStr="";
	try {
	OMFactory fac = OMAbstractFactory.getOMFactory();

    OMNamespace omNs = fac.createOMNamespace(
            "SFBR_Web_API", "tns");// 命名空间

    // 请求参数设置
    Options options = new Options();
    options.setTo(targetEPR);// 设定webservice地址
    options.setTransportInProtocol(Constants.TRANSPORT_HTTP);// 设定传输协议
    options.setSoapVersionURI(SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI);// 设定SOAP版本soap1.2

    // 客户端绑定参数设置
    ServiceClient sender = new ServiceClient();
    sender.setOptions(options);

    // 设定访问的接口方法

    OMElement method = fac.createOMElement("ModifyUserPass", omNs);// 要调用的接口方法名称
    
    OMElement value1 = fac.createOMElement("strUserCode", omNs);// 方法的第一个参数名称
    value1.addChild(fac.createOMText(value1, userNumber));// 设定参数的值
    method.addChild(value1);// 方法设置参数
    
    OMElement value2 = fac.createOMElement("strUserPass", omNs);// 方法的第一个参数名称
    value2.addChild(fac.createOMText(value2, password));// 设定参数的值
    method.addChild(value2);// 方法设置参数

    OMElement result = sender.sendReceive(method);// 调用接口方法

   resultStr=result.toString();
  
	}catch (Exception e) {
    e.printStackTrace();
	}	
	return parse(resultStr);
	}
	
	
	
	/**
	 * 获取指定设备下的通道信息
	 * @Title: ITPCDeviceChannelInfoGetByIDForJson 
	 * @Description: TODO
	 * @param monNumber
	 * @return   
	 * @throws
	 */
	public  String ITPCDeviceChannelInfoGetByIDForJson(String monNumber){
	
		String resultStr = new String();
		try {
	OMFactory fac = OMAbstractFactory.getOMFactory();

    OMNamespace omNs = fac.createOMNamespace(
            "SFBR_Web_API", "tns");// 命名空间

    // 请求参数设置
    Options options = new Options();
    options.setTo(targetEPR);// 设定webservice地址
    options.setTransportInProtocol(Constants.TRANSPORT_HTTP);// 设定传输协议
    options.setSoapVersionURI(SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI);// 设定SOAP版本soap1.2

    // 客户端绑定参数设置
    ServiceClient sender = new ServiceClient();
    sender.setOptions(options);

    // 设定访问的接口方法

    OMElement method = fac.createOMElement("ITPCBoxDeviceChannelInfoGetByIDForJson", omNs);// 要调用的接口方法名称
    
    OMElement value1 = fac.createOMElement("strDeviceId", omNs);// 方法的第一个参数名称
    value1.addChild(fac.createOMText(value1, monNumber));// 设定参数的值
    method.addChild(value1);// 方法设置参数
    
//    OMElement value2 = fac.createOMElement("CountryName", omNs);// 方法的第一个参数名称
//    value2.addChild(fac.createOMText(value2, "China"));// 设定参数的值
//    method.addChild(value2);// 方法设置参数

    OMElement result = sender.sendReceive(method);// 调用接口方法
//    Iterator iterator = result.getChildrenWithLocalName("AuthorizationResult");
//    System.out.println("guid="+((OMElement)iterator.next()).getText());
    resultStr=result.toString();

	}catch (Exception e) {
    e.printStackTrace();
	}	
		return parse(resultStr);
	}
	
	/**
	 * 获取所有设备信息
	 * @Title: DeviceInfoGetForJson 
	 * @return   
	 * @throws
	 */
	public    String DeviceInfoGetForJson() {
//		
		String resultStr ="[]";
		try {
			OMFactory fac = OMAbstractFactory.getOMFactory();

		    OMNamespace omNs = fac.createOMNamespace(
		            "SFBR_Web_API", "tns");// 命名空间

		    // 请求参数设置
		    Options options = new Options();
		    options.setTo(targetEPR);// 设定webservice地址
		    options.setTransportInProtocol(Constants.TRANSPORT_HTTP);// 设定传输协议
		    options.setSoapVersionURI(SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI);// 设定SOAP版本soap1.2

		    // 客户端绑定参数设置
		    ServiceClient sender = new ServiceClient();
		    sender.setOptions(options);

		    // 设定访问的接口方法
		    OMElement method = fac.createOMElement("DeviceInfoGetForJson", omNs);// 要调用的接口方法名称
	    


		    OMElement result = sender.sendReceive(method);// 调用接口方法

		    resultStr=result.toString();
			}catch (Exception e) {
		    e.printStackTrace();
			
			}
		return parse(resultStr);
	}
	
	/**
	 * 获取所有设备列表
	 * @Title: RegionInfoGetForJson 
	 * @return   
	 * @throws
	 */
	public    String RegionInfoGetForJson() {
		
		String resultStr = new String();
		try {
			OMFactory fac = OMAbstractFactory.getOMFactory();

		    OMNamespace omNs = fac.createOMNamespace(
		            "SFBR_Web_API", "tns");// 命名空间

		    // 请求参数设置
		    Options options = new Options();
		    options.setTo(targetEPR);// 设定webservice地址
		    options.setTransportInProtocol(Constants.TRANSPORT_HTTP);// 设定传输协议
		    options.setSoapVersionURI(SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI);// 设定SOAP版本soap1.2

		    // 客户端绑定参数设置
		    ServiceClient sender = new ServiceClient();
		    sender.setOptions(options);

		    // 设定访问的接口方法
		    OMElement method = fac.createOMElement("RegionInfoGetForJson", omNs);// 要调用的接口方法名称
		    


		    OMElement result = sender.sendReceive(method);// 调用接口方法

		    resultStr=result.toString();
			}catch (Exception e) {
		    e.printStackTrace();
			}
		return parse(resultStr);
	}
	
	
	/**
	 * 获取用户关联的设备
	 * @Title: DeviceInfoGetByUserIdForJson 
	 * @Description: TODO
	 * @param userId
	 * @return   
	 * @throws
	 */
	public    String DeviceInfoGetByUserIdForJson(String userId) {
		
		String resultStr = new String();
		try {
			OMFactory fac = OMAbstractFactory.getOMFactory();

		    OMNamespace omNs = fac.createOMNamespace(
		            "SFBR_Web_API", "tns");// 命名空间

		    // 请求参数设置
		    Options options = new Options();
		    options.setTo(targetEPR);// 设定webservice地址
		    options.setTransportInProtocol(Constants.TRANSPORT_HTTP);// 设定传输协议
		    options.setSoapVersionURI(SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI);// 设定SOAP版本soap1.2

		    // 客户端绑定参数设置
		    ServiceClient sender = new ServiceClient();
		    sender.setOptions(options);

		    // 设定访问的接口方法
		    OMElement method = fac.createOMElement("DeviceInfoGetByUserIdForJson", omNs);// 要调用的接口方法名称
		    OMElement value1 = fac.createOMElement("userId", omNs);// 方法的第一个参数名称
		    value1.addChild(fac.createOMText(value1, userId));// 设定参数的值
		    method.addChild(value1);


		    OMElement result = sender.sendReceive(method);// 调用接口方法

		    resultStr=result.toString();
			}catch (Exception e) {
		    e.printStackTrace();
			}
		return parse(resultStr);
	}
	
	
	
	 public  String parse(String protocolXML) { 
		
         	String json="[]";
	        try {   
	             DocumentBuilderFactory factory = DocumentBuilderFactory   
	                     .newInstance();   
	             DocumentBuilder builder = factory.newDocumentBuilder();   
	             Document doc = builder   
	                     .parse(new InputSource(new StringReader(protocolXML)));   
	  
	             Element root = doc.getDocumentElement();   
	             NodeList books = root.getChildNodes();  
	             Node boo=books.item(0);
	            
	             if(boo.getFirstChild()!=null){
	            	 json=boo.getFirstChild().getNodeValue(); 
	            	
	            
	            
	             }
	             return json;
//	            if (books != null) {   
//	                for (int i = 0; i < books.getLength(); i++) {   
//	                     Node book = books.item(i);   
//	                     System.out.println("节点=" + book.getNodeName() + "\ttext="  
//	                             + book.getFirstChild().getNodeValue());   
//	                 }   
//	             }   
	         } catch (Exception e) {   
	             e.printStackTrace();   
	         }
			return json;  
	        
	        
	        
	     }   
	 
	 
	 /**
	  * 获取所有用户信息
	  * @Title: UserInfoGetForJson 
	  * @Description: TODO
	  * @return   
	  * @throws
	  */
		public    String UserInfoGetForJson() {
		
			String resultStr ="[]";
			try {
				OMFactory fac = OMAbstractFactory.getOMFactory();

			    OMNamespace omNs = fac.createOMNamespace(
			            "SFBR_Web_API", "tns");// 命名空间

			    // 请求参数设置
			    Options options = new Options();
			    options.setTo(targetEPR);// 设定webservice地址
			    options.setTransportInProtocol(Constants.TRANSPORT_HTTP);// 设定传输协议
			    options.setSoapVersionURI(SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI);// 设定SOAP版本soap1.2

			    // 客户端绑定参数设置
			    ServiceClient sender = new ServiceClient();
			    sender.setOptions(options);

			    // 设定访问的接口方法
			    OMElement method = fac.createOMElement("UserInfoGetForJson", omNs);// 要调用的接口方法名称

			    OMElement result = sender.sendReceive(method);// 调用接口方法

			    resultStr=result.toString();
			   
				}catch (Exception e) {
			    e.printStackTrace();
				
				}
			return parse(resultStr);
		}
	  
		public static void main(String[] args) {
			WebService webService=new WebService();
			DecimalFormat dFormat=new DecimalFormat("#.0");
			double ee=12.141111;
			System.out.println(dFormat.format(ee));
//			System.out.println(webService.ModifyUserName("admin","系统管理员"));
//			System.out.println(webService.ModifyUserPass("admin","123456"));
//			System.out.println(webService.UserInfoGetForJson());
			
		}


}
