package action;

import com.opensymphony.xwork2.ActionContext;

import service.WebAlgo;
import domain.UserInfo;
import domain.WeatherInfo;

public class PublishWeatherAction extends BaseAction {
	/**
	 * 用户发布天气的Action
	 */
	private static final long serialVersionUID = 1L;
	private WeatherInfo weatherInfo;
	private String place;
	
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public WeatherInfo getWeatherInfo() {
		return weatherInfo;
	}

	public void setWeatherInfo(WeatherInfo weatherInfo) {
		this.weatherInfo = weatherInfo;
	}

	@Override
	public String execute() throws Exception {
		WebAlgo algo=new WebAlgo();
		ActionContext ctx=ActionContext.getContext();
		String userName=(String) ctx.getSession().get("user");
		UserInfo userInfo=baseService.findByUserName(userName).get(0);
		
		String places[]=null;
		if(weatherInfo.getCoordinate().length()!=0||place.length()!=0){
			if(weatherInfo.getCoordinate().length()!=0){
				places=algo.getPlaceNameByCoorinateByBaiduMap(weatherInfo.getCoordinate());
				
			}else{
				places=place.split("，");
			}
			weatherInfo.setProvince(places[0]);
			weatherInfo.setCity(places[1]);
			weatherInfo.setDistrict(places[2]);
			if(places.length==4)
				weatherInfo.setOther(places[3]);
			else
				weatherInfo.setOther("");
		}
		else{
			weatherInfo.setProvince(userInfo.getNowprovince());
			weatherInfo.setCity(userInfo.getNowcity());
			weatherInfo.setDistrict(userInfo.getNowdistrict());
			weatherInfo.setOther(userInfo.getNowother());
		}
		
		baseService.addWeather(weatherInfo,userInfo);
		
		return SUCCESS;
	}
	
}
