/**
 * 
 */
package zn.entity;

/**
 * @author hq
 *
 */
public class Monitor {
	private int monId;                              //�豸id
	private String monAlias;						//�豸����
	private String monInstall;						//�豸��װλ��
	private String monIP;							//�豸IP
	private String monModel;						//�豸�ͺ�
	private String monName;							//�豸����
	private String monPlace;						//�豸������
	private int monPlaceLevel;					//�豸�����ؼ���
	private String monType;							//�豸����
	private String monNumber;                       //�豸���
	private int monRoute;                           //�豸·��
                    
	
	public int getMonRoute() {
		return monRoute;
	}
	public void setMonRoute(int monRoute) {
		this.monRoute = monRoute;
	}
	public String getMonNumber() {
		return monNumber;
	}
	public void setMonNumber(String monNumber) {
		this.monNumber = monNumber;
	}
	public int getMonId() {
		return monId;
	}
	public void setMonId(int monId) {
		this.monId = monId;
	}
	public String getMonAlias() {
		return monAlias;
	}
	public void setMonAlias(String monAlias) {
		this.monAlias = monAlias;
	}
	public String getMonInstall() {
		return monInstall;
	}
	public void setMonInstall(String monInstall) {
		this.monInstall = monInstall;
	}
	public String getMonIP() {
		return monIP;
	}
	public void setMonIP(String monIP) {
		this.monIP = monIP;
	}
	public String getMonModel() {
		return monModel;
	}
	public void setMonModel(String monModel) {
		this.monModel = monModel;
	}
	public String getMonName() {
		return monName;
	}
	public void setMonName(String monName) {
		this.monName = monName;
	}
	public String getMonPlace() {
		return monPlace;
	}
	public void setMonPlace(String monPlace) {
		this.monPlace = monPlace;
	}
	public String getMonType() {
		return monType;
	}
	public void setMonType(String monType) {
		this.monType = monType;
	}
	public int getMonPlaceLevel() {
		return monPlaceLevel;
	}
	public void setMonPlaceLevel(int monPlaceLevel) {
		this.monPlaceLevel = monPlaceLevel;
	}
	@Override
	public String toString() {
		return "Monitor [monId=" + monId + ", monAlias=" + monAlias + ", monInstall=" + monInstall + ", monIP=" + monIP
				+ ", monModel=" + monModel + ", monName=" + monName + ", monPlace=" + monPlace + ", monPlaceLevel="
				+ monPlaceLevel + ", monType=" + monType + ", monNumber=" + monNumber + ", monRoute=" + monRoute + "]";
	}

	

	
	

}
