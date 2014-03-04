package action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import vo.MessageBean;

public class TimePlaceAction extends BaseAction {
	/**
	 * 处理新鲜地点  实时，根据同城市，用户关注类别，实时；
	 * 显示内容：1，信息原创；2，信息转发；3，活动原创；4，活动转发
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
		setMessage(baseService.getTimePlace(userName));
		
		return SUCCESS;
	}
	
}
