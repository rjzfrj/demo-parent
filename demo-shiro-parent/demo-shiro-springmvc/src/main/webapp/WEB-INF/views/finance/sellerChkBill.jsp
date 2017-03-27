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
			title:'商家对账单', //标题
			method:'post',
			iconCls:'icon-edit', //图标
			singleSelect:false, //多选
			width: 'auto',
			height:360, //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, //奇偶行颜色不同
			loadMsg:'数据加载中请稍后……', 
			collapsible:true,//可折叠
			url:'sellerChkBill', //数据来源
			//sortName: 'create_time', //排序的列
			//sortOrder: 'desc', //倒序
			remoteSort: false, //服务器端排序
			//idField:'create_time', //主键字段
			queryParams:{}, //查询条件
			pagination:true, //显示分页
			//rownumbers:true, //显示行号
			columns:[[
			    {field:'ck',checkbox:true,width:2}, //显示复选框
				{field:'create_time',title:'对账时间',sortable:true,
					formatter:function(value,row,index){return row.create_time;}
				},
				{field:'merchant_code',title:'商户编号',sortable:true,
					formatter:function(value,row,index){
						return row.merchant_code;  
					}
				},
				{field:'merchant_name',title:'商户名称',sortable:true,
					formatter:function(value,row,index){
						return row.merchant_name;  
					}
				},
				{field:'sumorderamt',title:'订单金额(元)',sortable:true,
					formatter:function(value,row,index){
						return row.sumorderamt;  
					}
				},
				{field:'sumpound',title:'手续费',sortable:true,
					formatter:function(value,row,index){
						return row.sumpound; 
					}
				},
				{field:'yhamt',title:'优惠金额(元)',sortable:true,
					formatter:function(value,row,index){
						return row.yhamt; 
					}
				},
				{field:'sumtranamt',title:'实际结算(元)',sortable:true,
					formatter:function(value,row,index){
						return row.sumtranamt; 
					}
				},
				{field:'wetamt',title:'微信收款(元)/笔数',sortable:true,
					formatter:function(value,row,index){
						return (row.wetamt==null?"":row.wetamt)+"/"+(row.wetnum==0?"":row.wetnum+"笔"); 
					}
				},
				{field:'zfbamt',title:'支付宝收款(元)/笔数',sortable:true,
					formatter:function(value,row,index){
						return (row.zfbamt==null?"":row.zfbamt)+"/"+(row.zfbnum==0?"":row.zfbnum+"笔"); 
					}
				},
				{field:'cardamt',title:'银行卡收款(元)/笔数',sortable:true,
					formatter:function(value,row,index){
						return (row.cardamt==null?"":row.cardamt)+"/"+(row.cardnum==0?"":row.cardnum+"笔"); 
					}
				},
				{field:'check_result',title:'对账结果',sortable:true,
					formatter:function(value,row,index){
					    var s ="";
					    if(row.check_result =="00"){
					      s='差错未处理';
					    }else if(row.check_result =="01"){
					      s='差错已处理';
					    }else{
					      s='正常';
					    }
						return s; 
					}
				},
				{field:'review_result',title:'审核结果',sortable:true,
					formatter:function(value,row,index){
					    var s ="";
					    if(row.review_result =="00"){
					      s='未审核';
					    }else if(row.review_result =="01"){
					      s='审核通过';
					    }else{
					      s='审核不通过';
					    }
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
	

	
		 //审核
    function review(){
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
       var create_time = row.create_time;
       var MERCHANT_CODE = row.MERCHANT_CODE;
       var check_result = row.check_result;
       var review_result = row.review_result;
        $("#create_time1").val(create_time);
        $("#MERCHANT_CODE1").val(MERCHANT_CODE);
        $("#check_result1").val(check_result);
        $("#review_result1").val(review_result);

      $('#dd').show().dialog({
		    title: '对账审核',
		    width: 400,
		    height: 270,
		    closed: false,
		    cache: false,
		    modal: true
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
   function exportExcel(){
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
       //alert(params);
	   window.location.href="${request.getContextPath()}/finance/sellerExportBill"+params;

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
   <div class="title_right"><strong>商家对账单</strong></div>
       <div style="width:1000px; margin:auto">
     <table class="table table-bordered">
         <tr>
      <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">开始时间：</td>
     <td width="23%">
     <input type="text" class="laydate-icon span2-1" id="strDate" name="strDate" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"/></td>
     <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">结束时间：</td>
     <td width="23%">
     <input type="text" class="laydate-icon span2-1" id="endDate" name="endDate" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"/>
     </td><td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">商户编号：</td>
	     <td><input type="text" id="MERCHANT_CODE" name="MERCHANT_CODE" class=" span1-1" maxlength="8"/></td>
	     <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">商户名称：</td>
	     <td><input type="text" id="merchant_name" name="merchant_name" class=" span1-1" /></td>
     </tr>
     <tr>
         <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">对账结果：</td>
         <td><select id="check_result" name="check_result" style="height:25px">
          <option value="">全部</option>
          <option value="00">差错未处理</option>
          <option value="01">差错已处理</option>
          <option value="02">正常</option>
          </select></td>
          <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">审核结果：</td>
         <td><select id="review_result" name="review_result" style="height:25px">
          <option value="">全部</option>
          <option value="00">未审核</option>
          <option value="01">审核通过</option>
          <option value="02">审核不通过</option>
          </select></td>
     </tr>
       </table>
        <table  class="margin-bottom-20 table  no-border" >
         <tr>
     	<td class="text-center"><input type="button" value="查询" class="btn btn-info " style="width:80px;" onclick="searchUser();"/>
     	<input type="button" value="导出" class="btn btn-info " style="width:80px;" onclick="exportExcel();"/>
     	<input type="button" value="审核" class="btn btn-info " style="width:80px;" onclick="review();"/>

     	
     	
     	</td>
     </tr>
 </table>
   <table id="userTable"></table>
   </div>
 </form>
 <div id="dd" class="easyui-dialog" title="对账审核" closed="true">
       <form action="${request.getContextPath()}/finance/dzreview" method="post">
      
       <input type="hidden" name="MERCHANT_CODE1" id="MERCHANT_CODE1" />
       <input type="hidden" name="check_result1" id="check_result1" />
       <input type="hidden" name="review_result1" id="review_result1" />
        <input type="hidden" name="create_time1" id="create_time1" />
     <div class="title_right"><strong></strong></div>
     <table class="table table-bordered" >
     <tr>
       <td colspan="2" align="center" nowrap="nowrap" bgcolor="#f1f1f1">
       <input type="radio" name="reviewPass" value="01"/>通过&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       <input type="radio" name="reviewPass" value="02"/>不通过</td>
     </tr>

      <tr>
       <td colspan="2" align="center" nowrap="nowrap" bgcolor="#f1f1f1">原因
       <textarea rows="5" cols="30" name="reason">
        
       </textarea>
       </td>
     </tr>
     </table>
   <table class="margin-bottom-20 table no-border" >
        <tr>
     	<td class="text-center"><input type="submit" value="确定" class="btn btn-info " style="width:80px;"/>
     	<input type="button" value="返回" class="btn btn-info " style="width:80px;" onclick="comBack();"/>
     	</td>
     </tr>
 </table>
</form>
   </div>
</body>
</html>
