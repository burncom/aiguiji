package dao;

import java.util.List;

import domain.WeatherInfo;


public interface WeatherInfoDAO {
	/**
	 * ��������Ԫ���ݱ�������ȡ��Ӧ��ʵ��
	 * @param weather_id
	 * @return
	 */
	public	WeatherInfo	get(int weather_id);
	/**
	 * �ҵ����е�����ʵ��
	 * @return
	 */
	public	List<WeatherInfo>	findAll();
	/**
	 * �����û�������ȡ����ʵ��
	 * @param user_id
	 * @return
	 */
	public	List<WeatherInfo>	findByUserId(int user_id);
	/**
	 * ͨ��ʡ�ݣ����У���   ���� ��ȡ����ʵ��
	 * @param province
	 * @param city
	 * @param district
	 * @return
	 */
	public	List<WeatherInfo>	findByPCD(String province,String city,String district,int datetime);
	/**
	 * ͨ��ʡ�ݣ����� ������  ��ȡ����ʵ��
	 * @param province
	 * @param city
	 * @return
	 */
	public	List<WeatherInfo>	findByPCD(String province,String city,int datetime);
	/**
	 * ����ʵ�����������ݿ�
	 * @param weatherInfo
	 */
	public	void	save(WeatherInfo weatherInfo);
	/**
	 * ����ʵ���������ݿ�
	 * @param weatherInfo
	 */
	public	void 	update(WeatherInfo weatherInfo);
	/**
	 * ����ʵ��ɾ�����ݿ�
	 * @param weatherInfo
	 */
	public	void 	delete(WeatherInfo weatherInfo);
	/**
	 * ��������ɾ��
	 * @param weather_id
	 */
	public	void	delete(int weather_id);
}
