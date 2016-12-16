/**
 * 
 */
package zn.entity;

/**
 * @author hq
 *
 */
public class Alarms {
	private int alarmsId;
	private int monId;
	private String monName;
	
	public String getMonName() {
		return monName;
	}
	public void setMonName(String monName) {
		this.monName = monName;
	}
	private String monPlace;
	
	
	public int getAlarmsId() {
		return alarmsId;
	}
	public void setAlarmsId(int alarmsId) {
		this.alarmsId = alarmsId;
	}
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
		return "Alarms [alarmsId=" + alarmsId + ", monId=" + monId + ", monName=" + monName + ", monPlace=" + monPlace
				+ ", monAlarmsType=" + monAlarmsType + ", monAlarmsTM=" + monAlarmsTM + ", monAlarmsInfo="
				+ monAlarmsInfo + "]";
	}

	

}
