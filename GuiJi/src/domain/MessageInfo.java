package domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class MessageInfo implements Serializable {
	/**
	 * ��ϢԪ���ݱ�
	 */
	private static final long serialVersionUID = 1L;
	//��Ϣ���
	private int msg_id;
	//��Ϣ����
	private String msg_content;
	//��Ϣ���ࣨ1����Ϣԭ����2����Ϣ���ۣ�3����Ϣת��; 4,�ԭ����5������ۣ�6���ת����
	private int type;
	//����ָ��,��Χ�ǣ�1,2,3,4,5��
	private int mood;
	//�Ƽ�������
	private int recommend_amount;
	//�Ƽ��ķ���ֵ
	private int recommend_value;
	//���۹�������
	private int commented_amount;
	//��������������
	private int comment_amount;
	//ת����������
	private int transmited_amount;
	//������ת������
	private int transmit_amount;
	//��μӵ�����
	private int join_amount;
	//��μӵķ���ֵ
	private int join_value;
	//����Ϣ������ͼƬ������
	private int pictures_amount;
	//������Ϣʵʱʱ�䣨ʱ�����ӣ��룩
	private int realtime;
	//������Ϣ������ʱ��
	private int datetime;
	//��ʼ��ʵʱʱ�䣨ʱ�����ӣ��룩
	private int start_realtime;
	//��ʼ������ʱ��
	private int start_datetime;
	//������ʵʱʱ�䣨ʱ�����ӣ��룩
	private int end_realtime;
	//����������ʱ��
	private int end_datetime;
	//�����������
	private String leader_name;
	//ͼƬ�洢·��
	private String picture;
	//���꣬�����ȣ�γ�ȣ�
	private String coordinate;
	//ʡ��
	private String province;
	//����
	private String city;
	//�أ�����
	private String district;
	//���µ������ֶ�
	private String other;
	//�����
	private String actcoordinate;
	//��ص��ʡ��
	private String actprovince;
	//��ص�ĳ���
	private String actcity;
	//��ص�ĳ�������
	private String actdistrict;
	//��ص�����µ��ֶ�
	private String actother;
	//��Ϣ��������
	private int category;
	//��Ӧ�û���Ϣ������
	private UserInfo userInfo;
	//��Ӧ�û�����Ϣ������
	private Set<UserMsgIndex> userMsgIndex=new HashSet<UserMsgIndex>();
	public MessageInfo(){
		
	}

	public MessageInfo(int msg_id, String msg_content, int type,int mood,
			int recommend_amount, int recommend_value, int commented_amount,
			int comment_amount, int transmited_amount, int transmit_amount,
			int join_amount, int join_value, int pictures_amount, int realtime,
			int datetime, int start_realtime, int start_datetime,
			int end_realtime, int end_datetime, String leader_name,
			String picture, String coordinate, String province, String city,
			String district, String other, String actcoordinate,
			String actprovince, String actcity, String actdistrict,
			String actother, int category, UserInfo userInfo,
			Set<UserMsgIndex> userMsgIndex) {
		super();
		this.msg_id = msg_id;
		this.msg_content = msg_content;
		this.type = type;
		this.mood=mood;
		this.recommend_amount = recommend_amount;
		this.recommend_value = recommend_value;
		this.commented_amount = commented_amount;
		this.comment_amount = comment_amount;
		this.transmited_amount = transmited_amount;
		this.transmit_amount = transmit_amount;
		this.join_amount = join_amount;
		this.join_value = join_value;
		this.pictures_amount = pictures_amount;
		this.realtime = realtime;
		this.datetime = datetime;
		this.start_realtime = start_realtime;
		this.start_datetime = start_datetime;
		this.end_realtime = end_realtime;
		this.end_datetime = end_datetime;
		this.leader_name = leader_name;
		this.picture = picture;
		this.coordinate = coordinate;
		this.province = province;
		this.city = city;
		this.district = district;
		this.other = other;
		this.actcoordinate = actcoordinate;
		this.actprovince = actprovince;
		this.actcity = actcity;
		this.actdistrict = actdistrict;
		this.actother = actother;
		this.category = category;
		this.userInfo = userInfo;
		this.userMsgIndex = userMsgIndex;
	}

	
	public int getMood() {
		return mood;
	}

	public void setMood(int mood) {
		this.mood = mood;
	}

	public int getTransmited_amount() {
		return transmited_amount;
	}

	public void setTransmited_amount(int transmited_amount) {
		this.transmited_amount = transmited_amount;
	}
	public int getTransmit_amount() {
		return transmit_amount;
	}
	public void setTransmit_amount(int transmit_amount) {
		this.transmit_amount = transmit_amount;
	}
	public int getMsg_id() {
		return msg_id;
	}
	public void setMsg_id(int msg_id) {
		this.msg_id = msg_id;
	}
	public String getMsg_content() {
		return msg_content;
	}
	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getRecommend_amount() {
		return recommend_amount;
	}
	public void setRecommend_amount(int recommend_amount) {
		this.recommend_amount = recommend_amount;
	}
	public int getRecommend_value() {
		return recommend_value;
	}
	public void setRecommend_value(int recommend_value) {
		this.recommend_value = recommend_value;
	}
	public int getCommented_amount() {
		return commented_amount;
	}
	public void setCommented_amount(int commented_amount) {
		this.commented_amount = commented_amount;
	}
	public int getComment_amount() {
		return comment_amount;
	}
	public void setComment_amount(int comment_amount) {
		this.comment_amount = comment_amount;
	}
	public int getJoin_amount() {
		return join_amount;
	}
	public void setJoin_amount(int join_amount) {
		this.join_amount = join_amount;
	}
	public int getJoin_value() {
		return join_value;
	}
	public void setJoin_value(int join_value) {
		this.join_value = join_value;
	}
	public String getLeader_name() {
		return leader_name;
	}
	public void setLeader_name(String leader_name) {
		this.leader_name = leader_name;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public Set<UserMsgIndex> getUserMsgIndex() {
		return userMsgIndex;
	}
	public void setUserMsgIndex(Set<UserMsgIndex> userMsgIndex) {
		this.userMsgIndex = userMsgIndex;
	}
	public int getRealtime() {
		return realtime;
	}
	public void setRealtime(int realtime) {
		this.realtime = realtime;
	}
	public int getDatetime() {
		return datetime;
	}
	public void setDatetime(int datetime) {
		this.datetime = datetime;
	}
	public int getStart_realtime() {
		return start_realtime;
	}
	public void setStart_realtime(int start_realtime) {
		this.start_realtime = start_realtime;
	}
	public int getStart_datetime() {
		return start_datetime;
	}
	public void setStart_datetime(int start_datetime) {
		this.start_datetime = start_datetime;
	}
	public int getEnd_realtime() {
		return end_realtime;
	}
	public void setEnd_realtime(int end_realtime) {
		this.end_realtime = end_realtime;
	}
	public int getEnd_datetime() {
		return end_datetime;
	}
	public void setEnd_datetime(int end_datetime) {
		this.end_datetime = end_datetime;
	}
	public int getPictures_amount() {
		return pictures_amount;
	}
	public void setPictures_amount(int pictures_amount) {
		this.pictures_amount = pictures_amount;
	}

	public String getActcoordinate() {
		return actcoordinate;
	}

	public void setActcoordinate(String actcoordinate) {
		this.actcoordinate = actcoordinate;
	}

	public String getActprovince() {
		return actprovince;
	}

	public void setActprovince(String actprovince) {
		this.actprovince = actprovince;
	}

	public String getActcity() {
		return actcity;
	}

	public void setActcity(String actcity) {
		this.actcity = actcity;
	}

	public String getActdistrict() {
		return actdistrict;
	}

	public void setActdistrict(String actdistrict) {
		this.actdistrict = actdistrict;
	}

	public String getActother() {
		return actother;
	}

	public void setActother(String actother) {
		this.actother = actother;
	}
	
}
