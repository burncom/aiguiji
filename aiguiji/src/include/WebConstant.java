package include;

public interface WebConstant {
	
	//订单状态
	int order_receive=1;//已接收订单
	int order_delivery=2;//正在派送
	int order_pay=3;//确认收到
	
	//账号,号码
	int user_account=1;
	int user_phone=2;
	
	//菜单评价获取根据菜单Id，个人Id
	int OrderEvaluate_menuId=1;
	int OrderEvaluate_userId=2;
	
	//商户评价根据商户Id，个人Id
	int ShopEvaluate_shopId=1;
	int ShopEvaluate_userId=2;
}
