/**
 * 
 */
package zn.service;

import zn.entity.MonAlarms;
import zn.until.UdpServerSocket;

/**
 * @author hq
 *
 */
public interface AnalysisService {
	public void analysisMon(UdpServerSocket udpServerSocket);
	public void analysisHex(byte[] hex,String monNumber);
	public MonAlarms  analysisWarningHex(byte[] mes);
}
