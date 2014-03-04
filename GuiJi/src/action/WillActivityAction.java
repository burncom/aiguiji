package action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import service.WebConstant;
import vo.MessageBean;

public class WillActivityAction extends BaseAction {
	/**
	 * ���� �����ʵĵص� ��Action�������û����ڵ�City��ȥ��������Ȥ��𣬰���ʱ������(�������ʱ������ȵ��������������ֵ�ߵ�������)��Ȼ���վ����������
	 * ��ʾ���ݣ�1���ԭ����2���ת��
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
