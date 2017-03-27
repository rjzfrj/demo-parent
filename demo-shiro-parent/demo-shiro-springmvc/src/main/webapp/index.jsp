<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" session="false" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商户后台管理系统</title>
<link rel="stylesheet" href="resources/css/bootstrap.css" />
<link rel="stylesheet" href="resources/css/css.css" />
<link rel="stylesheet" type="text/css" href="resources/easyui/themes/default/easyui.css" />   
<link rel="stylesheet" type="text/css" href="resources/easyui/themes/icon.css" />
<script type="text/javascript" src="resources/easyui/jquery-1.7.2.min.js"></script>  
<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resources/js/sdmenu.js"></script>
<script type="text/javascript" src="resources/js/laydate/laydate.js"></script>
<script type="text/javascript" src="resources/easyui/jquery.validate.js"></script> 
<style type="text/css">
body{ background:#0066A8 url(resources/img/login_bg.png) no-repeat center 0px;}
.tit{ margin:auto; margin-top:170px; text-align:center; width:350px; padding-bottom:20px;}
.login-wrap{ width:220px; padding:30px 50px 0 330px; height:220px; background:#fff url(resources/img/20150212154319.jpg) no-repeat 30px 40px; margin:auto; overflow: hidden;}
.login_input{ display:block;width:210px;}
.login_user{ background: url(resources/img/input_icon_1.png) no-repeat 200px center; font-family: "Lucida Sans Unicode", "Lucida Grande", sans-serif}
.login_password{ background: url(resources/img/input_icon_2.png) no-repeat 200px center; font-family:"Courier New", Courier, monospace}
.btn-login{ background:#40454B; box-shadow:none; text-shadow:none; color:#fff; border:none;height:35px; line-height:26px; font-size:14px; font-family:"microsoft yahei";}
.btn-login:hover{ background:#333; color:#fff;}
.copyright{ margin:auto; margin-top:10px; text-align:center; width:370px; color:#CCC}
@media (max-height: 700px) {.tit{ margin:auto; margin-top:100px; }}
@media (max-height: 500px) {.tit{ margin:auto; margin-top:50px; }}
</style>
<script type="text/javascript">
  function login(){
        var params =$('#loginForm').serialize(); //自动序列化
        //alert("params:"+params);
		var user = $.trim($("#user").val());
		var pwd = $.trim($("#pwd").val());
		if(user==''){
		  alert("用户名不能为空");
		  return;
		}
		if(pwd==''){
		  alert("密码不能为空");
		  return;
		}
 
		$.ajax({type:"post",url:"<%=request.getContextPath()%>/login/verifyLogin?"+params,  
			  success:function(data) {
			   			   //alert(data);
			   if(data=="success"){
			  
			      $("#loginForm").submit();
			      
			   }else if(data=="fail1"){
			      //$('#loginForm').form('clear');
			      alert("登录失败,用户名或密码不正确");
			      //return;
			  }else if(data=="fail2"){
			      //$('#loginForm').form('clear');
			      alert("该用户没有权限");
			      //return;
			  }else if(data=="verifyFalse"){
			      alert("验证码不正确");
			  }
	        }
	 });
  }

</script>
</head>

<body>
<div class="tit"><img src="resources/img/seller1.png" alt="" /></div>
<form action="<%=request.getContextPath()%>/user/login" method="post" id="loginForm">
<div class="login-wrap">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25" valign="bottom">用户名：</td>
    </tr>
    <tr>
      <td><input type="text" name ="user" id="user" class="login_input login_user" value="" /></td>
    </tr>
    <tr>
      <td height="35" valign="bottom">密  码：</td>
    </tr>
    <tr>
      <td><input type="password" name="pwd" id="pwd" class="login_input login_password" value="" /></td>
    </tr>
    <tr>
      <td height="60" valign="bottom">
      <input type="button" class="btn btn-block btn-login" value="登录" onclick="login();"/>
      </td>                             
    </tr>
  </table>
</div>
</form>
<div class="copyright">建议使用IE8以上版本或谷歌浏览器</div>
</body>
</html>
