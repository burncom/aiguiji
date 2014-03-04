package domain;

import java.io.Serializable;

public class PhotosInfo implements Serializable {
	/**
	 * 用户相册
	 */
	private static final long serialVersionUID = 1L;
	private int photo_id;
	private String photo;
	//对应用户元数据表主键
	private UserInfo userInfo;
	
	public PhotosInfo() {
	}

	public PhotosInfo(int photo_id, String photo, UserInfo userInfo) {
		super();
		this.photo_id = photo_id;
		this.photo = photo;
		this.userInfo = userInfo;
	}

	public int getPhoto_id() {
		return photo_id;
	}

	public void setPhoto_id(int photo_id) {
		this.photo_id = photo_id;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
}
