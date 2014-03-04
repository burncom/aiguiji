package dao;

import java.util.List;

import domain.WeatherInfo;


public interface WeatherInfoDAO {
	/**
	 * 根据天气元数据表主键获取相应的实例
	 * @param weather_id
	 * @return
	 */
	public	WeatherInfo	get(int weather_id);
	/**
	 * 找到所有的天气实例
	 * @return
	 */
	public	List<WeatherInfo>	findAll();
	/**
	 * 根据用户主键获取天气实例
	 * @param user_id
	 * @return
	 */
	public	List<WeatherInfo>	findByUserId(int user_id);
	/**
	 * 通过省份，城市，县   日期 获取天气实例
	 * @param province
	 * @param city
	 * @param district
	 * @return
	 */
	public	List<WeatherInfo>	findByPCD(String province,String city,String district,int datetime);
	/**
	 * 通过省份，城市 ，日期  获取天气实例
	 * @param province
	 * @param city
	 * @return
	 */
	public	List<WeatherInfo>	findByPCD(String province,String city,int datetime);
	/**
	 * 根据实例保存入数据库
	 * @param weatherInfo
	 */
	public	void	save(WeatherInfo weatherInfo);
	/**
	 * 根据实例更新数据库
	 * @param weatherInfo
	 */
	public	void 	update(WeatherInfo weatherInfo);
	/**
	 * 根据实例删除数据库
	 * @param weatherInfo
	 */
	public	void 	delete(WeatherInfo weatherInfo);
	/**
	 * 根据主键删除
	 * @param weather_id
	 */
	public	void	delete(int weather_id);
}
