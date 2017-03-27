package com.allscore.wireless.controller;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allscore.wireless.biz.ActivityManager;
import com.allscore.wireless.biz.MemberPublicManager;
import com.allscore.wireless.common.BaseController;
import com.allscore.wireless.common.Pager;
import com.allscore.wireless.dao.MemberPublicWithBLOBs;
import com.allscore.wireless.dao.MsgTemplate;
import com.allscore.wireless.dao.PromotionPlan;
/**
 * 消息模板
 * @author wwb
 *
 */
@Controller
@RequestMapping("/msg")
public class MsgTemplateController extends BaseController {

	private static final Logger logger = Logger.getLogger(MsgTemplateController.class);

	@Autowired
	private MemberPublicManager memberPublicManager;

    /**
     * 跳转界面---查看消息模板
     * @return
     */
	@RequestMapping(value = "/queryMsgTemFowd")
	public String queryMsgTemFowd() {
		logger.info("跳转界面==================查看消息模板");
		
		return "/sellerManage/queryMsgTemplate";
	}
	/**
	 * 商户管理---查看消息模板
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/queryMsgTem")
	@ResponseBody
	public Map querySchem(HttpServletRequest request) throws Exception {
		logger.info("消息模板管理==================查看消息模板");
		Map<String, Object> result = new HashMap<String, Object>(2);

		String merchant_code = (String) request.getParameter("merchant_code");//商户编号
		

		logger.info("merchant_code:"+merchant_code);
		
		//获取分页数据参数
		int pageRows=Integer.parseInt(request.getParameter("rows").toString());
		int page=Integer.parseInt(request.getParameter("page").toString());
		Pager pager = new Pager(page,pageRows);
		//查询条件封装参数
		Map map = new HashMap();
	    map.put("merchant_code", merchant_code);
	    
	    Map m = this.memberPublicManager.getMsgTemplateByMap(pager, map);
	    List<MsgTemplate> listMsgTemplate = (List) m.get("list");
	    logger.info("返回结果列表："+listMsgTemplate.size());
	    //将list中的PosInfo转成map
	    List list = this.getList(listMsgTemplate);
	    
		result.put("total", m.get("total"));
	    result.put("rows", list);
		return result;
	}
	
	/**
     * 查询商户明细信息
     * @return
	 * @throws Exception 
     */
	@RequestMapping(value = "/queryMemberInfo")
	@ResponseBody
	public String queryMemberInfo(HttpServletRequest request) throws Exception {
		logger.info("消息模板==================查询商户明细信息");
		String merchant_code = request.getParameter("merchant_code");
		MemberPublicWithBLOBs memberPublicWithBLOBs = this.memberPublicManager.getMemberPublicByMerchantID(merchant_code);
		
		return memberPublicWithBLOBs.getPUBLIC_ID();
	}
	
	/**
     * 新增消息模板
     * @return
	 * @throws Exception 
     */
	@RequestMapping(value = "/addMsgtemplate")
	@ResponseBody
	public String addMsgtemplate(HttpServletRequest request){
		logger.info("消息模板==================新增消息模板");
		String merchant_code = request.getParameter("merchant_id");
		String public_id = request.getParameter("public_id");
		String template_id = request.getParameter("template_id");
		String template_type = request.getParameter("template_type");
		String remark = request.getParameter("remark");
		String status = request.getParameter("status");
		Map map = new HashMap();
		map.put("merchant_code", merchant_code);
		map.put("public_id", public_id);
		map.put("template_id", template_id);
		map.put("template_type", template_type);
		map.put("remark", remark);
		map.put("status", status);
		
		try{
			int cnt = this.memberPublicManager.addMsgTemplateByMap(map);
			if(cnt>0){
				return "success";
			}else{
				return "fail";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
	}
	
	/**
     * 删除消息模板
     * @return
	 * @throws Exception 
     */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public String delete(HttpServletRequest request){
		logger.info("消息模板==================删除消息模板");
		String id = request.getParameter("id");
		try{
			int cnt = this.memberPublicManager.delMsgTemplateByID(id);
			if(cnt>0){
				return "success";
			}else{
				return "fail";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
	}
	
}
