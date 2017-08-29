package com.luo.dao;

import java.util.List;

import com.luo.domain.SSim;



public interface SSimDao {
	
    int deleteByPrimaryKey(String ccid);

    int insertSelective(SSim record);
    
    List<SSim> selectAllSSims();

}