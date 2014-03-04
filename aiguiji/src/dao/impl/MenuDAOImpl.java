package dao.impl;

import include.AiGuiJiHibernateDaoSupport;

import java.util.List;

import dao.MenuDAO;
import domain.Menu;

public class MenuDAOImpl extends AiGuiJiHibernateDaoSupport implements MenuDAO {

	@Override
	public Menu get(int iMenuId) {
		return getHibernateTemplate().get(Menu.class, iMenuId);
	}

	@Override
	public void save(Menu menu) {
		getHibernateTemplate().save(menu);
	}

	@Override
	public void update(Menu menu) {
		getHibernateTemplate().update(menu);
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Menu> getMenuList(int iShopId) {
//		return (List<Menu>)getHibernateTemplate().find("from Menu where iShopId=?",iShopId);
//	}

	@Override
	public List<?> findByPage(String hql, int iShopId, int offset, int pageSize) {
		return super.findByPage("from Menu where iShopId=?", iShopId, offset, pageSize);
	}
	
}
