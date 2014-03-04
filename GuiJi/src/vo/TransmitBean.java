package vo;

import java.io.Serializable;

public class TransmitBean implements Serializable {
	/**
	 * 处理转发 的Bean
	 */
	private static final long serialVersionUID = 1L;

	private String transmitMsgContent;
	private int transmitUserId; 
	private String transmitUserName;
	
	private int orignalUserId;	
	private String orignalUserName;
	private String orignalMsgContent;
	private String orignalMsgPicture;
	
	private String orignalActStartTime;
	private String orignalActEndTime;
	private String orignalActPlace;
	
	private String leaderName;
	
	public TransmitBean(){}
	
	//当转发信息时
	public TransmitBean(int transmitUserId,String transmitUserName,String transmitMsgContent,int orignalUserId,String orignalUserName,
			String orignalMsgContent, String orignalMsgPicture) {
		super();
		this.transmitUserId=transmitUserId;
		this.transmitUserName=transmitUserName;
		this.transmitMsgContent = transmitMsgContent;
		this.orignalUserId=orignalUserId;
		this.orignalUserName = orignalUserName;
		this.orignalMsgContent = orignalMsgContent;
		this.orignalMsgPicture = orignalMsgPicture;
	}
	//当转发活动时
	public TransmitBean(int transmitUserId,String transmitUserName,String transmitMsgContent, int orignalUserId,String orignalUserName,
			String orignalMsgContent, String orignalMsgPicture,
			String orignalActStartTime, String orignalActEndTime,
			String orignalActPlace,String leaderName) {
		super();
		this.transmitUserId=transmitUserId;
		this.transmitUserName=transmitUserName;
		this.transmitMsgContent = transmitMsgContent;
		this.orignalUserId=orignalUserId;
		this.orignalUserName = orignalUserName;
		this.orignalMsgContent = orignalMsgContent;
		this.orignalMsgPicture = orignalMsgPicture;
		this.orignalActStartTime = orignalActStartTime;
		this.orignalActEndTime = orignalActEndTime;
		this.orignalActPlace = orignalActPlace;
		this.leaderName=leaderName;
	}
	
	public int getTransmitUserId() {
		return transmitUserId;
	}

	public void setTransmitUserId(int transmitUserId) {
		this.transmitUserId = transmitUserId;
	}

	public int getOrignalUserId() {
		return orignalUserId;
	}

	public void setOrignalUserId(int orignalUserId) {
		this.orignalUserId = orignalUserId;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public String getTransmitUserName() {
		return transmitUserName;
	}

	public void setTransmitUserName(String transmitUserName) {
		this.transmitUserName = transmitUserName;
	}

	public String getTransmitMsgContent() {
		return transmitMsgContent;
	}

	public void setTransmitMsgContent(String transmitMsgContent) {
		this.transmitMsgContent = transmitMsgContent;
	}

	public String getOrignalUserName() {
		return orignalUserName;
	}

	public void setOrignalUserName(String orignalUserName) {
		this.orignalUserName = orignalUserName;
	}

	public String getOrignalMsgContent() {
		return orignalMsgContent;
	}

	public void setOrignalMsgContent(String orignalMsgContent) {
		this.orignalMsgContent = orignalMsgContent;
	}

	public String getOrignalMsgPicture() {
		return orignalMsgPicture;
	}

	public void setOrignalMsgPicture(String orignalMsgPicture) {
		this.orignalMsgPicture = orignalMsgPicture;
	}

	public String getOrignalActStartTime() {
		return orignalActStartTime;
	}

	public void setOrignalActStartTime(String orignalActStartTime) {
		this.orignalActStartTime = orignalActStartTime;
	}

	public String getOrignalActEndTime() {
		return orignalActEndTime;
	}

	public void setOrignalActEndTime(String orignalActEndTime) {
		this.orignalActEndTime = orignalActEndTime;
	}

	public String getOrignalActPlace() {
		return orignalActPlace;
	}

	public void setOrignalActPlace(String orignalActPlace) {
		this.orignalActPlace = orignalActPlace;
	}
	
	
}
