package service;

import java.util.ArrayList;
import java.util.List;

import vo.CommentBean;
import vo.FindUsersBean;
import vo.FollowerFansBean;
import vo.MessageBean;
import vo.MoodBean;
import vo.PhotosBean;
import vo.PicturesetBean;
import vo.RealWeatherBean;
import vo.TopListBean;
import vo.TopUserBean;
import vo.TransmitBean;
import domain.MessageInfo;
import domain.UserInfo;
import domain.WeatherInfo;

public interface BaseService {
	/**
	 * ��֤��¼
	 * @param userInfo
	 * @return
	 */
	public List<UserInfo> validLogin(UserInfo userInfo);
	/**
	 * ��֤����
	 * @param email
	 * @return
	 */
	public int validEmail(String email);
	/**
	 * �����û�����ȡ���е��û���Ϣʵ��
	 * @param user_name
	 * @return
	 */
	public List<UserInfo> findByUserName(String user_name);
	/**
	 * ����ע����Ϣ
	 * @param userInfo
	 */
	public void register(UserInfo userInfo);
	/**
	 * ��֤������ǳ��Ƿ��ظ�����ͨ��ת�������Ϣҳ�棻
	 * @param userInfo
	 * @return
	 */
	public int validateRegister(UserInfo userInfo);
	/**
	 * ���ø�����Ϣ
	 * @param userInfo
	 */
	public void setProfile(String path);
	/**
	 * ���ø��˸���Ȥ�ĵص�
	 * @param userInfo
	 * @param category
	 */
	public void setCategoryPage(UserInfo userInfo,String category);
	/**
	 * �����û���־��Ϣ
	 * �û��������ͣ�1��ԭ����2���Ƽ���3�����ۣ�4����μӣ�5����ע��6������Ƭ��7��������8����¼��9���˳���10����������ָ����11������������12�������޳ɣ�13���������޳�,14��ע�ᡣ
	 * @param userInfo
	 */
	public void addLog(int operator);
	/**
	 * ���·�������Ϣ���������ݿ�
	 * @param messageInfo
	 * @param userName
	 */
	public void addMessage(MessageInfo messageInfo,String userName);
	/**
	 * ������ʵ�����������ݿ�
	 * @param weatherInfo
	 */
	public void addWeather(WeatherInfo weatherInfo,UserInfo userInfo);
	/**
	 * �õ�ʵʱ����
	 * @return
	 */
	public List<RealWeatherBean> getRealWeather(String user_name);
	/**
	 * ͨ��user_name���û����ڵ����꣬��ʡ�ݡ����С�������Ϣ���������ݿ�
	 * @param user_name
	 */
	public void addNowPlace(UserInfo userInfo);
	/**
	 * ���������������޳ɻ��߲��޳ɵİ�ť
	 * @param weather_id
	 * @param approve
	 */
	public void doApprove(int weather_id,String approve);
	/**
	 * ͨ���㷨������MessageInfo���ṩ�����ʵص��MessageBean���Թ�������ʾ����
	 * ��ʾ���ݣ�1����Ϣԭ����2����Ϣת����3���ԭ����4���ת��
	 * @param messageInfo
	 * @return
	 */
	public List<MessageBean> makeMessageBean(UserInfo userInfo,ArrayList<MessageInfo> messages);
	/**
	 * ���ݵ�ǰ�û����õ�  ʵʱ ���ʵص�
	 * ����ͬ���У��û���ע���ʵʱ��
	 * ��ʾ���ݣ�1����Ϣԭ����2����Ϣת����3���ԭ����4���ת��
	 * @param user_name
	 */
	public List<MessageBean> getTimePlace(String user_name);
	/**
	 * �������ʵص�  �ȶȣ�����ͬ���У��û���ע����Ƽ��ֺ���������ʵʱ��
	 * ��ʾ���ݣ�1����Ϣԭ����2����Ϣת����3���ԭ����4���ת��
	 * @param user_name
	 * @return
	 */
	public List<MessageBean> getHotPlace(String user_name,int chooseDayType);
	/**
	 * �������ʵص�  �ȶȣ�����ͬ��/�������other��ͬ�У������ȣ����û���ע��𣬹�ע�ߣ�ʵʱ���Ƽ��ֺ���������
	 * ��ʾ���ݣ�1����Ϣԭ����2����Ϣת����3���ԭ����4���ת��
	 * @param user_name
	 * @return
	 */
	public List<MessageBean> getRecommendPlace(String user_name,int recomType);
	/**
	 * ���� �����ʵĵص� ��Action�������û����ڵ�City��ȥ��������Ȥ��𣬰���ʱ������Ȼ���վ����������
	 * @param user_name
	 * @param willActivityType
	 * @return
	 */
	public List<MessageBean> getWillActivity(String user_name,int willActivityType);
	/**
	 * ������Լ�����Ϣ+��ע�ߵ���Ϣ
	 * @param user_name
	 * @return
	 */
	public List<MessageBean> getMyHome(String user_name);
	/**
	 * ������Լ���������Ϣ+��ע�ߵ�������Ϣ,ѡ���ǹ�ע�ߣ����� ͬ������ʾ
	 * @param user_name
	 * @return
	 */
	public List<MoodBean>	getMoodDynamic(String user_name,int moodType);
	/**
	 * ��ȡ���з�������а�
	 * @return
	 */
	public List<TopListBean> getTopList();
	/**
	 * ͨ����ǰ�û����õ�����Ĳ���ֵ
	 * @param user_name
	 * @return
	 */
	public MoodBean	getMoodInfo(String user_name);
	/**
	 * ������Ϣ�����Ͳ������� �õ��������۵�ͼƬ
	 * @param msg_id
	 * @param type
	 * @return
	 */
	public List<PicturesetBean> doPictureset(int msg_id,int type);
	/**
	 * ͨ����Ϣ���߻�����Ͳ�������ɾ�����������ݿ�
	 * @param msg_id
	 * @param type
	 */
	public void doDeleteMsgOrAct(int msg_id,int type );
	/**
	 * �����Ƽ� ��ť,�����Ƽ��������Ƽ�����ֵ
	 * @param msg_id
	 */
	public void doRecommend(int msg_id,int recommend_value);
	/**
	 * ������μӰ�ť��������μӴ�������μӷ���ֵ
	 * @param msg_id
	 * @param recommend_value
	 */
	public void doJoin(int msg_id,int recommend_value);
	/**
	 * ������Ϣ������ҳ����ʾ����  �õ�ת��ҳ�����ʾ����
	 * @param msg_id
	 * @param type
	 * @return
	 */
	public TransmitBean getTransmitBean(UserInfo userInfo,int msg_id,int type);
	/**
	 * ����userInfo,msg_id��type��msg_contentд������
	 * @param msg_id
	 * @param type
	 * @param msg_content
	 */
	public void doTransmit(UserInfo userInfo,int msg_id,int firstMsgId,int type,String msg_content,boolean comment,boolean orignalComment);
	/**
	 * ͨ����ǰ��Ϣ������ҳ����ʾ���ͣ��õ�ԭ�������û���
	 * @param msg_id
	 * @param type
	 * @return
	 */
	public String getOrignalUserName(int msg_id,int type);
	/**
	 * ����msg_id �� type�õ��������۸���Ϣ�� ����
	 * @param msg_id
	 * @param type
	 * @return
	 */
	public List<CommentBean> getCommentBean(UserInfo userInfo,int msg_id,int type);
	/**
	 * ���ݵ�ǰ������Ϣ������ȡ������������Ϣ
	 * @param msg_id
	 * @return
	 */
	public List<CommentBean> getCommentMoodBean(UserInfo userInfo,int msg_id);
	/**
	 * ���������Ϣ
	 * @param messageInfo
	 * @param type
	 * @param coordinate
	 */
	public void addComment(MessageInfo messageInfo,UserInfo userInfo,int msg_id,int firstMsgId,int type, String coordinate,String messageContent,boolean comment,boolean transmit);
	/**
	 * ���� ����  ����
	 * @param messageInfo
	 * @param userInfo
	 * @param msg_id
	 */
	public void addMoodComment(MessageInfo messageInfo,UserInfo userInfo,int msg_id);
	/**
	 * ɾ���ظ�
	 * @param commentMsgId
	 */
	public void deleteReplyMsg(int msgId,int commentMsgId);
	/**
	 * ����ظ� 
	 * @param msg_id
	 * @param commentUserName
	 * @param msgContent
	 */
	public void doRely(int msg_id,String commentUserName,MessageInfo messageInfo,UserInfo userInfo);
	/**
	 * ���� ���� �ظ� 
	 * @param msg_id
	 * @param commentUserName
	 * @param msgContent
	 */
	public void doRelyMood(int msg_id,String commentUserName,MessageInfo messageInfo,UserInfo userInfo); 
	/**
	 * �������飬���䱣�������ݿ�
	 * @param publishMoodBean
	 * @param messageInfo
	 * @param userName
	 */
	public void doPublishMood(MessageInfo messageInfo,String userName);
	/**
	 * ͨ����Ϣ����ɾ�������¼
	 * @param msg_id
	 */
	public void doDeleteMood(int msg_id);
	/**
	 * �õ������TOP50���û�
	 * @return
	 */
	public List<TopUserBean> getClassTopUser(int type);
	/**
	 * �������status��Ȼ��ֱ�ÿ������µĲ���statusValue
	 * @param status
	 * @param statusValue
	 */
	public void doStatusUUR(int status,int statusValue,int userId);
	/**
	 * �õ���ע���б� 
	 * @return
	 */
	public List<FollowerFansBean> getFollowers();
	/**
	 * �õ���˿�б� 
	 * @return
	 */
	public List<FollowerFansBean> getFans(); 
	/**
	 * �����û�
	 * @param findUserName
	 * @return
	 */
	public List<FindUsersBean> findUser(String findUserName);
	/**
	 * ��������
	 * @param userInfo
	 */
	public void	setup(UserInfo userInfo,String userName);
	/**
	 * �õ��û����
	 * @return
	 */
	public List<PhotosBean> getPhotos();
	/**
	 * ɾ����������ͼƬ
	 * @param photo_id
	 */
	public void doDeletePhoto(int photo_id);
	/**
	 * ͨ����ǰ�û�������ͼƬ;�����������ݿ�
	 * @param userName
	 * @param path
	 */
	public void uploadPicture(String userName,String path);
	/**
	 * ͨ���û������õ�FindUsersBean
	 * @param userId
	 */
	public FindUsersBean getUserHeadInfo(int userId);
	/**
	 * ͨ���û������õ��û���Ƭ
	 * @param userId
	 * @return
	 */
	public List<PhotosBean> getUserPhotos(int userId);
	/**
	 * ͨ���û�������ȡ�û����˵���Ϣ
	 * @param userId
	 * @return
	 */
	public List<MessageBean> getUserMessages(int userId);
	/**
	 * ͨ���û�������ȡ�û����˵�����
	 * @param userId
	 * @return
	 */
	public List<MoodBean> getUserMood(UserInfo userInfo,int userId);
	/**
	 * �޸�ͷ��
	 * @param userName
	 * @param path
	 */
	public void setUserAvator(String userName,String path);
	/**
	 * �޸��û�ע������
	 * @param email
	 */
	public void setUserEmail(String email);
	/**
	 * �޸��û�����Ȥ�ص�
	 * @param interest
	 */
	public void setUserInterest(String interest);
	/**
	 * �޸��û�������Ϣ
	 * @param userInfo
	 */
	public boolean setOwnInfo(UserInfo userInfo);
	/**
	 * �������
	 * @param value
	 * @param content
	 */
	public void setFeedBack(int value,String content);
	/**
	 * ����Ա�û������û�������ֵ
	 */
	public void ComputeTrustValue();
}
