package dao.impl;

import include.AiGuiJiHibernateDaoSupport;

import java.util.List;

import dao.OrderDAO;
import domain.Order;

public class OrderDAOImpl extends AiGuiJiHibernateDaoSupport implements OrderDAO {

	@Override
	public Order get(int iOrderId) {
		return getHibernateTemplate().get(Order.class,iOrderId);
	}

	@Override
	public void save(Order order) {
		getHibernateTemplate().save(order);
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Order> getOrderList(int iUserId) {
//		return (List<Order>)getHibernateTemplate().find("from Order where iUserId=?",iUserId);
//	}

	@Override
	public List<?> findByPage(String hql, int iUserId, int offset, int pageSize) {
		return super.findByPage("from Order where iUserId=?", iUserId, offset, pageSize);
	}
	
	
}
