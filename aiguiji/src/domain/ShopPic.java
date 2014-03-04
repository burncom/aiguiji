package domain;

import java.io.Serializable;

public class ShopPic implements Serializable {
	/**
	 * ÉÌ»§Í¼Æ¬
	 */
	private static final long serialVersionUID = 1L;
	private int iPicId;
	private int iShopId;
	private String sPath;
	private Shop shop;
	public ShopPic() {
		super();
	}
	
	public ShopPic(int iPicId, int iShopId, String sPath, Shop shop) {
		super();
		this.iPicId = iPicId;
		this.iShopId = iShopId;
		this.sPath = sPath;
		this.shop = shop;
	}
	
	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public int getiPicId() {
		return iPicId;
	}
	public void setiPicId(int iPicId) {
		this.iPicId = iPicId;
	}
	public int getiShopId() {
		return iShopId;
	}
	public void setiShopId(int iShopId) {
		this.iShopId = iShopId;
	}
	public String getsPath() {
		return sPath;
	}
	public void setsPath(String sPath) {
		this.sPath = sPath;
	}
	
}
