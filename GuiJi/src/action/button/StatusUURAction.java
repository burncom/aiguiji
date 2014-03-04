package action.button;

import action.BaseAction;

public class StatusUURAction extends BaseAction {
	/**
	 * 处理四种类型情况下的关注情况按钮
	 * 1，无任何关系：关注
	 * 2，B为A（当前用户）的粉丝：关注，移除粉丝
	 * 3，A关注B：取消关注
	 * 4，A，B互相关注：取消关注，移除粉丝
	 */
	private static final long serialVersionUID = 1L;
	private int status;
	private int statusValue;
	private int userId;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getStatusValue() {
		return statusValue;
	}
	public void setStatusValue(int statusValue) {
		this.statusValue = statusValue;
	}
	@Override
	public String execute() throws Exception {
		baseService.doStatusUUR(status, statusValue, userId);
		return SUCCESS;
	}
	
}
