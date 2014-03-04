package action.button;

import action.BaseAction;

public class DeleteReplyAction extends BaseAction {
	/**
	 * ɾ���ظ� 
	 */
	private static final long serialVersionUID = 1L;
	private int commentMsgId;
	private int msgId;

	public int getCommentMsgId() {
		return commentMsgId;
	}

	public void setCommentMsgId(int commentMsgId) {
		this.commentMsgId = commentMsgId;
	}
	
	public int getMsgId() {
		return msgId;
	}

	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}

	@Override
	public String execute() throws Exception {
		baseService.deleteReplyMsg(msgId, commentMsgId);
		return SUCCESS;
	}
	
}
