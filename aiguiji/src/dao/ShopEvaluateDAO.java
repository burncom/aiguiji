package dao;

import java.util.List;

import domain.ShopEvaluate;

public interface ShopEvaluateDAO {
	public ShopEvaluate get(int iEvaluateId);
	
	public void save(ShopEvaluate shopEvaluate);
	
	//商户评价根据商户Id，个人Id
//	public List<ShopEvaluate> getShopEvaluateList(int id,int type);
	
	public List<?> findByPage(String hql, int[] values, int offset, int pageSize);
}
