package dao.impl;

import hibernate3.support.GuiJiHibernateDaoSupport;

import java.util.List;

import service.WebAlgo;
import dao.MessageInfoDAO;
import domain.MessageInfo;

public class MessageInfoDAOImpl extends GuiJiHibernateDaoSupport implements MessageInfoDAO {

	@Override
	public MessageInfo get(int msg_id) {
		return (MessageInfo)getHibernateTemplate().get(MessageInfo.class, msg_id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MessageInfo> findAll() {
		return (List<MessageInfo>)getHibernateTemplate().find("from MessageInfo where (type=1 or type=3 or type=4 or type=6)");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MessageInfo> findAllMood() {
		return (List<MessageInfo>)getHibernateTemplate().find("from MessageInfo where type=7");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MessageInfo> findAllMoodOfSameCity(String province, String city) {
		return (List<MessageInfo>)getHibernateTemplate().find("from MessageInfo where type=7 and province=? and city=?",
				province,city);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MessageInfo> findByUserId(int user_id) {
		return (List<MessageInfo>)getHibernateTemplate().find("from MessageInfo where user_id=? and (type=1 or type=3 or type=4 or type=6)",user_id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MessageInfo> findMoodByUserId(int user_id) {
		return (List<MessageInfo>)getHibernateTemplate().find("from MessageInfo where user_id=? and type=7",user_id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MessageInfo> findByPCD(String province, String city,
			String district) {
		return (List<MessageInfo>)getHibernateTemplate().find("from MessageInfo where province=? and city=? and district=? and (type=1 or type=3 or type=4 or type=6)",
				province,city,district);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MessageInfo> findByPC(String province, String city) {
//		String values[]=new String[2];
//		values[0]=province;
//		values[1]=city;
//		return (List<MessageInfo>) findByPage("from MessageInfo where province=? and city=? and (type=1 or type=3)", values, 0, 2);
		return (List<MessageInfo>)getHibernateTemplate().find("from MessageInfo where province=? and city=? and (type=1 or type=3 or type=6)",province,city);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MessageInfo> findByPCOfAct(String actprovince, String actcity) {
		// µœ÷∑÷“≥
//		String values[]=new String[2];
//		values[0]=actprovince;
//		values[1]=actcity;
//		return (List<MessageInfo>) findByPage("from MessageInfo where actprovince=? and actcity=? and (type=4 or type=6)", values, 0, 2);
		return (List<MessageInfo>)getHibernateTemplate().find("from MessageInfo where actprovince=? and actcity=? and type=4 ",actprovince,actcity);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MessageInfo> findByPCOfWillAct(String actprovince,
			String actcity, int datetime) {
		return (List<MessageInfo>)getHibernateTemplate().find("from MessageInfo where actprovince=? and actcity=? " +
				"and (type=4 or type=6) and (start_datetime >= ?)",actprovince,actcity,datetime);
	}

	@Override
	public void save(MessageInfo messageInfo) {
		getHibernateTemplate().save(messageInfo);
	}

	@Override
	public void update(MessageInfo messageInfo) {
		getHibernateTemplate().update(messageInfo);
	}

	@Override
	public void delete(int msg_id) {
		getHibernateTemplate().delete(get(msg_id));
	}

	@Override
	public void delete(MessageInfo messageInfo) {
		getHibernateTemplate().delete(messageInfo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MessageInfo> findByPCAndType(String province, String city,
			int chooseDayType) {
		WebAlgo algo=new WebAlgo();
		int startend[]=algo.getStartEndByDayType(chooseDayType);
		int start=startend[0];
		int end=startend[1];
		
		if(end==0)
			return (List<MessageInfo>)getHibernateTemplate().find("from MessageInfo where province=? and city=? and " +
					"(type=1 or type=3 ) and datetime=?",province,city,start);
		else
			return (List<MessageInfo>)getHibernateTemplate().find("from MessageInfo where province=? and city=? and " +
					"(type=1 or type=3 ) and datetime between ? and ?",province,city,start,end);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MessageInfo> findByPCAndTypeOfAct(String actprovince, String actcity,
			int chooseDayType) {
		WebAlgo algo=new WebAlgo();
		int startend[]=algo.getStartEndByDayType(chooseDayType);
		int start=startend[0];
		int end=startend[1];
		
		if(end==0)
			return (List<MessageInfo>)getHibernateTemplate().find("from MessageInfo where actprovince=? and actcity=? and " +
					"(type=4 or type=6) and datetime=?",actprovince,actcity,start);
		else
			return (List<MessageInfo>)getHibernateTemplate().find("from MessageInfo where actprovince=? and actcity=? and " +
					"(type=4 or type=6) and datetime between ? and ?",actprovince,actcity,start,end);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MessageInfo> findByPCDAndType(String province,String city,String district,int chooseDayType) {
		WebAlgo algo=new WebAlgo();
		int startend[]=algo.getStartEndByDayType(chooseDayType);
		int start=startend[0];
		int end=startend[1];
		
		if(end==0)
			return (List<MessageInfo>)getHibernateTemplate().find("from MessageInfo where province=? and city=? and " +
					"(type=1 or type=3 ) and district=? and  datetime=? ",province,city,district,start);
		else
			return (List<MessageInfo>)getHibernateTemplate().find("from MessageInfo where province=? and city=? and " +
					"(type=1 or type=3 ) and district=? and datetime between ? and ?",province,city,district,start,end);
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<MessageInfo> findByPCDAndTypeOfAct(String actprovince,
			String actcity, String actdistrict, int chooseDayType) {
		WebAlgo algo=new WebAlgo();
		int startend[]=algo.getStartEndByDayType(chooseDayType);
		int start=startend[0];
		int end=startend[1];
		
		if(end==0)
			return (List<MessageInfo>)getHibernateTemplate().find("from MessageInfo where actprovince=? and actcity=? and " +
					"( type=4 or type=6) and actdistrict=? and  datetime=? ",actprovince,actcity,actdistrict,start);
		else
			return (List<MessageInfo>)getHibernateTemplate().find("from MessageInfo where actprovince=? and actcity=? and " +
					"( type=4 or type=6) and actdistrict=? and datetime between ? and ?",actprovince,actcity,actdistrict,start,end);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MessageInfo> findByPCDOAndType(String province,String city,String district,String other,int chooseDayType) {
		WebAlgo algo=new WebAlgo();
		int startend[]=algo.getStartEndByDayType(chooseDayType);
		int start=startend[0];
		int end=startend[1];
		
		if(end==0)
			return (List<MessageInfo>)getHibernateTemplate().find("from MessageInfo where province=? and city=? and " +
					"(type=1 or type=3 ) and district=? and other=? and  datetime=? ",province,city,district,other,start);
		else
			return (List<MessageInfo>)getHibernateTemplate().find("from MessageInfo where province=? and city=? and " +
					"(type=1 or type=3 ) and district=? and other=? and datetime between ? and ?",province,city,district,other,start,end);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MessageInfo> findByPCDOAndTypeOfAct(String actprovince,
			String actcity, String actdistrict, String actother,
			int chooseDayType) {
		WebAlgo algo=new WebAlgo();
		int startend[]=algo.getStartEndByDayType(chooseDayType);
		int start=startend[0];
		int end=startend[1];
		
		if(end==0)
			return (List<MessageInfo>)getHibernateTemplate().find("from MessageInfo where actprovince=? and actcity=? and " +
					"( type=4 or type=6) and actdistrict=? and other=? and  datetime=? ",actprovince,actcity,actdistrict,actother,start);
		else
			return (List<MessageInfo>)getHibernateTemplate().find("from MessageInfo where actprovince=? and actcity=? and " +
					"(type=4 or type=6) and actdistrict=? and other=? and datetime between ? and ?",actprovince,actcity,actdistrict,actother,start,end);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MessageInfo> findAllByType(int category) {
		return (List<MessageInfo>)getHibernateTemplate().find("from MessageInfo where category=?",category);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MessageInfo> findAllByTypeForClassTopUser(int category) {
		return (List<MessageInfo>)getHibernateTemplate().find("from MessageInfo where category=? and (type=1 or type=3 or type=4 or type=6)",category);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MessageInfo> findRecentMood(int user_id) {
		return (List<MessageInfo>)getHibernateTemplate().find("from MessageInfo a where user_id =? and type=7 and " +
				"(a.datetime >= all(select max(b.datetime) from MessageInfo b where type=7))",user_id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MessageInfo> findRecentMsgOrAct(int user_id,int type) {
		return (List<MessageInfo>)getHibernateTemplate().find("from MessageInfo a where user_id =? and type=? and " +
				"(a.datetime >= all(select max(b.datetime) from MessageInfo b where (type=?) ))",user_id,type,type);
	}
	
	
}
