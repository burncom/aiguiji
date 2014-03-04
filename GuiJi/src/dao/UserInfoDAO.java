package dao;

import java.util.List;

import domain.UserInfo;

public interface UserInfoDAO {
	/**
	 * ����������ȡ�û���Ϣʵ��
	 * @param user_id
	 * @return
	 */
	public	UserInfo	get(int user_id);
	/**
	 * �ҵ������û���Ϣʵ��
	 * @return
	 */
	public	List<UserInfo>	findAll();
	/**
	 * ͨ����������û�ʵ��
	 * @param email
	 * @return
	 */
	public  List<UserInfo>  findByEmail(String email);
	/**
	 * ���� �û����� �ҵ��û�ʵ��
	 * @param user_name
	 * @return
	 */
	public	List<UserInfo>	findByUserName(String user_name);
	/**
	 * �����û����ֺ������ȡ�û�ʵ��
	 * @param user_name
	 * @param user_password
	 * @return
	 */
	public	List<UserInfo>	findByEmailAndPass(String email,String user_password);
	/**
	 * ����ʵ���������ݿ�
	 * @param userInfo
	 */
	public	void	save(UserInfo userInfo);
	/**
	 * ����ʵ���������ݿ�
	 * @param userInfo
	 */
	public	void 	update(UserInfo userInfo);
	/**
	 * �����û�����ɾ��
	 * @param user_id
	 */
	public	void	delete(int user_id);
	/**
	 * ����ʵ��ɾ��
	 * @param userInfo
	 */
	public	void	delete(UserInfo userInfo);
}
