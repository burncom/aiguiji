package action;

import java.util.List;

import service.WebConstant;
import service.WebControl;

import com.opensymphony.xwork2.ActionContext;

import domain.UserInfo;

public class LoginAction extends BaseAction{
	/**
	 * 处理登录请求
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
		ActionContext ctx=ActionContext.getContext();
		
		List<UserInfo> users=baseService.validLogin(userInfo);
		if(users!=null && users.size()!=0)
		{
			UserInfo user=users.get(0);
			
			userInfo.setUser_name(user.getUser_name());
			ctx.getSession().put("user", userInfo.getUser_name());
			//(纬度，经度)
			WebControl.getWebUserList().add(userInfo.getUser_name());
			System.out.println(WebControl.getWebUserList());
			baseService.addNowPlace(userInfo);
			if(userInfo.getUser_name().equals(WebConstant.WEBADMIN)){
				baseService.ComputeTrustValue();
			}
			
			baseService.addLog(WebConstant.OP_LOGIN);
			
			return SUCCESS;
		}
		else 
			return ERROR;
	}
	
}
