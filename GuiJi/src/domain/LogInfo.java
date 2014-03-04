package domain;

import java.io.Serializable;

public class LogInfo implements Serializable {
	
	/**
	 * 日志记录表
	 */
	private static final long serialVersionUID = 1L;
	//日志信息表主键
	private int id;
	//用户操作类型：1，原创；2，推荐；3，评论；4，想参加；5，关注；6，加照片；7，发起活动；8，登录；9，退出；10，发表心情指数；11，发表天气；12，天气赞成；13，天气不赞成,14，注册。
	private int operate;
	//心情指数
	private int mood;
	//140个字节，用户文字内容增加
	private String content;
	//坐标，（经度，纬度）
	private String coordinate;
	//省份
	private String province;
	//城市
	private String city;
	//县，区名
	private String district;
	//余下的字段名
	private String other;
	//操作时分秒时间
	private int oprealtime;
	//操作日期时间
	private int	opdatetime;
	//对应用户信息表主键
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
