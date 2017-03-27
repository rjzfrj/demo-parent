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
			title:'消息模板', //标题
			method:'post',
			iconCls:'icon-save', //图标
			singleSelect:false, //多选
			width: 'auto',
			height:360, //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, //奇偶行颜色不同
			loadMsg:'数据加载中请稍后……', 
			collapsible:true,//可折叠
			url:'queryMsgTem', //数据来源
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
				{field:'merchant_code',title:'商户编号',sortable:true,width:30,
					formatter:function(value,row,index){return row.merchant_code;} 
				},
				{field:'public_id',title:'公众号原始id',sortable:true,width:30,
					formatter:function(value,row,index){return row.public_id;} 
				},
				{field:'template_id',title:'模板ID',sortable:true,
					formatter:function(value,row,index){return row.template_id;}
				},
				{field:'template_tpye',title:'模板类型',sortable:true,width:30,
					formatter:function(value,row,index){
					  var s ="";
					  if(row.template_tpye=="TM00055")
					      s="会员充值通知";
					  if(row.template_tpye=="TM00052")
					      s="消费通知";
					  if(row.template_tpye=="OPENTM201048309")
					      s="优惠券领取成功通知";
					  return s;
					
					}
				},
				{field:'remark',title:'备注',sortable:true,width:30,
					formatter:function(value,row,index){
						return row.remark;  
					}
				},
				{field:'status',title:'状态',sortable:true,hidden:true,
					formatter:function(value,row,index){
						return row.status;  
					}
				},
				{field:'operator',title:'操作',sortable:true,width:30,
					formatter:function(value,row,index){
					var j = '<a href="javascript:void(0)" onclick="delrow(\''+row.id+'\')">删除</a> ';
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
            
         $.ajax({  
			  type:"post",  
			  url:"${request.getContextPath()}/pos/merchantName_List",  
			  success:function(data) {
				  var json = eval(data);
				  $("<option value=''>请选择</option>").appendTo("#merchant_id"); 
				  $.each(json,function(i,value){
				  //根据json格式的不同，取值的方式也不一样
				     var id = value.merchant_code;
				     var name = value.merchant_name;
				      //var id = json[i].MERCHANT_CODE;
				      //var name = json[i].merchant_name; 
					  $("<option value="+id+">"+name+"</option>").appendTo("#merchant_id");  
					  }  
					);   
				}  
			 });
	});
	
	function getMerchantCodeModify(merchantcode){
	        $.ajax({  
			  type:"post",  
			  url:"queryMemberInfo?merchant_code="+merchantcode,  
			  success:function(data) {
				     var public_id = data;
					 $('#public_id').val(public_id);  
				}  
			 });
	}
    //打开新增界面
    function addMsg(){
          $('#add').show().dialog({
		    title: '消息模板增加',
		    width: 600,
		    height: 450,
		    closed: false,
		    cache: false,
		    modal: true
          }); 
 
	}
	//新增
    function addMsgTem(){
       var params = $('#msgForm').serialize();
       //alert(params);
       var merchant_id = $.trim($('#merchant_id').val());
       var public_id = $.trim($('#public_id').val());
       var template_id = $.trim($('#template_id').val());
       if(merchant_id==''){
          alert("商户名称不能为空");
          return false;
       }
       if(public_id==''){
          alert("公众号原始id不能为空");
          return false;
       }
       if(template_id==''){
          alert("模板ID不能为空");
          return false;
       }
       $.ajax({  
			  type:"post",  
			  url:"addMsgtemplate?"+params,  
			  success:function(data) {
			  //alert("data:"+data)
				    if(data=="success"){
				       alert("新增成功");
				       window.location.href="${request.getContextPath()}/msg/queryMsgTemFowd";
				    }else{
				       alert("新增失败");
				    }
				}  
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

	//清空查询条件
	function clearForm(){
		$('#queryForm').form('clear');
		searchUser();
	}
	
	  
  //删除
  	function delrow(id){  	
	  if(confirm("您确定要删除该记录？")){
       	var ps = "?id="+id;
       	 $.ajax({  
			  type:"post",  
			  url:"delete"+ps,  
			  success:function(data) {
			  //alert("data:"+data);
				    if(data=="success"){
				       alert("删除成功");
				       window.location.href="${request.getContextPath()}/msg/queryMsgTemFowd";
				    }else{
				       alert("删除失败");
				    }
				}  
			 });
         }
  	}
  	
  		//返回
    function comBack(id){
        
        $('#add').dialog('close');
        //window.location.href="${request.getContextPath()}/msg/queryMsgTemFowd";
	}

</script>

</head>

<body>
 
<form id="queryForm">
   <div class="title_right"><strong>查询消息模板</strong></div>  
       <div style="width:100% margin:auto">
       <table class="table table-bordered">
         <tr>
	     <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">商户编号：</td>
	     <td colspan="3"><input type="text" id="merchant_code" name="merchant_code" class=" span1-1" /></td>
     </tr>
       </table>
       <table class="margin-bottom-20 table  no-border" >
        <tr>
     	<td class="text-center"><input type="button" value="查询" class="btn btn-info " style="width:80px;" onclick="searchUser();"/>
     	<input type="button" value="新增" id="exportId" class="btn btn-info " style="width:80px;" onclick="addMsg();"/>
     	</td>
     </tr>
 </table>
     <table id="userTable"></table>
   </div>
  </form>
  <div id="add" class="easyui-dialog" title="" closed="true">
   <form action="${request.getContextPath()}/msg/addMsgtem" method="post" id="msgForm">
     <div class="modal-body">
     <table class="table table-bordered">
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">商户名称：</td>
       <td colspan="3"><select id="merchant_id" name="merchant_id" 
           onChange="getMerchantCodeModify(this.options[this.selectedIndex].value)">
          </select></td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">公众号原始id：</td>
       <td colspan="3"><input type="text" name="public_id" id="public_id" class="span3" readonly="readonly"/></td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">模板ID：</td>
       <td colspan="3"><input type="text" name="template_id" id="template_id" class="span3" /></td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">模板类型：</td>
       <td colspan="3"><select id="template_type" name="template_type">
          <option value="TM00055">会员充值通知</option>
          <option value="TM00052">消费通知</option>
          <option value="OPENTM201048309">优惠券领取成功通知</option>
          </select>
       </td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">备注：</td>
       <td colspan="3"><input type="text" name="remark" id="remark" class="span3" /></td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">状态：</td>
       <td colspan="3"><select id="status" name="status">
          <option value="00">正常</option>
          <option value="01">已废弃</option>
          </select></td>
     </tr>
   </table>
   <table class="margin-bottom-20 table  no-border" >
        <tr>
     	<td class="text-center"><input type="button" value="提交" class="btn btn-info " style="width:80px;" onclick="addMsgTem();"/>
     	<input type="button" value="返回" class="btn btn-info " style="width:80px;" onclick="comBack();"/>
     	</td>
     </tr>
 </table>
</div>
</form>
</div>

</body>
</html>
