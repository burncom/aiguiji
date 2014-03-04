package action;

import com.opensymphony.xwork2.ActionContext;

import vo.MoodBean;

public class MoodAction extends BaseAction {
	/**
	 * 处理心情页面的 Action
	 */
	private static final long serialVersionUID = 1L;
	private MoodBean moodBean;

	public MoodBean getMoodBean() {
		return moodBean;
	}

	public void setMoodBean(MoodBean moodBean) {
		this.moodBean = moodBean;
	}

	@Override
	public String execute() throws Exception {
		ActionContext ctx=ActionContext.getContext();
		String userName=(String) ctx.getSession().get("user");
		setMoodBean(baseService.getMoodInfo(userName));
		return SUCCESS;
	}
	
}
