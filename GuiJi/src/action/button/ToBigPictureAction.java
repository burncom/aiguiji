package action.button;

import action.BaseAction;

public class ToBigPictureAction extends BaseAction {
	/**
	 * �Ŵ�ͼƬ
	 */
	private static final long serialVersionUID = 1L;
	private String picture;

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
}
