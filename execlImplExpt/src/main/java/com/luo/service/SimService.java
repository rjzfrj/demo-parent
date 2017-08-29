package com.luo.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.luo.domain.SSim;

public interface SimService {

	/**
	 * 
	 * @Title: importExcelInfo
	 * @param @param InputStream,MultipartFile
	 * @param @return      
	 * @Description:导入excel插入数据
	 */
	List<SSim> importExcelInfo(InputStream in, MultipartFile file)  throws Exception;
	
}
