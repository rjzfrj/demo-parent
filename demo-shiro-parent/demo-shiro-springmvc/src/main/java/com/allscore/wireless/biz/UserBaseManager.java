package com.allscore.wireless.biz;

import java.util.List;

import com.allscore.wireless.dao.UserBase;


public interface UserBaseManager {
	UserBase getUserBaseByLoginName(String loginName,String loginPwd);
	
	UserBase getUserBaseInfo(String loginName,String pwd);
}
