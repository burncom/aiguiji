package action.mood;

import action.BaseAction;

public class DeleteMoodAction extends BaseAction {
	/**
	 * ɾ��������Ϣԭ��
	 */
	private static final long serialVersionUID = 1L;
	private int msgId;

	public int getMsgId() {
		return msgId;
	}

	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}

	@Override
	public String execute() throws Exception {
		 baseService.doDeleteMood(msgId);
		return SUCCESS;
	}
	
}
