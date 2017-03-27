package com.allscore.wireless.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allscore.wireless.biz.MenuManager;
import com.allscore.wireless.biz.RoleManager;
import com.allscore.wireless.biz.SysManager;
import com.allscore.wireless.common.BaseController;
import com.allscore.wireless.common.Pager;
import com.allscore.wireless.dao.Menu;
import com.allscore.wireless.dao.RoleManage;
import com.allscore.wireless.dao.UserBase;
/**
 * 系统设置
 * @author dell
 *
 */
@Controller
@RequestMapping("/sys")
public class SysController extends BaseController {

	private static final Logger logger = Logger.getLogger(SysController.class);

	@Autowired
	private SysManager sysManager;
	
	@Autowired
	private RoleManager roleManager;
	
	@Autowired
	private MenuManager menuManager;
	
	/**
     * 系统设置---校验原密码是否正确
     * @return
	 * @throws Exception 
     */
	@RequestMapping(value = "/checkPwd")
	@ResponseBody
	public boolean checkPwd(HttpServletRequest request) throws Exception {
		logger.info("系统设置==================校验密码");	
		String login_name = request.getParameter("login_name");
		String login_pwd = request.getParameter("login_pwd");
		logger.info("login_name"+login_name);
		logger.info("login_pwd"+login_pwd);
		login_pwd = this.EncoderByMd5(login_pwd);
		boolean flag = this.sysManager.verifyLoginPwd(login_name,login_pwd);
		//System.out.println("修改数量："+count);
		
		return flag;
	}
    /**
     * 系统设置---修改密码
     * @return
     * @throws Exception 
     */
	@RequestMapping(value = "/updatePwd")
	@ResponseBody
	public int updatePwd(HttpServletRequest request) throws Exception {
		System.out.println("系统设置==================修改密码");	
		String login_name = request.getParameter("login_name");
		String login_pwd = request.getParameter("login_pwd");
		String login_newpwd = request.getParameter("login_newpwd");
		login_pwd = this.EncoderByMd5(login_pwd);
		login_newpwd = this.EncoderByMd5(login_newpwd);
		Map map = new HashMap();
		map.put("login_name", login_name);
		map.put("login_pwd", login_pwd);
		map.put("login_newpwd", login_newpwd);
		int count = sysManager.updateUserPwdbyMap(map);
		System.out.println("修改数量："+count);
		
		return count;
	}

    /**
     * 跳转界面---人员管理
     * @return
     */
	@RequestMapping(value = "/userFowd")
	public String userFowd() {
		System.out.println("跳转界面==================人员管理");	
		return "/xtsz/userManage";
	}
	/**
	 * 系统设置---人员管理---查询
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/queryUser")
	@ResponseBody
	public Map queryUser(HttpServletRequest request) throws Exception {
		System.out.println("人员管理==================查询");
		Map<String, Object> result = new HashMap<String, Object>(2);
		
		//获取分页数据参数
		int pageRows=Integer.parseInt(request.getParameter("rows").toString());
		int page=Integer.parseInt(request.getParameter("page").toString());
		Pager pager = new Pager(page,pageRows);
		//查询条件封装参数
		Map map = new HashMap();
	    Map m = this.sysManager.getUserBaseByMap(pager, map);
		
	    List<UserBase> listUserBase = (List) m.get("list");
	    System.out.println("返回结果列表："+listUserBase.size());
	    //将list中的PosInfo转成map
	    List list = this.getList(listUserBase);

		result.put("total", m.get("total"));
	    result.put("rows", list);
		return result;
	}
	
	/**
	 * 系统设置---人员管理---新增
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/addUser")
	@ResponseBody
	public String addUser(HttpServletRequest request) throws Exception {
		System.out.println("人员管理==================新增");
		Map<String, Object> result = new HashMap<String, Object>(2);
		String name = (String) request.getParameter("name");//人员名称
		name = new String(name.getBytes("ISO-8859-1"),"UTF-8");
		String login_name = (String) request.getParameter("login_name");//登录名
		login_name = new String(login_name.getBytes("ISO-8859-1"),"UTF-8");
		String login_pwd = (String) request.getParameter("login_pwd");//用户密码
		login_pwd = this.EncoderByMd5(login_pwd);
		String mobile = (String) request.getParameter("mobile");//手机号
		String role_id = (String) request.getParameter("role_id");//角色ID
		String merchant_code = (String) request.getParameter("merchant_code");//商户编号
		System.out.println("name:"+name);
		System.out.println("login_name:"+login_name);
		System.out.println("login_pwd:"+login_pwd);
		System.out.println("mobile:"+mobile);
		System.out.println("role_id:"+role_id);
		System.out.println("merchant_code:"+merchant_code);
		
		//查询条件封装参数
		Map map = new HashMap();
	    map.put("name", name);
	    map.put("login_name", login_name);
	    map.put("login_pwd", login_pwd);
	    map.put("mobile", mobile);
	    map.put("role_id", role_id);
	    map.put("merchant_code", merchant_code);
	    try{
		    int cnt =  this.sysManager.addUserBaseByMap(map);
		    if(cnt>0){
		    	return "success";
		    	
		    }else{
		    	return "fail";
		    }
	    }catch(Exception e){
	    	return "fail";
	    }

	}
	
	/**
	 * 系统设置---人员管理---修改查询
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/editUserFowd")
	public String editUserFowd(HttpServletRequest request,ModelMap map) throws Exception {
		System.out.println("人员管理==================修改查询");
		Map<String, Object> result = new HashMap<String, Object>(2);
		String id = (String) request.getParameter("id");//主键
        logger.info("id:"+id);
		//查询条件封装参数
		UserBase userBase = this.sysManager.getUserBaseById(id);
		map.put("userBase", userBase);

		return "/xtsz/editUser";
	}
	
	/**
	 * 系统设置---人员管理---修改
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/editUser")
	public String editUser(HttpServletRequest request) throws Exception {
		System.out.println("人员管理==================修改");
		Map<String, Object> result = new HashMap<String, Object>(2);
		String id = (String) request.getParameter("id");//主键
		String name = (String) request.getParameter("name");//人员名称
		//name = new String(name.getBytes("ISO-8859-1"),"UTF-8");
		String login_name = (String) request.getParameter("login_name");//登录名
		String login_pwd = (String) request.getParameter("login_pwd");//用户密码
		login_pwd = this.EncoderByMd5(login_pwd);
		String mobile = (String) request.getParameter("mobile");//手机号
		String role_id = (String) request.getParameter("role_id");//角色ID
		System.out.println("id:"+id);
		System.out.println("name:"+name);
		System.out.println("login_name:"+login_name);
		System.out.println("login_pwd:"+login_pwd);
		System.out.println("mobile:"+mobile);
		System.out.println("role_id:"+role_id);
		
		//查询条件封装参数
		Map map = new HashMap();
		map.put("id", id);
	    map.put("name", name);
	    map.put("login_name", login_name);
	    map.put("login_pwd", login_pwd);
	    map.put("mobile", mobile);
	    map.put("role_id", role_id);
	    this.sysManager.updateUserBaseById(map);
	    
		return "/xtsz/userManage";
	}
	
	/**
	 * 系统设置---人员管理---删除
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/delUser")
	@ResponseBody
	public String delUser(HttpServletRequest request) throws Exception {
		System.out.println("人员管理==================删除");
		Map<String, Object> result = new HashMap<String, Object>(2);
		String id = (String) request.getParameter("id");//主键
		System.out.println("id:"+id);
		
		//查询条件封装参数
		//Map map = new HashMap();
		//map.put("id", id);
	    this.sysManager.delUserBaseById(id);
	    
		return "success";
	}
	
	/**
	 * 系统设置---人员管理---重置密码
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/resetUser")
	@ResponseBody
	public String resetUser(HttpServletRequest request) throws Exception {
		System.out.println("人员管理==================重置密码");
		Map<String, Object> result = new HashMap<String, Object>(2);
		String id = (String) request.getParameter("id");//主键
		System.out.println("id:"+id);
		
		//查询条件封装参数
		//Map map = new HashMap();
		//map.put("id", id);
	    this.sysManager.updateUserBaseById(id);
	    
		return "success";
	}
	
	/**
	 * 系统设置---人员管理---注销
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/zxUser")
	@ResponseBody
	public String zxUser(HttpServletRequest request) throws Exception {
		System.out.println("人员管理==================注销");
		Map<String, Object> result = new HashMap<String, Object>(2);
		String id = (String) request.getParameter("id");//主键
		System.out.println("id:"+id);
		
		//查询条件封装参数
		//Map map = new HashMap();
		//map.put("id", id);
	    this.sysManager.updateUserBaseById(id);
	    
		return "success";
	}
	
	
    /**
     * 跳转界面---角色管理
     * @return
     */
	@RequestMapping(value = "/roleFowd")
	public String roleFowd() {
		System.out.println("跳转界面==================角色管理");	
		return "/xtsz/roleManage";
	}
	/**
	 * 系统设置---角色管理---查询
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/queryRole")
	@ResponseBody
	public Map queryRole(HttpServletRequest request) throws Exception {
		System.out.println("角色管理==================查询");
		Map<String, Object> result = new HashMap<String, Object>(2);
		
		//获取分页数据参数
		int pageRows=Integer.parseInt(request.getParameter("rows").toString());
		int page=Integer.parseInt(request.getParameter("page").toString());
		Pager pager = new Pager(page,pageRows);
		//查询条件封装参数
		Map map = new HashMap();
	    Map m = this.sysManager.getRoleBaseByMap(pager, map);
		
	    List<RoleManage> listRoleManage = (List) m.get("list");
	    System.out.println("返回结果列表："+listRoleManage.size());
	    //将list中的PosInfo转成map
	    List list = this.getList(listRoleManage);

		result.put("total", m.get("total"));
	    result.put("rows", list);
		return result;
	}
	
	/**
	 * 系统设置---新增角色---获取角色编号
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getRoleId")
	@ResponseBody
	public String getRoleId(HttpServletRequest request) throws Exception {
	    String role_id = this.sysManager.getRoleId();
	    return role_id;
	}
	
	/**
	 * 系统设置---新增角色---新增
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/addRole")
	@ResponseBody
	public String addRole(HttpServletRequest request) throws Exception {
		System.out.println("角色管理==================新增");
		Map<String, Object> result = new HashMap<String, Object>(2);
		String role_id = (String) request.getParameter("role_id");//角色ID
		String role_name = (String) request.getParameter("role_name");//角色名称
		role_name = new String(role_name.getBytes("ISO-8859-1"),"UTF-8");

		System.out.println("role_id:"+role_id);
		System.out.println("role_name:"+role_name);
		
		//查询条件封装参数
		Map map = new HashMap();
	    map.put("role_id", role_id);
	    map.put("role_name", role_name);
	    int cnt = this.sysManager.addRoleBaseByMap(map);
	    if(cnt > 0){
	    	return  "fail";
	    	
	    }else{
	    	return "success";
	    }
	    
	}
	
	/**
	 * 系统设置---分配角色---新增
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/fenpRole")
	@ResponseBody
	public String fenpRole(HttpServletRequest request) throws Exception {
		System.out.println("角色管理==================分配权限");
		Map<String, Object> result = new HashMap<String, Object>(2);
		String id = (String) request.getParameter("id");//主键
		String seller = (String) request.getParameter("sellerManage");//商户管理
		String member = (String) request.getParameter("memberManage");//会员管理
		String business = (String) request.getParameter("businessManage");//会员交易管理
		String finance = (String) request.getParameter("financeManage");//财务管理
		String activity = (String) request.getParameter("activityManage");//活动管理
		String pos = (String) request.getParameter("posManage");//POS机管理
		String sys = (String) request.getParameter("sysManage");//系统设置
		String app = (String) request.getParameter("appManage");//店+APP管理
		Map map = new HashMap();
		map.put("seller", seller);
		map.put("member", member);
		map.put("business", business);
		map.put("finance", finance);
		map.put("activity", activity);
		map.put("pos", pos);
		map.put("sys", sys);
		map.put("app", app);
		/*List<RoleManage> listRoleManage = this.roleManager.getRoleName();
		//List list 
		for(int i=0;i<listRoleManage.size();i++){
			String role_id = (String) request.getParameter(listRoleManage.get(i).getROLE_ID());//角色ID
			if(role_id!=null&&!"".equals(role_id)){
				map.put(role_id, role_id);
			}
		}
	    int cnt = this.sysManager.addUserBaseByMap(map, id);
	    System.out.println("新增数据条数："+cnt);*/
		int cnt = this.sysManager.addRoleMenuByMap(map,id);
		System.out.println("新增数据条数："+cnt);
		return "success";
	}
	/**
	 * 系统设置---角色管理---删除人员角色
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/delRole")
	@ResponseBody
	public String delRole(HttpServletRequest request) throws Exception {
		System.out.println("人员管理==================删除");
		Map<String, Object> result = new HashMap<String, Object>(2);
		String id = (String) request.getParameter("id");//主键
		String role_id = (String) request.getParameter("role_id");//角色ID
		System.out.println("id:"+id);
		int cnt = this.sysManager.getUserCountByRoleId(role_id);
		if(cnt >0){
			return "delFail";
		}else{
			this.sysManager.delMenuById(id,role_id);
			return "success";
		}
	    
	    
		
	}
	
	/**
	 * 系统设置---角色管理---登录名校验
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/loginVerifyAction")
	@ResponseBody
	public boolean loginVerifyAction(HttpServletRequest request) throws Exception {
		System.out.println("人员管理==================登录名校验");
		String login_name = (String) request.getParameter("login_name");//登录名
		System.out.println("login_name:"+login_name);
		
		//查询条件封装参数
		//Map map = new HashMap();
		//map.put("id", id);
	    boolean flag = this.sysManager.verifyLoginName(login_name);
	    
		return flag;
	}
	
	/**
	 * 系统设置---角色管理---商户编号校验
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/merchantIdVerifyAction")
	@ResponseBody
	public boolean merchantIdVerifyAction(HttpServletRequest request) throws Exception {
		System.out.println("人员管理==================商户编号校验");
		String merchant_code = (String) request.getParameter("merchant_code");//商户编号
		System.out.println("merchant_code:"+merchant_code);
		
		//查询条件封装参数
		//Map map = new HashMap();
		//map.put("id", id);
	    boolean flag = this.sysManager.verifyMerchantId(merchant_code);
	    
		return flag;
	}
	
	/**
	 * 系统设置---角色管理---跳转角色权限界面
	 * 
	 */
	@RequestMapping(value = "/roleFpFowd")
	public String roleFpFowd(HttpServletRequest request,ModelMap map) {
		System.out.println("跳转界面==================角色权限分配");	
		String login_name = (String)request.getParameter("login_name");
		String id = (String)request.getParameter("id");
		System.out.println("login_name:"+login_name);
		System.out.println("id:"+id);
		map.addAttribute("login_name", login_name.trim());
		map.addAttribute("id", id.trim());
		return "/xtsz/fpRole";
	}
	/**
	 * 角色管理--加载复选框
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/menu_List")
	@ResponseBody
	public List menu_List(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		
		String loginName = (String) request.getParameter("login_name");//主键

		List<Menu> listMenu = this.menuManager.getMenuByLoginName(loginName);
		System.out.println("获取权限数:"+listMenu.size());
		List list = this.getList(listMenu);
		//json格式返回，需要封装在map集合中
		
		return list;

	}
	
	/**
	 * 角色管理--加载动态下拉框
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/role_List")
	@ResponseBody
	public List role_List(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = new Pager();
		//查询条件封装参数
		Map map = new HashMap();
		List<RoleManage> listRoleManage = this.roleManager.getRoleName();
		System.out.println("获取下拉框角色数:"+listRoleManage.size());
		List list = this.getList(listRoleManage);
		//json格式返回，需要封装在map集合中
		
		return list;

	}
	
}
