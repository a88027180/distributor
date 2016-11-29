/**
 * 
 */
package zn.listener;

import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.support.WebApplicationContextUtils;

import zn.service.AnalysisService;
import zn.until.UdpServerSocket;

/**
 * @author hq
 *
 */
public class AnalysisInfoListener implements ServletContextListener{
	
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		AnalysisService analysisService=WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext()).getBean(AnalysisService.class);
		

	
		UdpServerSocket udpServerSocket = null;
		 try {
			 
		
			InputStream inStream =AnalysisInfoListener.class.getClassLoader() .getResourceAsStream( "./udp.properties" );  
			System.out.println(inStream);
			Properties prop = new Properties();    
			prop.load(inStream);    
				int localityPort = Integer.valueOf(prop.getProperty("localityPort"));
			udpServerSocket = new UdpServerSocket(InetAddress.getLocalHost().getHostAddress(), localityPort);
			if(udpServerSocket!=null){
			analysisService.analysisMon( udpServerSocket);
			}
		 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
