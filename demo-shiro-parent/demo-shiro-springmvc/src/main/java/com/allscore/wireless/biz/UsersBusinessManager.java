package com.allscore.wireless.biz;

import java.util.List;

import com.allscore.wireless.dao.UsersBussiness;

public interface UsersBusinessManager {
	
	List<UsersBussiness>  getUsersBussinessType(String date);
	
	List<UsersBussiness>  getUsersBussiness(String date);
   
}
