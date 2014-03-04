package action.button;

import java.util.List;

import vo.PicturesetBean;
import action.BaseAction;

public class PicturesetAction extends BaseAction {
	/**
	 * 处理图片集 的Action
	 */
	private static final long serialVersionUID = 1L;
	private int msgId;
	private String type;
	private List<PicturesetBean> pictureset;
	public int getMsgId() {
		return msgId;
	}
	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<PicturesetBean> getPictureset() {
		return pictureset;
	}
	public void setPictureset(List<PicturesetBean> pictureset) {
		this.pictureset = pictureset;
	}
	@Override
	public String execute() throws Exception {
		setPictureset(baseService.doPictureset(msgId, Integer.parseInt(type)));
		return SUCCESS;
	}
	
}
