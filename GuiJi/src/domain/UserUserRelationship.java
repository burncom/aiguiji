package domain;

import java.io.Serializable;

public class UserUserRelationship implements Serializable {
	/**
	 * 用户与用户关系表
	 */
	private static final long serialVersionUID = 1L;
	//用户与用户关系表主键
	private int id;
	//被关注者序号
	private int follower_id;
	//关系类型(1:粉丝；2：关注；3，互相关注)
	private int type;
	//对应用户信息元数据表
	private UserInfo userInfo;
	
	public UserUserRelationship(){
		
	}

	public UserUserRelationship(int id, int follower_id, int type,
			UserInfo userInfo) {
		super();
		this.id = id;
		this.follower_id = follower_id;
		this.type = type;
		this.userInfo = userInfo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFollower_id() {
		return follower_id;
	}

	public void setFollower_id(int follower_id) {
		this.follower_id = follower_id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
}
