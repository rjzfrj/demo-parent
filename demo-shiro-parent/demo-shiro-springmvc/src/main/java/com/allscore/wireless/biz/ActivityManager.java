package com.allscore.wireless.biz;

import java.util.Map;

import com.allscore.wireless.common.Pager;
import com.allscore.wireless.dao.PromotionPlan;

public interface ActivityManager {
   
	Map getPromotionPlanByMap(Pager pager,Map map);
	
	void addPromotionPlanByMap(Map map);
	
	int delPromotionPlanByID(String id);
	
	int checkIsExist(Map map);
	
	PromotionPlan getPromotionPlanByID(String id) throws Exception;
}
