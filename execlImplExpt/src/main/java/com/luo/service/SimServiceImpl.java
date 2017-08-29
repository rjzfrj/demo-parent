package com.luo.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tools.ExcelUtil;

import com.luo.dao.SSimDao;
import com.luo.domain.SSim;

@Service
public class SimServiceImpl implements SimService {

	@Autowired
	private SSimDao sSimDao;

//-------------------------------------导入Excel新增sim卡------------------------------------
	public List<SSim> importExcelInfo(InputStream in, MultipartFile file)
			throws Exception {
		 List<List<Object>> listob = ExcelUtil.getBankListByExcel(in,file.getOriginalFilename());
		 //for (int i = 1; i < listob.size()-1; i++) {
		 for (int i = 1; i < listob.size(); i++) {
	    	 //List<Object> ob = listob.get(i+1);
			 List<Object> ob = listob.get(i);
	    	 SSim sSim = new SSim();
	    	 if(ob.get(1)!=null){
	    		 sSim.setCcid(String.valueOf(ob.get(1))); 
	    	 }
	    	 if(ob.get(0)!=null){
	    		 String str1=ob.get(0).toString();
	    		 String str2 = str1.substring(0,str1.length()-3);
	    		 sSim.setSim(str2);
	    	 }
	    	 if(ob.get(2)!=null){
	    		 sSim.setImsi(String.valueOf(ob.get(2)));
	    	 }
	    	 if(ob.get(3)!=null){
	    		 sSim.setStatus(String.valueOf(ob.get(3)));
	    	 }
	    	 if(ob.get(4)!=null){
	    		 sSim.setCombo(String.valueOf(ob.get(4)));
	    	 }
	    	 if(ob.get(5)!=null){
	    		 sSim.setName(String.valueOf(ob.get(5)));
	    	 }
	    	 if(ob.get(6)!=null){
	    		 sSim.setType(String.valueOf(ob.get(6)));
	    	 }
	    	 if(ob.get(7)!=null){
	    		 sSim.seteId(String.valueOf(ob.get(7)));
	    	 }
	    	 if(ob.get(10)!=null){
	    		 sSim.setRemark(String.valueOf(ob.get(10)));
	    	 }
	    	 sSimDao.deleteByPrimaryKey(sSim.getCcid());
	    	 sSimDao.insertSelective(sSim);
	     }
		 List<SSim> sSims = sSimDao.selectAllSSims();
		 return sSims;
	}
	
}


