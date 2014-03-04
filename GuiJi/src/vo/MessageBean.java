package vo;

import java.io.Serializable;

public class MessageBean implements Serializable {
	/**
	 * ����켣�� ������Ϣ��Bean��������Ϣ���
	 */
	private static final long serialVersionUID = 1L;
	
	//��Ϊ���������1����Ϣԭ����2����Ϣת����3���ԭ����4���ת��
	private String type;
	
	//��ǰ��Ϣ����
	private int msgId;
	//��ǰ�û�����
	private int userId;
	//��ǰ�û�Logo
	private String userLogo;
	//��ǰ�û��ǳ�
	private String userName;
	//��ǰ�û���Ϣ�������
	private String msgContent;
	//��ǰ�û���Ϣ���ͼƬ
	private String msgPicture;
	//��ǰ�û���Ϣ���ʱ��
	private String time;
	//��ǰ�û���Ϣ����ص�
	private String place;
	//��ǰ�û����������˵���Ϣ���ͼƬ����
	private int pictureSetNum;
	//��ǰ�û���Ϣ����Ƽ���
	private int recommendNum;
	//��ǰ�û���Ϣ���ת����
	private int transmitNum;
	//��ǰ�û���Ϣ���������
	private int commentNum;
	
	//ԭ����Ϣ����
	private int firstMsgId;
	//ԭ���û�����
	private int firstUserId;
	//ԭ���û�
	private String firstUserName;
	//ԭ����Ϣ�������
	private String firstMsgContent;
	//ԭ����Ϣ���ͼƬ
	private String firstMsgPicture;
	//ԭ���û���Ϣ���ʱ��
	private String firstTime;
	//ԭ���û���Ϣ����ص�
	private String firstPlace;
	//ԭ���û����������˵���Ϣ���ͼƬ����
	private int firstPictureSetNum;
	//ԭ���û���Ϣ����Ƽ���
	private int firstRecommendNum;
	//ԭ���û���Ϣ���ת����
	private int firstTransmitNum;
	//ԭ���û���Ϣ���������
	private int firstCommentNum;
	
	//��Ϣ�������
	private String category;
	
	//���ʼʱ��
	private String startTime;
	//�����ʱ��
	private String endTime;
	//��ص�
	private String activityPlace;
	//�������
	private String leaderName;
	//��ǰ�û����μ���
	private int joinNum;
	
	//ԭ���û����μ���
	private int firstJoinNum;
	
	public MessageBean(){}
	
	//��Ϣ��Ϊԭ�������
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
		this.pictureSetNum = pictureSetNum;//���� ���� ����Ƭ
		this.recommendNum = recommendNum;
		this.transmitNum = transmitNum;
		this.commentNum = commentNum;
		this.category = category;
	}
	
	//��Ϣ��Ϊת�������
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
		this.firstPictureSetNum = firstPictureSetNum;////���� ���� ����Ƭ
		this.firstRecommendNum = firstRecommendNum;
		this.firstTransmitNum = firstTransmitNum;
		this.firstCommentNum = firstCommentNum;
		this.category = category;
	}
	
	//�ԭ�������
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
	
	//�ת�������
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
