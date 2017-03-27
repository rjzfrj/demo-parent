package com.allscore.wireless.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.allscore.wireless.biz.CouponManager;


public class CouponQuartzTask {
	private static final Logger logger = Logger.getLogger(CouponQuartzTask.class);
	@Autowired
	CouponManager couponManager;
	
	public void execute() throws Exception{
		//修改当日已过期优惠券、清除资金池当日金额
		this.couponManager.updateCouponStatus();
		
		//修改促销方案状态
		this.couponManager.updatePromotionPlanStatus();
		
		//删除表WX_TOKEN_INFO信息
		this.couponManager.deleteTokenInfo();
    }
}
