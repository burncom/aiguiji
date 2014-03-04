package action.top;

import java.util.List;

import vo.TopListBean;
import action.BaseAction;

public class TopListAction extends BaseAction {
	/**
	 * 处理排行榜的Action
	 */
	private static final long serialVersionUID = 1L;
	private List<TopListBean> topListBean;

	public List<TopListBean> getTopListBean() {
		return topListBean;
	}

	public void setTopListBean(List<TopListBean> topListBean) {
		this.topListBean = topListBean;
	}


	@Override
	public String execute() throws Exception {
		setTopListBean(baseService.getTopList());
		return SUCCESS;
	}
	
}
