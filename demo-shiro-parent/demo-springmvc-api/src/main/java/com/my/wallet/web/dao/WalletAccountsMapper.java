package com.my.wallet.web.dao;

import com.my.wallet.web.domain.WalletAccounts;

/******************************************
 * 
 * 用户支付账户Mapper 
 * 提供业务与用户支付账户表之前的数据库SQL关系
 * 
 * @author HeTong
 * 
 ******************************************/

public interface WalletAccountsMapper {
	
	/**
	 * 通用方法,第一次进入钱包时，如果没有该用户的支付账户，那么就创建一个支付账户，插入一条记录
	 * @param record
	 * @return
	 */
	public int insert(WalletAccounts record);

	/**
	 * 通用方法,通过主键ID查询用户账户记录
	 * @param orderId
	 * @return
	 */
	public WalletAccounts selectByPrimaryKey(String id);

	/**
	 * 通用方法,更新钱包支付账户方法
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKeySelective(WalletAccounts record);
	
	/**
	 * 通过查询条件查询账户信息
	 * @param record
	 * @return
	 */
	public WalletAccounts queryWalletAccount(WalletAccounts record);
	
	
	/**
	 * 提现冻结金额方法
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKeyFrozenPersonalWithDraw(WalletAccounts record);
	
	/**
	 * 提现解冻冻结金额方法
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKeyUnfreezePersonalWithDraw(WalletAccounts record);
	
	
	
	/**
	 * 提现退换冻结金额方法
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKeyReturnFreezePersonalWithDraw(WalletAccounts record);
	
	/**
	  * <p>钱包账户转账转出</p>
	  * @param record
	  * @return
	  * @author lzf
	  * @date 2015年12月20日 下午4:51:37
	 */
	public int updateByPrimaryKeyTransferBalancetOut(WalletAccounts record);
	
	
	/**
	  * <p>钱包转账转入/充值</p>
	  * 本方法用法不需要先查选账户原金额然后加上交易金额，而是把交易金额直接传入用交易金额去+原账户金额，顾
	  * 本方法中的账号金额是指交易金额，交易金额是5就给 balance传入5就行了
	  * @param record
	  * @return
	  * @author lzf
	  * @date 2015年12月20日 下午5:06:51
	 */
	public int updateByPrimaryKeyTransferBalancetInto(WalletAccounts record);
	
	
	
	/**
	  * <p>余额付款 消费这里传人balance 参数 是指交易金额不是变化后的金额详细可以查看sql</p>
	  * @param record
	  * @return
	  * @author lzf
	  * @date 2015年12月27日 下午2:37:27
	 */
	public int updateBalenceByPrimaryKey(WalletAccounts record);
    
}