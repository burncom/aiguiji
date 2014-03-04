package action;

import service.WebConstant;

import com.opensymphony.xwork2.ActionContext;

import domain.UserInfo;

public class RegisterAction extends BaseAction {
	/**
	 * ����ע������Action
	 */
	private static final long serialVersionUID = 1L;
	
	private UserInfo userInfo;
	private String tip;
	private String repassword;
	private String gender;
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	@Override
	public String execute() throws Exception {
		if(userInfo.getUser_password().equals(repassword)){
			int flag=baseService.validateRegister(userInfo);
			if(flag==WebConstant.RG_CANREGISTER){
				ActionContext ctx=ActionContext.getContext();
				ctx.getSession().put("user", userInfo.getUser_name());
				if(gender.equals("˧��"))
					userInfo.setGender(WebConstant.SEX_MALE);
				else 
					userInfo.setGender(WebConstant.SEX_FEMAL);
				userInfo.setNowcoordinate(userInfo.getCoordinate());
				baseService.register(userInfo);
				baseService.addNowPlace(userInfo);
				baseService.addLog(WebConstant.OP_REGISTER);
				return SUCCESS;
			}
			else if(flag==WebConstant.RG_CANLOGIN){
				setTip("�Ѿ�ע���Ŷ������ֱ�ӵ�¼");
				return WebConstant.TOLOGIN;
			}else if(flag==WebConstant.RG_EMAILSAME){
				setTip("�����Ѿ�ע�����");
				return ERROR;
			}
			else{
				setTip("�ǳ��Ѿ���ע�����");
				return ERROR;
			}
		}else{
			setTip("��������������벻ͬ!");
			return ERROR;
		}
	}
	
}
