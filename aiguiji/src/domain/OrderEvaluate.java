package domain;

import java.io.Serializable;

public class OrderEvaluate implements Serializable {
	/**
	 * ²Ëµ¥ÆÀ¼Û
	 */
	private static final long serialVersionUID = 1L;
	private int iEvaluateId;
	private int iMenuId;
	private int iUserId;
	private int iPoint;
	private String sComment;
	private String sPath;
	private String sDate;
	private String sTime;
	private Menu menu;
	private User user;
	public OrderEvaluate() {
		super();
	}
	
	public OrderEvaluate(int iEvaluateId, int iMenuId, int iUserId, int iPoint,
			String sComment, String sPath, String sDate, String sTime,
			Menu menu, User user) {
		super();
		this.iEvaluateId = iEvaluateId;
		this.iMenuId = iMenuId;
		this.iUserId = iUserId;
		this.iPoint = iPoint;
		this.sComment = sComment;
		this.sPath = sPath;
		this.sDate = sDate;
		this.sTime = sTime;
		this.menu = menu;
		this.user = user;
	}
	
	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getiEvaluateId() {
		return iEvaluateId;
	}
	public void setiEvaluateId(int iEvaluateId) {
		this.iEvaluateId = iEvaluateId;
	}
	public int getiMenuId() {
		return iMenuId;
	}
	public void setiMenuId(int iMenuId) {
		this.iMenuId = iMenuId;
	}
	public int getiUserId() {
		return iUserId;
	}
	public void setiUserId(int iUserId) {
		this.iUserId = iUserId;
	}
	public int getiPoint() {
		return iPoint;
	}
	public void setiPoint(int iPoint) {
		this.iPoint = iPoint;
	}
	public String getsComment() {
		return sComment;
	}
	public void setsComment(String sComment) {
		this.sComment = sComment;
	}
	public String getsPath() {
		return sPath;
	}
	public void setsPath(String sPath) {
		this.sPath = sPath;
	}
	public String getsDate() {
		return sDate;
	}
	public void setsDate(String sDate) {
		this.sDate = sDate;
	}
	public String getsTime() {
		return sTime;
	}
	public void setsTime(String sTime) {
		this.sTime = sTime;
	}
	
}
