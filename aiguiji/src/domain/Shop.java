package domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Shop implements Serializable {
	/**
	 * 商户
	 */
	private static final long serialVersionUID = 1L;
	private int iShopId;
	private String sName;
	private String sLocation;
	private float fLatitude;
	private float fLongitude;
	private String sPhones;//多个电话用；分隔
	private String sWorkWeekday;//多个周几时间用；分隔
	private String sWorkTime;//开始时间；结束时间
	private String sDate;
	private String sTime;
	private int iWeight;
	private Set<User> user=new HashSet<User>();
	private Set<Menu> menu=new HashSet<Menu>();
	private Set<ShopPic> shopPic=new HashSet<ShopPic>();
	private Set<ShopEvaluate> shopEvaluate=new HashSet<ShopEvaluate>();
	
	public Shop() {
		super();
	}
	
	public Shop(int iShopId, String sName, String sLocation, float fLatitude,
			float fLongitude, String sPhones, String sWorkWeekday,
			String sWorkTime, String sDate, String sTime, int iWeight,
			Set<User> user, Set<Menu> menu, Set<ShopPic> shopPic,
			Set<ShopEvaluate> shopEvaluate) {
		super();
		this.iShopId = iShopId;
		this.sName = sName;
		this.sLocation = sLocation;
		this.fLatitude = fLatitude;
		this.fLongitude = fLongitude;
		this.sPhones = sPhones;
		this.sWorkWeekday = sWorkWeekday;
		this.sWorkTime = sWorkTime;
		this.sDate = sDate;
		this.sTime = sTime;
		this.iWeight = iWeight;
		this.user = user;
		this.menu = menu;
		this.shopPic = shopPic;
		this.shopEvaluate = shopEvaluate;
	}
	
	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}

	public Set<Menu> getMenu() {
		return menu;
	}

	public void setMenu(Set<Menu> menu) {
		this.menu = menu;
	}

	public Set<ShopPic> getShopPic() {
		return shopPic;
	}

	public void setShopPic(Set<ShopPic> shopPic) {
		this.shopPic = shopPic;
	}

	public Set<ShopEvaluate> getShopEvaluate() {
		return shopEvaluate;
	}

	public void setShopEvaluate(Set<ShopEvaluate> shopEvaluate) {
		this.shopEvaluate = shopEvaluate;
	}

	public int getiShopId() {
		return iShopId;
	}
	public void setiShopId(int iShopId) {
		this.iShopId = iShopId;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
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
	public String getsPhones() {
		return sPhones;
	}
	public void setsPhones(String sPhones) {
		this.sPhones = sPhones;
	}
	public String getsWorkWeekday() {
		return sWorkWeekday;
	}
	public void setsWorkWeekday(String sWorkWeekday) {
		this.sWorkWeekday = sWorkWeekday;
	}
	public String getsWorkTime() {
		return sWorkTime;
	}
	public void setsWorkTime(String sWorkTime) {
		this.sWorkTime = sWorkTime;
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
	public int getiWeight() {
		return iWeight;
	}
	public void setiWeight(int iWeight) {
		this.iWeight = iWeight;
	}
	
	
}
