package com.allscore.wireless.biz.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.allscore.wireless.biz.UserBaseManager;
import com.allscore.wireless.dao.Menu;
import com.allscore.wireless.dao.MenuExample;
import com.allscore.wireless.dao.MenuMapper;
import com.allscore.wireless.dao.UserBase;
import com.allscore.wireless.dao.UserBaseExample;
import com.allscore.wireless.dao.UserBaseMapper;


public class UserBaseManagerImpl implements UserBaseManager {
	
	@Autowired
	UserBaseMapper userBaseMapper;
	
	@Autowired
	MenuMapper menuMapper;
	
	private static final Logger  logger = Logger.getLogger(UserBaseManagerImpl.class);

	public UserBase getUserBaseByLoginName(String loginName,String pwd) {
		UserBaseExample userBaseExample = new UserBaseExample();
		userBaseExample.setLoginName(loginName);
		if(pwd!=null && !"".equals(pwd))
		{
			userBaseExample.setLoginPwd(pwd);	
		}
        
			List<UserBase> list =  this.userBaseMapper.selectByExample(userBaseExample);
			logger.info("返回权限数："+list.size());
			if(list!=null && list.size()>0){
				UserBase userBase = new UserBase();
				userBase.setID(list.get(0).getID());
				userBase.setLOGIN_NAME(list.get(0).getLOGIN_NAME());
				userBase.setLOGIN_PWD(list.get(0).getLOGIN_PWD());
				userBase.setSTATUS(list.get(0).getSTATUS());
				userBase.setMOBILE(list.get(0).getMOBILE());
				userBase.setEMAIL(list.get(0).getEMAIL());
				userBase.setNAME(list.get(0).getNAME());
				userBase.setROLE_ID(list.get(0).getROLE_ID());
				userBase.setMERCHANT_CODE(list.get(0).getMERCHANT_CODE());
				
				//获取该用户下的所有菜单
				MenuExample menuExample = new MenuExample();
				MenuExample.Criteria menuExampleCir = menuExample.createCriteria();
				menuExampleCir.andROLE_IDEqualTo(list.get(0).getROLE_ID());
				menuExampleCir.andLOGIN_NAMEEqualTo(list.get(0).getLOGIN_NAME());
				List<Menu> listMenu = this.menuMapper.selectByExample(menuExample);
				
				userBase.setListMenu(listMenu);
				
				return userBase;
			}else{
				
				return null;
			}
			

	}
	
	public UserBase getUserBaseInfo(String loginName, String pwd) {
		UserBaseExample userBaseExample = new UserBaseExample();
		userBaseExample.setLoginName(loginName);
		if(pwd!=null && !"".equals(pwd))
		{
			userBaseExample.setLoginPwd(pwd);	
		}
        
	    List<UserBase> list =  this.userBaseMapper.selectUserBaseByExample(userBaseExample);
	    if(list!=null && list.size()>0){
	    	return list.get(0);
	    	
	    }
		return null;
	}
	
}
