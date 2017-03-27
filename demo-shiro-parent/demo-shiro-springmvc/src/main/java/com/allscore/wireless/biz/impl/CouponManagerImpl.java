package com.allscore.wireless.biz.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


import com.allscore.wireless.biz.CouponManager;
import com.allscore.wireless.common.DateUtil;

import com.allscore.wireless.dao.Coupon;
import com.allscore.wireless.dao.CouponExample;
import com.allscore.wireless.dao.CouponMapper;
import com.allscore.wireless.dao.FundPool;
import com.allscore.wireless.dao.FundPoolExample;
import com.allscore.wireless.dao.FundPoolMapper;
import com.allscore.wireless.dao.PromotionPlan;
import com.allscore.wireless.dao.PromotionPlanExample;
import com.allscore.wireless.dao.PromotionPlanMapper;
import com.allscore.wireless.dao.TokenInfoExample;
import com.allscore.wireless.dao.TokenInfoMapper;

public class CouponManagerImpl implements CouponManager {
	
	private static final Logger  logger = Logger.getLogger(CouponManagerImpl.class);
	
	@Autowired
	CouponMapper couponMapper;
	
	@Autowired
	FundPoolMapper fundPoolMapper;
	
	@Autowired
	PromotionPlanMapper promotionPlanMapper;
	
	@Autowired
	TokenInfoMapper tokenInfoMapper;
	
	
	public int getCouponById(String id,String merchant_code) {
		
		CouponExample couponExample = new CouponExample();
		CouponExample.Criteria couponExampleCir = couponExample.createCriteria();
		if(null!=merchant_code && !"".equals(merchant_code))
			couponExampleCir.andMERCHANT_CODEEqualTo(merchant_code);
		if(null!=id && !"".equals(id))
			couponExampleCir.andUSER_IDEqualTo(id);
		couponExampleCir.andSTATUSEqualTo("00");
       // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
		couponExampleCir.andEND_DATEGreaterThanOrEqualTo(new Date());

		return couponMapper.countByExample(couponExample);
		
	}
	
	/**
	 * 
	 */
	public List<Coupon> getCouponByList(String id) {
		
		CouponExample couponExample = new CouponExample();
		CouponExample.Criteria couponExampleCir = couponExample.createCriteria();
		if(null!=id && !"".equals(id))
			couponExampleCir.andUSER_IDEqualTo(id);
		//couponExampleCir.andSTATUSEqualTo("00");
       // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
		//couponExampleCir.andEND_DATEGreaterThanOrEqualTo(new Date());
		int total = couponMapper.countByExample(couponExample);
		
		
		couponExample.setOrderByClause("create_time desc");
		return couponMapper.selectByExample(couponExample);
		
	}
	
	/**
	 * 清除资金池当日金额、修改到期优惠券状态。
	 * 定时任务
	 */
	public void updateCouponStatus() {
		CouponExample couponExample = new CouponExample();
		CouponExample.Criteria couponExampleCir = couponExample.createCriteria();
		couponExampleCir.andSTATUSEqualTo("00");
		couponExampleCir.andEND_DATELessThanOrEqualTo(new Date());
		
		Coupon coupon = new Coupon();
		coupon.setSTATUS("02");
		this.couponMapper.updateByExampleSelective(coupon, couponExample);
        
		
		FundPoolExample fundPoolExample = new FundPoolExample();
		
		FundPool fundPool = new FundPool();
		fundPool.setDAY_MOENY(new BigDecimal("0"));
		
		this.fundPoolMapper.updateByExampleSelective(fundPool, fundPoolExample);
		
	}
    
    /**
     * 修改已过期的促销方案为无效
     * 定时任务
     */
	public void updatePromotionPlanStatus() throws Exception {
		PromotionPlanExample promotionPlanExample = new PromotionPlanExample();
		PromotionPlanExample.Criteria promotionPlanExampleCir = promotionPlanExample.createCriteria();
		promotionPlanExampleCir.andSTART_TIMEEqualTo("00");
		List<PromotionPlan> listPromotionPlan = this.promotionPlanMapper.selectByExample(promotionPlanExample);
		if(listPromotionPlan!=null && listPromotionPlan.size() > 0){
			for(PromotionPlan promotionPlan:listPromotionPlan){
				String start_time = promotionPlan.getSTART_TIME();
				String valid_date = promotionPlan.getVALID_DATE();
				boolean flag = isNumeric(valid_date);
				if(flag){
					//方案生效时间+优惠券有效期（天）
					String validDate = DateUtil.getAddDate(start_time, valid_date);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String tDate = DateUtil.getCurrentFormatDate("yyyy-MM-dd");//当前日期
					//有效期是否小于等于当前日期
					if(sdf.parse(validDate).getTime() <= sdf.parse(tDate).getTime())
					{   
						promotionPlan.setSTATUS("01");
						this.promotionPlanMapper.updateByPrimaryKeySelective(promotionPlan);
					}	
				}	
			}	
		}
	}

	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
    
	//定时任务
	public void deleteTokenInfo() throws Exception {
	   TokenInfoExample tokenInfoExample = new TokenInfoExample();
	   this.tokenInfoMapper.deleteByExample(tokenInfoExample);
	}
	
}
