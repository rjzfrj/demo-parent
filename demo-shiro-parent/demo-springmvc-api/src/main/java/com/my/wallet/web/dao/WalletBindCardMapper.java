package com.my.wallet.web.dao;

import java.util.List;

import com.my.wallet.web.domain.WalletBindCard;

public interface WalletBindCardMapper {
	
	/**
	 * 通用方法,用户绑定银行卡处理，插入一条记录
	 * @param record
	 * @return
	 */
	public int insert(WalletBindCard record);

	/**
	 * 通用方法,通过主键ID查询记录
	 * @param orderId
	 * @return
	 */
	public WalletBindCard selectByPrimaryKey(String id);

	/**
	 * 通用方法,更新绑定状态
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKeySelective(WalletBindCard record);
	
	/**
	 * 通用方法,删除记录
	 * @param record
	 * @return
	 */
	public int deleteByPrimaryKey(String id);
	
	/**
	 * 通用方法 
	 * @param record
	 * @return
	 */
	public int deleteByPrimaryKeyAndMemberIdAndUserNo(WalletBindCard record);
	
	/**
	 * 查询用户绑定的银行卡列表
	 * @param record
	 * @return
	 */
	public List<WalletBindCard> queryBindCardList(WalletBindCard record);
	
	/**
	 * 通过扫码的数字码查询用户绑定的银行卡信息
	 * @param record
	 * @return
	 */
	public WalletBindCard queryBindCardByScanCode(String scanCode);
	
	/**
	 * 查询该卡号是否已经被绑定过
	 * @param record
	 * @return
	 */
	public List<WalletBindCard> checkCardNoList(WalletBindCard record);
	
	
	/**
	  * <p>通过卡号和会员编号，商户号，绑定状态校验银行卡是否是本人的有效借记卡卡号</p>
	  * @param record
	  * @return
	  * @author lzf
	  * @date 2015年12月17日 下午1:42:30
	 */
	public WalletBindCard checkCardValid(WalletBindCard record);
	
	
	/**
	 * @param 通过用户传来的绑卡id查询用户的绑卡信息但是要传入条件用户编号和商户号做校验保证是本人的卡
	 * @return
	 * @author lzf
	 * @date 2016年1月16日 下午14:56:30
	 */
	public WalletBindCard queryCardValid(WalletBindCard record);
	

    
}