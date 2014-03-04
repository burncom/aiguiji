package action.button;

import action.BaseAction;

public class DeleteMsgOrActAction extends BaseAction {
	/**
	 * 删除信息或者活动
	 */
	private static final long serialVersionUID = 1L;
	private int msgId;
	private String type;
	public int getMsgId() {
		return msgId;
	}
	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String execute() throws Exception {
		baseService.doDeleteMsgOrAct(msgId, Integer.parseInt(type));
		return SUCCESS;
	}
	
}
