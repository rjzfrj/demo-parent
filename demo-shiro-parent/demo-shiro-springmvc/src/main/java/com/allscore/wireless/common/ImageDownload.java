package com.allscore.wireless.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


public class ImageDownload extends HttpServlet {
	

	private Logger logger = Logger.getLogger(this.getClass());
	
	public ImageDownload() {
		super();
	}
    
	public void destroy(){
		super.destroy();
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)
	  throws ServletException,IOException{
		//ServletOutputStream outputStream = null;
		PrintWriter out = null;
		String id = request.getParameter("id");
		System.out.println("id = "+id);
		String type = request.getParameter("type");
		request.getRequestDispatcher("/seller/servletSeller").forward(request, response);
		//response.sendRedirect(request.getContextPath()+"/seller/servletSeller?id="+id);
		

	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	 throws ServletException,IOException{
		doGet(request, response);
	}
	
	public void init() throws ServletException{
		
	}
}
