<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>钱包后台</title>
<link rel="stylesheet" href="../resources/css/bootstrap.css" />
<link rel="stylesheet" href="../resources/css/css.css" />
<link rel="stylesheet" type="text/css" href="../resources/easyui/themes/default/easyui.css" />   
<link rel="stylesheet" type="text/css" href="../resources/easyui/themes/icon.css" />
<script type="text/javascript" src="../resources/easyui/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="../resources/easyui/jquery.validate.js"></script>    
<script type="text/javascript" src="../resources/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../resources/js/laydate/laydate.js"></script>
<script type="text/javascript" src="../resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../resources/js/sdmenu.js"></script>

<script type="text/javascript" >
jQuery(function($){
$('#userTable').datagrid({
			title:'人员管理', //标题
			method:'post',
			iconCls:'icon-edit', //图标
			singleSelect:false, //多选
			width: 'auto',
			height:360, //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, //奇偶行颜色不同
			loadMsg:'数据加载中请稍后……', 
			collapsible:true,//可折叠
			url:'queryUser', //数据来源
			sortName: 'id', //排序的列
			sortOrder: 'desc', //倒序
			remoteSort: false, //服务器端排序
			idField:'id', //主键字段
			queryParams:{}, //查询条件
			pagination:true, //显示分页
			//rownumbers:true, //显示行号
			columns:[[
			    {field:'ck',checkbox:true,width:2}, //显示复选框
				{field:'id',title:'主键',width:30,sortable:true,hidden:true,
					formatter:function(value,row,index){return row.id;}
				},
				{field:'login_name',title:'登录名',width:30,sortable:true,
					formatter:function(value,row,index){
						return row.login_name;  
					}
				},
				{field:'name',title:'姓名',width:30,sortable:true,
					formatter:function(value,row,index){
						return row.name;  
					}
				},
				{field:'mobile',title:'手机号',width:30,sortable:true,
					formatter:function(value,row,index){
						return row.mobile;  
					}
				},
				{field:'email',title:'e-mail',width:30,sortable:true,hidden:true,
					formatter:function(value,row,index){
						return row.email; 
					}
				},
				{field:'role_name',title:'角色名称',width:30,sortable:true,
					formatter:function(value,row,index){
						return row.role_name; 
					}
				},
				{field:'create_time',title:'创建时间',width:30,sortable:true,
					formatter:function(value,row,index){
						return row.create_time; 
					}
				},
				{field:'operator',title:'操作',width:30,sortable:true,
					formatter:function(value,row,index){
					    var i = '<a href="javascript:void(0)" onclick="userEdit(\''+row.id+'\')">修改</a> ';
					    var j = '<a href="javascript:void(0)" onclick="userDel(\''+row.id+'\')">删除</a> ';
					    var k = '<a href="javascript:void(0)" onclick="pwdReset(\''+row.id+'\')">密码重置</a> ';
						return i+j+k; 
					}
				}
			]],
			onLoadSuccess:function(){
				$('#userTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			}
			
			
		});
		//分页显示代码
		$('#userTable').datagrid('getPager').pagination({
			    pageSize: 10,//每页显示的记录条数，默认为10
                pageList: [10, 30, 40, 50, 100],//可以设置每页记录条数的列表 
                beforePageText: '第',//页数文本框前显示的汉字  
                afterPageText: '页    共 {pages} 页',
                displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
            });
            
        $.ajax({  
			  type:"post",  
			  url:"role_List",  
			  success:function(data) {
			  var json = eval(data);
			  $("<option value=''>请选择</option>").appendTo("#role_id"); 
			  $.each(json,function(i,value){
			  //根据json格式的不同，取值的方式也不一样
			     var id = value.role_id;
			     var name = value.role_name; 
			      //var id = json[i].MERCHANT_CODE;
			      //var name = json[i].merchant_name; 
				  $("<option value="+id+">"+name+"</option>").appendTo("#role_id");  
				  }  
				);   
				}  
			 });
     
	});
	
    //表格查询
	function searchUser(){
		var params = $('#userTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
		var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
		$.each( fields, function(i, field){
			params[field.name] = field.value; //设置查询参数
		}); 
		$('#userTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
	}

	  //新增
	  function displayUser(){
    
          $('#dd').show().dialog({
		    title: '新增人员',
		    width: 600,
		    height: 450,
		    closed: false,
		    cache: false,
		    modal: true
          }); 
        // $('#dd').dialog('open');//当div中带样式时使用该方法，不带样式使用上面的方法

	}
	
	//修改
    function userEdit(id){

    $('#edit').dialog({title: '修改用户信息',
            iconCls:"icon-edit",
            collapsible: true,
            minimizable: true,
            maximizable: true,
            resizable: true,
            width: 700,
            height:350,
            href: 'editUserFowd?id='+id,
            modal: true,
           });

	}
	//删除人员
  function userDel(id){
  if(confirm("您确定要删除该记录？")){
    $.ajax({
     type: "post",
     url: "delUser",
     data: "id="+id,
     success: function(msg){
       if(msg=="success"){
          alert("删除成功！");
          searchUser();  
       }else{
          alert("删除失败！");
       }
      }
     });
     } 
   }
   //重置密码
   function pwdReset(id){
   if(confirm("您确定要重置密码？")){
	    $.ajax({
	     type: "post",
	     url: "resetUser",
	     data: "id="+id,
	     success: function(msg){
	       if(msg=="success"){
	          alert("重置成功！");
	          searchUser();  
	       }else{
	          alert("重置失败！");
	       }
	      }
	     });  
     }
   }

	

	
	//清空查询条件
	function clearForm(){
		$('#queryForm').form('clear');
		searchUser();
	}
    
    function getRoleModify(val){
       if(val=="3"){
          $("#sellerName").append("<span id='shbh'>商户编号:<span>");
          
         // $("").appendTo("#sellerName");
          $("<select id='merchant_code' name='merchant_code'></select>").appendTo("#sellerId");

          $.ajax({  
			  type:"post",  
			  url:"${request.getContextPath()}/pos/merchantName_List",  
			  success:function(data) {
				  var json = eval(data);
				  $("<option value=''>请选择</option>").appendTo("#merchant_code"); 
				  $.each(json,function(i,value){
				  //根据json格式的不同，取值的方式也不一样
				     var id = value.merchant_code;
				     var name = value.merchant_name;
				      //var id = json[i].MERCHANT_CODE;
				      //var name = json[i].merchant_name; 
					  $("<option value="+id+">"+name+"</option>").appendTo("#merchant_code");  
					  }  
					);   
				}  
			 });
       }else{
          $("#merchant_code").remove();
          $("#shbh").remove();
       }
        
    }
    //返回
   function comBack(){
       $('#userForm').form('clear');   
       $('#dd').dialog('close');
   }
   
   function addUser(){
      var name = $.trim($('#name').val());
      var login_name = $.trim($('#login_name').val());
      var login_pwd = $.trim($('#login_pwd').val());
      var login_pwd1 = $.trim($('#login_pwd1').val());
      var mobile = $.trim($('#mobile').val());
      var role_id = $.trim($('#role_id').val());
      var reg =/^\d{11}$/;
      if(name ==''){
        alert("人员名称不能为空");
        return;
      }
      if(login_name ==''){
        alert("登录名不能为空");
        return;
      }
      if(login_pwd ==''){
        alert("用户密码不能为空");
        return;
      }
      if(login_pwd1 ==''){
        alert("确认密码不能为空");
        return;
      }
      if(mobile ==''){
        alert("手机号码不能为空");
        return;
      }
      if(!reg.test(mobile)){
		   alert("手机号必须满11位，并且只能是数字");
		   return;
	  }
      if(role_id ==''){
        alert("角色名称不能为空");
        return;
      }
      if(login_pwd1 !=login_pwd){
        alert("确认密码与用户密码必须相同");
        return;
      }      
      if(login_pwd.length < 8){
        alert("密码不能小于8位");
        return;
      }
      //校验登录名是否存在
       $.ajax({type:"post",url:"loginVerifyAction?login_name="+login_name,  
			  success:function(data) {
			   if(data==true){
                  	if(role_id=='3'){
				        var merchant_code = $.trim($('#merchant_code').val());
				        //校验商户编号是否存在
				        $.ajax({type:"post",url:"merchantIdVerifyAction?merchant_code="+merchant_code,  
							         success:function(data) {
									   if(data==true){
									      var params = $('#userForm').serialize();
									      //增加商户后台人员信息
									      $.ajax({type:"post",url:"addUser?"+params,  
									         success:function(data) {
											   if(data=="success"){
											      alert("商户后台人员新增成功");
											      window.location.href="${request.getContextPath()}/sys/userFowd";
											   }else{
											     alert("商户后台人员增加失败");
											   }
							                }
							               });
									   }else{
									     alert("商户编号不存在");
									   }
					                }
					   });
				     }else{
				       var params = $('#userForm').serialize();
				       //增加管理后台人员信息
				      $.ajax({type:"post",url:"addUser?"+params,  
				         success:function(data) {
						   if(data=="success"){
						      alert("管理后台人员新增成功");
						      window.location.href="${request.getContextPath()}/sys/userFowd";
						   }else{
						     alert("管理后台人员增加失败");
						   }
		                }
		               });
				     }
			   }else{
			      alert("登录名已存在");
			   }
			   
	        }
	 }); 
   }
   
   	 //分配权限
	 function fpRole(){
	      var checks = $("[type='checkbox']:checked");
	
	      if(checks.length ==0){
	         alert("请选择一条数据！");
	         return;
	      }
	      var row = $('#userTable').datagrid('getSelected');
          window.location.href="${request.getContextPath()}/sys/roleFpFowd?login_name="+row.login_name+"&id="+row.id;
	}
	

</script>

</head>

<body>
 
<form id="queryForm">
   <div class="title_right"><strong>人员管理</strong></div>  
       <div style="width:100% margin:auto">
        <table  class="margin-bottom-20 table  no-border" >
         <tr>
     	<td class="text-center">
     	<input type="button" value="新增人员" class="btn btn-info " style="width:80px;" onclick="displayUser();"/>
     	<input type="button" value="分配权限" class="btn btn-info " style="width:80px;" onclick="fpRole();"/>
     	</td>
     </tr>
 </table>
 <table id="userTable"></table>
    
   </div>
 </form>
 
<div id="dd" class="easyui-dialog" title="" closed="true">
   <form action="${request.getContextPath()}/sys/addUser" method="post" id="userForm">
     <div class="title_right"><strong></strong></div>
     <div class="modal-body">
     <table class="table table-bordered"> 
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">人员名称：</td>
       <td colspan="3"><input type="text" name="name" id="name" class="span2"/></td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">登录名：</td>
       <td colspan="3"><input type="text" name="login_name" id="login_name" class="span2"/>(*由数字或字母组成)</td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">用户密码：</td>
       <td colspan="3"><input type="password" name="login_pwd" id="login_pwd" class="span2"/></td>
     </tr>
      <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">确认密码：</td>
       <td colspan="3"><input type="password" name="login_pwd1" id="login_pwd1" class="span2"/></td>
     </tr>
      <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">手机号码：</td>
       <td colspan="3"><input type="text" name="mobile" id="mobile" class="span2" maxlength="11"/></td>
     </tr>
      <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">角色名称：</td>
       <td colspan="3"><select id="role_id" name="role_id"
        onChange="getRoleModify(this.options[this.selectedIndex].value)">
          </select></td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1" >
        <div id="sellerName"></div>
       </td>
       <td colspan="3" ><div id="sellerId"></div></td>
     </tr>
     
   </table>
   </div>
   <table class="margin-bottom-20 table  no-border" >
        <tr>
     	<td class="text-center"><input type="button" value="提交" class="btn btn-info " style="width:80px;" onclick="addUser();"/>
     	<input type="button" value="返回" class="btn btn-info " style="width:80px;" onclick="comBack();"/>
     				     	
     	</td>
     </tr>
 </table>

</form>
<div id="edit"></div>
</div>
</body>
</html>
