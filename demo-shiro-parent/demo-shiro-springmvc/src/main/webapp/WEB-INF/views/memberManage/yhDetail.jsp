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
<form id="listForm">
       <div style="width:600px; margin:auto">
       <table class="table table-bordered table-hover table-striped">
         
           <tr align="center">
             <td><strong>会员卡号码</strong></td>
             <td><strong>商户名称</strong></td>
             <td><strong>金额</strong></td>
             <td><strong>领取时间</strong></td>
             <td><strong>有效期</strong></td>
             <td><strong>优惠券状态</strong></td>
           </tr>
          <#list coupons as coupon>
	      <tr>
	        <td align="left" ><span class="STYLE15">${coupon.MEMBER_CARD}</span></td>
	        <td align="left" ><span class="STYLE15">${coupon.MERCHANT_NAME}</span></td>
	        <td align="left" ><span class="STYLE15">${coupon.AMOUNT}</span></td>
	        <td align="left" ><span class="STYLE15">${coupon.CREATE_TIME?string("yyyy-MM-dd HH:mm:ss")}</span></td>
	        <td align="left" ><span class="STYLE15">${coupon.END_DATE?string("yyyy-MM-dd HH:mm:ss")}</span></td>
	        <td align="left" ><span class="STYLE15"><#if coupon.STATUS=='00'>可使用
							                            <#elseif coupon.STATUS=='01'> 已使用
							                            <#else>已过期
	                                                </#if>
	                          </span>
	        </td>
	      </tr>
      </#list>
     </table>
   </div>
  </form>
 
</body>
</html>
