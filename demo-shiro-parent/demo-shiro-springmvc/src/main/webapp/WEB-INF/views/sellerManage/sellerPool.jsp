
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
<form action="${request.getContextPath()}/seller/sellerPool_fd" method="post">
   <div class="title_right"><strong>商户资金池</strong></div>  
       <div style="width:900px; margin:auto">
       
        
             <table class="table table-bordered table-hover table-striped">
         <tbody>
           <tr align="center">
             <td><strong>当前余额</strong></td>
             <td><strong>当日金额</strong></td>
             <td><strong>冻结金额</strong></td>
             <td><strong>可体现金额</strong></td>
             <td><strong>操作</strong></td>
           </tr>
           <#if fundPool?exists>
           <tr align="center">
             <td>${fundPool.CURR_MOENY}
             <input type="hidden" name="ID" id="ID" class="span2" value="${fundPool.ID}"/>
             <input type="hidden" name="MERCHANT_CODE" id="MERCHANT_CODE" class="span2" value="${fundPool.MERCHANT_CODE}"/>
             </td>
             <td>${fundPool.DAY_MOENY}</td>
             <td>${fundPool.FROZEN_MOENY}</td>
             <td>${fundPool.VALID_MOENY}</td>
             <td><#if fundPool.VALID_MOENY !=0 >
                <input type="submit" name="tx" value="提现" class="span2" />
                </#if>
             </td>
             
           </tr>
           </tbody>
           </table>
           </#if>
   </div>
 </form>

</body>
</html>
