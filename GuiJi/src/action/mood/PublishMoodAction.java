package action.mood;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import domain.MessageInfo;
import domain.UserInfo;

import service.WebAlgo;
import action.BaseAction;

public class PublishMoodAction extends BaseAction {
	/**
	 * 处理发布心情的Action
	 */
	private static final long serialVersionUID = 1L;
	private String userLogo;
	private MessageInfo messageInfo;
	private String place;
	private String moodPic;
	private String moodPicFileName;
	private String savePath;
	private String mood;
	
	public String getMood() {
		return mood;
	}
	public void setMood(String mood) {
		this.mood = mood;
	}
	public MessageInfo getMessageInfo() {
		return messageInfo;
	}
	public void setMessageInfo(MessageInfo messageInfo) {
		this.messageInfo = messageInfo;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getMoodPic() {
		return moodPic;
	}
	public void setMoodPic(String moodPic) {
		this.moodPic = moodPic;
	}
	public String getMoodPicFileName() {
		return moodPicFileName;
	}
	public void setMoodPicFileName(String moodPicFileName) {
		this.moodPicFileName = moodPicFileName;
	}
	public String getSavePath() {
		return ServletActionContext.getServletContext().getRealPath(savePath);
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public String getUserLogo() {
		return userLogo;
	}
	public void setUserLogo(String userLogo) {
		this.userLogo = userLogo;
	}
	@Override
	public String execute() throws Exception {
		if(messageInfo==null)
			return "doPublishMood";
		else{
			ActionContext ctx=ActionContext.getContext();
			String userName=(String) ctx.getSession().get("user");
			UserInfo userInfo=baseService.findByUserName(userName).get(0);
			
			if(getMoodPic()!=null)
			{
				String path=getSavePath()+"\\"+userInfo.getEmail()+"\\"+getMoodPicFileName();
				File file=new File(path);
				if(!file.getParentFile().exists())
					file.getParentFile().mkdirs();
				FileOutputStream fos=new FileOutputStream(path);
				FileInputStream fis=new FileInputStream(getMoodPic());
				byte[] buffer=new byte[1024];
				int len=0;
				while((len=fis.read(buffer)) > 0)
					fos.write(buffer,0,len);
				fis.close();
				fos.close();
				
				messageInfo.setPicture(path);
				
			}
			//如果只是传送了坐标数据，则通过坐标数据解析地址保留到数据库
			//信息地理位置
			String places[]=null;
			if(messageInfo.getCoordinate().length() !=0 || getPlace().length()!=0){
				if(messageInfo.getCoordinate().length() !=0){
					WebAlgo algo=new WebAlgo();
					places=algo.getPlaceNameByCoorinateByBaiduMap(messageInfo.getCoordinate());
				}
				else if(getPlace().length()!=0){
					places=place.split("，");
				}
				
				messageInfo.setProvince(places[0]);
				messageInfo.setCity(places[1]);
				messageInfo.setDistrict(places[2]);
				if(places.length==4)
					messageInfo.setOther(places[3]);
				else 
					messageInfo.setOther("");
			}
			else{
				messageInfo.setProvince(userInfo.getNowprovince());
				messageInfo.setCity(userInfo.getNowcity());
				messageInfo.setDistrict(userInfo.getNowdistrict());
				messageInfo.setOther(userInfo.getNowother());
			}
				
			if(mood.length()!=0)
				messageInfo.setMood(Integer.parseInt(mood));
			baseService.doPublishMood(messageInfo, userName);
			
			return SUCCESS;
		}
	}
	
}
