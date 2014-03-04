package action.setsys;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.struts2.ServletActionContext;

import service.WebConstant;

import com.opensymphony.xwork2.ActionContext;

import domain.UserInfo;

import action.BaseAction;

public class SetAvatorAction extends BaseAction {
	/**
	 * 修改头像
	 */
	private static final long serialVersionUID = 1L;
	private String avator;
	private String avatorFileName;
	private String avatorContentType;
	private String savePath;
	private String tip;
	
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public String getAvator() {
		return avator;
	}
	public void setAvator(String avator) {
		this.avator = avator;
	}
	public String getAvatorFileName() {
		return avatorFileName;
	}
	public void setAvatorFileName(String avatorFileName) {
		this.avatorFileName = avatorFileName;
	}
	public String getAvatorContentType() {
		return avatorContentType;
	}
	public void setAvatorContentType(String avatorContentType) {
		this.avatorContentType = avatorContentType;
	}
	public String getSavePath() {
		return ServletActionContext.getServletContext().getRealPath(savePath);
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	@Override
	public String execute() throws Exception {
		if(avator!=null){
			ActionContext ctx=ActionContext.getContext();
			String userName=(String) ctx.getSession().get("user");
			UserInfo userInfo=baseService.findByUserName(userName).get(0);
			String path=getSavePath()+"\\"+userInfo.getEmail()+"\\"+getAvatorFileName();
			
			File file=new File(path);
			if(!file.getParentFile().exists())
				file.getParentFile().mkdirs();
			FileOutputStream fos=new FileOutputStream(path);
			FileInputStream fis=new FileInputStream(getAvator());
			byte[] buffer=new byte[1024];
			int len=0;
			while((len=fis.read(buffer)) > 0)
				fos.write(buffer,0,len);
			fis.close();
			fos.close();
			baseService.setUserAvator(userName,path);
			setTip("修改头像成功！");
			return WebConstant.SETUSERAVATORYES;
	}
	else
		return SUCCESS;
	}
	
}
