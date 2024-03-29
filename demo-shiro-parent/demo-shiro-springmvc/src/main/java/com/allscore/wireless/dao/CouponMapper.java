package com.allscore.wireless.dao;

import com.allscore.wireless.dao.Coupon;
import com.allscore.wireless.dao.CouponExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CouponMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table WX_COUPON
     *
     * @mbggenerated Fri Nov 27 15:12:26 CST 2015
     */
    int countByExample(CouponExample example);
    
    List<Coupon> couponSumByExample(CouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table WX_COUPON
     *
     * @mbggenerated Fri Nov 27 15:12:26 CST 2015
     */
    int deleteByExample(CouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table WX_COUPON
     *
     * @mbggenerated Fri Nov 27 15:12:26 CST 2015
     */
    int deleteByPrimaryKey(String ID);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table WX_COUPON
     *
     * @mbggenerated Fri Nov 27 15:12:26 CST 2015
     */
    int insert(Coupon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table WX_COUPON
     *
     * @mbggenerated Fri Nov 27 15:12:26 CST 2015
     */
    int insertSelective(Coupon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table WX_COUPON
     *
     * @mbggenerated Fri Nov 27 15:12:26 CST 2015
     */
    List<Coupon> selectByExample(CouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table WX_COUPON
     *
     * @mbggenerated Fri Nov 27 15:12:26 CST 2015
     */
    Coupon selectByPrimaryKey(String ID);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table WX_COUPON
     *
     * @mbggenerated Fri Nov 27 15:12:26 CST 2015
     */
    int updateByExampleSelective(@Param("record") Coupon record, @Param("example") CouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table WX_COUPON
     *
     * @mbggenerated Fri Nov 27 15:12:26 CST 2015
     */
    int updateByExample(@Param("record") Coupon record, @Param("example") CouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table WX_COUPON
     *
     * @mbggenerated Fri Nov 27 15:12:26 CST 2015
     */
    int updateByPrimaryKeySelective(Coupon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table WX_COUPON
     *
     * @mbggenerated Fri Nov 27 15:12:26 CST 2015
     */
    int updateByPrimaryKey(Coupon record);
}