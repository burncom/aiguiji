package dao;

import java.util.List;
import domain.LogInfo;


public interface LogInfoDAO {
	/**
	 * ͨ����־��Ϣ��������ȡʵ��
	 * @param id
	 * @return
	 */
	public	LogInfo	get(int id);
	/**
	 * �����û������ҵ��������Ϣ
	 * @param user_id
	 * @return
	 */
	public List<LogInfo> getRecentLog(int user_id,int operate);
	/**
	 * ������־��Ϣ�����ҵ����е���־��Ϣʵ��
	 * @return
	 */
	public	List<LogInfo>	findAll(int id);
	/**
	 * �����û������ҵ���Ӧ��־ʵ��
	 * @param user_id
	 * @return
	 */
	public	List<LogInfo>	findByUserId(int user_id);
	/**
	 * ����ʵ���������ݿ�
	 * @param logInfo
	 */
	public	void	save(LogInfo logInfo);
	/**
	 * ����ʵ���������ݿ�
	 * @param logInfo
	 */
	public	void 	update(LogInfo logInfo);
}
