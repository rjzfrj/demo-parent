package com.allscore.wireless.biz;

import java.util.List;
import java.util.Map;

import com.allscore.wireless.common.Pager;
import com.allscore.wireless.dao.ShopBusiness;


public interface FinanceManager {
	
	Map getReviewTxByMap(Pager pager ,Map map);
	
	ShopBusiness getShopBusinessByID(String id);
	
	void updateShopBusinessByID(String id);
	
	Map getBatchTxByMap(Pager pager ,Map map);
	
	List<ShopBusiness> getShopBusinessByBatchID(String batch_id);
	
	Map getSellerChkBillByMap(Pager pager ,Map map);
	
	Map getAccManageByMap(Pager pager ,Map map);
	
	void updateCheckBusiness(Map map);
	
	void updateCheckError(String id);
	
	void updateShopBusinessByList(Map map);
	
}
