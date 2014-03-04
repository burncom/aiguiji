package action.setsys;

import service.WebConstant;
import action.BaseAction;

public class SetupInterestAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String interest;
	private String tip;
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	@Override
	public String execute() throws Exception {
		if(interest.length()!=0 && interest!=null){
			baseService.setUserInterest(interest);
			setTip("�޸ĸ���Ȥ�ص�ɹ���");
			return WebConstant.SETUPINTERESTYES;
		}else{
			setTip("�޸ĸ���Ȥ�ص�ʧ�ܣ�");
			return SUCCESS;
		}
	}
	
}
