package action.button;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.catalina.ant.BaseRedirectorHelperTask;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import domain.UserInfo;

import action.BaseAction;

public class UploadPictureAction extends BaseAction {
	/**
	 * 处理上传图片的Action
	 */
	private static final long serialVersionUID = 1L;
	private String picture;
	private String pictureFileName;
	private String pictureContentType;
	private String savePath;
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
	@Override
	public String execute() throws Exception {
		if(picture!=null){
			ActionContext ctx=ActionContext.getContext();
			String userName=(String) ctx.getSession().get("user");
			UserInfo userInfo=baseService.findByUserName(userName).get(0);
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
			baseService.uploadPicture(userName, path);
		}
		return SUCCESS;
	}
	
}
