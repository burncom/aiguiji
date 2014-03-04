package dao;

import java.util.List;
import domain.LogInfo;


public interface LogInfoDAO {
	/**
	 * 通过日志信息表主键获取实例
	 * @param id
	 * @return
	 */
	public	LogInfo	get(int id);
	/**
	 * 根据用户主键找到最近的信息
	 * @param user_id
	 * @return
	 */
	public List<LogInfo> getRecentLog(int user_id,int operate);
	/**
	 * 根据日志信息主键找到所有的日志信息实例
	 * @return
	 */
	public	List<LogInfo>	findAll(int id);
	/**
	 * 根据用户主键找到相应日志实例
	 * @param user_id
	 * @return
	 */
	public	List<LogInfo>	findByUserId(int user_id);
	/**
	 * 保存实例进入数据库
	 * @param logInfo
	 */
	public	void	save(LogInfo logInfo);
	/**
	 * 根据实例更新数据库
	 * @param logInfo
	 */
	public	void 	update(LogInfo logInfo);
}
