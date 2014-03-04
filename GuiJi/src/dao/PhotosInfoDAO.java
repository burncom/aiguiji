package dao;

import java.util.List;

import domain.PhotosInfo;

public interface PhotosInfoDAO {
	/**
	 * ͨ�����������ȡʵ��
	 * @param photo_id
	 * @return
	 */
	public PhotosInfo get(int photo_id);
	/**
	 * ͨ���û�������ȡ���û����������
	 * @param user_id
	 * @return
	 */
	public List<PhotosInfo> findByUserId(int user_id);
	/**
	 * ���������ݿ� 
	 * @param photosInfo
	 */
	public void save(PhotosInfo photosInfo);
	public void delete(int photo_id);
}
