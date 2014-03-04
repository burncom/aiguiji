package dao;

import java.util.List;

import domain.Order;

public interface OrderDAO {
	public Order get(int iOrderId);
	
	public void save(Order order);
	
//	public List<Order> getOrderList(int iUserId);
	
	public List<?> findByPage(String hql, int iUserId, int offset, int pageSize);
}
