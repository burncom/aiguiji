package action;

import java.util.List;

import service.WebConstant;
import vo.MessageBean;

import com.opensymphony.xwork2.ActionContext;

public class HotPlaceAction extends BaseAction {
	/**
	 * �������ʵص�  �ȶȣ�����ͬ���У��û���ע����Ƽ���,��������ת��������μӣ�ʱ�䷶Χ��
	 * �Ƽ���,��������ת��������μ� ��Ȩ�ط���:ת���� >�Ƽ�=��μ�>������ 
	 * ������Ϣ���ȶ�ֵ=0.4*�Ƽ���+0.1*�Ƽ�����ֵ+0.3*����ת����+0.2*����������
	 * ���ڻ���ȶ�ֵ=0.15*�Ƽ���+0.1*�Ƽ�����ֵ+0.1*�Ƽ�����ֵ+0.3*����ת����+0.2*����������+0.15*��μ���+0.1*��μӷ���ֵ
	 * ��ʾ���ݣ�1����Ϣԭ����2����Ϣת����3���ԭ����4���ת��
	 */
	private static final long serialVersionUID = 1L;
	private List<MessageBean> message;
	//��hotPlace.jsp��ѡ�����ʾ�ȶ���Ϣ����
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
