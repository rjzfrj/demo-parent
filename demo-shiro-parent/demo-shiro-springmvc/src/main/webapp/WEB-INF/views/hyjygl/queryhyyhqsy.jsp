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
<script type="text/javascript" src="../resources/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../resources/js/laydate/laydate.js"></script>
<script type="text/javascript" >
jQuery(function($){
$('#userTable').datagrid({
			title:'会员优惠券查询列表', //标题
			method:'post',
			iconCls:'icon-edit', //图标
			singleSelect:false, //多选
			width: 'auto',
			height:360, //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, //奇偶行颜色不同
			loadMsg:'数据加载中请稍后……', 
			collapsible:true,//可折叠
			url:"coupon", //数据来源
			sortName: 'id', //排序的列
			sortOrder: 'desc', //倒序
			remoteSort: false, //服务器端排序
			idField:'id', //主键字段
			queryParams:{}, //查询条件
			pagination:true, //显示分页
			//rownumbers:true, //显示行号
			columns:[[
			    {field:'id',title:'主键',sortable:true,hidden:true,
					formatter:function(value,row,index){return row.id;} 
				},
				{field:'member_card',title:'会员卡号',sortable:true,
					formatter:function(value,row,index){return row.member_card;} 
				},
				{field:'merchant_name',title:'商户名称',sortable:true,
					formatter:function(value,row,index){return row.merchant_name;}
				},
				{field:'amount',title:'优惠券金额(元)',sortable:true,
					formatter:function(value,row,index){
						return row.amount;  
					}
				},
				{field:'validday',title:'优惠券有效期(天)',sortable:true,hidden:true,
					formatter:function(value,row,index){
						return row.validday;  
					}
				},
				{field:'start_date',title:'有效开始时间',sortable:true,
					formatter:function(value,row,index){
						return row.start_date;  
					}
				},
			    {field:'end_date',title:'有效结束时间',sortable:true,
					formatter:function(value,row,index){
						return row.end_date;  
					}
				},
				{field:'update_time',title:'使用时间',sortable:true,
					formatter:function(value,row,index){
					    if(row.status=="01"){
					       return row.update_time;
					    }else{
					       return "";
					    }
					}
				},
				{field:'status',title:'优惠券状态',sortable:true,
					formatter:function(value,row,index){
						 var s ="";
					    if(row.status=="00")
					      s="可使用";
					    else if(row.status=="01")
					      s="已使用";
					    else if(row.status=="02")
					      s="已过期";
						return s; 
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
   function exportUser(){
		
		var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
		var params = "?";
		$.each( fields, function(i, field){
		     if(i==0) 
	        	params += field.name+"="+field.value;
	         else
	            params += "&"+field.name+"="+field.value;
			//params[field.name] = field.value; //设置查询参数
			 
		}); 
		
	    var rows =  $('#userTable').datagrid('getPager').data("pagination").options.pageSize;
	    var page =  $('#userTable').datagrid('getPager').data("pagination").options.pageNumber;
	    params+="&rows="+rows+"&page="+page;
        window.location.href="${request.getContextPath()}/business/coupon_export"+params;

	}
	
	//清空查询条件
	function clearForm(){
		$('#queryForm').form('clear');
		searchUser();
	}
    

</script>

</head>

<body>
 
<form id="queryForm">

   <div class="title_right"><strong>会员优惠券使用查询</strong></div>  
       <div style="width:100% margin:auto">
       <table class="table table-bordered">
         <tr>
         <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">会员卡号：</td>
	     <td><input type="text" id="member_card" name="member_card" class=" span1-1" maxlength="12"/></td>
         <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">优惠券状态：</td>
         <td><select id="status" name="status" style="height:25px">
          <option value="">全部</option>
          <option value="00">可使用</option>
          <option value="01">已使用</option>
          <option value="02">已过期</option>
        </select></td>
     </tr>
       </table>
       
       <table class="margin-bottom-20 table  no-border" >
        <tr>
     	<td class="text-center"><input type="button" value="查询" class="btn btn-info " style="width:80px;" onclick="searchUser();"/>
     	<input type="button" value="导出" class="btn btn-info " style="width:80px;" onclick="exportUser();"/>
     	
     	
     	</td>
     </tr>
 </table>
    <table id="userTable"></table>
    <div id="dd">
       
    </div>
   </div>
 </form>
</body>
</html>
