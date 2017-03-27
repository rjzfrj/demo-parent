
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
			title:'提现审核查询列表', //标题
			method:'post',
			iconCls:'icon-edit', //图标
			singleSelect:false, //多选
			width: 'auto',
			height:360, //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, //奇偶行颜色不同
			loadMsg:'数据加载中请稍后……', 
			collapsible:true,//可折叠
			url:'txReview', //数据来源
			sortName: 'id', //排序的列
			sortOrder: 'desc', //倒序
			remoteSort: false, //服务器端排序
			idField:'id', //主键字段
			queryParams:{}, //查询条件
			pagination:true, //显示分页
			//rownumbers:true, //显示行号
			columns:[[
			    {field:'ck',checkbox:true,width:2}, //显示复选框
			    {field:'id',title:'主键',width:20,sortable:true,hidden:true,
					formatter:function(value,row,index){return row.id;} 
				},
				{field:'business_time',title:'交易时间',width:20,sortable:true,
					formatter:function(value,row,index){return row.business_time;}
				},
				{field:'MERCHANT_CODE',title:'商户编号',width:20,sortable:true,
					formatter:function(value,row,index){return row.MERCHANT_CODE;} 
				},
	
				{field:'merchant_name',title:'商户名称',width:30,sortable:true,
					formatter:function(value,row,index){return row.merchant_name;}
				},
				{field:'serial',title:'交易流水号',width:30,sortable:true,
					formatter:function(value,row,index){
						return row.serial;  
					}
				},
				{field:'order_amt',title:'提现金额',width:30,sortable:true,
					formatter:function(value,row,index){
						return row.order_amt;  
					}
				},
				{field:'poundage',title:'手续费',width:30,sortable:true,
					formatter:function(value,row,index){
						return row.poundage;  
					}
				},
				{field:'tran_amt',title:'实际到账金额',width:30,sortable:true,
					formatter:function(value,row,index){
						return row.tran_amt; 
					}
				},
				{field:'status',title:'状态',width:30,sortable:true,
					formatter:function(value,row,index){
						return row.status; 
					}
				},
				{field:'operator',title:'操作',width:30,sortable:true,
					formatter:function(value,row,index){
					var i = '<a href="javascript:void(0)" onclick="operator(\''+row.id+'\')">审核</a> ';
					return i; 
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
	
	
		 //审核
    function operator(id){
    
		   $('#dd').dialog({title: '审核',
            iconCls:"icon-edit",
            collapsible: true,
            minimizable: true,
            maximizable: true,
            resizable: true,
            width: 900,
            height:300,
            href: "reviewFwd?id="+id,
            modal: true
           });         
	    
	}
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
	   //var params = $('#userTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
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
	    window.location.href="${request.getContextPath()}/finance/reviewTx_export"+params;

	}
	
	//清空查询条件
	function clearForm(){
		$('#queryForm').form('clear');
		searchUser();
	}
	
	//打批次
    function addBatchNum(){
      var rows = $('#userTable').datagrid('getSelections');
      var batchValue = "";
	  $.each(rows, function(i, value){
			var val = value.id;
			if(i==0)
			   batchValue +=val;
			else
			   batchValue +=","+val;
				   
			});
	  $.ajax({  
			  type:"post",  
			  url:"addBatchNum?id="+batchValue,
			  success:function(data) {
			     var json = evel(data);
			     if(json=="success"){
			        alert("成功");
			     }
			  }
			  
	  });  
	}
    

</script>

</head>

<body>
 
<form id="queryForm">
   <div class="title_right"><strong>提现审核查询列表</strong></div>  
       <div style="width:900px; margin:auto">
     <table class="table table-bordered">
         <tr>
       <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">开始时间：</td>
     <td width="23%">
     <input type="text" class="laydate-icon span2-1" id="strDate" name="strDate" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"/></td>
     <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">结束时间：</td>
     <td width="23%">
     <input type="text" class="laydate-icon span2-1" id="endDate" name="endDate" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"/>
     </td>
     <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">商户编号：</td>
	     <td><input type="text" id="MERCHANT_CODE" name="MERCHANT_CODE" class=" span1-1" /></td>
	     <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">商户名称：</td>
	     <td><input type="text" id="merchant_name" name="merchant_name" class=" span1-1" /></td>
     </tr>
     <tr>
	     <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">流水号：</td>
	     <td><input type="text" id="serial" name="serial" class=" span1-1" /></td>
 
         <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">状态：</td>
         <td><select id="status" name="status">
          <option selected value="">全部</option>
          <option value="00">未审核</option>
          <option value="01">审核通过</option>
          <option value="02">审核不通过</option>
          <option value="03">已制批次</option>
          <option value="04">已到账</option>
          <option value="05">失败</option>
          </select></td>
     </tr>
       </table>
        <table  class="margin-bottom-20 table  no-border" >
         <tr>
     	<td class="text-center"><input type="button" value="查询" class="btn btn-info " style="width:80px;" onclick="searchUser();"/>
     	<input type="button" value="导出" class="btn btn-info " style="width:80px;" onclick="exportUser();"/>
     	<input type="button" value="打批次" class="btn btn-info " style="width:80px; " onclick="addBatchNum();"/>
     	
     	
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
