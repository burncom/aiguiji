package action.setsys;

import service.WebConstant;
import domain.UserInfo;
import action.BaseAction;

public class SetOwnInfoAction extends BaseAction {
	/**
	 * �޸� �û�������Ϣ
	 */
	private static final long serialVersionUID = 1L;
	private String birthday;
	private UserInfo userInfo;
	private String tip;
	
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	@Override
	public String execute() throws Exception {
		if((birthday!=null) && (birthday.length()!=0)){
			userInfo.setBirthday(Integer.parseInt(birthday));
		}
		
		if(baseService.setOwnInfo(userInfo)==true){
			setTip("�޸ĸ�����Ϣ�ɹ���");
			return WebConstant.SETUPOWNINFOYES;
		}
		
		return SUCCESS;
	}
	
}
