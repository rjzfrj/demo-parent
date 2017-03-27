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
			title:'商户资金池交易明细查询列表', //标题
			method:'post',
			iconCls:'icon-edit', //图标
			singleSelect:false, //多选
			width: 'auto',
			height:360, //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, //奇偶行颜色不同
			loadMsg:'数据加载中请稍后……', 
			collapsible:true,//可折叠
			url:'sellerPool', //数据来源
			sortName: 'business_time', //排序的列
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
				{field:'business_time',title:'交易时间',sortable:true,
					formatter:function(value,row,index){return row.business_time;}
				},
				{field:'serial',title:'交易订单号',sortable:true,
					formatter:function(value,row,index){
						return row.serial;  
					}
				},
				{field:'SOURCE_SERIA',title:'金融基础交易流水号',sortable:true,
					formatter:function(value,row,index){
						return row.source_serial;  
					}
				},
				{field:'CHN_ORDER_NO',title:'渠道交易流水号',sortable:true,
					formatter:function(value,row,index){
						return row.chn_order_no;  
					}
				},
				{field:'merchant_name',title:'商户名称',sortable:true,
					formatter:function(value,row,index){return row.merchant_name;}
				},
				{field:'merchant_code',title:'商户编号',sortable:true,hidden:false,
					formatter:function(value,row,index){return row.merchant_code;}
				},
				{field:'order_amt',title:'交易金额(元)',sortable:true,
					formatter:function(value,row,index){
						return row.order_amt;  
					}
				},
				{field:'poundage',title:'手续费(元)',sortable:true,
					formatter:function(value,row,index){
						return row.poundage;  
					}
				},
				{field:'tran_amt',title:'结算金额(元)',sortable:true,
					formatter:function(value,row,index){
						return row.tran_amt;  
					}
				},
				{field:'business_type',title:'交易方式',sortable:true,
					formatter:function(value,row,index){
						 var s ="";
					    if(row.business_type=="00")
					      s="提现";
					    if(row.business_type=="01")
					      s="充值";
					    if(row.business_type=="02")
					      s="消费";
					    if(row.business_type=="03")
					      s="退款";
						return s; 
					}
				},
				{field:'business_result',title:'交易状态',sortable:true,
					formatter:function(value,row,index){
						 var s ="";
					    if(row.business_result=="00")
					      s="处理中";
					    else if(row.business_result=="01")
					      s="交易成功";
					    else if(row.business_result=="02")
					      s="交易失败";
						return s;  
					}
				}
			]],
			onLoadSuccess:
			function(){
				$('#userTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		        //$('#userTable').datagrid({onLoadSuccess:compute});

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

       
       //var data = $('#userTable').datagrid('getData');
       //var rows = $('#userTable').datagrid('getRows');//获取当前页收入列

       // $('#sumPoundage').val(data.total);
          //alert('总数据量:' + data.total);//注意你的数据源一定要定义了total，要不会为undefined，datagrid分页就是靠这个total定义
          //alert('当前页数据量:' + data.rows.length);

	});
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
			  url:"sellerSum"+params,  
			  success:function(data) {
				  var json = eval(data);
				  var sumTx = json.sumTx;
				  var sumXf = json.sumXf;
				  var sumPe = json.sumPe;
				  var sumCz = json.sumCz;
				  var sumCzSXF = json.sumCzSXF;
				  var sumXfSXF = json.sumXfSXF;
				  
				  $('#sumTx').val(sumTx);
	              $('#sumXf').val(sumXf);
	              $('#sumPe').val(sumPe);
	              $('#sumCz').val(sumCz);
	              $('#sumCzSXF').val(sumCzSXF);
	              $('#sumXfSXF').val(sumXfSXF);
				}  
			 });  
		
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
        window.location.href="${request.getContextPath()}/business/sellerPool_export"+params;

	}
	//
	//获取收入列之和
	function compute() {
        var rows = $('#userTable').datagrid('getRows');//获取当前页收入列
        //alert('1111111111:'+rows);
        var totalSr = 0;
        var totalZc = 0;
        for (var i = 0; i < rows.length; i++) {
            totalSr += rows[i]['tran_amt'];
            totalZc += rows[i]['poundage'];
        }
       // alert(totalSr);
       // alert(totalZc);
    }

	
	//清空查询条件
	function clearForm(){
		$('#queryForm').form('clear');
		searchUser();
	}
	
	var start = {
		    elem: '#strDate',
		    format: 'YYYY-MM-DD hh:mm:ss',
		    min: laydate.now(), //设定最小日期为当前日期
		    max: '2099-06-16 23:59:59', //最大日期
		    istime: true,
		    istoday: false,
		    choose: function(datas){
		         end.min = datas; //开始日选好后，重置结束日的最小日期
		         end.start = datas //将结束日的初始值设定为开始日
		    }
		};
		var end = {
		    elem: '#endDate',
		    format: 'YYYY-MM-DD hh:mm:ss',
		    min: laydate.now(),
		    max: '2099-06-16 23:59:59',
		    istime: true,
		    istoday: false,
		    choose: function(datas){
		        start.max = datas; //结束日选好后，重置开始日的最大日期
		    }
		};
		laydate(start);
		laydate(end);

</script>

</head>

<body>
 
<form id="queryForm">
	<div class="title_right"><strong>商户资金池交易明细查询列表</strong></div>  
	<div style="width:100% margin:auto">
	<table class="table table-bordered">
		<tr>
			<td align="right" nowrap="nowrap" bgcolor="#f1f1f1">开始时间：</td>
			<td >
				<input type="text" class="laydate-icon span2-1" id="strDate" name="strDate"/></td>
			<td align="right" nowrap="nowrap" bgcolor="#f1f1f1">结束时间：</td>
			<td>
				<input type="text" class="laydate-icon span2-1" id="endDate" name="endDate" />
			</td>
			
			<td align="right" nowrap="nowrap" bgcolor="#f1f1f1">状态：</td>
			<td>
				<select id="business_result" name="business_result" style="height:25px">
					<option value="">全部</option>
					<option value="00">处理中</option>
					<option value="01">交易成功</option>
					<option value="02">交易失败</option>
				</select>
			</td>
			<td align="right" nowrap="nowrap" bgcolor="#f1f1f1">交易方式：</td>
			<td>
				<select id="business_type" name="business_type" style="height:25px">
					<option value="">全部</option>
					<option value="00">提现</option>
					<option value="01">充值</option>
					<option value="02">消费</option>
				</select>
			</td>
		</tr>
		<tr>	     
			<td align="right" nowrap="nowrap" bgcolor="#f1f1f1">商户编号：</td>
			<td><input type="text" id="merchant_code" name="merchant_code" class=" span1-3" /></td>
			
			<td align="right" nowrap="nowrap" bgcolor="#f1f1f1">交易订单号：</td>
			<td><input type="text" id="serial" name="serial" class=" span1-3" /></td>
			<td align="right" nowrap="nowrap" bgcolor="#f1f1f1">交易流水号：</td>
			<td><input type="text" id="source_serial" name="source_serial" class=" span1-3" /></td>
		</tr>
	</table>	
	<table class="margin-bottom-20 table  no-border" >
		<tr>
			<td class="text-center"><input type="button" value="查询" class="btn btn-info " style="width:80px;" onclick="searchUser();"/>
			<input type="button" value="导出" class="btn btn-info " style="width:80px;" onclick="exportUser();"/>
			</td>
		</tr>
	</table>
	<table class="table-bordered">
		<tbody>
			<tr align="center">
				<td>总提现</td>
				<td>总消费入账金额</td>
				<td>总充值金额</td>
				<td>总充值手续费</td>
				<td>总消费手续费</td>
				<td>总手续费</td>
			</tr>
			<tr>
				<td><input type="text" id="sumTx" name="sumTx" class=" span1-1" /></td>
				<td><input type="text" id="sumXf" name="sumXf" class=" span1-1" /></td>
				<td><input type="text" id="sumCz" name="sumCz" class=" span1-1" /></td>
				<td><input type="text" id="sumCzSXF" name="sumCzSXF" class=" span1-1" /></td>
				<td><input type="text" id="sumXfSXF" name="sumXfSXF" class=" span1-1" /></td>
				<td><input type="text" id="sumPe" name="sumPe" class=" span1-1" /></td>
			</tr>
		</tbody>
	</table>
	<table id="userTable"></table>
	<div id="dd"></div>
	</div>
</form>
</body>
</html>
<script type="text/javascript" src="../resources/js/loadLayDate.js"></script>
