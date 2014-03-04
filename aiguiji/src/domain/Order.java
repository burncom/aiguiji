package domain;

import java.io.Serializable;

public class Order implements Serializable {
	/**
	 * ¶©µ¥
	 */
	private static final long serialVersionUID = 1L;
	private int iOrderId;
	private int iUserId;
	private int iMenuId;
	private int iPrice;
	private String sPrice;
	private String sDate;
	private String sTime;
	private User user;
	private Menu menu;
	public Order() {
		super();
	}
	
	

	public Order(int iOrderId, int iUserId, int iMenuId, int iPrice,
			String sPrice, String sDate, String sTime, User user, Menu menu) {
		super();
		this.iOrderId = iOrderId;
		this.iUserId = iUserId;
		this.iMenuId = iMenuId;
		this.iPrice = iPrice;
		this.sPrice = sPrice;
		this.sDate = sDate;
		this.sTime = sTime;
		this.user = user;
		this.menu = menu;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}



	public int getiOrderId() {
		return iOrderId;
	}
	public void setiOrderId(int iOrderId) {
		this.iOrderId = iOrderId;
	}
	public int getiUserId() {
		return iUserId;
	}
	public void setiUserId(int iUserId) {
		this.iUserId = iUserId;
	}
	public int getiMenuId() {
		return iMenuId;
	}
	public void setiMenuId(int iMenuId) {
		this.iMenuId = iMenuId;
	}
	public int getiPrice() {
		return iPrice;
	}
	public void setiPrice(int iPrice) {
		this.iPrice = iPrice;
	}
	public String getsPrice() {
		return sPrice;
	}
	public void setsPrice(String sPrice) {
		this.sPrice = sPrice;
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
