package com.allscore.wireless.biz;

import java.util.Map;

import com.allscore.wireless.common.Pager;
import com.allscore.wireless.dao.UserBase;


public interface SysManager {
	
    Map getUserBaseByMap(Pager pager, Map map);
    
    int addUserBaseByMap(Map map);
    
    UserBase getUserBaseById(String id);
    
    void updateUserBaseById(Map map);
    
    void delUserBaseById(String id);
    
    void updateUserBaseById(String id);
    
    Map getRoleBaseByMap(Pager pager, Map map);
    
    int addRoleBaseByMap(Map map);
    
    int updateUserPwdbyMap(Map map);
    
    int addRoleMenuByMap(Map map,String id);
    
    void delMenuById(String id,String role_id);
    
    boolean verifyLoginName(String loginName);
    
    boolean verifyMerchantId(String merchant_code);
    
    boolean verifyLoginPwd(String loginName,String loginPwd);
    
    String getRoleId();
    
    int getUserCountByRoleId(String role_id);

    
}
