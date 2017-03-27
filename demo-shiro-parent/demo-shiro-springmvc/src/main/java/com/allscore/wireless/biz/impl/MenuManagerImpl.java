package com.allscore.wireless.biz.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.allscore.wireless.biz.MenuManager;
import com.allscore.wireless.dao.MenuExample;
import com.allscore.wireless.dao.MenuMapper;

public class MenuManagerImpl implements MenuManager {
    
	@Autowired
	MenuMapper menuMapper;
	
	public List getMenuByLoginName(String loginName) {
		MenuExample menuExample = new MenuExample();
		MenuExample.Criteria menuExampleCir = menuExample.createCriteria();
		menuExampleCir.andLOGIN_NAMEEqualTo(loginName);
		
		List list = this.menuMapper.selectByExample(menuExample);
		
		return list;
	}

}
