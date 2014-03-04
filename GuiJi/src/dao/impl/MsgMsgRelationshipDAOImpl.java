package dao.impl;

import hibernate3.support.GuiJiHibernateDaoSupport;

import java.util.List;

import dao.MsgMsgRelationshipDAO;
import domain.MsgMsgRelationship;

public class MsgMsgRelationshipDAOImpl extends GuiJiHibernateDaoSupport implements MsgMsgRelationshipDAO {

	@Override
	public MsgMsgRelationship get(int id) {
		return (MsgMsgRelationship)getHibernateTemplate().get(MsgMsgRelationship.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MsgMsgRelationship> findByUserAndMsgAndType(int referenced_id,
			int referenced_msg_id,int type) {
		return (List<MsgMsgRelationship>)getHibernateTemplate().find("from MsgMsgRelationship where referenced_id=? and referenced_msg_id=? and type=?",
				referenced_id,referenced_msg_id,type);
	}

	@Override
	public void save(MsgMsgRelationship msgMsgRelationship) {
		getHibernateTemplate().save(msgMsgRelationship);
	}

	@Override
	public void update(MsgMsgRelationship msgMsgRelationship) {
		getHibernateTemplate().update(msgMsgRelationship);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MsgMsgRelationship> findOriginalMessage(int reference_msg_id, int type) {
		return (List<MsgMsgRelationship>) getHibernateTemplate().find("from MsgMsgRelationship where reference_msg_id=? and type=? ",reference_msg_id,type);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MsgMsgRelationship> findCommentOrTransmitMsgOrAct(int referenced_msg_id,
			int type) {
		return (List<MsgMsgRelationship>)getHibernateTemplate().find("from MsgMsgRelationship where referenced_msg_id=? and type=?",
				referenced_msg_id,type);
	}

	@Override
	public void delete(MsgMsgRelationship msgMsgRelationship) {
		getHibernateTemplate().delete(msgMsgRelationship);
	}

	@Override
	public void delete(int referenced_msg_id) {
		@SuppressWarnings("unchecked")
		List<MsgMsgRelationship> msgMsgRelationship=getHibernateTemplate().find("from MsgMsgRelationship where referenced_msg_id=?",
				referenced_msg_id);
		getHibernateTemplate().deleteAll(msgMsgRelationship);
	}
	
	@Override
	public void deleteByReferenced_Msg_IdAndType(int referenced_msg_id, int type) {
		@SuppressWarnings("unchecked")
		List<MsgMsgRelationship> msgMsgRelationship=getHibernateTemplate().find("from MsgMsgRelationship where referenced_msg_id=? and type=?",
				referenced_msg_id,type);
		getHibernateTemplate().deleteAll(msgMsgRelationship);
	}
	
	@Override
	public void deleteByReference_Msg_IdAndType(int reference_msg_id, int type) {
		@SuppressWarnings("unchecked")
		List<MsgMsgRelationship> msgMsgRelationship=getHibernateTemplate().find("from MsgMsgRelationship where reference_msg_id=? and type=?",
				reference_msg_id,type);
		getHibernateTemplate().deleteAll(msgMsgRelationship);
	}

	@Override
	public void deleteReply(int reference_msg_id) {
		@SuppressWarnings("unchecked")
		List<MsgMsgRelationship> msgMsgRelationship=getHibernateTemplate().find("from MsgMsgRelationship where reference_msg_id=?",
				reference_msg_id);
		getHibernateTemplate().deleteAll(msgMsgRelationship);
	}

	
}
