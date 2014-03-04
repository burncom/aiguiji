package vo;

import java.io.Serializable;

public class FollowerFansBean implements Serializable {
	/**
	 * ���� ��ע�ߣ���˿ 
	 */
	private static final long serialVersionUID = 1L;
	//��ǰ�û�A�����û�B�Ĺ�ϵ״̬
	//1,A,Bû���κι�ϵ��2��BΪA�ķ�˿,Ҳ����B��עA��3��AΪB�ķ�˿��Ҳ����A��עB��4��A��B�����ע
	private int status;
	private int userId;
	//1�����û�û�з���Ϣ���߻ʱ��2���û����Ϊ��Ϣʱ;3���û����Ϊ�ʱ��
	private int type;
	//�û���logo
	private String userLogo;
	//�û�����
	private String userName;
	//��Ϊ��Ϣ����Ϊ����ʱ���ʱ�䣻
	private String time;
	//��Ϊ��Ϣ����Ϊ����ʱ��ĵص㣻��Ϊ�����Ϊ��ĵص�
	private String place;
	//��Ϊ�����Ϊ�����ֹʱ�� 
	private String start_time;
	//��Ϊ�����Ϊ��Ľ���ʱ��
	private String end_time;
	//����ָ��
	private int mood;
	private String msg_content;
	private String picture;
	
	public FollowerFansBean() {
	}
	
	//�����û�з�����Ϣ��������ʱ
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

	//Ϊ�
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
	//Ϊ��Ϣ
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
