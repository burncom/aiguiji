package action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import vo.FindUsersBean;
import vo.MoodBean;
import vo.PhotosBean;

public class UserMoodAction extends BaseAction {
	/**
	 * 处理用户心情页面
	 */
	private static final long serialVersionUID = 1L;
	private int userId;
	private String userName;
	private FindUsersBean findUsersBean;
	private List<PhotosBean> photosBean;
	private List<MoodBean> moodBean;
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
	
	public List<MoodBean> getMoodBean() {
		return moodBean;
	}
	public void setMoodBean(List<MoodBean> moodBean) {
		this.moodBean = moodBean;
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
		setMoodBean(baseService.getUserMood(baseService.findByUserName(userName).get(0),userId));
		return super.execute();
	}
	
}
