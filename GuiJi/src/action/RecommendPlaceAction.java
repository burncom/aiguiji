package action;

import java.util.List;

import service.WebConstant;
import vo.MessageBean;

import com.opensymphony.xwork2.ActionContext;

public class RecommendPlaceAction extends BaseAction {
	/**
	 * �������ʵص�  �ȶȣ�����ͬ��/�������other��ͬ�У������ȣ����û���ע��𣬹�ע�ߣ�ʵʱ���Ƽ��ֺ�������,���룻
	 * ��ʾ���ݣ�1����Ϣԭ����2����Ϣת����3���ԭ����4���ת��
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
