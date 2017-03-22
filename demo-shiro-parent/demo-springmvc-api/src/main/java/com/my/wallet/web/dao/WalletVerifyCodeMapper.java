package com.my.wallet.web.dao;

import com.my.wallet.web.domain.WalletAccounts;
import com.my.wallet.web.domain.WalletVerifyCode;

/******************************************
 * 
 * 用户短信验证码Mapper 
 * 提供业务与用户短信验证码表之前的数据库SQL关系
 * 
 * @author HeTong
 * 
 ******************************************/

public interface WalletVerifyCodeMapper {
	
	/**
	 * 通用方法,插入一条短信验证码信息
	 * @param record
	 * @return
	 */
	public int insert(WalletVerifyCode record);

	/**
	 * 通用方法,通过主键ID查询用户短信验证码相关内容
	 * @param orderId
	 * @return
	 */
	public WalletVerifyCode selectByPrimaryKey(String id);

	/**
	 * 通用方法,更新短信验证码表数据
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKeySelective(WalletVerifyCode record);
	
	/**
	 * 通过查询条件查询用户有效短信验证码
	 * @param record
	 * @return
	 */
	public WalletVerifyCode selectVerifyCode(WalletVerifyCode record);
    
}