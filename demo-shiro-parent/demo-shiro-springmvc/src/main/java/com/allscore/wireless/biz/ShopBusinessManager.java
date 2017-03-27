package com.allscore.wireless.biz;

import java.util.List;

import com.allscore.wireless.dao.ShopBusiness;

public interface ShopBusinessManager {
	
	void addShopBusiness(ShopBusiness shopBusiness);
	
	List<ShopBusiness> loadShopBusinessByOutTranNO(String outTranNo);
	
	void updateShopBusiness(ShopBusiness shopBusiness);

}
