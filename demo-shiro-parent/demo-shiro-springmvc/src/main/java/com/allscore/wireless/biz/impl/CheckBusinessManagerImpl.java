package com.allscore.wireless.biz.impl;


import org.springframework.beans.factory.annotation.Autowired;

import com.allscore.wireless.biz.CheckBusinessManager;
import com.allscore.wireless.dao.CheckBusiness;
import com.allscore.wireless.dao.CheckBusinessMapper;

public class CheckBusinessManagerImpl implements CheckBusinessManager {
	
	@Autowired
	CheckBusinessMapper checkBusinessMapper;

	public void addCheckBusinessByList(CheckBusiness checkBusiness) {

	  this.checkBusinessMapper.insertSelective(checkBusiness);

	}

}
