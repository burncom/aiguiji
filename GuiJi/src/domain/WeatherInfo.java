package domain;

import java.io.Serializable;

public class WeatherInfo implements Serializable {
	/**
	 * ������Ϣ��
	 */
	private static final long serialVersionUID = 1L;
	//����������
	private	int weather_id;
	//��������
	private String weatherscene;
	//����
	private String temperature;
	//����
	private String wind;
	//�޳����ͣ�1�����޳ɣ�2���޳ɣ�
	private int approve_type;
	//�޳�����
	private int approve_amount;
	//���޳�����
	private int disapprove_amount;
	//��������ʵʱʱ�䣨ʱ�����ӣ��룩
	private int realtime;
	//��������������
	private int datetime;
	//����
	private String coordinate;
	//ʡ��
	private String province;
	//����
	private String city;
	//�أ�����
	private String district;
	//���µ��ֶ���
	private String other;
	//��Ӧ�û�Ԫ���ݱ�����
	private UserInfo userInfo;
	
	public WeatherInfo(){
		
	}

	public WeatherInfo(int weather_id,String weatherscene,
			String temperature, String wind, int approve_type,
			int approve_amount, int disapprove_amount, int realtime,
			int datetime, String coordinate, String province, String city,
			String district, String other, UserInfo userInfo) {
		super();
		this.weather_id = weather_id;
		this.weatherscene = weatherscene;
		this.temperature = temperature;
		this.wind = wind;
		this.approve_type = approve_type;
		this.approve_amount = approve_amount;
		this.disapprove_amount = disapprove_amount;
		this.realtime = realtime;
		this.datetime = datetime;
		this.coordinate = coordinate;
		this.province = province;
		this.city = city;
		this.district = district;
		this.other = other;
		this.userInfo = userInfo;
	}
	
	public String getWeatherscene() {
		return weatherscene;
	}

	public void setWeatherscene(String weatherscene) {
		this.weatherscene = weatherscene;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getWind() {
		return wind;
	}

	public void setWind(String wind) {
		this.wind = wind;
	}

	public int getWeather_id() {
		return weather_id;
	}

	public void setWeather_id(int weather_id) {
		this.weather_id = weather_id;
	}

	public int getApprove_type() {
		return approve_type;
	}

	public void setApprove_type(int approve_type) {
		this.approve_type = approve_type;
	}

	public int getApprove_amount() {
		return approve_amount;
	}

	public void setApprove_amount(int approve_amount) {
		this.approve_amount = approve_amount;
	}

	public int getDisapprove_amount() {
		return disapprove_amount;
	}

	public void setDisapprove_amount(int disapprove_amount) {
		this.disapprove_amount = disapprove_amount;
	}
	
	public int getRealtime() {
		return realtime;
	}

	public void setRealtime(int realtime) {
		this.realtime = realtime;
	}

	public int getDatetime() {
		return datetime;
	}

	public void setDatetime(int datetime) {
		this.datetime = datetime;
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
	
}
