package action;

import java.util.List;

import service.WebConstant;
import vo.MessageBean;

import com.opensymphony.xwork2.ActionContext;

public class HotPlaceAction extends BaseAction {
	/**
	 * 处理新鲜地点  热度，根据同城市，用户关注类别，推荐分,评论数，转发数，想参加，时间范围；
	 * 推荐分,评论数，转发数，想参加 的权重分析:转发数 >推荐=想参加>评论数 
	 * 对于信息，热度值=0.4*推荐数+0.1*推荐分数值+0.3*所有转发数+0.2*所有评论数
	 * 对于活动，热度值=0.15*推荐数+0.1*推荐分数值+0.1*推荐分数值+0.3*所有转发数+0.2*所有评论数+0.15*想参加数+0.1*想参加分数值
	 * 显示内容：1，信息原创；2，信息转发；3，活动原创；4，活动转发
	 */
	private static final long serialVersionUID = 1L;
	private List<MessageBean> message;
	//在hotPlace.jsp，选择的显示热度信息日期
	private String chooseValue;
	
	public String getChooseValue() {
		return chooseValue;
	}

	public void setChooseValue(String chooseValue) {
		this.chooseValue = chooseValue;
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
		if(chooseValue==null){
			setMessage(baseService.getHotPlace(userName,WebConstant.DATE_TODAY));
		}
		else{
			setMessage(baseService.getHotPlace(userName,Integer.parseInt(chooseValue)));
		}
		return SUCCESS;
	}
}
