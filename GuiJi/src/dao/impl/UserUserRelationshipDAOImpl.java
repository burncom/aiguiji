package dao.impl;

import hibernate3.support.GuiJiHibernateDaoSupport;

import java.util.List;

import dao.UserUserRelationshipDAO;
import domain.UserUserRelationship;

public class UserUserRelationshipDAOImpl extends GuiJiHibernateDaoSupport implements
		UserUserRelationshipDAO {

	@Override
	public UserUserRelationship get(int id) {
		return (UserUserRelationship)getHibernateTemplate().get(UserUserRelationship.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserUserRelationship> findByUserIdAndType(int user_id,int type) {
		return (List<UserUserRelationship>)getHibernateTemplate().find("from UserUserRelationship where user_id=? and type=?",
				user_id,type);
	}

	@Override
	public void save(UserUserRelationship userUserRelationship) {
		getHibernateTemplate().save(userUserRelationship);
	}

	@Override
	public void update(UserUserRelationship userUserRelationship) {
		getHibernateTemplate().update(userUserRelationship);
	}

	@Override
	public void delete(int id) {
		getHibernateTemplate().delete(get(id));
	}

	@Override
	public void delete(UserUserRelationship userUserRelationship) {
		getHibernateTemplate().delete(userUserRelationship);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserUserRelationship> findByUserIdAndTypeOfFollower(int user_id) {
		return (List<UserUserRelationship>)getHibernateTemplate().find("from UserUserRelationship where user_id=? and type= 2 ",
				user_id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserUserRelationship> findByUserIdAndTypeOfFans(int user_id) {
		return (List<UserUserRelationship>)getHibernateTemplate().find("from UserUserRelationship where user_id=? and type= 1 ",
				user_id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserUserRelationship> findByUserIdFollowerFans(int user_id) {
		return (List<UserUserRelationship>)getHibernateTemplate().find("from UserUserRelationship where user_id=? ",
				user_id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserUserRelationship> getStatusByUsersId(int user_id,
			int follower_id) {
		return (List<UserUserRelationship>)getHibernateTemplate().find("from UserUserRelationship where user_id=? and follower_id= ? ",
				user_id,follower_id);
	}
	
}
