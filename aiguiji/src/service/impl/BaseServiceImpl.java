package service.impl;

import dao.MenuDAO;
import dao.OrderDAO;
import dao.OrderEvaluateDAO;
import dao.ShopDAO;
import dao.ShopEvaluateDAO;
import dao.ShopPicDAO;
import dao.UserDAO;
import service.BaseService;

public class BaseServiceImpl implements BaseService {
	private MenuDAO menuDAO;
	private OrderDAO orderDAO;
	private OrderEvaluateDAO orderEvaluateDAO;
	private ShopDAO shopDAO;
	private ShopEvaluateDAO shopEvaluateDAO;
	private ShopPicDAO shopPicDAO;
	private UserDAO userDAO;
	
	@Override
	public boolean verifyUser(String sAccount, String sPassword, int type) {
		return userDAO.verifyUser(sAccount, sPassword, type);
	}

	public MenuDAO getMenuDAO() {
		return menuDAO;
	}

	public void setMenuDAO(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}

	public OrderDAO getOrderDAO() {
		return orderDAO;
	}

	public void setOrderDAO(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}

	public OrderEvaluateDAO getOrderEvaluateDAO() {
		return orderEvaluateDAO;
	}

	public void setOrderEvaluateDAO(OrderEvaluateDAO orderEvaluateDAO) {
		this.orderEvaluateDAO = orderEvaluateDAO;
	}

	public ShopDAO getShopDAO() {
		return shopDAO;
	}

	public void setShopDAO(ShopDAO shopDAO) {
		this.shopDAO = shopDAO;
	}

	public ShopEvaluateDAO getShopEvaluateDAO() {
		return shopEvaluateDAO;
	}

	public void setShopEvaluateDAO(ShopEvaluateDAO shopEvaluateDAO) {
		this.shopEvaluateDAO = shopEvaluateDAO;
	}

	public ShopPicDAO getShopPicDAO() {
		return shopPicDAO;
	}

	public void setShopPicDAO(ShopPicDAO shopPicDAO) {
		this.shopPicDAO = shopPicDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
}
