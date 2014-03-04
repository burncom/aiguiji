package dao.impl;

import include.AiGuiJiHibernateDaoSupport;
import include.WebConstant;

import java.util.List;

import dao.OrderEvaluateDAO;
import domain.OrderEvaluate;

public class OrderEvaluateDAOImpl extends AiGuiJiHibernateDaoSupport implements
		OrderEvaluateDAO {

	@Override
	public OrderEvaluate get(int iEvaluateId) {
		return getHibernateTemplate().get(OrderEvaluate.class, iEvaluateId);
	}

	@Override
	public void save(OrderEvaluate orderEvaluate) {
		getHibernateTemplate().save(orderEvaluate);
	}

	@Override
	public void delete(int iEvaluateId) {
		getHibernateTemplate().delete(get(iEvaluateId));
	}

	@Override
	public void delete(OrderEvaluate orderEvaluate) {
		getHibernateTemplate().delete(orderEvaluate);
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<OrderEvaluate> getOrderEvaluateList(int id, int type) {
//		List<OrderEvaluate> orderEvaluates;
//		if(type==WebConstant.OrderEvaluate_menuId){
//			orderEvaluates=(List<OrderEvaluate>)getHibernateTemplate().find("from OrderEvaluate where iMenuId=?",id);
//		}
//		else{
//			orderEvaluates=(List<OrderEvaluate>)getHibernateTemplate().find("from OrderEvaluate where iUserId=?",id);
//		}
//		return orderEvaluates;
//	}

	@Override
	public List<?> findByPage(String hql, int[] values, int offset,int pageSize) {
		if(values[0]==WebConstant.OrderEvaluate_menuId){
			return super.findByPage("from OrderEvaluate where iMenuId=?", values[1], offset, pageSize);
		}else{
			return super.findByPage("from OrderEvaluate where iUserId=?", values[1], offset, pageSize); 
		}
		
	}

	
	
}
