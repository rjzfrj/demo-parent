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
<script type="text/javascript" src="../resources/js/ajaxfileupload.js"></script>
<script type="text/javascript" >
jQuery(function($){
$('#userTable').datagrid({
			title:'pos机入库', //标题
			method:'post',
			iconCls:'icon-edit', //图标
			singleSelect:false, //多选
			width: 'auto',
			height:360, //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, //奇偶行颜色不同
			loadMsg:'数据加载中请稍后……', 
			collapsible:true,//可折叠
			url:'posrk', //数据来源
			sortName: 'create_time', //排序的列
			sortOrder: 'desc', //倒序
			remoteSort: false, //服务器端排序
			idField:'id', //主键字段
			queryParams:{}, //查询条件
			pagination:true, //显示分页
			//rownumbers:true, //显示行号
			columns:[[
				{field:'id',title:'主键',width:30,sortable:true,hidden:true,
					formatter:function(value,row,index){return row.id;}
				},
				{field:'pos_sn',title:'SN码',sortable:true,
					formatter:function(value,row,index){
						return row.pos_sn;  
					}
				},
				{field:'factory_name',title:'厂家',width:30,sortable:true,
					formatter:function(value,row,index){
						return row.factory_name;  
					}
				},
				{field:'pos_model',title:'型号',width:30,sortable:true,
					formatter:function(value,row,index){
						return row.pos_model;  
					}
				},
				{field:'communication',title:'通讯模式',width:30,sortable:true,
					formatter:function(value,row,index){
						return row.communication; 
					}
				},
				{field:'remark',title:'备注',width:30,sortable:true,
					formatter:function(value,row,index){
						return row.remark; 
					}
				},
				{field:'create_time',title:'入库时间',width:30,sortable:true,
					formatter:function(value,row,index){
						return row.create_time; 
					}
				},
				{field:'status',title:'状态',sortable:true,width:30,hidden:true,
					formatter:function(value,row,index){
					    var s="";
					    if(row.status=="00")
					      s="未分配";
					    else
					      s="已分配"
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
	function searchPos(){
		var params = $('#userTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
		var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
		$.each( fields, function(i, field){
			params[field.name] = field.value; //设置查询参数
		}); 
		$('#userTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
	}
	//带查询条件导出
   function exportPos(){
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
        window.location.href="${request.getContextPath()}/pos/posrk_export"+params;

	}
	//导入Excel
	  function importExcel(){
          $('#dd').show().dialog({
		    title: '导入pos信息',
		    width: 420,
		    height: 180,
		    closed: false,
		    cache: false,
		    modal: true
          }); 
        // $('#dd').dialog('open');//当div中带样式时使用该方法，不带样式使用上面的方法

	}
	//导出Excel,全部
   function exportExcel(){
   
      window.location.href="${request.getContextPath()}/pos/posrk_export";

	}
	//下载模板
	function downLoad(){
       window.location.href="${request.getContextPath()}/pos/posrk_style";
	}

	//清空查询条件
	function clearForm(){
		$('#queryForm').form('clear');
		searchUser();
	}
	//pos机信息导入
    function posSubmit(){
        var file =  $('#file').val();
        if(file==''){
          alert("导入文件不能为空");
          return;
        }
		$("#posImportForm").submit();
    }
   	//返回
   function comBack(){
       $('#dd').dialog('close');
   }
   


</script>

</head>

<body>
 
<form id="queryForm">
   <div class="title_right"><strong>pos机入库</strong></div>  
       <div style="width:100% margin:auto">
     <table class="table table-bordered">
         <tr>
         
         <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">SN码：</td>
	     <td><input type="text" id="pos_sn" name="pos_sn" class=" span1-1" /></td>
	     <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">厂家：</td>
	     <td><input type="text" id="factory_name" name="factory_name" class=" span1-1" /></td>
     </tr>
     <tr>
	     <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">型号：</td>
	     <td><input type="text" id="pos_model" name="pos_model" class=" span1-1" /></td>
 
         <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">通讯模式：</td>
         <td><input type="text" id="communication" name="communication" class=" span1-1" /></td>
     </tr>
       </table>
        <table  class="margin-bottom-20 table  no-border" >
         <tr>
     	<td class="text-center"><input type="button" value="查询" class="btn btn-info " style="width:80px;" onclick="searchPos();"/>
     	<input type="button" value="导入Excel" class="btn btn-info " style="width:80px;" onclick="importExcel();"/>
     	<input type="button" value="导出Excel" class="btn btn-info " style="width:80px;" onclick="exportPos();"/>
     	<a href="javascript:void(0)" onclick="downLoad();">下载模板</a>
     	
     	
     	</td>
     </tr>
 </table>
 <table id="userTable"></table>
    
   </div>
 </form>
 
<div id="dd" class="easyui-dialog" title="导入" closed="true">
   <form action="${request.getContextPath()}/pos/posrk_import" method="post" enctype="multipart/form-data" id="posImportForm">
     <div class="modal-body">
     <table class="table table-bordered">
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">导入文件：</td>
       <td colspan="3"><input type="file" name="file" id="file" class="span4" /></td>
     </tr>
   </table>
   <table class="margin-bottom-20 table  no-border" >
        <tr>
     	<td class="text-center"><input type="button" value="提交" class="btn btn-info " style="width:80px;" onclick="posSubmit();"/>
     	<input type="button" value="返回" class="btn btn-info " style="width:80px;" onclick="comBack();"/>
     	</td>
     </tr>
 </table>
</div>
</form>
</div>
</body>
</html>
