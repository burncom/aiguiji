package dao;

import domain.User;

public interface UserDAO {
	public User get(int iUserId);
	
	public void update(User user);
	
	public void save(User user);
	
	//1:ÕËºÅ,2:ºÅÂë
	public boolean verifyUser(String sAccount,String sPassword,int type);
}
