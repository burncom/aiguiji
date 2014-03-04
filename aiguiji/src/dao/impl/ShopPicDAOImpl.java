package dao.impl;

import include.AiGuiJiHibernateDaoSupport;

import java.util.List;

import dao.ShopPicDAO;
import domain.ShopPic;

public class ShopPicDAOImpl extends AiGuiJiHibernateDaoSupport implements ShopPicDAO {

	@Override
	public ShopPic get(int iPicId) {
		return getHibernateTemplate().get(ShopPic.class, iPicId);
	}

	@Override
	public void save(ShopPic shopPic) {
		getHibernateTemplate().save(shopPic);
	}

	@Override
	public void delete(ShopPic shopPic) {
		getHibernateTemplate().delete(shopPic);
	}

	@Override
	public void delete(int iPicId) {
		getHibernateTemplate().delete(get(iPicId));
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<ShopPic> getShopPicList(int iShopId) {
//		return (List<ShopPic>)getHibernateTemplate().find("from ShopPic where iShopId=?",iShopId);
//	}

	@Override
	public List<?> findByPage(String hql, int iShopId, int offset, int pageSize) {
		return super.findByPage("from ShopPic where iShopId=?", iShopId, offset, pageSize);
	}
	
}
