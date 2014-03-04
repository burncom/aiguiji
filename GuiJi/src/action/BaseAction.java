package action;

import service.BaseService;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {
	/**
	 * »ù±¾Action
	 */
	private static final long serialVersionUID = 1L;
	
	protected BaseService baseService;
	
	public BaseService getBaseService() {
		return baseService;
	}
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	
}
