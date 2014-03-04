package dao.impl;

import hibernate3.support.GuiJiHibernateDaoSupport;

import java.util.List;

import dao.PhotosInfoDAO;
import domain.PhotosInfo;

public class PhotosInfoDAOImpl extends GuiJiHibernateDaoSupport implements
		PhotosInfoDAO {

	@Override
	public PhotosInfo get(int photo_id) {
		return getHibernateTemplate().get(PhotosInfo.class, photo_id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PhotosInfo> findByUserId(int user_id) {
		return (List<PhotosInfo>)getHibernateTemplate().find("from PhotosInfo where user_id=?",user_id);
	}

	@Override
	public void save(PhotosInfo photosInfo) {
		getHibernateTemplate().save(photosInfo);
	}

	@Override
	public void delete(int photo_id) {
		getHibernateTemplate().delete(get(photo_id));
	}
	
}
