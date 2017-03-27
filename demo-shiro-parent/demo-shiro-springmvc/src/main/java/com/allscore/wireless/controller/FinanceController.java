package com.allscore.wireless.controller;

import java.io.OutputStream;
import java.text.ParseException;
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


import com.allscore.wireless.biz.FinanceManager;
import com.allscore.wireless.common.BaseController;
import com.allscore.wireless.common.ExportExcelStyleAndData;
import com.allscore.wireless.common.Pager;
import com.allscore.wireless.dao.CheckBusiness;
import com.allscore.wireless.dao.CheckError;
import com.allscore.wireless.dao.ShopBusiness;
/**
 * 财务管理
 * @author dell
 *
 */
@Controller
@RequestMapping("/finance")
public class FinanceController extends BaseController {

	private static final Logger logger = Logger.getLogger(FinanceController.class);

	@Autowired
	private FinanceManager financeManager;
	
	//@Autowired
	//private NPayAgentPayService nPayAgentPayService;


    /**
     * 跳转界面---提现审核查询
     * @return
     */
	@RequestMapping(value = "/reviewFowd")
	public String txReviewFoward() {
		logger.info("跳转界面==================提现审核查询");
		return "/finance/txReviewQuery";
	}
	/**
	 * 财务管理---提现审核查询
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/txReview")
	@ResponseBody
	public Map queryTxReview(HttpServletRequest request) throws Exception {
		System.out.println("财务管理==================提现审核查询");
		Map<String, Object> result = new HashMap<String, Object>(2);
		String strDate = (String) request.getParameter("strDate");//交易开始时间
		String endDate = (String) request.getParameter("endDate");//交易结束时间
		String MERCHANT_CODE = (String) request.getParameter("MERCHANT_CODE");//商户编号
		String merchant_name = (String) request.getParameter("merchant_name");//商户名称
		String serial = (String) request.getParameter("serial");//交易流水号
		String status =  (String) request.getParameter("status");//交易状态
		logger.info("strDate:"+strDate);
		logger.info("endDate:"+endDate);
		logger.info("MERCHANT_CODE:"+MERCHANT_CODE);
		logger.info("merchant_name:"+merchant_name);
		logger.info("serial:"+serial);
		logger.info("status:"+status);
		
		//获取分页数据参数
		int pageRows=Integer.parseInt(request.getParameter("rows").toString());
		int page=Integer.parseInt(request.getParameter("page").toString());
		Pager pager = new Pager(page,pageRows);
		//查询条件封装参数
		Map map = new HashMap();
	    map.put("strDate", strDate);
	    map.put("endDate", endDate);
	    map.put("MERCHANT_CODE", MERCHANT_CODE);
	    map.put("merchant_name", merchant_name);
	    map.put("serial", serial);
	    map.put("status", status);
	    Map m = this.financeManager.getReviewTxByMap(pager, map);
	    
	    List<ShopBusiness> listShopBusiness = (List) m.get("list");
	    System.out.println("返回结果列表："+listShopBusiness.size());
	   
	    List list = this.getList(listShopBusiness);
	    
	    result.put("total", m.get("total"));  
        result.put("rows", list);
		return result;
	}
	
	/**
	 * 财务管理---提现审核查询---打批次
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/addBatchNum")
	@ResponseBody
	public String addBatchNum(HttpServletRequest request) throws Exception {
		System.out.println("财务管理==================提现审核---打批次");
		Map<String, Object> result = new HashMap<String, Object>(2);
		String id = (String) request.getParameter("id");//主键
		String[] strId  = id.split(",");
		//获取分页数据参数
		//int pageRows=Integer.parseInt(request.getParameter("rows").toString());
		//int page=Integer.parseInt(request.getParameter("page").toString());
		Pager pager = new Pager();
		//查询条件封装参数
		Map map = new HashMap();
	    map.put("strId", strId);
	    this.financeManager.updateShopBusinessByList(map);
	
		return "success";
	}
	
	/**
	 * 提现审核查询导出
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/reviewTx_export")
	public void querytxReviewexport(HttpServletRequest request,HttpServletResponse response) throws Exception{
		System.out.println("财务管理==================提现审核查询");
		Map<String, Object> result = new HashMap<String, Object>(2);
		String strDate = (String) request.getParameter("strDate");//交易开始时间
		String endDate = (String) request.getParameter("endDate");//交易结束时间
		String MERCHANT_CODE = (String) request.getParameter("MERCHANT_CODE");//商户编号
		String merchant_name = (String) request.getParameter("merchant_name");//商户名称
		merchant_name = new String(merchant_name.getBytes("ISO-8859-1"),"UTF-8");
		String serial = (String) request.getParameter("serial");//交易流水号
		String status =  (String) request.getParameter("status");//交易状态
		logger.info("strDate:"+strDate);
		logger.info("endDate:"+endDate);
		logger.info("MERCHANT_CODE:"+MERCHANT_CODE);
		logger.info("merchant_name:"+merchant_name);
		logger.info("serial:"+serial);
		logger.info("status:"+status);
		
		//获取分页数据参数
		int pageRows=Integer.parseInt(request.getParameter("rows").toString());
		int page=Integer.parseInt(request.getParameter("page").toString());
		Pager pager = new Pager(page,pageRows);
		//查询条件封装参数
		Map map = new HashMap();
	    map.put("strDate", strDate);
	    map.put("endDate", endDate);
	    map.put("MERCHANT_CODE", MERCHANT_CODE);
	    map.put("merchant_name", merchant_name);
	    map.put("serial", serial);
	    map.put("status", status);
	    Map m = this.financeManager.getReviewTxByMap(pager, map);
	    
	    List listShopBusiness = (List) m.get("list");
	    System.out.println("返回结果列表："+listShopBusiness.size());
	    
	    
	    String[] headers = 
		{"交易时间","商户编号","商户名称","交易流水号","提现金额","手续费","实际到账金额","状态"};
		String[] columns = {"CREATE_TIME","MERCHANT_CODE","MERCHANT_NAME","SERIAL","ORDER_AMT","POUNDAGE","TRAN_AMT","STATUS"};
		response.setContentType("application/msexcel"); // 设置返回的文件类型
		response.setHeader("Content-Disposition", "attachment;filename="
				+ java.net.URLEncoder.encode("提现审核.xls", "UTF-8")+"");
		OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
		ExportExcelStyleAndData.exportExcel(headers, columns, listShopBusiness, toClient); 		
		//toClient.write(imgByte); // 输出数据
		toClient.close();
	}
	
	/**
     * 跳转界面---审核
     * @return
     */
	@RequestMapping(value = "/reviewFwd")
	public String reviewFoward(HttpServletRequest request,ModelMap map) {
		System.out.println("跳转界面==================审核");
		String id = request.getParameter("id");
		System.out.println("ID:"+id);
		ShopBusiness shopBusiness= this.financeManager.getShopBusinessByID(id);
		
		map.addAttribute("shopBusiness",shopBusiness);
		return "/finance/review";
	}
	
	/**
     * 财务管理---审核
     * @return
     */
	@RequestMapping(value = "/review")
	public String reviewTx(HttpServletRequest request) {
		System.out.println("财务管理==================审核");
		String id = request.getParameter("id");
		this.financeManager.updateShopBusinessByID(id);
		return "/finance/txReviewQuery";
	}
	
	/**
     * 跳转界面---提现批次处理
     * @return
     */
	@RequestMapping(value = "/batchFowd")
	public String txBatchFoward() {
		System.out.println("跳转界面==================提现批次处理");	
		return "/finance/txBatchManage";
	}
	
	/**
	 * 财务管理---提现批次处理
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/txBatch")
	@ResponseBody
	public Map txBatchManage(HttpServletRequest request) throws Exception {
		System.out.println("财务管理==================提现批次处理");
		Map<String, Object> result = new HashMap<String, Object>(2);
		String strDate = (String) request.getParameter("strDate");//交易开始时间
		String endDate = (String) request.getParameter("endDate");//交易结束时间
		String MERCHANT_CODE = (String) request.getParameter("MERCHANT_CODE");//商户编号
		String merchant_name = (String) request.getParameter("merchant_name");//商户名称
		String serial = (String) request.getParameter("serial");//交易流水号
		String business_result =  (String) request.getParameter("business_result");//交易状态
		logger.info("strDate:"+strDate);
		logger.info("endDate:"+endDate);
		logger.info("MERCHANT_CODE:"+MERCHANT_CODE);
		logger.info("merchant_name:"+merchant_name);
		logger.info("serial:"+serial);
		logger.info("business_result:"+business_result);
		
		//获取分页数据参数
		int pageRows=Integer.parseInt(request.getParameter("rows").toString());
		int page=Integer.parseInt(request.getParameter("page").toString());
		Pager pager = new Pager(page,pageRows);
		//查询条件封装参数
		Map map = new HashMap();
	    map.put("strDate", strDate);
	    map.put("endDate", endDate);
	    map.put("MERCHANT_CODE", MERCHANT_CODE);
	    map.put("merchant_name", merchant_name);
	    map.put("serial", serial);
	    map.put("business_result", business_result);
	    Map m = this.financeManager.getBatchTxByMap(pager, map);

	    
	    List<ShopBusiness> listShopBusiness = (List) m.get("list");
	    System.out.println("返回结果列表："+listShopBusiness.size());
	   
	    List list = this.getList(listShopBusiness);
	    
	    result.put("total", m.get("total"));  
        result.put("rows", list);
		return result;
	}
	
	/**
	 * 财务管理---提现批次处理--查看明细
	 * @param request
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/batchDetail")
	public String queryBatchDetail(HttpServletRequest request,ModelMap map){
		
		//批次号
		String batch_number = (String) request.getParameter("batch_number");
		System.out.println("batch_number:"+batch_number);
		

		//获取批次处理明细信息
		List<ShopBusiness> list = this.financeManager.getShopBusinessByBatchID(batch_number);

        map.addAttribute("spBiss", list);
		return "/finance/batchDetail";
		
	}
	
	
	/**
	 * 财务管理---提现批次处理--上送
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/submitAgentpay")
	public void accM(HttpServletRequest request) throws Exception {
		System.out.println("=================================");
		//AgentPayReq a = new AgentPayReq();
		//AgentPayRep ap =  nPayAgentPayService.batchAgentPay(a);
		
		//logger.info(ap.getReturnCode());
	    //this.financeManager.updateCheckError(id);
	}
	
	/**
	 * 提现批次处理导出
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/batchTx_export")
	public void querytxBatchexport(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String strDate = (String) request.getParameter("strDate");//交易开始时间
		String endDate = (String) request.getParameter("endDate");//交易结束时间
		String MERCHANT_CODE = (String) request.getParameter("MERCHANT_CODE");//商户编号
		String merchant_name = (String) request.getParameter("merchant_name");//商户名称
		merchant_name = new String(merchant_name.getBytes("ISO-8859-1"),"UTF-8");
		String batch_number = (String) request.getParameter("batch_number");//批次号
		String business_result =  (String) request.getParameter("business_result");//交易状态
		logger.info("strDate:"+strDate);
		logger.info("endDate:"+endDate);
		logger.info("MERCHANT_CODE:"+MERCHANT_CODE);
		logger.info("merchant_name:"+merchant_name);
		logger.info("batch_number:"+batch_number);
		logger.info("business_result:"+business_result);
		
		//获取分页数据参数
		int pageRows=Integer.parseInt(request.getParameter("rows").toString());
		int page=Integer.parseInt(request.getParameter("page").toString());
		Pager pager = new Pager(page,pageRows);
		//查询条件封装参数
		Map map = new HashMap();
	    map.put("strDate", strDate);
	    map.put("endDate", endDate);
	    map.put("MERCHANT_CODE", MERCHANT_CODE);
	    map.put("merchant_name", merchant_name);
	    map.put("batch_number", batch_number);
	    map.put("business_result", business_result);
	    Map m = this.financeManager.getBatchTxByMap(pager, map);

	   
	    List listShopBusiness = (List) m.get("list");
	    System.out.println("返回结果列表："+listShopBusiness.size());
	    
	    String[] headers = 
		{"商户编号","商户名称","批次号","提交时间","操作员","总金额","总笔数","成功金额","成功笔数","失败金额","失败笔数","状态"};
		String[] columns = {"MERCHANT_CODE","MERCHANT_NAME","BATCH_NUMBER","UPDATE_TIME","OPERATOR","AMT","NUM","SulAmt","SulCnt","FailAmt","FailCnt"
				,"BUSINESS_RESULT"};
		response.setContentType("application/msexcel"); // 设置返回的文件类型
		response.setHeader("Content-Disposition", "attachment;filename="
				+ java.net.URLEncoder.encode("提现批次处理.xls", "UTF-8")+"");
		OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
		ExportExcelStyleAndData.exportExcel(headers, columns, listShopBusiness, toClient); 		
		//toClient.write(imgByte); // 输出数据
		toClient.close();
	}
	
	/**
     * 跳转界面---商户对账单
     * @return
     */
	@RequestMapping(value = "/sellerChkFowd")
	public String sellerChkFoward() {
		System.out.println("跳转界面==================商户对账单");	
		return "/finance/sellerChkBill";
	}
	
	/**
	 * 财务管理---商户对账单--查询
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/sellerChkBill")
	@ResponseBody
	public Map sellerChkBillManage(HttpServletRequest request) throws Exception {
		System.out.println("财务管理==================商户对账单--查询");
		Map<String, Object> result = new HashMap<String, Object>(2);
		String strDate = (String) request.getParameter("strDate");//交易开始时间
		String endDate = (String) request.getParameter("endDate");//交易结束时间
		String MERCHANT_CODE = (String) request.getParameter("MERCHANT_CODE");//商户编号
		String merchant_name = (String) request.getParameter("merchant_name");//商户名称
		String check_result = (String) request.getParameter("check_result");//交易流水号
		String review_result =  (String) request.getParameter("review_result");//交易状态
		logger.info("strDate:"+strDate);
		logger.info("endDate:"+endDate);
		logger.info("MERCHANT_CODE:"+MERCHANT_CODE);
		logger.info("merchant_name:"+merchant_name);
		logger.info("check_result:"+check_result);
		logger.info("review_result:"+review_result);
		
		//获取分页数据参数
		int pageRows=Integer.parseInt(request.getParameter("rows").toString());
		int page=Integer.parseInt(request.getParameter("page").toString());
		Pager pager = new Pager(page,pageRows);
		//查询条件封装参数
		Map map = new HashMap();
	    map.put("strDate", strDate);
	    map.put("endDate", endDate);
	    map.put("MERCHANT_CODE", MERCHANT_CODE);
	    map.put("merchant_name", merchant_name);
	    map.put("check_result", check_result);
	    map.put("review_result", review_result);
	    Map m = this.financeManager.getSellerChkBillByMap(pager, map);
		
	    List<CheckBusiness> listCheckBusiness = (List) m.get("list");
	    System.out.println("返回结果列表："+listCheckBusiness.size());
	    //将list中的PosInfo转成map
	    List list = this.getList(listCheckBusiness);

		result.put("total", m.get("total"));
	    result.put("rows", list);
		return result;
	}
	
	/**
	 * 财务管理---商户对账单--审核
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/dzreview")
	public String dzreview(HttpServletRequest request) throws Exception {
		System.out.println("财务管理==================商户对账单--审核");
		Map<String, Object> result = new HashMap<String, Object>(2);
		String create_time = (String) request.getParameter("create_time1");//对账时间
		String MERCHANT_CODE = (String) request.getParameter("MERCHANT_CODE1");//商户编号
		String check_result = (String) request.getParameter("check_result1");//对账结果
		String review_result = (String) request.getParameter("review_result1");//审核结果
		String reviewPass = (String) request.getParameter("reviewPass");//审核状态
		String reason =  (String) request.getParameter("reason");//原因
		logger.info("create_time:"+create_time);
		logger.info("MERCHANT_CODE:"+MERCHANT_CODE);
		logger.info("check_result:"+check_result);
		logger.info("review_result:"+review_result);
		logger.info("reviewPass:"+reviewPass);
		logger.info("reason:"+reason);


		//查询条件封装参数
		Map map = new HashMap();
	    map.put("create_time", create_time);
	    map.put("MERCHANT_CODE", MERCHANT_CODE);
	    map.put("check_result", check_result);
	    map.put("review_result", review_result);
	    map.put("reviewPass", reviewPass);
	    map.put("reason", reason);
	    
	    this.financeManager.updateCheckBusiness(map);

		return "/finance/sellerChkBill";
	}
	
	/**
	 * 财务管理---商户对账单--导出
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/sellerExportBill")
	public void sellerExportManage(HttpServletRequest request,HttpServletResponse response) throws Exception {
		System.out.println("财务管理==================商户对账单--导出");
		Map<String, Object> result = new HashMap<String, Object>(2);
		String strDate = (String) request.getParameter("strDate");//交易开始时间
		String endDate = (String) request.getParameter("endDate");//交易结束时间
		String MERCHANT_CODE = (String) request.getParameter("MERCHANT_CODE");//商户编号
		String merchant_name = (String) request.getParameter("merchant_name");//商户名称
		merchant_name = new String(merchant_name.getBytes("ISO-8859-1"),"UTF-8");
		String check_result = (String) request.getParameter("check_result");//交易流水号
		String review_result =  (String) request.getParameter("review_result");//交易状态
		logger.info("strDate:"+strDate);
		logger.info("endDate:"+endDate);
		logger.info("MERCHANT_CODE:"+MERCHANT_CODE);
		logger.info("merchant_name:"+merchant_name);
		logger.info("check_result:"+check_result);
		logger.info("review_result:"+review_result);
		
		//获取分页数据参数
		int pageRows=Integer.parseInt(request.getParameter("rows").toString());
		int page=Integer.parseInt(request.getParameter("page").toString());
		
		logger.info("pageRows:"+pageRows);
		logger.info("page:"+page);
		Pager pager = new Pager(page,pageRows);
		//查询条件封装参数
		Map map = new HashMap();
	    map.put("strDate", strDate);
	    map.put("endDate", endDate);
	    map.put("MERCHANT_CODE", MERCHANT_CODE);
	    map.put("merchant_name", merchant_name);
	    map.put("check_result", check_result);
	    map.put("review_result", review_result);
	    Map m = this.financeManager.getSellerChkBillByMap(pager, map);
		
	    List listCheckBusiness = (List) m.get("list");
	    logger.info("返回结果列表："+listCheckBusiness.size());
	    
	    
	    for(Object obj:listCheckBusiness){
	    	CheckBusiness checkBusiness = (CheckBusiness) obj;
	    	String wetAmt = checkBusiness.getWetAmt()==null?"0":checkBusiness.getWetAmt();
	    	String zfbAmt = checkBusiness.getZfbAmt()==null?"0":checkBusiness.getZfbAmt();
	    	String cardAmt = checkBusiness.getCardAmt()==null?"0":checkBusiness.getCardAmt();
	    	checkBusiness.setWetAmt(wetAmt+"/"+checkBusiness.getWetNum());
	    	checkBusiness.setZfbAmt(zfbAmt+"/"+checkBusiness.getZfbNum());
	    	checkBusiness.setCardAmt(cardAmt+"/"+checkBusiness.getCardNum());
	    	
	    }
	    String[] headers = 
		{"对账时间","商户编号","商户名称","订单金额(元)","手续费","优惠金额(元)","结算金额(元)","微信收款(元)/笔数","支付宝收款(元)/笔数","银行收款(元)/笔数"
	    		,"对账结果","审核结果"};
		String[] columns = {"CREATE_TIME","MERCHANT_CODE","MERCHANT_NAME","SumTranAmt","SumPound","YhAmt","SumOrderAmt","WetAmt","ZfbAmt",
				"CardAmt","CHECK_RESULT","REVIEW_RESULT"};
		response.setContentType("application/msexcel"); // 设置返回的文件类型
		response.setHeader("Content-Disposition", "attachment;filename="
				+ java.net.URLEncoder.encode("商户对账单.xls", "UTF-8")+"");
		OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
		ExportExcelStyleAndData.exportExcel(headers, columns, listCheckBusiness, toClient); 		
		//toClient.write(imgByte); // 输出数据
		toClient.close();
	    

	}
	/**
     * 跳转界面---差错账处理
     * @return
     */
	@RequestMapping(value = "/accManageFowd")
	public String accManageFoward() {
		System.out.println("跳转界面==================差错账处理");	
		return "/finance/accManage";
	}
	
	/**
	 * 财务管理--差错账处理--查询
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/accBillManage")
	@ResponseBody
	public Map accBillManageManage(HttpServletRequest request) throws Exception {
		System.out.println("财务管理==================差错账处理");
		Map<String, Object> result = new HashMap<String, Object>(2);
		String strDate = (String) request.getParameter("strDate");//交易开始时间
		String endDate = (String) request.getParameter("endDate");//交易结束时间
		String MERCHANT_CODE = (String) request.getParameter("MERCHANT_CODE");//商户编号
		String merchant_name = (String) request.getParameter("merchant_name");//商户名称
		String serial = (String) request.getParameter("serial");//交易流水号
		String check_result = (String) request.getParameter("check_result");//对账结果
		String review_result =  (String) request.getParameter("review_result");//处理结果
		logger.info("strDate:"+strDate);
		logger.info("endDate:"+endDate);
		logger.info("MERCHANT_CODE:"+MERCHANT_CODE);
		logger.info("merchant_name:"+merchant_name);
		logger.info("serial:"+serial);
		logger.info("check_result:"+check_result);
		logger.info("review_result:"+review_result);
		
		//获取分页数据参数
		int pageRows=Integer.parseInt(request.getParameter("rows").toString());
		int page=Integer.parseInt(request.getParameter("page").toString());
		Pager pager = new Pager(page,pageRows);
		//查询条件封装参数
		Map map = new HashMap();
	    map.put("strDate", strDate);
	    map.put("endDate", endDate);
	    map.put("MERCHANT_CODE", MERCHANT_CODE);
	    map.put("merchant_name", merchant_name);
	    map.put("serial", serial);
	    map.put("check_result", check_result);
	    map.put("review_result", review_result);
	    Map m = this.financeManager.getAccManageByMap(pager, map);
       
	    List<CheckError> listCheckError = (List) m.get("list");
	    logger.info("返回结果列表："+listCheckError.size());
	    //将list中的PosInfo转成map
	    List list = this.getList(listCheckError);

		result.put("total", m.get("total"));
	    result.put("rows", list);
		return result;
	}

	
	/**
	 * 财务管理---差错账处理--处理
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/accManage")
	public void accManage(HttpServletRequest request) throws Exception {
		System.out.println("财务管理==================差错账处理--处理");
		Map<String, Object> result = new HashMap<String, Object>(2);
		String id = (String) request.getParameter("id");//主键
        logger.info("id:"+id);
	    
	    //this.financeManager.updateCheckError(id);
	}
	/**
	 * 财务管理---差错账处理--导出
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/accExport")
	public void accExportManage(HttpServletRequest request,HttpServletResponse response) throws Exception {
		System.out.println("财务管理==================差错账处理--导出");
		Map<String, Object> result = new HashMap<String, Object>(2);
		String strDate = (String) request.getParameter("strDate");//交易开始时间
		String endDate = (String) request.getParameter("endDate");//交易结束时间
		String MERCHANT_CODE = (String) request.getParameter("MERCHANT_CODE");//商户编号
		String merchant_name = (String) request.getParameter("merchant_name");//商户名称
		merchant_name = new String(merchant_name.getBytes("ISO-8859-1"),"UTF-8");
		String serial = (String) request.getParameter("serial");//交易流水号
		String check_result = (String) request.getParameter("check_result");//对账结果
		String review_result =  (String) request.getParameter("review_result");//处理结果
		logger.info("strDate:"+strDate);
		logger.info("endDate:"+endDate);
		logger.info("MERCHANT_CODE:"+MERCHANT_CODE);
		logger.info("merchant_name:"+merchant_name);
		logger.info("serial:"+serial);
		logger.info("check_result:"+check_result);
		logger.info("review_result:"+review_result);
		
		//获取分页数据参数
		int pageRows=Integer.parseInt(request.getParameter("rows").toString());
		int page=Integer.parseInt(request.getParameter("page").toString());
		Pager pager = new Pager(page,pageRows);
		//查询条件封装参数
		Map map = new HashMap();
	    map.put("strDate", strDate);
	    map.put("endDate", endDate);
	    map.put("MERCHANT_CODE", MERCHANT_CODE);
	    map.put("merchant_name", merchant_name);
	    map.put("serial", serial);
	    map.put("check_result", check_result);
	    map.put("review_result", review_result);
	    Map m = this.financeManager.getAccManageByMap(pager, map);
       

		
	    List listCheckBusiness = (List) m.get("list");
	    logger.info("返回结果列表："+listCheckBusiness.size());
	    
	    String[] headers = 
		{"商户编号","商户名称","流水号","交易时间","账号","收款渠道","交易金额(元)","终端号"
	    		,"对账结果","审核结果"};
		String[] columns = {"MERCHANT_CODE","MERCHANT_NAME","SERIAL","SETTLEMENT_DATE","CARD_NUMBER","BUSINESS_TYPE","TRAN_AMT","CLIENT_ID",
				"CHECK_RESULT","REVIEW_RESULT"};
		response.setContentType("application/msexcel"); // 设置返回的文件类型
		response.setHeader("Content-Disposition", "attachment;filename="
				+ java.net.URLEncoder.encode("差错账处理.xls", "UTF-8")+"");
		OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
		ExportExcelStyleAndData.exportExcel(headers, columns, listCheckBusiness, toClient); 		
		//toClient.write(imgByte); // 输出数据
		toClient.close();
	    

	}
	
	
	
}
