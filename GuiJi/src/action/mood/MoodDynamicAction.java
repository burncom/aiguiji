package action.mood;

import java.util.List;

import service.WebConstant;
import vo.MoodBean;

import com.opensymphony.xwork2.ActionContext;

import action.BaseAction;

public class MoodDynamicAction extends BaseAction {
	/**
	 * 获得我自己的心情信息+关注者的心情信息
	 */
	private static final long serialVersionUID = 1L;
	private List<MoodBean> moodBean;
	private int moodType;

	public int getMoodType() {
		return moodType;
	}
	public void setMoodType(int moodType) {
		this.moodType = moodType;
	}
	public List<MoodBean> getMoodBean() {
		return moodBean;
	}
	public void setMoodBean(List<MoodBean> moodBean) {
		this.moodBean = moodBean;
	}
	@Override
	public String execute() throws Exception {
		ActionContext ctx=ActionContext.getContext();
		String userName=(String) ctx.getSession().get("user");
		if(moodType==0){
			setMoodBean(baseService.getMoodDynamic(userName,WebConstant.MOOD_FOLLOWER));
		}
		else{
			setMoodBean(baseService.getMoodDynamic(userName,moodType));
		}
		
		return SUCCESS;
	}
}
