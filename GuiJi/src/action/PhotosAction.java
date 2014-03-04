package action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import vo.PhotosBean;

public class PhotosAction extends BaseAction {
	/**
	 * ”√ªßœ‡≤·
	 */
	private static final long serialVersionUID = 1L;
	private List<PhotosBean> photos;
	private String userName;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<PhotosBean> getPhotos() {
		return photos;
	}

	public void setPhotos(List<PhotosBean> photos) {
		this.photos = photos;
	}

	@Override
	public String execute() throws Exception {
		ActionContext ctx=ActionContext.getContext();
		userName=(String) ctx.getSession().get("user");
		setPhotos(baseService.getPhotos());
		return SUCCESS;
	}
	
}
