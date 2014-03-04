package dao;

import java.util.List;

import domain.UserUserRelationship;


public interface UserUserRelationshipDAO {
	/**
	 * �����û����û���ϵ������ȡ��Ӧʵ��
	 * @param id
	 * @return
	 */
	public	UserUserRelationship	get(int id);
	/**
	 * �����û������ҵ����е�ʵ��
	 * @param user_id
	 * @return
	 */
	public	List<UserUserRelationship>	findByUserIdAndType(int user_id,int type);
	/**
	 * �����û������ҵ����е�  ��ע��
	 * @param user_id
	 * @return
	 */
	public	List<UserUserRelationship>	findByUserIdAndTypeOfFollower(int user_id);
	/**
	 * �����û������ҵ����е�  ��˿
	 * @param user_id
	 * @return
	 */
	public	List<UserUserRelationship>	findByUserIdAndTypeOfFans(int user_id);
	/**
	 * ͨ����ǰ�û������ҵ���������й�ϵ���û�
	 * @param user_id
	 * @return
	 */
	public 	List<UserUserRelationship>	findByUserIdFollowerFans(int user_id);
	/**
	 *����ʵ�����������ݿ�
	 * @param userUserRelationship
	 */
	public	void	save(UserUserRelationship userUserRelationship);
	/**
	 * ����ʵ���������ݿ�
	 * @param userUserRelationship
	 */
	public	void 	update(UserUserRelationship userUserRelationship);
	/**
	 * ��������ɾ��
	 * @param id
	 */
	public	void 	delete(int id);
	/**
	 * ����ʵ��ɾ��
	 * @param userUserRelationship
	 */
	public	void 	delete(UserUserRelationship userUserRelationship);
	/**
	 * ͨ���û���� �� ����ע����� ���û���� �õ�����֮��Ĺ�ϵ״̬
	 * @param user_id
	 * @param follower_id
	 * @return
	 */
	public	List<UserUserRelationship> getStatusByUsersId(int user_id,int follower_id);
}
