package action.mood;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import vo.CommentBean;
import domain.MessageInfo;
import domain.UserInfo;
import action.BaseAction;

public class CommentMoodAction extends BaseAction {
	/**
	 * 心情 评论
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private MessageInfo messageInfo;
	private int msgId;
	private String picture;
	private String pictureFileName;
	private String pictureContentType;
	private String savePath;
	private List<CommentBean> commentBean;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public MessageInfo getMessageInfo() {
		return messageInfo;
	}
	public void setMessageInfo(MessageInfo messageInfo) {
		this.messageInfo = messageInfo;
	}
	public int getMsgId() {
		return msgId;
	}
	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
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
	public String getSavePath() {
		return ServletActionContext.getServletContext().getRealPath(savePath);
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public List<CommentBean> getCommentBean() {
		return commentBean;
	}
	public void setCommentBean(List<CommentBean> commentBean) {
		this.commentBean = commentBean;
	}
	@Override
	public String execute() throws Exception {
		ActionContext ctx=ActionContext.getContext();
		//userName为当前会话的用户名
		userName=(String) ctx.getSession().get("user");
		UserInfo userInfo=baseService.findByUserName(userName).get(0);
		if(messageInfo==null){
			//得到msgId这条信息的所有评论信息 
			setCommentBean(baseService.getCommentMoodBean(userInfo,msgId));
			return "doCommentMood";
		}
		else{
			
			if(picture!=null&&picture.length()!=0){
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
			baseService.addMoodComment(messageInfo, userInfo, msgId);
			
			return SUCCESS;
		}
		
	}
	
}
