package com.allscore.wireless.biz.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.allscore.wireless.biz.SysManager;
import com.allscore.wireless.common.DateUtil;
import com.allscore.wireless.common.Pager;
import com.allscore.wireless.dao.MemberPublicExample;
import com.allscore.wireless.dao.MemberPublicMapper;
import com.allscore.wireless.dao.Menu;
import com.allscore.wireless.dao.MenuExample;
import com.allscore.wireless.dao.MenuMapper;
import com.allscore.wireless.dao.RoleManage;
import com.allscore.wireless.dao.RoleManageExample;
import com.allscore.wireless.dao.RoleManageMapper;
import com.allscore.wireless.dao.UserBase;
import com.allscore.wireless.dao.UserBaseExample;
import com.allscore.wireless.dao.UserBaseMapper;


public class SysManagerImpl implements SysManager {
	
	private static final Logger logger = Logger.getLogger(SysManagerImpl.class);
	
	@Autowired
	UserBaseMapper userBaseMapper;
	
	@Autowired
	RoleManageMapper roleManageMapper;
	
	@Autowired
	MenuMapper menuMapper;
	
	@Autowired
	MemberPublicMapper memberPublicMapper;
	
	/**
	 * 获取人员信息列表
	 */
	public Map getUserBaseByMap(Pager pager, Map map) {
		UserBaseExample userBaseExample = new UserBaseExample();
		//userBaseExample.or().andSTATUSLike("%00%");
		
		int total = this.userBaseMapper.countByExample(userBaseExample);
		
		List<UserBase> list = this.userBaseMapper.selectUserBaseByExample(userBaseExample);
		
		Map m = new HashMap();
		m.put("total", total);
		m.put("list", list);
		return m;
	}
	
	/**
	 * 获取角色ID关联人员数
	 */
	public int getUserCountByRoleId(String role_id) {
		UserBaseExample userBaseExample = new UserBaseExample();
		UserBaseExample.Criteria userBaseExampleCir  =userBaseExample.createCriteria();
		userBaseExampleCir.andROLE_IDEqualTo(role_id);
 
		return this.userBaseMapper.countByExample(userBaseExample);
	}
    
	/**
	 * 获取角色信息列表
	 */
	public Map getRoleBaseByMap(Pager pager, Map map) {
		
		RoleManageExample roleManageExample = new RoleManageExample();
		RoleManageExample.Criteria roleManageExampleCir = roleManageExample.createCriteria();
		
		int total = this.roleManageMapper.countByExample(roleManageExample);
		List<RoleManage> list = this.roleManageMapper.selectByExample(roleManageExample);
		
		Map m = new HashMap();
		m.put("total", total);
		m.put("list", list);
		return m;
	}
    
	/**
	 * 新增人员信息
	 */
	public int addUserBaseByMap(Map map) {
	    String name = (String) map.get("name");
	    String login_name = (String) map.get("login_name");
	    String login_pwd = (String) map.get("login_pwd");
	    String mobile = (String) map.get("mobile");
	    String role_id = (String) map.get("role_id");
	    String merchant_code = (String) map.get("merchant_code");
	    
	    UserBase userBase = new UserBase();
	    userBase.setID(UUID.randomUUID().toString());
	    userBase.setCREATE_TIME(DateUtil.getFormatDate());
	    userBase.setSTATUS("01");
	    if(name!=null && !"".equals(name)){
	    	userBase.setNAME(name);
	    }
	    if(login_name!=null && !"".equals(login_name)){
	    	userBase.setLOGIN_NAME(login_name);
	    }
	    if(login_pwd!=null && !"".equals(login_pwd)){
	    	userBase.setLOGIN_PWD(login_pwd);
	    }
	    if(mobile!=null && !"".equals(mobile)){
	    	userBase.setMOBILE(mobile);
	    }
	    if(role_id!=null && !"".equals(role_id)){
	    	userBase.setROLE_ID(role_id);
	    }
	    if(merchant_code!=null && !"".equals(merchant_code)){
	    	userBase.setMERCHANT_CODE(merchant_code);
	    }
	    
	   return this.userBaseMapper.insertSelective(userBase);
	}
    
	/**
	 * 查询人员信息
	 */
	public UserBase getUserBaseById(String id) {
		UserBase userBase= this.userBaseMapper.selectByPrimaryKey(id);
		return userBase;
	}
    
	/**
	 * 修改人员信息
	 */
	public void updateUserBaseById(Map map) {
		String id = (String) map.get("id");
		String name = (String) map.get("name");
	    String login_name = (String) map.get("login_name");
	    String login_pwd = (String) map.get("login_pwd");
	    String mobile = (String) map.get("mobile");
	    String role_id = (String) map.get("role_id");
	    
	    UserBase userBase = new UserBase();
	    userBase.setID(id);
	    userBase.setUPDATE_TIME(DateUtil.getFormatDate());
	    //userBase.setSTATUS("00");
	    if(name!=null && !"".equals(name)){
	    	userBase.setNAME(name);
	    }
	    if(login_name!=null && !"".equals(login_name)){
	    	userBase.setLOGIN_NAME(login_name);
	    }
	    if(login_pwd!=null && !"".equals(login_pwd)){
	    	userBase.setLOGIN_PWD(login_pwd);
	    }
	    if(mobile!=null && !"".equals(mobile)){
	    	userBase.setMOBILE(mobile);
	    }
	    if(role_id!=null && !"".equals(role_id)){
	    	userBase.setROLE_ID(role_id);
	    }
	    
	    this.userBaseMapper.updateByPrimaryKeySelective(userBase);
		
	}
    
	/**
	 * 删除人员信息
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly = false, rollbackFor = RuntimeException.class)
	public void delUserBaseById(String id) {

		UserBase userBase = this.userBaseMapper.selectByPrimaryKey(id);
        String loginName = userBase.getLOGIN_NAME();
        
        MenuExample menuExample = new MenuExample();
        MenuExample.Criteria menuExampleCir = menuExample.createCriteria();
        menuExampleCir.andLOGIN_NAMEEqualTo(loginName);
        
        this.menuMapper.deleteByExample(menuExample);
        
		
		this.userBaseMapper.deleteByPrimaryKey(id);
		
	}
    
	
	/**
	 * 重置密码
	 */
	public void updateUserBaseById(String id) {

	    
	    UserBase userBase = new UserBase();
	    userBase.setID(id);
	    userBase.setUPDATE_TIME(DateUtil.getFormatDate());
	    userBase.setLOGIN_PWD("lueSGJZetyySpUndWjMBEg==");
	    this.userBaseMapper.updateByPrimaryKeySelective(userBase);
		
	}
    
	/**
	 * 新增角色
	 */
	public int addRoleBaseByMap(Map map) {
	    String role_id = (String) map.get("role_id");
	    String role_name = (String) map.get("role_name");
	    RoleManageExample roleManageExample = new RoleManageExample();
	    RoleManageExample.Criteria roleManageExampleCir = roleManageExample.createCriteria();
	    roleManageExample.or().andROLE_IDEqualTo(role_id);
	    roleManageExample.or().andROLE_NAMEEqualTo(role_name);
	    int cnt = this.roleManageMapper.countByExample(roleManageExample);
	    if(cnt>0){
	    	return cnt;
	    }else{
	    	RoleManage roleManage = new RoleManage();
		    roleManage.setID(UUID.randomUUID().toString());
		    roleManage.setCREATE_TIME(new Date());
		    if(role_id!=null && !"".equals(role_id)){
		    	roleManage.setROLE_ID(role_id);
		    }
		    if(role_name!=null && !"".equals(role_name)){
		    	roleManage.setROLE_NAME(role_name);	
		    }
		     this.roleManageMapper.insertSelective(roleManage);
		     return 0;
	    }
	}
    
	/**
	 * 修改用户密码
	 */
	public int updateUserPwdbyMap(Map map) {
		String login_name = (String) map.get("login_name");
		String login_pwd = (String)map.get("login_pwd");
		String login_newpwd = (String)map.get("login_newpwd");
		UserBaseExample userBaseExample = new UserBaseExample();
		UserBaseExample.Criteria userBaseExampleCir = userBaseExample.createCriteria();
		if(!"".equals(login_name) && login_name!=null){
			userBaseExampleCir.andLOGIN_NAMEEqualTo(login_name);
		}
		if(!"".equals(login_pwd) && login_pwd!=null){
			userBaseExampleCir.andLOGIN_PWDEqualTo(login_pwd);
		}
		UserBase userBase = new UserBase();
		userBase.setLOGIN_PWD(login_newpwd);
		int count = this.userBaseMapper.updateByExampleSelective(userBase, userBaseExample);
		return count;
	}
    
	/**
	 * 为用户分配权限
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly = false, rollbackFor = RuntimeException.class)
	public int addRoleMenuByMap(Map map,String id) {
		//用户基本信息表主键
		UserBase userBase = this.userBaseMapper.selectByPrimaryKey(id);
		Iterator it = map.keySet().iterator();
		int i=0;
		String loginName = userBase.getLOGIN_NAME();
		userBase.setCREATE_TIME(null);
	    userBase.setUPDATE_TIME(DateUtil.getFormatDate());
	    
	    userBase.setSTATUS("00");
	    this.userBaseMapper.updateByPrimaryKeySelective(userBase);
        
        MenuExample menuExample = new MenuExample();
        MenuExample.Criteria menuExampleCir = menuExample.createCriteria();
        menuExampleCir.andLOGIN_NAMEEqualTo(loginName);
        
        this.menuMapper.deleteByExample(menuExample);
		while(it.hasNext()){
			
			String key = (String) it.next();
			String value = (String)map.get(key);
			if(value!=null && !"".equals(value)){
				Menu menu = new Menu();
				menu.setID(UUID.randomUUID().toString());
				menu.setLOGIN_NAME(userBase.getLOGIN_NAME());
				menu.setROLE_ID(userBase.getROLE_ID());
				menu.setMENU_NAME(value);
				menu.setCREATE_TIME(new Date());
				this.menuMapper.insertSelective(menu);
				i++;	
			}
			
		}
        return i;
	}
    
	/**
	 * 删除角色
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly = false, rollbackFor = RuntimeException.class)
	public void delMenuById(String id,String role_id) {
        
        RoleManageExample roleManageExample = new RoleManageExample();
        RoleManageExample.Criteria roleManageExampleCir = roleManageExample.createCriteria();
        roleManageExampleCir.andROLE_IDEqualTo(role_id);
        roleManageExampleCir.andIDEqualTo(id);
        
        this.roleManageMapper.deleteByExample(roleManageExample);
	}
    
	/**
	 * 校验登录名是否存在
	 */
	public boolean verifyLoginName(String loginName) {
		UserBaseExample userBaseExample = new UserBaseExample();
		UserBaseExample.Criteria userBaseExampleCir = userBaseExample.createCriteria();
		userBaseExampleCir.andLOGIN_NAMEEqualTo(loginName);
		int cnt = this.userBaseMapper.countByExample(userBaseExample);
		if(cnt>0){
			return false;
		}else{
			
			return true;
		}
		
	}
    
	/**
	 * 校验商户编号是否存在
	 */
	public boolean verifyMerchantId(String merchant_code) {
		MemberPublicExample memberPublicExample = new MemberPublicExample();
		MemberPublicExample.Criteria memberPublicExampleCir = memberPublicExample.createCriteria();
		memberPublicExampleCir.andMERCHANT_CODEEqualTo(merchant_code);
		int cnt = this.memberPublicMapper.countByExample(memberPublicExample);
		if(cnt >0){
			return true;
		}else{
			return false;
		}
		
	}
    
	/**
	 * 校验当前用户是否存在
	 */
	public boolean verifyLoginPwd(String loginName, String loginPwd) {
		UserBaseExample userBaseExample = new UserBaseExample();
		userBaseExample.setLoginName(loginName);
		userBaseExample.setLoginPwd(loginPwd);
		List list = this.userBaseMapper.selectUserBaseByExample(userBaseExample);
		if(list!=null && list.size()>0){
			return true;
		}
		return false;
	}
    
	/**
	 * 生成角色编号
	 */
	public String getRoleId() {
		String role_id = this.roleManageMapper.selectMaxRoleId();
		BigDecimal big = new BigDecimal(role_id);
		big = big.add(new BigDecimal("1"));
		return String.valueOf(big);
	}

}
