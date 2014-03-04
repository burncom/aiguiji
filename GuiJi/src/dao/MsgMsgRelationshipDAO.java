package dao;

import java.util.List;

import domain.MsgMsgRelationship;


public interface MsgMsgRelationshipDAO {
	/**
	 * 根据主键得到信息与信息关系的实例
	 * @param id
	 * @return
	 */
	public	MsgMsgRelationship	get(int id);
	/**
	 * 根据引用 消息用户序号 和 引用消息序号 得到实例
	 * @param reference_id
	 * @param refence_msg_id
	 * @return
	 */
	public	List<MsgMsgRelationship>	findByUserAndMsgAndType(int referenced_id,int referenced_msg_id,int type);
	/**
	 * 通过转发的信息找到原创信息
	 * @param reference_id
	 * @param type
	 * @return
	 */
	public  List<MsgMsgRelationship>	findOriginalMessage(int reference_msg_id,int type);
	/**
	 * 根据被引用信息的主键和操作类型 获取所有评论的 实例
	 * @param referenced_msg_id
	 * @param type
	 * @return
	 */
	public  List<MsgMsgRelationship>	findCommentOrTransmitMsgOrAct(int referenced_msg_id,int type);
	/**
	 * 保存实例进入数据库
	 * @param msgMsgRelationship
	 */
	public	void	save(MsgMsgRelationship msgMsgRelationship);
	/**
	 * 根据实例更新数据库
	 * @param msgMsgRelationship
	 */
	public	void 	update(MsgMsgRelationship msgMsgRelationship);
	/**
	 * 根据MsgMsgRelationship实例删除
	 * @param msgMsgRelationship
	 */
	public	void 	delete(MsgMsgRelationship msgMsgRelationship);
	/**
	 * 根据被引用信息主键删除，这样可以达到删除评论和转发的所有记录
	 * @param referenced_msg_id
	 * @param type
	 */
	public	void	delete(int referenced_msg_id);
	/**
	 * 根据被引用消息序号，和 操作类型 删除记录
	 * @param referenced_msg_id
	 * @param type
	 */
	public	void 	deleteByReferenced_Msg_IdAndType(int referenced_msg_id,int type);
	/**
	 * 根据引用消息序号  和 操作类型 删除记录
	 * @param reference_msg_id
	 * @param type
	 */
	public  void 	deleteByReference_Msg_IdAndType(int reference_msg_id,int type);
	/**
	 * 删除回复 信息
	 * @param reference_msg_id
	 */
	public	void 	deleteReply(int reference_msg_id);
}
