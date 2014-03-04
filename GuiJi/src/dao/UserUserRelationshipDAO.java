package dao;

import java.util.List;

import domain.UserUserRelationship;


public interface UserUserRelationshipDAO {
	/**
	 * 根据用户与用户关系主键获取相应实例
	 * @param id
	 * @return
	 */
	public	UserUserRelationship	get(int id);
	/**
	 * 根据用户主键找到所有的实例
	 * @param user_id
	 * @return
	 */
	public	List<UserUserRelationship>	findByUserIdAndType(int user_id,int type);
	/**
	 * 根据用户主键找到所有的  关注者
	 * @param user_id
	 * @return
	 */
	public	List<UserUserRelationship>	findByUserIdAndTypeOfFollower(int user_id);
	/**
	 * 根据用户主键找到所有的  粉丝
	 * @param user_id
	 * @return
	 */
	public	List<UserUserRelationship>	findByUserIdAndTypeOfFans(int user_id);
	/**
	 * 通过当前用户主键找到所有与此有关系的用户
	 * @param user_id
	 * @return
	 */
	public 	List<UserUserRelationship>	findByUserIdFollowerFans(int user_id);
	/**
	 *根据实例保存入数据库
	 * @param userUserRelationship
	 */
	public	void	save(UserUserRelationship userUserRelationship);
	/**
	 * 根据实例更新数据库
	 * @param userUserRelationship
	 */
	public	void 	update(UserUserRelationship userUserRelationship);
	/**
	 * 根据主键删除
	 * @param id
	 */
	public	void 	delete(int id);
	/**
	 * 根据实例删除
	 * @param userUserRelationship
	 */
	public	void 	delete(UserUserRelationship userUserRelationship);
	/**
	 * 通过用户序号 和 被关注者序号 的用户序号 得到两者之间的关系状态
	 * @param user_id
	 * @param follower_id
	 * @return
	 */
	public	List<UserUserRelationship> getStatusByUsersId(int user_id,int follower_id);
}
