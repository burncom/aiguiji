package domain;

import java.io.Serializable;

public class ShopEvaluate implements Serializable {
	/**
	 * …Ãªß∆¿º€
	 */
	private static final long serialVersionUID = 1L;
	private int iEvaluateId;
	private int iShopId;
	private int iUserId;
	private int iService;
	private int iSpeed;
	private int iTaste;
	private Shop shop;
	private User user;
	public ShopEvaluate() {
		super();
	}
	
	public ShopEvaluate(int iEvaluateId, int iShopId, int iUserId,
			int iService, int iSpeed, int iTaste, Shop shop, User user) {
		super();
		this.iEvaluateId = iEvaluateId;
		this.iShopId = iShopId;
		this.iUserId = iUserId;
		this.iService = iService;
		this.iSpeed = iSpeed;
		this.iTaste = iTaste;
		this.shop = shop;
		this.user = user;
	}
	
	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
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
	public int getiShopId() {
		return iShopId;
	}
	public void setiShopId(int iShopId) {
		this.iShopId = iShopId;
	}
	public int getiUserId() {
		return iUserId;
	}
	public void setiUserId(int iUserId) {
		this.iUserId = iUserId;
	}
	public int getiService() {
		return iService;
	}
	public void setiService(int iService) {
		this.iService = iService;
	}
	public int getiSpeed() {
		return iSpeed;
	}
	public void setiSpeed(int iSpeed) {
		this.iSpeed = iSpeed;
	}
	public int getiTaste() {
		return iTaste;
	}
	public void setiTaste(int iTaste) {
		this.iTaste = iTaste;
	}
	
	
}
