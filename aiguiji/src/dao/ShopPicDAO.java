package dao;

import java.util.List;

import domain.ShopPic;

public interface ShopPicDAO {
	public ShopPic get(int iPicId);
	
	public void save(ShopPic shopPic);
	
	public void delete(ShopPic shopPic);
	
	public void delete(int iPicId);
	
//	public List<ShopPic> getShopPicList(int iShopId);
	
	public List<?> findByPage(String hql, int iShopId, int offset, int pageSize);
}
