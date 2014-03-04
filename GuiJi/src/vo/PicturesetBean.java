package vo;

import java.io.Serializable;

public class PicturesetBean implements Serializable {
	/**
	 * ���� ���� ͼƬ����
	 */
	private static final long serialVersionUID = 1L;
	private int userId;
	//ͼƬ������
	private String userName;
	//ͼƬ��ʱ��
	private String time;
	//ͼƬ�ĵص�
	private String place;
	private String picture;//���� ���� ����Ƭ
	public PicturesetBean(){}
	public PicturesetBean(int userId,String userName, String time, String place,
			String picture) {
		super();
		this.userId=userId;
		this.userName = userName;
		this.time = time;
		this.place = place;
		this.picture = picture;
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
}
