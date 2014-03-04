package vo;

import java.io.Serializable;

public class FindUsersBean implements Serializable {
	/**
	 * �����û�ʱ�������û��Ĳ����б�
	 */
	private static final long serialVersionUID = 1L;
	//��ǰ�û�A�����û�B�Ĺ�ϵ״̬
	//1,A,Bû���κι�ϵ��2��BΪA�ķ�˿,Ҳ����B��עA��3��AΪB�ķ�˿��Ҳ����A��עB��4��A��B�����ע
	private int status;
	private int userId;
	//�û�����
	private String userName;
	//�û���logo
	private String userLogo;
	//�û�����
	private String hometown;
	//���ھ�ס��
	private String place;
	
	
	public FindUsersBean() {
	}
	public FindUsersBean(int status, int userId, String userName,
			String userLogo, String hometown,String place) {
		super();
		this.status = status;
		this.userId = userId;
		this.userName = userName;
		this.userLogo = userLogo;
		this.hometown = hometown;
		this.place=place;
	}
	
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserLogo() {
		return userLogo;
	}
	public void setUserLogo(String userLogo) {
		this.userLogo = userLogo;
	}
	public String getHometown() {
		return hometown;
	}
	public void setHometown(String hometown) {
		this.hometown = hometown;
	}
}
