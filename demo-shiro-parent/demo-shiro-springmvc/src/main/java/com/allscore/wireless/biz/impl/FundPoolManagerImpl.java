package com.allscore.wireless.biz.impl;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.allscore.wireless.biz.FundPoolManager;
import com.allscore.wireless.dao.FundPool;
import com.allscore.wireless.dao.FundPoolExample;
import com.allscore.wireless.dao.FundPoolMapper;
import com.allscore.wireless.dao.ShopBusiness;
import com.allscore.wireless.dao.ShopBusinessMapper;
import com.allscore.wireless.dao.UsersBussiness;
import com.allscore.wireless.dao.UsersBussinessExample;
import com.allscore.wireless.dao.UsersBussinessMapper;


public class FundPoolManagerImpl implements FundPoolManager {
	
	private static final Logger logger = Logger.getLogger(FundPoolManagerImpl.class);
	
	@Autowired
	FundPoolMapper fundPoolMapper;
	
	@Autowired
	UsersBussinessMapper usersBussinessMapper;
	
	@Autowired
	ShopBusinessMapper shopBusinessMapper;
     
	/**
	 * 资金池金额结算
	 * T-1日交易金额数据写入商户资金池表
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly = false, rollbackFor = RuntimeException.class)
	public void updateFundPoolByBusinessTime(String businessTime) {
		
		UsersBussinessExample usersBussinessExample = new UsersBussinessExample();
		//UsersBussinessExample.Criteria usersBussinessExampleCir = usersBussinessExample.createCriteria();
		usersBussinessExample.setBusinessTime(businessTime);
		//获取T-1交易日数据
		List<UsersBussiness> listUsersBussiness = this.usersBussinessMapper.selectBusinessByExample(usersBussinessExample);
		
		for(UsersBussiness usersBussiness:listUsersBussiness){
			//商户编号
			String merchant_code = usersBussiness.getMERCHANT_CODE();
			//商户名称
			String merchant_name = usersBussiness.getMERCHANT_NAME();
			//T-1日交易总金额
			BigDecimal tran_amt = usersBussiness.getSumTranAmt();
			BigDecimal poundage = usersBussiness.getSumPoundage();
			BigDecimal accounts = tran_amt.subtract(poundage);
			FundPoolExample fundPoolExample = new FundPoolExample();
			FundPoolExample.Criteria fundPoolExampleCir = fundPoolExample.createCriteria();
			fundPoolExampleCir.andMERCHANT_CODEEqualTo(merchant_code);
			//获取商户资金池信息
			List<FundPool> listFundPool = this.fundPoolMapper.selectByExample(fundPoolExample);
			FundPool fundPool = new FundPool();
			//商户存在资金池，将T-1日交易数据写入该商户资金池
			if(listFundPool!=null && listFundPool.size()>0){
				
				fundPool.setCURR_MOENY(listFundPool.get(0).getCURR_MOENY().add(accounts));
				//fundPool.setVALID_MOENY(listFundPool.get(0).getVALID_MOENY().add(accounts));
				this.fundPoolMapper.updateByExampleSelective(fundPool, fundPoolExample);
			}else{
				//创建商户资金池，并写入T-1日交易数据
				fundPool.setID(UUID.randomUUID().toString());
				fundPool.setMERCHANT_CODE(merchant_code);
				fundPool.setCURR_MOENY(accounts);
				//fundPool.setVALID_MOENY(accounts);
				fundPool.setFROZEN_MOENY(new BigDecimal("0"));
				fundPool.setCREATE_TIME(new Date());
				this.fundPoolMapper.insertSelective(fundPool);
			}
			
			usersBussiness.setSTATUS("01");
			usersBussiness.setBUSINESS_TIME(businessTime);
			this.usersBussinessMapper.updateByBusinessTime(usersBussiness);
			
		}
		
	}
    
	/**
	 * 将T-1日消费充值信息写入商户交易表
	 */
	public int addShopBusinessByBusinessTime(String businessTime) {
		
		UsersBussinessExample usersBussinessExample = new UsersBussinessExample();
		UsersBussinessExample.Criteria usersBussinessExampleCir = usersBussinessExample.createCriteria();
		usersBussinessExampleCir.andBUSINESS_SOURCEEqualTo("00");
		usersBussinessExample.setBusinessTime(businessTime);
		//获取T-1交易日数据
		List<UsersBussiness> listUsersBussiness = this.usersBussinessMapper.selectXfBusinessByExample(usersBussinessExample);
		int cnt = 0;
		for(UsersBussiness usersBussiness:listUsersBussiness){
			//商户编号
			String merchant_code = usersBussiness.getMERCHANT_CODE();
			//商户名称
			String merchant_name = usersBussiness.getMERCHANT_NAME();
			
			//T-1日交易金额
			int k = 0;
			ShopBusiness shopBusiness = new ShopBusiness();
			shopBusiness.setID(UUID.randomUUID().toString());
			shopBusiness.setMERCHANT_CODE(merchant_code);
			shopBusiness.setMERCHANT_NAME(merchant_name);
			shopBusiness.setBUSINESS_TYPE("02");
			shopBusiness.setORDER_AMT(usersBussiness.getORDER_AMT());
			shopBusiness.setTRAN_AMT(usersBussiness.getTRAN_AMT());
		    shopBusiness.setPOUNDAGE(usersBussiness.getPOUNDAGE());
		    shopBusiness.setBUSINESS_RESULT(usersBussiness.getBUSINESS_RESULT());
		    shopBusiness.setCREATE_TIME(new Date());
		    //if(usersBussiness.getBUSINESS_TIME() !=null && !"".equals(usersBussiness.getBUSINESS_TIME())){
		    	System.out.println(usersBussiness.getBUSINESS_TIME());
		    	shopBusiness.setBUSINESS_TIME(usersBussiness.getBUSINESS_TIME());
		   // }
		    shopBusiness.setSERIAL(usersBussiness.getID());
		    k = this.shopBusinessMapper.insertSelective(shopBusiness);
		    cnt +=k;
		}
		return cnt;
	}
	
	
}
