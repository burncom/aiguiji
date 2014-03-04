package action.button;

import action.BaseAction;

public class JoinAction extends BaseAction {
	/**
	 * 处理  想参加按钮
	 */
	private static final long serialVersionUID = 1L;
	private int msgId;
	private String joinV;
	public int getMsgId() {
		return msgId;
	}
	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}
	public String getJoinV() {
		return joinV;
	}
	public void setJoinV(String joinV) {
		this.joinV = joinV;
	}
	@Override
	public String execute() throws Exception {
		if(joinV==null)
			return "joinV";
		else{
			baseService.doJoin(msgId,Integer.parseInt(joinV));
			return SUCCESS;
		}
	}
	
}
