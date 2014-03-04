package vo;

import java.io.Serializable;

public class TopUserBean implements Serializable {
	/**
	 * 处理每个分类的前20位
	 */
	private static final long serialVersionUID = 1L;
	//用户的logo
	private String userLogo;
	private int userId;
	//用户名字
	private String userName;
	//活跃值
	private float count;
	//心情指数
	private int mood;
	//心情内容
	private String moodContent;
	private String moodPic;
	//当前用户A，和用户B的关系状态
	//1,A,B没有任何关系；2，B为A的粉丝,也就是B关注A；3，A为B的粉丝，也就是A关注B；4，A，B互相关注
	private int status;
	private String time;
	private String place;
	private int msgId;
	private int commentCount;
	
	public TopUserBean() { 	}
	
	public TopUserBean(String userLogo,int userId, String userName, float count, int mood,
			String moodContent, String moodPic, int status, String time,
			String place, int msgId, int commentCount) {
		super();
		this.userLogo = userLogo;
		this.userId=userId;
		this.userName = userName;
		this.count = count;
		this.mood = mood;
		this.moodContent = moodContent;
		this.moodPic = moodPic;
		this.status = status;
		this.time = time;
		this.place = place;
		this.msgId = msgId;
		this.commentCount = commentCount;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getMsgId() {
		return msgId;
	}

	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public String getMoodPic() {
		return moodPic;
	}

	public void setMoodPic(String moodPic) {
		this.moodPic = moodPic;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public int getMood() {
		return mood;
	}
	public void setMood(int mood) {
		this.mood = mood;
	}
	public String getMoodContent() {
		return moodContent;
	}
	public void setMoodContent(String moodContent) {
		this.moodContent = moodContent;
	}
	public String getUserLogo() {
		return userLogo;
	}
	public void setUserLogo(String userLogo) {
		this.userLogo = userLogo;
	}
	public float getCount() {
		return count;
	}
	public void setCount(float count) {
		this.count = count;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
