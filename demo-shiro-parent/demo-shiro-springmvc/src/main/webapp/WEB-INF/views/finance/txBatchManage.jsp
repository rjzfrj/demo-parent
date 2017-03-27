
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
			title:'提现批次处理', //标题
			method:'post',
			iconCls:'icon-edit', //图标
			singleSelect:false, //多选
			width: 'auto',
			height:360, //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, //奇偶行颜色不同
			loadMsg:'数据加载中请稍后……', 
			collapsible:true,//可折叠
			url:'txBatch', //数据来源
			sortName: 'id', //排序的列
			sortOrder: 'desc', //倒序
			remoteSort: false, //服务器端排序
			idField:'id', //主键字段
			queryParams:{}, //查询条件
			pagination:true, //显示分页
			//rownumbers:true, //显示行号
			columns:[[
				{field:'batch_number',title:'批次号',width:30,sortable:true,
					formatter:function(value,row,index){return row.batch_number;}
				},
				{field:'update_time',title:'提交时间',width:30,sortable:true,
					formatter:function(value,row,index){
						return row.update_time;  
					}
				},
				{field:'operator',title:'操作员',width:30,sortable:true,
					formatter:function(value,row,index){
						return row.operator;  
					}
				},
				{field:'sum_amt',title:'总金额',width:30,sortable:true,
					formatter:function(value,row,index){
						return row.sum_amt;  
					}
				},
				{field:'sum_num',title:'总笔数',width:30,sortable:true,
					formatter:function(value,row,index){
						return row.sum_num; 
					}
				},
				{field:'succ_num',title:'成功笔数',width:30,sortable:true,
					formatter:function(value,row,index){
						return row.succ_num; 
					}
				},
				{field:'succ_amt',title:'成功金额',width:30,sortable:true,
					formatter:function(value,row,index){
						return row.succ_amt; 
					}
				},
				{field:'fail_num',title:'失败笔数',width:30,sortable:true,
					formatter:function(value,row,index){
						return row.fail_num; 
					}
				},
				{field:'fail_amt',title:'失败金额',width:30,sortable:true,
					formatter:function(value,row,index){
						return row.fail_amt; 
					}
				},
				{field:'business_result',title:'状态',width:30,sortable:true,
					formatter:function(value,row,index){
						return row.business_result; 
					}
				},
				{field:'operator',title:'操作',width:30,sortable:true,
					formatter:function(value,row,index){
					var i = '<a href="javascript:void(0)" onclick="upLoad()">上送</a> ';
					var j = '<a href="javascript:void(0)" onclick="detail(\''+row.batch_number+'\')">明细</a> ';
					    return i+j; 
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
	
		 //上送
    function upLoad(){
    
		   $('#dd').dialog({title: '上送',
            iconCls:"icon-edit",
            collapsible: true,
            minimizable: true,
            maximizable: true,
            resizable: true,
            width: 900,
            height:300,
            href: "batchUpLoad",
            modal: true,
           });         
	    
	}
	//明细
	function detail(id){
    
		   $('#dd').dialog({title: '明细',
            iconCls:"icon-edit",
            collapsible: true,
            minimizable: true,
            maximizable: true,
            resizable: true,
            width: 900,
            height:300,
            href: "batchDetail?batch_number="+id,
            modal: true,
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
	    window.location.href="${request.getContextPath()}/finance/batchTx_export"+params;

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
   <div class="title_right"><strong>提现批次处理</strong></div>  
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
	     <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">批次号：</td>
	     <td><input type="text" id="batch_number" name="batch_number" class=" span1-1" /></td>
 
         <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">状态：</td>
         <td><select id="business_result" name="business_result">
          <option selected value="">全部</option>
          <option value="00">处理中</option>
          <option value="01">交易成功</option>
          <option value="02">交易失败</option>
          </select></td>
     </tr>
       </table>
        <table  class="margin-bottom-20 table  no-border" >
         <tr>
     	<td class="text-center"><input type="button" value="查询" class="btn btn-info " style="width:80px;" onclick="searchUser();"/>
     	<input type="button" value="导出" class="btn btn-info " style="width:80px;" onclick="exportUser();"/>
     	<!--  <input type="button" value="打批次" class="btn btn-info " style="width:80px;"/>-->
     	
     	
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
