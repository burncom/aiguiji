package dao;

import domain.User;

public interface UserDAO {
	public User get(int iUserId);
	
	public void update(User user);
	
	public void save(User user);
	
	//1:�˺�,2:����
	public boolean verifyUser(String sAccount,String sPassword,int type);
}
