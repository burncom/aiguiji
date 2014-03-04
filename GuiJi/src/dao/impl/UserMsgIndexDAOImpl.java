package dao.impl;

import hibernate3.support.GuiJiHibernateDaoSupport;

import java.util.List;

import dao.UserMsgIndexDAO;
import domain.UserMsgIndex;

public class UserMsgIndexDAOImpl extends GuiJiHibernateDaoSupport implements
		UserMsgIndexDAO {

	@Override
	public UserMsgIndex get(int id) {
		return (UserMsgIndex)getHibernateTemplate().get(UserMsgIndex.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserMsgIndex> findByUserId(int user_id) {
		return (List<UserMsgIndex>)getHibernateTemplate().find("from UserMsgIndex where user_id=? ",user_id);
	}

	@Override
	public void save(UserMsgIndex userMsgIndex) {
		getHibernateTemplate().save(userMsgIndex);
	}

	@Override
	public void update(UserMsgIndex userMsgIndex) {
		getHibernateTemplate().update(userMsgIndex);
	}
	

	@Override
	public void delete(UserMsgIndex userMsgIndex) {
		getHibernateTemplate().delete(userMsgIndex);
	}

	@Override
	public void delete(int author_id, int msg_id) {
		@SuppressWarnings("unchecked")
		List<UserMsgIndex> userMsgIndex=getHibernateTemplate().find("from UserMsgIndex where author_id=? and msg_id=? ",
				author_id,msg_id);
		if(userMsgIndex.size()!=0)
			getHibernateTemplate().deleteAll(userMsgIndex);
	}

	@Override
	public void deleteByMsg_Id(int msg_id) {
		@SuppressWarnings("unchecked")
		List<UserMsgIndex> userMsgIndex=getHibernateTemplate().find("from UserMsgIndex where msg_id=?",msg_id);
		getHibernateTemplate().deleteAll(userMsgIndex);
	}
	
	
}
