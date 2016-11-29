/**
 * 
 */
package zn.service;



import zn.until.NoteResult;

/**
 * @author hq
 *
 */

public interface MonAlarmsService {
	
	public NoteResult selectAllMonAlarms();
	
	public NoteResult deleteMonAlarms(Integer monId);

}
