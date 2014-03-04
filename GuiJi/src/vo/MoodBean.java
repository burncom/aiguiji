package vo;

import java.io.Serializable;

public class MoodBean implements Serializable {
	/**
	 * 处理心情页的内容
	 */
	private static final long serialVersionUID = 1L;
	private int userId;
	private String userName;
	private String userLogo;
	private int msgId;
	private int mood;
	private String moodContent;
	private String moodPic;
	private String time;
	private String place;
	private int pictureCount;
	private int commentCount;
	
	public MoodBean() {
	}

	public MoodBean(int userId,String userName, String userLogo,int msgId, int mood,
			String moodContent, String moodPic, String time, String place,
			int pictureCount,int commentCount) {
		super();
		this.userId=userId;
		this.userName = userName;
		this.userLogo = userLogo;
		this.msgId=msgId;
		this.mood = mood;
		this.moodContent = moodContent;
		this.moodPic = moodPic;
		this.time = time;
		this.place = place;
		this.pictureCount=pictureCount;
		this.commentCount = commentCount;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPictureCount() {
		return pictureCount;
	}

	public void setPictureCount(int pictureCount) {
		this.pictureCount = pictureCount;
	}

	public int getMsgId() {
		return msgId;
	}

	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserLogo() {
		return userLogo;
	}

	public void setUserLogo(String userLogo) {
		this.userLogo = userLogo;
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

	public String getMoodPic() {
		return moodPic;
	}

	public void setMoodPic(String moodPic) {
		this.moodPic = moodPic;
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

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	
	
}
