/**
 * 
 */
package zn.service;

import zn.until.NoteResult;

/**
 * @author hq
 *
 */
public interface MonListService {
	public NoteResult selectMonListByList(String superiorListId);
	public NoteResult selectMonListByListLevel(Integer listLevel);
	

}
