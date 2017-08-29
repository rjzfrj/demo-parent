package com.luo.controller;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.luo.domain.SSim;
import com.luo.service.SimService;

/**
 * 
 * @ClassName: LoginController   
 * @Description: 登录controller
 * @Description:跳转到登录界面
 */
@Controller
@RequestMapping("/login")
public class LoginController{
	
	@Autowired
	private SimService simService;
	
	@RequestMapping("/toImportExcel")
	public ModelAndView toLoginPage(){
		ModelAndView mv = new ModelAndView("importSimExcel");
		return mv;
	}
	
	@RequestMapping(value="/importSimExcel", method=RequestMethod.POST)
	public ModelAndView toImportExcel(@RequestParam("file") MultipartFile file) throws Exception{
		ModelAndView mav = new ModelAndView();
		if(!file.isEmpty()){
			//这里将上传得到的文件保存至 d:\\temp\\file 目录
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File("d:\\temp\\file\\", 
					System.currentTimeMillis()+ file.getOriginalFilename()));
			InputStream in = file.getInputStream();
		    //数据导入
			List<SSim> sSims = simService.importExcelInfo(in,file);
			mav.addObject("sSims", sSims);
			mav.setViewName("success");
		    in.close();
		    return mav;
		}
	    mav.setViewName("error");
	    return mav;
	}
}