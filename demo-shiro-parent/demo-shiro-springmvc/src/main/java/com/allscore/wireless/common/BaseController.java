package com.allscore.wireless.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import sun.misc.BASE64Encoder;



public class BaseController {

	private void setParameter(HttpServletRequest request, ModelMap map) {
		Map<String, String> params = new HashMap();
		Set<Entry<String, String[]>> set = request.getParameterMap().entrySet();
		Iterator<Entry<String, String[]>> it = set.iterator();
		while (it.hasNext()) {
			Entry<String, String[]> entry = it.next();
			//忽略系统关键字
			if(entry.getKey().charAt(0)=='_')
				continue;
			String[] val = entry.getValue();
			if (null != val && val.length == 1) {
				params.put(entry.getKey(), val[0]);
			}
		}
		if(!params.isEmpty())
			map.addAttribute("_params", params);
	}

	protected void setPager(HttpServletRequest request, Pager pager, ModelMap map) {
		setParameter(request, map);
		map.addAttribute("_pager", pager);
	}

	protected Pager getPager(HttpServletRequest request) {
		int iPage = 1;
		int iSize = 10;
		try {
			String page = request.getParameter("_page");
			String size = request.getParameter("_size");
			if (null != page)
				iPage = Integer.parseInt(page);
			if (null != size)
				iSize = Integer.parseInt(size);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new Pager(iPage, iSize);
	}
	
	protected void setDelSuccess(RedirectAttributesModelMap map) {
		map.addFlashAttribute("_tip", "删除成功");
	}
	
	protected void setAddSuccess(RedirectAttributesModelMap map) {
		map.addFlashAttribute("_tip", "新增成功");
	}
	
	protected void setEditSuccess(RedirectAttributesModelMap map) {
		map.addFlashAttribute("_tip", "修改成功");
	}
	protected void setPageTip(RedirectAttributesModelMap map,String tip) {
		map.addFlashAttribute("_tip", tip);
	}
	
	public  Map getMap(Object obj) throws Exception {
		Map map = new HashMap();
		Field[] fields = obj.getClass().getDeclaredFields();

		for (Field v : fields) {
		  v.setAccessible(true);
		  String fieldName = v.getName().toLowerCase();
		  
		  map.put(fieldName, v.get(obj));
        }

		return map;
	}
	
	public List getList(List list) throws Exception {
		List arrList = new ArrayList();
		Field[] fields=null;
		
		for(int i=0;i<list.size();i++){
			if(fields==null){
				fields = list.get(i).getClass().getDeclaredFields();//获取对象属性
			}
			Map map = new HashMap();
			for (Field v : fields) {
			  v.setAccessible(true);
			  String fieldName = v.getName().toLowerCase();//将Key值转成小写
			  map.put(fieldName, v.get(list.get(i)));
			  
	        }
		    arrList.add(map);
		}

		return arrList;
	}
	
	
	public static Workbook createworkbook(InputStream inp) throws IOException,InvalidFormatException {

		if (!inp.markSupported()) {
		  inp = new PushbackInputStream(inp, 8);
		}
		if (POIFSFileSystem.hasPOIFSHeader(inp)) {
		  return new HSSFWorkbook(inp);
		}
		if (POIXMLDocument.hasOOXMLHeader(inp)) {
		  return new XSSFWorkbook(OPCPackage.open(inp));
		}
		  throw new IllegalArgumentException("你的excel版本目前poi解析不了");

		}
	public static Object getUserBase(HttpSession httpSession) throws Exception{
		 Object obj =  httpSession.getAttribute("currentUser");
		 return obj;
	}
	
	public String EncoderByMd5(String str) throws Exception{
		 //确定计算方法
		 MessageDigest md5=MessageDigest.getInstance("MD5");
		 BASE64Encoder base64en = new BASE64Encoder();
		 //加密后的字符串
		 String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
		 return newstr;
    }
	
}
