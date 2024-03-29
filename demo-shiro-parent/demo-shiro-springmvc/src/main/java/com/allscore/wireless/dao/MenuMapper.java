package com.allscore.wireless.dao;

import com.allscore.wireless.dao.Menu;
import com.allscore.wireless.dao.MenuExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository(value="menuMapper")
public interface MenuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table WX_MENU
     *
     * @mbggenerated Thu Dec 24 13:54:25 CST 2015
     */
    int countByExample(MenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table WX_MENU
     *
     * @mbggenerated Thu Dec 24 13:54:25 CST 2015
     */
    int deleteByExample(MenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table WX_MENU
     *
     * @mbggenerated Thu Dec 24 13:54:25 CST 2015
     */
    int insert(Menu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table WX_MENU
     *
     * @mbggenerated Thu Dec 24 13:54:25 CST 2015
     */
    int insertSelective(Menu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table WX_MENU
     *
     * @mbggenerated Thu Dec 24 13:54:25 CST 2015
     */
    List<Menu> selectByExample(MenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table WX_MENU
     *
     * @mbggenerated Thu Dec 24 13:54:25 CST 2015
     */
    int updateByExampleSelective(@Param("record") Menu record, @Param("example") MenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table WX_MENU
     *
     * @mbggenerated Thu Dec 24 13:54:25 CST 2015
     */
    int updateByExample(@Param("record") Menu record, @Param("example") MenuExample example);
}