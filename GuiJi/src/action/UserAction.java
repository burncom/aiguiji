package action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import vo.FindUsersBean;
import vo.MessageBean;
import vo.PhotosBean;

public class UserAction extends BaseAction {
	/**
	 * 用户Logo或者用户名点击进入的个人主页
	 */
	private static final long serialVersionUID = 1L;
	private int userId;
	private String userName;
	private FindUsersBean findUsersBean;
	private List<PhotosBean> photosBean;
	private List<MessageBean> messageBean;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public FindUsersBean getFindUsersBean() {
		return findUsersBean;
	}
	public void setFindUsersBean(FindUsersBean findUsersBean) {
		this.findUsersBean = findUsersBean;
	}
	public List<PhotosBean> getPhotosBean() {
		return photosBean;
	}
	public void setPhotosBean(List<PhotosBean> photosBean) {
		this.photosBean = photosBean;
	}
	public List<MessageBean> getMessageBean() {
		return messageBean;
	}
	public void setMessageBean(List<MessageBean> messageBean) {
		this.messageBean = messageBean;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String execute() throws Exception {
		ActionContext ctx=ActionContext.getContext();
		userName=(String) ctx.getSession().get("user");
		setFindUsersBean(baseService.getUserHeadInfo(userId));
		setPhotosBean(baseService.getUserPhotos(userId));
		setMessageBean(baseService.getUserMessages(userId));
		return SUCCESS;
	}
	
}
