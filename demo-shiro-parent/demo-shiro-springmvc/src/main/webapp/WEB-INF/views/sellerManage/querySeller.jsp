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
			title:'查询商户', //标题
			method:'post',
			iconCls:'icon-save', //图标
			singleSelect:false, //多选
			width: 'auto',
			height:360, //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, //奇偶行颜色不同
			loadMsg:'数据加载中请稍后……', 
			collapsible:true,//可折叠
			url:'seller_list', //数据来源
			sortName: 'create_time', //排序的列
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
				{field:'merchant_code',title:'商户编号',sortable:true,
					formatter:function(value,row,index){return row.merchant_code;} 
				},
				{field:'merchant_name',title:'商户名称',sortable:true,
					formatter:function(value,row,index){return row.merchant_name;} 
				},
				{field:'contact_person',title:'商户联系人',sortable:true,
					formatter:function(value,row,index){return row.contact_person;}
				},
				{field:'contact_phone',title:'联系电话',sortable:true,
					formatter:function(value,row,index){return row.contact_phone;}
				},
				{field:'public_id',title:'微信公众号ID',sortable:true,
					formatter:function(value,row,index){
						return row.public_id;  
					}
				},
				{field:'appid',title:'微信公众号AppId',sortable:true,
					formatter:function(value,row,index){
						return row.appid;  
					}
				},
				{field:'appsecret',title:'微信公众号AppSecret',sortable:true,
					formatter:function(value,row,index){
						return row.appsecret;  
					}
				},
				{field:'bank_address',title:'开户行',sortable:true,
					formatter:function(value,row,index){
						return row.bank_address+row.bank_province+row.bank_city+row.bank_street; 
					}
				},
				{field:'bank_account',title:'银行账号',sortable:true,
					formatter:function(value,row,index){
						return row.bank_account; 
					}
				},
				{field:'create_time',title:'商户新增时间',sortable:true,
					formatter:function(value,row,index){
						return row.create_time; 
					}
				},
				{field:'operator',title:'操作',sortable:true,
					formatter:function(value,row,index){
					var i = '<a href="javascript:void(0)" onclick="queryrow(\''+row.id+'\')">查看</a> ';
					var j = '<a href="javascript:void(0)" onclick="editrow(\''+row.id+'\')">修改</a> ';
					var b = '<a href="javascript:void(0)" onclick="delrow(\''+row.merchant_code+'\')">删除</a> ';
					var m = '<a href="javascript:void(0)" onclick="createMenu(\''+row.public_id+'\',\''+row.appid+'\',\''+row.appsecret+'\')">创建菜单</a> ';
					var n = '<a href="javascript:void(0)" onclick="deleteMenu(\''+row.public_id+'\',\''+row.appid+'\',\''+row.appsecret+'\')">删除菜单</a> ';
					var k = '<a href="javascript:void(0)" onclick="userSynchro(\''+row.public_id+'\',\''+row.appid+'\',\''+row.appsecret+'\',\''+row.merchant_code+'\',\''+row.merchant_name+'\')">用户同步</a> ';
					var l = '<a href="javascript:void(0)" onclick="delRedis(\''+row.merchant_code+'\')">重新加载缓存</a> '; 
 
				    return i+j+b+l;
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
	
		 //创建菜单
    function createMenu(public_id,appid,appsecret){
       if(public_id=="null" || public_id==""){
         alert("公众号原始id不存在,不能创建菜单");
         return;
       }
       if(appid=="null" || appid==""){
         alert("微信公众号AppId不存在,不能创建菜单");
         return;
       }
       if(appsecret=="null" || appsecret==""){
         alert("微信公众号AppSecret不存在,不能创建菜单");
         return;
       }
         var url = "../wxMenu/createMenu?public_id="+public_id+"&appid="+appid+"&appsecret="+appsecret;
		 //window.location.href=url+parms;
		 //alert(url);
		 $.ajax({type:"post",url:url,  
			success:function(data) {
				if(data=="fail"){
				   alert("微信端异常,创建菜单失败");
				}else{
				   var json = eval("("+data+")");
				   var errcode = json.errcode;
				   var errmsg = json.errmsg;
				   alert("errmsg:"+errmsg);
				}
	       }
	 });
          
	    
	}
		 //删除菜单
    function deleteMenu(public_id,appid,appsecret){
       if(public_id=="null" || public_id==""){
         alert("公众号原始id不存在,不能删除菜单");
         return;
       }
       if(appid=="null" || appid==""){
         alert("微信公众号AppId不存在,不能删除菜单");
         return;
       }
       if(appsecret=="null" || appsecret==""){
         alert("微信公众号AppSecret不存在,不能删除菜单");
         return;
       }
         var url = "../wxMenu/deleteMenu?public_id="+public_id+"&appid="+appid+"&appsecret="+appsecret;
         
		 //window.location.href=url+parms;
	   if(confirm("您确定要删除该记录？")){
		 $.ajax({type:"post",url:url,  
			success:function(data) {
            
				if(data=="fail"){
				   alert("微信端异常,删除菜单失败");
				}else{
				   var json = eval("("+data+")");
				   var errcode = json.errcode;
				   var errmsg = json.errmsg;
				   alert("errmsg:"+errmsg);
				}
	         }
	    });
	 }
          
	}
	
	//用户同步
    function userSynchro(public_id,appid,appsecret,merchant_code,merchant_name){
       if(public_id=="null" || public_id==""){
         alert("公众号原始id不存在");
         return;
       }
       if(appid=="null" || appid==""){
         alert("微信公众号AppId不存在");
         return;
       }
       if(appsecret=="null" || appsecret==""){
         alert("微信公众号AppSecret不存在");
         return;
       }
       var url = "${request.getContextPath()}/synchro/userSynchro?public_id="+public_id+"&appid="+appid+"&appsecret="+appsecret+"&merchant_code="+merchant_code+"&merchant_name="+merchant_name;
       
		 //window.location.href=url+parms;
	   if(confirm("该功能为初始化功能，是否继续？")){
		 $.ajax({type:"post",url:url,
			success:function(data) {
                
				if(data=="success"){
				   alert("同步用户成功");
				   //var json = eval("("+data+")");
				   //var errcode = json.errcode;
				  // var errmsg = json.errmsg;
				   //alert("errmsg:"+errmsg);
				}else{
				   alert("同步用户失败");
				}
	         }
	    });
	 }
          
	}
	 //查看
    function queryrow(id){
       window.location.href="${request.getContextPath()}/seller/detailSeller?id="+id;
	}
 //新增
    function addrow(){
        window.location.href="${request.getContextPath()}/seller/addSeller";
 
	}
	
	//修改
    function editrow(id){
        window.location.href="${request.getContextPath()}/seller/editSeller?id="+id;
	}
  
    //删除
  	function delrow(merchant_code){
       var url = "${request.getContextPath()}/seller/seller_del?merchant_code="+merchant_code;
       
		 //window.location.href=url+parms;
	   if(confirm("是否删除该商户信息？")){
		 $.ajax({type:"post",url:url,
			success:function(data) {
                
				if(data=="success"){
				   alert("删除成功");
				   window.location.href="${request.getContextPath()}/seller/sellerInfo";
				}else if(data=="posFail"){
				   alert("当前商户下存在pos机，不能删除");
				}else if(data=="fundPoolFail"){
				   alert("当前商户下资金池有交易数据，不能删除");
				}
	         }
	    });
	 }
  	}
	//删除
  	function delRedis(merchant_code){
        var url = "${request.getContextPath()}/seller/seller_delRedis?merchant_code="+merchant_code;
        
 		 //window.location.href=url+parms;
 	   if(confirm("是否删除该商户缓存信息？")){
 		 $.ajax({type:"post",url:url,
 			success:function(data) {
                 
 				if(data=="success"){
 				   alert("删除成功");
 				   window.location.href="${request.getContextPath()}/seller/sellerInfo";
 				}else {
 				   alert("删除失败");
 				}
 	         }
 	    });
 	 }
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
        window.location.href="${request.getContextPath()}/seller/seller_export"+params;
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
   <div class="title_right"><strong>查询商户</strong></div>  
       <div style="width:100% margin:auto">
       <table class="table table-bordered">
         <tr>
	     <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">商户编号：</td>
	     <td><input type="text" id="MERCHANT_CODE" name="MERCHANT_CODE" class=" span1-1" maxlength="8"/></td>
	     <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">商户名称：</td>
	     <td><input type="text" id="merchant_name" name="merchant_name" class=" span1-1" /></td>
     <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">开始时间：</td>
     <td width="23%">
      <input type="text" class="laydate-icon span2-1" id="strDate" name="strDate" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"/></td>
     <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">结束时间：</td>
     <td width="23%">
      <input type="text" class="laydate-icon span2-1" id="endDate" name="endDate" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"/>
     </td>
     </tr>
       </table>
       <table  class="margin-bottom-20 table  no-border" >
        <tr>
     	<td class="text-center"><input type="button" value="查询" class="btn btn-info " style="width:80px;" onclick="searchUser();"/>
     	<input type="button" value="新增" id="exportId" class="btn btn-info " style="width:80px;" onclick="addrow();"/>
     	<input type="button" value="导出" id="exportId" class="btn btn-info " style="width:80px;" onclick="exportUser();"/>
     	</td>
     </tr>
 </table>
     <table  id="userTable"></table>
    <div id="dd">
       
    </div>
   </div>

  </form>

</body>
</html>
