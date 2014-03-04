package action;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;

public class LoginAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String sAccount;
	public String sPassword;
	public int type;
	public String sResult;
	
	public String getsResult() {
		return sResult;
	}
	public void setsResult(String sResult) {
		this.sResult = sResult;
	}
	public String getsAccount() {
		return sAccount;
	}
	public void setsAccount(String sAccount) {
		this.sAccount = sAccount;
	}
	public String getsPassword() {
		return sPassword;
	}
	public void setsPassword(String sPassword) {
		this.sPassword = sPassword;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String execute() throws Exception {
		boolean bRet=baseService.verifyUser(sAccount, sPassword, type);
		System.out.println(sAccount+type);
		if(bRet){
			sResult="yes";
			return SUCCESS;
		}else{
			Map<String, Comparable>  mResult=new HashMap();
			mResult.put("account",sAccount);
			mResult.put("pass",sPassword);
			mResult.put("type",type );
			JSONArray jsonArray = JSONArray.fromObject( mResult ); 
			
			sResult=jsonArray.toString();
			
			sResult=getJsonStr4Map(mResult);
			
			return ERROR;
		}
	}
	
}
