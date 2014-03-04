package dao;

import java.util.List;

import domain.MessageInfo;

public interface MessageInfoDAO {
	/**
	 * 根据信息元数据表主键得到信息实例
	 * @param msg_id
	 * @return
	 */
	public	MessageInfo	get(int msg_id);
	/**
	 * 获取所有的信息实例,包括信息原创，信息转发，活动原创，活动转发
	 * @return
	 */
	public	List<MessageInfo>	findAll();
	/**
	 * 获取所有的心情信息实例,心情原创
	 * @return
	 */
	public  List<MessageInfo>	findAllMood();
	/**
	 * 根据同城找到当前心情动态
	 * @param province
	 * @param city
	 * @return
	 */
	public  List<MessageInfo>	findAllMoodOfSameCity(String province,String city);
	/**
	 * 根据用户主键获取信息实例
	 * @param user_id
	 * @return
	 */
	public	List<MessageInfo>	findByUserId(int user_id);
	/**
	 * 根据用户主键获取心情实例
	 * @param user_id
	 * @return
	 */
	public  List<MessageInfo>   findMoodByUserId(int user_id);
	/**
	 * 根据省份，城市，地区/县 找到所有的信息实例
	 * 只要原创信息或者转发信息，评论信息不需要
	 * @param province
	 * @param city
	 * @param district
	 * @return
	 */
	public  List<MessageInfo>	findByPCD(String province,String city,String district);
	/**
	 * 根据省份，城市  找到所有的 信息 实例
	 * @param province
	 * @param city
	 * @return
	 */
	public  List<MessageInfo>	findByPC(String province,String city);
	/**
	 * 根据省份，城市  找到所有的 活动 实例
	 * @param province
	 * @param city
	 * @return
	 */
	public 	List<MessageInfo>	findByPCOfAct(String actprovince, String actcity);
	/**
	 * 根据省份，城市 ，根据 活动发生的时间 > 目前时间  找到所有的 活动 实例
	 * @param province
	 * @param city
	 * @return
	 */
	public  List<MessageInfo>	findByPCOfWillAct(String actprovince, String actcity,int datetime);
	/**
	 * 根据省份，城市 ，选择的时间类型  找到所有的信息实例
	 * @param province
	 * @param city
	 * @param chooseDayType
	 * @return
	 */
	public  List<MessageInfo>	findByPCAndType(String province,String city,int chooseDayType);
	/**
	 * 根据省份，城市 ，选择的时间类型  找到所有的  活动 实例
	 * @param province
	 * @param city
	 * @param chooseDayType
	 * @return
	 */
	public  List<MessageInfo>	findByPCAndTypeOfAct(String actprovince,String actcity,int chooseDayType);
	/**
	 * 根据省份，城市 ，地区选择的时间类型  找到所有的信息实例
	 * @param province
	 * @param city
	 * @param chooseDayType
	 * @return
	 */
	public  List<MessageInfo>	findByPCDAndType(String province,String city,String district,int chooseDayType);
	/**
	 * 根据省份，城市 ，地区选择的时间类型  找到所有的 活动 实例
	 * @param province
	 * @param city
	 * @param chooseDayType
	 * @return
	 */
	public  List<MessageInfo>	findByPCDAndTypeOfAct(String actprovince,String actcity,String actdistrict,int chooseDayType);
	/**
	 * 根据省份，城市 ，地区,街道(other)选择的时间类型  找到所有的信息实例
	 * @param province
	 * @param city
	 * @param chooseDayType
	 * @return
	 */
	public  List<MessageInfo>	findByPCDOAndType(String province,String city,String district,String other,int chooseDayType);
	/**
	 * 根据省份，城市 ，地区,街道(other)选择的时间类型  找到所有的 活动 实例
	 * @param actprovince
	 * @param actcity
	 * @param actdistrict
	 * @param actother
	 * @param chooseDayType
	 * @return
	 */
	public  List<MessageInfo>	findByPCDOAndTypeOfAct(String actprovince,String actcity,String actdistrict,String actother,int chooseDayType);
	/**
	 * 根据 category 找到所有的类型的记录
	 * @param category
	 * @return
	 */
	public  List<MessageInfo>   findAllByType(int category);
	/**
	 * 找到分类的信息，只要原创和转发,来计算其中用户值
	 * @param category
	 * @return
	 */
	public  List<MessageInfo>   findAllByTypeForClassTopUser(int category);
	/**
	 * 找到最近的mood信息
	 * @return
	 */
	public  List<MessageInfo>   findRecentMood(int user_id);
	/**
	 * 找到用户最近的信息或者是活动
	 * @param user_id
	 * @return
	 */
	public  List<MessageInfo>   findRecentMsgOrAct(int user_id,int type);
	/**
	 * 根据实例保存进入数据库
	 * @param messageInfo
	 */
	public	void	save(MessageInfo messageInfo);
	/**
	 * 根据实例更新数据库
	 * @param messageInfo
	 */
	public	void 	update(MessageInfo messageInfo);
	/**
	 * 根据信息主键删除
	 * @param msg_id
	 */
	public	void	delete(int msg_id);
	/**
	 * 根据实例删除
	 * @param messageInfo
	 */
	public	void	delete(MessageInfo messageInfo);
}
