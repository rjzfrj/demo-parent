package com.allscore.wireless.biz.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


import com.allscore.wireless.biz.FinanceManager;
import com.allscore.wireless.common.Pager;

import com.allscore.wireless.dao.CheckBusiness;
import com.allscore.wireless.dao.CheckBusinessExample;
import com.allscore.wireless.dao.CheckBusinessMapper;
import com.allscore.wireless.dao.CheckError;
import com.allscore.wireless.dao.CheckErrorExample;
import com.allscore.wireless.dao.CheckErrorMapper;
import com.allscore.wireless.dao.ShopBusiness;
import com.allscore.wireless.dao.ShopBusinessExample;
import com.allscore.wireless.dao.ShopBusinessMapper;

public class FinanceManagerImpl implements FinanceManager {
	
	private static final Logger  logger = Logger.getLogger(FinanceManagerImpl.class);
	
	@Autowired
	ShopBusinessMapper shopBusinessMapper;
	
	@Autowired
	CheckBusinessMapper checkBusinessMapper;
	
	@Autowired
	CheckErrorMapper checkErrorMapper;

    /**
     * 提现审核查询
     */
	public Map getReviewTxByMap(Pager pager, Map map) {
		ShopBusinessExample shopBusinessExample = new ShopBusinessExample();
		ShopBusinessExample.Criteria shopBusinessExampleCir = shopBusinessExample.createCriteria();

		String strDate = (String)map.get("strDate");
		String endDate = (String)map.get("endDate");
		String MERCHANT_CODE = (String)map.get("MERCHANT_CODE");
		String merchant_name = (String)map.get("merchant_name");
		String serial = (String)map.get("serial");
		String status = (String)map.get("status");
		
		if(null!=MERCHANT_CODE && !"".equals(MERCHANT_CODE)){
			shopBusinessExampleCir.andMERCHANT_CODELike("%"+MERCHANT_CODE+"%");
		}
		if(null!=merchant_name && !"".equals(merchant_name)){
			shopBusinessExampleCir.andMERCHANT_NAMELike("%"+merchant_name+"%");
		}	
		if(null!=serial && !"".equals(serial)){
			shopBusinessExampleCir.andSERIALLike("%"+serial+"%");
		}
		if(null!=status && !"".equals(status)){
			shopBusinessExampleCir.andSTATUSEqualTo(status);
		}
		if(null!=strDate && null!=endDate && !"".equals(strDate) && !"".equals(endDate)){
			shopBusinessExample.setStrDate(strDate);
			shopBusinessExample.setEndDate(endDate);
	
		}
		int total = shopBusinessMapper.countByExample(shopBusinessExample);
		pager.setTotal(total);
		if(pager.getSize()>=0 && pager.getOffest()>=0){
			shopBusinessExample.setLimitStart(pager.getOffest());
			shopBusinessExample.setLimitEnd(pager.getPage()*pager.getSize());
		}
		shopBusinessExample.setOrderByClause("create_time desc");
		List<ShopBusiness> list = shopBusinessMapper.selectByExample(shopBusinessExample);

		
		
		Map m = new HashMap();
		m.put("total", total);
		m.put("list", list);
		return m;
	}
    /**
     * 提现审核查询---查看详细
     */
	public ShopBusiness getShopBusinessByID(String id) {
		return shopBusinessMapper.selectByPrimaryKey(id);
	}
    
	/**
     * 提现审核查询---审核
     */
	public void updateShopBusinessByID(String id) {
		
		ShopBusiness shopBusiness = new ShopBusiness();
		shopBusiness.setID(id);
		shopBusiness.setSTATUS("01");
		shopBusinessMapper.updateByPrimaryKeySelective(shopBusiness);
	}
    
	/**
	 * 提现批次处理--查询
	 */
	public Map getBatchTxByMap(Pager pager, Map map) {
		ShopBusinessExample shopBusinessExample = new ShopBusinessExample();

		String strDate = (String)map.get("strDate");
		String endDate = (String)map.get("endDate");
		String MERCHANT_CODE = (String)map.get("MERCHANT_CODE");
		String merchant_name = (String)map.get("merchant_name");
		String batch_number = (String)map.get("batch_number");
		String business_result = (String)map.get("business_result");
		if(null!=strDate && null!=endDate && !"".equals(strDate) && !"".equals(endDate)){
			shopBusinessExample.setStrDate(strDate);
			shopBusinessExample.setEndDate(endDate);
	
		}
		shopBusinessExample.setMERCHANT_CODE(MERCHANT_CODE);
		shopBusinessExample.setMerchant_name(merchant_name);
		shopBusinessExample.setBatch_number(batch_number);
		shopBusinessExample.setBusiness_result(business_result);
		int total = shopBusinessMapper.countTxBatchByExample(shopBusinessExample);
		pager.setTotal(total);
		if(pager.getSize()>=0 && pager.getOffest()>=0){
			shopBusinessExample.setLimitStart(pager.getOffest());
			shopBusinessExample.setLimitEnd(pager.getPage()*pager.getSize());
		}
		shopBusinessExample.setOrderByClause("BATCH_NUMBER desc");
		List<ShopBusiness> list = shopBusinessMapper.selectTxBatchByExample(shopBusinessExample);

		Map m = new HashMap();
		total=1;
		m.put("total", total);
		m.put("list", list);
		return m;
	}
	public List<ShopBusiness> getShopBusinessByBatchID(String batchId) {
		
		List<ShopBusiness> list = shopBusinessMapper.selectByBatchId(batchId);
		
		return list;
	}
	
	/**
	 * 商户对账单
	 */
	public Map getSellerChkBillByMap(Pager pager, Map map) {
        CheckBusinessExample checkBusinessExample = new CheckBusinessExample();
        CheckBusinessExample.Criteria checkBusinessExampleCir = checkBusinessExample.createCriteria();
		
		String strDate = (String)map.get("strDate");
		String endDate = (String)map.get("endDate");
		String MERCHANT_CODE = (String)map.get("MERCHANT_CODE");
		String merchant_name = (String)map.get("merchant_name");
		String check_result = (String)map.get("check_result");
		String review_result = (String)map.get("review_result");
		if(MERCHANT_CODE !=null && !"".equals(MERCHANT_CODE)){
			checkBusinessExampleCir.andMERCHANT_CODELike("%"+MERCHANT_CODE+"%");	
		}
		if(merchant_name !=null && !"".equals(merchant_name)){
			checkBusinessExampleCir.andMERCHANT_NAMELike("%"+merchant_name+"%");	
		}
		if(check_result !=null && !"".equals(check_result)){
			checkBusinessExampleCir.andCHECK_RESULTLike("%"+check_result+"%");	
		}
		if(review_result !=null && !"".equals(review_result)){
			checkBusinessExampleCir.andREVIEW_RESULTLike("%"+review_result+"%");	
		}
		
		if(null!=strDate && null!=endDate && !"".equals(strDate) && !"".equals(endDate)){
			checkBusinessExample.setStrDate(strDate);
			checkBusinessExample.setEndDate(endDate);
	
		}
		
		int total = this.checkBusinessMapper.countByExample(checkBusinessExample);
		pager.setTotal(total);
		if(pager.getSize()>=0 && pager.getOffest()>=0){
			checkBusinessExample.setLimitStart(pager.getOffest());
			checkBusinessExample.setLimitEnd(pager.getPage()*pager.getSize());
		}
		checkBusinessExample.setOrderByClause("CREATE_TIME desc");
		List<CheckBusiness> list = this.checkBusinessMapper.selectByExample(checkBusinessExample);

		Map m = new HashMap();
		m.put("total", total);
		m.put("list", list);
		return m;
	}
	

	public Map getAccManageByMap(Pager pager, Map map) {
        CheckErrorExample checkErrorExample = new CheckErrorExample();
        CheckErrorExample.Criteria checkErrorExampleCir = checkErrorExample.createCriteria();
		String strDate = (String)map.get("strDate");
		String endDate = (String)map.get("endDate");
		String MERCHANT_CODE = (String)map.get("MERCHANT_CODE");
		String merchant_name = (String)map.get("merchant_name");
		String serial = (String)map.get("serial");
		String check_result = (String)map.get("check_result");
		String review_result = (String)map.get("review_result");
		if(MERCHANT_CODE !=null && !"".equals(MERCHANT_CODE)){
			checkErrorExampleCir.andMERCHANT_CODELike("%"+MERCHANT_CODE+"%");	
		}
		if(merchant_name !=null && !"".equals(merchant_name)){
			checkErrorExampleCir.andMERCHANT_NAMELike("%"+merchant_name+"%");	
		}
		if(check_result !=null && !"".equals(check_result)){
			checkErrorExampleCir.andCHECK_RESULTLike("%"+check_result+"%");	
		}
		if(review_result !=null && !"".equals(review_result)){
			checkErrorExampleCir.andREVIEW_RESULTLike("%"+review_result+"%");	
		}
		
		if(serial !=null && !"".equals(serial)){
			checkErrorExampleCir.andSERIALLike("%"+serial+"%");	
		}
		
		if(null!=strDate && null!=endDate && !"".equals(strDate) && !"".equals(endDate)){
			checkErrorExample.setStrDate(strDate);
			checkErrorExample.setEndDate(endDate);
	
		}
		
		int total = this.checkErrorMapper.countByExample(checkErrorExample);
		pager.setTotal(total);
		if(pager.getSize()>=0 && pager.getOffest()>=0){
			checkErrorExample.setLimitStart(pager.getOffest());
			checkErrorExample.setLimitEnd(pager.getPage()*pager.getSize());
		}
		checkErrorExample.setOrderByClause("create_time desc");
		List<CheckError> list = this.checkErrorMapper.selectByExample(checkErrorExample);

		Map m = new HashMap();
		m.put("total", total);
		m.put("list", list);
		return m;
	}
	public void updateCheckBusiness(Map map) {
		CheckBusinessExample checkBusinessExample = new CheckBusinessExample();
		CheckBusinessExample.Criteria checkBusinessExampleCir = checkBusinessExample.createCriteria();
		String create_time = (String)map.get("create_time");
		String MERCHANT_CODE = (String)map.get("MERCHANT_CODE");
		String check_result = (String)map.get("check_result");
		String review_result = (String)map.get("review_result");
		String reviewPass = (String)map.get("reviewPass");
		String reason = (String)map.get("reason");
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			try {
				Date sDate = sdf.parse(create_time);

				System.out.println("开始日期："+sDate);
				checkBusinessExampleCir.andCREATE_TIMEBetween(sDate, sDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			
			checkBusinessExampleCir.andMERCHANT_CODELike("%"+MERCHANT_CODE+"%");
			checkBusinessExampleCir.andCHECK_RESULTLike("%"+check_result+"%");
			checkBusinessExampleCir.andREVIEW_RESULTLike("%"+review_result+"%");
			
			CheckBusiness checkBusiness = new CheckBusiness();
			checkBusiness.setREVIEW_RESULT(reviewPass);
			//checkBusiness.setReason(reason);
			
			this.checkBusinessMapper.updateByExampleSelective(checkBusiness, checkBusinessExample);
		
	}
	public void updateCheckError(String id) {
		CheckError checkError = new CheckError();
		checkError.setID(id);
		checkError.setREVIEW_RESULT("01");
		this.checkErrorMapper.updateByPrimaryKeySelective(checkError);
		
	}
	public void updateShopBusinessByList(Map map) {
		ShopBusinessExample shopBusinessExample = new ShopBusinessExample();
		ShopBusiness shopBusiness = new ShopBusiness();
		//id数组
		String[] strId = (String[]) map.get("strId");
		//获取批次号
		String strBatch = this.shopBusinessMapper.randomMathFour();
		SimpleDateFormat sdf = new SimpleDateFormat("hhmmss");
		shopBusiness.setBATCH_NUMBER(sdf.format(new Date())+strBatch);
		
		List list = new ArrayList();
		for(String str : strId){
			list.add(str);
			
		}
		shopBusinessExample.createCriteria().andIDIn(list);
		
		this.shopBusinessMapper.updateByExampleSelective(shopBusiness, shopBusinessExample);
		
	}

}
