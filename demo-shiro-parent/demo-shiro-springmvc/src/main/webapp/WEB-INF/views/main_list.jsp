<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>钱包后台</title>
<link rel="stylesheet" href="../resources/css/bootstrap.css" />
<link rel="stylesheet" href="../resources/css/css.css" />
<link rel="stylesheet" type="text/css" href="../resources/easyui/themes/default/easyui.css" />   
<link rel="stylesheet" type="text/css" href="../resources/easyui/themes/icon.css" />
<script type="text/javascript" src="../resources/easyui/jquery-1.7.2.min.js"></script>  
<script type="text/javascript" src="../resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../resources/js/sdmenu.js"></script>
<script type="text/javascript" src="../resources/js/laydate/laydate.js"></script>
<script type="text/javascript" src="../resources/easyui/jquery.validate.js"></script> 
     
  <script type="text/javascript">
    var myMenu;
    window.onload = function() {
	myMenu = new SDMenu("my_menu");
	myMenu.init();
	myMenu.collapseAll(); // 关闭所有菜单
	var firstSubmenu = myMenu.submenus[0];
	myMenu.expandMenu(firstSubmenu); // 打开一个菜单 
	
};

   $(document).ready(function(e) {
    $(".Switch").click(function(){
	$(".left").toggle();
		});
		
});

  function openNext(name,val,url)
  {    
    //var loginSession = "${loginSession}";
    
    //alert(loginSession);
    
    //if(loginSession!=null && loginSession!=''){
        $("#main").attr("src",url);
  	    $("#second").text(name);
  	    $("#third").text(val);
    //}

  }
  
  function updatePwd(){
        var params =$('#updateForm').serialize(); //自动序列化
       // alert("params:"+params);
		var login_pwd = $.trim($("#login_pwd").val());
		var login_newpwd = $.trim($("#login_newpwd").val());
		var login_newpwd1 = $.trim($("#login_newpwd1").val());
		var reg =/^[a-zA-Z]{1}([a-zA-Z0-9]{7,})$/;
		if(login_pwd==''){
		   alert("原密码不能为空");
		   return;
		}
		if(login_newpwd==''){
		   alert("新密码不能为空");
		   return;
		}
	    if(eval(login_newpwd.length)< 8 ){
		   alert("新密码位数不能低于8位");
		   return;
		}
		
	    if(!reg.test(login_newpwd)){
		   alert("新密码格式不正确");
		   return;
		}
		
		if(login_newpwd1==''){
		   alert("确认密码不能为空");
		   return;
		}
		if(login_newpwd!=login_newpwd1){
		   alert("新密码与确认密码不一致");
		   return;
		}
		$.ajax({type:"post",url:"${request.getContextPath()}/sys/checkPwd?"+params,  
			  success:function(data) {
			  var json = eval(data);
			  //alert(json);
              if(json==false){
                  alert("原密码不正确，请重新输入");
	              return;
              }else{
                   $.ajax({type:"post",url:"${request.getContextPath()}/sys/updatePwd?"+params,  
					  success:function(data) {
							     var json = eval(data);
							     if(json>0){
							       alert("密码修改成功");
							       $("#back").click();
							     }else{
							       alert("密码修改失败");
							     }
						}  
					 }); 
                }
	          }
	          
			 });
	}
</script>

</head>
  <body>

  <div class="header">
	 <div class="logo"><img  src="../resources/img/seller1.png" /></div>
				<div class="header-right">
                <i class="icon-question-sign icon-white"></i> <a href="#">帮助</a> <i class="icon-off icon-white"></i> 
                <a id="modal-973558" href="#zx" role="button" data-toggle="modal">注销</a> 
                <a id="modal-973559" href="#xg" role="button" data-toggle="modal">修改密码</a> 
                <div id="xg" class="modal hide fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width:500px; margin-left:-150px; top:30%">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h3 id="myModalLabel">
						修改密码
					</h3>
				</div>
				<form action="${request.getContextPath()}/sys/updatePwd" id="updateForm">
				<div class="modal-body">
                <table class="table table-bordered" >
			     <tr>
			       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">账号：</td>
			       <td ><input type="text" name="login_name" id="login_name" class="span3"  value="${userBase.LOGIN_NAME}" readonly="readonly"/></td>
			     </tr>
			     <tr>
			       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">原密码：</td>
			       <td><input type="password" name="login_pwd" id="login_pwd" class="span3"  /></td>
			       
			     </tr>
			     <tr>
			       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">新密码：</td>
			       <td><input type="password" name="login_newpwd" id="login_newpwd" class="span3"  />
			        (首位是字母，最短8位，最长不限，只有字母和数字组成)
			       </td>
			     </tr>
			     <tr>
			       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">确认密码：</td>
			       <td><input type="password" name="login_newpwd1" id="login_newpwd1" class="span3"  /></td>
			     </tr>
			     
			   </table>
			   </div>
			   <table  class="margin-bottom-20 table  no-border" >
			       <tr>
			     	<td class="text-center"><input type="button" value="确认" class="btn btn-info " style="width:80px;" onclick="updatePwd();"/>
			     	<input id="back" type="button" value="返回" class="btn btn-info " style="width:80px;" data-dismiss="modal" aria-hidden="true"/>
			     	</td>
			     </tr>
			 </table>
			 </form>
			</div>
                
                <div id="zx" class="modal hide fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width:300px; margin-left:-150px; top:30%">
				<div class="modal-header">
					 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h3 id="myModalLabel">
						注销系统
					</h3>
				</div>
				<div class="modal-body">
					<p>
						您确定要注销退出系统吗？
					</p>
				</div>
				<div class="modal-footer">
					 <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button> <a class="btn btn-primary" style="line-height:20px;" href="../index.jsp" >确定退出</a>
				</div>
			</div>
				</div>
</div>
           
<div id="middle">
     <div class="left">


<div id="my_menu" class="sdmenu">
    <@shiro.hasAnyRoles name="sellerManage">
	<div>
		<span>商户管理</span>
		 <a href="#"  onclick="openNext('商户管理','查询商户','${request.getContextPath()}/seller/sellerInfo')">查询商户</a>
		 <a href="#" onclick="openNext('交易管理','商户提现交易明细查询','${request.getContextPath()}/business/txInfo')">商户提现交易明细查询</a>
		 <a href="#" onclick="openNext('交易管理','商户资金池交易明细查询','${request.getContextPath()}/business/sellerPoolInfo')">商户资金池交易明细查询</a>
	
	</div>
	</@shiro.hasAnyRoles>
	<@shiro.hasAnyRoles name="businessManage">
	<div>
		<span>交易管理</span>
		<a href="#" onclick="openNext('交易管理','会员充值交易明细查询','${request.getContextPath()}/business/czInfo')">会员充值交易明细查询</a>
		<a href="#" onclick="openNext('交易管理','消费交易明细查询','${request.getContextPath()}/business/xfInfo')">消费交易明细查询</a>
		<a href="#" onclick="openNext('交易管理','会员提现明细查询','${request.getContextPath()}/business/hytxInfo')">会员提现明细查询</a>
			   
	</div>
	</@shiro.hasAnyRoles>
	<!--
	<@shiro.hasAnyRoles name="financeManage">
	<div>
	   
		<span>财务管理</span>
		  
		<a href="#" onclick="openNext('财务管理','提现审核查询','${request.getContextPath()}/finance/reviewFowd')">提现审核查询</a>
		<a href="#" onclick="openNext('财务管理','提现批次处理','${request.getContextPath()}/finance/batchFowd')">提现批次处理</a>
		
		<a href="#" onclick="openNext('财务管理','商户对账单','${request.getContextPath()}/finance/sellerChkFowd')">商户对账单</a>
		<a href="#" onclick="openNext('财务管理','差错账处理','${request.getContextPath()}/finance/accManageFowd')">差错账处理</a>
	     
	</div>
	</@shiro.hasAnyRoles>
	-->
	<@shiro.hasAnyRoles name="posManage">
	<div>
	    
		<span>POS机管理</span>
		<a href="#" onclick="openNext('POS机管理','POS机入库','${request.getContextPath()}/pos/posrkFowd')">POS机入库</a>
		<a href="#" onclick="openNext('POS机管理','POS机出库','${request.getContextPath()}/pos/posckFowd')">POS机出库</a>
		
	</div>
	</@shiro.hasAnyRoles>
    <@shiro.hasRole name="sysManage">
	<div>
		<span>系统设置</span>
		<a href="#" onclick="openNext('系统设置','角色设置','${request.getContextPath()}/sys/roleFowd')">角色设置</a>
		<a href="#" onclick="openNext('系统设置','人员管理','${request.getContextPath()}/sys/userFowd')">人员管理</a>
		<!--  
		<a href="#" onclick="openNext('系统设置','修改密码','${request.getContextPath()}/views/xtsz/updatePwd.jsp')">修改密码</a>
	     -->
	</div>
	 </@shiro.hasRole>
	<@shiro.hasAnyRoles name="activityManage">
	<div>
		<span>活动管理</span>
		<a href="#" onclick="openNext('活动管理','查看促销方案','${request.getContextPath()}/activity/queryCxSchemaFowd')">查看促销方案</a>
		<!--  <a href="#" onclick="openNext('活动管理','设置充值促销方案','${request.getContextPath()}/activity/czSchemaFowd')">设置充值促销方案</a>
		<a href="#" onclick="openNext('活动管理','设置消费促销方案','${request.getContextPath()}/activity/xfSchemaFowd')">设置消费促销方案</a>
		<a href="#" onclick="openNext('活动管理','设置点评促销方案','${request.getContextPath()}/activity/dpSchemaFowd')">设置点评促销方案</a>
		<a href="#" onclick="openNext('活动管理','设置注册促销方案','${request.getContextPath()}/activity/zcSchemaFowd')">设置注册促销方案</a>
         -->
	</div>
	</@shiro.hasAnyRoles>
	<@shiro.hasAnyRoles name="appManage">
	<div >
		<span>店+APP管理</span>
		<a href="#" onclick="openNext('店+APP管理','会员钱包账户管理','${request.getContextPath()}/app/appFowd')">会员钱包账户管理</a>
	</div>
	</@shiro.hasAnyRoles>
	<@shiro.hasAnyRoles name="memberManage">
	<div>
		<span>会员管理</span>
		<a href="#" onclick="openNext('会员管理','会员基本信息查询','${request.getContextPath()}/memberManage/memberInfo')">会员基本信息查询</a>
    
	</div>
	</@shiro.hasAnyRoles>
	<@shiro.hasAnyRoles name="sellerManage1">
	<div>
		<span>商户管理</span>
		<a href="#"  onclick="openNext('商户管理','商户资金池','${request.getContextPath()}/seller/sellerPool_list')">商户资金池</a>
	</div>
	</@shiro.hasAnyRoles>
</div>
     </div>
     <div class="Switch"></div>

<div class="right"  id="mainFrame">
     
 <div class="right_cont">
    <ul class="breadcrumb">当前位置：
  <a href="#">首页</a> <span class="divider">/</span>
  <a href="#" id="second"></a> <span class="divider">/</span>
  <a href="#" id="third"></a>

</div>
<iframe id="main" name="main" src="" style="padding-top:2px;" frameborder="0" scrolling="yes" height="150%" width="100%" ></iframe>

  </div>  
     
 </div>

</body>

  
</html>
