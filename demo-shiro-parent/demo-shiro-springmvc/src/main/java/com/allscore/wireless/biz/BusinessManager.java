package com.allscore.wireless.biz;

import java.util.List;
import java.util.Map;

import com.allscore.wireless.common.Pager;
import com.allscore.wireless.dao.MemberPublic;
import com.allscore.wireless.dao.ShopBusiness;
import com.allscore.wireless.dao.Users;


public interface BusinessManager {
	
	Map getBusinessByMap(Pager pager, Map map) throws Exception;
	
	Map getUsersBusinessCzByMap(Pager pager, Map map);
	
	Map getUsersBusinessXfByMap(Pager pager, Map map);
	
	Map getUsersBusinessTkByMap(Pager pager, Map map);
	
	Map getUsersBusinessJfByMap(Pager pager, Map map);
	
	Map getShopBusinessTxByMap(Pager pager, Map map);
	
	Map getShopBusinessPoolByMap(Pager pager, Map map);
	
	ShopBusiness getShopBusinessByMap(Map map);
	
}
