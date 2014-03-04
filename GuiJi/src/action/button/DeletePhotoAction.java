package action.button;

import action.BaseAction;

public class DeletePhotoAction extends BaseAction {
	/**
	 * É¾³ýÍ¼Æ¬
	 */
	private static final long serialVersionUID = 1L;
	private int photoId;

	public int getPhotoId() {
		return photoId;
	}

	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}

	@Override
	public String execute() throws Exception {
		baseService.doDeletePhoto(photoId);
		return SUCCESS;
	}
	
}
