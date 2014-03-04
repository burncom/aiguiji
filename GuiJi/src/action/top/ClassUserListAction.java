package action.top;

import java.util.List;

import service.WebAlgo;
import vo.TopUserBean;
import action.BaseAction;

public class ClassUserListAction extends BaseAction {
	/**
	 * 处理每个分类的 top20用户
	 */
	private static final long serialVersionUID = 1L;
	private int type;
	private String typeName;
	private List<TopUserBean> topUserBean;
	
	public List<TopUserBean> getTopUserBean() {
		return topUserBean;
	}

	public void setTopUserBean(List<TopUserBean> topUserBean) {
		this.topUserBean = topUserBean;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public String execute() throws Exception {
		WebAlgo algo=new WebAlgo();
		setTypeName(algo.getCategoryIS(type));
		setTopUserBean(baseService.getClassTopUser(type));
		return SUCCESS;
	}
	
}
