package com.allscore.wireless.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.allscore.wireless.biz.UserBaseManager;
import com.allscore.wireless.common.BaseController;
import com.allscore.wireless.dao.UserBase;

@Controller
public class LoginController extends BaseController {

	private static final Logger logger = Logger.getLogger(LoginController.class);
    
	@Autowired
	UserBaseManager userBaseManager;
	
	@RequestMapping(value = "/user/login",method = RequestMethod.POST)
	public String checkLogin(@RequestParam("user")String user,@RequestParam("pwd")String pwd,HttpServletRequest request,
			HttpSession httpSession,ModelMap modelMap) throws Exception {

        logger.info("登录用户名称"+user);
        logger.info("登录用户密码"+pwd);
        String resultPageURL = "";//InternalResourceViewResolver.FORWARD_URL_PREFIX + "/";
        //logger.info("KKKK:"+InternalResourceViewResolver.FORWARD_URL_PREFIX + "/");
        logger.info("resultPageURL:"+resultPageURL);
        //String username = request.getParameter("username");  
        //String password = request.getParameter("password");
        String username = user;  
        String password = pwd;
        password = this.EncoderByMd5(password);
        //获取HttpSession中的验证码  
        //String verifyCode = (String)request.getSession().getAttribute("verifyCode");  
        //获取用户请求表单中输入的验证码  
       // String submitCode = WebUtils.getCleanParam(request, "verifyCode");  
        //logger.info("用户[" + username + "]登录时输入的验证码为[" + submitCode + "],HttpSession中的验证码为[" + verifyCode + "]");  
       // if (StringUtils.isEmpty(submitCode) || !StringUtils.equals(verifyCode, submitCode.toLowerCase())){  
          //  request.setAttribute("message_login", "验证码不正确");  
          //  return resultPageURL;  
        //}  
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);  
        //logger.info("为了验证登录用户而封装的token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));  
        //获取当前的Subject  
        Subject currentUser = SecurityUtils.getSubject();  
        try {  
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查  
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应  
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法  
            logger.info("对用户[" + username + "]进行登录验证..验证开始");  
            currentUser.login(token);
            //服务器tomcat30分钟无操作清除session，再次产生的sessionID与原来的不一致
            httpSession.setAttribute("loginSession", request.getRequestedSessionId());
            UserBase userBase = (UserBase) httpSession.getAttribute("currentUser");
            
            if(!"".equals(userBase.getMERCHANT_CODE()) && userBase.getMERCHANT_CODE() !=null){
            	logger.info("userBase.getMERCHANT_CODE():"+userBase.getMERCHANT_CODE());
            	logger.info("对用户[" + username + "]进行登录验证..验证未通过,该账户不是后台账户");  
            	
            	resultPageURL="redirect:/index.jsp";
            }else{
            	logger.info("对用户[" + username + "]进行登录验证..验证通过");
            	
            	resultPageURL = "main_list";
            }
            modelMap.addAttribute("userBase",userBase);
            //modelMap.addAttribute("loginSession",request.getRequestedSessionId());
        }catch(UnknownAccountException uae){  
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");  
            
            //String message_login = "未知账户";
            resultPageURL="redirect:/index.jsp";
        }catch(IncorrectCredentialsException ice){  
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,密码不正确"); 
            resultPageURL="redirect:/index.jsp";
        }catch(LockedAccountException lae){  
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");  
        }catch(ExcessiveAttemptsException eae){  
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多"); 
        }catch(AuthenticationException ae){  
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景  
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
            resultPageURL="redirect:/index.jsp";
        } 
        //验证是否登录成功  
        if(currentUser.isAuthenticated()){  
            logger.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");  
        }else{  
            token.clear();
        } 
        return resultPageURL;

	}
	
	@RequestMapping(value = "/login/verifyLogin",method = RequestMethod.POST)
	@ResponseBody
	public String verifyLogin(@RequestParam("user")String user,@RequestParam("pwd")String pwd,HttpServletRequest request,
			HttpServletResponse response,
			HttpSession httpSession,ModelMap modelMap) throws Exception {
		pwd = this.EncoderByMd5(pwd);
		

		UserBase userBase = this.userBaseManager.getUserBaseInfo(user, pwd);
		if(userBase==null){
			return "fail1";
		}else{
			//return "success";
		}
		
		UserBase userBase1 = this.userBaseManager.getUserBaseByLoginName(user, pwd);
		if(userBase1==null){
			return "fail2";
		}else{
			return "success";
		}
        
	}

	
}
