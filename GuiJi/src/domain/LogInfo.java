package domain;

import java.io.Serializable;

public class LogInfo implements Serializable {
	
	/**
	 * ��־��¼��
	 */
	private static final long serialVersionUID = 1L;
	//��־��Ϣ������
	private int id;
	//�û��������ͣ�1��ԭ����2���Ƽ���3�����ۣ�4����μӣ�5����ע��6������Ƭ��7��������8����¼��9���˳���10����������ָ����11������������12�������޳ɣ�13���������޳�,14��ע�ᡣ
	private int operate;
	//����ָ��
	private int mood;
	//140���ֽڣ��û�������������
	private String content;
	//���꣬�����ȣ�γ�ȣ�
	private String coordinate;
	//ʡ��
	private String province;
	//����
	private String city;
	//�أ�����
	private String district;
	//���µ��ֶ���
	private String other;
	//����ʱ����ʱ��
	private int oprealtime;
	//��������ʱ��
	private int	opdatetime;
	//��Ӧ�û���Ϣ������
	private UserInfo userInfo;
	public LogInfo(){
		
	}
	
	public LogInfo(int id, int operate, int mood,String content, String coordinate,
			String province, String city, String district, String other,
			int oprealtime, int opdatetime, UserInfo userInfo) {
		super();
		this.id = id;
		this.operate = operate;
		this.mood = mood;
		this.content=content;
		this.coordinate = coordinate;
		this.province = province;
		this.city = city;
		this.district = district;
		this.other = other;
		this.oprealtime = oprealtime;
		this.opdatetime = opdatetime;
		this.userInfo = userInfo;
	}

	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOperate() {
		return operate;
	}
	public void setOperate(int operate) {
		this.operate = operate;
	}
	public int getMood() {
		return mood;
	}
	public void setMood(int mood) {
		this.mood = mood;
	}
	public String getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public int getOprealtime() {
		return oprealtime;
	}

	public void setOprealtime(int oprealtime) {
		this.oprealtime = oprealtime;
	}

	public int getOpdatetime() {
		return opdatetime;
	}

	public void setOpdatetime(int opdatetime) {
		this.opdatetime = opdatetime;
	}
	
}
