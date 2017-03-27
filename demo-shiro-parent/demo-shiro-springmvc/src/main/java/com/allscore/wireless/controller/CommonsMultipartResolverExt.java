package com.allscore.wireless.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class CommonsMultipartResolverExt extends CommonsMultipartResolver {

	private HttpServletRequest request;

	protected FileUpload newFileUpload(FileItemFactory fileItemFactory) {		
		ServletFileUpload upload = new ServletFileUpload(fileItemFactory);  
        upload.setSizeMax(-1);  
        if (request != null) {  
        	HttpSession session = request.getSession();
        	FileUploadProgressListener progressListener = new FileUploadProgressListener(session);
            upload.setProgressListener(progressListener);  
        }  
        return upload; 
	}

	@Override
	public MultipartHttpServletRequest resolveMultipart(
			HttpServletRequest request) throws MultipartException {
		this.request = request;// 获取到request,要用到session
		return super.resolveMultipart(request);
	}
	
}
