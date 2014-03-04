package action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import vo.MessageBean;

public class TimePlaceAction extends BaseAction {
	/**
	 * �������ʵص�  ʵʱ������ͬ���У��û���ע���ʵʱ��
	 * ��ʾ���ݣ�1����Ϣԭ����2����Ϣת����3���ԭ����4���ת��
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
