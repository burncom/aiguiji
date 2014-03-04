package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import service.WebControl;

import com.opensymphony.xwork2.ActionContext;

public class LogoutAction extends BaseAction implements ServletRequestAware{
	/**
	 * �˳�ϵͳ
	 */
	private static final long serialVersionUID = 1L;
	//����һ��HttpServletRequest����
	private HttpServletRequest request;
	//ʵ��ServletRequestAware�ӿڱ�����д�ķ���
	public void setServletRequest(
		HttpServletRequest request)
	{
		this.request = request;
	}
	public String execute()
		throws Exception
	{
		ActionContext ctx=ActionContext.getContext();
		String userName=(String) ctx.getSession().get("user");
		WebControl.getWebUserList().remove(userName);
		//��ȡHttpSession
		HttpSession session = request.getSession();
		//ʹSessionʧЧ
		session.invalidate();
		return SUCCESS;
	}
}
