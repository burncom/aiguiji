package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import service.WebControl;

import com.opensymphony.xwork2.ActionContext;

public class LogoutAction extends BaseAction implements ServletRequestAware{
	/**
	 * 退出系统
	 */
	private static final long serialVersionUID = 1L;
	//定义一个HttpServletRequest对象
	private HttpServletRequest request;
	//实现ServletRequestAware接口必须重写的方法
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
		//获取HttpSession
		HttpSession session = request.getSession();
		//使Session失效
		session.invalidate();
		return SUCCESS;
	}
}
