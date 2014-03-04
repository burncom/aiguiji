package dao.impl;

import hibernate3.support.GuiJiHibernateDaoSupport;

import java.util.List;

import dao.UserInfoDAO;
import domain.UserInfo;

public class UserInfoDAOImpl extends GuiJiHibernateDaoSupport implements UserInfoDAO {

	@Override
	public UserInfo get(int user_id) {
		return getHibernateTemplate().get(UserInfo.class,user_id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfo> findAll() {
		return (List<UserInfo>)getHibernateTemplate().find("from UserInfo");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfo> findByEmail(String email) {
		return (List<UserInfo>)getHibernateTemplate().find("from UserInfo where email=?",email);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfo> findByUserName(String user_name) {
		return (List<UserInfo>)getHibernateTemplate().find("from UserInfo where user_name=?",user_name);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfo> findByEmailAndPass(String email, String user_password) {
		return (List<UserInfo>)getHibernateTemplate().find("from UserInfo where email=? and user_password=?",email,user_password);
	}

	@Override
	public void save(UserInfo userInfo) {
		getHibernateTemplate().save(userInfo);
	}

	@Override
	public void update(UserInfo userInfo) {
		getHibernateTemplate().update(userInfo);
	}

	@Override
	public void delete(int user_id) {
		getHibernateTemplate().delete(get(user_id));
	}

	@Override
	public void delete(UserInfo userInfo) {
		getHibernateTemplate().delete(userInfo);
	}

}
