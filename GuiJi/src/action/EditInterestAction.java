package action;

import service.WebConstant;
import domain.UserInfo;

public class EditInterestAction extends BaseAction {
	/**
	 * 处理设置感兴趣地点的Action
	 */
	private static final long serialVersionUID = 1L;
	private UserInfo userInfo;
	
	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public String execute() throws Exception {
		baseService.setCategoryPage(userInfo,userInfo.getCategory());
		baseService.addLog(WebConstant.OP_LOGIN);
		return SUCCESS;
	}	
}
