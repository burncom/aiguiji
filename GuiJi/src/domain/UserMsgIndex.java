package domain;

import java.io.Serializable;

public class UserMsgIndex implements Serializable {
	/**
	 * 用户信息索引表
	 */
	private static final long serialVersionUID = 1L;
	//用户信息索引表主键
	private int id;
	//信息发布者序号
	private int author_id;
	//用户序号
	private UserInfo userInfo;
	//信息序号
	private MessageInfo messageInfo;
	//实时时间
	private int realtime;
	//日期时间
	private int datetime;
	public UserMsgIndex(){
		
	}

	public UserMsgIndex(int id, int author_id, UserInfo userInfo,
			MessageInfo messageInfo, int realtime, int datetime) {
		super();
		this.id = id;
		this.author_id = author_id;
		this.userInfo = userInfo;
		this.messageInfo = messageInfo;
		this.realtime = realtime;
		this.datetime = datetime;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public MessageInfo getMessageInfo() {
		return messageInfo;
	}

	public void setMessageInfo(MessageInfo messageInfo) {
		this.messageInfo = messageInfo;
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
	
}
