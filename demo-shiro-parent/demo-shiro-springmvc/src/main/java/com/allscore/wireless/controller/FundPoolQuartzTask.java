package com.allscore.wireless.controller;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.allscore.wireless.biz.FundPoolManager;
import com.allscore.wireless.biz.UsersBusinessManager;


public class FundPoolQuartzTask {
	private static final Logger  logger = Logger.getLogger(FundPoolQuartzTask.class);
	
	@Autowired
	UsersBusinessManager usersBusinessManager;

	@Autowired
	FundPoolManager fundPoolManager;
	
	public void execute() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date date = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String businessTime = sdf.format(date);
		try{
			//资金池金额结算
			this.fundPoolManager.updateFundPoolByBusinessTime(businessTime);
		}catch(Exception e){
			e.printStackTrace();
			logger.info("T-1日资金池金额结算失败");
		}
		
		try{
			//T-1日消费记录总额写入商户交易表
			int cnt = this.fundPoolManager.addShopBusinessByBusinessTime(businessTime);
		}catch(Exception e){
			e.printStackTrace();
			logger.info("T-1日消费记录总额写入商户交易表失败");
		}
    }



}
