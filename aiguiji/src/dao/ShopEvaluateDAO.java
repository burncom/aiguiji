package dao;

import java.util.List;

import domain.ShopEvaluate;

public interface ShopEvaluateDAO {
	public ShopEvaluate get(int iEvaluateId);
	
	public void save(ShopEvaluate shopEvaluate);
	
	//�̻����۸����̻�Id������Id
//	public List<ShopEvaluate> getShopEvaluateList(int id,int type);
	
	public List<?> findByPage(String hql, int[] values, int offset, int pageSize);
}
