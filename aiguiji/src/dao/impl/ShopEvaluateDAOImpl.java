package dao.impl;

import include.AiGuiJiHibernateDaoSupport;
import include.WebConstant;

import java.util.List;

import dao.ShopEvaluateDAO;
import domain.ShopEvaluate;

public class ShopEvaluateDAOImpl extends AiGuiJiHibernateDaoSupport implements
		ShopEvaluateDAO {

	@Override
	public ShopEvaluate get(int iEvaluateId) {
		return getHibernateTemplate().get(ShopEvaluate.class, iEvaluateId);
	}

	@Override
	public void save(ShopEvaluate shopEvaluate) {
		getHibernateTemplate().save(shopEvaluate);
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<ShopEvaluate> getShopEvaluateList(int id,int type) {
//		List<ShopEvaluate> shopEvaluates;
//		if(type==WebConstant.ShopEvaluate_shopId){
//			shopEvaluates=(List<ShopEvaluate>)getHibernateTemplate().find("from ShopEvaluate where iShopId=?",id);
//		}else{
//			shopEvaluates=(List<ShopEvaluate>)getHibernateTemplate().find("from ShopEvaluate where iUserId=?",id);
//		}
//		return shopEvaluates;
//	}

	@Override
	public List<?> findByPage(String hql, int[] values, int offset, int pageSize) {
		if(values[0]==WebConstant.ShopEvaluate_shopId){
			return super.findByPage("from ShopEvaluate where iShopId=?", values[1], offset, pageSize);
		}else{
			return super.findByPage("from ShopEvaluate where iUserId=?", values[1], offset, pageSize);
		}
		
	}
	
	

}
