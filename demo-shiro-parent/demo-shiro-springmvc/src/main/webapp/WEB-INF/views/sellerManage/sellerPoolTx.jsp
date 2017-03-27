
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
<script type="text/javascript">
  //提现
 // function tx(){
  // $('#dd').show().dialog({
		   // title: '提现',
		   // width: 400,
		   // height: 150,
		  //  closed: false,
		  //  cache: false,
		   // modal: true
         // }); 
	//}
</script>
</head>
<body>
 <!--  
<form action="${request.getContextPath()}/seller/getCheck" method="post">
   <div class="title_right"><strong>提现身份验证</strong></div>  
       <div style="width:900px; margin:auto">
       <input type="hidden" name="MERCHANT_CODE" id="MERCHANT_CODE" class="span2" value="${memberPublic.MERCHANT_CODE}"/>
       <table class="table table-bordered">
         <tr>
		     <td><input type="text" id="mobile" name="mobile" class=" span1-1" value="${memberPublic.MOBILE}"/></td>
		     <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">
		     <input type="button" id="get_num" name="get_num" value="获取验证码"/>
		     </td>
	     </tr>
	     <tr>
		     <td><input type="text" id="check_num" name="check_num" class=" span1-1" /></td>
		     <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">
		      <input type="button" id="next" name="next" value="下一步" onclick="tx();"/>
		     </td>
         </tr>
       </table> 
   </div>
 </form>
 -->

    <form action="${request.getContextPath()}/seller/sellerPool_tx" method="post">
     <div class="title_right"><strong>提现</strong></div>
      <input type="hidden" name="MERCHANT_CODE" id="MERCHANT_CODE" class="span2" value="${memberPublic.MERCHANT_CODE}"/>
     <table class="table table-bordered">
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">提现金额：</td>
       <td><input type="text" id="order_amt" name="order_amt" /></td>
     </tr>
   </table>
   <table class="margin-bottom-20 table  no-border" >
        <tr>
     	<td class="text-center"><input type="submit" value="确定" class="btn btn-info " style="width:80px;"/>
     	<input type="button" value="返回" class="btn btn-info " style="width:80px;" data-dismiss="modal" aria-hidden="true"/>
     	</td>
     </tr>
 </table>

</form>

</body>
</html>
