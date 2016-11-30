/**
 * 
 */
package zn.dao;

import java.util.List;

import zn.entity.MonAlarms;

/**
 * @author hq
 *
 */
public interface MonAlarmsDao {
	public  List<MonAlarms> selectAllMonAlarms();
	public   int selectAlarmsDateIsExist(int monId);
	public    int  addMonAlarms(MonAlarms monAlarms);
	public int changeMonAlarms(MonAlarms monAlarms);
	public int deleteMonAlarmsById(int alarmsId);
	public int deleteMonAlarmsByMonId(int monId);
}
