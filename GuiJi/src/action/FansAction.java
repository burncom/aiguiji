package action;

import java.util.List;

import vo.FollowerFansBean;

public class FansAction extends BaseAction {
	/**
	 * 粉丝,列出粉丝排行，根据和当前用户关系更加紧密排序
	 */
	private static final long serialVersionUID = 1L;
	private List<FollowerFansBean> followerFansBean;
	public List<FollowerFansBean> getFollowerFansBean() {
		return followerFansBean;
	}
	public void setFollowerFansBean(List<FollowerFansBean> followerFansBean) {
		this.followerFansBean = followerFansBean;
	}
	@Override
	public String execute() throws Exception {
		setFollowerFansBean(baseService.getFans());
		return SUCCESS;
	}
}
