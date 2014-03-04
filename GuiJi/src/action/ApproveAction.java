package action;

public class ApproveAction extends BaseAction {
	/**
	 * ���������������޳ɻ��߲��޳ɵİ�ť
	 */
	private static final long serialVersionUID = 1L;
	private String approve;
	private int weatherId;
	public String getApprove() {
		return approve;
	}
	public void setApprove(String approve) {
		this.approve = approve;
	}
	
	public int getWeatherId() {
		return weatherId;
	}
	public void setWeatherId(int weatherId) {
		this.weatherId = weatherId;
	}
	@Override
	public String execute() throws Exception {
		baseService.doApprove(weatherId, approve);
		return SUCCESS;
	}
}
