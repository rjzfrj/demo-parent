package com.allscore.wireless.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.allscore.wireless.biz.ShopBusinessManager;
import com.allscore.wireless.dao.ShopBusiness;
import com.allscore.wireless.dao.ShopBusinessExample;
import com.allscore.wireless.dao.ShopBusinessMapper;

public class ShopBusinessManagerImpl implements ShopBusinessManager {
    
	
	@Autowired
	ShopBusinessMapper shopBusinessMapper;
	
	public void addShopBusiness(ShopBusiness shopBusiness) {
		//String serial = this.shopBusinessMapper.randomMathSix();
		//shopBusiness.setSERIAL(serial);
		
		this.shopBusinessMapper.insertSelective(shopBusiness);
	}
	
	 public List<ShopBusiness> loadShopBusinessByOutTranNO(String outTranNo) {

	     ShopBusinessExample shopBusinessExample = new ShopBusinessExample();
	     ShopBusinessExample.Criteria shopBusinessExampleCri = shopBusinessExample.createCriteria();
	     shopBusinessExampleCri.andSERIALEqualTo(outTranNo);
	     shopBusinessExampleCri.andBUSINESS_RESULTEqualTo("00");
	     return this.shopBusinessMapper.selectByExample(shopBusinessExample); 

	  }

	public void updateShopBusiness(ShopBusiness shopBusiness) {
		try{
			this.shopBusinessMapper.updateByPrimaryKeySelective(shopBusiness);
			
		}catch(Exception e){
			
			e.printStackTrace();
			
		}
		
		
	}

}
