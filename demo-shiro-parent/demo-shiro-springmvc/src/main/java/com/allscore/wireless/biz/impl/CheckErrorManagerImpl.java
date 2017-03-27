package com.allscore.wireless.biz.impl;


import org.springframework.beans.factory.annotation.Autowired;

import com.allscore.wireless.biz.CheckErrorManager;
import com.allscore.wireless.dao.CheckError;
import com.allscore.wireless.dao.CheckErrorMapper;

public class CheckErrorManagerImpl implements CheckErrorManager {
	
	@Autowired
	CheckErrorMapper checkErrorMapper;

	public void addCheckErrorByList(CheckError checkError) {

	  this.checkErrorMapper.insertSelective(checkError);

	}

}
