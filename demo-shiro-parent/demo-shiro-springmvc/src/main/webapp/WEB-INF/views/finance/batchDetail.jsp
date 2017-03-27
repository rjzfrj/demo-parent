
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
</head>
<body>
<form action="${request.getContextPath()}/finance/review" method="post">
   <div class="title_right"><strong>明细</strong></div>  
       <div style="width:900px; margin:auto">
       
       <table class="table table-bordered table-hover table-striped">
         <tbody>
           <tr align="center">
             <td><strong>交易时间</strong></td>
             <td><strong>商户名称</strong></td>
             <td><strong>商户编号</strong></td>
             <td><strong>交易流水号</strong></td>
             <td><strong>提现金额</strong></td>
             <td><strong>手续费</strong></td>
             <td><strong>实际到账金额</strong></td>
             <td><strong>状态</strong></td>
           </tr>
           <#list spBiss as shopBusiness>
           <tr align="center">
             <td>${shopBusiness.BUSINESS_TIME}</td>
             <td>${shopBusiness.MERCHANT_NAME}</td>
             <td>${shopBusiness.MERCHANT_CODE}</td>
             <td>${shopBusiness.SERIAL}</td>
             <td>${shopBusiness.ORDER_AMT}</td>
             <td>${shopBusiness.POUNDAGE}</td>
             <td>${shopBusiness.TRAN_AMT}</td>
             <td>${shopBusiness.STATUS}</td>
           </tr>
           <#list>
           </tbody>
           </table>
   </div>
 </form>

</body>
</html>
