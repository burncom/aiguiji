package domain;

import java.io.Serializable;

public class MsgMsgRelationship implements Serializable {
	/**
	 * 信息与信息关系表
	 */
	private static final long serialVersionUID = 1L;
	//信息与信息关系表主键
	private int id;
	//引用信息用户序号
	private int reference_id;
	//引用信息序号
	private int reference_msg_id;
	//信息发布者序号
	private int referenced_id;
	//被引用信息序号
	private int referenced_msg_id;
	//操作类型（1：信息原创；2，信息评论；3，信息转发; 4,活动原创；5，活动评论；6，活动转发）
	private int type;
	//实时时间
	private int realtime;
	//日期时间
	private int datetime;
	
	//无参数的构造器
	public MsgMsgRelationship(){
		
	}
	//初始化全部属性的构造器
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
	//以下为各属性的set和get方法
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
