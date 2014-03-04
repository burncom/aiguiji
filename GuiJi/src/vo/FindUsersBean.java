package vo;

import java.io.Serializable;

public class FindUsersBean implements Serializable {
	/**
	 * 查找用户时，对于用户的查找列表
	 */
	private static final long serialVersionUID = 1L;
	//当前用户A，和用户B的关系状态
	//1,A,B没有任何关系；2，B为A的粉丝,也就是B关注A；3，A为B的粉丝，也就是A关注B；4，A，B互相关注
	private int status;
	private int userId;
	//用户名字
	private String userName;
	//用户的logo
	private String userLogo;
	//用户家乡
	private String hometown;
	//现在居住地
	private String place;
	
	
	public FindUsersBean() {
	}
	public FindUsersBean(int status, int userId, String userName,
			String userLogo, String hometown,String place) {
		super();
		this.status = status;
		this.userId = userId;
		this.userName = userName;
		this.userLogo = userLogo;
		this.hometown = hometown;
		this.place=place;
	}
	
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserLogo() {
		return userLogo;
	}
	public void setUserLogo(String userLogo) {
		this.userLogo = userLogo;
	}
	public String getHometown() {
		return hometown;
	}
	public void setHometown(String hometown) {
		this.hometown = hometown;
	}
}
