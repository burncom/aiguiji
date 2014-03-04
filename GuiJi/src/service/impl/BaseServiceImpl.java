package service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import service.BaseService;
import service.WebAlgo;
import service.WebConstant;
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

import com.opensymphony.xwork2.ActionContext;

import dao.LogInfoDAO;
import dao.MessageInfoDAO;
import dao.MsgMsgRelationshipDAO;
import dao.PhotosInfoDAO;
import dao.UserInfoDAO;
import dao.UserMsgIndexDAO;
import dao.UserUserRelationshipDAO;
import dao.WeatherInfoDAO;
import domain.LogInfo;
import domain.MessageInfo;
import domain.MsgMsgRelationship;
import domain.PhotosInfo;
import domain.UserInfo;
import domain.UserMsgIndex;
import domain.UserUserRelationship;
import domain.WeatherInfo;

public class BaseServiceImpl implements BaseService {
	private LogInfoDAO logInfoDAO;
	private MessageInfoDAO messageInfoDAO;
	private MsgMsgRelationshipDAO msgMsgRelationshipDAO;
	private UserInfoDAO userInfoDAO;
	private UserMsgIndexDAO userMsgIndexDAO;
	private UserUserRelationshipDAO userUserRelationshipDAO;
	private WeatherInfoDAO weatherInfoDAO;
	private PhotosInfoDAO photosInfoDAO;
	
	private static UserTrust userTrust=null;
	private static Timer timer=null;
	
	public static void runUserTrust(){
		getTimerInstance().schedule(getUserTrustInstance(), 0,2000);
	}
	//计时器的单列模式
	public static Timer getTimerInstance(){
		if(timer==null){
			timer=new Timer();
		}
		return timer;
	}
	//计算用户信任值的单列模式
	public static UserTrust getUserTrustInstance(){
		if(userTrust==null){
			userTrust=new UserTrust();
		}
		return userTrust;
	}
	//计算用户信任值的内部类 
	static class UserTrust extends TimerTask{

		@Override
		public void run() {
			System.out.println("yes time !");
			
		}
		
	} 
	
	public PhotosInfoDAO getPhotosInfoDAO() {
		return photosInfoDAO;
	}

	public void setPhotosInfoDAO(PhotosInfoDAO photosInfoDAO) {
		this.photosInfoDAO = photosInfoDAO;
	}

	public LogInfoDAO getLogInfoDAO() {
		return logInfoDAO;
	}

	public void setLogInfoDAO(LogInfoDAO logInfoDAO) {
		this.logInfoDAO = logInfoDAO;
	}

	public MessageInfoDAO getMessageInfoDAO() {
		return messageInfoDAO;
	}

	public void setMessageInfoDAO(MessageInfoDAO messageInfoDAO) {
		this.messageInfoDAO = messageInfoDAO;
	}

	public MsgMsgRelationshipDAO getMsgMsgRelationshipDAO() {
		return msgMsgRelationshipDAO;
	}

	public void setMsgMsgRelationshipDAO(MsgMsgRelationshipDAO msgMsgRelationshipDAO) {
		this.msgMsgRelationshipDAO = msgMsgRelationshipDAO;
	}

	public UserInfoDAO getUserInfoDAO() {
		return userInfoDAO;
	}

	public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
		this.userInfoDAO = userInfoDAO;
	}

	public UserMsgIndexDAO getUserMsgIndexDAO() {
		return userMsgIndexDAO;
	}

	public void setUserMsgIndexDAO(UserMsgIndexDAO userMsgIndexDAO) {
		this.userMsgIndexDAO = userMsgIndexDAO;
	}

	public UserUserRelationshipDAO getUserUserRelationshipDAO() {
		return userUserRelationshipDAO;
	}

	public void setUserUserRelationshipDAO(
			UserUserRelationshipDAO userUserRelationshipDAO) {
		this.userUserRelationshipDAO = userUserRelationshipDAO;
	}

	public WeatherInfoDAO getWeatherInfoDAO() {
		return weatherInfoDAO;
	}

	public void setWeatherInfoDAO(WeatherInfoDAO weatherInfoDAO) {
		this.weatherInfoDAO = weatherInfoDAO;
	}

	@Override
	public List<UserInfo> validLogin(UserInfo userInfo) {
		return userInfoDAO.findByEmailAndPass(userInfo.getEmail(), userInfo.getUser_password());
	}
	
	@Override
	public int validEmail(String email) {
		List<UserInfo> users=userInfoDAO.findByEmail(email);
		if(users.size()!=0)
			return WebConstant.EMAILEXIST;
		else
			return WebConstant.EMAILNOTEXIST;
	}

	@Override
	public int validateRegister(UserInfo userInfo) {
		List<UserInfo> users=userInfoDAO.findAll();
		int flag=WebConstant.RG_CANREGISTER;
		for(UserInfo u:users){
			if(u.getEmail().equalsIgnoreCase(userInfo.getEmail())){
				if(u.getUser_name().equalsIgnoreCase(userInfo.getUser_name())){
					flag=WebConstant.RG_CANLOGIN;//已经注册过哦，可以直接登录
					break;
				}else{
					flag=WebConstant.RG_EMAILSAME;//邮箱已经注册过了
					break;
				}
			}
			else if(u.getUser_name().equalsIgnoreCase(userInfo.getUser_name())){
					flag=WebConstant.RG_NAMESAME;//昵称已经被注册过了
					break;
				}
		}
		return flag;
	}

	@Override
	public void register(UserInfo userInfo) {
			userInfoDAO.save(userInfo);
	}
	
	@Override
	public List<UserInfo> findByUserName(String user_name) {
		return (List<UserInfo>)userInfoDAO.findByUserName(user_name);
	}

	@Override
	public void setProfile(String path) {
		ActionContext ctx=ActionContext.getContext();
		String userName=(String)ctx.getSession().get("user");
		List<UserInfo> user=userInfoDAO.findByUserName(userName);
		UserInfo dbUser=user.get(0);
		if(user.size()!=0)
		{
			dbUser.setAvatar(path);
			userInfoDAO.update(dbUser);
			
			PhotosInfo photosInfo=new PhotosInfo();
			photosInfo.setUserInfo(dbUser);
			photosInfo.setPhoto(path);
			photosInfoDAO.save(photosInfo);
		}
		
	}

	@Override
	public void setCategoryPage(UserInfo userInfo,String category) {
		ActionContext ctx=ActionContext.getContext();
		String userName=(String)ctx.getSession().get("user");
		List<UserInfo> user=userInfoDAO.findByUserName(userName);
		if(user.size()!=0)
		{
			user.get(0).setCategory(category);
			userInfoDAO.update(user.get(0));
		}
	}

	@Override
	public void addLog(int operator) {
		WebAlgo algo=new WebAlgo();
		ActionContext ctx=ActionContext.getContext();
		String userName=(String)ctx.getSession().get("user");
		List<UserInfo> users=userInfoDAO.findByUserName(userName);
		UserInfo userInfo=users.get(0);
		LogInfo log=new LogInfo();
		log.setUserInfo(userInfo);
		log.setOperate(operator);
		if(operator==WebConstant.OP_LOGIN || operator == WebConstant.OP_LOGOUT||operator==WebConstant.OP_REGISTER){
			if(operator ==WebConstant.OP_LOGIN ){
				log.setOperate(WebConstant.OP_LOGIN);
				log.setCoordinate(userInfo.getNowcoordinate());
				log.setProvince(userInfo.getNowprovince());
				log.setCity(userInfo.getNowcity());
				log.setDistrict(userInfo.getNowdistrict());
				log.setOther(userInfo.getNowother());
			}
			else if(operator == WebConstant.OP_LOGOUT){
				log.setOperate(WebConstant.OP_LOGOUT);
			}
			else
				log.setOperate(WebConstant.OP_REGISTER);
			
			log.setOprealtime(Integer.parseInt(algo.getRealTime()));
			log.setOpdatetime(Integer.parseInt(algo.getRealDay()));
		}
		logInfoDAO.save(log);
	}

	@Override
	public void addMessage(MessageInfo messageInfo, String userName) {
		WebAlgo algo=new WebAlgo();
		List<UserInfo> users=userInfoDAO.findByUserName(userName);
		UserInfo userInfo=users.get(0);
		messageInfo.setUserInfo(userInfo);
		
		messageInfo.setDatetime(Integer.parseInt(algo.getRealDay()));
		messageInfo.setRealtime(Integer.parseInt(algo.getRealTime()));
		
		messageInfoDAO.save(messageInfo);
		//加入user_msg_index表中
		UserMsgIndex userMsgIndex=new UserMsgIndex();
		userMsgIndex.setUserInfo(userInfo);
		userMsgIndex.setAuthor_id(userInfo.getUser_id());
		userMsgIndex.setMessageInfo(messageInfo);
		userMsgIndex.setRealtime(messageInfo.getRealtime());
		userMsgIndex.setDatetime(messageInfo.getDatetime());
		userMsgIndexDAO.save(userMsgIndex);
		
		//保存日志信息
		if(messageInfo.getActprovince()!=null)
			addLogInfo(userInfo, WebConstant.OP_ACTIVITY, 0, messageInfo.getCoordinate(), messageInfo.getProvince(),
					messageInfo.getCity(), messageInfo.getDistrict(), messageInfo.getOther(), 
					messageInfo.getRealtime(), messageInfo.getDatetime());
		else
			addLogInfo(userInfo, WebConstant.OP_MESSAGE, 0, messageInfo.getCoordinate(), messageInfo.getProvince(),
					messageInfo.getCity(), messageInfo.getDistrict(), messageInfo.getOther(), 
					messageInfo.getRealtime(), messageInfo.getDatetime());
	}

	@Override
	public void addWeather(WeatherInfo weatherInfo,UserInfo userInfo) {
		WebAlgo algo=new WebAlgo();
		weatherInfo.setUserInfo(userInfo);
		
		weatherInfo.setDatetime(Integer.parseInt(algo.getRealDay()));
		weatherInfo.setRealtime(Integer.parseInt(algo.getRealTime()));
		weatherInfoDAO.save(weatherInfo);
		
		//保存日志信息
		addLogInfo(userInfo, WebConstant.OP_ADDWEATHER, 0, weatherInfo.getCoordinate(),
				weatherInfo.getProvince(), weatherInfo.getCity(), weatherInfo.getDistrict(), weatherInfo.getOther(),
				weatherInfo.getRealtime(), weatherInfo.getDatetime());
	}
	

	@Override
	public List<RealWeatherBean> getRealWeather(String user_name) {
		WebAlgo algo=new WebAlgo();
		List<UserInfo> users=findByUserName(user_name);
		String province=users.get(0).getNowprovince();
		String city=users.get(0).getNowcity();
		String district=users.get(0).getNowdistrict();
		ArrayList<WeatherInfo> members=(ArrayList<WeatherInfo>) weatherInfoDAO.findByPCD(province,
				city, district,Integer.parseInt(algo.getRealDay()));
		
		int membersSize=members.size();
		
		if(membersSize!=0){
			//使用快速排序算法
			//1,根据同地区发布的时间排序
			
			algo.qsort(members, 0,membersSize-1);
			//2,如果是同一时间发布的按照信任值排序,且删除信任值较低者;可能会存在高信任值相同者，那这样的还是保留他们存在
			for(int i=0;i<membersSize;i++){
				WeatherInfo temp=members.get(i);
				int index=i;//时间相等的下标
				int flag=i;//最大值下标
				for(int j=i+1;j<membersSize;j++){
					if(temp.getRealtime()==members.get(j).getRealtime()){//找出发布时间相等的实例
						index=j;
						if(members.get(j).getUserInfo().getTrust_value().compareTo(temp.getUserInfo().getTrust_value()) > 0)//找出信任值最大的那个实例
							flag=j;
					}
					else
						break;
				}
				if(index != i){//如果存在连续时间相同的实例，则删除
					for(int k=i;k<=index;k++){
						if(k!=flag)//k!=flag 说明连续的几个实例中不是最大的那个或者那些
							members.remove(k);
					}
					i=index;//重新设置开始搜索的位置
				}
			}
		}
		ArrayList<RealWeatherBean> result=new ArrayList<RealWeatherBean>();
		
		for(WeatherInfo tt:members){
			String path=tt.getUserInfo().getAvatar();
			String paths[]=path.split("GuiJi");
			result.add(new RealWeatherBean(tt.getWeather_id(),
					paths[1].replace('\\', '/'),
					tt.getUserInfo().getUser_id(),
					tt.getUserInfo().getUser_name(),
					tt.getWeatherscene(),
					tt.getTemperature(),tt.getWind(),
					algo.transferVirtualTime(tt.getDatetime(),tt.getRealtime(),Integer.parseInt(algo.getRealDay()),Integer.parseInt(algo.getRealTime())),
					tt.getProvince()+tt.getCity()+tt.getDistrict(),
					tt.getApprove_amount(),
					tt.getDisapprove_amount()));
		}
		return result;
	}

	@Override
	public void addNowPlace(UserInfo userInfo) {
		List<UserInfo> users=userInfoDAO.findByUserName(userInfo.getUser_name());
				
		String coordinate=userInfo.getNowcoordinate();//(纬度，经度)
		users.get(0).setNowcoordinate(coordinate);
		WebAlgo algo=new WebAlgo();
		String placeName[]=algo.getPlaceNameByCoorinateByBaiduMap(coordinate);
		 //处理内容
		users.get(0).setNowprovince(placeName[0]);
		users.get(0).setNowcity(placeName[1]);
		users.get(0).setNowdistrict(placeName[2]);
		users.get(0).setNowother(placeName[3]);
		 
		userInfoDAO.update(users.get(0));
	}

	@Override
	public void doApprove(int weather_id, String approve) {
		ActionContext ctx=ActionContext.getContext();
		String userName=(String) ctx.getSession().get("user");
		List<UserInfo> userInfo=userInfoDAO.findByUserName(userName);
		LogInfo logInfo=new LogInfo();
		logInfo.setUserInfo(userInfo.get(0));
		WeatherInfo weather=weatherInfoDAO.get(weather_id);
		if(approve.equals("yes")){
			weather.setApprove_amount(weather.getApprove_amount()+1);
			logInfo.setOperate(WebConstant.OP_APPROVEWEATHER);
		}
		else{
			weather.setDisapprove_amount(weather.getDisapprove_amount()+1);
			logInfo.setOperate(WebConstant.OP_DISAPPROVEWEATHER);
		}
		weatherInfoDAO.update(weather);
		logInfoDAO.save(logInfo);
	}
	
	

	@Override
	public ArrayList<MessageBean> makeMessageBean(UserInfo userInfo,ArrayList<MessageInfo> messages) {
		//通过算法产生的MessageInfo，提供给新鲜地点的MessageBean，以供各种显示需求
		// 显示内容：1，信息原创；2，信息转发；3，活动原创；4，活动转发
		WebAlgo algo=new WebAlgo();
		ArrayList<MessageBean> result=new ArrayList<MessageBean>();
		for(MessageInfo tt:messages){
			String path=tt.getUserInfo().getAvatar();
			String paths[]=path.split("GuiJi");
			
			String picPath=null;
			String picPaths[]=null;
			String picPathStr=null;
			if(tt.getPicture()!=null){
				picPath=tt.getPicture();
				picPaths=picPath.split("GuiJi");
				picPathStr=picPaths[1].replace('\\', '/');
			}
			
			List<MsgMsgRelationship> msgmsgR=null;
			UserInfo originalUser=null;
			MessageInfo originalMsg=null;
			String originalPath=null;
			String originalPaths[]=null;
			String originalPathStr=null;
			if(tt.getType()==WebConstant.DB_MSGTRANSMIT || tt.getType()==WebConstant.DB_ACTTRANSMIT){
				if(tt.getType()==WebConstant.DB_MSGTRANSMIT)
					msgmsgR=msgMsgRelationshipDAO.findOriginalMessage(tt.getMsg_id(),WebConstant.R_MSGTRANSMIT);
				else
					msgmsgR=msgMsgRelationshipDAO.findOriginalMessage(tt.getMsg_id(), WebConstant.R_ACTTRANSMIT);
				originalUser=userInfoDAO.get(messageInfoDAO.get(msgmsgR.get(0).getReferenced_msg_id()).getUserInfo().getUser_id());
				originalMsg=messageInfoDAO.get(msgmsgR.get(0).getReferenced_msg_id());
				
				if(originalMsg.getPicture()!=null){
					originalPath=originalMsg.getPicture();
					originalPaths=originalPath.split("GuiJi");
					originalPathStr=originalPaths[1].replace('\\', '/');
				}
			}
			
			if(tt.getType()==WebConstant.DB_MSGORIGNAL){
				
				result.add(
						new MessageBean(WebConstant.T_MSGORIGNAL+"",
						tt.getMsg_id(),
						tt.getUserInfo().getUser_id(),
						paths[1].replace('\\', '/'),
						tt.getUserInfo().getUser_name(),
						tt.getMsg_content(),
						picPathStr,
						algo.transferVirtualTime(tt.getDatetime(),tt.getRealtime(),Integer.parseInt(algo.getRealDay()),Integer.parseInt(algo.getRealTime())),
						getBestMsgPlaceName(userInfo, tt),
						tt.getPictures_amount(),
						tt.getRecommend_amount(),
						tt.getTransmit_amount(),
						tt.getComment_amount(),
						algo.getCategoryIS(tt.getCategory())));
			}
			else if(tt.getType() == WebConstant.DB_MSGTRANSMIT){
				result.add(new MessageBean(
						WebConstant.T_MSGTRANSMIT+"",
						tt.getMsg_id(),
						tt.getUserInfo().getUser_id(),
						paths[1].replace('\\', '/'),
						tt.getUserInfo().getUser_name(),
						tt.getMsg_content(),
						algo.transferVirtualTime(tt.getDatetime(),tt.getRealtime(),Integer.parseInt(algo.getRealDay()),Integer.parseInt(algo.getRealTime())),
						getBestMsgPlaceName(userInfo, tt),
						tt.getPictures_amount(),
						tt.getRecommend_amount(),
						tt.getTransmit_amount(),
						tt.getComment_amount(),
						originalMsg.getMsg_id(),
						originalUser.getUser_id(),
						originalUser.getUser_name(),
						originalMsg.getMsg_content(),
						originalPathStr,
						algo.transferVirtualTime(originalMsg.getDatetime(),originalMsg.getRealtime(),Integer.parseInt(algo.getRealDay()),Integer.parseInt(algo.getRealTime())),
						getBestMsgPlaceName(userInfo, originalMsg),
						originalMsg.getPictures_amount(),
						originalMsg.getRecommend_amount(),
						originalMsg.getTransmit_amount(),
						originalMsg.getComment_amount(),
						algo.getCategoryIS(originalMsg.getCategory())));
			}
			else if(tt.getType()==WebConstant.DB_ACTORIGNAL){
				result.add(new MessageBean(
						WebConstant.T_ACTORIGNAL+"",
						tt.getMsg_id(),
						tt.getUserInfo().getUser_id(),
						paths[1].replace('\\', '/'),
						tt.getUserInfo().getUser_name(),
						tt.getMsg_content(),
						picPathStr,
						algo.transferVirtualTime(tt.getDatetime(),tt.getRealtime(),Integer.parseInt(algo.getRealDay()),Integer.parseInt(algo.getRealTime())),
						getBestMsgPlaceName(userInfo, tt),
						tt.getPictures_amount(),
						tt.getRecommend_amount(),
						tt.getTransmit_amount(),
						tt.getComment_amount(),
						algo.getCategoryIS(tt.getCategory()),
						algo.transferTimeIStr(tt.getStart_datetime(),tt.getStart_realtime()),
						algo.transferTimeIStr(tt.getEnd_datetime(), tt.getEnd_realtime()),
						getBestActPlaceName(userInfo, tt),
						tt.getLeader_name(),
						tt.getJoin_amount()	));
			}
			else {
				result.add(new MessageBean(
						WebConstant.T_ACTTRANSMIT+"",
						tt.getMsg_id(),
						tt.getUserInfo().getUser_id(),
						paths[1].replace('\\', '/'),
						tt.getUserInfo().getUser_name(),
						tt.getMsg_content(),
						algo.transferVirtualTime(tt.getDatetime(),tt.getRealtime(),Integer.parseInt(algo.getRealDay()),Integer.parseInt(algo.getRealTime())),
						getBestMsgPlaceName(userInfo, tt),
						tt.getPictures_amount(),
						tt.getRecommend_amount(),
						tt.getTransmit_amount(),
						tt.getComment_amount(),
						originalMsg.getMsg_id(),
						originalUser.getUser_id(),
						originalUser.getUser_name(),
						originalMsg.getMsg_content(),
						originalPathStr,
						algo.transferVirtualTime(originalMsg.getDatetime(),originalMsg.getRealtime(),Integer.parseInt(algo.getRealDay()),Integer.parseInt(algo.getRealTime())),
						getBestMsgPlaceName(userInfo, originalMsg),
						originalMsg.getPictures_amount(),
						originalMsg.getRecommend_amount(),
						originalMsg.getTransmit_amount(),
						originalMsg.getComment_amount(),
						algo.getCategoryIS(originalMsg.getCategory()),
						algo.transferTimeIStr(originalMsg.getStart_datetime(),originalMsg.getStart_realtime()),
						algo.transferTimeIStr(originalMsg.getEnd_datetime(), originalMsg.getEnd_realtime()),
						getBestActPlaceName(userInfo, originalMsg),
						originalMsg.getLeader_name(),
						tt.getJoin_amount(),
						originalMsg.getJoin_amount()));
			}
				
		}
		
		return result;
	}

	@Override
	public List<MessageBean> getTimePlace(String user_name) {
		List<UserInfo> users=findByUserName(user_name);
		String province=users.get(0).getNowprovince();
		String city=users.get(0).getNowcity();
		//找到同一个城市的信息或者活动,原创或者是转发
		//1,首先加入信息 记录
		ArrayList<MessageInfo> messages=(ArrayList<MessageInfo>) messageInfoDAO.findByPC(province, city);
		//2,然后加入活动记录，因为要根据活动将要发生的地点来找到实例，而不是根据分布人的地理位置
		messages.addAll((ArrayList<MessageInfo>) messageInfoDAO.findByPCOfAct(province, city));
		
		int messagesSize=messages.size();
		WebAlgo algo=new WebAlgo();
		if(messagesSize!=0){
			//1,去掉和用户感兴趣类别不符的 信息或者活动
			algo.deleteDisInterest(messages,users.get(0).getCategory());
			int changeSize=messages.size();
			//2,按照实时快速排序
			if(changeSize!=0){
				//2.1，首先按照日期dateTime排序
				algo.qsortMessageByDateTime(messages, 0, changeSize-1);
				//2.2，对于同一天按照realTime排序
				int len=messages.size();
				for(int i=0;i<len;i++){
					int index = i;
					for(int j=i+1;j<len;j++){
						if(messages.get(index).getDatetime()==messages.get(j).getDatetime()){
							index = j;
						}
						else
							break;
					}
					if(index != i){
						algo.qsortMessageByRealTime(messages,i, index);
					}
					i=index;
				}
				//如果存在时间上相等的情况，则按照信任值高到低排序
				algo.makeSameTimeMaxTrust(messages,changeSize);
			}
		}
		return makeMessageBean(users.get(0),messages);
	}
	
	@Override
	public List<MessageBean> getHotPlace(String user_name,int chooseDayType) {
		List<UserInfo> users=findByUserName(user_name);
		String province=users.get(0).getNowprovince();
		String city=users.get(0).getNowcity();
		//找到同一个城市的信息或者活动,其实在限定的时间范围内找寻,原创或者是转发
		//1,首先加入信息 记录
		ArrayList<MessageInfo> messages=(ArrayList<MessageInfo>) messageInfoDAO.findByPCAndType(province, city, chooseDayType);
		//2,然后加入活动记录，因为要根据活动将要发生的地点来找到实例，而不是根据分布人的地理位置
		messages.addAll((ArrayList<MessageInfo>) messageInfoDAO.findByPCAndTypeOfAct(province, city, chooseDayType));
		
		int messagesSize=messages.size();
		WebAlgo algo=new WebAlgo();
		ArrayList<MessageInfo> result=new ArrayList<MessageInfo>();
		if(messagesSize!=0){
			//1,去掉和用户感兴趣类别不符的 信息或者活动
			algo.deleteDisInterest(messages,users.get(0).getCategory());
			int changeSize=messages.size();
			if(changeSize!=0){
				//2,按照推荐分,评论数，转发数  计算得来的热度进行排序
				Map<MessageInfo, Float> hotMessages = new HashMap<MessageInfo, Float>();
				float hotValue=0;
				for(MessageInfo hotMember:messages){
					//对于信息，热度值=0.4*推荐数+0.1*推荐分数值+0.3*所有转发数+0.2*所有评论数
					//对于活动，热度值=0.15*推荐数+0.1*推荐分数值+0.3*所有转发数+0.2*所有评论数+0.15*想参加数+0.1*想参加分数值
					
					if(hotMember.getType()==WebConstant.DB_MSGORIGNAL||hotMember.getType()==WebConstant.DB_MSGTRANSMIT){
						
						hotValue=(float) (0.4*hotMember.getRecommend_amount()+0.1*hotMember.getRecommend_value()+
							0.3*hotMember.getTransmit_amount()+0.2*hotMember.getComment_amount());
					}
					else{
						hotValue=(float) (0.15*hotMember.getRecommend_amount()+0.1*hotMember.getRecommend_value()+
								0.3*hotMember.getTransmit_amount()+0.2*hotMember.getComment_amount()+
								0.15*hotMember.getJoin_amount()+0.1*hotMember.getJoin_value());
					}
					
					hotMessages.put(hotMember, hotValue);
				}
				
				//对hotMessages进行快速排序
				List<Map.Entry<MessageInfo, Float>> hotMessagesList =
				    new ArrayList<Map.Entry<MessageInfo, Float>>(hotMessages.entrySet());
				algo.qsortHashMapOfMessageinfo(hotMessagesList, 0, hotMessagesList.size()-1);
				
				for(Map.Entry<MessageInfo, Float> member:hotMessagesList){
					result.add(member.getKey());
				}
			}
		}
		
		return makeMessageBean(users.get(0),result);
	}

	
	@Override
	public List<MessageBean> getRecommendPlace(String user_name,int recomType) {
		//处理新鲜地点  热度，根据同县/区（如果other相同有，则优先），用户关注类别，关注者，实时，推荐分和评论数,距离；
		List<UserInfo> users=findByUserName(user_name);
		UserInfo userInfo=users.get(0);
		String province=userInfo.getNowprovince();
		String city=userInfo.getNowcity();
		String district=userInfo.getNowdistrict();
		String other=userInfo.getNowother();
		//找到同一个district的信息或者活动,其实在限定的时间范围内找寻,原创或者是转发
		//1,首先加入信息 记录
		ArrayList<MessageInfo> messages=(ArrayList<MessageInfo>) messageInfoDAO.findByPCDOAndType(province, city, district,
				other, WebConstant.DATE_TODAY);
		//2,然后加入活动记录，因为要根据活动将要发生的地点来找到实例，而不是根据分布人的地理位置
		messages.addAll((ArrayList<MessageInfo>) messageInfoDAO.findByPCDOAndTypeOfAct(province, city, district,
				other, WebConstant.DATE_TODAY));
		
		if(messages.size()==0){
			messages=(ArrayList<MessageInfo>) messageInfoDAO.findByPCDAndType(province, city, district, WebConstant.DATE_TODAY);
			messages.addAll((ArrayList<MessageInfo>) messageInfoDAO.findByPCDAndTypeOfAct(province, city, district, WebConstant.DATE_TODAY));
		}
		
		int messagesSize=messages.size();
		WebAlgo algo=new WebAlgo();
		ArrayList<MessageInfo> result=new ArrayList<MessageInfo>();
		if(messagesSize!=0){
			//1,去掉和用户感兴趣类别不符的 信息或者活动
			algo.deleteDisInterest(messages,users.get(0).getCategory());
			int changeSize=messages.size();
			if(changeSize!=0){
				//2,按照推荐分,评论数，转发数  计算得来的热度进行排序
				Map<MessageInfo, Double> hotMessages = new HashMap<MessageInfo, Double>();
				double hotValue=0;
				for(MessageInfo hotMember:messages){
					//对于信息，热度值=0.4*推荐数+0.1*推荐分数值+0.3*所有转发数+0.2*所有评论数
					//对于活动，热度值=0.15*推荐数+0.1*推荐分数值+0.3*所有转发数+0.2*所有评论数+0.15*想参加数+0.1*想参加分数值
					
					if(hotMember.getType()==WebConstant.DB_MSGORIGNAL||hotMember.getType()==WebConstant.DB_MSGTRANSMIT){
						
						hotValue=(double) (0.4*hotMember.getRecommend_amount()+0.1*hotMember.getRecommend_value()+
							0.3*hotMember.getTransmit_amount()+0.2*hotMember.getComment_amount());
					}
					else{
						hotValue=(double) (0.15*hotMember.getRecommend_amount()+0.1*hotMember.getRecommend_value()+
								0.3*hotMember.getTransmit_amount()+0.2*hotMember.getComment_amount()+
								0.15*hotMember.getJoin_amount()+0.1*hotMember.getJoin_value());
					}
					
					hotMessages.put(hotMember, hotValue);
				}
				
				//对hotMessages进行快速排序
				List<Map.Entry<MessageInfo, Double>> hotMessagesList =
				    new ArrayList<Map.Entry<MessageInfo, Double>>(hotMessages.entrySet());
				algo.qsortHashMapOfMessageinfoDouble(hotMessagesList, 0, hotMessagesList.size()-1);
				
				
				String coorUser[]=userInfo.getNowcoordinate().split(",");
				double latUser=Double.parseDouble(coorUser[0]);
				double lonUser=Double.parseDouble(coorUser[1]);
				//根据坐标，计算距离，找到距离最近;对于活动，客户端需要获取活动的坐标,数据库中坐标的存储型式为（纬度，经度）
				List<Integer> noChange=new ArrayList<Integer>();
				for(int i=0;i<hotMessagesList.size();i++){
					if(hotMessagesList.get(i).getKey().getType()==WebConstant.DB_MSGORIGNAL || hotMessagesList.get(i).getKey().getType()==WebConstant.DB_MSGTRANSMIT){
						String coor[]=hotMessagesList.get(i).getKey().getCoordinate().split(",");
						double lat=Double.parseDouble(coor[0]);
						double lon=Double.parseDouble(coor[1]);
						hotMessagesList.get(i).setValue(algo.getDistance(lonUser,latUser,lon,lat));
					}
					else{
						if(hotMessagesList.get(i).getKey().getActcoordinate()==null){
							noChange.add(i);
						}
						else{
							String actcoor[]=hotMessagesList.get(i).getKey().getActcoordinate().split(",");
							double lat=Double.parseDouble(actcoor[0]);
							double lon=Double.parseDouble(actcoor[1]);
							hotMessagesList.get(i).setValue(algo.getDistance(lonUser,latUser,lon,lat));
						}
					}
				}
				if(noChange.size()!=0){
					List<Map.Entry<MessageInfo, Double>> changedList=new ArrayList<Map.Entry<MessageInfo, Double>>();
					for(int i=0;i<hotMessagesList.size();i++){
						if(!noChange.contains(i))
							changedList.add(hotMessagesList.get(i));
					}
					
					//对含有距离值的列表进行快速排序,有距离近到远
					algo.qsortHashMapOfMessageinfoDistance(changedList, 0, changedList.size()-1);
					//产生对changedList的迭代器
					Iterator< Map.Entry<MessageInfo, Double> >  changeListIter=changedList.iterator();
					for(int i=0;i<hotMessagesList.size();i++){
						//对于没有坐标的实例，采取不改变排序的位置，而将距离有近到远的排序重新设置到hotMessagesList中
						//绝妙的想法，很精炼的实现方式
						if(!noChange.contains(i))
							hotMessagesList.set(i,changeListIter.next());
					}
				}
				
				
				if(recomType==WebConstant.RECOMMEND_FOLLOWER){
					//找到当前用户所有的关注者
					List<UserUserRelationship> list=userUserRelationshipDAO.findByUserIdAndTypeOfFollower(userInfo.getUser_id());
					List<Integer> followerIdList=new ArrayList<Integer>();
					for(UserUserRelationship t:list)
						followerIdList.add(t.getFollower_id());
					for(Map.Entry<MessageInfo, Double> member:hotMessagesList){
						if(followerIdList.contains(member.getKey().getUserInfo().getUser_id())){
							result.add(member.getKey());
						}
							
					}
				}
				else{
					for(Map.Entry<MessageInfo, Double> member:hotMessagesList){
						result.add(member.getKey());
					}
				}
				
			}
		}
		return makeMessageBean(userInfo,result);
			
	}
	
	@Override
	public List<MessageBean> getWillActivity(String user_name,
			int willActivityType) {
		//处理 将精彩的地点 的Action，根据用户所在的City，去掉不感兴趣类别，按照时间排序，然后按照距离最近排序
		List<UserInfo> users=findByUserName(user_name);
		UserInfo userInfo=users.get(0);
		String province=userInfo.getNowprovince();
		String city=userInfo.getNowcity();
		
		String coorUser[]=userInfo.getNowcoordinate().split(",");
		double latUser=Double.parseDouble(coorUser[0]);
		double lonUser=Double.parseDouble(coorUser[1]);
		
		WebAlgo algo=new WebAlgo();
		
		//找到同一个城市的 活动,原创或者是转发
		ArrayList<MessageInfo> messages=(ArrayList<MessageInfo>) messageInfoDAO.findByPCOfWillAct(province, city,Integer.parseInt(algo.getRealDay()));
		int messagesSize=messages.size();
		ArrayList<MessageInfo> result=new ArrayList<MessageInfo>();
		
		if(messagesSize!=0){
			//1,去掉和用户感兴趣类别不符的 信息或者活动
			algo.deleteDisInterest(messages,users.get(0).getCategory());
			int changeSize=messages.size();
			if(changeSize!=0){
				//2.1，首先按照活动开始日期StartDateTime排序
				algo.qsortMessageByStartDateTime(messages, 0, changeSize-1);
				//2.2，对于同一天按照realTime排序
				int len=messages.size();
				for(int i=0;i<len;i++){
					int index = i;
					for(int j=i+1;j<len;j++){
						if(messages.get(index).getStart_datetime()==messages.get(j).getStart_datetime()){
							index = j;
						}
						else
							break;
					}
					if(index != i){
						algo.qsortMessageByStartRealTime(messages,i, index);
					}
					i=index;
				}
				
				//如果存在时间上相等的情况，则按照信任值高到低排序
				algo.makeSameTimeMaxTrust(messages,changeSize);
				
				Map<MessageInfo, Double> hotMessages = new HashMap<MessageInfo, Double>();
				//根据坐标，计算距离，找到距离最近;对于活动，客户端需要获取活动的坐标,数据库中坐标的存储型式为（纬度，经度）
				List<Integer> noChange=new ArrayList<Integer>();
				for(int i=0;i<messages.size();i++){
					if(messages.get(i).getActcoordinate()==null){
						noChange.add(i);
					}
					else{
						String actcoor[]=messages.get(i).getActcoordinate().split(",");
						double lat=Double.parseDouble(actcoor[0]);
						double lon=Double.parseDouble(actcoor[1]);
						hotMessages.put(messages.get(i), algo.getDistance(lonUser,latUser,lon,lat));
					}
				}
				
				//对hotMessages进行快速排序,按照距离由近到远的排序
				List<Map.Entry<MessageInfo, Double>> hotMessagesList =
				    new ArrayList<Map.Entry<MessageInfo, Double>>(hotMessages.entrySet());
				algo.qsortHashMapOfMessageinfoDistance(hotMessagesList, 0, hotMessagesList.size()-1);
				
				if(noChange.size()!=0){
					//产生对changedList的迭代器
					Iterator< Map.Entry<MessageInfo, Double> >  hotMessagesListIter=hotMessagesList.iterator();
					for(int i=0;i<messages.size();i++){
						//对于没有坐标的实例，采取不改变排序的位置，而将距离有近到远的排序重新设置到hotMessagesList中
						//绝妙的想法，很精炼的实现方式
						if(!noChange.contains(i))
							messages.set(i,hotMessagesListIter.next().getKey());
					}
				}
				
				if(willActivityType==WebConstant.RECOMMEND_FOLLOWER){
					//找到当前用户所有的关注者
					List<UserUserRelationship> list=userUserRelationshipDAO.findByUserIdAndTypeOfFollower(userInfo.getUser_id());
					List<Integer> followerIdList=new ArrayList<Integer>();
					for(UserUserRelationship t:list)
						followerIdList.add(t.getFollower_id());
					for(MessageInfo member:messages){
						if(followerIdList.contains(member.getUserInfo().getUser_id())){
							result.add(member);
						}
							
					}
				}
				else{
					for(MessageInfo member:messages){
						result.add(member);
					}
				}
				
			}//end changeSize
		}
		
		return makeMessageBean(userInfo,result);
	}

	
	@Override
	public List<MessageBean> getMyHome(String user_name) {
		//获得我自己的信息+关注者的信息
		List<UserInfo> users=findByUserName(user_name);
		UserInfo userInfo=users.get(0);

		// 获取所有的信息实例,包括信息原创，信息转发，活动原创，活动转发
		ArrayList<MessageInfo> messages=(ArrayList<MessageInfo>) messageInfoDAO.findAll();
		
		int messagesSize=messages.size();
		ArrayList<MessageInfo> result=new ArrayList<MessageInfo>();
		WebAlgo algo=new WebAlgo();
		if(messagesSize!=0){
			//1,去掉和用户感兴趣类别不符的 信息或者活动
			algo.deleteDisInterest(messages,users.get(0).getCategory());
			int changeSize=messages.size();
			if(changeSize!=0){
				//2.1，首先按照日期dateTime排序
				algo.qsortMessageByDateTime(messages, 0, changeSize-1);
				//2.2，对于同一天按照realTime排序
				int len=messages.size();
				for(int i=0;i<len;i++){
					int index = i;
					for(int j=i+1;j<len;j++){
						if(messages.get(index).getDatetime()==messages.get(j).getDatetime()){
							index = j;
						}
						else
							break;
					}
					if(index != i){
						algo.qsortMessageByRealTime(messages,i, index);
					}
					i=index;
				}
				
				//如果存在时间上相等的情况，则按照信任值高到低排序
				algo.makeSameTimeMaxTrust(messages,changeSize);
				
				//找到当前用户所有的关注者
				List<UserUserRelationship> list=userUserRelationshipDAO.findByUserIdAndTypeOfFollower(userInfo.getUser_id());
				List<Integer> followerIdList=new ArrayList<Integer>();
				for(UserUserRelationship t:list)
					followerIdList.add(t.getFollower_id());
				//还要加入自己的user_id
				followerIdList.add(userInfo.getUser_id());
				for(MessageInfo member:messages){
					if(followerIdList.contains(member.getUserInfo().getUser_id())){
						result.add(member);
					}
				}
				
			}//end changeSize
		}
		
		return makeMessageBean(userInfo,result);
	}
	
	@Override
	public List<MoodBean> getMoodDynamic(String user_name,int moodType) {
		//获得我自己的心情信息+关注者的心情信息
		List<UserInfo> users=findByUserName(user_name);
		UserInfo userInfo=users.get(0);
		
		ArrayList<MessageInfo> messages=null;
		if(moodType==WebConstant.MOOD_FOLLOWER){
		// 获取所有的心情信息实例
			messages=(ArrayList<MessageInfo>) messageInfoDAO.findAllMood();
		}
		else{
			//得到当前用户当前所在地
			String province=userInfo.getNowprovince();
			String city=userInfo.getNowcity();
			messages=(ArrayList<MessageInfo>) messageInfoDAO.findAllMoodOfSameCity(province, city);
		}
		
		int messagesSize=messages.size();
		List<MoodBean> result=new ArrayList<MoodBean>();
		WebAlgo algo=new WebAlgo();
		if(messagesSize!=0){
				//2.1，首先按照日期dateTime排序
				algo.qsortMessageByDateTime(messages, 0, messagesSize-1);
				//2.2，对于同一天按照realTime排序
				int len=messages.size();
				for(int i=0;i<len;i++){
					int index = i;
					for(int j=i+1;j<len;j++){
						if(messages.get(index).getDatetime()==messages.get(j).getDatetime()){
							index = j;
						}
						else
							break;
					}
					if(index != i){
						algo.qsortMessageByRealTime(messages,i, index);
					}
					i=index;
				}
				
				//如果存在时间上相等的情况，则按照信任值高到低排序
				algo.makeSameTimeMaxTrust(messages,messagesSize);
				
				if(moodType==WebConstant.MOOD_FOLLOWER){
					//找到当前用户所有的关注者,得到当前用户的心情信息动态
					List<UserUserRelationship> list=userUserRelationshipDAO.findByUserIdAndTypeOfFollower(userInfo.getUser_id());
					List<Integer> followerIdList=new ArrayList<Integer>();
					for(UserUserRelationship t:list)
						followerIdList.add(t.getFollower_id());
					//还要加入自己的user_id
					followerIdList.add(userInfo.getUser_id());
					
					for(MessageInfo member:messages){
						UserInfo tempUser=member.getUserInfo();
						if(followerIdList.contains(tempUser.getUser_id())){
							String path=tempUser.getAvatar();
							String paths[]=path.split("GuiJi");
							
							String pathmsg=null;
							String pathsmsg[]=null;
							String pathStr=null;
							if(member.getPicture()!=null){
								pathmsg=member.getPicture();
								pathsmsg=pathmsg.split("GuiJi");
								pathStr=pathsmsg[1].replace('\\', '/');
							}
							result.add(new MoodBean(
									tempUser.getUser_id(),
									tempUser.getUser_name(),
									paths[1].replace('\\', '/'),
									member.getMsg_id(),
									member.getMood(),
									member.getMsg_content(),
									pathStr,
									algo.transferVirtualTime(member.getDatetime(),member.getRealtime(),Integer.parseInt(algo.getRealDay()),Integer.parseInt(algo.getRealTime())),
									getBestMsgPlaceName(userInfo, member),
									member.getPictures_amount(),
									member.getComment_amount()
							));
						}
					}//end for
				}// end if(moodType==WebConstant.MOOD_FOLLOWER)
				else{
					
					String coorUser[]=userInfo.getNowcoordinate().split(",");
					double latUser=Double.parseDouble(coorUser[0]);
					double lonUser=Double.parseDouble(coorUser[1]);
					
					//处理当为同城时的情况,然后按照距离由近到远排序
					Map<MessageInfo, Double> hotMessages = new HashMap<MessageInfo, Double>();
					//根据坐标，计算距离，找到距离最近;对于活动，客户端需要获取活动的坐标,数据库中坐标的存储型式为（纬度，经度）
					List<Integer> noChange=new ArrayList<Integer>();
					for(int i=0;i<messages.size();i++){
						if(messages.get(i).getActcoordinate()==null){
							noChange.add(i);
						}
						else{
							String actcoor[]=messages.get(i).getActcoordinate().split(",");
							double lat=Double.parseDouble(actcoor[0]);
							double lon=Double.parseDouble(actcoor[1]);
							hotMessages.put(messages.get(i), algo.getDistance(lonUser,latUser,lon,lat));
						}
					}
					
					//对hotMessages进行快速排序,按照距离由近到远的排序
					List<Map.Entry<MessageInfo, Double>> hotMessagesList =
					    new ArrayList<Map.Entry<MessageInfo, Double>>(hotMessages.entrySet());
					algo.qsortHashMapOfMessageinfoDistance(hotMessagesList, 0, hotMessagesList.size()-1);
					
					if(noChange.size()!=0){
						//产生对changedList的迭代器
						Iterator< Map.Entry<MessageInfo, Double> >  hotMessagesListIter=hotMessagesList.iterator();
						for(int i=0;i<messages.size();i++){
							//对于没有坐标的实例，采取不改变排序的位置，而将距离有近到远的排序重新设置到hotMessagesList中
							//绝妙的想法，很精炼的实现方式
							if(!noChange.contains(i))
								messages.set(i,hotMessagesListIter.next().getKey());
						}
					}
					
					for(MessageInfo member:messages){
						UserInfo tempUser=member.getUserInfo();
						String path=tempUser.getAvatar();
						String paths[]=path.split("GuiJi");
							
						String pathmsg=null;
						String pathsmsg[]=null;
						String pathStr=null;
						if(member.getPicture()!=null){
							pathmsg=member.getPicture();
							pathsmsg=pathmsg.split("GuiJi");
							pathStr=pathsmsg[1].replace('\\', '/');
						}
						result.add(new MoodBean(
								tempUser.getUser_id(),
								tempUser.getUser_name(),
								paths[1].replace('\\', '/'),
								member.getMsg_id(),
								member.getMood(),
								member.getMsg_content(),
								pathStr,
								algo.transferVirtualTime(member.getDatetime(),member.getRealtime(),Integer.parseInt(algo.getRealDay()),Integer.parseInt(algo.getRealTime())),
								getBestMsgPlaceName(userInfo, member),
								member.getPictures_amount(),
								member.getComment_amount()
							));
						}
					}// end else
				}//end if(messagesSize!=0)
		return result;
	}

	@Override
	public List<TopListBean> getTopList() {
		//对于每个分类排行值计算：原创*0.5+评论*0.15+转发*0.35
		Map<Integer, Float> topList = new HashMap<Integer, Float>();
		List<MessageInfo> totalMsgByType=null;
		
		float totalOfComment=0;
		float totalOfTransmit=0;
		float totalOfOrignal=0;
		float totalValue=0;
		
		WebAlgo algo=new WebAlgo();
		
		for(int i=1;i<=WebConstant.C_TOTAL;i++){
			totalMsgByType=messageInfoDAO.findAllByType(i);
			totalOfComment=0;
			totalOfTransmit=0;
			totalOfOrignal=0;
			totalValue=0;
			for(MessageInfo member:totalMsgByType){
				if(member.getType()==WebConstant.DB_ACTCOMMENT||member.getType()==WebConstant.DB_MSGCOMMENT){
					totalOfComment++;
				}
				else if(member.getType()==WebConstant.DB_ACTORIGNAL||member.getType()==WebConstant.DB_MSGORIGNAL){
					totalOfOrignal++;
				}
				else
					totalOfTransmit++;
			}
			totalValue=(float) (totalOfOrignal*0.5+totalOfComment*0.15+totalOfOrignal*0.35);
			
			topList.put(i, totalValue);
			
		}//end for
		//对topList进行快速排序，由高到低排序
		List<Map.Entry<Integer, Float>> topMessagesList =
		    new ArrayList<Map.Entry<Integer, Float>>(topList.entrySet());
		algo.qsortTopList(topMessagesList,0,topMessagesList.size()-1);
		
		List<TopListBean> result=new ArrayList<TopListBean>();
		for(Map.Entry<Integer, Float> member:topMessagesList){
			result.add(new TopListBean(
					member.getKey(),
					algo.getCategoryIS(member.getKey()),
					member.getValue()
			));
		}
		
		return result;
	}
	
	@Override
	public MoodBean getMoodInfo(String user_name) {
		List<UserInfo> users=findByUserName(user_name);
		UserInfo userInfo=users.get(0);
		String path=userInfo.getAvatar();
		String paths[]=path.split("GuiJi");
		ArrayList<MessageInfo> messages=(ArrayList<MessageInfo>) messageInfoDAO.findRecentMood(userInfo.getUser_id());
		WebAlgo algo=new WebAlgo();
		MoodBean result=null;
		if(messages!=null && (messages.size() != 0) ){
			algo.qsortMessageByRealTime(messages,0,messages.size()-1);
			//取最近的心情信息
			MessageInfo messageInfo=messages.get(0);
			String pathmsg=null;
			String pathsmsg[]=null;
			String pathStr=null;
			if(messageInfo.getPicture()!=null){
				pathmsg=messageInfo.getPicture();
				pathsmsg=pathmsg.split("GuiJi");
				pathStr=pathsmsg[1].replace('\\', '/');
			}
			String place=null;
			if(messageInfo.getProvince()==null){
				place=userInfo.getNowcity()+userInfo.getNowdistrict()+userInfo.getNowother();
			}
			else
				place=messageInfo.getCity()+messageInfo.getDistrict()+messageInfo.getOther();
			result=new MoodBean(
					userInfo.getUser_id(),
					userInfo.getUser_name(),
					paths[1].replace('\\', '/'),
					messageInfo.getMsg_id(),
					messageInfo.getMood(),
					messageInfo.getMsg_content(),
					pathStr,
					algo.transferVirtualTime(messageInfo.getDatetime(),messageInfo.getRealtime(),Integer.parseInt(algo.getRealDay()),Integer.parseInt(algo.getRealTime())),
					place,
					messageInfo.getPictures_amount(),
					messageInfo.getComment_amount()
			);
		}
		else{
			result=new MoodBean(
					userInfo.getUser_id(),
					userInfo.getUser_name(),
					paths[1].replace('\\', '/'),
					0,
					0,
					null,
					null,
					null,
					null,
					0,
					0);
					
		}
		return result;
	}

	@Override
	public List<PicturesetBean> doPictureset(int msg_id, int type) {
		ArrayList<MsgMsgRelationship> msgMsgRelationship=(ArrayList<MsgMsgRelationship>) msgMsgRelationshipDAO.findCommentOrTransmitMsgOrAct(msg_id, type);
		
		List<MsgMsgRelationship> temp=new ArrayList<MsgMsgRelationship>();
		for(MsgMsgRelationship member:msgMsgRelationship){
			MessageInfo messageInfo=messageInfoDAO.get(member.getReference_msg_id());
			if(messageInfo.getPicture()==null){
				temp.add(member);
			}
		}
		msgMsgRelationship.removeAll(temp);
		//根据msgmsgrelation表中的时间进行排序，按照时间最早
		//首先要根据日期dateTime，如果是同一天要根据realTime
		WebAlgo algo=new WebAlgo();
		algo.qsortMsgMsgRDateTime(msgMsgRelationship,0,msgMsgRelationship.size()-1);
		int len=msgMsgRelationship.size();
		for(int i=0;i<len;i++){
			int index=i;//设置相等的下标
			for(int j=i+1;j<len;j++){
				if(msgMsgRelationship.get(index).getDatetime()==msgMsgRelationship.get(j).getDatetime()){
					index=j;
				}
				else
					break;
			}
			if(index != i){
				algo.qsortMsgMsgRRealTime(msgMsgRelationship, i, index);
			}
			i=index;//重新设置开始搜索的位置
		}
		//到这里，已经将所有评论的信息按照时间最早排序
		List<PicturesetBean> result=new ArrayList<PicturesetBean>();
		ActionContext ctx=ActionContext.getContext();
		UserInfo userInfo=findByUserName((String)ctx.getSession().get("user")).get(0);
		for(MsgMsgRelationship msgmsgR:msgMsgRelationship){
			MessageInfo m=messageInfoDAO.get(msgmsgR.getReference_msg_id());
			String path=m.getPicture();
			String paths[]=path.split("GuiJi");
			
			result.add(new PicturesetBean(
					msgmsgR.getReference_id(),
					userInfoDAO.get(msgmsgR.getReference_id()).getUser_name(),
					algo.transferVirtualTime(msgmsgR.getDatetime(), msgmsgR.getRealtime(),Integer.parseInt(algo.getRealDay()),Integer.parseInt(algo.getRealTime())),
					getBestMsgPlaceName(userInfo, m),
					paths[1].replace('\\', '/')
					));
		}
		return result;
	}
	
	

	@Override
	public void doRecommend(int msg_id,int recommend_value) {
		WebAlgo algo=new WebAlgo();
		MessageInfo messageInfo=messageInfoDAO.get(msg_id);
		messageInfo.setRecommend_amount(messageInfo.getRecommend_amount()+1);
		messageInfo.setRecommend_value(messageInfo.getRecommend_value()+recommend_value);
		messageInfoDAO.update(messageInfo);
		//加入推荐日志
		LogInfo logInfo = new LogInfo();
		UserInfo userInfo=messageInfo.getUserInfo();
		logInfo.setUserInfo(userInfo);
		if(messageInfo.getType()==WebConstant.DB_MSGORIGNAL || messageInfo.getType()==WebConstant.DB_MSGTRANSMIT)
			addLogInfo(userInfo, WebConstant.OP_RECOMMENDMSG, 0, userInfo.getNowcoordinate(),
					userInfo.getNowprovince(), userInfo.getNowcity(), userInfo.getNowdistrict(), userInfo.getNowother(),
					Integer.parseInt(algo.getRealTime()),Integer.parseInt(algo.getRealDay()));
		else
			addLogInfo(userInfo, WebConstant.OP_RECOMMENDACT, 0, userInfo.getNowcoordinate(),
					userInfo.getNowprovince(), userInfo.getNowcity(), userInfo.getNowdistrict(), userInfo.getNowother(),
					Integer.parseInt(algo.getRealTime()),Integer.parseInt(algo.getRealDay()));
	}

	@Override
	public void doDeleteMsgOrAct(int msg_id, int type) {
		//对信息或者活动 为转发的情况
		if(type==WebConstant.T_MSGTRANSMIT || type==WebConstant.T_ACTTRANSMIT){
			List<MsgMsgRelationship> commentList=null;
			if(type==WebConstant.T_MSGTRANSMIT){
				commentList=msgMsgRelationshipDAO.findCommentOrTransmitMsgOrAct(msg_id, WebConstant.R_MSGCOMMENT);
			}
			else{
				commentList=msgMsgRelationshipDAO.findCommentOrTransmitMsgOrAct(msg_id, WebConstant.R_ACTCOMMENT);
			}
			
			//删除与当前信息有关的所有评论信息，MessageInfo
			for(MsgMsgRelationship commember:commentList){
					messageInfoDAO.delete(commember.getReference_msg_id());
				}
			
			//根据信息主键，删除User_msg_index的记录
			userMsgIndexDAO.deleteByMsg_Id(msg_id);
			
			if(type==WebConstant.T_MSGTRANSMIT){
				//删除对当前信息的所有评论信息之间的关系记录
				msgMsgRelationshipDAO.deleteByReferenced_Msg_IdAndType(msg_id, WebConstant.R_MSGCOMMENT);
				//删除当前信息 与 原创信息 之间的转发关系记录
				msgMsgRelationshipDAO.deleteByReference_Msg_IdAndType(msg_id, WebConstant.R_MSGTRANSMIT);
			}
			else{
				//删除对当前信息的所有评论信息之间的关系记录
				msgMsgRelationshipDAO.deleteByReferenced_Msg_IdAndType(msg_id, WebConstant.R_ACTCOMMENT);
				//删除当前信息 与 原创信息 之间的转发关系记录
				msgMsgRelationshipDAO.deleteByReference_Msg_IdAndType(msg_id, WebConstant.R_ACTTRANSMIT);
			}
				
		}
		
		////对信息或者活动 为转发的情况
		if(type==WebConstant.T_ACTORIGNAL || type==WebConstant.T_MSGORIGNAL){
			//所有转发信息
			List<MsgMsgRelationship> commentList=null;
			List<MsgMsgRelationship> transmitList=null;
			if(type==WebConstant.T_MSGORIGNAL){
				commentList=msgMsgRelationshipDAO.findCommentOrTransmitMsgOrAct(msg_id, WebConstant.R_MSGCOMMENT);
				transmitList=msgMsgRelationshipDAO.findCommentOrTransmitMsgOrAct(msg_id, WebConstant.R_MSGTRANSMIT);
			}
			else{
				commentList=msgMsgRelationshipDAO.findCommentOrTransmitMsgOrAct(msg_id, WebConstant.R_ACTCOMMENT);
				transmitList=msgMsgRelationshipDAO.findCommentOrTransmitMsgOrAct(msg_id, WebConstant.R_ACTTRANSMIT);
			}
			
			//删除当前信息的所有 评论信息 ，MessageInfo
			for(MsgMsgRelationship commentMember:commentList){
				messageInfoDAO.delete(commentMember.getReference_msg_id());
			}
			//删除当前信息与所有评论信息的 评论关系 信息
			if(type==WebConstant.T_MSGORIGNAL){
				msgMsgRelationshipDAO.deleteByReferenced_Msg_IdAndType(msg_id, WebConstant.R_MSGCOMMENT);
			}
			else{
				msgMsgRelationshipDAO.deleteByReferenced_Msg_IdAndType(msg_id, WebConstant.R_ACTCOMMENT);
			}
			
			
			//根据当前信息主键，删除User_msg_index的记录
			userMsgIndexDAO.deleteByMsg_Id(msg_id);
			
			
			for(MsgMsgRelationship transmitMember:transmitList){
				//找到所有转发信息的 评论信息
				List<MsgMsgRelationship> transmitListOfComment=null;
				if(type==WebConstant.T_MSGORIGNAL)
					transmitListOfComment=msgMsgRelationshipDAO.findCommentOrTransmitMsgOrAct(transmitMember.getReference_msg_id(), WebConstant.R_MSGCOMMENT);
				else
					transmitListOfComment=msgMsgRelationshipDAO.findCommentOrTransmitMsgOrAct(transmitMember.getReference_msg_id(), WebConstant.R_ACTCOMMENT);
				//删除与当前信息有关的所有评论信息，MessageInfo
				for(MsgMsgRelationship trancommember:transmitListOfComment){
						messageInfoDAO.delete(trancommember.getReference_msg_id());
					}
				
				//删除当前信息的转发信息，MessageInfo
				messageInfoDAO.delete(transmitMember.getReference_msg_id());
				//根据信息主键，删除User_msg_index的记录
				userMsgIndexDAO.deleteByMsg_Id(transmitMember.getReference_msg_id());
				
				
				if(type==WebConstant.T_MSGORIGNAL){
					//删除对当前信息的转发信息的所有评论信息之间的关系记录
					msgMsgRelationshipDAO.deleteByReferenced_Msg_IdAndType(transmitMember.getReference_msg_id(), WebConstant.R_MSGCOMMENT);
					//删除当前信息的转发信息 与 原创信息 之间的转发关系记录
					msgMsgRelationshipDAO.deleteByReference_Msg_IdAndType(transmitMember.getReference_msg_id(), WebConstant.R_MSGTRANSMIT);
				}
				else{
					//删除对当前信息的转发信息的所有评论信息之间的关系记录
					msgMsgRelationshipDAO.deleteByReferenced_Msg_IdAndType(transmitMember.getReference_msg_id(), WebConstant.R_ACTCOMMENT);
					//删除当前信息的转发信息 与 原创信息 之间的转发关系记录
					msgMsgRelationshipDAO.deleteByReference_Msg_IdAndType(transmitMember.getReference_msg_id(), WebConstant.R_ACTTRANSMIT);
				}
			}
			
			//删除当前信息与所有转发信息的 转发关系 信息
			if(type==WebConstant.T_MSGORIGNAL){
				msgMsgRelationshipDAO.deleteByReferenced_Msg_IdAndType(msg_id, WebConstant.R_MSGTRANSMIT);
			}
			else{
				msgMsgRelationshipDAO.deleteByReferenced_Msg_IdAndType(msg_id, WebConstant.R_ACTTRANSMIT);
			}
			
		}
		
		WebAlgo algo=new WebAlgo();
		//写入日志表
		LogInfo logInfo=new LogInfo();
		UserInfo userInfo=messageInfoDAO.get(msg_id).getUserInfo();
		logInfo.setUserInfo(userInfo);
		if(type==WebConstant.T_MSGORIGNAL || type==WebConstant.T_MSGTRANSMIT)
			addLogInfo(userInfo, WebConstant.OP_DELETEMSG, 0, userInfo.getNowcoordinate(),
					userInfo.getNowprovince(), userInfo.getNowcity(), userInfo.getNowdistrict(), userInfo.getNowother(),
					Integer.parseInt(algo.getRealTime()),Integer.parseInt(algo.getRealDay()));
		else if(type==WebConstant.T_ACTORIGNAL || type==WebConstant.T_ACTTRANSMIT)
			addLogInfo(userInfo, WebConstant.OP_DELETEACTIVITY, 0, userInfo.getNowcoordinate(),
					userInfo.getNowprovince(), userInfo.getNowcity(), userInfo.getNowdistrict(), userInfo.getNowother(),
					Integer.parseInt(algo.getRealTime()),Integer.parseInt(algo.getRealDay()));
		
		messageInfoDAO.delete(msg_id);//删除当前信息，在message_info表
	}

	@Override
	public void doJoin(int msg_id, int recommend_value) {
		WebAlgo algo=new WebAlgo();
		
		MessageInfo messageInfo=messageInfoDAO.get(msg_id);
		messageInfo.setJoin_amount(messageInfo.getJoin_amount()+1);
		messageInfo.setJoin_value(messageInfo.getJoin_value()+recommend_value);
		messageInfoDAO.update(messageInfo);
		
		//加入想参加  日志
		UserInfo userInfo=messageInfo.getUserInfo();
		addLogInfo(userInfo, WebConstant.OP_WANTJOIN, 0, userInfo.getNowcoordinate(),
				userInfo.getNowprovince(), userInfo.getNowcity(),userInfo.getNowdistrict(), userInfo.getNowother(),
				Integer.parseInt(algo.getRealTime()),Integer.parseInt(algo.getRealDay()));
	}

	@Override
	public TransmitBean getTransmitBean(UserInfo userInfo,int msg_id, int type) {
		MessageInfo messageInfo=messageInfoDAO.get(msg_id);
		String path=null;
		String paths[]=null;
		String pathStr=null;
		TransmitBean result=null;
		WebAlgo algo=new WebAlgo();
		
		if(type==WebConstant.T_ACTORIGNAL ||type==WebConstant.T_MSGORIGNAL){
			if(messageInfo.getPicture()!=null){
				path=messageInfo.getPicture();
				paths=path.split("GuiJi");
				pathStr=paths[1].replace('\\', '/');
			}
			if(type==WebConstant.T_MSGORIGNAL){
				result=new TransmitBean(
						0,
						null,
						null,
						messageInfo.getUserInfo().getUser_id(),
						messageInfo.getUserInfo().getUser_name(),
						messageInfo.getMsg_content(),
						pathStr);
			}
			else{
				result=new TransmitBean(
						0,
						null,
						null,
						messageInfo.getUserInfo().getUser_id(),
						messageInfo.getUserInfo().getUser_name(),
						messageInfo.getMsg_content(),
						pathStr,
						algo.transferTimeIStr(messageInfo.getStart_datetime(),messageInfo.getStart_realtime()),
						algo.transferTimeIStr(messageInfo.getEnd_datetime(), messageInfo.getEnd_realtime()),
						getBestActPlaceName(userInfo, messageInfo),
						messageInfo.getLeader_name()
						);
			}
		}
		else {
			if(type==WebConstant.T_MSGTRANSMIT ||type==WebConstant.T_ACTTRANSMIT){
				List<MsgMsgRelationship> msgMsgRelationship=null;
				if(type==WebConstant.T_MSGTRANSMIT)
					msgMsgRelationship=msgMsgRelationshipDAO.findOriginalMessage(msg_id, WebConstant.R_MSGTRANSMIT);
				else
					msgMsgRelationship=msgMsgRelationshipDAO.findOriginalMessage(msg_id, WebConstant.R_ACTTRANSMIT);
//				MessageInfo orignalMessage=messageInfoDAO.get(msgMsgRelationship.get(0).getReference_msg_id());
				MessageInfo orignalMessage=messageInfoDAO.get(msgMsgRelationship.get(0).getReferenced_msg_id());
				
				if(orignalMessage.getPicture()!=null){
					path=orignalMessage.getPicture();
					paths=path.split("GuiJi");
					pathStr=paths[1].replace('\\', '/');
				}
				
				if(type==WebConstant.T_MSGTRANSMIT){
					result=new TransmitBean(
							messageInfo.getUserInfo().getUser_id(),
							messageInfo.getUserInfo().getUser_name(),
							messageInfo.getMsg_content(),
							orignalMessage.getUserInfo().getUser_id(),
							orignalMessage.getUserInfo().getUser_name(),
							orignalMessage.getMsg_content(),
							pathStr);
				}
				else{
					result=new TransmitBean(
							messageInfo.getUserInfo().getUser_id(),
							messageInfo.getUserInfo().getUser_name(),
							messageInfo.getMsg_content(),
							orignalMessage.getUserInfo().getUser_id(),
							orignalMessage.getUserInfo().getUser_name(),
							orignalMessage.getMsg_content(),
							pathStr,
							algo.transferTimeIStr(orignalMessage.getStart_datetime(),orignalMessage.getStart_realtime()),
							algo.transferTimeIStr(orignalMessage.getEnd_datetime(), orignalMessage.getEnd_realtime()),
							getBestActPlaceName(userInfo, orignalMessage),
							orignalMessage.getLeader_name()
							);
				}
			}
				
		}
		
		
		return result;
	}

	@Override
	public void doTransmit(UserInfo userInfo, int msg_id,int firstMsgId, int type,
			String msg_content,boolean comment,boolean orignalComment) {
		MessageInfo transmitMessageInfo=new MessageInfo();  //转发记录信息
		MessageInfo commentMessageInfo=null;    			//评论记录信息
		
		MessageInfo mm=messageInfoDAO.get(msg_id);//转发的该信息
		MsgMsgRelationship commentMsgMsgRelationship=new MsgMsgRelationship();
		MsgMsgRelationship transmitMsgMsgRelationship=new MsgMsgRelationship();
		MsgMsgRelationship orignalCommentMsgMsgRelationship=new MsgMsgRelationship();
		
		
		MessageInfo orginal=null;
		
		LogInfo commentLogInfo=new LogInfo();
		LogInfo transmitLogInfo=new LogInfo();
		
		if(orignalComment==true){
			orginal=messageInfoDAO.get(firstMsgId);
		}
			
		
		if(type==WebConstant.T_MSGTRANSMIT ||type==WebConstant.T_MSGORIGNAL){
			//创建新的MessageInfo
			transmitMessageInfo.setType(WebConstant.DB_MSGTRANSMIT);
			commentMessageInfo=new MessageInfo();
			
			//对  转发  的信息进行更新
			mm.setTransmit_amount(mm.getTransmit_amount()+1);
			mm.setTransmited_amount(mm.getTransmited_amount()+1);
			
			//转发关系记录
			transmitMsgMsgRelationship.setType(WebConstant.R_MSGTRANSMIT);
			
			if(comment==true){
				commentMsgMsgRelationship.setType(WebConstant.R_MSGCOMMENT);
				commentMessageInfo.setType(WebConstant.DB_MSGCOMMENT);
			}
			if(orignalComment==true){
				orignalCommentMsgMsgRelationship.setType(WebConstant.R_MSGCOMMENT);
				commentLogInfo.setOperate(WebConstant.OP_COMMENTORIGNAL);
				
				commentMessageInfo.setType(WebConstant.DB_MSGCOMMENT);
			}
			
		}
		else {
			//创建新的MessageInfo
			transmitMessageInfo.setType(WebConstant.DB_ACTTRANSMIT);
			commentMessageInfo=new MessageInfo();
			
			//对  转发  的信息进行更新
			mm.setTransmit_amount(mm.getTransmit_amount()+1);
			mm.setTransmited_amount(mm.getTransmited_amount()+1);
			
			//转发关系记录
			transmitMsgMsgRelationship.setType(WebConstant.R_ACTTRANSMIT);
			
			if(comment==true){
				commentMsgMsgRelationship.setType(WebConstant.R_ACTCOMMENT);
				commentMessageInfo.setType(WebConstant.DB_ACTCOMMENT);
			}
			
			if(orignalComment==true){
				orignalCommentMsgMsgRelationship.setType(WebConstant.R_ACTCOMMENT);
				commentLogInfo.setOperate(WebConstant.OP_COMMENTAND);
				
				commentMessageInfo.setType(WebConstant.DB_ACTCOMMENT);
			}
		}
		if(comment==true && orignalComment ==true)
			commentLogInfo.setOperate(WebConstant.OP_COMMENTAND);
		
		WebAlgo algo=new WebAlgo();
		
		transmitMessageInfo.setUserInfo(userInfo);
		transmitMessageInfo.setMsg_content(msg_content);
		transmitMessageInfo.setRealtime(Integer.parseInt(algo.getRealTime()));
		transmitMessageInfo.setDatetime(Integer.parseInt(algo.getRealDay()));
		transmitMessageInfo.setCoordinate(userInfo.getNowcoordinate());
		transmitMessageInfo.setProvince(userInfo.getNowprovince());
		transmitMessageInfo.setCity(userInfo.getNowcity());
		transmitMessageInfo.setDistrict(userInfo.getNowdistrict());
		transmitMessageInfo.setOther(userInfo.getNowother());
		transmitMessageInfo.setCategory(mm.getCategory());
		messageInfoDAO.save(transmitMessageInfo);
		
		if(comment==true || orignalComment==true){
			commentMessageInfo.setUserInfo(userInfo);
			commentMessageInfo.setMsg_content(msg_content);
			commentMessageInfo.setRealtime(Integer.parseInt(algo.getRealTime()));
			commentMessageInfo.setDatetime(Integer.parseInt(algo.getRealDay()));
			commentMessageInfo.setCoordinate(userInfo.getNowcoordinate());
			commentMessageInfo.setProvince(userInfo.getNowprovince());
			commentMessageInfo.setCity(userInfo.getNowcity());
			commentMessageInfo.setDistrict(userInfo.getNowdistrict());
			commentMessageInfo.setOther(userInfo.getNowother());
			commentMessageInfo.setCategory(mm.getCategory());
			messageInfoDAO.save(commentMessageInfo);
		}
		
		if(comment==true){
			mm.setComment_amount(mm.getComment_amount()+1);
			mm.setCommented_amount(mm.getCommented_amount()+1);
			
			//增加评论的信息之间关系，msg_msg_relation
			commentMsgMsgRelationship.setReference_id(userInfo.getUser_id());
			commentMsgMsgRelationship.setReference_msg_id(commentMessageInfo.getMsg_id());
			//本来下面设置为messageInfoDAO.get(msg_id).getUserInfo().getUser_id(),但是为了区分回复的情况，将这里设置为0；回复的情况，设置想要的用户序号
			commentMsgMsgRelationship.setReferenced_id(0);
			commentMsgMsgRelationship.setReferenced_msg_id(msg_id);
			commentMsgMsgRelationship.setRealtime(Integer.parseInt(algo.getRealTime()));
			commentMsgMsgRelationship.setDatetime(Integer.parseInt(algo.getRealDay()));
			
			msgMsgRelationshipDAO.save(commentMsgMsgRelationship);
			
			//评论信息的日志记录
			commentLogInfo.setOperate(WebConstant.OP_COMMENT);
		}
		messageInfoDAO.update(mm);
		
		//增加转发的信息之间关系，msg_msg_relation
		transmitMsgMsgRelationship.setReference_id(userInfo.getUser_id());
		transmitMsgMsgRelationship.setReference_msg_id(transmitMessageInfo.getMsg_id());
		transmitMsgMsgRelationship.setReferenced_id(messageInfoDAO.get(msg_id).getUserInfo().getUser_id());
		transmitMsgMsgRelationship.setReferenced_msg_id(msg_id);
		transmitMsgMsgRelationship.setRealtime(Integer.parseInt(algo.getRealTime()));
		transmitMsgMsgRelationship.setDatetime(Integer.parseInt(algo.getRealDay()));
		msgMsgRelationshipDAO.save(transmitMsgMsgRelationship);
		
		if(orignalComment==true){
			orginal.setComment_amount(orginal.getComment_amount()+1);
			orginal.setCommented_amount(orginal.getCommented_amount()+1);
			
			messageInfoDAO.update(orginal);
			
			//增加评论的信息之间关系，msg_msg_relation
			orignalCommentMsgMsgRelationship.setReference_id(userInfo.getUser_id());
			orignalCommentMsgMsgRelationship.setReference_msg_id(commentMessageInfo.getMsg_id());
			//本来下面设置为messageInfoDAO.get(firstMsgId).getUserInfo().getUser_id(),但是为了区分回复的情况，将这里设置为0；回复的情况，设置想要的用户序号
			orignalCommentMsgMsgRelationship.setReferenced_id(0);
			orignalCommentMsgMsgRelationship.setReferenced_msg_id(firstMsgId);
			orignalCommentMsgMsgRelationship.setRealtime(Integer.parseInt(algo.getRealTime()));
			orignalCommentMsgMsgRelationship.setDatetime(Integer.parseInt(algo.getRealDay()));
			
			msgMsgRelationshipDAO.save(orignalCommentMsgMsgRelationship);
		}
		
		
		//对日志信息
		transmitLogInfo.setOperate(WebConstant.OP_TRANSMITMSG);
		
		commentLogInfo.setUserInfo(userInfo);
		commentLogInfo.setCoordinate(userInfo.getNowcoordinate());
		commentLogInfo.setProvince(userInfo.getNowprovince());
		commentLogInfo.setCity(userInfo.getNowcity());
		commentLogInfo.setDistrict(userInfo.getNowdistrict());
		commentLogInfo.setOther(userInfo.getNowother());
		commentLogInfo.setOprealtime(Integer.parseInt(algo.getRealTime()));
		commentLogInfo.setOpdatetime(Integer.parseInt(algo.getRealDay()));
		logInfoDAO.save(commentLogInfo);
		
		transmitLogInfo.setUserInfo(userInfo);
		transmitLogInfo.setCoordinate(userInfo.getNowcoordinate());
		transmitLogInfo.setProvince(userInfo.getNowprovince());
		transmitLogInfo.setCity(userInfo.getNowcity());
		transmitLogInfo.setDistrict(userInfo.getNowdistrict());
		transmitLogInfo.setOther(userInfo.getNowother());
		transmitLogInfo.setOprealtime(Integer.parseInt(algo.getRealTime()));
		transmitLogInfo.setOpdatetime(Integer.parseInt(algo.getRealDay()));
		logInfoDAO.save(transmitLogInfo);
		
		
		//最后对自己的user_message_index，增加记录
		UserMsgIndex userMsgIndex=new UserMsgIndex();
		userMsgIndex.setUserInfo(userInfo);
		userMsgIndex.setAuthor_id(userInfo.getUser_id());
		userMsgIndex.setMessageInfo(transmitMessageInfo);
		userMsgIndex.setRealtime(Integer.parseInt(algo.getRealTime()));
		userMsgIndex.setDatetime(Integer.parseInt(algo.getRealDay()));
		userMsgIndexDAO.save(userMsgIndex);
		
		
		//对自己的关注者进行广播信息
		List<UserUserRelationship> userUserRelationship=userUserRelationshipDAO.findByUserIdAndType(userInfo.getUser_id(), WebConstant.UUR_FANS);
		if(userUserRelationship.size()!=0){
			for(UserUserRelationship member:userUserRelationship){
				UserMsgIndex umi=new UserMsgIndex();
				umi.setUserInfo(userInfoDAO.get(member.getFollower_id()));
				umi.setAuthor_id(userInfo.getUser_id());
				umi.setMessageInfo(transmitMessageInfo);
				umi.setRealtime(Integer.parseInt(algo.getRealTime()));
				umi.setDatetime(Integer.parseInt(algo.getRealDay()));
				userMsgIndexDAO.save(umi);
			}
		}
		
	}

	
	@Override
	public String getOrignalUserName(int msg_id, int type) {
		String userName = null;
		if(type==WebConstant.T_ACTTRANSMIT || type==WebConstant.T_MSGTRANSMIT){
			List<MsgMsgRelationship> msgMsgRelationship=null;
			if(type==WebConstant.T_MSGTRANSMIT){
				msgMsgRelationship=msgMsgRelationshipDAO.findOriginalMessage(msg_id, WebConstant.R_MSGTRANSMIT);
			}
			else
				msgMsgRelationship=msgMsgRelationshipDAO.findOriginalMessage(msg_id, WebConstant.R_ACTTRANSMIT);
			userName=messageInfoDAO.get(msgMsgRelationship.get(0).getReferenced_msg_id()).getUserInfo().getUser_name();
		}
		
		return userName;
	}

	@Override
	public List<CommentBean> getCommentBean(UserInfo userInfo,int msg_id, int type) {
		List<CommentBean> result=new ArrayList<CommentBean>();
		//评论的关系ArrayList
		ArrayList<MsgMsgRelationship> commentMsgMsgRelationship=null;
		if(type==WebConstant.T_MSGORIGNAL || type==WebConstant.T_MSGTRANSMIT)
			commentMsgMsgRelationship=(ArrayList<MsgMsgRelationship>) msgMsgRelationshipDAO.findCommentOrTransmitMsgOrAct(msg_id, WebConstant.R_MSGCOMMENT);
		else
			commentMsgMsgRelationship=(ArrayList<MsgMsgRelationship>) msgMsgRelationshipDAO.findCommentOrTransmitMsgOrAct(msg_id, WebConstant.R_ACTCOMMENT);
		WebAlgo algo=new WebAlgo();
		
		//根据msgmsgrelation表中的时间进行排序，按照时间最早
		//首先要根据日期dateTime，如果是同一天要根据realTime
		algo.qsortMsgMsgRDateTime(commentMsgMsgRelationship,0,commentMsgMsgRelationship.size()-1);
		int len=commentMsgMsgRelationship.size();
		for(int i=0;i<len;i++){
			int index=i;//设置相等的下标
			for(int j=i+1;j<len;j++){
				if(commentMsgMsgRelationship.get(index).getDatetime()==commentMsgMsgRelationship.get(j).getDatetime()){
					index=j;
				}
				else
					break;
			}
			if(index != i){
				algo.qsortMsgMsgRRealTime(commentMsgMsgRelationship, i, index);
			}
			i=index;//重新设置开始搜索的位置
		}
		//到这里，已经将所有评论的信息按照时间最早排序
		
		for(MsgMsgRelationship member:commentMsgMsgRelationship){
			MessageInfo message=messageInfoDAO.get(member.getReference_msg_id());
			String path=message.getUserInfo().getAvatar();
			String paths[]=path.split("GuiJi");
			
			String picPath=null;
			String picPaths[]=null;
			String pathStr=null;
			if(message.getPicture()!=null){
				picPath=message.getPicture();
				picPaths=picPath.split("GuiJi");
				pathStr=picPaths[1].replace('\\', '/');
			}
			String tempReferencedName=null;
			if(member.getReferenced_id()!=0)
				tempReferencedName=userInfoDAO.get(member.getReferenced_id()).getUser_name();
			
			result.add(new CommentBean(
					message.getMsg_id(),
					message.getUserInfo().getUser_id(),
					message.getUserInfo().getUser_name(),
					member.getReferenced_id(),
					tempReferencedName,
					messageInfoDAO.get(member.getReferenced_msg_id()).getUserInfo().getUser_name(),
					paths[1].replace('\\', '/'),
					message.getMsg_content(),
					pathStr,
					algo.transferVirtualTime(message.getDatetime(),message.getRealtime(),Integer.parseInt(algo.getRealDay()),Integer.parseInt(algo.getRealTime())),
					getBestMsgPlaceName(userInfo, message)
			));
		}
		return result;
	}
	
	@Override
	public List<CommentBean> getCommentMoodBean(UserInfo userInfo,int msg_id) {
		List<CommentBean> result=new ArrayList<CommentBean>();
		//评论的关系ArrayList
		ArrayList<MsgMsgRelationship> commentMsgMsgRelationship=(ArrayList<MsgMsgRelationship>) msgMsgRelationshipDAO.findCommentOrTransmitMsgOrAct(msg_id, WebConstant.R_MOODCOMMENT);
		WebAlgo algo=new WebAlgo();
		
		//根据msgmsgrelation表中的时间进行排序，按照时间最早
		//首先要根据日期dateTime，如果是同一天要根据realTime
		algo.qsortMsgMsgRDateTime(commentMsgMsgRelationship,0,commentMsgMsgRelationship.size()-1);
		int len=commentMsgMsgRelationship.size();
		for(int i=0;i<len;i++){
			int index=i;//设置相等的下标
			for(int j=i+1;j<len;j++){
				if(commentMsgMsgRelationship.get(index).getDatetime()==commentMsgMsgRelationship.get(j).getDatetime()){
					index=j;
				}
				else
					break;
			}
			if(index != i){
				algo.qsortMsgMsgRRealTime(commentMsgMsgRelationship, i, index);
			}
			i=index;//重新设置开始搜索的位置
		}
		//到这里，已经将所有评论的信息按照时间最早排序
		
		for(MsgMsgRelationship member:commentMsgMsgRelationship){
			MessageInfo message=messageInfoDAO.get(member.getReference_msg_id());
			String path=message.getUserInfo().getAvatar();
			String paths[]=path.split("GuiJi");
			
			String picPath=null;
			String picPaths[]=null;
			String pathStr=null;//心情信息图片
			if(message.getPicture()!=null){
				picPath=message.getPicture();
				picPaths=picPath.split("GuiJi");
				pathStr=picPaths[1].replace('\\', '/');
			}
			String tempReferencedName=null;
			if(member.getReferenced_id()!=0)
				tempReferencedName=userInfoDAO.get(member.getReferenced_id()).getUser_name();
			
			result.add(new CommentBean(
					message.getMsg_id(),
					message.getUserInfo().getUser_id(),
					message.getUserInfo().getUser_name(),
					member.getReferenced_id(),
					tempReferencedName,
					messageInfoDAO.get(member.getReferenced_msg_id()).getUserInfo().getUser_name(),
					paths[1].replace('\\', '/'),
					message.getMsg_content(),
					pathStr,
					algo.transferVirtualTime(message.getDatetime(),message.getRealtime(),Integer.parseInt(algo.getRealDay()),Integer.parseInt(algo.getRealTime())),
					getBestMsgPlaceName(userInfo, message)
			));
		}
		return result;
	}

	@Override
	public void addComment(MessageInfo messageInfo,UserInfo userInfo, int msg_id,int firstMsgId,int type, String coordinate,
			String messageContent,boolean comment,boolean transmit) {
		WebAlgo algo=new WebAlgo();
		MsgMsgRelationship commentMsgMsgRelationship= new MsgMsgRelationship();
		MsgMsgRelationship commentOrginalMsgMsgRelationship= new MsgMsgRelationship();
		
		if(type==WebConstant.T_MSGORIGNAL||type==WebConstant.T_MSGTRANSMIT){
			messageInfo.setType(WebConstant.OP_REPLYMSG);
			commentMsgMsgRelationship.setType(WebConstant.R_MSGCOMMENT);
			if(comment==true)
				commentOrginalMsgMsgRelationship.setType(WebConstant.R_MSGCOMMENT);
		}
		else{
			messageInfo.setType(WebConstant.OP_REPLYACT);
			commentMsgMsgRelationship.setType(WebConstant.R_ACTCOMMENT);
			if(comment==true)
				commentOrginalMsgMsgRelationship.setType(WebConstant.R_ACTCOMMENT);
		}
		messageInfo.setMsg_content(messageContent);	
		messageInfo.setRealtime(Integer.parseInt(algo.getRealTime()));
		messageInfo.setDatetime(Integer.parseInt(algo.getRealDay()));
		messageInfo.setCoordinate(coordinate);
		if(coordinate!=null){
			String place[]=algo.getPlaceNameByCoorinateByBaiduMap(coordinate);
			messageInfo.setProvince(place[0]);
			messageInfo.setCity(place[1]);
			messageInfo.setDistrict(place[2]);
			messageInfo.setOther(place[3]);
		}
		else{
			messageInfo.setProvince(userInfo.getNowprovince());
			messageInfo.setCity(userInfo.getNowcity());
			messageInfo.setDistrict(userInfo.getNowdistrict());
			messageInfo.setOther(userInfo.getNowother());
		}
		messageInfo.setCategory(messageInfoDAO.get(msg_id).getCategory());
		messageInfoDAO.save(messageInfo);
		
		//对被评论的该信息进行更新
		MessageInfo mem=messageInfoDAO.get(msg_id);
		mem.setComment_amount(mem.getComment_amount()+1);
		mem.setCommented_amount(mem.getCommented_amount()+1);
		if(messageInfo.getPicture()!=null)
			mem.setPictures_amount(mem.getPictures_amount()+1);
		messageInfoDAO.update(mem);
		
		commentMsgMsgRelationship.setReference_id(userInfo.getUser_id());
		commentMsgMsgRelationship.setReference_msg_id(messageInfo.getMsg_id());
		//本来下面设置为messageInfoDAO.get(msg_id).getUserInfo().getUser_id(),但是为了区分回复的情况，将这里设置为0；回复的情况，设置想要的用户序号
		commentMsgMsgRelationship.setReferenced_id(0);
		commentMsgMsgRelationship.setReferenced_msg_id(msg_id);
		commentMsgMsgRelationship.setRealtime(Integer.parseInt(algo.getRealTime()));
		commentMsgMsgRelationship.setDatetime(Integer.parseInt(algo.getRealDay()));
		msgMsgRelationshipDAO.save(commentMsgMsgRelationship);
		
		if(comment==true){
			commentOrginalMsgMsgRelationship.setReference_id(userInfo.getUser_id());
			commentOrginalMsgMsgRelationship.setReference_msg_id(messageInfo.getMsg_id());
			//本来下面设置为messageInfoDAO.get(firstMsgId).getUserInfo().getUser_id(),,但是为了区分回复的情况，将这里设置为0；回复的情况，设置想要的用户序号
			commentOrginalMsgMsgRelationship.setReferenced_id(0);
			commentOrginalMsgMsgRelationship.setReferenced_msg_id(firstMsgId);
			commentOrginalMsgMsgRelationship.setRealtime(Integer.parseInt(algo.getRealTime()));
			commentOrginalMsgMsgRelationship.setDatetime(Integer.parseInt(algo.getRealDay()));
			msgMsgRelationshipDAO.save(commentOrginalMsgMsgRelationship);
			
			//对原创信息 进行更新
			MessageInfo memOrignal=messageInfoDAO.get(firstMsgId);
			memOrignal.setComment_amount(memOrignal.getComment_amount()+1);
			memOrignal.setCommented_amount(memOrignal.getCommented_amount()+1);
			if(messageInfo.getPicture()!=null)
				memOrignal.setPictures_amount(memOrignal.getPictures_amount()+1);
			messageInfoDAO.update(memOrignal);
		}
		
		if(transmit==true){
			//增加转发消息
			MessageInfo transmitMsg=new MessageInfo();
			transmitMsg.setUserInfo(userInfo);
			transmitMsg.setMsg_content(messageContent);
			
			//对MsgMsgRaltion表增加记录
			MsgMsgRelationship transmitMsgMsgRelationship= new MsgMsgRelationship();
			
			if(type==WebConstant.T_MSGORIGNAL||type==WebConstant.T_MSGTRANSMIT){
				transmitMsg.setType(WebConstant.DB_MSGTRANSMIT);
				transmitMsgMsgRelationship.setType(WebConstant.R_MSGTRANSMIT);
			}
			else{
				transmitMsg.setType(WebConstant.DB_ACTTRANSMIT);
				transmitMsgMsgRelationship.setType(WebConstant.R_ACTTRANSMIT);
			}
				
			transmitMsg.setRealtime(Integer.parseInt(algo.getRealTime()));
			transmitMsg.setDatetime(Integer.parseInt(algo.getRealDay()));
			transmitMsg.setPicture(messageInfo.getPicture());
			transmitMsg.setCoordinate(messageInfo.getCoordinate());
			transmitMsg.setProvince(messageInfo.getProvince());
			transmitMsg.setCity(messageInfo.getCity());
			transmitMsg.setDistrict(messageInfo.getDistrict());
			transmitMsg.setOther(messageInfo.getOther());
			transmitMsg.setCategory(messageInfo.getCategory());
			messageInfoDAO.save(transmitMsg);
			
			MsgMsgRelationship msgmsgr=null;
			int tempmsgid=0;
			int tempuserid=0;
			if(type==WebConstant.T_MSGTRANSMIT||type==WebConstant.T_ACTTRANSMIT){
				msgmsgr=msgMsgRelationshipDAO.findOriginalMessage(msg_id, type).get(0);
				tempmsgid=msgmsgr.getReferenced_msg_id();
				tempuserid=messageInfoDAO.get(tempmsgid).getUserInfo().getUser_id();
			}else{
				tempmsgid=msg_id;
				tempuserid=messageInfoDAO.get(msg_id).getUserInfo().getUser_id();
			}
			transmitMsgMsgRelationship.setReference_id(userInfo.getUser_id());
			transmitMsgMsgRelationship.setReference_msg_id(transmitMsg.getMsg_id());
			transmitMsgMsgRelationship.setReferenced_id(tempuserid);
			transmitMsgMsgRelationship.setReferenced_msg_id(tempmsgid);
//			transmitMsgMsgRelationship.setReferenced_id(messageInfoDAO.get(msg_id).getUserInfo().getUser_id());
//			transmitMsgMsgRelationship.setReferenced_msg_id(msg_id);
			transmitMsgMsgRelationship.setRealtime(Integer.parseInt(algo.getRealTime()));
			transmitMsgMsgRelationship.setDatetime(Integer.parseInt(algo.getRealDay()));
			msgMsgRelationshipDAO.save(transmitMsgMsgRelationship);
			
			
			//最后对自己的user_message_index，增加记录
			UserMsgIndex userMsgIndex=new UserMsgIndex();
			userMsgIndex.setUserInfo(userInfo);
			userMsgIndex.setAuthor_id(userInfo.getUser_id());
			userMsgIndex.setMessageInfo(transmitMsg);
			userMsgIndex.setRealtime(Integer.parseInt(algo.getRealTime()));
			userMsgIndex.setDatetime(Integer.parseInt(algo.getRealDay()));
			userMsgIndexDAO.save(userMsgIndex);
			
			
			//对自己的关注者进行广播信息
			List<UserUserRelationship> userUserRelationship=userUserRelationshipDAO.findByUserIdAndType(userInfo.getUser_id(), WebConstant.UUR_FANS);
			if(userUserRelationship.size()!=0){
				for(UserUserRelationship member:userUserRelationship){
					UserMsgIndex umi=new UserMsgIndex();
					umi.setUserInfo(userInfoDAO.get(member.getFollower_id()));
					umi.setAuthor_id(userInfo.getUser_id());
					umi.setMessageInfo(transmitMsg);
					umi.setRealtime(Integer.parseInt(algo.getRealTime()));
					umi.setDatetime(Integer.parseInt(algo.getRealDay()));
					userMsgIndexDAO.save(umi);
				}
			}
			
		}
		
		//增加评论日志
		if(comment==true)
			addLogInfo(userInfo, WebConstant.OP_COMMENTAND, 0, messageInfo.getCoordinate(),
					messageInfo.getProvince(), messageInfo.getCity(), messageInfo.getDistrict(), messageInfo.getOther(),
					messageInfo.getRealtime(), messageInfo.getDatetime());
		else
			addLogInfo(userInfo, WebConstant.OP_COMMENT, 0, messageInfo.getCoordinate(),
					messageInfo.getProvince(), messageInfo.getCity(), messageInfo.getDistrict(), messageInfo.getOther(),
					messageInfo.getRealtime(), messageInfo.getDatetime());
		
		//增加转发日志
		if(transmit==true){
			if(type==WebConstant.T_MSGORIGNAL||type==WebConstant.T_MSGTRANSMIT)
				addLogInfo(userInfo, WebConstant.OP_TRANSMITMSG, 0, messageInfo.getCoordinate(),
						messageInfo.getProvince(), messageInfo.getCity(), messageInfo.getDistrict(), messageInfo.getOther(),
						messageInfo.getRealtime(), messageInfo.getDatetime());
			else
				addLogInfo(userInfo, WebConstant.OP_TRANSMITACT, 0, messageInfo.getCoordinate(),
						messageInfo.getProvince(), messageInfo.getCity(), messageInfo.getDistrict(), messageInfo.getOther(),
						messageInfo.getRealtime(), messageInfo.getDatetime());
		}
	}

	
	
	@Override
	public void addMoodComment(MessageInfo messageInfo, UserInfo userInfo,
			int msg_id) {
		WebAlgo algo=new WebAlgo();
		//创建评论关系
		MsgMsgRelationship commentMsgMsgRelationship= new MsgMsgRelationship();
		
		messageInfo.setType(WebConstant.DB_MOODCOMMENT);
		if(messageInfo.getCoordinate()==null){
			messageInfo.setCoordinate(userInfo.getNowcoordinate());
			messageInfo.setProvince(userInfo.getNowprovince());
			messageInfo.setCity(userInfo.getNowcity());
			messageInfo.setDistrict(userInfo.getNowdistrict());
			messageInfo.setOther(userInfo.getNowother());
		}
		else{
			String places[]=algo.getPlaceNameByCoorinateByBaiduMap(messageInfo.getCoordinate());
			messageInfo.setProvince(places[0]);
			messageInfo.setCity(places[1]);
			messageInfo.setDistrict(places[2]);
			if(places.length == 4)
				messageInfo.setOther(places[3]);
			else
				messageInfo.setOther("");
		}
		
		messageInfo.setRealtime(Integer.parseInt(algo.getRealTime()));
		messageInfo.setDatetime(Integer.parseInt(algo.getRealDay()));
		messageInfo.setCategory(WebConstant.C_MOOD);
		messageInfoDAO.save(messageInfo);
		
		//对被评论的该信息进行更新
		MessageInfo mem=messageInfoDAO.get(msg_id);
		mem.setComment_amount(mem.getComment_amount()+1);
		mem.setCommented_amount(mem.getCommented_amount()+1);
		if(messageInfo.getPicture()!=null)
			mem.setPictures_amount(mem.getPictures_amount()+1);
		messageInfoDAO.update(mem);
		
		//创建评论关系
		commentMsgMsgRelationship.setType(WebConstant.R_MOODCOMMENT);
		commentMsgMsgRelationship.setReference_id(userInfo.getUser_id());
		commentMsgMsgRelationship.setReference_msg_id(messageInfo.getMsg_id());
		//本来下面设置为messageInfoDAO.get(msg_id).getUserInfo().getUser_id(),但是为了区分回复的情况，将这里设置为0；回复的情况，设置想要的用户序号
		commentMsgMsgRelationship.setReferenced_id(0);
		commentMsgMsgRelationship.setReferenced_msg_id(msg_id);
		commentMsgMsgRelationship.setRealtime(Integer.parseInt(algo.getRealTime()));
		commentMsgMsgRelationship.setDatetime(Integer.parseInt(algo.getRealDay()));
		msgMsgRelationshipDAO.save(commentMsgMsgRelationship);
		
		//增加评论日志
		addLogInfo(userInfo, WebConstant.OP_REPLYMOOD, 0, messageInfo.getCoordinate(),
				messageInfo.getProvince(), messageInfo.getCity(), messageInfo.getDistrict(), messageInfo.getOther(),
				messageInfo.getRealtime(), messageInfo.getDatetime());
	}

	@Override
	public void deleteReplyMsg(int msgId,int commentMsgId) {
		//更新被评论的信息  MessageInfo
		MessageInfo msg=messageInfoDAO.get(msgId);
		msg.setComment_amount(msg.getComment_amount()-1);
		if(messageInfoDAO.get(commentMsgId).getPicture()!=null)
			msg.setPictures_amount(msg.getPictures_amount()-1);
		messageInfoDAO.update(msg);
		
		//删除回复 ，只涉及到两张表msgmsgRelation 和 messageinfo
		messageInfoDAO.delete(commentMsgId);
		
		//更新被评论的信息  MsgMsgRelationship
		msgMsgRelationshipDAO.deleteReply(commentMsgId);
	}

	@Override
	public void doRely(int msg_id, String commentUserName,
			MessageInfo messageInfo,UserInfo userInfo) {
		WebAlgo algo=new WebAlgo();
		//创建日志信息
		LogInfo logInfo= new LogInfo();
		
		//创建评论的msgmsgRealtion记录
		MsgMsgRelationship replyMsgMsgRelationship=new MsgMsgRelationship();
		
		//更新当前信息 的记录
		MessageInfo currentMsg=messageInfoDAO.get(msg_id);
		currentMsg.setComment_amount(currentMsg.getComment_amount()+1);
		currentMsg.setCommented_amount(currentMsg.getCommented_amount()+1);
		if(messageInfo.getPicture()!=null){
			currentMsg.setPictures_amount(currentMsg.getPictures_amount()+1);
		}
		messageInfoDAO.update(currentMsg);
		
		if(currentMsg.getType()==WebConstant.DB_MSGORIGNAL||currentMsg.getType()==WebConstant.DB_MSGTRANSMIT){
			messageInfo.setType(WebConstant.DB_MSGCOMMENT);
			logInfo.setOperate(WebConstant.OP_REPLYMSG);
			replyMsgMsgRelationship.setType(WebConstant.R_MSGCOMMENT);
		}
		else{
			messageInfo.setType(WebConstant.DB_ACTCOMMENT);
			logInfo.setOperate(WebConstant.OP_REPLYACT);
			replyMsgMsgRelationship.setType(WebConstant.R_ACTCOMMENT);
		}
		
		//创建评论信息
		messageInfo.setRealtime(Integer.parseInt(algo.getRealTime()));
		messageInfo.setDatetime(Integer.parseInt(algo.getRealDay()));
		
		if(messageInfo.getCoordinate()==null){
			messageInfo.setProvince(userInfo.getNowprovince());
			messageInfo.setCity(userInfo.getNowcity());
			messageInfo.setDistrict(userInfo.getNowdistrict());
			messageInfo.setOther(userInfo.getNowother());
		}
		else{
			String place[]=algo.getPlaceNameByCoorinateByBaiduMap(messageInfo.getCoordinate());
			messageInfo.setProvince(place[0]);
			messageInfo.setCity(place[1]);
			messageInfo.setDistrict(place[2]);
			messageInfo.setOther(place[3]);
		}
		messageInfo.setCategory(currentMsg.getCategory());
		messageInfoDAO.save(messageInfo);
		
		//创建msgmsgRealtion记录
		replyMsgMsgRelationship.setReference_id(userInfo.getUser_id());
		replyMsgMsgRelationship.setReference_msg_id(messageInfo.getMsg_id());
		replyMsgMsgRelationship.setReferenced_id(findByUserName(commentUserName).get(0).getUser_id());
		replyMsgMsgRelationship.setReferenced_msg_id(msg_id);
		replyMsgMsgRelationship.setRealtime(messageInfo.getRealtime());
		replyMsgMsgRelationship.setDatetime(messageInfo.getDatetime());
		msgMsgRelationshipDAO.save(replyMsgMsgRelationship);
		
		//创建日志信息
		logInfo.setUserInfo(userInfo);
		logInfo.setCoordinate(messageInfo.getCoordinate());
		logInfo.setProvince(messageInfo.getProvince());
		logInfo.setCity(messageInfo.getCity());
		logInfo.setDistrict(messageInfo.getDistrict());
		logInfo.setOther(messageInfo.getOther());
		logInfo.setOprealtime(messageInfo.getRealtime());
		logInfo.setOpdatetime(messageInfo.getDatetime());
		logInfoDAO.save(logInfo);
	}
	
	@Override
	public void doRelyMood(int msg_id, String commentUserName,
			MessageInfo messageInfo, UserInfo userInfo) {
		WebAlgo algo=new WebAlgo();
		//创建日志信息
		LogInfo logInfo= new LogInfo();
		
		//创建评论的msgmsgRealtion记录
		MsgMsgRelationship replyMsgMsgRelationship=new MsgMsgRelationship();
		
		//更新当前信息 的记录
		MessageInfo currentMsg=messageInfoDAO.get(msg_id);
		currentMsg.setComment_amount(currentMsg.getComment_amount()+1);
		currentMsg.setCommented_amount(currentMsg.getCommented_amount()+1);
		if(messageInfo.getPicture()!=null){
			currentMsg.setPictures_amount(currentMsg.getPictures_amount()+1);
		}
		messageInfoDAO.update(currentMsg);
		
		messageInfo.setType(WebConstant.DB_MOODCOMMENT);
		logInfo.setOperate(WebConstant.OP_REPLYMOOD);
		replyMsgMsgRelationship.setType(WebConstant.R_MOODCOMMENT);
		
		//创建评论信息
		messageInfo.setRealtime(Integer.parseInt(algo.getRealTime()));
		messageInfo.setDatetime(Integer.parseInt(algo.getRealDay()));
		
		if(messageInfo.getCoordinate()==null){
			messageInfo.setProvince(userInfo.getNowprovince());
			messageInfo.setCity(userInfo.getNowcity());
			messageInfo.setDistrict(userInfo.getNowdistrict());
			messageInfo.setOther(userInfo.getNowother());
		}
		else{
			String place[]=algo.getPlaceNameByCoorinateByBaiduMap(messageInfo.getCoordinate());
			messageInfo.setProvince(place[0]);
			messageInfo.setCity(place[1]);
			messageInfo.setDistrict(place[2]);
			messageInfo.setOther(place[3]);
		}
		messageInfo.setCategory(currentMsg.getCategory());
		messageInfoDAO.save(messageInfo);
		
		//创建msgmsgRealtion记录
		replyMsgMsgRelationship.setReference_id(userInfo.getUser_id());
		replyMsgMsgRelationship.setReference_msg_id(messageInfo.getMsg_id());
		replyMsgMsgRelationship.setReferenced_id(findByUserName(commentUserName).get(0).getUser_id());
		replyMsgMsgRelationship.setReferenced_msg_id(msg_id);
		replyMsgMsgRelationship.setRealtime(messageInfo.getRealtime());
		replyMsgMsgRelationship.setDatetime(messageInfo.getDatetime());
		msgMsgRelationshipDAO.save(replyMsgMsgRelationship);
		
		//创建日志信息
		logInfo.setUserInfo(userInfo);
		logInfo.setCoordinate(messageInfo.getCoordinate());
		logInfo.setProvince(messageInfo.getProvince());
		logInfo.setCity(messageInfo.getCity());
		logInfo.setDistrict(messageInfo.getDistrict());
		logInfo.setOther(messageInfo.getOther());
		logInfo.setOprealtime(messageInfo.getRealtime());
		logInfo.setOpdatetime(messageInfo.getDatetime());
		logInfoDAO.save(logInfo);
	}

	@Override
	public void doPublishMood(MessageInfo messageInfo, String userName) {
		List<UserInfo> users=findByUserName(userName);
		WebAlgo algo=new WebAlgo();
		UserInfo userInfo=users.get(0);
		if(messageInfo.getCoordinate() ==null || messageInfo.getProvince() == null){
			messageInfo.setCoordinate(userInfo.getNowcoordinate());
			messageInfo.setProvince(userInfo.getNowprovince());
			messageInfo.setCity(userInfo.getNowcity());
			messageInfo.setDistrict(userInfo.getNowdistrict());
			messageInfo.setOther(userInfo.getNowother());
		}
		messageInfo.setCategory(WebConstant.C_MOOD);
		messageInfo.setUserInfo(userInfo);
		messageInfo.setDatetime(Integer.parseInt(algo.getRealDay()));
		messageInfo.setRealtime(Integer.parseInt(algo.getRealTime()));
		messageInfo.setType(WebConstant.DB_MOODORIGNAL);
		messageInfoDAO.save(messageInfo);
		
		//最后对自己的user_message_index，增加记录
		UserMsgIndex userMsgIndex=new UserMsgIndex();
		userMsgIndex.setUserInfo(userInfo);
		userMsgIndex.setAuthor_id(userInfo.getUser_id());
		userMsgIndex.setMessageInfo(messageInfo);
		userMsgIndex.setRealtime(Integer.parseInt(algo.getRealTime()));
		userMsgIndex.setDatetime(Integer.parseInt(algo.getRealDay()));
		userMsgIndexDAO.save(userMsgIndex);

		//对自己的关注者进行广播信息
		List<UserUserRelationship> userUserRelationship=userUserRelationshipDAO.findByUserIdAndType(userInfo.getUser_id(), WebConstant.UUR_FANS);
		if(userUserRelationship.size()!=0){
			for(UserUserRelationship member:userUserRelationship){
				UserMsgIndex umi=new UserMsgIndex();
				umi.setUserInfo(userInfoDAO.get(member.getFollower_id()));
				umi.setAuthor_id(userInfo.getUser_id());
				umi.setMessageInfo(messageInfo);
				umi.setRealtime(Integer.parseInt(algo.getRealTime()));
				umi.setDatetime(Integer.parseInt(algo.getRealDay()));
				userMsgIndexDAO.save(umi);
			}
		}
		
		//创建日志
		addLogInfo(userInfo, WebConstant.OP_MOOD, messageInfo.getMood(), messageInfo.getCoordinate(),
				messageInfo.getProvince(), messageInfo.getCity(), messageInfo.getDistrict(), messageInfo.getOther(),
				messageInfo.getRealtime(), messageInfo.getDatetime());
	}

	@Override
	public void doDeleteMood(int msg_id) {
		//找到当前心情信息的所有评论关系记录
		List<MsgMsgRelationship>  commentOfMsgMsgRelationship=msgMsgRelationshipDAO.findCommentOrTransmitMsgOrAct(msg_id, WebConstant.R_MOODCOMMENT);
		//删除所有评论信息，messageInfo
		for(MsgMsgRelationship member:commentOfMsgMsgRelationship){
			messageInfoDAO.delete(member.getReference_msg_id());
		}
		//删除当前信息和所有评论信息的关系记录
		msgMsgRelationshipDAO.deleteByReferenced_Msg_IdAndType(msg_id,WebConstant.R_MOODCOMMENT);
		
		//删除当前usermsgindex中的记录
		userMsgIndexDAO.deleteByMsg_Id(msg_id);
		
		WebAlgo algo=new WebAlgo();
		//创建记录
		UserInfo userInfo=messageInfoDAO.get(msg_id).getUserInfo();
		addLogInfo(userInfo, WebConstant.OP_DELETEMOODORIGNAL, 0, userInfo.getNowcoordinate(),
				userInfo.getNowprovince(), userInfo.getNowcity(), userInfo.getNowdistrict(), userInfo.getNowother()
				, Integer.parseInt(algo.getRealTime()), Integer.parseInt(algo.getRealDay()));
		
		//删除当前心情信息，messageInfo
		messageInfoDAO.delete(msg_id);
	}

	@Override
	public List<TopUserBean> getClassTopUser(int type) {
		//对于每个分类排行值计算：原创*0.5+评论*0.15+转发*0.35
		//Integer为用户主键，Float为活跃值
		Map<Integer, Float> topList = new HashMap<Integer, Float>();
		WebAlgo algo=new WebAlgo();
		//找到原创和转发的信息,活动
		List<MessageInfo> totalMsgByType=messageInfoDAO.findAllByTypeForClassTopUser(type);
		for(MessageInfo member:totalMsgByType){
			int tempUserId=member.getUserInfo().getUser_id();
			if(member.getType()==WebConstant.DB_ACTCOMMENT||member.getType()==WebConstant.DB_MSGCOMMENT){
				if(topList.containsKey(tempUserId))
					topList.put(tempUserId, (float) (topList.get(tempUserId)+member.getComment_amount()*0.15));
				else
					topList.put(tempUserId, (float) (member.getComment_amount()*0.15));
			}
			else if(member.getType()==WebConstant.DB_ACTORIGNAL||member.getType()==WebConstant.DB_MSGORIGNAL){
				if(member.getType()==WebConstant.DB_ACTORIGNAL){
					if(topList.containsKey(tempUserId))
						topList.put(tempUserId, (float) (topList.get(tempUserId)+0.50*(
							member.getRecommend_amount()*0.15+
							member.getRecommend_value()*0.10+
							member.getTransmit_amount()*0.30+
							member.getComment_amount()*0.20+
							member.getJoin_amount()*0.15+
							member.getJoin_value()*0.10
							)));
					else
						topList.put(tempUserId, (float) (0.50*(
								member.getRecommend_amount()*0.15+
								member.getRecommend_value()*0.10+
								member.getTransmit_amount()*0.30+
								member.getComment_amount()*0.20+
								member.getJoin_amount()*0.15+
								member.getJoin_value()*0.10
								)));
				}
				else{
					if(topList.containsKey(tempUserId))
						topList.put(tempUserId, (float) (topList.get(tempUserId)+0.50*(
							member.getRecommend_amount()*0.40+
							member.getRecommend_value()*0.10+
							member.getTransmit_amount()*0.30+
							member.getComment_amount()*0.20
							)));
					else
						topList.put(tempUserId, (float) (0.50*(
								member.getRecommend_amount()*0.40+
								member.getRecommend_value()*0.10+
								member.getTransmit_amount()*0.30+
								member.getComment_amount()*0.20
								)));
				}
				
			}
			else{
				//当为转发的情况
				if(member.getType()==WebConstant.DB_ACTTRANSMIT){
					if(topList.containsKey(tempUserId))
						topList.put(tempUserId, (float) (topList.get(tempUserId)+0.35*(
							member.getRecommend_amount()*0.15+
							member.getRecommend_value()*0.10+
							member.getTransmit_amount()*0.30+
							member.getComment_amount()*0.20+
							member.getJoin_amount()*0.15+
							member.getJoin_value()*0.10
							)));
					else
						topList.put(tempUserId, (float) (0.35*(
								member.getRecommend_amount()*0.15+
								member.getRecommend_value()*0.10+
								member.getTransmit_amount()*0.30+
								member.getComment_amount()*0.20+
								member.getJoin_amount()*0.15+
								member.getJoin_value()*0.10
								)));
				}
				else if(member.getType()==WebConstant.DB_MSGTRANSMIT){
					if(topList.containsKey(tempUserId))
						topList.put(tempUserId, (float) (topList.get(tempUserId)+0.35*(
							member.getRecommend_amount()*0.40+
							member.getRecommend_value()*0.10+
							member.getTransmit_amount()*0.30+
							member.getComment_amount()*0.20
							)));
					else
						topList.put(tempUserId, (float) (0.35*(
								member.getRecommend_amount()*0.40+
								member.getRecommend_value()*0.10+
								member.getTransmit_amount()*0.30+
								member.getComment_amount()*0.20
								)));
				}
			}
		}//end for(MessageInfo member:totalMsgByType)
		
		//对topList进行快速排序，由高到低排序
		List<Map.Entry<Integer, Float>> topMessagesList =
		    new ArrayList<Map.Entry<Integer, Float>>(topList.entrySet());
		algo.qsortTopList(topMessagesList,0,topMessagesList.size()-1);
		
		ActionContext ctx=ActionContext.getContext();
		String currentUserName=(String) ctx.getSession().get("user");
		List<UserInfo> currentUser=findByUserName(currentUserName);
		List<TopUserBean> result=new ArrayList<TopUserBean>();
		int flag=0;
		for(Map.Entry<Integer, Float> member:topMessagesList){
			if((flag++)!=20){
				UserInfo userInfo=userInfoDAO.get(member.getKey());
				//获取用户与当前用户的关系
				List<UserUserRelationship> uuR=userUserRelationshipDAO.getStatusByUsersId(currentUser.get(0).getUser_id(), userInfo.getUser_id());
				int status=WebConstant.STATUS_NONE;
				if(uuR.size()!=0){
					if(uuR.get(0).getType()==WebConstant.UUR_FANS)
						status=WebConstant.STATUS_FANS;
					else if(uuR.get(0).getType()==WebConstant.UUR_FOLLOWER)
						status=WebConstant.STATUS_FOLLOWER;
					else if(uuR.get(0).getType()==WebConstant.UUR_FRIEND)
						status=WebConstant.STATUS_FRIEND;
					else
						status=WebConstant.STATUS_NONE;
				}
				
				
				String path=userInfo.getAvatar();
				String paths[]=path.split("GuiJi");
				
				result.add(new TopUserBean(
						paths[1].replace('\\', '/'),
						userInfo.getUser_id(),
						userInfo.getUser_name(),
						member.getValue(),
						getMoodInfo(userInfo.getUser_name()).getMood(),
						getMoodInfo(userInfo.getUser_name()).getMoodContent(),
						getMoodInfo(userInfo.getUser_name()).getMoodPic(),
						status,
						getMoodInfo(userInfo.getUser_name()).getTime(),
						getMoodInfo(userInfo.getUser_name()).getPlace(),
						getMoodInfo(userInfo.getUser_name()).getMsgId(),
						getMoodInfo(userInfo.getUser_name()).getCommentCount()
				));
			}// end if((flag++)!=20)
			else
				break;
			
		}
		
		return result;
	}

	@Override
	public void doStatusUUR(int status, int statusValue,int userId) {
		ActionContext ctx=ActionContext.getContext();
		//当前用户
		String currentUserName=(String) ctx.getSession().get("user");
		UserInfo currentUserInfo=userInfoDAO.get(findByUserName(currentUserName).get(0).getUser_id());
		UserUserRelationship userUserRA=null;
		UserUserRelationship userUserRB=null;
		List<UserUserRelationship> useruserRAList=null;
		List<UserUserRelationship> useruserRBList=null;
		if(status==WebConstant.STATUS_NONE){
			if(statusValue==1){
				userUserRA=new UserUserRelationship();
				userUserRA.setUserInfo(currentUserInfo);
				userUserRA.setFollower_id(userId);
				userUserRA.setType(WebConstant.UUR_FOLLOWER);
				userUserRelationshipDAO.save(userUserRA);
				
				userUserRB=new UserUserRelationship();
				userUserRB.setUserInfo(userInfoDAO.get(userId));
				userUserRB.setFollower_id(currentUserInfo.getUser_id());
				userUserRB.setType(WebConstant.UUR_FANS);
				userUserRelationshipDAO.save(userUserRB);
			}
		}//end if(status==WebConstant.STATUS_NONE)
		else if(status==WebConstant.STATUS_FANS){
			useruserRAList=userUserRelationshipDAO.findByUserIdAndType(currentUserInfo.getUser_id(), WebConstant.UUR_FANS);
			useruserRBList=userUserRelationshipDAO.findByUserIdAndType(userId, WebConstant.UUR_FOLLOWER);
			//关注
			if(statusValue==2){
				useruserRAList.get(0).setType(WebConstant.UUR_FRIEND);
				useruserRBList.get(0).setType(WebConstant.UUR_FRIEND);
				userUserRelationshipDAO.update(useruserRAList.get(0));
				userUserRelationshipDAO.update(useruserRBList.get(0));
			}else if(statusValue==3){
			//移除粉丝
				userUserRelationshipDAO.delete(useruserRAList.get(0));
				userUserRelationshipDAO.delete(useruserRBList.get(0));
			}
		}//end else if(status==WebConstant.STATUS_FANS)
		else if(status==WebConstant.STATUS_FOLLOWER){
			//取消关注
			if(statusValue==4){
				useruserRAList=userUserRelationshipDAO.findByUserIdAndType(currentUserInfo.getUser_id(), WebConstant.UUR_FOLLOWER);
				useruserRBList=userUserRelationshipDAO.findByUserIdAndType(userId, WebConstant.UUR_FANS);
				userUserRelationshipDAO.delete(useruserRAList.get(0));
				userUserRelationshipDAO.delete(useruserRBList.get(0));
			}
		}//end else if(status==WebConstant.STATUS_FOLLOWER)
		else if(status==WebConstant.STATUS_FRIEND){
			useruserRAList=userUserRelationshipDAO.findByUserIdAndType(currentUserInfo.getUser_id(), WebConstant.UUR_FRIEND);
			useruserRBList=userUserRelationshipDAO.findByUserIdAndType(userId, WebConstant.UUR_FRIEND);
			//取消关注
			if(statusValue==5){
				useruserRAList.get(0).setType(WebConstant.UUR_FANS);
				useruserRBList.get(0).setType(WebConstant.UUR_FOLLOWER);
				userUserRelationshipDAO.update(useruserRAList.get(0));
				userUserRelationshipDAO.update(useruserRBList.get(0));
			}
			//移除粉丝
			if(statusValue==6){
				useruserRAList.get(0).setType(WebConstant.UUR_FOLLOWER);
				useruserRBList.get(0).setType(WebConstant.UUR_FANS);
				userUserRelationshipDAO.update(useruserRAList.get(0));
				userUserRelationshipDAO.update(useruserRBList.get(0));
			}
		}
	}

	@Override
	public List<FollowerFansBean> getFollowers() {
		WebAlgo algo=new WebAlgo();
		//得到关注者列表
		ActionContext ctx=ActionContext.getContext();
		String userName=(String) ctx.getSession().get("user");
		List<UserInfo> users=findByUserName(userName);
		UserInfo userInfo=users.get(0);
		//得到所有用户的关注者
		List<UserUserRelationship> userUserRelationship=userUserRelationshipDAO.findByUserIdAndType(userInfo.getUser_id(), 
				WebConstant.UUR_FOLLOWER);
		//朋友列表
		userUserRelationship.addAll(userUserRelationshipDAO.findByUserIdAndType(userInfo.getUser_id(), 
				WebConstant.UUR_FRIEND));
		
		List<FollowerFansBean> result=new ArrayList<FollowerFansBean>();
		ArrayList<MessageInfo> recentMsgOrActList=null;
		List<MessageInfo> recentMoodList=null;
		for(UserUserRelationship member:userUserRelationship){
			//得到当前用户最近的信息或者活动
			recentMsgOrActList=(ArrayList<MessageInfo>) messageInfoDAO.findRecentMsgOrAct(member.getFollower_id(),WebConstant.DB_MSGORIGNAL);
			recentMsgOrActList.addAll(messageInfoDAO.findRecentMsgOrAct(member.getFollower_id(),WebConstant.DB_MSGTRANSMIT));
			recentMsgOrActList.addAll(messageInfoDAO.findRecentMsgOrAct(member.getFollower_id(),WebConstant.DB_ACTORIGNAL));
			recentMsgOrActList.addAll(messageInfoDAO.findRecentMsgOrAct(member.getFollower_id(),WebConstant.DB_ACTTRANSMIT));

			algo.qsortMessageByDateTime(recentMsgOrActList, 0, recentMsgOrActList.size()-1);
			//2.2，对于同一天按照realTime排序
			int len=recentMsgOrActList.size();
			for(int i=0;i<len;i++){
				int index = i;
				for(int j=i+1;j<len;j++){
					if(recentMsgOrActList.get(index).getDatetime()==recentMsgOrActList.get(j).getDatetime()){
						index = j;
					}
					else
						break;
				}
				if(index != i){
					algo.qsortMessageByRealTime(recentMsgOrActList,i, index);
				}
				i=index;
			}
			
			MessageInfo recentMsgOrAct=null;
			//对于信息或者活动的图片
			String picpathStr=null;
			String picpath=null;
			String picpaths[]=null;
			
			if(recentMsgOrActList.size()!=0){
				recentMsgOrAct=recentMsgOrActList.get(0);
				
				if(recentMsgOrAct.getPicture()!=null){
					picpath=recentMsgOrAct.getPicture();
					picpaths=picpath.split("GuiJi");
					picpathStr=picpaths[1].replace('\\', '/');
				}
			}
			
			//status设置
			int status=0;
			if(member.getType()==WebConstant.UUR_FOLLOWER)
				status=WebConstant.STATUS_FOLLOWER;
			else
				status=WebConstant.STATUS_FRIEND;
			
			//找到最近用户的心情指数
			recentMoodList=messageInfoDAO.findRecentMood(member.getFollower_id());
			int recentMood=0;
			if(recentMoodList.size()!=0){
				recentMood=recentMoodList.get(recentMoodList.size()-1).getMood();
			}
			
			String path=userInfoDAO.get(member.getFollower_id()).getAvatar();
			String paths[]=path.split("GuiJi");
			//用户没有发表信息或者活动
			if(recentMsgOrAct==null){
				result.add(new FollowerFansBean(
						1,
						status,
						member.getFollower_id(),
						paths[1].replace('\\', '/'),
						userInfoDAO.get(member.getFollower_id()).getUser_name(),
						recentMood
						));
			}
			//最近的信息情况
			else if(recentMsgOrAct.getType()==WebConstant.DB_MSGORIGNAL || recentMsgOrAct.getType()==WebConstant.DB_MSGTRANSMIT){
				result.add(new FollowerFansBean(
						2,
						status,
						member.getFollower_id(),
						paths[1].replace('\\', '/'),
						userInfoDAO.get(member.getFollower_id()).getUser_name(),
						algo.transferVirtualTime(recentMsgOrAct.getDatetime(),recentMsgOrAct.getRealtime(),Integer.parseInt(algo.getRealDay()),Integer.parseInt(algo.getRealTime())),
						getBestMsgPlaceName(userInfo, recentMsgOrAct),
						recentMood,
						recentMsgOrAct.getMsg_content(),
						picpathStr
				));
			}
			//最近的活动情况
			else{
				result.add(new FollowerFansBean(
						3,
						status,
						member.getFollower_id(),
						paths[1].replace('\\', '/'),
						userInfoDAO.get(member.getFollower_id()).getUser_name(),
						getBestActPlaceName(userInfo, recentMsgOrAct),
						algo.transferTimeIStr(recentMsgOrAct.getStart_datetime(),recentMsgOrAct.getStart_realtime()),
						algo.transferTimeIStr(recentMsgOrAct.getEnd_datetime(),recentMsgOrAct.getEnd_realtime()),
						recentMood,
						recentMsgOrAct.getMsg_content(),
						picpathStr
				));
			}
		}
		
		
		return result;
	}

	@Override
	public List<FollowerFansBean> getFans() {
		WebAlgo algo=new WebAlgo();
		//得到粉丝列表
		ActionContext ctx=ActionContext.getContext();
		String userName=(String) ctx.getSession().get("user");
		List<UserInfo> users=findByUserName(userName);
		UserInfo userInfo=users.get(0);
		//得到所有用户的粉丝
		List<UserUserRelationship> userUserRelationship=userUserRelationshipDAO.findByUserIdAndType(userInfo.getUser_id(), 
				WebConstant.UUR_FANS);
		
		List<FollowerFansBean> result=new ArrayList<FollowerFansBean>();
		ArrayList<MessageInfo> recentMsgOrActList=null;
		List<MessageInfo> recentMoodList=null;
		for(UserUserRelationship member:userUserRelationship){
			//得到当前用户最近的信息或者活动
			recentMsgOrActList=(ArrayList<MessageInfo>) messageInfoDAO.findRecentMsgOrAct(member.getFollower_id(),WebConstant.DB_MSGORIGNAL);
			recentMsgOrActList.addAll(messageInfoDAO.findRecentMsgOrAct(member.getFollower_id(),WebConstant.DB_MSGTRANSMIT));
			recentMsgOrActList.addAll(messageInfoDAO.findRecentMsgOrAct(member.getFollower_id(),WebConstant.DB_ACTORIGNAL));
			recentMsgOrActList.addAll(messageInfoDAO.findRecentMsgOrAct(member.getFollower_id(),WebConstant.DB_ACTTRANSMIT));

			algo.qsortMessageByDateTime(recentMsgOrActList, 0, recentMsgOrActList.size()-1);
			//2.2，对于同一天按照realTime排序
			int len=recentMsgOrActList.size();
			for(int i=0;i<len;i++){
				int index = i;
				for(int j=i+1;j<len;j++){
					if(recentMsgOrActList.get(index).getDatetime()==recentMsgOrActList.get(j).getDatetime()){
						index = j;
					}
					else
						break;
				}
				if(index != i){
					algo.qsortMessageByRealTime(recentMsgOrActList,i, index);
				}
				i=index;
			}
			
			MessageInfo recentMsgOrAct=null;
			//对于信息或者活动的图片
			String picpathStr=null;
			String picpath=null;
			String picpaths[]=null;
			
			if(recentMsgOrActList.size()!=0){
				recentMsgOrAct=recentMsgOrActList.get(0);
				
				if(recentMsgOrAct.getPicture()!=null){
					picpath=recentMsgOrAct.getPicture();
					picpaths=picpath.split("GuiJi");
					picpathStr=picpaths[1].replace('\\', '/');
				}
			}
			
			//找到最近用户的心情指数
			recentMoodList=messageInfoDAO.findRecentMood(member.getFollower_id());
			int recentMood=0;
			if(recentMoodList.size()!=0){
				recentMood=recentMoodList.get(0).getMood();
			}
			
			String path=userInfoDAO.get(member.getFollower_id()).getAvatar();
			String paths[]=path.split("GuiJi");
			//用户没有发表信息或者活动
			if(recentMsgOrAct==null){
				result.add(new FollowerFansBean(
						1,
						WebConstant.STATUS_FANS,
						member.getFollower_id(),
						paths[1].replace('\\', '/'),
						userInfoDAO.get(member.getFollower_id()).getUser_name(),
						recentMood
						));
			}
			else if(recentMsgOrAct.getType()==WebConstant.DB_MSGORIGNAL || recentMsgOrAct.getType()==WebConstant.DB_MSGTRANSMIT){
				result.add(new FollowerFansBean(
						2,
						WebConstant.STATUS_FANS,
						member.getFollower_id(),
						paths[1].replace('\\', '/'),
						userInfoDAO.get(member.getFollower_id()).getUser_name(),
						algo.transferVirtualTime(recentMsgOrAct.getDatetime(),recentMsgOrAct.getRealtime(),Integer.parseInt(algo.getRealDay()),Integer.parseInt(algo.getRealTime())),
						getBestMsgPlaceName(userInfo, recentMsgOrAct),
						recentMood,
						recentMsgOrAct.getMsg_content(),
						picpathStr//信息的图片
				));
			}
			else{
				result.add(new FollowerFansBean(
						3,
						WebConstant.STATUS_FANS,
						member.getFollower_id(),
						paths[1].replace('\\', '/'),
						userInfoDAO.get(member.getFollower_id()).getUser_name(),
						getBestActPlaceName(userInfo, recentMsgOrAct),
						algo.transferTimeIStr(recentMsgOrAct.getStart_datetime(),recentMsgOrAct.getStart_realtime()),
						algo.transferTimeIStr(recentMsgOrAct.getEnd_datetime(),recentMsgOrAct.getEnd_realtime()),
						recentMood,
						recentMsgOrAct.getMsg_content(),
						picpathStr//活动的图片
				));
			}
		}
		
		
		return result;
	}

	@Override
	public List<FindUsersBean> findUser(String findUserName) {
		WebAlgo algo=new WebAlgo();
		//当前用户
		ActionContext ctx=ActionContext.getContext();
		String currentUserName=(String) ctx.getSession().get("user");
		List<UserInfo> users=findByUserName(currentUserName);
		UserInfo currentUser=users.get(0);
		List<UserUserRelationship> useruserR=userUserRelationshipDAO.findByUserIdFollowerFans(currentUser.getUser_id());
		//第一个Integer为用户Id，第二个Integer为关系类型
		Map<Integer, Integer> useruserRList = new HashMap<Integer, Integer>();
		for(UserUserRelationship tt:useruserR){
			useruserRList.put(tt.getFollower_id(),tt.getType());
		}
		
		//对于全名匹配的用户直接拿出来
		List<UserInfo> directFindUsers=findByUserName(findUserName);
		//返回结果
		List<FindUsersBean> result=new ArrayList<FindUsersBean>();
		int status=0;
		
		//对于全名匹配的用户直接拿出来
		if(directFindUsers.size()!=0){
			UserInfo directUser=directFindUsers.get(0);
			
			String path=directUser.getAvatar();
			String paths[]=path.split("GuiJi");
			if(findUserName.equalsIgnoreCase(currentUserName)){
				status=WebConstant.STATUS_SAME;
			}
			else{
				if(!useruserRList.containsKey(directUser.getUser_id()))
					status=WebConstant.STATUS_NONE;
				else{
					if(useruserRList.get(directUser.getUser_id())==WebConstant.UUR_FANS)
						status=WebConstant.STATUS_FANS;
					else if(useruserRList.get(directUser.getUser_id())==WebConstant.UUR_FOLLOWER)
						status=WebConstant.STATUS_FOLLOWER;
					else 
						status=WebConstant.STATUS_FRIEND;
				}
			}
			
			
			result.add(new FindUsersBean(
					status,
					directUser.getUser_id(),
					directUser.getUser_name(),
					paths[1].replace('\\', '/'),
					directUser.getHometown(),
					getBestUserPlaceName(currentUser, directUser)
					));
			
		}
		else{
			List<UserInfo> allUser=userInfoDAO.findAll();
			//Integer代表查询的字符串和数据库用户名的正序匹配的数量
			Map<UserInfo, Integer> resultUserList = new HashMap<UserInfo, Integer>();
			int count=0;
			for(UserInfo member:allUser){
				count=algo.searchUser(findUserName, member.getUser_name());
				if(count >=1){
					resultUserList.put(member, count);
				}
			}
			//对map进行快速排序，由高到低 
			List<Map.Entry<UserInfo, Integer>> resultMap =
			    new ArrayList<Map.Entry<UserInfo, Integer>>(resultUserList.entrySet());
			
			algo.qsortFindUser(resultMap, 0, resultMap.size()-1);
			
			
			for(Map.Entry<UserInfo, Integer> mem:resultMap){
				//每次循环的User
				UserInfo tempUser=mem.getKey();
				String path=tempUser.getAvatar();
				String paths[]=path.split("GuiJi");
				if(currentUserName.equalsIgnoreCase(tempUser.getUser_name())){
					status=WebConstant.STATUS_SAME;
				}
				else{
					if(!useruserRList.containsKey(tempUser.getUser_id()))
						status=WebConstant.STATUS_NONE;
					else{
						if(useruserRList.get(tempUser.getUser_id())==WebConstant.UUR_FANS)
							status=WebConstant.STATUS_FANS;
						else if(useruserRList.get(tempUser.getUser_id())==WebConstant.UUR_FOLLOWER)
							status=WebConstant.STATUS_FOLLOWER;
						else 
							status=WebConstant.STATUS_FRIEND;
					}
				}
				
				result.add(new FindUsersBean(
						status,
						tempUser.getUser_id(),
						tempUser.getUser_name(),
						paths[1].replace('\\', '/'),
						tempUser.getHometown(),
						getBestUserPlaceName(currentUser, tempUser)
						));
			}
		}
		
		
		return result;
	}

	@Override
	public void setup(UserInfo userInfo,String userName) {
		List<UserInfo> users=findByUserName(userName);
		UserInfo setUser=users.get(0);
		if(userInfo.getAvatar()!=null)
			setUser.setAvatar(userInfo.getAvatar());
		if(userInfo.getProvince().length()!=0){
			setUser.setProvince(userInfo.getProvince());
			setUser.setCity(userInfo.getCity());
			setUser.setDistrict(userInfo.getDistrict());
			setUser.setOther(userInfo.getOther());
		}
		if(userInfo.getEmail()!=null){
			setUser.setEmail(userInfo.getEmail());
		}
		userInfoDAO.update(setUser);
	}

	@Override
	public List<PhotosBean> getPhotos() {
		ActionContext ctx=ActionContext.getContext();
		String userName=(String) ctx.getSession().get("user");
		UserInfo userInfo=findByUserName(userName).get(0);
		ArrayList<PhotosInfo> photos=(ArrayList<PhotosInfo>) photosInfoDAO.findByUserId(userInfo.getUser_id());
		WebAlgo algo=new WebAlgo();
		algo.qsortPhoto(photos, 0, photos.size()-1);
		String picPath=null;
		String picPaths[]=null;
		List<PhotosBean> result=new ArrayList<PhotosBean>();
		for(PhotosInfo member:photos){
			picPath=member.getPhoto();
			picPaths=picPath.split("GuiJi");
			result.add(new PhotosBean(
					picPaths[1].replace('\\', '/'),
					member.getPhoto_id()));
		}
		
		return result;
	}

	@Override
	public void doDeletePhoto(int photo_id) {
		photosInfoDAO.delete(photo_id);
	}
	
	@Override
	public void uploadPicture(String userName, String path) {
		UserInfo userInfo=findByUserName(userName).get(0);
		PhotosInfo photosInfo=new PhotosInfo();
		photosInfo.setUserInfo(userInfo);
		photosInfo.setPhoto(path);
		photosInfoDAO.save(photosInfo);
		
		//增加上传图片的日志
		WebAlgo algo=new WebAlgo();
		addLogInfo(userInfo, WebConstant.OP_UPLOADPICTURE, 0, userInfo.getNowcoordinate(), 
				userInfo.getNowprovince(), userInfo.getNowcity(), userInfo.getNowdistrict(), userInfo.getNowother(), 
				Integer.parseInt(algo.getRealTime()), Integer.parseInt(algo.getRealDay()));
	}
	
	
	@Override
	public FindUsersBean getUserHeadInfo(int userId) {
		//当前用户
		ActionContext ctx=ActionContext.getContext();
		String currentUserName=(String) ctx.getSession().get("user");
		List<UserInfo> users=findByUserName(currentUserName);
		UserInfo currentUser=users.get(0);
		
		//被选择的用户
		UserInfo userInfo=userInfoDAO.get(userId);
		
		int status=0;
		String path=userInfo.getAvatar();
		String paths[]=path.split("GuiJi");
		
		if(currentUser.getUser_id()!=userId){
			List<UserUserRelationship> uur=userUserRelationshipDAO.getStatusByUsersId(currentUser.getUser_id(), userId);
			if(uur.size()!=0){
				if(uur.get(0).getType()==WebConstant.UUR_FANS)
					status=WebConstant.STATUS_FANS;
				else if(uur.get(0).getType()==WebConstant.UUR_FOLLOWER)
					status=WebConstant.STATUS_FOLLOWER;
				else if(uur.get(0).getType()==WebConstant.UUR_FRIEND)
					status=WebConstant.STATUS_FRIEND;
			}
			else{
				status=WebConstant.STATUS_NONE;
			}
			
		}
		else{
			status=WebConstant.STATUS_SAME;
		}
			
		return new FindUsersBean(
				status,
				userId,
				userInfo.getUser_name(),
				paths[1].replace('\\', '/'),
				userInfo.getHometown(),
				getBestUserPlaceName(currentUser, userInfo)
		);
	}
	
	
	@Override
	public List<PhotosBean> getUserPhotos(int userId) {
		ArrayList<PhotosInfo> photos=(ArrayList<PhotosInfo>) photosInfoDAO.findByUserId(userId);
		WebAlgo algo=new WebAlgo();
		algo.qsortPhoto(photos, 0, photos.size()-1);
		String picPath=null;
		String picPaths[]=null;
		List<PhotosBean> result=new ArrayList<PhotosBean>();
		for(PhotosInfo member:photos){
			picPath=member.getPhoto();
			picPaths=picPath.split("GuiJi");
			result.add(new PhotosBean(
					picPaths[1].replace('\\', '/'),
					member.getPhoto_id()));
		}
		
		return result;
	}
	
	@Override
	public List<MessageBean> getUserMessages(int userId) {
		//找到个人用户的所有信息、活动原创或者转发
		ArrayList<MessageInfo> messages=(ArrayList<MessageInfo>) messageInfoDAO.findByUserId(userId);
		
		//找到当前会话 的用户
		ActionContext ctx=ActionContext.getContext();
		String userName=(String) ctx.getSession().get("user");
		UserInfo userInfo=findByUserName(userName).get(0);
		
		int messagesSize=messages.size();
		WebAlgo algo=new WebAlgo();
		if(messagesSize!=0){
			//1,按照实时快速排序
			//2.1，首先按照日期dateTime排序
			algo.qsortMessageByDateTime(messages, 0, messagesSize-1);
			//2.2，对于同一天按照realTime排序
			int len=messages.size();
			for(int i=0;i<len;i++){
				int index = i;
				for(int j=i+1;j<len;j++){
					if(messages.get(index).getDatetime()==messages.get(j).getDatetime()){
						index = j;
					}
					else
						break;
				}
				if(index != i){
					algo.qsortMessageByRealTime(messages,i, index);
				}
				i=index;
			}//end for
		}//end if(messagesSize!=0)
		
		return makeMessageBean(userInfo,messages);
	}
	
	@Override
	public List<MoodBean> getUserMood(UserInfo userInfo,int userId) {
		//获取个人用户的心情
		ArrayList<MessageInfo> messages=(ArrayList<MessageInfo>) messageInfoDAO.findMoodByUserId(userId);
		int messagesSize=messages.size();
		List<MoodBean> result=null;
		WebAlgo algo=new WebAlgo();
		if(messagesSize!=0){
			result=new ArrayList<MoodBean>();
			//2.1，首先按照日期dateTime排序
			algo.qsortMessageByDateTime(messages, 0, messagesSize-1);
			//2.2，对于同一天按照realTime排序
			int len=messages.size();
			for(int i=0;i<len;i++){
				int index = i;
				for(int j=i+1;j<len;j++){
					if(messages.get(index).getDatetime()==messages.get(j).getDatetime()){
						index = j;
					}
					else
						break;
				}
				if(index != i){
					algo.qsortMessageByRealTime(messages,i, index);
				}
				i=index;
			}//end for
			for(MessageInfo member:messages){
				UserInfo tempUser=member.getUserInfo();
				String path=tempUser.getAvatar();
				String paths[]=path.split("GuiJi");
					
				String pathmsg=null;
				String pathsmsg[]=null;
				String pathStr=null;
				if(member.getPicture()!=null){
					pathmsg=member.getPicture();
					pathsmsg=pathmsg.split("GuiJi");
					pathStr=pathsmsg[1].replace('\\', '/');
				}
				result.add(new MoodBean(
						tempUser.getUser_id(),
						tempUser.getUser_name(),
						paths[1].replace('\\', '/'),
						member.getMsg_id(),
						member.getMood(),
						member.getMsg_content(),
						pathStr,
						algo.transferVirtualTime(member.getDatetime(),member.getRealtime(),Integer.parseInt(algo.getRealDay()),Integer.parseInt(algo.getRealTime())),
						getBestMsgPlaceName(userInfo, member),
						member.getPictures_amount(),
						member.getComment_amount()
					));
				}
		}
				
				
		return result;
	}
	
	@Override
	public void setUserAvator(String userName, String path) {
		List<UserInfo> users=findByUserName(userName);
		UserInfo userInfo=users.get(0);
		userInfo.setAvatar(path);
		userInfoDAO.update(userInfo);
		
		PhotosInfo photosInfo=new PhotosInfo();
		photosInfo.setUserInfo(userInfo);
		photosInfo.setPhoto(path);
		photosInfoDAO.save(photosInfo);
	}
	
	@Override
	public void setUserEmail(String email) {
		ActionContext ctx=ActionContext.getContext();
		String userName=(String) ctx.getSession().get("user");
		List<UserInfo> users=findByUserName(userName);
		UserInfo userInfo=users.get(0);
		userInfo.setEmail(email);
		userInfoDAO.update(userInfo);
	}
	
	@Override
	public void setUserInterest(String interest) {
		ActionContext ctx=ActionContext.getContext();
		String userName=(String) ctx.getSession().get("user");
		List<UserInfo> users=findByUserName(userName);
		UserInfo userInfo=users.get(0);
		userInfo.setCategory(interest);
		userInfoDAO.update(userInfo);
	}
	
	@Override
	public void setFeedBack(int value, String content) {
		ActionContext ctx=ActionContext.getContext();
		String userName=(String) ctx.getSession().get("user");
		List<UserInfo> users=findByUserName(userName);
		UserInfo userInfo=users.get(0);
		WebAlgo algo=new WebAlgo();
		LogInfo logInfo=new LogInfo();
		logInfo.setUserInfo(userInfo);
		logInfo.setOperate(WebConstant.OP_FEEDBACK);
		logInfo.setMood(value);
		logInfo.setContent(content);
		logInfo.setCoordinate(userInfo.getNowcoordinate());
		logInfo.setProvince(userInfo.getNowprovince());
		logInfo.setCity(userInfo.getNowcity());
		logInfo.setDistrict(userInfo.getNowdistrict());
		logInfo.setOther(userInfo.getNowother());
		logInfo.setOpdatetime(Integer.parseInt(algo.getRealDay()));
		logInfo.setOprealtime(Integer.parseInt(algo.getRealTime()));
		logInfoDAO.save(logInfo);
		
	}
	
	@Override
	public boolean setOwnInfo(UserInfo userInfo) {
		ActionContext ctx=ActionContext.getContext();
		String userName=(String) ctx.getSession().get("user");
		List<UserInfo> users=findByUserName(userName);
		UserInfo currentUserInfo=users.get(0);
		if(userInfo.getBirthday()!=0){
			currentUserInfo.setBirthday(userInfo.getBirthday());
		}
		if(userInfo.getProvince().length()!=0){
			currentUserInfo.setProvince(userInfo.getProvince());
			currentUserInfo.setCity(userInfo.getCity());
			currentUserInfo.setDistrict(userInfo.getDistrict());
			currentUserInfo.setOther(userInfo.getOther());
		}
		if(userInfo.getHometown().length()!=0){
			currentUserInfo.setHometown(userInfo.getHometown());
		}
		if(userInfo.getBirthday()!=0 || userInfo.getProvince().length()!=0 || userInfo.getHometown().length()!=0){
			userInfoDAO.update(currentUserInfo);
			return true;
		}
		
		return false;
	}

	public void addLogInfo(UserInfo userInfo,int operate,int mood,String coordinate,String province,
			String city,String district,String other,int oprealtime,int opdatetime){
		//设置保存日志方法
		LogInfo logInfo=new LogInfo();
		logInfo.setUserInfo(userInfo);
		logInfo.setOperate(operate);
		logInfo.setMood(mood);
		logInfo.setCoordinate(coordinate);
		logInfo.setProvince(province);
		logInfo.setCity(city);
		logInfo.setDistrict(district);
		logInfo.setOther(other);
		logInfo.setOprealtime(oprealtime);
		logInfo.setOpdatetime(opdatetime);
		logInfoDAO.save(logInfo);
	}
	
	public String getBestUserPlaceName(UserInfo currentUser,UserInfo tempUser){
		String tempPlace="";
		int flag=1;
		if(currentUser.getNowdistrict().equals(tempUser.getDistrict())){
			tempPlace=tempUser.getOther();
			flag=0;
		}
		else if(flag==1 && currentUser.getNowcity().equals(tempUser.getCity())){
			tempPlace=tempUser.getDistrict()+" "+tempUser.getOther();
			flag=0;
		}
		else if(flag==1 && currentUser.getNowprovince().equals(tempUser.getProvince())){
			tempPlace=tempUser.getCity()+" "+tempUser.getDistrict()+" "+tempUser.getOther();
		}
		else{
			tempPlace=tempUser.getProvince()+" "+tempUser.getCity()+" "+tempUser.getDistrict()+" "+tempUser.getOther();
		}
		
		return tempPlace;
	}
	
	public String getBestMsgPlaceName(UserInfo currentUser,MessageInfo tempMsg){
		String tempPlace="";
		int flag=1;
		if(currentUser.getNowdistrict().equals(tempMsg.getDistrict())){
			tempPlace=tempMsg.getOther();
			flag=0;
		}
		else if(flag==1 && currentUser.getNowcity().equals(tempMsg.getCity())){
			tempPlace=tempMsg.getDistrict()+" "+tempMsg.getOther();
			flag=0;
		}
		else if(flag==1 && currentUser.getNowprovince().equals(tempMsg.getProvince())){
			tempPlace=tempMsg.getCity()+" "+tempMsg.getDistrict()+" "+tempMsg.getOther();
		}
		else{
			tempPlace=tempMsg.getProvince()+" "+tempMsg.getCity()+" "+tempMsg.getDistrict()+" "+tempMsg.getOther();
		}
		
		return tempPlace;
	}
	
	public String getBestActPlaceName(UserInfo currentUser,MessageInfo tempAct){
		String tempPlace="";
		int flag=1;
		if(currentUser.getNowdistrict().equals(tempAct.getActdistrict())){
			tempPlace=tempAct.getActdistrict()+" "+tempAct.getActother();
			flag=0;
		}
		else if(flag==1 && currentUser.getNowcity().equals(tempAct.getActcity())){
			tempPlace=tempAct.getActdistrict()+" "+tempAct.getActother();
			flag=0;
		}
		else if(flag==1 && currentUser.getNowprovince().equals(tempAct.getActprovince())){
			tempPlace=tempAct.getActcity()+" "+tempAct.getActdistrict()+" "+tempAct.getActother();
		}
		else{
			tempPlace=tempAct.getActprovince()+" "+tempAct.getActcity()+" "+tempAct.getActdistrict()+" "+tempAct.getActother();
		}
		
		return tempPlace;
	}
	
	@Override
	public void ComputeTrustValue() {
		//计算用户信任值,总共比例为1  (含23类)
		//1,7,11,14,26,31   0.07;
		//6,18,19,27,30     0.05;
		//3,8,20,23,24,25   0.04;
		//2,4,5,12,13,21    0.015;
		//首先找到Admin登录操作的最早时间
		List<LogInfo> recentLog=logInfoDAO.getRecentLog(WebConstant.WEBADMINID, WebConstant.OP_LOGIN);
		//找到比上次Admin操作晚的记录
		if(recentLog.size()!=0){
			List<LogInfo> allLog=logInfoDAO.findAll(recentLog.get(0).getId());
			Map<Integer, Float> userTrustValue = new HashMap<Integer, Float>();
			for(LogInfo member:allLog){
				int tempId=member.getUserInfo().getUser_id();
				if(userTrustValue.containsKey(tempId)){
					switch(member.getOperate()){
					case WebConstant.OP_MESSAGE:
					case WebConstant.OP_ACTIVITY:
					case WebConstant.OP_ADDWEATHER:
					case WebConstant.OP_REGISTER:
					case WebConstant.OP_MOOD:
					case WebConstant.OP_FEEDBACK:userTrustValue.put(tempId, (float) (userTrustValue.get(tempId)+0.07));continue;
					case WebConstant.OP_ADDPHOTO:
					case WebConstant.OP_TRANSMITMSG:
					case WebConstant.OP_TRANSMITACT:
					case WebConstant.OP_REPLYMOOD:
					case WebConstant.OP_UPLOADPICTURE:userTrustValue.put(tempId, (float) (userTrustValue.get(tempId)+0.05));continue;
					case WebConstant.OP_COMMENT:
					case WebConstant.OP_LOGIN:
					case WebConstant.OP_REPLYMSG:
					case WebConstant.OP_COMMENTAND:
					case WebConstant.OP_COMMENTORIGNAL:
					case WebConstant.OP_REPLYACT:userTrustValue.put(tempId, (float) (userTrustValue.get(tempId)+0.04));continue;
					case WebConstant.OP_RECOMMENDMSG:
					case WebConstant.OP_WANTJOIN:
					case WebConstant.OP_ATTENTION:
					case WebConstant.OP_APPROVEWEATHER:
					case WebConstant.OP_DISAPPROVEWEATHER:
					case WebConstant.OP_RECOMMENDACT:userTrustValue.put(tempId, (float) (userTrustValue.get(tempId)+0.015));continue;
					}
				}else{
					switch(member.getOperate()){
					case WebConstant.OP_MESSAGE:
					case WebConstant.OP_ACTIVITY:
					case WebConstant.OP_ADDWEATHER:
					case WebConstant.OP_REGISTER:
					case WebConstant.OP_MOOD:
					case WebConstant.OP_FEEDBACK:userTrustValue.put(tempId, (float) (0.07));continue;
					case WebConstant.OP_ADDPHOTO:
					case WebConstant.OP_TRANSMITMSG:
					case WebConstant.OP_TRANSMITACT:
					case WebConstant.OP_REPLYMOOD:
					case WebConstant.OP_UPLOADPICTURE:userTrustValue.put(tempId, (float) (0.05));continue;
					case WebConstant.OP_COMMENT:
					case WebConstant.OP_LOGIN:
					case WebConstant.OP_REPLYMSG:
					case WebConstant.OP_COMMENTAND:
					case WebConstant.OP_COMMENTORIGNAL:
					case WebConstant.OP_REPLYACT:userTrustValue.put(tempId, (float) (0.04));continue;
					case WebConstant.OP_RECOMMENDMSG:
					case WebConstant.OP_WANTJOIN:
					case WebConstant.OP_ATTENTION:
					case WebConstant.OP_APPROVEWEATHER:
					case WebConstant.OP_DISAPPROVEWEATHER:
					case WebConstant.OP_RECOMMENDACT:userTrustValue.put(tempId, (float) (0.015));continue;
					}
				}
			}//end for
			List<Map.Entry<Integer, Float>> userTrustValueList =
			    new ArrayList<Map.Entry<Integer, Float>>(userTrustValue.entrySet());
			
			for(Map.Entry<Integer, Float> tt:userTrustValueList){
				UserInfo userInfo=userInfoDAO.get(tt.getKey());
				userInfo.setTrust_value(""+tt.getValue());
				userInfoDAO.update(userInfo);
			}
		}
		
	}
}
