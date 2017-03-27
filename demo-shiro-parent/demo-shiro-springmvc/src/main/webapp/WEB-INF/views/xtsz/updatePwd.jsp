<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>钱包后台</title>
<link rel="stylesheet" href="/resources/css/bootstrap.css" />
<link rel="stylesheet" href="/resources/css/css.css" />
<script type="text/javascript" src="/resources/js/jquery1.9.0.min.js"></script>
<script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/js/sdmenu.js"></script>
<script type="text/javascript" src="/resources/js/laydate/laydate.js"></script>

</head>

<body>

   <div class="title_right"><strong>修改密码</strong></div>
   <div style="width:900px; margin:auto">
   <table class="table table-bordered" >
     <tr>
       <td width="12%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">账号：</td>
       <td ><input type="text" name="input" id="input" class="span10"  /></td>
       
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">原密码：</td>
       <td><input type="text" name="input3" id="input3" class="span10"  /></td>
       
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">新密码：</td>
       <td><input type="text" name="input2" id="input2" class="span10"  /></td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">确认密码：</td>
       <td><input type="text" name="input2" id="input2" class="span10"  /></td>
     </tr>
     
   </table>
   <table  class="margin-bottom-20 table  no-border" >
        <tr>
     	<td class="text-center"><input type="button" value="确认" class="btn btn-info " style="width:80px;" />
     	<input type="button" value="取消" class="btn btn-info " style="width:80px;" />
     	</td>
     </tr>
 </table>
      
   </div> 

    

 <script>
!function(){
laydate.skin('molv');
laydate({elem: '#Calendar'});
laydate.skin('molv');
laydate({elem: '#Calendar2'});
}();
 
</script>



 
</body>
</html>
