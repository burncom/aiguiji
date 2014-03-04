package dao;

import java.util.List;

import domain.OrderEvaluate;

public interface OrderEvaluateDAO {
	public OrderEvaluate get(int iEvaluateId);
	
	public void save(OrderEvaluate orderEvaluate);
	
	public void delete(int iEvaluateId);
	
	public void delete(OrderEvaluate orderEvaluate);
	
	////�˵����ۻ�ȡ���ݲ˵�Id������Id
//	public List<OrderEvaluate> getOrderEvaluateList(int id,int type);
	
	public List<?> findByPage(String hql, int[] values, int offset,int pageSize);
}
