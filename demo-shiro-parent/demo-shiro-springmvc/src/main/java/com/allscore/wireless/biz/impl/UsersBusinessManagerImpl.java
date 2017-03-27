package com.allscore.wireless.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.allscore.wireless.biz.UsersBusinessManager;
import com.allscore.wireless.dao.UsersBussiness;
import com.allscore.wireless.dao.UsersBussinessExample;
import com.allscore.wireless.dao.UsersBussinessMapper;


public class UsersBusinessManagerImpl implements UsersBusinessManager {
	
	@Autowired
	UsersBussinessMapper usersBussinessMapper;
	
	

	public List<UsersBussiness> getUsersBussinessType(String date) {
		UsersBussinessExample usersBussinessExample = new UsersBussinessExample();
		UsersBussinessExample.Criteria usersBussinessExampleCir  = usersBussinessExample.createCriteria();
		usersBussinessExample.setBusinessTime(date);
		usersBussinessExampleCir.andBUSINESS_TYPEEqualTo("00");
		
		List<UsersBussiness> listUsersBussiness =  this.usersBussinessMapper.selectBusinessByExampleType(usersBussinessExample);
		
		// TODO Auto-generated method stub
		return listUsersBussiness;
	}
	
	/**
	 * 获取每日消费总金额信息
	 */
	public List<UsersBussiness> getUsersBussiness(String date) {
		UsersBussinessExample usersBussinessExample = new UsersBussinessExample();
		UsersBussinessExample.Criteria usersBussinessExampleCir  = usersBussinessExample.createCriteria();
		usersBussinessExample.setBusinessTime(date);
		
		List<UsersBussiness> listUsersBussiness =  this.usersBussinessMapper.selectBusinessByExample(usersBussinessExample);
		
		// TODO Auto-generated method stub
		return listUsersBussiness;
	}



}
