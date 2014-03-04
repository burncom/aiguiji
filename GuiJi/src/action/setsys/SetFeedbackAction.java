package action.setsys;

import service.WebConstant;
import action.BaseAction;

public class SetFeedbackAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String feedbackValue;
	private String feedbackContent;
	private String tip;
	
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public String getFeedbackValue() {
		return feedbackValue;
	}
	public void setFeedbackValue(String feedbackValue) {
		this.feedbackValue = feedbackValue;
	}
	public String getFeedbackContent() {
		return feedbackContent;
	}
	public void setFeedbackContent(String feedbackContent) {
		this.feedbackContent = feedbackContent;
	}
	@Override
	public String execute() throws Exception {
		int value=5;
		if(feedbackValue.length()!=0 || feedbackContent.length()!=0){
			if(feedbackValue.length()!=0){
				value=Integer.parseInt(feedbackValue);
			}
			baseService.setFeedBack(value, feedbackContent);
			setTip("感谢您的意见反馈！");
			return WebConstant.SETUPFEEDBACK;
		}else{
			setTip("意见反馈失败哦！");
			return SUCCESS;
		}
		
	}
	
}
