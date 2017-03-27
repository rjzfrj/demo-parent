<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>钱包后台</title>
<link rel="stylesheet" href="../resources/css/bootstrap.css" />
<link rel="stylesheet" href="../resources/css/css.css" />
<link rel="stylesheet" type="text/css" href="../resources/easyui/themes/default/easyui.css" />
</head>
<body>
<script type="text/javascript" src="../resources/easyui/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="../resources/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../resources/js/sdmenu.js"></script>
<script type="text/javascript" src="../resources/js/laydate/laydate.js"></script>
<script type="text/javascript" >
   function comBack(){
       window.location.href="${request.getContextPath()}/sys/userFowd";
      // $('#edit').dialog('close');
       
   }
   
   function saveUserInfo(){
       var login_pwd = $.trim($('#login_pwd').val());
       var login_pwd1 = $.trim($('#login_pwd1').val());
       
       if(login_pwd==''){
          alert("用户密码不能为空！");
          return false;
       }
       
       if(login_pwd1==''){
          alert("确认密码不能为空！");
          return false;
       }
       
       if(login_pwd1!=login_pwd){
         alert("用户密码与确认密码不一致！");
         return false;
       }
       $("#loginForm").submit();
       
   }
</script>
  <form action="${request.getContextPath()}/sys/editUser" method="post" id="loginForm">
  <div class="modal-body">
   <div style="width:600px; margin:auto">
   <input type="hidden" name="id" id="id" class="span4" value="${userBase.ID }"/>
	<table class="table table-bordered"> 
	     <tr>
	       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">人员名称：</td>
	       <td><input type="text" name="name" id="name" class="span2" value="${userBase.NAME }"/></td>
	     </tr>
	     <tr>
	       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">登录名：</td>
	       <td ><input type="text" name="login_name" id="login_name" class="span2" value="${userBase.LOGIN_NAME }" readonly="readonly"/></td>
	     </tr>
	     <tr>
	       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">用户密码：</td>
	       <td><input type="password" name="login_pwd" id="login_pwd" class="span2" value="${userBase.LOGIN_PWD }"/></td>
	     </tr>
	      <tr>
	       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">确认密码：</td>
	       <td><input type="password" name="login_pwd1" id="login_pwd1" class="span2" value="${userBase.LOGIN_PWD }"/></td>
	     </tr>
	      <tr>
	       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">手机号码：</td>
	       <td><input type="text" name="mobile" id="mobile" class="span2" value="${userBase.MOBILE}" maxlength="11"/></td>
	     </tr>
	   </table>

        <table  class="margin-bottom-20 table  no-border" >
        <tr>
     	<td class="text-center"><input type="button" value="保存" class="btn btn-info " style="width:80px;" onclick="saveUserInfo();"/>
     	<input type="button" value="返回" class="btn btn-info " style="width:80px;" onclick="comBack();"/>
     	</td>
     </tr>
 </table>

      
</div> 
</div>
</form>
</body>
</html>
