package action;

import java.util.List;

import vo.FindUsersBean;

public class FindUsersAction extends BaseAction {
	/**
	 * 处理查找用户的Action
	 */
	private static final long serialVersionUID = 1L;
	private List<FindUsersBean> findUsersBean;
	private String findUserName;
	public List<FindUsersBean> getFindUsersBean() {
		return findUsersBean;
	}
	public void setFindUsersBean(List<FindUsersBean> findUsersBean) {
		this.findUsersBean = findUsersBean;
	}
	public String getFindUserName() {
		return findUserName;
	}
	public void setFindUserName(String findUserName) {
		this.findUserName = findUserName;
	}
	@Override
	public String execute() throws Exception {
		if(findUserName==null){
			return "findUser";
		}
		else{
			setFindUsersBean(baseService.findUser(findUserName));
			findUserName=null;
			return SUCCESS;
		}
		
	}
	
}
