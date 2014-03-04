package domain;

import java.io.Serializable;

public class UserUserRelationship implements Serializable {
	/**
	 * �û����û���ϵ��
	 */
	private static final long serialVersionUID = 1L;
	//�û����û���ϵ������
	private int id;
	//����ע�����
	private int follower_id;
	//��ϵ����(1:��˿��2����ע��3�������ע)
	private int type;
	//��Ӧ�û���ϢԪ���ݱ�
	private UserInfo userInfo;
	
	public UserUserRelationship(){
		
	}

	public UserUserRelationship(int id, int follower_id, int type,
			UserInfo userInfo) {
		super();
		this.id = id;
		this.follower_id = follower_id;
		this.type = type;
		this.userInfo = userInfo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFollower_id() {
		return follower_id;
	}

	public void setFollower_id(int follower_id) {
		this.follower_id = follower_id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
}
