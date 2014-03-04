package dao;

import domain.Shop;

public interface ShopDAO {
	public Shop get(int iShopId);
	
	public void save(Shop shop);
	
	public void update(Shop shop);
}
