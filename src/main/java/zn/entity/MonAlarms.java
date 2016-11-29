/**
 * 
 */
package zn.entity;

/**
 * @author hq
 *
 */
public class MonAlarms {
	private int monId;
	
	private String monAlarmsType;
	private String monAlarmsTM;
	private String monAlarmsInfo;
	public int getMonId() {
		return monId;
	}
	public void setMonId(int monId) {
		this.monId = monId;
	}

	public String getMonAlarmsType() {
		return monAlarmsType;
	}
	public void setMonAlarmsType(String monAlarmsType) {
		this.monAlarmsType = monAlarmsType;
	}
	public String getMonAlarmsTM() {
		return monAlarmsTM;
	}
	public void setMonAlarmsTM(String monAlarmsTM) {
		this.monAlarmsTM = monAlarmsTM;
	}
	public String getMonAlarmsInfo() {
		return monAlarmsInfo;
	}
	public void setMonAlarmsInfo(String monAlarmsInfo) {
		this.monAlarmsInfo = monAlarmsInfo;
	}
	@Override
	public String toString() {
		return "Monalarms [monId=" + monId  + ", monAlarmsType=" + monAlarmsType + ", monAlarmsTM="
				+ monAlarmsTM + ", monAlarmsInfo=" + monAlarmsInfo + "]";
	}
	
}
