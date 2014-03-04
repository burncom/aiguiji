package dao;

import java.util.List;

import domain.UserInfo;

public interface UserInfoDAO {
	/**
	 * 根据主键获取用户信息实例
	 * @param user_id
	 * @return
	 */
	public	UserInfo	get(int user_id);
	/**
	 * 找到所有用户信息实例
	 * @return
	 */
	public	List<UserInfo>	findAll();
	/**
	 * 通过邮箱查找用户实例
	 * @param email
	 * @return
	 */
	public  List<UserInfo>  findByEmail(String email);
	/**
	 * 根据 用户名字 找到用户实例
	 * @param user_name
	 * @return
	 */
	public	List<UserInfo>	findByUserName(String user_name);
	/**
	 * 根据用户名字和密码获取用户实例
	 * @param user_name
	 * @param user_password
	 * @return
	 */
	public	List<UserInfo>	findByEmailAndPass(String email,String user_password);
	/**
	 * 保存实例进入数据库
	 * @param userInfo
	 */
	public	void	save(UserInfo userInfo);
	/**
	 * 根据实例更新数据库
	 * @param userInfo
	 */
	public	void 	update(UserInfo userInfo);
	/**
	 * 根据用户主键删除
	 * @param user_id
	 */
	public	void	delete(int user_id);
	/**
	 * 根据实例删除
	 * @param userInfo
	 */
	public	void	delete(UserInfo userInfo);
}
