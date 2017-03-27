package com.allscore.wireless.biz.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


import com.allscore.wireless.biz.UsersManager;
import com.allscore.wireless.common.Pager;
import com.allscore.wireless.dao.Coupon;
import com.allscore.wireless.dao.CouponExample;
import com.allscore.wireless.dao.CouponMapper;
import com.allscore.wireless.dao.Users;
import com.allscore.wireless.dao.UsersExample;
import com.allscore.wireless.dao.UsersMapper;


public class UsersManagerImpl implements UsersManager {
	
	private static final Logger logger = Logger.getLogger(UsersManagerImpl.class);
	
	@Autowired
	UsersMapper usersMapper;
	
	@Autowired
	CouponMapper couponMapper;

    /*
     * 查询会员基本信息列表
     * (non-Javadoc)
     * @see com.allscore.wireless.biz.UsersManager#getUsersByList(com.allscore.wireless.common.Pager, java.util.Map)
     */
	public List<Users> getUsersByList(Pager pager, Map map) {
		return null;
	}
	
	public Map getUsersByMap(Pager pager, Map map) {
		UsersExample usersExample = new UsersExample();
		UsersExample.Criteria usersExampleCir = usersExample.createCriteria();
		String member_card = (String)map.get("member_card");//会员卡号
		
		String merchant_name = (String)map.get("merchant_name");//商户名称
		String strDate = (String)map.get("strDate");//注册开始时间
		String endDate = (String)map.get("endDate");//注册结束时间
		if(null!=member_card && !"".equals(member_card)){;
		    usersExampleCir.andMEMBER_CARDLike("%"+member_card+"%");
		}	
		if(null!=merchant_name  && !"".equals(merchant_name)){
			usersExampleCir.andMERCHANT_NAMELike("%"+merchant_name+"%");
		}
		if(null!=strDate && null!=endDate && !"".equals(strDate) && !"".equals(endDate)){
			usersExample.setStrDate(strDate);
			usersExample.setEndDate(endDate);
		}
		int total = usersMapper.countByExample(usersExample);
		pager.setTotal(total);
		if(pager.getSize()>=0 && pager.getOffest()>=0){
			usersExample.setLimitStart(pager.getOffest());
			usersExample.setLimitEnd(pager.getPage()*pager.getSize());
		}
		
		usersExample.setOrderByClause("member_card desc");
		List<Users> list = usersMapper.selectByExample(usersExample);
		for(Users users :list){
			CouponExample couponExample = new CouponExample();
			CouponExample.Criteria couponExampleCir = couponExample.createCriteria();
			if(null!=users.getID() && !"".equals(users.getID()))
				couponExampleCir.andUSER_IDEqualTo(users.getID());
			//couponExampleCir.andSTATUSEqualTo("00");
	       // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        
			//couponExampleCir.andEND_DATEGreaterThanOrEqualTo(new Date());
			System.out.println("会员编号："+users.getID());
			List<Coupon> listCoupon = couponMapper.couponSumByExample(couponExample);
			if(listCoupon!=null && listCoupon.size() > 0){
				
			    int useCopSum = listCoupon.get(0).getUseCopSum();
			    System.out.println("已使用优惠券："+useCopSum);
			    int validCopSum = listCoupon.get(0).getValidCopSum();
			    System.out.println("可使用优惠券："+validCopSum);
			    int inValidCopSum = listCoupon.get(0).getInValidCopSum();
			    System.out.println("已过期优惠券："+inValidCopSum);
				users.setCOUPON_NUMBER(Integer.valueOf(useCopSum)+Integer.valueOf(validCopSum)
						+Integer.valueOf(inValidCopSum));
				users.setUseCopSum(useCopSum);
				users.setValidCopSum(validCopSum);
				users.setInValidCopSum(inValidCopSum);
			}

		}
		Map m = new HashMap();
		m.put("total", total);
		m.put("list", list);
		return m;
	}

	public Map getUsersByMobile(Pager pager,String member_card) {
		UsersExample usersExample = new UsersExample();
		UsersExample.Criteria usersExampleCir = usersExample.createCriteria();
		if(null!=member_card && !"".equals(member_card)){
			usersExampleCir.andMEMBER_CARDLike("%"+member_card+"%");
		}
		
		int total = usersMapper.countByExample(usersExample);
		pager.setTotal(total);
		if(pager.getSize()>=0 && pager.getOffest()>=0){
			usersExample.setLimitStart(pager.getOffest());
			usersExample.setLimitEnd(pager.getPage()*pager.getSize());
		}
		List<Users> list = usersMapper.selectByMember_card(usersExample);
		Map m = new HashMap();
		m.put("total", total);
		m.put("list", list);
		return m;
	}
}
