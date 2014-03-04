package domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserInfo implements Serializable {
	/**
	 * 用户信息表
	 */
	private static final long serialVersionUID = 1L;
	//用户信息主键
	private int user_id;
	//邮箱账号
	private String email;
	//昵称
	private String user_name;
	//密码
	private String user_password;
	//总共信息条数
	private int	msg_amount;
	//粉丝数量
	private int	fans_amount;
	//关注者数量
	private int follow_amount;
	//生日
	private int birthday;
	//性别（1，男；2，女）
	private int gender;
	//头像图片存储路径
	private String avatar;
	//坐标（经度，纬度）
	private String coordinate;
	//家乡
	private	String hometown;
	//省份
	private String province;
	//城市名
	private String city;
	//区、县名
	private String district;
	//余下数据字段
	private String other;
	//现在所在地坐标
	private String nowcoordinate;
	//现在所在地省份
	private String nowprovince;
	//现在所在地城市
	private String nowcity;
	//现在所在地区域
	private String nowdistrict;
	//现在所在地余下的地名字段
	private String nowother;
	//用户感兴趣地点分类（类别1，类别2，类别3，。。。）
	private String category;
	//用户心情指数
	private int mood;
	//用户信任值
	private String trust_value;
	//对应天气信息
	private Set<WeatherInfo> weatherInfo=new HashSet<WeatherInfo>();
	//对应信息元数据表
	private Set<MessageInfo> messageInfo=new HashSet<MessageInfo>();
	//对应用户用户关系表
	private Set<UserUserRelationship> userUserRelationship=new HashSet<UserUserRelationship>();
	//日志信息表
	private Set<LogInfo> logInfo=new HashSet<LogInfo>();
	//用户信息索引
	private Set<UserMsgIndex> userMsgIndex=new HashSet<UserMsgIndex>();
	//用户相册
	private Set<PhotosInfo> photosInfo=new HashSet<PhotosInfo>();
	//无参数的构造器
	public UserInfo (){
		
	}
	//初始化全部属性的构造器
	public UserInfo(int user_id, String email, String user_name,
			String user_password, int msg_amount, int fans_amount,
			int follow_amount, int birthday, int gender, String avatar,
			String coordinate, String hometown, String province, String city,
			String district, String other, String nowcoordinate,
			String nowprovince, String nowcity, String nowdistrict,
			String nowother, String category, int mood, String trust_value,
			Set<WeatherInfo> weatherInfo, Set<MessageInfo> messageInfo,
			Set<UserUserRelationship> userUserRelationship,
			Set<LogInfo> logInfo, Set<UserMsgIndex> userMsgIndex,Set<PhotosInfo> photosInfo) {
		super();
		this.user_id = user_id;
		this.email = email;
		this.user_name = user_name;
		this.user_password = user_password;
		this.msg_amount = msg_amount;
		this.fans_amount = fans_amount;
		this.follow_amount = follow_amount;
		this.birthday = birthday;
		this.gender = gender;
		this.avatar = avatar;
		this.coordinate = coordinate;
		this.hometown = hometown;
		this.province = province;
		this.city = city;
		this.district = district;
		this.other = other;
		this.nowcoordinate = nowcoordinate;
		this.nowprovince = nowprovince;
		this.nowcity = nowcity;
		this.nowdistrict = nowdistrict;
		this.nowother = nowother;
		this.category = category;
		this.mood = mood;
		this.trust_value = trust_value;
		this.weatherInfo = weatherInfo;
		this.messageInfo = messageInfo;
		this.userUserRelationship = userUserRelationship;
		this.logInfo = logInfo;
		this.userMsgIndex = userMsgIndex;
		this.photosInfo=photosInfo;
	}
	//以下为各属性的set和get方法
	
	
	public int getBirthday() {
		return birthday;
	}
	
	public Set<PhotosInfo> getPhotosInfo() {
		return photosInfo;
	}
	public void setPhotosInfo(Set<PhotosInfo> photosInfo) {
		this.photosInfo = photosInfo;
	}
	public String getHometown() {
		return hometown;
	}
	public void setHometown(String hometown) {
		this.hometown = hometown;
	}
	public void setBirthday(int birthday) {
		this.birthday = birthday;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public int getMsg_amount() {
		return msg_amount;
	}

	public void setMsg_amount(int msg_amount) {
		this.msg_amount = msg_amount;
	}

	public int getFans_amount() {
		return fans_amount;
	}

	public void setFans_amount(int fans_amount) {
		this.fans_amount = fans_amount;
	}

	public int getFollow_amount() {
		return follow_amount;
	}

	public void setFollow_amount(int follow_amount) {
		this.follow_amount = follow_amount;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
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
	
	public String getNowcoordinate() {
		return nowcoordinate;
	}
	public void setNowcoordinate(String nowcoordinate) {
		this.nowcoordinate = nowcoordinate;
	}
	public String getNowprovince() {
		return nowprovince;
	}
	public void setNowprovince(String nowprovince) {
		this.nowprovince = nowprovince;
	}
	public String getNowcity() {
		return nowcity;
	}
	public void setNowcity(String nowcity) {
		this.nowcity = nowcity;
	}
	public String getNowdistrict() {
		return nowdistrict;
	}
	public void setNowdistrict(String nowdistrict) {
		this.nowdistrict = nowdistrict;
	}
	public String getNowother() {
		return nowother;
	}
	public void setNowother(String nowother) {
		this.nowother = nowother;
	}
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getMood() {
		return mood;
	}

	public void setMood(int mood) {
		this.mood = mood;
	}

	public String getTrust_value() {
		return trust_value;
	}

	public void setTrust_value(String trust_value) {
		this.trust_value = trust_value;
	}

	public Set<WeatherInfo> getWeatherInfo() {
		return weatherInfo;
	}

	public void setWeatherInfo(Set<WeatherInfo> weatherInfo) {
		this.weatherInfo = weatherInfo;
	}

	public Set<MessageInfo> getMessageInfo() {
		return messageInfo;
	}

	public void setMessageInfo(Set<MessageInfo> messageInfo) {
		this.messageInfo = messageInfo;
	}

	public Set<UserUserRelationship> getUserUserRelationship() {
		return userUserRelationship;
	}

	public void setUserUserRelationship(
			Set<UserUserRelationship> userUserRelationship) {
		this.userUserRelationship = userUserRelationship;
	}

	public Set<LogInfo> getLogInfo() {
		return logInfo;
	}

	public void setLogInfo(Set<LogInfo> logInfo) {
		this.logInfo = logInfo;
	}

	public Set<UserMsgIndex> getUserMsgIndex() {
		return userMsgIndex;
	}

	public void setUserMsgIndex(Set<UserMsgIndex> userMsgIndex) {
		this.userMsgIndex = userMsgIndex;
	}
}
