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
			title:'pos机出库', //标题
			method:'post',
			iconCls:'icon-edit', //图标
			singleSelect:false, //多选
			width: 'auto',
			height:360, //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, //奇偶行颜色不同
			loadMsg:'数据加载中请稍后……', 
			collapsible:true,//可折叠
			url:'posck', //数据来源
			sortName: 'update_time', //排序的列
			sortOrder: 'desc', //倒序
			remoteSort: false, //服务器端排序
			idField:'id', //主键字段
			queryParams:{}, //查询条件
			pagination:true, //显示分页
			//rownumbers:true, //显示行号
			columns:[[
				{field:'ck',checkbox:true,width:2}, //显示复选框
				{field:'id',title:'主键',sortable:true,hidden:true,
					formatter:function(value,row,index){return row.id;}
				},
				{field:'pos_sn',title:'SN码',sortable:true,
					formatter:function(value,row,index){
						return row.pos_sn;  
					}
				},
				{field:'merchant_name',title:'商户名称',sortable:true,width:30,
					formatter:function(value,row,index){
						return row.merchant_name;  
					}
				},
				{field:'factory_name',title:'厂家',sortable:true,width:30,
					formatter:function(value,row,index){
						return row.factory_name;  
					}
				},
				{field:'pos_model',title:'型号',sortable:true,width:30,
					formatter:function(value,row,index){
						return row.pos_model;  
					}
				},
				{field:'status',title:'状态',sortable:true,width:30,
					formatter:function(value,row,index){
					    var s="";
					    if(row.status=="00")
					      s="未分配";
					    else
					      s="已分配"
						return s; 
					}
				},
				{field:'update_time',title:'更新时间',sortable:true,width:30,
					formatter:function(value,row,index){
						return row.update_time; 
					}
				},
				{field:'operator',title:'操作',width:30,sortable:true,
					formatter:function(value,row,index){
						var i = '<a href="javascript:void(0)" onclick="delrow(\''+row.id+'\',\''+row.status+'\')">删除</a> '; 
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
          $.ajax({  
			  type:"post",  
			  url:"merchantName_List",  
			  success:function(data) {
			  var json = eval(data);
			  $("<option value=''>请选择</option>").appendTo("#merchantId");
			  $("<option value=''>请选择</option>").appendTo("#merchantCode");
			  $.each(json,function(i,value){
			  //根据json格式的不同，取值的方式也不一样
			     var id = value.merchant_code;
			     var name = value.merchant_name; 
			      //var id = json[i].MERCHANT_CODE;
			      //var name = json[i].merchant_name; 
				  $("<option value="+id+">"+name+"</option>").appendTo("#merchantId");
				  $("<option value="+id+">"+name+"</option>").appendTo("#merchantCode");  
				  }  
				);   
				}  
			 });

	});

	
    //表格查询
	function searchPosck(){
		var params = $('#userTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
		var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
		$.each( fields, function(i, field){
			params[field.name] = field.value; //设置查询参数
		}); 
		$('#userTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
	}
	//批量分配--界面
  function batchFp(){
    var rows = $('#userTable').datagrid('getSelections');
    if(rows.length==0){
      alert("请选择需分配的POS机！");
      return;
    }
    var k = 0;
    $.each(rows, function(i, value){
			var val = value.status;
			if(val=="01")
			{
			  alert("该POS机已分配！");
			  k++;
			  //value.ck=false;
			  //break;
			}
			   
		});
    if(k>0){
      return;
    }
   $('#operateType').val("fp");
   $('#dd').show().dialog({
		    title: '批量分配',
		    width: 420,
		    height: 180,
		    closed: false,
		    cache: false,
		    modal: true,
		    position:['top','right']
          }); 
 
	}
	//批量提交
   function batchTj(){
	var rows = $('#userTable').datagrid('getSelections');
	//alert("rows:"+rows[0].id);
	var batchValue = "";
	$.each(rows, function(i, value){
			//alert("rows:"+rows[i].id);
			//alert("rows:"+value.id);
			var val = value.id;
			if(i==0)
			   batchValue +=val;
			else
			   batchValue +=","+val;
			
		});
		
	var merchantId = $('#merchantId').val();
	var merchantName = $('#merchantId').text();
	var operateType = $('#operateType').val();
	if(merchantId==""){
	 alert("请选择商户！");
	 return;
	}
	$.ajax({  
			  type:"post",  
			  url:"batch_fp?batchValue="+batchValue+"&merchantId="+merchantId+"&operateType="+operateType,
			  success:function(data) {
			      if(data=="xgsuccess"){
			         alert("批量修改成功");
			         window.location.href="${request.getContextPath()}/pos/posckFowd";
			      }else if(data=="fpsuccess"){
			         alert("批量分配成功");
			         window.location.href="${request.getContextPath()}/pos/posckFowd";
			      }else {
			         alert("操作失败");
			      }
			      
			  }
			 }); 
   }
  //批量修改--界面
  function batchEdit(){

    var rows = $('#userTable').datagrid('getSelections');
    if(rows.length==0){
      alert("请选择需修改的POS机！");
      return;
    }
    //alert(rows.length);
    $('#operateType').val("xg");
    var k = 0;
    $.each(rows, function(i, value){
			var val = value.status;
			if(val=="00")
			{
			  alert("该POS机未分配，不能修改商户！");
			  k++;
			  //value.ck=false;
			  //break;
			}
			   
		});
    if(k>0){
      return;
    }
     
   $('#dd').show().dialog({
		    title: '批量修改',
		    width: 400,
		    height: 150,
		    closed: false,
		    cache: false,
		    modal: true
          }); 
 
	}
	
	//批量解除Pos绑定
  function batchDel(){
    var rows = $('#userTable').datagrid('getSelections');
    if(rows.length==0){
      alert("请选择需解绑的POS机！");
      return;
    }
	var batchValue = "";
	$.each(rows, function(i, value){
			var val = value.id;
			if(i==0)
			   batchValue +=val;
			else
			   batchValue +=","+val;
		});
	if(confirm("是否解除绑定pos机？")){	
		$.ajax({  
				  type:"post",  
				  url:"batch_jc?batchValue="+batchValue,
				  success:function(data) {
				      if(data=="success"){
				         alert("批量解除绑定成功");
				         window.location.href="${request.getContextPath()}/pos/posckFowd";
				      }else {
				         alert("操作失败");
				      }
				  }
				 });
	}
   }
	//导出经过滤的信息
   function exportPos(){
		//var params = $('#userTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
		var params =$('#queryForm').serialize(); //自动序列化表单元素为JSON对象
	    var rows =  $('#userTable').datagrid('getPager').data("pagination").options.pageSize;
	    var page =  $('#userTable').datagrid('getPager').data("pagination").options.pageNumber;
	    params+="&rows="+rows+"&page="+page;
        window.location.href="${request.getContextPath()}/pos/posck_export?"+params;

	}
	//返回
   function comBack(){
       $('#dd').dialog('close');
  }
	//导出Excel,全部
   function exportExcel(){
      window.location.href="${request.getContextPath()}/pos/posck_export";
	}
	//清空查询条件
	function clearForm(){
		$('#queryForm').form('clear');
		searchUser();
	}
	
	//删除
   function delrow(id,status){
       var url = "${request.getContextPath()}/pos/pos_del?id="+id;
       if(status=='01'){
         alert("该pos机已分配，不能删除");
         return;
       }
	   if(confirm("是否删除该pos机？")){
		 $.ajax({type:"post",url:url,
			success:function(data) {
                
				if(data=="success"){
				   alert("删除成功");
				   window.location.href="${request.getContextPath()}/pos/posckFowd";
				}else{
				   alert("删除失败");
				}
	         }
	    });
	 }
  	}
</script>

</head>

<body>
 
<form id="queryForm">
   <div class="title_right"><strong>pos机出库</strong></div>  
       <div style="width:100% margin:auto">
     <table class="table table-bordered">
         <tr>
         <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">SN码：</td>
	     <td><input type="text" id="pos_sn" name="pos_sn" class=" span1-1" /></td>
	     <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">厂家：</td>
	     <td><input type="text" id="factory_name" name="factory_name" class=" span1-1" /></td>
	     <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">型号：</td>
	     <td><input type="text" id="pos_model" name="pos_model" class=" span1-1" /></td>
	     
     </tr>
     <tr>
	     <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">商户名称：</td>
	     <td><select id="merchantCode" name="merchantCode" style="height:25px">
          </select></td>
         <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">状态：</td>
         <td colspan="3"><select id="status" name="status" style="height:25px">
          <option value="">全部</option>
          <option value="00">未分配</option>
          <option value="01">已分配</option>
          </select></td>
     </tr>
       </table>
        <table  class="margin-bottom-20 table  no-border" >
         <tr>
     	<td class="text-center"><input type="button" value="查询" class="btn btn-info " style="width:80px;" onclick="searchPosck();"/>
     	<input type="button" value="批量分配" class="btn btn-info " style="width:80px;" onclick="batchFp();"/>
     	<input type="button" value="批量修改" class="btn btn-info " style="width:80px;" onclick="batchEdit();"/>
     	<input type="button" value="导出" class="btn btn-info " style="width:80px;" onclick="exportPos();"/>
     	<input type="button" value="批量解绑" class="btn btn-info " style="width:80px;" onclick="batchDel();"/>
     	
     	
     	
     	</td>
     </tr>
 </table>
      <table id="userTable"></table>
    
   </div>
 </form>
  <div id="dd" class="easyui-dialog" title="" closed="true">
   <form action="${request.getContextPath()}/pos/batch_fp" method="post">
    <div class="modal-body">
     <div class="title_right"><strong></strong></div>
     <input type="hidden" id="operateType" name="operateType" value="" />
     <table class="table table-bordered">
     
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">选择商家：</td>
       <td colspan="3"><select id="merchantId" name="merchantId" style="height:25px">
          </select></td>
     </tr>
   </table>
   <table class="margin-bottom-20 table  no-border">
        <tr>
     	<td class="text-center"><input type="button" value="确定" class="btn btn-info " style="width:80px;" onclick="batchTj();"/>
     	<input type="button" value="返回" class="btn btn-info " style="width:80px;" onclick="comBack();"/>
     	</td>
     </tr>
 </table>
</div>
</form>
    </div>

</body>
</html>
