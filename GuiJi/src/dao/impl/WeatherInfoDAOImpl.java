package dao.impl;

import hibernate3.support.GuiJiHibernateDaoSupport;

import java.util.List;

import dao.WeatherInfoDAO;
import domain.WeatherInfo;

public class WeatherInfoDAOImpl extends GuiJiHibernateDaoSupport implements
		WeatherInfoDAO {

	@Override
	public WeatherInfo get(int weather_id) {
		return (WeatherInfo)getHibernateTemplate().get(WeatherInfo.class, weather_id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WeatherInfo> findAll() {
		return (List<WeatherInfo>)getHibernateTemplate().find("from WeatherInfo");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WeatherInfo> findByUserId(int user_id) {
		return (List<WeatherInfo>)getHibernateTemplate().find("from WeatherInfo where user_id=?",user_id);
	}

	@Override
	public void save(WeatherInfo weatherInfo) {
		getHibernateTemplate().save(weatherInfo);
	}

	@Override
	public void update(WeatherInfo weatherInfo) {
		getHibernateTemplate().update(weatherInfo);
	}

	@Override
	public void delete(WeatherInfo weatherInfo) {
		getHibernateTemplate().delete(weatherInfo);
	}

	@Override
	public void delete(int weather_id) {
		getHibernateTemplate().delete(get(weather_id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WeatherInfo> findByPCD(String province, String city,
			String district,int datetime) {
		return (List<WeatherInfo>)getHibernateTemplate().find("from WeatherInfo where province=? and city=? and district=? and datetime=? ",
				province,city,district,datetime);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WeatherInfo> findByPCD(String province, String city,int datetime) {
		return (List<WeatherInfo>)getHibernateTemplate().find("from WeatherInfo where province=? and city=? and datetime=? ",
				province,city,datetime);
	}

	
}
