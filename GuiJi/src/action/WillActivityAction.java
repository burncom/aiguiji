package action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import service.WebConstant;
import vo.MessageBean;

public class WillActivityAction extends BaseAction {
	/**
	 * 处理 将精彩的地点 的Action，根据用户所在的City，去掉不感兴趣类别，按照时间排序(如果存在时间上相等的情况，则按照信任值高到低排序)，然后按照距离最近排序
	 * 显示内容：1，活动原创；2，活动转发
	 */
	private static final long serialVersionUID = 1L;
	private List<MessageBean> message;
	private String willActivityType;
	
	
	public String getWillActivityType() {
		return willActivityType;
	}

	public void setWillActivityType(String willActivityType) {
		this.willActivityType = willActivityType;
	}

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
		if(willActivityType==null)
			setMessage(baseService.getWillActivity(userName,WebConstant.RECOMMEND_TOTAL));
		else 
			setMessage(baseService.getWillActivity(userName,Integer.parseInt(willActivityType)));
		return SUCCESS;
	}
	
}
