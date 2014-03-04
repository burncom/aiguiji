package action.setsys;

import com.sun.net.httpserver.Authenticator.Failure;

import service.WebConstant;
import action.BaseAction;

public class SetEmailAction extends BaseAction {
	/**
	 * 修改注册邮箱
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	private String tip;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	@Override
	public String execute() throws Exception {
		if(email.length()!=0){
			if(baseService.validEmail(email)==WebConstant.EMAILNOTEXIST){
				baseService.setUserEmail(email);
				setTip("修改邮箱成功！");
				return WebConstant.SETUSEREMAILYES;
			}
			else{
				setTip("邮箱已经被使用！");
				return INPUT;
			}
			
		}
		return SUCCESS;
	}
	
}
