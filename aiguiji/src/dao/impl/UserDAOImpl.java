package dao.impl;

import include.AiGuiJiHibernateDaoSupport;
import include.WebConstant;

import java.util.List;


import dao.UserDAO;
import domain.User;

public class UserDAOImpl extends AiGuiJiHibernateDaoSupport implements UserDAO {

	@Override
	public User get(int iUserId) {
		return getHibernateTemplate().get(User.class,iUserId);
	}

	@Override
	
	public void update(User user) {
		getHibernateTemplate().update(user);
	}

	@Override
	public void save(User user) {
		getHibernateTemplate().save(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean verifyUser(String sAccount, String sPassword, int type) {
		List<User> userList;
		if(type==WebConstant.user_account)
		{
			userList= (List<User>)getHibernateTemplate().find("from User where sAccount=? and sPassWord=?",sAccount,sPassword);
		}
		else
		{
			userList= (List<User>)getHibernateTemplate().find("from User where sPhone=? and sPassWord=?",sAccount,sPassword);
		}
		
		if(userList.size()==1){
			return true;
		}else
		{
			return false;
		}
	}

}
