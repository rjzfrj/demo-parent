package com.my.wallet.web.dao;

import com.my.wallet.web.domain.WalletScanCodes;

/******************************************
 * 
 * 用户支付账户Mapper 
 * 提供业务与用户支付账户表之前的数据库SQL关系
 * 
 * @author HeTong
 * 
 ******************************************/

public interface WalletScanCodesMapper {
	
	/**
	 * 通用方法,第一次进入钱包时，如果没有该用户的支付账户，那么就创建一个支付账户，插入一条记录
	 * @param record
	 * @return
	 */
	public int insert(WalletScanCodes record);

	/**
	 * 通用方法,通过主键ID查询用户账户记录
	 * @param orderId
	 * @return
	 */
	public WalletScanCodes selectByPrimaryKey(String id);

	/**
	 * 通用方法,更新钱包支付账户方法
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKeySelective(WalletScanCodes record);
	
	/**
	 * 通过条件删除记录
	 * @param record
	 * @return
	 */
	public int deleteByConditions(WalletScanCodes record);
	
	/**
	 * 通过条形码查找记录
	 * @param record
	 * @return
	 */
	public WalletScanCodes queryByConditions(WalletScanCodes record);
    
}