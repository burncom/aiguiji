package dao;

import java.util.List;

import domain.UserMsgIndex;


public interface UserMsgIndexDAO {
	/**
	 * �����û�����Ϣ�����������õ�ʵ��
	 * @param id
	 * @return
	 */
	public	UserMsgIndex	get(int id);
	/**
	 * �����û�������ȡ��Ҫ�û�����Ϣ������ʵ��
	 * @param user_id
	 * @return
	 */
	public	List<UserMsgIndex>	findByUserId(int user_id);
	/**
	 * ����ʵ�����������ݿ�
	 * @param userMsgIndex
	 */
	public	void	save(UserMsgIndex userMsgIndex);
	/**
	 * ����ʵ���������ݿ�
	 * @param userMsgIndex
	 */
	public	void 	update(UserMsgIndex userMsgIndex);
	/**
	 * ͨ����Ϣ��������� �� ��Ϣ��� ɾ��������user_msg_index��
	 * @param author_id
	 * @param msg_id
	 */
	public 	void 	delete(int author_id, int msg_id);
	/**
	 * ͨ����Ϣ ���� ɾ�� ��¼
	 * @param msg_id
	 */
	public	void 	deleteByMsg_Id(int msg_id);
	/**
	 * ͨ��UserMsgIndexɾ������
	 * @param userMsgIndex
	 */
	public	void 	delete(UserMsgIndex userMsgIndex);
}
