package com.allscore.wireless.controller;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.allscore.wireless.biz.PosManager;
import com.allscore.wireless.common.BaseController;
import com.allscore.wireless.common.DateUtil;
import com.allscore.wireless.common.ExportExcelStyle;
import com.allscore.wireless.common.ExportExcelStyleAndData;
import com.allscore.wireless.common.Pager;
import com.allscore.wireless.common.TripleDES;
import com.allscore.wireless.dao.MemberPublic;
import com.allscore.wireless.dao.PosInfo;
/**
 * POS机管理
 * @author dell
 *
 */
@Controller
@RequestMapping("/pos")
public class PosController extends BaseController {

	private static final Logger logger = Logger.getLogger(PosController.class);

	@Autowired
	private PosManager posManager;

    /**
     * 跳转界面---pos机入库
     * @return
     */
	@RequestMapping(value = "/posrkFowd")
	public String posrkFowd() {
		System.out.println("跳转界面==================pos机入库");	
		return "/pos/posrk";
	}
	/**
	 * pos机管理---pos机入库查询
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/posrk")
	@ResponseBody
	public Map posrk(HttpServletRequest request) throws Exception {
		System.out.println("pos机管理==================pos机入库");
		Map<String, Object> result = new HashMap<String, Object>(2);
		String pos_sn = (String) request.getParameter("pos_sn");//sn码
		String factory_name = (String) request.getParameter("factory_name");//厂家
		String pos_model = (String) request.getParameter("pos_model");//型号
		String communication = (String) request.getParameter("communication");//通讯模式
		System.out.println("pos_sn:"+pos_sn);
		System.out.println("factory_name:"+factory_name);
		System.out.println("pos_model:"+pos_model);
		System.out.println("communication:"+communication);
		
		//获取分页数据参数
		int pageRows=Integer.parseInt(request.getParameter("rows").toString());
		int page=Integer.parseInt(request.getParameter("page").toString());
		Pager pager = new Pager(page,pageRows);
		//查询条件封装参数
		Map map = new HashMap();
	    map.put("pos_sn", pos_sn);
	    map.put("factory_name", factory_name);
	    map.put("pos_model", pos_model);
	    map.put("communication", communication);
	    Map m = this.posManager.getPosInfoRkByMap(pager, map);
		
	    List<PosInfo> listPosInfo = (List) m.get("list");
	    System.out.println("返回结果列表："+listPosInfo.size());
	    //将list中的PosInfo转成map
	    List list = this.getList(listPosInfo);

		result.put("total", m.get("total"));
	    result.put("rows", list);
		return result;
	}
	
	/**
	 * pos机入库信息导入
	 * @param request
	 * @return
	 * @throws Exception 
	 * @throws IOException 
	 * @throws BiffException 
	 */
	@RequestMapping(value = "/posrk_import")
	public void posrk_import(HttpServletRequest request,@RequestParam("file") MultipartFile file,
			ModelMap map,HttpServletResponse response) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		// 从输入流创建Workbook读取excel数据表

		String fileName = file.getOriginalFilename();
		long fileSize = (long) file.getSize();
		System.out.println("文件名：" + fileName);
		System.out.println("文件大小：" + fileSize);
		
		response.setCharacterEncoding("UTF-8");  
		response.setContentType("text/html; charset=utf-8");  
		PrintWriter out = null;   
		out = response.getWriter();
		try {
			
			InputStream is = file.getInputStream();

			// POIFSFileSystem fs = new POIFSFileSystem(is);
			Workbook wb = this.createworkbook(is);

			Sheet sheet = (Sheet) wb.getSheetAt(0);

			// 得到总行数,行默认从0开始
			int rowNum = sheet.getLastRowNum() + 1;

			Row row = sheet.getRow(0);
			int colNum = row.getPhysicalNumberOfCells();
			int colnum1 = row.getLastCellNum();
			System.out.println("colNum:" + colNum);
			System.out.println("colnum1:" + colnum1);
			System.out.println("rowNum:" + rowNum);

			// pos对象信息列表
			List<PosInfo> listPos = new ArrayList();
			for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
				row = sheet.getRow(i);
				// pos对象
				PosInfo posInfo = new PosInfo();
				// 单元格内容
				List<String> list = new ArrayList();
				for (int j = 0; j < colNum; j++) {
					Cell content = row.getCell(j);
					if (!"".equals(content) && content != null) {
						System.out.println("content.toString():" + content);
						list.add(content.toString().trim());
					} else {
						list.add("");
					}

				}
				// pos对象赋值
				// posInfo.setMERCHANT_CODE(list.get(0).toString());
				posInfo.setPOS_SN(list.get(0).toString());
				posInfo.setPOS_MODEL(list.get(1).toString());
				posInfo.setFACTORY_NAME(list.get(2).toString());
				posInfo.setCOMMUNICATION(list.get(3).toString());
				posInfo.setREMARK(list.get(4).toString());
				// posInfo.setSTATUS(list.get(5).toString());
				posInfo.setSTATUS("00");
				posInfo.setCREATE_TIME(DateUtil.getFormatDate());
				posInfo.setUPDATE_TIME(DateUtil.getFormatDate());
				posInfo.setPOS_TLK(TripleDES.tlk);

				String tmkPlainText = TripleDES.getDesKey();
				posInfo.setPOS_TMK(TripleDES.tmk(tmkPlainText));
				posInfo.setPOS_PIK(TripleDES.pik(tmkPlainText));
				posInfo.setPOS_MAK(TripleDES.mak(tmkPlainText));
				posInfo.setPOS_TRK(TripleDES.trk(tmkPlainText));

				System.out.println("=============================");
				for (int k = 0; k < list.size(); k++) {
					System.out.println("---" + list.get(k));
				}
				System.out.println("=============================");
				listPos.add(posInfo);

			}
			int num = this.posManager.addPosInfoByList(listPos);
			//Map map = new HashMap();
			map.put("sucNum", num);
			map.put("failNum", listPos.size()-num);
			//if(num >0){
				//map.put("errmsg", "success");
				//return "success";
			//}else{
				//map.put("errmsg", "fail");
				//String ret = JSON.toJSONString(map);
				//return "fail";
			//}
			//String ret = JSON.toJSONString(map);
			//JSONObject responseJSONObject = JSONObject.fromObject(ret);  
			
	        //out.append(responseJSONObject.toString());
	        //String errmsg 
	        if(num>0){
	        	int count = listPos.size()-num;
	        	out.print("<script>alert('成功条数"+num+",失败条数："+count+"')</script>");
	        	
	        }else{
	        	out.print("<script>alert('导入失败，数据已存在')</script>");
	        	
	        }
	        out.print("<script>window.location.href='posrkFowd'</script>");
	        out.flush();
	        if (out != null) {  
	            out.close();  
	        } 
	       
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			out.print("<script>alert('导入异常')</script>");
			out.print("<script>window.location.href='posrkFowd'</script>");
			out.flush();
	        if (out != null) {  
	            out.close();  
	        } 
			//String ret = JSON.toJSONString(map);
			//return "fail";
		}
		//return "/pos/posrkFowd";
		
	}
	
	/**
	 * pos机入库信息导出
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/posrk_export")
	public void posrk_export(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		
		
		String pos_sn = (String) request.getParameter("pos_sn");//sn码
		String factory_name = (String) request.getParameter("factory_name");//厂家
		factory_name = new String(factory_name.getBytes("ISO-8859-1"),"UTF-8");
		String pos_model = (String) request.getParameter("pos_model");//型号
		String communication = (String) request.getParameter("communication");//通讯模式
		System.out.println("pos_sn:"+pos_sn);
		System.out.println("factory_name:"+factory_name);
		System.out.println("pos_model:"+pos_model);
		System.out.println("communication:"+communication);

		int pageRows=Integer.parseInt(request.getParameter("rows").toString());
		int page=Integer.parseInt(request.getParameter("page").toString());
		Pager pager = new Pager(page,pageRows);
		//查询条件封装参数
		Map map = new HashMap();
	    map.put("pos_sn", pos_sn);
	    map.put("factory_name", factory_name);
	    map.put("pos_model", pos_model);
	    map.put("communication", communication);
	    Map m = this.posManager.getPosInfoRkByMap(pager, map);
	    
		List listPosInfo = (List) m.get("list");
		System.out.println("导出列表数据："+listPosInfo.size());
		/*for(Object obj:listPosInfo){
		    PosInfo psosInfo =(PosInfo)obj;
		    String posStatus = psosInfo.getSTATUS();
		    if("00".equals(posStatus)){
		    	psosInfo.setSTATUS("未分配");
		    }else if("01".equals(posStatus)){
		    	psosInfo.setSTATUS("已分配");
		    }
			
		}*/
		String[] headers = 
		{"POS机设备序列号","厂家","POS机型号","通讯模式","备注"};
		String[] columns = {"POS_SN","FACTORY_NAME","POS_MODEL","COMMUNICATION","REMARK"};
		response.setContentType("application/msexcel"); // 设置返回的文件类型
		response.setHeader("Content-Disposition", "attachment;filename="
				+ java.net.URLEncoder.encode("pos机入库信息.xls", "UTF-8")+"");
		OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
		ExportExcelStyleAndData.exportExcel(headers, columns, listPosInfo, toClient); 		
		//toClient.write(imgByte); // 输出数据
		toClient.close();

	}
	
	/**
	 * pos机入库信息模板下载
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/posrk_style")
	public void posrk_style(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String, Object> result = new HashMap<String, Object>();
		String[] headers = 
		{"POS机设备序列号","POS机型号","厂家","通讯模式","备注"};

		response.setContentType("application/msexcel"); // 设置返回的文件类型
		response.setHeader("Content-Disposition", "attachment;filename="
				+ java.net.URLEncoder.encode("pos机入库模板.xls", "UTF-8")+"");
		OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
	    ExportExcelStyle.exportExcel(headers,toClient); 		
		//toClient.write(imgByte); // 输出数据
		toClient.close();	
	}

	 /**
     * 跳转界面---pos机出库
     * @return
     */
	@RequestMapping(value = "/posckFowd")
	public String posckFowd() {
		System.out.println("跳转界面==================pos机出库");	
		return "/pos/posck";
	}
	/**
	 * pos机管理---pos机出库
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/posck")
	@ResponseBody
	public Map posck(HttpServletRequest request) throws Exception {
		System.out.println("pos机管理==================pos机出库");
		Map<String, Object> result = new HashMap<String, Object>(2);
		String pos_sn = (String) request.getParameter("pos_sn");//sn码
		String factory_name = (String) request.getParameter("factory_name");//厂家
		String pos_model = (String) request.getParameter("pos_model");//型号
		String status = (String) request.getParameter("status");//pos机状态
		String merchantCode = (String) request.getParameter("merchantCode");//商户编号
		System.out.println("pos_sn:"+pos_sn);
		System.out.println("factory_name:"+factory_name);
		System.out.println("pos_model:"+pos_model);
		System.out.println("status:"+status);
		System.out.println("merchantCode:"+merchantCode);
		
		//获取分页数据参数
		int pageRows=Integer.parseInt(request.getParameter("rows").toString());
		int page=Integer.parseInt(request.getParameter("page").toString());
		Pager pager = new Pager(page,pageRows);
		//查询条件封装参数
		Map map = new HashMap();
	    map.put("pos_sn", pos_sn);
	    map.put("factory_name", factory_name);
	    map.put("pos_model", pos_model);
	    map.put("status", status);
	    map.put("merchantCode", merchantCode);
	    Map m = this.posManager.getPosInfoCkByMap(pager, map);
	    
		
	    List<PosInfo> listPosInfo = (List) m.get("list");
	    System.out.println("返回结果列表："+listPosInfo.size());
	    //将list中的PosInfo转成map
	    List list = this.getList(listPosInfo);

		result.put("total", m.get("total"));
	    result.put("rows", list);
		return result;
	}
	
	/**
	 * pos机出库信息导出
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/posck_export")
	public void posck_export(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		
		String pos_sn = (String) request.getParameter("pos_sn");//sn码
		String factory_name = (String) request.getParameter("factory_name");//厂家
		factory_name = new String(factory_name.getBytes("ISO-8859-1"),"UTF-8");
		String pos_model = (String) request.getParameter("pos_model");//型号
		String status = (String) request.getParameter("status");//pos机状态
		System.out.println("pos_sn:"+pos_sn);
		System.out.println("factory_name:"+factory_name);
		System.out.println("pos_model:"+pos_model);
		System.out.println("status:"+status);

		int pageRows=Integer.parseInt(request.getParameter("rows").toString());
		int page=Integer.parseInt(request.getParameter("page").toString());
		Pager pager = new Pager(page,pageRows);
		//查询条件封装参数
		Map map = new HashMap();
	    map.put("pos_sn", pos_sn);
	    map.put("factory_name", factory_name);
	    map.put("pos_model", pos_model);
	    map.put("status", status);
	    Map m = this.posManager.getPosInfoCkByMap(pager, map);
	    
		
	    List listPosInfo = (List) m.get("list");
		System.out.println("导出列表数据："+listPosInfo.size());
		for(Object obj:listPosInfo){
		    PosInfo psosInfo =(PosInfo)obj;
		    String posStatus = psosInfo.getSTATUS();
		    if("00".equals(posStatus)){
		    	psosInfo.setSTATUS("未分配");
		    }else if("01".equals(posStatus)){
		    	psosInfo.setSTATUS("已分配");
		    }
			
		}
		String[] headers = 
		{"POS机设备序列号","商户名称","厂家","POS机型号","POS状态","更新时间"};
		String[] columns = {"POS_SN","MERCHANT_NAME","FACTORY_NAME","POS_MODEL","STATUS","UPDATE_TIME"};

		response.setContentType("application/msexcel"); // 设置返回的文件类型
		response.setHeader("Content-Disposition", "attachment;filename="
				+ java.net.URLEncoder.encode("pos机出库信息.xls", "UTF-8")+"");
		OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
		ExportExcelStyleAndData.exportExcel(headers, columns, listPosInfo, toClient); 		
		//toClient.write(imgByte); // 输出数据
		toClient.close();

	}
	

	/**
	 * pos机出库信息--加载动态下拉框
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/merchantName_List")
	@ResponseBody
	public List merchantName_List(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = new Pager();
		//查询条件封装参数
		Map map = new HashMap();
		List<MemberPublic> listMemberPublic = this.posManager.getMerchantNameByList(pager, map);
		System.out.println("获取下拉框商家数:"+listMemberPublic.size());
		List list = this.getList(listMemberPublic);
		//json格式返回，需要封装在map集合中
		
		return list;

	}
	
	/**
	 * pos机出库信息--批量分配/修改
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/batch_fp")
	@ResponseBody
	public String batch_fp(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		String merchantId = request.getParameter("merchantId");
		String batchValue = request.getParameter("batchValue");
		String operateType = request.getParameter("operateType");
		String[] strId = batchValue.split(",");
		
		System.out.println("商户编号："+merchantId);
		System.out.println("pos机编号："+batchValue);
		logger.info("操作类型："+operateType);
		
		
		Pager pager = new Pager();
		//查询条件封装参数
		Map map = new HashMap();
		map.put("merchantId", merchantId);
		map.put("strId", strId);
	    this.posManager.updatePosInfoFpByList(map);
	    if("xg".equals(operateType)){
	    	return "xgsuccess";
	    }
	    if("fp".equals(operateType)){
	    	return "fpsuccess";
	    }
		return "";
	}
	
	/**
	 * pos机出库信息--批量解除绑定
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/batch_jc")
	@ResponseBody
	public String batch_jc(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		String batchValue = request.getParameter("batchValue");
		String[] strId = batchValue.split(",");
		
		Pager pager = new Pager();
		//查询条件封装参数
		Map map = new HashMap();
		map.put("strId", strId);
	    int cnt = this.posManager.updatePosInfoJcByList(map);
	    if(cnt>0){
	    	return "success";
	    }else{
	    	return "";
	    }
	}
	
	/**
	 * pos机出库信息 -----删除
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/pos_del",method = RequestMethod.POST)
	@ResponseBody
	public String pos_del(@RequestParam("id") String id) throws IOException {
		System.out.println("pos机出库信息=====================删除");

		int cnt = this.posManager.delPosInfoById(id);
		System.out.println("删除记录数："+cnt);
		if(cnt>0){
		  //跳转到查询界面
		  return "success";
		}else{
		  return "";
		}
		
	}
}
