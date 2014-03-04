package action.button;

import action.BaseAction;

public class RecommendAction extends BaseAction {
	/**
	 * 处理 推荐 按钮
	 */
	private static final long serialVersionUID = 1L;
	private int msgId;
	private String recomValue;
	
	public String getRecomValue() {
		return recomValue;
	}

	public void setRecomValue(String recomValue) {
		this.recomValue = recomValue;
	}

	public int getMsgId() {
		return msgId;
	}

	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}

	@Override
	public String execute() throws Exception {
		if(recomValue==null)
			return "recommendV";
		else{
			baseService.doRecommend(msgId,Integer.parseInt(recomValue));
			return SUCCESS;
		}
	}
	
}
