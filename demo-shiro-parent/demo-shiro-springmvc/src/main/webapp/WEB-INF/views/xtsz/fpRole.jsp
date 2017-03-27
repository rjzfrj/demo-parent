<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" content="-1" />
<title>钱包后台</title>
<link rel="stylesheet" href="../resources/css/bootstrap.css" />
<link rel="stylesheet" href="../resources/css/css.css" />
<link rel="stylesheet" type="text/css" href="../resources/easyui/themes/default/easyui.css" />   
<link rel="stylesheet" type="text/css" href="../resources/easyui/themes/icon.css" />
<script type="text/javascript" src="../resources/easyui/jquery-1.7.2.min.js"></script>   
<script type="text/javascript" src="../resources/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../resources/js/laydate/laydate.js"></script>
<script type="text/javascript">
jQuery(function($){
      var login_name = "${login_name}";
      //alert(login_name);
	  $.ajax({  
			 type:"post",  
			  url:"menu_List?login_name="+login_name,
			  success:function(data) {
			      var json = eval(data);
			      $.each(json, function(i, value){
			         var menu = value.menu_name;
			         $("#"+menu).attr("checked",true);  
		          });   
			  }
			 });
  });
  
  	//分配权限
	 function fenpRole(){
       
       var fields =$('#roleForm').serializeArray(); //自动序列化表单元素为JSON对象
		var params = "?";
		$.each( fields, function(i, field){
		     if(i==0) 
	        	params += field.name+"="+field.value;
	         else
	            params += "&"+field.name+"="+field.value;
			//params[field.name] = field.value; //设置查询参数
			 
		}); 
       	$.ajax({  
			  type:"post",  
			  url:"fenpRole"+params,
			  success:function(data) {
			      if(data=="success"){
			        alert("权限新增成功！");
			        window.location.href="${request.getContextPath()}/sys/userFowd";
			      }
			   
			     
			  }
			 });        

	}
    //返回
   function comBack(){
       window.location.href="${request.getContextPath()}/sys/userFowd";
   }
</script>
</head>
<body>
<form action="" method="post" id="roleForm">
   <div style="width:900px; margin:auto;">
   <div class="modal-body">
     <input type="hidden" name="id" class="span2" id="id" value="${id}"/>
     <table class="table table-bordered"> 
     <tr>
       <td align="right" nowrap="nowrap" class="span2" bgcolor="#f1f1f1">商户管理：</td>
       <td><input type="checkbox" name="sellerManage" class="span2" id="sellerManage" value="sellerManage"/></td>     
       <td colspan="2"></td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" class="span2" bgcolor="#f1f1f1">会员管理：</td>
       <td><input type="checkbox" class="span2" name="memberManage" id="memberManage" value="memberManage"/></td>     
       <td colspan="2"></td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" class="span2" bgcolor="#f1f1f1">会员交易管理：</td>
       <td><input type="checkbox" class="span2" name="businessManage" id="businessManage" value="businessManage"/></td>     
       <td colspan="2"></td>
     </tr>
     <!--  
     <tr>
       <td align="right" nowrap="nowrap" class="span2" bgcolor="#f1f1f1">财务管理：</td>
       <td><input type="checkbox" class="span2" name="financeManage" id="financeManage" value="financeManage"/></td>     
       <td colspan="2"></td>
     </tr>
    
     <tr>
       <td align="right" nowrap="nowrap" class="span2" bgcolor="#f1f1f1">活动管理：</td>
       <td><input type="checkbox" class="span2" name="activityManage" id="activityManage" value="activityManage"/></td>     
       <td colspan="2"></td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" class="span2" bgcolor="#f1f1f1">POS机管理：</td>
       <td><input type="checkbox" class="span2" name="posManage" id="posManage" value="posManage"/></td>     
       <td colspan="2"></td>
     </tr> -->
     <tr>
       <td align="right" nowrap="nowrap" class="span2" bgcolor="#f1f1f1">系统设置：</td>
       <td><input type="checkbox" class="span2" name="sysManage" id="sysManage" value="sysManage"/></td>     
       <td colspan="2"></td>
     </tr>
   <!--   <tr>
       <td align="right" nowrap="nowrap" class="span2" bgcolor="#f1f1f1">店+APP管理：</td>
       <td><input type="checkbox" class="span2" name="appManage" id="appManage" value="appManage"/></td>     
       <td colspan="2"></td>
     </tr>--> 
    
   </table>
   </div>
   <table class="margin-bottom-20 table  no-border" >
        <tr>
     	<td class="text-center"><input type="button" value="提交" class="btn btn-info " style="width:80px;" onclick="fenpRole();"/>
     	<input type="button" value="返回" class="btn btn-info " style="width:80px;" onclick="comBack();"/>
     	</td>
     </tr>
 </table>
</div>
</form>

</body>
</html>
