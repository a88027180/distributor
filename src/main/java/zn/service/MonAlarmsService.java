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
	
	public NoteResult deleteMonAlarmsByMonId(Integer monId);
	public NoteResult deleteMonAlarmsById(Integer alarmsId);
	public NoteResult selectMonAlarmsById(Integer monId);

}
