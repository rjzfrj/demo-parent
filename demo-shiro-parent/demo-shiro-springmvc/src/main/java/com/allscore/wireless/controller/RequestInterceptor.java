package com.allscore.wireless.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class RequestInterceptor extends HandlerInterceptorAdapter{
	    private final Logger log = LoggerFactory.getLogger(RequestInterceptor.class); 
	    
	    private HttpSession httpSession;
 
	    public boolean preHandle(HttpServletRequest request,    
	            HttpServletResponse response, Object handler) throws Exception {    
	        if ("GET".equalsIgnoreCase(request.getMethod())) {  
	            //RequestUtil.saveRequest();  
	        }  
	        log.info("==============执行顺序: 1、preHandle================");    
	        String requestUri = request.getRequestURI();  
	        String contextPath = request.getContextPath();  
	        String url = requestUri.substring(contextPath.length());  
	        
	        log.info("requestUri:"+requestUri);    
	        log.info("contextPath:"+contextPath);    
	        log.info("url:"+url); 
	        
	        //若30分钟未操作，此处获取的session为新的session，loginSession为空，登陆时会获取创建session并赋值
	        httpSession = request.getSession();
	   	    String loginSession = (String) httpSession.getAttribute("loginSession");
	   	    if(loginSession!=null && !"".equals(loginSession)){
	   	    	return true; 
	   	    }else{
	   	    	response.setCharacterEncoding("UTF-8");  
	   			response.setContentType("text/html; charset=utf-8");  
	   			PrintWriter out = null;   
	   			out = response.getWriter();
	   			out.print("<script>alert('会话超时,请重新登录');parent.location.href='../index.jsp';</script>");
	   			
	   			out.flush();
		        if (out != null) {  
		            out.close();  
		        } 
	   	    	return false;
	   	    	
	   	    }
	              
	    }
	    
	    public void postHandle(HttpServletRequest request,    
	            HttpServletResponse response, Object handler,    
	            ModelAndView modelAndView) throws Exception {     
	        log.info("==============执行顺序: 2、postHandle================");   
	    } 
	    
	    public void afterCompletion(HttpServletRequest request,    
	            HttpServletResponse response, Object handler, Exception ex)    
	            throws Exception {    
	        log.info("==============执行顺序: 3、afterCompletion================");    
	    }
	    

}
