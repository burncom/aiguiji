package domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable {
	/**
	 * 用户
	 */
	private static final long serialVersionUID = 1L;
	private int iUserId;
	private int iShopId;
	private String sAccount;
	private String sPassWord;
	private String sPhone;
	private String sLocation;//包含定位位置，和用户输入的靠近位置
	private float fLatitude;
	private float fLongitude;
	private String sAvatar;
	private String sDate;
	private String sTime;
	private Set<Order> order=new HashSet<Order>();
	private Set<OrderEvaluate> orderEvaluate=new HashSet<OrderEvaluate>();
	private Set<ShopEvaluate> shopEvaluate=new HashSet<ShopEvaluate>();
	private Shop shop;
	public User() {
		super();
	}
	
	public User(int iUserId, int iShopId, String sAccount, String sPassWord,
			String sPhone, String sLocation, float fLatitude, float fLongitude,
			String sAvatar, String sDate, String sTime, Set<Order> order,
			Set<OrderEvaluate> orderEvaluate, Set<ShopEvaluate> shopEvaluate,
			Shop shop) {
		super();
		this.iUserId = iUserId;
		this.iShopId = iShopId;
		this.sAccount = sAccount;
		this.sPassWord = sPassWord;
		this.sPhone = sPhone;
		this.sLocation = sLocation;
		this.fLatitude = fLatitude;
		this.fLongitude = fLongitude;
		this.sAvatar = sAvatar;
		this.sDate = sDate;
		this.sTime = sTime;
		this.order = order;
		this.orderEvaluate = orderEvaluate;
		this.shopEvaluate = shopEvaluate;
		this.shop = shop;
	}

	
	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Set<Order> getOrder() {
		return order;
	}

	public void setOrder(Set<Order> order) {
		this.order = order;
	}

	public Set<OrderEvaluate> getOrderEvaluate() {
		return orderEvaluate;
	}

	public void setOrderEvaluate(Set<OrderEvaluate> orderEvaluate) {
		this.orderEvaluate = orderEvaluate;
	}

	public Set<ShopEvaluate> getShopEvaluate() {
		return shopEvaluate;
	}

	public void setShopEvaluate(Set<ShopEvaluate> shopEvaluate) {
		this.shopEvaluate = shopEvaluate;
	}

	public int getiUserId() {
		return iUserId;
	}
	public void setiUserId(int iUserId) {
		this.iUserId = iUserId;
	}
	public int getiShopId() {
		return iShopId;
	}
	public void setiShopId(int iShopId) {
		this.iShopId = iShopId;
	}
	public String getsAccount() {
		return sAccount;
	}
	public void setsAccount(String sAccount) {
		this.sAccount = sAccount;
	}
	public String getsPassWord() {
		return sPassWord;
	}
	public void setsPassWord(String sPassWord) {
		this.sPassWord = sPassWord;
	}
	public String getsPhone() {
		return sPhone;
	}
	public void setsPhone(String sPhone) {
		this.sPhone = sPhone;
	}
	public String getsLocation() {
		return sLocation;
	}
	public void setsLocation(String sLocation) {
		this.sLocation = sLocation;
	}
	public float getfLatitude() {
		return fLatitude;
	}
	public void setfLatitude(float fLatitude) {
		this.fLatitude = fLatitude;
	}
	public float getfLongitude() {
		return fLongitude;
	}
	public void setfLongitude(float fLongitude) {
		this.fLongitude = fLongitude;
	}
	public String getsAvatar() {
		return sAvatar;
	}
	public void setsAvatar(String sAvatar) {
		this.sAvatar = sAvatar;
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
