/**
 * 
 */
package zn.service;

import zn.until.NoteResult;

/**
 * @author hq
 *
 */

public interface MonitorService {
	public  NoteResult  addMon(String monStr);
	public  NoteResult  findAllMon();
	public  NoteResult  deleteMon(Integer monId);
	public  NoteResult  changeMon(String monStr);
	
	public NoteResult    monAddUser(String jsonStr);
	public NoteResult    seleteMonByUserId(Integer userId);
	public NoteResult findMonByState(Integer monState);
}
