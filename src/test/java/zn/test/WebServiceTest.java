/**
 * 
 */
package zn.test;

import java.rmi.RemoteException;

import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

/**
 * @author hq
 *
 */
public class WebServiceTest {
	public String invokeRemoteFuc() {
		String endpoint = "http://IP:Port/WebAPI/SFBRWebS.asmx ";
		String result = "no result!";
		Service service = new Service();
		Call call;
		Object[] object = new Object[1];
		object[0] = "Dear I miss you";//Object是用来存储方法的参数
		try {
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpoint);// 远程调用路径
			call.setOperationName("DeviceInfoGetForJson");// 调用的方法名

			// 设置参数名:
//			call.addParameter("str1", // 参数名
//					XMLType.XSD_STRING,// 参数类型:String
//					ParameterMode.IN);// 参数模式：'IN' or 'OUT'

			// 设置返回值类型：
			call.setReturnType(XMLType.XSD_STRING);// 返回值类型：String			

			result = (String) call.invoke(object);// 远程调用
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		WebServiceTest t = new WebServiceTest();
		String result = t.invokeRemoteFuc();
		System.out.println(result);
	}
}
			

	
