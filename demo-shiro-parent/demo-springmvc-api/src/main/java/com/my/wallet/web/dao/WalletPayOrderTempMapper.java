package com.my.wallet.web.dao;

import java.util.List;

import com.my.wallet.web.domain.WalletPayOrderTemp;

public interface WalletPayOrderTempMapper {

	/**
	 * 通用方法,插入交易订单
	 * 
	 * @param record
	 * @return
	 */
	public int insert(WalletPayOrderTemp record);

	/**
	 * 通用方法,通过订单系统订单号查询交易记录
	 * 
	 * @param orderId
	 * @return
	 */
	public WalletPayOrderTemp selectByPrimaryKey(String orderId);

	/**
	 * 通用方法,更新支付状态
	 * 
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKeySelective(WalletPayOrderTemp record);

	/**
	 * 根据条件查询订单记录
	 * 
	 * @return
	 */
	public List<WalletPayOrderTemp> queryPayOrderTempByCondition(
			WalletPayOrderTemp payOrder);

}