package vo;

import java.io.Serializable;

public class TopListBean implements Serializable {
	/**
	 * ¥¶¿Ì ≈≈––∞Ò
	 */
	private static final long serialVersionUID = 1L;
	private int type;
	private String typeName;
	private float count;
	public TopListBean() {
		super();
	}
	public TopListBean(int type, String typeName, float count) {
		super();
		this.type = type;
		this.typeName = typeName;
		this.count = count;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public float getCount() {
		return count;
	}
	public void setCount(float count) {
		this.count = count;
	}
	
}
