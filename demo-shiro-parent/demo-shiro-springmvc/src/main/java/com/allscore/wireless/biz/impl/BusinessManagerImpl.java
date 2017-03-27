package com.allscore.wireless.biz.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;





import com.allscore.wireless.biz.BusinessManager;
import com.allscore.wireless.common.DateUtil;
import com.allscore.wireless.common.Pager;
import com.allscore.wireless.dao.Business;
import com.allscore.wireless.dao.BusinessExample;
import com.allscore.wireless.dao.BusinessMapper;
import com.allscore.wireless.dao.ShopBusiness;
import com.allscore.wireless.dao.UsersBussiness;


public class BusinessManagerImpl implements BusinessManager {
	
	private static final Logger logger = Logger.getLogger(BusinessManagerImpl.class);
	
	@Autowired
	BusinessMapper businessMapper;

    /*
     * 查询会员基本信息列表
     * (non-Javadoc)
     * @see com.allscore.wireless.biz.UsersManager#getUsersByList(com.allscore.wireless.common.Pager, java.util.Map)
     */
	
	public Map getBusinessByMap(Pager pager, Map map) throws Exception {
	
		BusinessExample businessExample = new BusinessExample();

		String member_card = (String)map.get("member_card");
		String merchant_name = (String)map.get("merchant_name");
		String status = (String)map.get("status");
		
		if(null!=member_card && !"".equals(member_card)){
			businessExample.setMember_card(member_card);
		}
		if(null!=merchant_name && !"".equals(merchant_name)){
			businessExample.setMerchant_name(merchant_name);
			
		}
		if(null!=status && !"".equals(status)){
			businessExample.setStatus(status);
		}
		businessExample.setEndDate(DateUtil.getCurrentFormatDate("yyyy-MM-dd"));
		int total = businessMapper.countByExample(businessExample);
		pager.setTotal(total);
		if(pager.getSize()>=0 && pager.getOffest()>=0){
		   businessExample.setLimitStart(pager.getOffest());
		   businessExample.setLimitEnd(pager.getPage()*pager.getSize());
		}
		
		businessExample.setOrderByClause("create_time desc");
		List<Business> list = businessMapper.selectByExample(businessExample);
		
	    for(Business business:list){
	    	String startDate = business.getSTART_DATE().substring(0, 10);
	    	String endDate = business.getEND_DATE().substring(0, 10);
	    	long day = DateUtil.getDateSub(startDate, endDate);
	    	business.setValidDay(String.valueOf(day));
	    }
		Map m = new HashMap();
		m.put("total", total);
		m.put("list", list);
		return m;
	}

	public Map getUsersBusinessCzByMap(Pager pager, Map map) {
	
		BusinessExample businessExample = new BusinessExample();
		String strDate = (String)map.get("strDate");
		String endDate = (String)map.get("endDate");
		String member_card = (String)map.get("member_card");
		String merchant_name = (String)map.get("merchant_name");
		String serial = (String)map.get("serial");
		BigDecimal order_amt = new BigDecimal((String)map.get("order_amt"));
		String business_result = (String)map.get("business_result");

		if(null!=strDate && null!=endDate && !"".equals(strDate) && !"".equals(endDate)){
			businessExample.setStrDate(strDate);
			businessExample.setEndDate(endDate);
	
		}
		if(null!=member_card && !"".equals(member_card)){
			businessExample.setMember_card(member_card);
		}
		if(null!=merchant_name && !"".equals(merchant_name)){
			businessExample.setMerchant_name(merchant_name);	
		}
		if(null!=serial && !"".equals(serial)){
			businessExample.setSerial(serial);
		}
		if(null!=order_amt && new BigDecimal("0").compareTo(order_amt)==-1){
			businessExample.setOrder_amt(order_amt);
		}
		if(null!=business_result && !"".equals(business_result)){
			businessExample.setBusiness_result(business_result);
		}
		
		
		
		int total = businessMapper.countUsersBussinessCzByExample(businessExample);
		pager.setTotal(total);
		if(pager.getSize()>=0 && pager.getOffest()>=0){
			businessExample.setLimitStart(pager.getOffest());
			businessExample.setLimitEnd(pager.getPage()*pager.getSize());
		}
		businessExample.setOrderByClause("business_time desc");
		List<UsersBussiness> list = businessMapper.selectUsersBussinessCzByExample(businessExample);
		if(list !=null && list.size()>0){
			for(UsersBussiness usersBussiness:list){
				BigDecimal tran_amt = usersBussiness.getTRAN_AMT();
				BigDecimal poundage = usersBussiness.getPOUNDAGE();
				// 结算金额= 实际支付金额-手续费
				usersBussiness.setFACT_AMT(tran_amt.subtract(poundage));
			}
			
		}
		Map m = new HashMap();
		m.put("total", total);
		m.put("list", list);
		return m;
	}
	
	public Map getUsersBusinessXfByMap(Pager pager, Map map) {
	
		BusinessExample businessExample = new BusinessExample();
		String strDate = (String)map.get("strDate");
		String endDate = (String)map.get("endDate");
		String member_card = (String)map.get("member_card");
		String merchant_name = (String)map.get("merchant_name");
		String serial = (String)map.get("serial");
		String id = (String)map.get("id");
		String business_type = (String)map.get("business_type");
		String business_result = (String)map.get("business_result");
		
		if(null!=strDate && null!=endDate && !"".equals(strDate) && !"".equals(endDate)){
			businessExample.setStrDate(strDate);
			businessExample.setEndDate(endDate);
	
		}
		
		if(null!=member_card && !"".equals(member_card)){
			businessExample.setMember_card(member_card);
		}
		if(null!=merchant_name && !"".equals(merchant_name)){
			businessExample.setMerchant_name(merchant_name);	
		}
		if(null!=serial && !"".equals(serial)){
			businessExample.setSerial(serial);
		}
		if(null!=id && !"".equals(id)){
			businessExample.setId(id);
		}
		if(null!=business_type && !"".equals(business_type)){
			businessExample.setBusiness_type(business_type);
		}
		if(null!=business_result && !"".equals(business_result)){
			businessExample.setBusiness_result(business_result);
		}

		int total = businessMapper.countUsersBussinessXfByExample(businessExample);
		pager.setTotal(total);
		if(pager.getSize()>=0 && pager.getOffest()>=0){
			businessExample.setLimitStart(pager.getOffest());
			businessExample.setLimitEnd(pager.getPage()*pager.getSize());
		}
		businessExample.setOrderByClause("business_time desc");
		List<UsersBussiness> list = businessMapper.selectUsersBussinessXfByExample(businessExample);
		
		if(list !=null && list.size()>0){
			for(UsersBussiness usersBussiness:list){
				BigDecimal tran_amt = usersBussiness.getTRAN_AMT();
				BigDecimal poundage = usersBussiness.getPOUNDAGE();
				// 结算金额= 实际支付金额-手续费
				usersBussiness.setFACT_AMT(tran_amt.subtract(poundage));
			}
			
		}
		Map m = new HashMap();
		m.put("total", total);
		m.put("list", list);
		return m;
	}
	
	public Map getUsersBusinessTkByMap(Pager pager, Map map) {
	
		BusinessExample businessExample = new BusinessExample();
		String strDate = (String)map.get("strDate");
		String endDate = (String)map.get("endDate");
		String member_card = (String)map.get("member_card");
		String merchant_name = (String)map.get("merchant_name");
		String serial = (String)map.get("serial");
		String source_serial = (String)map.get("source_serial");
		String id = (String)map.get("id");
		String business_result = (String)map.get("business_result");
		
		if(null!=strDate && null!=endDate && !"".equals(strDate) && !"".equals(endDate)){
			businessExample.setStrDate(strDate);
			businessExample.setEndDate(endDate);
	
		}
		
		if(null!=member_card && !"".equals(member_card)){
			businessExample.setMember_card(member_card);
		}
		if(null!=merchant_name && !"".equals(merchant_name)){
			businessExample.setMerchant_name(merchant_name);	
		}
		if(null!=serial && !"".equals(serial)){
			businessExample.setSerial(serial);
		}
		if(null!=source_serial && !"".equals(source_serial)){
			businessExample.setSource_serial(source_serial);
		}
		if(null!=id && !"".equals(id)){
			businessExample.setId(id);
		}
		if(null!=business_result && !"".equals(business_result)){
			businessExample.setBusiness_result(business_result);
		}
		
		int total = businessMapper.countUsersBussinessTkByExample(businessExample);
		pager.setTotal(total);
		if(pager.getSize()>=0 && pager.getOffest()>=0){
			businessExample.setLimitStart(pager.getOffest());
			businessExample.setLimitEnd(pager.getPage()*pager.getSize());
		}
		businessExample.setOrderByClause("business_time desc");
		List<UsersBussiness> list = businessMapper.selectUsersBussinessTkByExample(businessExample);

		Map m = new HashMap();
		m.put("total", total);
		m.put("list", list);
		return m;
	}
	
	public Map getUsersBusinessJfByMap(Pager pager, Map map) {
	
		BusinessExample businessExample = new BusinessExample();
		String strDate = (String)map.get("strDate");
		String endDate = (String)map.get("endDate");
		String member_card = (String)map.get("member_card");
		String merchant_name = (String)map.get("merchant_name");
		String serial = (String)map.get("serial");
		String business_source = (String)map.get("business_source");
		
		if(null!=strDate && null!=endDate && !"".equals(strDate) && !"".equals(endDate)){
			businessExample.setStrDate(strDate);
			businessExample.setEndDate(endDate);
		}
		if(null!=member_card && !"".equals(member_card)){
			businessExample.setMember_card(member_card);
		}
		if(null!=merchant_name && !"".equals(merchant_name)){
			businessExample.setMerchant_name(merchant_name);	
		}
		if(null!=serial && !"".equals(serial)){
			businessExample.setSerial(serial);
		}
		if(null!=business_source && !"".equals(business_source)){
			businessExample.setBusiness_source(business_source);
		}
		
		int total = businessMapper.countUsersBussinessJfByExample(businessExample);
		pager.setTotal(total);
		if(pager.getSize()>=0 && pager.getOffest()>=0){
			businessExample.setLimitStart(pager.getOffest());
			businessExample.setLimitEnd(pager.getPage()*pager.getSize());
		}
		businessExample.setOrderByClause("business_time desc");
		List<UsersBussiness> list = businessMapper.selectUsersBussinessJfByExample(businessExample);

		Map m = new HashMap();
		m.put("total", total);
		m.put("list", list);
		return m;
	}
	
	public Map getShopBusinessTxByMap(Pager pager, Map map) {
	

		BusinessExample businessExample = new BusinessExample();
		String strDate = (String)map.get("strDate");
		String endDate = (String)map.get("endDate");
		String merchant_code = (String)map.get("merchant_code");
		String merchant_name = (String)map.get("merchant_name");
		String serial = (String)map.get("serial");
		String source_serial = (String)map.get("source_serial");
		String business_result = (String)map.get("business_result");
		
		if(null!=strDate && null!=endDate && !"".equals(strDate) && !"".equals(endDate)){
			businessExample.setStrDate(strDate);
			businessExample.setEndDate(endDate);
	
		}
		
		
		if(StringUtils.isNotBlank(merchant_name)){
			businessExample.setMerchant_name(merchant_name);	
		}
		if(StringUtils.isNotBlank(serial)){
			businessExample.setSerial(serial);
		}
		if(StringUtils.isNotBlank(source_serial)){
			businessExample.setSource_serial(source_serial);
		}
		if(StringUtils.isNotBlank(business_result)){
			businessExample.setBusiness_result(business_result);
		}
		if(StringUtils.isNotBlank(merchant_code)){
		     businessExample.setMerchant_code(merchant_code);
		}
		
		int total = businessMapper.countShopBusinessTxByExample(businessExample);
		pager.setTotal(total);
		if(pager.getSize()>=0 && pager.getOffest()>=0){
			businessExample.setLimitStart(pager.getOffest());
			businessExample.setLimitEnd(pager.getPage()*pager.getSize());
		}
		businessExample.setOrderByClause("business_time desc");
		List<ShopBusiness> list = businessMapper.selectShopBusinessTxByExample(businessExample);
		Map m = new HashMap();
		m.put("total", total);
		m.put("list", list);
		return m;
	}
	
	public Map getShopBusinessPoolByMap(Pager pager, Map map) {
	

		BusinessExample businessExample = new BusinessExample();
		String strDate = (String)map.get("strDate");
		String endDate = (String)map.get("endDate");
		String merchant_name = (String)map.get("merchant_name");
		String merchant_code = (String)map.get("merchant_code");
		String serial = (String)map.get("serial");
		String source_serial = (String)map.get("source_serial");
		String business_type = (String)map.get("business_type");
		String business_result = (String)map.get("business_result");
		
		if(null!=strDate && null!=endDate && !"".equals(strDate) && !"".equals(endDate)){
			businessExample.setStrDate(strDate);
			businessExample.setEndDate(endDate);
		}
		
		if(null!=merchant_code && !"".equals(merchant_code)){
			businessExample.setMerchant_code(merchant_code);	
		}
		if(null!=merchant_name && !"".equals(merchant_name)){
			businessExample.setMerchant_name(merchant_name);	
		}
		if(null!=serial && !"".equals(serial)){
			businessExample.setSerial(serial);
		}
		if(null!=source_serial && !"".equals(source_serial)){
			businessExample.setSource_serial(source_serial);
		}
		if(null!=business_type && !"".equals(business_type)){
			businessExample.setBusiness_type(business_type);
		}
		if(null!=business_result && !"".equals(business_result)){
			businessExample.setBusiness_result(business_result);
		}
		
		int total = businessMapper.countShopBusinessSellerPoolByExample(businessExample);
		pager.setTotal(total);
		if(pager.getSize()>=0 && pager.getOffest()>=0){
			businessExample.setLimitStart(pager.getOffest());
		    businessExample.setLimitEnd(pager.getPage()*pager.getSize());
		}
		businessExample.setOrderByClause("business_time desc");
		List<ShopBusiness> list = businessMapper.selectShopBusinessSellerPoolByExample(businessExample);
		//ShopBusiness shopBusiness = businessMapper.selectSellerPoolSum(businessExample);
		Map m = new HashMap();
		//m.put("shopBusiness", shopBusiness);
		m.put("total", total);
		m.put("list", list);
		return m;
	}

	public ShopBusiness getShopBusinessByMap(Map map) {
	

		BusinessExample businessExample = new BusinessExample();
		String strDate = (String)map.get("strDate");
		String endDate = (String)map.get("endDate");
		String merchant_name = (String)map.get("merchant_name");
		String serial = (String)map.get("serial");
		String source_serial = (String)map.get("source_serial");
		String business_type = (String)map.get("business_type");
		String business_result = (String)map.get("business_result");
		String merchant_code = (String)map.get("merchant_code");
		
		if(null!=strDate && null!=endDate && !"".equals(strDate) && !"".equals(endDate)){
			businessExample.setStrDate(strDate);
			businessExample.setEndDate(endDate);
		}

		if(null!=merchant_name && !"".equals(merchant_name)){
			businessExample.setMerchant_name(merchant_name);	
		}
		if(null!=serial && !"".equals(serial)){
			businessExample.setSerial(serial);
		}
		if(null!=source_serial && !"".equals(source_serial)){
			businessExample.setSource_serial(source_serial);
		}
		if(null!=business_type && !"".equals(business_type)){
			businessExample.setBusiness_type(business_type);
		}
		if(null!=business_result && !"".equals(business_result)){
			businessExample.setBusiness_result(business_result);
		}
		if(null!=merchant_code && !"".equals(merchant_code)){
		     businessExample.setMerchant_code(merchant_code);
		}

		return businessMapper.selectSellerPoolSum(businessExample);
	}
}
