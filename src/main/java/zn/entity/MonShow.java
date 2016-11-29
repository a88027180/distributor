/**
 * 
 */
package zn.entity;

import java.util.List;

/**
 * @author hq
 *
 */
public class MonShow {
	private int monId;
	private String monName;
	private String monAlias;						//�豸����
	private String monInstall;						//�豸��װλ��
	private String monIP;							//�豸IP
	private String monModel;						//�豸�ͺ�
	private String monPlace;						//�豸������
	private int monPlaceLevel;					//�豸�����ؼ���
	private String monType;							//�豸����
	private String monNumber;                       //�豸���
	private int monRoute;                           //�豸·��
	private String allAV;
	private String allBV;
	private String allCV;
	private String humidity;
	private String allAA;
	private String allBA;
	private String allCA;
	private List<MonSon> list;

	public int getMonId() {
		return monId;
	}
	public void setMonId(int monId) {
		this.monId = monId;
	}
	public String getMonName() {
		return monName;
	}
	public void setMonName(String monName) {
		this.monName = monName;
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
	public String getMonPlace() {
		return monPlace;
	}
	public void setMonPlace(String monPlace) {
		this.monPlace = monPlace;
	}
	public int getMonPlaceLevel() {
		return monPlaceLevel;
	}
	public void setMonPlaceLevel(int monPlaceLevel) {
		this.monPlaceLevel = monPlaceLevel;
	}
	public String getMonType() {
		return monType;
	}
	public void setMonType(String monType) {
		this.monType = monType;
	}
	public String getMonNumber() {
		return monNumber;
	}
	public void setMonNumber(String monNumber) {
		this.monNumber = monNumber;
	}
	public int getMonRoute() {
		return monRoute;
	}
	public void setMonRoute(int monRoute) {
		this.monRoute = monRoute;
	}
	public String getAllAV() {
		return allAV;
	}
	public void setAllAV(String allAV) {
		this.allAV = allAV;
	}
	public String getAllBV() {
		return allBV;
	}
	public void setAllBV(String allBV) {
		this.allBV = allBV;
	}
	public String getAllCV() {
		return allCV;
	}
	public void setAllCV(String allCV) {
		this.allCV = allCV;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getAllAA() {
		return allAA;
	}
	public void setAllAA(String allAA) {
		this.allAA = allAA;
	}
	public String getAllBA() {
		return allBA;
	}
	public void setAllBA(String allBA) {
		this.allBA = allBA;
	}
	public String getAllCA() {
		return allCA;
	}
	public void setAllCA(String allCA) {
		this.allCA = allCA;
	}
	public List<MonSon> getList() {
		return list;
	}
	public void setList(List<MonSon> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "MonShow [monId=" + monId + ", monName=" + monName + ", monAlias=" + monAlias + ", monInstall="
				+ monInstall + ", monIP=" + monIP + ", monModel=" + monModel + ", monPlace=" + monPlace
				+ ", monPlaceLevel=" + monPlaceLevel + ", monType=" + monType + ", monNumber=" + monNumber
				+ ", monRoute=" + monRoute + ", allAV=" + allAV + ", allBV=" + allBV + ", allCV=" + allCV
				+ ", humidity=" + humidity + ", allAA=" + allAA + ", allBA=" + allBA + ", allCA=" + allCA + ", list="
				+ list + "]";
	}
	
	
	

}
