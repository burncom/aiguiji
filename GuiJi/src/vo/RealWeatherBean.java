package vo;

import java.io.Serializable;

public class RealWeatherBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//主键
	private int  weatherId;
	//用户logo
	private String userLogo;
	//用户主键
	private int userId;
	//用户昵称
	private String userName;
	//天气气象
	private String wscene;
	//气温
	private String wtemp;
	//风向与风力
	private String wwind;
	//时间
	private String time;
	//地区
	private String place;
	//赞成数量
	private int agreeAmount;
	//不赞成数量
	private int disagreeAmount;
	
	
	public RealWeatherBean(){
		
	}

	public RealWeatherBean(int weatherId, String userLogo, int userId,String userName,
			String wscene, String wtemp, String wwind, String time,
			String place, int agreeAmount, int disagreeAmount) {
		super();
		this.weatherId = weatherId;
		this.userLogo = userLogo;
		this.userId=userId;
		this.userName = userName;
		this.wscene = wscene;
		this.wtemp = wtemp;
		this.wwind = wwind;
		this.time = time;
		this.place = place;
		this.agreeAmount = agreeAmount;
		this.disagreeAmount = disagreeAmount;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getWeatherId() {
		return weatherId;
	}


	public void setWeatherId(int weatherId) {
		this.weatherId = weatherId;
	}


	public String getUserLogo() {
		return userLogo;
	}

	public void setUserLogo(String userLogo) {
		this.userLogo = userLogo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getWscene() {
		return wscene;
	}

	public void setWscene(String wscene) {
		this.wscene = wscene;
	}

	public String getWtemp() {
		return wtemp;
	}

	public void setWtemp(String wtemp) {
		this.wtemp = wtemp;
	}

	public String getWwind() {
		return wwind;
	}

	public void setWwind(String wwind) {
		this.wwind = wwind;
	}

	public String getPlace() {
		return place;
	}


	public void setPlace(String place) {
		this.place = place;
	}


	public int getAgreeAmount() {
		return agreeAmount;
	}


	public void setAgreeAmount(int agreeAmount) {
		this.agreeAmount = agreeAmount;
	}


	public int getDisagreeAmount() {
		return disagreeAmount;
	}


	public void setDisagreeAmount(int disagreeAmount) {
		this.disagreeAmount = disagreeAmount;
	}
	
}
