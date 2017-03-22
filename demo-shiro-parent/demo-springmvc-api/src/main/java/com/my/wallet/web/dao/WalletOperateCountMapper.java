package com.my.wallet.web.dao;

import com.my.wallet.web.domain.WalletOperateCount;

/******************************************
 * 
 * 操作计数Mapper 
 * 提供业务与用户支付账户表之前的数据库SQL关系
 * 
 * @author HeTong
 * 
 ******************************************/

public interface WalletOperateCountMapper {
	
	/**
	 * 通用方法,插入一条记录
	 * @param record
	 * @return
	 */
	public int insert(WalletOperateCount record);

	/**
	 * 通用方法,通过主键ID查询记录
	 * @param orderId
	 * @return
	 */
	public WalletOperateCount selectByPrimaryKey(String id);

	/**
	 * 通用方法,更新方法
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKeySelective(WalletOperateCount record);
	
	/**
	 * 通过查询条件查询账户信息
	 * @param record
	 * @return
	 */
	public WalletOperateCount queryOperateCount(WalletOperateCount record);
    
}