package dao;

import java.util.List;

import domain.MsgMsgRelationship;


public interface MsgMsgRelationshipDAO {
	/**
	 * ���������õ���Ϣ����Ϣ��ϵ��ʵ��
	 * @param id
	 * @return
	 */
	public	MsgMsgRelationship	get(int id);
	/**
	 * �������� ��Ϣ�û���� �� ������Ϣ��� �õ�ʵ��
	 * @param reference_id
	 * @param refence_msg_id
	 * @return
	 */
	public	List<MsgMsgRelationship>	findByUserAndMsgAndType(int referenced_id,int referenced_msg_id,int type);
	/**
	 * ͨ��ת������Ϣ�ҵ�ԭ����Ϣ
	 * @param reference_id
	 * @param type
	 * @return
	 */
	public  List<MsgMsgRelationship>	findOriginalMessage(int reference_msg_id,int type);
	/**
	 * ���ݱ�������Ϣ�������Ͳ������� ��ȡ�������۵� ʵ��
	 * @param referenced_msg_id
	 * @param type
	 * @return
	 */
	public  List<MsgMsgRelationship>	findCommentOrTransmitMsgOrAct(int referenced_msg_id,int type);
	/**
	 * ����ʵ���������ݿ�
	 * @param msgMsgRelationship
	 */
	public	void	save(MsgMsgRelationship msgMsgRelationship);
	/**
	 * ����ʵ���������ݿ�
	 * @param msgMsgRelationship
	 */
	public	void 	update(MsgMsgRelationship msgMsgRelationship);
	/**
	 * ����MsgMsgRelationshipʵ��ɾ��
	 * @param msgMsgRelationship
	 */
	public	void 	delete(MsgMsgRelationship msgMsgRelationship);
	/**
	 * ���ݱ�������Ϣ����ɾ�����������Դﵽɾ�����ۺ�ת�������м�¼
	 * @param referenced_msg_id
	 * @param type
	 */
	public	void	delete(int referenced_msg_id);
	/**
	 * ���ݱ�������Ϣ��ţ��� �������� ɾ����¼
	 * @param referenced_msg_id
	 * @param type
	 */
	public	void 	deleteByReferenced_Msg_IdAndType(int referenced_msg_id,int type);
	/**
	 * ����������Ϣ���  �� �������� ɾ����¼
	 * @param reference_msg_id
	 * @param type
	 */
	public  void 	deleteByReference_Msg_IdAndType(int reference_msg_id,int type);
	/**
	 * ɾ���ظ� ��Ϣ
	 * @param reference_msg_id
	 */
	public	void 	deleteReply(int reference_msg_id);
}
