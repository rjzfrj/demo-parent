package com.allscore.wireless.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allscore.wireless.biz.ActivityManager;
import com.allscore.wireless.common.BaseController;
import com.allscore.wireless.common.Pager;
import com.allscore.wireless.dao.PromotionPlan;
/**
 * 活动管理
 * @author wwb
 *
 */
@Controller
@RequestMapping("/activity")
public class ActivityController extends BaseController {

	private static final Logger logger = Logger.getLogger(ActivityController.class);

	@Autowired
	private ActivityManager activityManager;

    /**
     * 跳转界面---查看促销方案
     * @return
     */
	@RequestMapping(value = "/queryCxSchemaFowd")
	public String queryCxSchemaFowd() {
		logger.info("跳转界面==================查看促销方案");
		
		return "/hdgl/queryCxfa";
	}
	/**
	 * 活动管理---查看促销方案
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/querySchem")
	@ResponseBody
	public Map querySchem(HttpServletRequest request) throws Exception {
		logger.info("活动管理==================查看促销方案");
		Map<String, Object> result = new HashMap<String, Object>(2);

		String merchant_name = (String) request.getParameter("merchant_name");//商户名称
		String type =  (String) request.getParameter("type");//促销方案

		logger.info("merchant_name:"+merchant_name);
		logger.info("type:"+type);
		
		//获取分页数据参数
		int pageRows=Integer.parseInt(request.getParameter("rows").toString());
		int page=Integer.parseInt(request.getParameter("page").toString());
		Pager pager = new Pager(page,pageRows);
		//查询条件封装参数
		Map map = new HashMap();
	    map.put("merchant_name", merchant_name);
	    map.put("type", type);
	    Map m = this.activityManager.getPromotionPlanByMap(pager, map);
	    List<PromotionPlan> listPromotionPlan = (List) m.get("list");
	    logger.info("返回结果列表："+listPromotionPlan.size());
	    //将list中的PosInfo转成map
	    List list = this.getList(listPromotionPlan);

		result.put("total", m.get("total"));
	    result.put("rows", list);
		return result;
	}
	
	/**
	 * 活动管理---查看促销方案明细
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/detailInfo")
	public String detailInfo(HttpServletRequest request,ModelMap map) throws Exception {
		logger.info("活动管理==================查看促销方案明细");
		Map<String, Object> result = new HashMap<String, Object>(2);
		String id = (String) request.getParameter("id");//主键

		logger.info("id:"+id);
	    PromotionPlan promotionPlan = this.activityManager.getPromotionPlanByID(id);

	    //将list中的PosInfo转成map
	    map.put("promotionPlan", promotionPlan);
		return "/hdgl/cxDetail";
	}
	
	 /**
     * 跳转界面---设置充值促销方案
     * @return
     */
	@RequestMapping(value = "/czSchemaFowd")
	public String czSchemaFoward() {
		logger.info("跳转界面==================设置充值促销方案");	
		return "/hdgl/szczcxfa";
	}
	/**
	 * 活动管理---设置充值促销方案--查询
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/czSchema")
	@ResponseBody
	public Map czSchema(HttpServletRequest request) throws Exception {
		logger.info("活动管理==================设置充值促销方案--查询");
		Map<String, Object> result = new HashMap<String, Object>(2);
		String strDate = (String) request.getParameter("strDate");//生效开始时间
		String endDate = (String) request.getParameter("endDate");//生效结束时间

		logger.info("strDate:"+strDate);
		logger.info("endDate:"+endDate);
		
		//获取分页数据参数
		int pageRows=Integer.parseInt(request.getParameter("rows").toString());
		int page=Integer.parseInt(request.getParameter("page").toString());
		Pager pager = new Pager(page,pageRows);
		//查询条件封装参数
		Map map = new HashMap();
	    map.put("strDate", strDate);
	    map.put("endDate", endDate);
	    map.put("type", "00");
	    Map m = this.activityManager.getPromotionPlanByMap(pager, map);
	    List<PromotionPlan> listPromotionPlan = (List) m.get("list");
	    logger.info("返回结果列表："+listPromotionPlan.size());
	    //将list中的PosInfo转成map
	    List list = this.getList(listPromotionPlan);

		result.put("total", m.get("total"));
	    result.put("rows", list);
		return result;
	}
	
	/**
	 * 活动管理---设置充值促销方案--新增
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/setCzSchema")
	@ResponseBody
	public String setCzSchema(HttpServletRequest request,@RequestParam("czAmount")String[] czAmount) throws Exception {
		logger.info("活动管理==================设置充值促销方案--新增");
		Map<String, Object> result = new HashMap<String, Object>(2);
		String merchantCode = (String) request.getParameter("merchantCode");//商户编号
		String validDay = (String) request.getParameter("validDay");//优惠券有效期限
		String stilValid = (String) request.getParameter("stilValid");//长期有效
		String validDate = (String) request.getParameter("validDate");//方案生效日期
        
        for(String strAmount :czAmount){
        	logger.info("czAmount:"+strAmount);
        }
        logger.info("merchantCode:"+merchantCode);
		logger.info("validDay:"+validDay);
		logger.info("stilValid:"+stilValid);
		logger.info("validDate:"+validDate);
		//查询条件封装参数
		Map map = new HashMap();
	    map.put("amount", czAmount);
	    map.put("merchantCode", merchantCode);
	    map.put("validDay", validDay);
	    map.put("stilValid", stilValid);
	    map.put("validDate", validDate);
	    map.put("type", "00");
	    
	    int cnt = this.activityManager.checkIsExist(map);
	    if(cnt>0){
	    	return "checkSuccess";
	    }
	    this.activityManager.addPromotionPlanByMap(map);
	    return "success";
	}
	
	 /**
     * 跳转界面---设置消费促销方案
     * @return
     */
	@RequestMapping(value = "/xfSchemaFowd")
	public String xfSchemaFowd() {
		logger.info("跳转界面==================设置消费促销方案");	
		return "/hdgl/szxfcxfa";
	}
	/**
	 * 活动管理---设置消费促销方案--查询
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/xfSchema")
	@ResponseBody
	public Map xfSchema(HttpServletRequest request) throws Exception {
		logger.info("活动管理==================设置消费促销方案--查询");
		Map<String, Object> result = new HashMap<String, Object>(2);
		String strDate = (String) request.getParameter("strDate");//生效开始时间
		String endDate = (String) request.getParameter("endDate");//生效结束时间

		logger.info("strDate:"+strDate);
		logger.info("endDate:"+endDate);
		
		//获取分页数据参数
		int pageRows=Integer.parseInt(request.getParameter("rows").toString());
		int page=Integer.parseInt(request.getParameter("page").toString());
		Pager pager = new Pager(page,pageRows);
		//查询条件封装参数
		Map map = new HashMap();
	    map.put("strDate", strDate);
	    map.put("endDate", endDate);
	    map.put("type", "01");
	    Map m = this.activityManager.getPromotionPlanByMap(pager, map);
	    List<PromotionPlan> listPromotionPlan = (List) m.get("list");
	    logger.info("返回结果列表："+listPromotionPlan.size());
	    //将list中的PosInfo转成map
	    List list = this.getList(listPromotionPlan);

		result.put("total", m.get("total"));
	    result.put("rows", list);
		return result;
	}
	
	/**
	 * 活动管理---设置消费促销方案--新增
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/setXfSchema")
	@ResponseBody
	public String setXfSchema(HttpServletRequest request,
			@RequestParam("xfAmount")String[] xfAmount) throws Exception {
		logger.info("活动管理==================设置消费促销方案--新增");
		Map<String, Object> result = new HashMap<String, Object>(2);
		String merchantCode = (String) request.getParameter("merchantCode");//商户编号
		String validDay = (String) request.getParameter("validDay");//优惠券有效期限
		String stilValid = (String) request.getParameter("stilValid");//长期有效
		String validDate = (String) request.getParameter("validDate");//方案生效日期
        
        for(String strAmount :xfAmount){
        	logger.info("czAmount:"+strAmount);
        }
        logger.info("merchantCode:"+merchantCode);
		logger.info("validDay:"+validDay);
		logger.info("stilValid:"+stilValid);
		logger.info("validDate:"+validDate);
		//封装参数
		Map map = new HashMap();
	    map.put("amount", xfAmount);
	    map.put("merchantCode", merchantCode);
	    map.put("validDay", validDay);
	    map.put("stilValid", stilValid);
	    map.put("validDate", validDate);
	    map.put("type", "01");
	    
	    int cnt = this.activityManager.checkIsExist(map);
	    if(cnt>0){
	    	return "checkSuccess";
	    }
	    this.activityManager.addPromotionPlanByMap(map);
		return "success";
	}
	
	
	 /**
     * 跳转界面---设置点评促销方案
     * @return
     */
	@RequestMapping(value = "/dpSchemaFowd")
	public String dpSchemaFowd() {
		logger.info("跳转界面==================设置点评促销方案");	
		return "/hdgl/szdpcxfa";
	}
	/**
	 * 活动管理---设置点评促销方案--查询
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/dpSchema")
	@ResponseBody
	public Map dpSchema(HttpServletRequest request) throws Exception {
		logger.info("活动管理==================设置点评促销方案--查询");
		Map<String, Object> result = new HashMap<String, Object>(2);
		String strDate = (String) request.getParameter("strDate");//生效开始时间
		String endDate = (String) request.getParameter("endDate");//生效结束时间

		logger.info("strDate:"+strDate);
		logger.info("endDate:"+endDate);
		
		//获取分页数据参数
		int pageRows=Integer.parseInt(request.getParameter("rows").toString());
		int page=Integer.parseInt(request.getParameter("page").toString());
		Pager pager = new Pager(page,pageRows);
		//查询条件封装参数
		Map map = new HashMap();
	    map.put("strDate", strDate);
	    map.put("endDate", endDate);
	    map.put("type", "02");
	    Map m = this.activityManager.getPromotionPlanByMap(pager, map);
	    List<PromotionPlan> listPromotionPlan = (List) m.get("list");
	    logger.info("返回结果列表："+listPromotionPlan.size());
	    //将list中的PosInfo转成map
	    List list = this.getList(listPromotionPlan);
        
	    logger.info("总记录数："+m.get("total"));
		result.put("total", m.get("total"));
	    result.put("rows", list);
		return result;
	}
	
	/**
	 * 活动管理---设置点评促销方案--新增
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/setDpSchema")
	@ResponseBody
	public String setDpSchema(HttpServletRequest request) throws Exception {
		logger.info("活动管理==================设置点评促销方案--新增");
		Map<String, Object> result = new HashMap<String, Object>(2);
		String reward = (String) request.getParameter("reward");//优惠券金额
		String merchantCode = (String) request.getParameter("merchantCode");//商户编号
		String validDay = (String) request.getParameter("validDay");//优惠券有效期限
		String stilValid = (String) request.getParameter("stilValid");//长期有效
		String validDate = (String) request.getParameter("validDate");//方案生效日期

		logger.info("reward:"+reward);
		logger.info("merchantCode:"+merchantCode);
		logger.info("validDay:"+validDay);
		logger.info("stilValid:"+stilValid);
		logger.info("validDate:"+validDate);
		//查询条件封装参数
		Map map = new HashMap();
	    map.put("reward", reward);
	    map.put("merchant_code", merchantCode);
	    map.put("validDay", validDay);
	    map.put("stilValid", stilValid);
	    map.put("validDate", validDate);
	    map.put("type", "02");
	    int cnt = this.activityManager.checkIsExist(map);
	    System.out.println("cnt:"+cnt);
	    if(cnt>0){
	    	return "checkSuccess";
	    }
	    this.activityManager.addPromotionPlanByMap(map);
		return "success";
	}
	 /**
     * 跳转界面---设置注册促销方案
     * @return
     */
	@RequestMapping(value = "/zcSchemaFowd")
	public String zcSchemaFowd() {
		logger.info("跳转界面==================设置注册促销方案");	
		return "/hdgl/szzccxfa";
	}
	/**
	 * 活动管理---设置注册促销方案--查询
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/zcSchema")
	@ResponseBody
	public Map zcSchema(HttpServletRequest request) throws Exception {
		logger.info("活动管理==================设置注册促销方案--查询");
		Map<String, Object> result = new HashMap<String, Object>(2);
		String strDate = (String) request.getParameter("strDate");//生效开始时间
		String endDate = (String) request.getParameter("endDate");//生效结束时间

		logger.info("strDate:"+strDate);
		logger.info("endDate:"+endDate);
		
		//获取分页数据参数
		int pageRows=Integer.parseInt(request.getParameter("rows").toString());
		int page=Integer.parseInt(request.getParameter("page").toString());
		Pager pager = new Pager(page,pageRows);
		//查询条件封装参数
		Map map = new HashMap();
	    map.put("strDate", strDate);
	    map.put("endDate", endDate);
	    map.put("type", "03");
	    Map m = this.activityManager.getPromotionPlanByMap(pager, map);
	    List<PromotionPlan> listPromotionPlan = (List) m.get("list");
	    logger.info("返回结果列表："+listPromotionPlan.size());
	    //将list中的PosInfo转成map
	    List list = this.getList(listPromotionPlan);

		result.put("total", m.get("total"));
	    result.put("rows", list);
		return result;
	}
	
	/**
	 * 活动管理---设置注册促销方案--新增
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/setZcSchema")
	@ResponseBody
	public String setZcSchema(HttpServletRequest request) throws Exception {
		logger.info("活动管理==================设置注册促销方案--新增");
		Map<String, Object> result = new HashMap<String, Object>(2);
		String reward = (String) request.getParameter("reward");//优惠券金额
		String merchantCode = (String) request.getParameter("merchantCode");//商户编号
		String validDay = (String) request.getParameter("validDay");//优惠券有效期限
		String stilValid = (String) request.getParameter("stilValid");//长期有效
		String validDate = (String) request.getParameter("validDate");//方案生效日期

		logger.info("reward:"+reward);
		logger.info("merchantCode:"+merchantCode);
		logger.info("validDay:"+validDay);
		logger.info("stilValid:"+stilValid);
		logger.info("validDate:"+validDate);
		//查询条件封装参数
		Map map = new HashMap();
	    map.put("reward", reward);
	    map.put("merchant_code", merchantCode);
	    map.put("validDay", validDay);
	    map.put("stilValid", stilValid);
	    map.put("validDate", validDate);
	    map.put("type", "03");
	    
	    int cnt = this.activityManager.checkIsExist(map);
	    if(cnt>0){
	    	return "checkSuccess";
	    }
	    this.activityManager.addPromotionPlanByMap(map);
		return "success";
	}
	
	/**
	 * 删除数据
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delSchema")
	@ResponseBody
	public String delSchema(HttpServletRequest request){
		logger.info("活动管理==================删除");
		Map<String, Object> result = new HashMap<String, Object>(2);
		String id = (String) request.getParameter("id");//促销方案ID
		

		logger.info("id:"+id);
		
		//查询条件封装参数
		Map map = new HashMap();
	    try{
	    	int i = this.activityManager.delPromotionPlanByID(id);
	    	logger.info("删除记录数："+i);
			return "success";
	    	
	    }catch(Exception e){
	    	logger.info("Exception:"+e.getMessage());
	    	
	    	return "error";
	    }
	    
	}
	
	
}
