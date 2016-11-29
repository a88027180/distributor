/**
 * 
 */
package zn.until;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author hq
 *
 */
public class UdpClientSocket {
	 private byte[] buffer = new byte[1024];    
	    
	    private DatagramSocket ds = null;    
	    
	    /**  
	     * ���캯��������UDP�ͻ���  
	     * @throws Exception  
	     */    
	    public UdpClientSocket() throws Exception {    
	        ds = new DatagramSocket();    
	    }    
	        
	    /**  
	     * ���ó�ʱʱ�䣬�÷���������bind����֮��ʹ��.  
	     * @param timeout ��ʱʱ��  
	     * @throws Exception  
	     */    
	    public final void setSoTimeout(final int timeout) throws Exception {    
	        ds.setSoTimeout(timeout);    
	    }    
	    
	    /**  
	     * ��ó�ʱʱ��.  
	     * @return ���س�ʱʱ��  
	     * @throws Exception  
	     */    
	    public final int getSoTimeout() throws Exception {    
	        return ds.getSoTimeout();    
	    }    
	    
	    public final DatagramSocket getSocket() {    
	        return ds;    
	    }    
	    
	    /**  
	     * ��ָ���ķ���˷���������Ϣ.  
	     * @param host ������������ַ  
	     * @param port ����˶˿�  
	     * @param bytes ���͵�������Ϣ  
	     * @return ���ع��������ݱ�  
	     * @throws IOException  
	     */    
	    public final DatagramPacket send(final String host, final int port,    
	            final byte[] bytes) throws IOException {    
	        DatagramPacket dp = new DatagramPacket(bytes, bytes.length, InetAddress    
	                .getByName(host), port);    
	        ds.send(dp);    
	        return dp;    
	    }    
	    
	    /**  
	     * ���մ�ָ���ķ���˷��ص�����.  
	     * @param lhost ���������  
	     * @param lport ����˶˿�  
	     * @return ���ش�ָ���ķ���˷��ص�����.  
	     * @throws Exception  
	     */    
	    public final String receive(final String lhost, final int lport)    
	            throws Exception {    
	        DatagramPacket dp = new DatagramPacket(buffer, buffer.length);    
	        ds.receive(dp);    
	        String info = new String(dp.getData(), 0, dp.getLength());    
	        return info;    
	    }    
	    
	    /**  
	     * �ر�udp����.  
	     */    
	    public final void close() {    
	        try {    
	            ds.close();    
	        } catch (Exception ex) {    
	            ex.printStackTrace();    
	        }    
	    }    
	    
	    /**  
	     * ���Կͻ��˷����ͽ��ջ�Ӧ��Ϣ�ķ���.  
	     * @param args  
	     * @throws Exception  
	     */    
	    public static void main(String[] args) throws Exception {    
	        UdpClientSocket client = new UdpClientSocket();    
	        String serverHost = "192.168.2.8";    
	        int serverPort = 8080;    
	        client.send(serverHost, serverPort, ("���!").getBytes()); 
//	        client.setSoTimeout(2000);
//	        client.send(serverHost, serverPort, ("��ܺ�!").getBytes());
//	        String info = client.receive(serverHost, serverPort);    
//	        System.out.println("����˻�Ӧ���ݣ�" + info);    
	    }    

}
