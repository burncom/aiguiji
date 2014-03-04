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
	 * 验证登录
	 * @param userInfo
	 * @return
	 */
	public List<UserInfo> validLogin(UserInfo userInfo);
	/**
	 * 验证邮箱
	 * @param email
	 * @return
	 */
	public int validEmail(String email);
	/**
	 * 根据用户名获取所有的用户信息实例
	 * @param user_name
	 * @return
	 */
	public List<UserInfo> findByUserName(String user_name);
	/**
	 * 保存注册信息
	 * @param userInfo
	 */
	public void register(UserInfo userInfo);
	/**
	 * 验证邮箱和昵称是否重复，若通过转入个人信息页面；
	 * @param userInfo
	 * @return
	 */
	public int validateRegister(UserInfo userInfo);
	/**
	 * 设置个人信息
	 * @param userInfo
	 */
	public void setProfile(String path);
	/**
	 * 设置个人感兴趣的地点
	 * @param userInfo
	 * @param category
	 */
	public void setCategoryPage(UserInfo userInfo,String category);
	/**
	 * 保存用户日志信息
	 * 用户操作类型：1，原创；2，推荐；3，评论；4，想参加；5，关注；6，加照片；7，发起活动；8，登录；9，退出；10，发表心情指数；11，发表天气；12，天气赞成；13，天气不赞成,14，注册。
	 * @param userInfo
	 */
	public void addLog(int operator);
	/**
	 * 将新发布的信息保存入数据库
	 * @param messageInfo
	 * @param userName
	 */
	public void addMessage(MessageInfo messageInfo,String userName);
	/**
	 * 将天气实例保存入数据库
	 * @param weatherInfo
	 */
	public void addWeather(WeatherInfo weatherInfo,UserInfo userInfo);
	/**
	 * 得到实时天气
	 * @return
	 */
	public List<RealWeatherBean> getRealWeather(String user_name);
	/**
	 * 通过user_name将用户现在的坐标，和省份、城市、地区信息保存入数据库
	 * @param user_name
	 */
	public void addNowPlace(UserInfo userInfo);
	/**
	 * 处理对天气点击的赞成或者不赞成的按钮
	 * @param weather_id
	 * @param approve
	 */
	public void doApprove(int weather_id,String approve);
	/**
	 * 通过算法产生的MessageInfo，提供给新鲜地点的MessageBean，以供各种显示需求
	 * 显示内容：1，信息原创；2，信息转发；3，活动原创；4，活动转发
	 * @param messageInfo
	 * @return
	 */
	public List<MessageBean> makeMessageBean(UserInfo userInfo,ArrayList<MessageInfo> messages);
	/**
	 * 根据当前用户名得到  实时 新鲜地点
	 * 根据同城市，用户关注类别，实时；
	 * 显示内容：1，信息原创；2，信息转发；3，活动原创；4，活动转发
	 * @param user_name
	 */
	public List<MessageBean> getTimePlace(String user_name);
	/**
	 * 处理新鲜地点  热度，根据同城市，用户关注类别，推荐分和评论数，实时；
	 * 显示内容：1，信息原创；2，信息转发；3，活动原创；4，活动转发
	 * @param user_name
	 * @return
	 */
	public List<MessageBean> getHotPlace(String user_name,int chooseDayType);
	/**
	 * 处理新鲜地点  热度，根据同县/区（如果other相同有，则优先），用户关注类别，关注者，实时，推荐分和评论数；
	 * 显示内容：1，信息原创；2，信息转发；3，活动原创；4，活动转发
	 * @param user_name
	 * @return
	 */
	public List<MessageBean> getRecommendPlace(String user_name,int recomType);
	/**
	 * 处理 将精彩的地点 的Action，根据用户所在的City，去掉不感兴趣类别，按照时间排序，然后按照距离最近排序
	 * @param user_name
	 * @param willActivityType
	 * @return
	 */
	public List<MessageBean> getWillActivity(String user_name,int willActivityType);
	/**
	 * 获得我自己的信息+关注者的信息
	 * @param user_name
	 * @return
	 */
	public List<MessageBean> getMyHome(String user_name);
	/**
	 * 获得我自己的心情信息+关注者的心情信息,选择是关注者，还是 同城人显示
	 * @param user_name
	 * @return
	 */
	public List<MoodBean>	getMoodDynamic(String user_name,int moodType);
	/**
	 * 获取所有分类的排行榜
	 * @return
	 */
	public List<TopListBean> getTopList();
	/**
	 * 通过当前用户名得到心情的参数值
	 * @param user_name
	 * @return
	 */
	public MoodBean	getMoodInfo(String user_name);
	/**
	 * 根据信息主键和操作类型 得到所有评论的图片
	 * @param msg_id
	 * @param type
	 * @return
	 */
	public List<PicturesetBean> doPictureset(int msg_id,int type);
	/**
	 * 通过信息或者活动主键和操作类型删除，清理数据库
	 * @param msg_id
	 * @param type
	 */
	public void doDeleteMsgOrAct(int msg_id,int type );
	/**
	 * 处理推荐 按钮,加入推荐次数和推荐分数值
	 * @param msg_id
	 */
	public void doRecommend(int msg_id,int recommend_value);
	/**
	 * 处理想参加按钮，加入想参加次数和想参加分数值
	 * @param msg_id
	 * @param recommend_value
	 */
	public void doJoin(int msg_id,int recommend_value);
	/**
	 * 根据信息主键和页面显示类型  得到转发页面的显示内容
	 * @param msg_id
	 * @param type
	 * @return
	 */
	public TransmitBean getTransmitBean(UserInfo userInfo,int msg_id,int type);
	/**
	 * 根据userInfo,msg_id，type，msg_content写入内容
	 * @param msg_id
	 * @param type
	 * @param msg_content
	 */
	public void doTransmit(UserInfo userInfo,int msg_id,int firstMsgId,int type,String msg_content,boolean comment,boolean orignalComment);
	/**
	 * 通过当前信息主键和页面显示类型，得到原创帖的用户名
	 * @param msg_id
	 * @param type
	 * @return
	 */
	public String getOrignalUserName(int msg_id,int type);
	/**
	 * 根据msg_id 和 type得到所有评论该信息的 内容
	 * @param msg_id
	 * @param type
	 * @return
	 */
	public List<CommentBean> getCommentBean(UserInfo userInfo,int msg_id,int type);
	/**
	 * 根据当前心情信息主键获取其所有评论信息
	 * @param msg_id
	 * @return
	 */
	public List<CommentBean> getCommentMoodBean(UserInfo userInfo,int msg_id);
	/**
	 * 添加评论信息
	 * @param messageInfo
	 * @param type
	 * @param coordinate
	 */
	public void addComment(MessageInfo messageInfo,UserInfo userInfo,int msg_id,int firstMsgId,int type, String coordinate,String messageContent,boolean comment,boolean transmit);
	/**
	 * 增加 心情  评论
	 * @param messageInfo
	 * @param userInfo
	 * @param msg_id
	 */
	public void addMoodComment(MessageInfo messageInfo,UserInfo userInfo,int msg_id);
	/**
	 * 删除回复
	 * @param commentMsgId
	 */
	public void deleteReplyMsg(int msgId,int commentMsgId);
	/**
	 * 处理回复 
	 * @param msg_id
	 * @param commentUserName
	 * @param msgContent
	 */
	public void doRely(int msg_id,String commentUserName,MessageInfo messageInfo,UserInfo userInfo);
	/**
	 * 处理 心情 回复 
	 * @param msg_id
	 * @param commentUserName
	 * @param msgContent
	 */
	public void doRelyMood(int msg_id,String commentUserName,MessageInfo messageInfo,UserInfo userInfo); 
	/**
	 * 处理心情，将其保存在数据库
	 * @param publishMoodBean
	 * @param messageInfo
	 * @param userName
	 */
	public void doPublishMood(MessageInfo messageInfo,String userName);
	/**
	 * 通过信息主键删除心情记录
	 * @param msg_id
	 */
	public void doDeleteMood(int msg_id);
	/**
	 * 得到分类的TOP50的用户
	 * @return
	 */
	public List<TopUserBean> getClassTopUser(int type);
	/**
	 * 四种情况status，然后分别每种情况下的操作statusValue
	 * @param status
	 * @param statusValue
	 */
	public void doStatusUUR(int status,int statusValue,int userId);
	/**
	 * 得到关注者列表 
	 * @return
	 */
	public List<FollowerFansBean> getFollowers();
	/**
	 * 得到粉丝列表 
	 * @return
	 */
	public List<FollowerFansBean> getFans(); 
	/**
	 * 查找用户
	 * @param findUserName
	 * @return
	 */
	public List<FindUsersBean> findUser(String findUserName);
	/**
	 * 处理设置
	 * @param userInfo
	 */
	public void	setup(UserInfo userInfo,String userName);
	/**
	 * 得到用户相册
	 * @return
	 */
	public List<PhotosBean> getPhotos();
	/**
	 * 删除相册里面的图片
	 * @param photo_id
	 */
	public void doDeletePhoto(int photo_id);
	/**
	 * 通过当前用户名，和图片途径保存入数据库
	 * @param userName
	 * @param path
	 */
	public void uploadPicture(String userName,String path);
	/**
	 * 通过用户主键得到FindUsersBean
	 * @param userId
	 */
	public FindUsersBean getUserHeadInfo(int userId);
	/**
	 * 通过用户主键拿到用户照片
	 * @param userId
	 * @return
	 */
	public List<PhotosBean> getUserPhotos(int userId);
	/**
	 * 通过用户主键获取用户个人的信息
	 * @param userId
	 * @return
	 */
	public List<MessageBean> getUserMessages(int userId);
	/**
	 * 通过用户主键获取用户个人的心情
	 * @param userId
	 * @return
	 */
	public List<MoodBean> getUserMood(UserInfo userInfo,int userId);
	/**
	 * 修改头像
	 * @param userName
	 * @param path
	 */
	public void setUserAvator(String userName,String path);
	/**
	 * 修改用户注册邮箱
	 * @param email
	 */
	public void setUserEmail(String email);
	/**
	 * 修改用户感兴趣地点
	 * @param interest
	 */
	public void setUserInterest(String interest);
	/**
	 * 修改用户个人信息
	 * @param userInfo
	 */
	public boolean setOwnInfo(UserInfo userInfo);
	/**
	 * 反馈意见
	 * @param value
	 * @param content
	 */
	public void setFeedBack(int value,String content);
	/**
	 * 管理员用户计算用户的信任值
	 */
	public void ComputeTrustValue();
}
