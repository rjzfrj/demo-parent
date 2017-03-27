package com.allscore.wireless.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.allscore.wireless.biz.RoleManager;
import com.allscore.wireless.dao.RoleManage;
import com.allscore.wireless.dao.RoleManageMapper;

public class RoleManagerImpl implements RoleManager {
    
	@Autowired
	RoleManageMapper roleManageMapper;
	public List<RoleManage> getRoleName() {
		
		return this.roleManageMapper.selectRoleName();
	}

}
