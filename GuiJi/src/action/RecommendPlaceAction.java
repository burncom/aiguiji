package action;

import java.util.List;

import service.WebConstant;
import vo.MessageBean;

import com.opensymphony.xwork2.ActionContext;

public class RecommendPlaceAction extends BaseAction {
	/**
	 * 处理新鲜地点  热度，根据同县/区（如果other相同有，则优先），用户关注类别，关注者，实时，推荐分和评论数,距离；
	 * 显示内容：1，信息原创；2，信息转发；3，活动原创；4，活动转发
	 */
	private static final long serialVersionUID = 1L;
	private List<MessageBean> message;
	private String recommendType;

	public String getRecommendType() {
		return recommendType;
	}

	public void setRecommendType(String recommendType) {
		this.recommendType = recommendType;
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
		if(recommendType==null)
			setMessage(baseService.getRecommendPlace(userName,WebConstant.RECOMMEND_TOTAL));
		else 
			setMessage(baseService.getRecommendPlace(userName,Integer.parseInt(recommendType)));
		
		return SUCCESS;
	}
}
