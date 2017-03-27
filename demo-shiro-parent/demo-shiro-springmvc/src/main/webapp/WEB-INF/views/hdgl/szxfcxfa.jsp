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
			title:'设置消费促销方案', //标题
			method:'post',
			iconCls:'icon-edit', //图标
			singleSelect:false, //多选
			width: 'auto',
			height:360, //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, //奇偶行颜色不同
			loadMsg:'数据加载中请稍后……', 
			collapsible:true,//可折叠
			url:'xfSchema', //数据来源
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
				{field:'MERCHANT_CODE',title:'商户编号',sortable:true,hidden:true,
					formatter:function(value,row,index){return row.MERCHANT_CODE;}
				},
				{field:'rule',title:'消费金额(元)',sortable:true,
					formatter:function(value,row,index){return row.rule;}
				},
				{field:'reward',title:'优惠券金额(元)',sortable:true,
					formatter:function(value,row,index){
						return row.reward;  
					}
				},
				{field:'valid_date',title:'有效期(天)',sortable:true,
					formatter:function(value,row,index){
						return row.valid_date;  
					}
				},
				{field:'start_time',title:'生效日期',sortable:true,
					formatter:function(value,row,index){
						return row.start_time; 
					}
				},
				{field:'operator',title:'操作',sortable:true,
					formatter:function(value,row,index){
					var i = '<a href="javascript:void(0)" onclick="delSchema(\''+row.id+'\')">删除</a> ';
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
            
        $("#stilValid").change(function(){
		  if($("#stilValid").prop("checked")){
		     //
		     $("#validDay").attr("value","");
		     $("#validDay").attr("readonly","readonly");
		  }else{
		     $("#validDay").attr("readonly",false);
		  }
		});
	    $.ajax({  
			  type:"post",  
			  url:"${request.getContextPath()}/pos/merchantName_List",  
			  success:function(data) {
			  var json = eval(data);
			  $("<option value=''>请选择</option>").appendTo("#merchantCode"); 
			  $.each(json,function(i,value){
			  //根据json格式的不同，取值的方式也不一样
			     var id = value.merchant_code;
			     var name = value.merchant_name; 
			      //var id = json[i].MERCHANT_CODE;
			      //var name = json[i].merchant_name; 
				  $("<option value="+id+">"+name+"</option>").appendTo("#merchantCode");  
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
       alert(params);
	   window.location.href="${request.getContextPath()}/finance/batchTx_export";

	}
	
	//清空查询条件
	function clearForm(){
		$('#queryForm').form('clear');
		searchUser();
	}
	
	//新增--界面
  function openXf(){
   
   $('#dd').show().dialog({
		    title: '设置消费促销方案',
		    width: 600,
		    height: 180,
		    closed: false,
		    cache: false,
		    modal: true
          }); 
 
	}
 function delSchema(id){
	 if(confirm("您确定要删除该记录？")){
	    $.ajax({type: "post",url: "delSchema",data: "id="+id,
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
	//返回
   function comBack(){
        //window.location.href="${request.getContextPath()}/activity/xfSchemaFowd";
        $('#dd').dialog('close');
	}
	var i=0
	function addXf(){
	   i++;
	   var s = "sub"+i;
	   var domEle = "<div id='xf'>消费金额:<input type='text' name='xfAmount' id ='xfAmount' class='span1' />~<input type='text' name='xfAmount' id ='xfAmount' class='span1'/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;赠送金额：<input type='text' name='xfAmount' id ='xfAmount' class='span1-1'/>&nbsp;<input type='button' name="+s+" id ="+s+" class='span1' value='-' onclick='delXf(this.id)'/></div>";
       $("#xfAdd").after(domEle);
	}
	function delXf(s){
	 $('#'+s).parent().remove();
	}
	
	function xfSchemaTj(){
	  var xfAmount = $("input[name='xfAmount']");
	  var reg =/^((\d+\.\d{1,2})|(\d+))$/;
	  for(var i=0;i<xfAmount.length;i++){
	     var amount = $.trim(xfAmount[i].value);
	     if(amount=="" || amount==null){
	        alert("金额不能为空");
	        return;
	     }
	     if(!reg.test(amount)){
	        alert("输入金额必须为正整数");
	        return;
	     }
  
	  }
	  var amount1 = $.trim(xfAmount[0].value);
	  var amount2 = $.trim(xfAmount[1].value);
	  if(amount2==amount1){
	     alert("消费金额不能相等");
	     return;
	  }
	  if(eval(amount1)>eval(amount2)){
	     alert("消费金额区间不正确");
	     return;
	  }
     var merchantCode = $("#merchantCode").val();
	 var validDay = $.trim($("#validDay").val());
	 var stilValid = $("#stilValid").attr("checked");
	 //var validDate = $("#validDate").val();
	 if(merchantCode==''){
	   alert("商户编号不能为空");
       return;
	 }
     if(validDay=='' && stilValid!="checked"){
       alert("优惠券有效期不能为空");
       return;
     }
     if(!reg.test(validDay) && stilValid!="checked"){
	   alert("优惠券有效期必须为整数");
	   return;
	 }
    // if(validDate==''){
      // alert("方案生效日期不能为空");
     //  return;
    // }
    // var start=new Date(validDate.replace("-", "/").replace("-", "/"));
     //var mydate = new Date();
    //// if(mydate>start){
    //   alert("方案生效日期不能小于当前日");
     //  return;
    // }
	 var url= "${request.getContextPath()}/activity/setXfSchema";
	 var parms = $('#userForm').serialize(); //自动序列化表单元素为JSON对象
	 //alert("parms:"+parms);
	 //return;
	 $.ajax({
     type: "post",
     url: url,
     data: parms,
     success: function(msg){
       if(msg=="success"){
         window.location.href="${request.getContextPath()}/activity/xfSchemaFowd";
       }else if(msg=="checkSuccess"){
         alert("该消费优惠已存在,请重新输入");
       }
      }
     });
	}
</script>

</head>

<body>
 
<form id="queryForm">
   <div class="title_right"><strong>设置消费促销方案</strong></div>  
       <div style="width:900px; margin:auto">
     <table class="table table-bordered">
        <tr>
     <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">生效时间：</td>
     <td >
     <input type="text" class="laydate-icon span2-1" id="strDate" name="strDate" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"/>
     ~
     <input type="text" class="laydate-icon span2-1" id="endDate" name="endDate" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"/>
     </td>
         
     </tr>

       </table>
        <table  class="margin-bottom-20 table  no-border" >
         <tr>
     	<td class="text-center"><input type="button" value="查询" class="btn btn-info " style="width:80px;" onclick="searchUser();"/>
     	<input type="button" value="新增" class="btn btn-info " style="width:80px;" onclick="openXf();"/>
     	</td>
     </tr>
 </table>
      <table id="userTable"></table>
    
   </div>
 </form>
<div id="dd" class="easyui-dialog" title="消费方案" closed="true">
       <form id="userForm" action="${request.getContextPath()}/activity/setXfSchema" method="post">
     <div class="modal-body">
                  选择商家：<select id="merchantCode" name="merchantCode">
          </select>
     <!--  
     <div id="xfAdd">
                 消费金额:<input type="text" name="xfAmount" id="xfAmount" class="span1" />~<input type="text" name="xfAmount" id="xfAmount" class="span1"/>
       &nbsp;&nbsp;&nbsp;&nbsp;赠送金额：
       <input type="text" name="xfAmount" id="xfAmount" class="span1-1" />
     </div>
     -->
     <table class="table table-bordered">     
      <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">消费金额：</td>
       <td>
         <input type="text" name="xfAmount" id="xfAmount" class="span1" />
         ~<input type="text" name="xfAmount" id="xfAmount" class="span1"/></td>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">赠送金额：</td>
       <td>
         <input type="text" name="xfAmount" id="xfAmount" class="span1-1" />
       </td> 
     </tr> 
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">优惠券有效期：</td>
       <td colspan="3">
       <input type="text" name="validDay" id="validDay" class="span2" readonly="readonly"/>天
       <input type="checkbox" name="stilValid" id="stilValid" value="长期有效" checked="checked"/>长期有效
       </td> 
     </tr>
     <!-- 
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">方案生效日期：</td>
       <td><input type="text" class="laydate-icon span2-1" id="validDate" name="validDate" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" />
       </td> 
     </tr>
      -->
   </table>
   <table class="margin-bottom-20 table  no-border" >
        <tr>
     	<td class="text-center"><input type="button" value="确定" class="btn btn-info " style="width:80px;" onclick="xfSchemaTj();"/>
     	<input type="button" value="返回" class="btn btn-info " style="width:80px;" onclick="comBack();"/>
     	</td>
     </tr>
 </table>
</div>
</form>
   </div>
</body>
</html>
