package com.my.wallet.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.my.wallet.web.domain.WalletPayOrder;

public interface WalletPayOrderMapper {
	
	/**
	 * 通用方法,插入交易订单
	 * @param record
	 * @return
	 */
	public int insert(WalletPayOrder record);

	/**
	 * 通用方法,通过订单系统订单号查询交易记录
	 * @param orderId
	 * @return
	 */
	public WalletPayOrder selectByPrimaryKey(String orderId);

	/**
	 * 通用方法
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKeySelective(WalletPayOrder record);
	
	/**
	 * 只用于修改订单状态 
	 * @param record
	 * @return
	 */
	public int updateTranStatus(WalletPayOrder record);
	
	/**
	 * 查询所有未成功发送通知的记录
	 * @return
	 */
	public List<WalletPayOrder> queryWalletPayResultNotify();

    /**
     * 根据条件查询订单记录
     * @return
     */
	public List<WalletPayOrder> queryWalletPayOrderByCondition(WalletPayOrder payOrder,RowBounds rowBounds);
    
	
	 /**
     * 根据tranNo查询订单
     * @return
     */
	public WalletPayOrder queryWalletPayOrderByTranNo(String tranNo);
	
	
	
	/**
	  * <p>更具商户号，用户账号，外部订单号查询订单详情</p>
	  * @param outOrderId 外部订单号
	  * @param merchantId 商户编号
	  * @param userNo 	     用户账号
	  * @return
	  * @author lzf
	  * @date 2015年12月24日 下午1:43:36
	 */
	public WalletPayOrder selectOrderDetialByOMU(Map map);
	
	
	/**
	  * <p>查询订单信息</p>
	  * @param map
	  * @param rowBounds
	  * @return
	  * @author lzf
	  * @date 2016年1月8日 下午1:46:53
	 */
	public List<Map> selectOrders(Map map,RowBounds rowBounds);
	
	/**
	  * <p>返回一共多少条记录</p>
	  * @param map
	  * @return
	  * @author lzf
	  * @date 2016年1月8日 下午1:46:30
	 */
	public int selectOrdersCount(Map map);
	
	/**
	  * <p>Description:获取订单编号</p>
	  * @return
	  * @author lzf
	  * @date 2016年2月26日 下午3:30:09
	 */
	public String getOrderId();
}