package vo;

import java.io.Serializable;

public class MessageBean implements Serializable {
	/**
	 * 处理轨迹网 所有信息的Bean，包含信息，活动
	 */
	private static final long serialVersionUID = 1L;
	
	//分为四种情况，1，信息原创；2，信息转发；3，活动原创；4，活动转发
	private String type;
	
	//当前信息主键
	private int msgId;
	//当前用户主键
	private int userId;
	//当前用户Logo
	private String userLogo;
	//当前用户昵称
	private String userName;
	//当前用户信息、活动内容
	private String msgContent;
	//当前用户信息、活动图片
	private String msgPicture;
	//当前用户信息、活动时间
	private String time;
	//当前用户信息、活动地点
	private String place;
	//当前用户所有评论人的信息、活动图片总数
	private int pictureSetNum;
	//当前用户信息、活动推荐数
	private int recommendNum;
	//当前用户信息、活动转发数
	private int transmitNum;
	//当前用户信息、活动评论数
	private int commentNum;
	
	//原创信息主键
	private int firstMsgId;
	//原创用户主键
	private int firstUserId;
	//原创用户
	private String firstUserName;
	//原创信息、活动内容
	private String firstMsgContent;
	//原创信息、活动图片
	private String firstMsgPicture;
	//原创用户信息、活动时间
	private String firstTime;
	//原创用户信息、活动地点
	private String firstPlace;
	//原创用户所有评论人的信息、活动图片总数
	private int firstPictureSetNum;
	//原创用户信息、活动推荐数
	private int firstRecommendNum;
	//原创用户信息、活动转发数
	private int firstTransmitNum;
	//原创用户信息、活动评论数
	private int firstCommentNum;
	
	//信息、活动分类
	private String category;
	
	//活动开始时间
	private String startTime;
	//活动结束时间
	private String endTime;
	//活动地点
	private String activityPlace;
	//活动发起人
	private String leaderName;
	//当前用户活动想参加数
	private int joinNum;
	
	//原创用户活动想参加数
	private int firstJoinNum;
	
	public MessageBean(){}
	
	//信息作为原创的情况
	public MessageBean(String type,int msgId, int userId,String userLogo, String userName,
			String msgContent, String msgPicture, String time, String place,
			int pictureSetNum, int recommendNum, int transmitNum,
			int commentNum, String category) {
		super();
		this.type = type;
		this.msgId=msgId;
		this.userId=userId;
		this.userLogo = userLogo;
		this.userName = userName;
		this.msgContent = msgContent;
		this.msgPicture = msgPicture;
		this.time = time;
		this.place = place;
		this.pictureSetNum = pictureSetNum;//所有 评论 的照片
		this.recommendNum = recommendNum;
		this.transmitNum = transmitNum;
		this.commentNum = commentNum;
		this.category = category;
	}
	
	//信息作为转发的情况
	public MessageBean(String type,int msgId,int userId, String userLogo, String userName,
			String msgContent, String time, String place, int pictureSetNum,
			int recommendNum, int transmitNum, int commentNum,int firstMsgId,int firstUserId,
			String firstUserName, String firstMsgContent,
			String firstMsgPicture, String firstTime, String firstPlace,
			int firstPictureSetNum, int firstRecommendNum,
			int firstTransmitNum, int firstCommentNum, String category) {
		super();
		this.type = type;
		this.msgId=msgId;
		this.userId=userId;
		this.userLogo = userLogo;
		this.userName = userName;
		this.msgContent = msgContent;
		this.time = time;
		this.place = place;
		this.pictureSetNum = pictureSetNum;
		this.recommendNum = recommendNum;
		this.transmitNum = transmitNum;
		this.commentNum = commentNum;
		this.firstMsgId=firstMsgId;
		this.firstUserId=firstUserId;
		this.firstUserName = firstUserName;
		this.firstMsgContent = firstMsgContent;
		this.firstMsgPicture = firstMsgPicture;  
		this.firstTime = firstTime;
		this.firstPlace = firstPlace;
		this.firstPictureSetNum = firstPictureSetNum;////所有 评论 的照片
		this.firstRecommendNum = firstRecommendNum;
		this.firstTransmitNum = firstTransmitNum;
		this.firstCommentNum = firstCommentNum;
		this.category = category;
	}
	
	//活动原创的情况
	public MessageBean(String type, int msgId,int userId, String userLogo, String userName,
			String msgContent, String msgPicture, String time, String place,
			int pictureSetNum, int recommendNum, int transmitNum,
			int commentNum, String category, String startTime, String endTime,
			String activityPlace, String leaderName, int joinNum) {
		super();
		this.type = type;
		this.msgId=msgId;
		this.userId=userId;
		this.userLogo = userLogo;
		this.userName = userName;
		this.msgContent = msgContent;
		this.msgPicture = msgPicture;
		this.time = time;
		this.place = place;
		this.pictureSetNum = pictureSetNum;
		this.recommendNum = recommendNum;
		this.transmitNum = transmitNum;
		this.commentNum = commentNum;
		this.category = category;
		this.startTime = startTime;
		this.endTime = endTime;
		this.activityPlace = activityPlace;
		this.leaderName = leaderName;
		this.joinNum = joinNum;
	}
	
	//活动转发的情况
	public MessageBean(String type, int msgId,int userId, String userLogo, String userName,
			String msgContent, String time, String place, int pictureSetNum,
			int recommendNum, int transmitNum, int commentNum,int firstMsgId,int firstUserId,
			String firstUserName, String firstMsgContent,
			String firstMsgPicture, String firstTime, String firstPlace,
			int firstPictureSetNum, int firstRecommendNum,
			int firstTransmitNum, int firstCommentNum, String category,
			String startTime, String endTime, String activityPlace,
			String leaderName, int joinNum, int firstJoinNum) {
		super();
		this.type = type;
		this.msgId=msgId;
		this.userId=userId;
		this.userLogo = userLogo;
		this.userName = userName;
		this.msgContent = msgContent;
		this.time = time;
		this.place = place;
		this.pictureSetNum = pictureSetNum;
		this.recommendNum = recommendNum;
		this.transmitNum = transmitNum;
		this.commentNum = commentNum;
		this.firstMsgId=firstMsgId;
		this.firstUserId=firstUserId;
		this.firstUserName = firstUserName;
		this.firstMsgContent = firstMsgContent;
		this.firstMsgPicture = firstMsgPicture;
		this.firstTime = firstTime;
		this.firstPlace = firstPlace;
		this.firstPictureSetNum = firstPictureSetNum;
		this.firstRecommendNum = firstRecommendNum;
		this.firstTransmitNum = firstTransmitNum;
		this.firstCommentNum = firstCommentNum;
		this.category = category;
		this.startTime = startTime;
		this.endTime = endTime;
		this.activityPlace = activityPlace;
		this.leaderName = leaderName;
		this.joinNum = joinNum;
		this.firstJoinNum = firstJoinNum;
	}

	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getFirstUserId() {
		return firstUserId;
	}

	public void setFirstUserId(int firstUserId) {
		this.firstUserId = firstUserId;
	}

	public int getMsgId() {
		return msgId;
	}

	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}

	public int getFirstMsgId() {
		return firstMsgId;
	}

	public void setFirstMsgId(int firstMsgId) {
		this.firstMsgId = firstMsgId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserLogo() {
		return userLogo;
	}

	public void setUserLogo(String userLogo) {
		this.userLogo = userLogo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public String getMsgPicture() {
		return msgPicture;
	}

	public void setMsgPicture(String msgPicture) {
		this.msgPicture = msgPicture;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getPictureSetNum() {
		return pictureSetNum;
	}

	public void setPictureSetNum(int pictureSetNum) {
		this.pictureSetNum = pictureSetNum;
	}

	public int getRecommendNum() {
		return recommendNum;
	}

	public void setRecommendNum(int recommendNum) {
		this.recommendNum = recommendNum;
	}

	public int getTransmitNum() {
		return transmitNum;
	}

	public void setTransmitNum(int transmitNum) {
		this.transmitNum = transmitNum;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public String getFirstUserName() {
		return firstUserName;
	}

	public void setFirstUserName(String firstUserName) {
		this.firstUserName = firstUserName;
	}

	public String getFirstMsgContent() {
		return firstMsgContent;
	}

	public void setFirstMsgContent(String firstMsgContent) {
		this.firstMsgContent = firstMsgContent;
	}

	public String getFirstMsgPicture() {
		return firstMsgPicture;
	}

	public void setFirstMsgPicture(String firstMsgPicture) {
		this.firstMsgPicture = firstMsgPicture;
	}

	public String getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(String firstTime) {
		this.firstTime = firstTime;
	}

	public String getFirstPlace() {
		return firstPlace;
	}

	public void setFirstPlace(String firstPlace) {
		this.firstPlace = firstPlace;
	}

	public int getFirstPictureSetNum() {
		return firstPictureSetNum;
	}

	public void setFirstPictureSetNum(int firstPictureSetNum) {
		this.firstPictureSetNum = firstPictureSetNum;
	}

	public int getFirstRecommendNum() {
		return firstRecommendNum;
	}

	public void setFirstRecommendNum(int firstRecommendNum) {
		this.firstRecommendNum = firstRecommendNum;
	}

	public int getFirstTransmitNum() {
		return firstTransmitNum;
	}

	public void setFirstTransmitNum(int firstTransmitNum) {
		this.firstTransmitNum = firstTransmitNum;
	}

	public int getFirstCommentNum() {
		return firstCommentNum;
	}

	public void setFirstCommentNum(int firstCommentNum) {
		this.firstCommentNum = firstCommentNum;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getActivityPlace() {
		return activityPlace;
	}

	public void setActivityPlace(String activityPlace) {
		this.activityPlace = activityPlace;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public int getJoinNum() {
		return joinNum;
	}

	public void setJoinNum(int joinNum) {
		this.joinNum = joinNum;
	}

	public int getFirstJoinNum() {
		return firstJoinNum;
	}

	public void setFirstJoinNum(int firstJoinNum) {
		this.firstJoinNum = firstJoinNum;
	}
	
}
