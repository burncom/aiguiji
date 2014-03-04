package action.button;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import domain.MessageInfo;
import domain.UserInfo;

import vo.CommentBean;
import action.BaseAction;

public class CommentAction extends BaseAction {
	/**
	 * 处理评论的Action
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private int msgId;
	private int firstMsgId;
	private String orignalUserName;
	private String type;
	private String messageContent;
	private String picture;
	private String pictureFileName;
	private String pictureContentType;
	private String savePath;
	private String coordinate;
	private boolean comment;
	private boolean transmit;
	private List<CommentBean> commentBean;
	
	public String getSavePath() {
		return ServletActionContext.getServletContext().getRealPath(savePath);
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
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
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public boolean isComment() {
		return comment;
	}
	public void setComment(boolean comment) {
		this.comment = comment;
	}
	public boolean isTransmit() {
		return transmit;
	}
	public void setTransmit(boolean transmit) {
		this.transmit = transmit;
	}
	public List<CommentBean> getCommentBean() {
		return commentBean;
	}
	public void setCommentBean(List<CommentBean> commentBean) {
		this.commentBean = commentBean;
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
	public String getOrignalUserName() {
		return orignalUserName;
	}
	public void setOrignalUserName(String orignalUserName) {
		this.orignalUserName = orignalUserName;
	}
	
	public String getPictureFileName() {
		return pictureFileName;
	}
	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}
	public String getPictureContentType() {
		return pictureContentType;
	}
	public void setPictureContentType(String pictureContentType) {
		this.pictureContentType = pictureContentType;
	}
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String execute() throws Exception {
		ActionContext ctx=ActionContext.getContext();
		//userName为当前会话的用户名
		userName=(String) ctx.getSession().get("user");
		UserInfo userInfo=baseService.findByUserName(userName).get(0);
		//保存信息实例
		MessageInfo messageInfo=new MessageInfo();
		
		//如果当前信息为转发，则orignalUserName为最初原创；否则为null
		if(messageContent==null){
			orignalUserName=baseService.getOrignalUserName(msgId, Integer.parseInt(type));
			//得到msgId这条信息的所有评论信息 
			setCommentBean(baseService.getCommentBean(userInfo,msgId, Integer.parseInt(type)));
			return "doComment";
		}
		else{
			if(picture!=null){
				//保存上传的图片
				String path=getSavePath()+"\\"+userInfo.getEmail()+"\\"+getPictureFileName();
				File file=new File(path);
				if(!file.getParentFile().exists())
					file.getParentFile().mkdirs();
				FileOutputStream fos=new FileOutputStream(path);
				FileInputStream fis=new FileInputStream(getPicture());
				byte[] buffer=new byte[1024];
				int len=0;
				while((len=fis.read(buffer)) > 0)
					fos.write(buffer,0,len);
				fis.close();
				fos.close();
				messageInfo.setPicture(path);
			}
			
			messageInfo.setUserInfo(userInfo);
			baseService.addComment(messageInfo, userInfo, msgId, firstMsgId, Integer.parseInt(type), coordinate, messageContent, comment, transmit);
			
			return SUCCESS;
		}
			
	}
	
	
	
}
