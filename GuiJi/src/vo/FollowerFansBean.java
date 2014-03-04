package vo;

import java.io.Serializable;

public class FollowerFansBean implements Serializable {
	/**
	 * 处理 关注者，粉丝 
	 */
	private static final long serialVersionUID = 1L;
	//当前用户A，和用户B的关系状态
	//1,A,B没有任何关系；2，B为A的粉丝,也就是B关注A；3，A为B的粉丝，也就是A关注B；4，A，B互相关注
	private int status;
	private int userId;
	//1，当用户没有发信息或者活动时；2，用户最近为信息时;3，用户最近为活动时。
	private int type;
	//用户的logo
	private String userLogo;
	//用户名字
	private String userName;
	//若为信息，则为发布时候的时间；
	private String time;
	//若为信息，则为发布时候的地点；若为活动，则为活动的地点
	private String place;
	//若为活动，则为活动的起止时间 
	private String start_time;
	//若为活动，则为活动的结束时间
	private String end_time;
	//心情指数
	private int mood;
	private String msg_content;
	private String picture;
	
	public FollowerFansBean() {
	}
	
	//当最近没有发表信息或者评论时
	public FollowerFansBean(int type,int status, int userId, String userLogo,
			String userName, int mood) {
		super();
		this.type=type;
		this.status = status;
		this.userId = userId;
		this.userLogo = userLogo;
		this.userName = userName;
		this.mood = mood;
	}

	//为活动
	public FollowerFansBean(int type,int status, int userId, String userLogo,
			String userName, String place, String start_time, String end_time,
			int mood, String msg_content, String picture) {
		super();
		this.type=type;
		this.status = status;
		this.userId = userId;
		this.userLogo = userLogo;
		this.userName = userName;
		this.place = place;
		this.start_time = start_time;
		this.end_time = end_time;
		this.mood = mood;
		this.msg_content = msg_content;
		this.picture = picture;
	}
	//为信息
	public FollowerFansBean(int type,int status, int userId, String userLogo,
			String userName, String time, String place, int mood,
			String msg_content, String picture) {
		super();
		this.type=type;
		this.status = status;
		this.userId = userId;
		this.userLogo = userLogo;
		this.userName = userName;
		this.time = time;
		this.place = place;
		this.mood = mood;
		this.msg_content = msg_content;
		this.picture = picture;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public int getMood() {
		return mood;
	}
	public void setMood(int mood) {
		this.mood = mood;
	}
	public String getMsg_content() {
		return msg_content;
	}
	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
