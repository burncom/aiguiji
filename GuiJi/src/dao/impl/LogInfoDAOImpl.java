package dao.impl;

import hibernate3.support.GuiJiHibernateDaoSupport;

import java.util.List;

import dao.LogInfoDAO;
import domain.LogInfo;

public class LogInfoDAOImpl extends GuiJiHibernateDaoSupport implements LogInfoDAO {

	@Override
	public LogInfo get(int id) {
		return (LogInfo)getHibernateTemplate().get(LogInfo.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LogInfo> getRecentLog(int user_id, int operate) {
		return (List<LogInfo>)getHibernateTemplate().find("from LogInfo a where user_id =? and operate=? and " +
				"(a.id >= all(select max(b.id) from LogInfo b where user_id =? and operate=?))",
				user_id,operate,user_id,operate);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LogInfo> findAll(int id) {
		return (List<LogInfo>)getHibernateTemplate().find("from LogInfo where id>?",id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LogInfo> findByUserId(int user_id) {
		return (List<LogInfo>)getHibernateTemplate().find("from LogInfo where user_id=?",user_id);
	}

	@Override
	public void save(LogInfo logInfo) {
		getHibernateTemplate().save(logInfo);
	}

	@Override
	public void update(LogInfo logInfo) {
		getHibernateTemplate().update(logInfo);
	}

}
