package vo;

import java.io.Serializable;

public class PhotosBean implements Serializable {
	/**
	 * ¥¶¿Ìœ‡≤·
	 */
	private static final long serialVersionUID = 1L;
	private String photo;
	private int photoId;
	public PhotosBean() {
		super();
	}

	public PhotosBean(String photo, int photoId) {
		super();
		this.photo = photo;
		this.photoId = photoId;
	}
	
	public int getPhotoId() {
		return photoId;
	}
	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
}
