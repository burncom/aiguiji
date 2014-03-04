package action.setsys;

import service.WebConstant;
import domain.UserInfo;
import action.BaseAction;

public class SetOwnInfoAction extends BaseAction {
	/**
	 * 修改 用户个人信息
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
			setTip("修改个人信息成功！");
			return WebConstant.SETUPOWNINFOYES;
		}
		
		return SUCCESS;
	}
	
}
