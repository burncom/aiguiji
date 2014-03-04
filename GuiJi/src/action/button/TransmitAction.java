package action.button;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import domain.UserInfo;

import vo.TransmitBean;
import action.BaseAction;

public class TransmitAction extends BaseAction {
	/**
	 * 处理  转发 请求
	 */
	private static final long serialVersionUID = 1L;
	//当前会话的用户名
	private String userName;
	private int msgId;
	private int firstMsgId;
	private String type;
	private int userId;
	private String userLogo;
	private TransmitBean transmitBean;
	private String msgContent;
	private boolean comment;
	private boolean orignalComment;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getMsgId() {
		return msgId;
	}
	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}
	public int getFirstMsgId() {
		return firstMsgId;
	}
	public void setFirstMsgId(int firstMsgId) {
		this.firstMsgId = firstMsgId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserLogo() {
		return userLogo;
	}
	public void setUserLogo(String userLogo) {
		this.userLogo = userLogo;
	}
	public TransmitBean getTransmitBean() {
		return transmitBean;
	}
	public void setTransmitBean(TransmitBean transmitBean) {
		this.transmitBean = transmitBean;
	}
	
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	
	public boolean isComment() {
		return comment;
	}
	public void setComment(boolean comment) {
		this.comment = comment;
	}
	public boolean isOrignalComment() {
		return orignalComment;
	}
	public void setOrignalComment(boolean orignalComment) {
		this.orignalComment = orignalComment;
	}
	@Override
	public String execute() throws Exception {
		ActionContext ctx=ActionContext.getContext();
		userName=(String) ctx.getSession().get("user");
		List<UserInfo> users=baseService.findByUserName(userName);
		UserInfo userInfo=users.get(0);
		String path=userInfo.getAvatar();
		String paths[]=path.split("GuiJi");
		setUserId(userInfo.getUser_id());
		setUserLogo(paths[1].replace('\\', '/'));
		setTransmitBean(baseService.getTransmitBean(userInfo,msgId, Integer.parseInt(type)));
		if(msgContent==null)
			return "doTransmit";
		else {
			baseService.doTransmit(userInfo, msgId, firstMsgId, Integer.parseInt(type), msgContent, comment, orignalComment);
			return SUCCESS;
		}
			
	}
}
