package action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import vo.RealWeatherBean;

public class RealWeatherAction extends BaseAction {
	/**
	 * 处理显示实时天气的Action
	 */
	private static final long serialVersionUID = 1L;
	private List<RealWeatherBean> realWeather;

	public List<RealWeatherBean> getRealWeather() {
		return realWeather;
	}

	public void setRealWeather(List<RealWeatherBean> realWeather) {
		this.realWeather = realWeather;
	}

	@Override
	public String execute() throws Exception {
		ActionContext ctx=ActionContext.getContext();
		String userName=(String) ctx.getSession().get("user");
		setRealWeather(baseService.getRealWeather(userName));
		return super.execute();
	}
	
}
