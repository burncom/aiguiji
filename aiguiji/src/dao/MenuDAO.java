package dao;

import java.util.List;

import domain.Menu;

public interface MenuDAO {
	public Menu get(int iMenuId);
	
	public void save(Menu menu);
	
	public void update(Menu menu);
	
//	public List<Menu> getMenuList(int iShopId);
	
	public List<?> findByPage(String hql, int iShopId, int offset, int pageSize);
}
