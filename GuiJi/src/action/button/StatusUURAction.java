package action.button;

import action.BaseAction;

public class StatusUURAction extends BaseAction {
	/**
	 * ����������������µĹ�ע�����ť
	 * 1�����κι�ϵ����ע
	 * 2��BΪA����ǰ�û����ķ�˿����ע���Ƴ���˿
	 * 3��A��עB��ȡ����ע
	 * 4��A��B�����ע��ȡ����ע���Ƴ���˿
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
