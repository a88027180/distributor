/**
 * 
 */
package zn.test;

import java.rmi.RemoteException;
import java.util.Iterator;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.soap.SOAP11Constants;
import org.apache.axiom.soap.SOAP12Constants;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.rpc.client.RPCServiceClient;



/**
 * @author hq
 *
 */
public class WebServiceTest {
	
    private static EndpointReference targetEPR = new EndpointReference(
            "http://www.webservicex.net/globalweather.asmx");

	public void  te(){
		try {
	OMFactory fac = OMAbstractFactory.getOMFactory();

    OMNamespace omNs = fac.createOMNamespace(
            "http://www.webserviceX.NET", "tns");// 命名空间

    // 请求参数设置
    Options options = new Options();
    options.setTo(targetEPR);// 设定webservice地址
    options.setTransportInProtocol(Constants.TRANSPORT_HTTP);// 设定传输协议
    options.setSoapVersionURI(SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI);// 设定SOAP版本soap1.2

    // 客户端绑定参数设置
    ServiceClient sender = new ServiceClient();
    sender.setOptions(options);

    // 设定访问的接口方法

    OMElement method = fac.createOMElement("GetCitiesByCountry", omNs);// 要调用的接口方法名称
    
//    OMElement value1 = fac.createOMElement("CityName", omNs);// 方法的第一个参数名称
//    value1.addChild(fac.createOMText(value1, "hangzhou"));// 设定参数的值
//    method.addChild(value1);// 方法设置参数
    
    OMElement value2 = fac.createOMElement("CountryName", omNs);// 方法的第一个参数名称
    value2.addChild(fac.createOMText(value2, "China"));// 设定参数的值
    method.addChild(value2);// 方法设置参数

    OMElement result = sender.sendReceive(method);// 调用接口方法
//    Iterator iterator = result.getChildrenWithLocalName("AuthorizationResult");
//    System.out.println("guid="+((OMElement)iterator.next()).getText());
    System.out.println(result.toString());
	}catch (Exception e) {
    e.printStackTrace();
	}
	}
	
 
	
	
	public String invokeRemoteFuc() {
//		String endpoint = "http://www.webservicex.net/globalweather.asmx?WSDL";
////		String endpoint = "http://192.168.1.100/WebAPI/SFBRWebS.asmx";
		String result = "no result!";
//		Service service = new Service();
//		Call call;
//		Object[] object = new Object[1];
//		object[0] = "Dear I miss you";//Object是用来存储方法的参数
//		try {
//			call = (Call) service.createCall();
//			call.setTargetEndpointAddress(endpoint);// 远程调用路径
//			call.setOperationName("GetWeather ");// 调用的方法名
//			
//			// 设置参数名:
//			call.addParameter("DateSet", // 参数名
//					XMLType.XSD_STRING,// 参数类型:String
//					ParameterMode.IN);// 参数模式：'IN' or 'OUT'
//  
//			// 设置返回值类型：
////			call.setReturnType();// 返回值类型：String	
//			
//		
//			call.setReturnType(XMLType.XSD_STRING);
//			result = (String) call.invoke(object);// 远程调用
//			System.out.println("aaa");
//		} catch (ServiceException e) {
//			e.printStackTrace();
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
		return result;
	}

	public static void main(String[] args) {
		WebServiceTest t = new WebServiceTest();
//		 t.testRPC();
		 t.te();
		
		
	}
}
			
  


	
