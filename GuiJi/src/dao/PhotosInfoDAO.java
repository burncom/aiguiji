package dao;

import java.util.List;

import domain.PhotosInfo;

public interface PhotosInfoDAO {
	/**
	 * 通过相册主键获取实例
	 * @param photo_id
	 * @return
	 */
	public PhotosInfo get(int photo_id);
	/**
	 * 通过用户主键获取该用户的所有相册
	 * @param user_id
	 * @return
	 */
	public List<PhotosInfo> findByUserId(int user_id);
	/**
	 * 保存入数据库 
	 * @param photosInfo
	 */
	public void save(PhotosInfo photosInfo);
	public void delete(int photo_id);
}
