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
<script type="text/javascript" >
jQuery(function($){
$('#userTable').datagrid({
			title:'角色设置', //标题
			method:'post',
			iconCls:'icon-edit', //图标
			singleSelect:false, //多选
			width: 'auto',
			height:360, //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, //奇偶行颜色不同
			loadMsg:'数据加载中请稍后……', 
			collapsible:true,//可折叠
			url:'queryRole', //数据来源
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
				{field:'role_id',title:'角色ID',width:30,sortable:true,
					formatter:function(value,row,index){return row.role_id;}
				},
				{field:'role_name',title:'角色名称',width:30,sortable:true,
					formatter:function(value,row,index){
						return row.role_name; 
					}
				},
				{field:'operator',title:'操作',width:30,sortable:true,
					formatter:function(value,row,index){
					    var j = '<a href="javascript:void(0)" onclick="roleDel(\''+row.id+'\',\''+row.role_id+'\')">删除</a> ';
						return j; 
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
  
    //新增角色
	function addRole(){
         $.ajax({
		     type: "post",
		     url: "getRoleId",
		     success: function(data){
		          
		        $('#role_id').val(data);
		      }
		     });
          $('#add').show().dialog({
		    title: '新增角色',
		    width: 450,
		    height: 230,
		    closed: false,
		    cache: false,
		    modal: true
          }); 
        // $('#dd').dialog('open');//当div中带样式时使用该方法，不带样式使用上面的方法

	}


    //删除角色
	function roleDel(id,role_id){
	 if(confirm("您确定要删除该记录？")){
     $.ajax({
     type: "post",
     url: "delRole",
     data: "id="+id+"&role_id="+role_id,
     success: function(msg){
       if(msg=="success"){
          alert("删除成功！");
          searchUser();  
       }else if(msg=="delFail"){
          alert("该角色有其他用户,不能删除");
       }
      }
     }); 
        // $('#dd').dialog('open');//当div中带样式时使用该方法，不带样式使用上面的方法
      }
	}

	//清空查询条件
	function clearForm(){
		$('#queryForm').form('clear');
		searchUser();
	}
	
	//新增角色
	function addRoleManage(){
	  var role_id = $.trim($('#role_id').val());
	  var role_name = $.trim($('#role_name').val());
	  var reg =/^\d{2}$/;
	  if(role_id==""){
	    alert("角色编号不能为空");
	    return;
	  }
	  if(role_name==""){
	    alert("角色名称不能为空");
	    return;
	  }
	  var params = $('#userForm').serialize(); //自动序列化表单元素
	  var url = "${request.getContextPath()}/sys/addRole?"+params;
	   $.ajax({  
			  type:"post",  
			  url:url,
			  success:function(data) {
                 if(data=="success"){
                    alert("角色新增成功");
                    window.location.href="${request.getContextPath()}/sys/userFowd";
                 }else if(data=="fail"){
                    alert("该角色名称已存在,新增失败");
                 }
			      
			  }
			 });
	}
    //返回
   function comBack(){
       $('#userForm').form('clear');   
       $('#add').dialog('close');
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
     	<input type="button" value="新增角色" class="btn btn-info " style="width:80px;" onclick="addRole();"/>
     	</td>
     </tr>
 </table>
 <table id="userTable"></table>
    
   </div>
 </form>
<div id="add" class="easyui-dialog" title="" closed="true">
<form action="" method="post" id="userForm">

  <div class="modal-body">
     <table class="table table-bordered"> 
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">角色编号：</td>
       <td colspan="3"><input type="text" name="role_id" id="role_id" class="span2" readonly="readonly"/></td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">角色名称：</td>
       <td colspan="3"><input type="text" name="role_name" id="role_name" class="span2"/></td>
     </tr>
   </table>
   </div>
   <table class="margin-bottom-20 table  no-border" >
        <tr>
     	<td class="text-center"><input type="button" value="提交" class="btn btn-info " style="width:80px;" onclick="addRoleManage();"/>
     	<input type="button" value="返回" class="btn btn-info " style="width:80px;" onclick="comBack();"/>
     	</td>
     </tr>
 </table>

</form>

</div>

</body>
</html>
