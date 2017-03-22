package com.my.wallet.web.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.my.wallet.web.domain.bean.TranSumInfo;

public interface TranSumInfoMapper {
    /**
     * 根据条件查询订单记录
     * @return
     */
    public List<TranSumInfo> queryPayOrderSumByMonth(TranSumInfo tranSumInfo, RowBounds rowBounds);
    
}