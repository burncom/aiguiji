package dao;

import java.util.List;

import domain.MessageInfo;

public interface MessageInfoDAO {
	/**
	 * ������ϢԪ���ݱ������õ���Ϣʵ��
	 * @param msg_id
	 * @return
	 */
	public	MessageInfo	get(int msg_id);
	/**
	 * ��ȡ���е���Ϣʵ��,������Ϣԭ������Ϣת�����ԭ�����ת��
	 * @return
	 */
	public	List<MessageInfo>	findAll();
	/**
	 * ��ȡ���е�������Ϣʵ��,����ԭ��
	 * @return
	 */
	public  List<MessageInfo>	findAllMood();
	/**
	 * ����ͬ���ҵ���ǰ���鶯̬
	 * @param province
	 * @param city
	 * @return
	 */
	public  List<MessageInfo>	findAllMoodOfSameCity(String province,String city);
	/**
	 * �����û�������ȡ��Ϣʵ��
	 * @param user_id
	 * @return
	 */
	public	List<MessageInfo>	findByUserId(int user_id);
	/**
	 * �����û�������ȡ����ʵ��
	 * @param user_id
	 * @return
	 */
	public  List<MessageInfo>   findMoodByUserId(int user_id);
	/**
	 * ����ʡ�ݣ����У�����/�� �ҵ����е���Ϣʵ��
	 * ֻҪԭ����Ϣ����ת����Ϣ��������Ϣ����Ҫ
	 * @param province
	 * @param city
	 * @param district
	 * @return
	 */
	public  List<MessageInfo>	findByPCD(String province,String city,String district);
	/**
	 * ����ʡ�ݣ�����  �ҵ����е� ��Ϣ ʵ��
	 * @param province
	 * @param city
	 * @return
	 */
	public  List<MessageInfo>	findByPC(String province,String city);
	/**
	 * ����ʡ�ݣ�����  �ҵ����е� � ʵ��
	 * @param province
	 * @param city
	 * @return
	 */
	public 	List<MessageInfo>	findByPCOfAct(String actprovince, String actcity);
	/**
	 * ����ʡ�ݣ����� ������ �������ʱ�� > Ŀǰʱ��  �ҵ����е� � ʵ��
	 * @param province
	 * @param city
	 * @return
	 */
	public  List<MessageInfo>	findByPCOfWillAct(String actprovince, String actcity,int datetime);
	/**
	 * ����ʡ�ݣ����� ��ѡ���ʱ������  �ҵ����е���Ϣʵ��
	 * @param province
	 * @param city
	 * @param chooseDayType
	 * @return
	 */
	public  List<MessageInfo>	findByPCAndType(String province,String city,int chooseDayType);
	/**
	 * ����ʡ�ݣ����� ��ѡ���ʱ������  �ҵ����е�  � ʵ��
	 * @param province
	 * @param city
	 * @param chooseDayType
	 * @return
	 */
	public  List<MessageInfo>	findByPCAndTypeOfAct(String actprovince,String actcity,int chooseDayType);
	/**
	 * ����ʡ�ݣ����� ������ѡ���ʱ������  �ҵ����е���Ϣʵ��
	 * @param province
	 * @param city
	 * @param chooseDayType
	 * @return
	 */
	public  List<MessageInfo>	findByPCDAndType(String province,String city,String district,int chooseDayType);
	/**
	 * ����ʡ�ݣ����� ������ѡ���ʱ������  �ҵ����е� � ʵ��
	 * @param province
	 * @param city
	 * @param chooseDayType
	 * @return
	 */
	public  List<MessageInfo>	findByPCDAndTypeOfAct(String actprovince,String actcity,String actdistrict,int chooseDayType);
	/**
	 * ����ʡ�ݣ����� ������,�ֵ�(other)ѡ���ʱ������  �ҵ����е���Ϣʵ��
	 * @param province
	 * @param city
	 * @param chooseDayType
	 * @return
	 */
	public  List<MessageInfo>	findByPCDOAndType(String province,String city,String district,String other,int chooseDayType);
	/**
	 * ����ʡ�ݣ����� ������,�ֵ�(other)ѡ���ʱ������  �ҵ����е� � ʵ��
	 * @param actprovince
	 * @param actcity
	 * @param actdistrict
	 * @param actother
	 * @param chooseDayType
	 * @return
	 */
	public  List<MessageInfo>	findByPCDOAndTypeOfAct(String actprovince,String actcity,String actdistrict,String actother,int chooseDayType);
	/**
	 * ���� category �ҵ����е����͵ļ�¼
	 * @param category
	 * @return
	 */
	public  List<MessageInfo>   findAllByType(int category);
	/**
	 * �ҵ��������Ϣ��ֻҪԭ����ת��,�����������û�ֵ
	 * @param category
	 * @return
	 */
	public  List<MessageInfo>   findAllByTypeForClassTopUser(int category);
	/**
	 * �ҵ������mood��Ϣ
	 * @return
	 */
	public  List<MessageInfo>   findRecentMood(int user_id);
	/**
	 * �ҵ��û��������Ϣ�����ǻ
	 * @param user_id
	 * @return
	 */
	public  List<MessageInfo>   findRecentMsgOrAct(int user_id,int type);
	/**
	 * ����ʵ������������ݿ�
	 * @param messageInfo
	 */
	public	void	save(MessageInfo messageInfo);
	/**
	 * ����ʵ���������ݿ�
	 * @param messageInfo
	 */
	public	void 	update(MessageInfo messageInfo);
	/**
	 * ������Ϣ����ɾ��
	 * @param msg_id
	 */
	public	void	delete(int msg_id);
	/**
	 * ����ʵ��ɾ��
	 * @param messageInfo
	 */
	public	void	delete(MessageInfo messageInfo);
}
