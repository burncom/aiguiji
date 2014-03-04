package vo;

import java.io.Serializable;

public class CommentBean implements Serializable {
	/**
	 * 处理所有人评论的信息的
	 */
	private static final long serialVersionUID = 1L;

	private int commentMsgId;
	private int userId;
	private String commentUserName;
	private int referencedUserId;
	private String referencedUserName;
	private String referenced_msgUserName;
	private String userLogo;
	private String msgContent;
	private String msgPicture;
	private String time;
	private String place;
	
	public CommentBean(){}
	
	public CommentBean(int commentMsgId,int userId,String commentUserName,int referencedUserId,String referencedUserName,String referenced_msgUserName,
			String userLogo,String msgContent, String msgPicture, String time, String place) {
		super();
		this.commentMsgId=commentMsgId;
		this.userId=userId;
		this.commentUserName=commentUserName;
		this.referencedUserId=referencedUserId;
		this.referencedUserName=referencedUserName;
		this.referenced_msgUserName=referenced_msgUserName;
		this.userLogo = userLogo;
		this.msgContent = msgContent;
		this.msgPicture = msgPicture;
		this.time = time;
		this.place = place;
	}
	
	
	public int getReferencedUserId() {
		return referencedUserId;
	}

	public void setReferencedUserId(int referencedUserId) {
		this.referencedUserId = referencedUserId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getReferenced_msgUserName() {
		return referenced_msgUserName;
	}

	public void setReferenced_msgUserName(String referenced_msgUserName) {
		this.referenced_msgUserName = referenced_msgUserName;
	}

	public String getReferencedUserName() {
		return referencedUserName;
	}

	public void setReferencedUserName(String referencedUserName) {
		this.referencedUserName = referencedUserName;
	}

	public String getCommentUserName() {
		return commentUserName;
	}

	public void setCommentUserName(String commentUserName) {
		this.commentUserName = commentUserName;
	}

	public int getCommentMsgId() {
		return commentMsgId;
	}

	public void setCommentMsgId(int commentMsgId) {
		this.commentMsgId = commentMsgId;
	}

	public String getUserLogo() {
		return userLogo;
	}
	public void setUserLogo(String userLogo) {
		this.userLogo = userLogo;
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
	
}
