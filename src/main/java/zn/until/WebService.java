/**
 * 
 */
package zn.until;

import java.io.StringReader;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * @author hq
 *
 */
public class WebService {
	private static final String endpoint="http://IP:Port/WebAPI/SFBRWebS.asmx ";
	
	
	
	public  String ITPCDeviceChannelInfoGetByIDForJson(String monNumber){
		String result = new String();
//		Service service = new Service();
//		Call call;
//		Object[] object = new Object[1];
//		object[0] = "Dear I miss you";//Object是用来存储方法的参数
//		try {
//			call = (Call) service.createCall();
//			call.setTargetEndpointAddress(endpoint);// 远程调用路径
//			call.setOperationName("ITPCDeviceChannelInfoGetByIDForJson");// 调用的方法名
//
//			// 设置参数名:
//			call.addParameter(monNumber, // 参数名
//					XMLType.XSD_STRING,// 参数类型:String
//					ParameterMode.IN);// 参数模式：'IN' or 'OUT'
////			 设置返回值类型：
//			call.setReturnType(XMLType.XSD_STRING);// 返回值类型：String			
//
//			result = (String) call.invoke(object);// 远程调用
//		} catch (ServiceException e) {
//			e.printStackTrace();
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
		return result;
	}
	
	/**
	 * 获取所有设备信息
	 * @Title: DeviceInfoGetForJson 
	 * @return   
	 * @throws
	 */
	public    String DeviceInfoGetForJson() {
//		
		String result = new String();
//		Service service = new Service();
//		Call call;
//		Object[] object = new Object[1];
//		object[0] = "Dear I miss you";//Object是用来存储方法的参数
//		try {
//			call = (Call) service.createCall();
//			call.setTargetEndpointAddress(endpoint);// 远程调用路径
//			call.setOperationName("DeviceInfoGetForJson");// 调用的方法名
//
//			// 设置参数名:
////			call.addParameter("str1", // 参数名
////					XMLType.XSD_STRING,// 参数类型:String
////					ParameterMode.IN);// 参数模式：'IN' or 'OUT'
//			// 设置返回值类型：
//			call.setReturnType(XMLType.XSD_STRING);// 返回值类型：String			
//
//			result = (String) call.invoke(object);// 远程调用
//		} catch (ServiceException e) {
//			e.printStackTrace();
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
		return result;
	}
	
	/**
	 * 获取所有设备列表
	 * @Title: RegionInfoGetForJson 
	 * @return   
	 * @throws
	 */
	public    String RegionInfoGetForJson() {
		
		String result = new String();
//		说
		return result;
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
	            
	             System.out.println(boo.getNodeValue());
	             json=boo.getNodeValue();
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
	  


}
