package action.setsys;

import com.sun.net.httpserver.Authenticator.Failure;

import service.WebConstant;
import action.BaseAction;

public class SetEmailAction extends BaseAction {
	/**
	 * �޸�ע������
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
				setTip("�޸�����ɹ���");
				return WebConstant.SETUSEREMAILYES;
			}
			else{
				setTip("�����Ѿ���ʹ�ã�");
				return INPUT;
			}
			
		}
		return SUCCESS;
	}
	
}
