package com.allscore.wireless.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.allscore.wireless.biz.UsersManager;
import com.allscore.wireless.common.BaseController;
import com.allscore.wireless.common.Pager;
import com.allscore.wireless.dao.Users;
/**
 * 店+App管理
 * @author dell
 *
 */
@Controller
@RequestMapping("/app")
public class AppController extends BaseController {

	private static final Logger logger = Logger.getLogger(AppController.class);

	@Autowired
	private UsersManager usersManager;

    /**
     * 跳转界面---会员钱包账户管理
     * @return
     */
	@RequestMapping(value = "/appFowd")
	public String appFowd() {
		System.out.println("跳转界面==================会员钱包账户管理");	
		return "/djapp/hyqbzhgl";
	}
	/**
	 * 店+App管理---会员钱包账户管理
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/memberApp")
	@ResponseBody
	public Map posrk(HttpServletRequest request) throws Exception {
		System.out.println("店+App管理==================会员钱包账户管理");
		Map<String, Object> result = new HashMap<String, Object>(2);
		String member_card = (String) request.getParameter("member_card");//会员卡号
		System.out.println("member_card:"+member_card);
		
		//获取分页数据参数
		int pageRows=Integer.parseInt(request.getParameter("rows").toString());
		int page=Integer.parseInt(request.getParameter("page").toString());
		Pager pager = new Pager(page,pageRows);
		//查询条件封装参数
		//Map map = new HashMap();
	    //map.put("mobile", mobile);
	    Map m = this.usersManager.getUsersByMobile(pager,member_card);
	    List<Users> listUsers = (List) m.get("list");
		List list = this.getList(listUsers);
		
		result.put("total", m.get("total"));
	    result.put("rows", list);
		return result;
	}
	
	
}
