package domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Menu implements Serializable {
	/**
	 *  ²Ëµ¥
	 */
	private static final long serialVersionUID = 1L;
	private int iMenuId;
	private int iShopId;
	private String sClassName;
	private String sDishName;
	private int iPrice;
	private Set<Order> order=new HashSet<Order>();
	private Set<OrderEvaluate> orderEvaluate=new HashSet<OrderEvaluate>();
	private Shop shop;
	public Menu() {
		super();
	}
	
	public Menu(int iMenuId, int iShopId, String sClassName, String sDishName,
			int iPrice, Set<Order> order, Set<OrderEvaluate> orderEvaluate,
			Shop shop) {
		super();
		this.iMenuId = iMenuId;
		this.iShopId = iShopId;
		this.sClassName = sClassName;
		this.sDishName = sDishName;
		this.iPrice = iPrice;
		this.order = order;
		this.orderEvaluate = orderEvaluate;
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

	public int getiMenuId() {
		return iMenuId;
	}
	public void setiMenuId(int iMenuId) {
		this.iMenuId = iMenuId;
	}
	public int getiShopId() {
		return iShopId;
	}
	public void setiShopId(int iShopId) {
		this.iShopId = iShopId;
	}
	public String getsClassName() {
		return sClassName;
	}
	public void setsClassName(String sClassName) {
		this.sClassName = sClassName;
	}
	public String getsDishName() {
		return sDishName;
	}
	public void setsDishName(String sDishName) {
		this.sDishName = sDishName;
	}
	public int getiPrice() {
		return iPrice;
	}
	public void setiPrice(int iPrice) {
		this.iPrice = iPrice;
	}
	
}
