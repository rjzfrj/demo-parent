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
			title:'差错账处理', //标题
			method:'post',
			iconCls:'icon-edit', //图标
			singleSelect:false, //多选
			width: 'auto',
			height:360, //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, //奇偶行颜色不同
			loadMsg:'数据加载中请稍后……', 
			collapsible:true,//可折叠
			url:'accBillManage', //数据来源
			sortName: 'id', //排序的列
			sortOrder: 'desc', //倒序
			remoteSort: false, //服务器端排序
			idField:'id', //主键字段
			queryParams:{}, //查询条件
			pagination:true, //显示分页
			//rownumbers:true, //显示行号
			columns:[[
			    {field:'ck',checkbox:true,width:2}, //显示复选框
			    {field:'id',title:'主键',sortable:true,hidden:true,
					formatter:function(value,row,index){
						return row.id;  
					}
				},
				{field:'MERCHANT_CODE',title:'商户编号',sortable:true,
					formatter:function(value,row,index){
						return row.MERCHANT_CODE;  
					}
				},
				{field:'merchant_name',title:'商户名称',sortable:true,
					formatter:function(value,row,index){
						return row.merchant_name;  
					}
				},
				{field:'serial',title:'流水号',sortable:true,
					formatter:function(value,row,index){
						return row.serial;  
					}
				},
				{field:'settlement_date',title:'交易时间',sortable:true,
					formatter:function(value,row,index){
						return row.settlement_date; 
					}
				},
				{field:'card_number',title:'账号',sortable:true,
					formatter:function(value,row,index){
						return row.card_number; 
					}
				},
				{field:'business_type',title:'收款渠道',sortable:true,
					formatter:function(value,row,index){
						return row.business_type; 
					}
				},
				{field:'tran_amt',title:'交易金额（元）',sortable:true,
					formatter:function(value,row,index){
						return row.tran_amt; 
					}
				},
				{field:'client_id',title:'终端号',sortable:true,
					formatter:function(value,row,index){
						return row.client_id; 
					}
				},
				{field:'check_result',title:'对账类型',sortable:true,
					formatter:function(value,row,index){
						return row.check_result; 
					}
				},
				{field:'review_result',title:'处理结果',sortable:true,
					formatter:function(value,row,index){
						return row.review_result; 
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
	
	//处理
    function manageAcc(){
       var checks = $("[type='checkbox']:checked");

      if(checks.length ==0){
         alert("请选择一条数据！");
         return;
      }
       if(checks.length >1){
         alert("只能选择一条数据！");
         return;
      }
       var row = $('#userTable').datagrid('getSelected');
       	$.ajax({  
			  type:"post",  
			  url:"accManage",
			  data: "id="+row.id,
			  success:function(data) {
			     // alert("成功！");
			      window.location.href="${request.getContextPath()}/finance/accManageFowd";
			  }
			 });        
	   
	}

    //表格查询
	function searchUser(){
		var strDate = $("#strDate").val();
	    var endDate = $("#endDate").val();
	    
	    if(strDate!='' && endDate==''){
		    alert("结束日期不能为空");
		    return;
        }
        if(strDate=='' && endDate!=''){
		    alert("开始日期不能为空");
		    return;
        }
	    
        if(strDate!='' && endDate!=''){
           var startDate=new Date(strDate.replace("-", "/").replace("-", "/"));
	       var eDate=new Date(endDate.replace("-", "/").replace("-", "/"));
	       if(startDate>eDate){
		       alert("开始日期不能大于结束日期");
		       return;
	       }
        }
		var params = $('#userTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
		var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
		$.each( fields, function(i, field){
			params[field.name] = field.value; //设置查询参数
		}); 
		$('#userTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
	}
   function exportUser(){
	    var strDate = $("#strDate").val();
	    var endDate = $("#endDate").val();
	    
	    if(strDate!='' && endDate==''){
		    alert("结束日期不能为空");
		    return;
        }
        if(strDate=='' && endDate!=''){
		    alert("开始日期不能为空");
		    return;
        }
	    
        if(strDate!='' && endDate!=''){
           var startDate=new Date(strDate.replace("-", "/").replace("-", "/"));
	       var eDate=new Date(endDate.replace("-", "/").replace("-", "/"));
	       if(startDate>eDate){
		       alert("开始日期不能大于结束日期");
		       return;
	       }
        }
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
       alert(params);
	   window.location.href="${request.getContextPath()}/finance/accExport"+params;

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
   <div class="title_right"><strong>差错账处理</strong></div>
       <div style="width:900px; margin:auto">
     <table class="table table-bordered">
         <tr>
     <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">开始时间：</td>
     <td >
     <input type="text" class="laydate-icon span2-1" id="strDate" name="strDate" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"/></td>
     <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">结束时间：</td>
     <td >
     <input type="text" class="laydate-icon span2-1" id="endDate" name="endDate" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"/>
     </td> 
     <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">商户编号：</td>
	     <td><input type="text" id="MERCHANT_CODE" name="MERCHANT_CODE" class=" span1-1" maxlength="8"/></td>
	     <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">商户名称：</td>
	     <td><input type="text" id="merchant_name" name="merchant_name" class=" span1-1" /></td>
     </tr>
     <tr>
         <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">流水号：</td>
	     <td><input type="text" id="serial" name="serial" class=" span1-1" /></td>
         <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">对账类型：</td>
         <td><select id="check_result" name="check_result" style="height:25px">
          <option value="">全部</option>
          <option value="00">长款</option>
          <option value="01">短款</option>
          <option value="02">错账</option>
          <option value="02">其他</option>
          </select></td>
          <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">处理结果：</td>
         <td><select id="review_result" name="review_result" style="height:25px">
          <option value="">全部</option>
          <option value="00">差错未处理</option>
          <option value="01">差错已处理</option>
          </select></td>
     </tr>
       </table>
        <table  class="margin-bottom-20 table  no-border" >
         <tr>
     	<td class="text-center"><input type="button" value="查询" class="btn btn-info " style="width:80px;" onclick="searchUser();"/>
     	<input type="button" value="导出" class="btn btn-info " style="width:80px;" onclick="exportUser();"/>
     	<input type="button" value="处理" class="btn btn-info " style="width:80px;" onclick="manageAcc();"/>

     	
     	
     	</td>
     </tr>
 </table>
      <table id="userTable"></table>
   
   </div>
 </form>
 <div id="dd">
       
    </div>
</body>
</html>
