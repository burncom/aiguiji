package action;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import domain.UserInfo;

public class EditProfileAction extends BaseAction {
	/**
	 * 处理个人信息的Action
	 */
	private static final long serialVersionUID = 1L;
	private File myAvatar;
	private String myAvatarFileName;
	private String myAvatarContentType;
	private String savePath;
	
	public File getMyAvatar() {
		return this.myAvatar;
	}
	public void setMyAvatar(File myAvatar) {
		this.myAvatar = myAvatar;
	}
	
	public String getMyAvatarFileName() {
		return myAvatarFileName;
	}
	public void setMyAvatarFileName(String myAvatarFileName) {
		this.myAvatarFileName = myAvatarFileName;
	}
	public String getMyAvatarContentType() {
		return myAvatarContentType;
	}
	public void setMyAvatarContentType(String myAvatarContentType) {
		this.myAvatarContentType = myAvatarContentType;
	}
	public String getSavePath() {
		return ServletActionContext.getServletContext().getRealPath(savePath);
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	@Override
	public String execute() throws Exception {
		ActionContext ctx=ActionContext.getContext();
		String userName=(String) ctx.getSession().get("user");
		UserInfo userInfo=baseService.findByUserName(userName).get(0);
		String path=getSavePath()+"\\"+userInfo.getEmail()+"\\"+getMyAvatarFileName();
		File file=new File(path);
		if(!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		FileOutputStream fos=new FileOutputStream(path);
		FileInputStream fis=new FileInputStream(getMyAvatar());
		byte[] buffer=new byte[1024];
		int len=0;
		while((len=fis.read(buffer)) > 0)
			fos.write(buffer,0,len);
		fis.close();
		fos.close();
		baseService.setProfile(path);
		return SUCCESS;
	}
}
