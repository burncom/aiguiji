package dao.impl;

import include.AiGuiJiHibernateDaoSupport;
import dao.ShopDAO;
import domain.Shop;

public class ShopDAOImpl extends AiGuiJiHibernateDaoSupport implements ShopDAO {

	@Override
	public Shop get(int iShopId) {
		return getHibernateTemplate().get(Shop.class, iShopId);
	}

	@Override
	public void save(Shop shop) {
		getHibernateTemplate().save(shop);
	}

	@Override
	public void update(Shop shop) {
		getHibernateTemplate().update(shop);
	}

}
