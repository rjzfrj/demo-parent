package com.allscore.wireless.biz;

import java.util.List;
import com.allscore.wireless.dao.Coupon;


public interface CouponManager {
	
	List<Coupon> getCouponByList(String id);
	
	void updateCouponStatus();
	
	void updatePromotionPlanStatus() throws Exception;
	
	void deleteTokenInfo() throws Exception;
	
}
