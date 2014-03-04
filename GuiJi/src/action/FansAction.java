package action;

import java.util.List;

import vo.FollowerFansBean;

public class FansAction extends BaseAction {
	/**
	 * ��˿,�г���˿���У����ݺ͵�ǰ�û���ϵ���ӽ�������
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
