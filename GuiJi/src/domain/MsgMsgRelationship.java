package domain;

import java.io.Serializable;

public class MsgMsgRelationship implements Serializable {
	/**
	 * ��Ϣ����Ϣ��ϵ��
	 */
	private static final long serialVersionUID = 1L;
	//��Ϣ����Ϣ��ϵ������
	private int id;
	//������Ϣ�û����
	private int reference_id;
	//������Ϣ���
	private int reference_msg_id;
	//��Ϣ���������
	private int referenced_id;
	//��������Ϣ���
	private int referenced_msg_id;
	//�������ͣ�1����Ϣԭ����2����Ϣ���ۣ�3����Ϣת��; 4,�ԭ����5������ۣ�6���ת����
	private int type;
	//ʵʱʱ��
	private int realtime;
	//����ʱ��
	private int datetime;
	
	//�޲����Ĺ�����
	public MsgMsgRelationship(){
		
	}
	//��ʼ��ȫ�����ԵĹ�����
	public MsgMsgRelationship(int id, int reference_id, int reference_msg_id,
			int referenced_id, int referenced_msg_id, int type, int realtime,
			int datetime) {
		super();
		this.id = id;
		this.reference_id = reference_id;
		this.reference_msg_id = reference_msg_id;
		this.referenced_id = referenced_id;
		this.referenced_msg_id = referenced_msg_id;
		this.type = type;
		this.realtime = realtime;
		this.datetime = datetime;
	}
	//����Ϊ�����Ե�set��get����
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getReference_id() {
		return reference_id;
	}

	public void setReference_id(int reference_id) {
		this.reference_id = reference_id;
	}

	
	public int getReference_msg_id() {
		return reference_msg_id;
	}
	public void setReference_msg_id(int reference_msg_id) {
		this.reference_msg_id = reference_msg_id;
	}
	public int getReferenced_id() {
		return referenced_id;
	}

	public void setReferenced_id(int referenced_id) {
		this.referenced_id = referenced_id;
	}

	public int getReferenced_msg_id() {
		return referenced_msg_id;
	}

	public void setReferenced_msg_id(int referenced_msg_id) {
		this.referenced_msg_id = referenced_msg_id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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
	
}
