package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.struts2.ServletActionContext;

import service.WebAlgo;
import service.WebConstant;

import com.opensymphony.xwork2.ActionContext;

import domain.MessageInfo;
import domain.UserInfo;

public class PublishAction extends BaseAction {
	/**
	 * 处理发布消息的Action
	 */
	private static final long serialVersionUID = 1L;
	private MessageInfo messageInfo;
	private String category;
	private String place;
	private String startTime;
	private String endTime;
	private String actplace;
	private File uploadpicture;
	private String uploadpictureFileName;
	private String picPath;
	public MessageInfo getMessageInfo() {
		return messageInfo;
	}
	public void setMessageInfo(MessageInfo messageInfo) {
		this.messageInfo = messageInfo;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public File getUploadpicture() {
		return uploadpicture;
	}
	public void setUploadpicture(File uploadpicture) {
		this.uploadpicture = uploadpicture;
	}
	public String getUploadpictureFileName() {
		return uploadpictureFileName;
	}
	public void setUploadpictureFileName(String uploadpictureFileName) {
		this.uploadpictureFileName = uploadpictureFileName;
	}
	public String getPicPath() {
		return ServletActionContext.getServletContext().getRealPath(picPath);
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	
	public String getActplace() {
		return actplace;
	}
	public void setActplace(String actplace) {
		this.actplace = actplace;
	}
	@Override
	public String execute() throws Exception {
		ActionContext ctx=ActionContext.getContext();
		String userName=(String) ctx.getSession().get("user");
		WebAlgo algo=new WebAlgo();
		UserInfo userInfo=baseService.findByUserName(userName).get(0);
		if(getUploadpicture()!=null)
		{
			String path=getPicPath()+"\\"+userInfo.getEmail()+"\\"+getUploadpictureFileName();
			File file=new File(path);
			if(!file.getParentFile().exists())
				file.getParentFile().mkdirs();
			FileOutputStream fos=new FileOutputStream(path);
			FileInputStream fis=new FileInputStream(getUploadpicture());
			byte[] buffer=new byte[1024];
			int len=0;
			while((len=fis.read(buffer)) > 0)
				fos.write(buffer,0,len);
			fis.close();
			fos.close();
			messageInfo.setPicture(path);
		}
		messageInfo.setCategory(Integer.parseInt(category));
		
		
		//活动开始和结束时间都要填写
		if((!startTime.isEmpty())&&(!endTime.isEmpty())){
			String start[]=startTime.split(",");
			messageInfo.setStart_datetime(Integer.parseInt(start[0]));
			messageInfo.setStart_realtime(Integer.parseInt(start[1]));
			
			String end[]=startTime.split(",");
			messageInfo.setEnd_datetime(Integer.parseInt(end[0]));
			messageInfo.setEnd_realtime(Integer.parseInt(end[1]));
		}
		
		//如果只是传送了坐标数据，则通过坐标数据解析地址保留到数据库
		//信息地理位置
		String places[]=null;
		if(getPlace().isEmpty()){
			if(messageInfo.getCoordinate()!=null&&messageInfo.getCoordinate().length()!=0)
				places=algo.getPlaceNameByCoorinateByBaiduMap(messageInfo.getCoordinate());
			else
				places=algo.getPlaceNameByCoorinateByBaiduMap(userInfo.getNowcoordinate());
		}
		else 
			places=place.split(" ");//以空格划分数据段
		messageInfo.setProvince(places[0]);
		messageInfo.setCity(places[1]);
		messageInfo.setDistrict(places[2]);
		if(places.length==4)
			messageInfo.setOther(places[3]);
		else 
			messageInfo.setOther("");
		messageInfo.setType(WebConstant.DB_MSGORIGNAL);
		
		messageInfo.setLeader_name(null);
		
		//如果只是传送了坐标数据，则通过坐标数据解析地址保留到数据库
		//活动地理位置必须要填写
		if(getActplace().length()!=0 || (messageInfo.getActcoordinate()!=null&&messageInfo.getActcoordinate().length()!=0)){
			String actPlaces[]=null;
			if(getActplace().length()==0){
				actPlaces=algo.getPlaceNameByCoorinateByBaiduMap(messageInfo.getActcoordinate());
			}
			else 
				actPlaces=actplace.split(" ");//以空格划分数据段 
			messageInfo.setActprovince(actPlaces[0]);
			messageInfo.setActcity(actPlaces[1]);
			messageInfo.setActdistrict(actPlaces[2]);
			if(actPlaces.length==4)
				messageInfo.setActother(actPlaces[3]);
			else
				messageInfo.setActother("");
			
			messageInfo.setType(WebConstant.DB_ACTORIGNAL);//信息类型为活动原创
			
			if(messageInfo.getLeader_name()==null||messageInfo.getLeader_name().length()==0)
				messageInfo.setLeader_name(userName);
		}
		if(messageInfo.getActcoordinate()==null||messageInfo.getActcoordinate().length()==0)
			messageInfo.setActcoordinate(null);
		baseService.addMessage(messageInfo, userName);
		
		return SUCCESS;
	}
}
