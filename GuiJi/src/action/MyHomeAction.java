package action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import vo.MessageBean;

public class MyHomeAction extends BaseAction {
	/**
	 * 获得我自己的信息+关注者的信息
	 */
	private static final long serialVersionUID = 1L;
	private List<MessageBean> message;

	public List<MessageBean> getMessage() {
		return message;
	}

	public void setMessage(List<MessageBean> message) {
		this.message = message;
	}

	@Override
	public String execute() throws Exception {
		ActionContext ctx=ActionContext.getContext();
		String userName=(String) ctx.getSession().get("user");
		setMessage(baseService.getMyHome(userName));
		return SUCCESS;
	}
	
}
