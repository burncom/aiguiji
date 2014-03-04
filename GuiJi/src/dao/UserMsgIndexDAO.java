package dao;

import java.util.List;

import domain.UserMsgIndex;


public interface UserMsgIndexDAO {
	/**
	 * 根据用户与信息索引表主键得到实例
	 * @param id
	 * @return
	 */
	public	UserMsgIndex	get(int id);
	/**
	 * 根据用户主键获取想要用户与信息索引表实例
	 * @param user_id
	 * @return
	 */
	public	List<UserMsgIndex>	findByUserId(int user_id);
	/**
	 * 根据实例保存入数据库
	 * @param userMsgIndex
	 */
	public	void	save(UserMsgIndex userMsgIndex);
	/**
	 * 根据实例更新数据库
	 * @param userMsgIndex
	 */
	public	void 	update(UserMsgIndex userMsgIndex);
	/**
	 * 通过消息发布者序号 和 消息序号 删除，清理user_msg_index表
	 * @param author_id
	 * @param msg_id
	 */
	public 	void 	delete(int author_id, int msg_id);
	/**
	 * 通过信息 主键 删除 记录
	 * @param msg_id
	 */
	public	void 	deleteByMsg_Id(int msg_id);
	/**
	 * 通过UserMsgIndex删除数据
	 * @param userMsgIndex
	 */
	public	void 	delete(UserMsgIndex userMsgIndex);
}
