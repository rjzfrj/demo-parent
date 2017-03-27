<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>钱包后台</title>
<link rel="stylesheet" href="../resources/css/bootstrap.css" />
<link rel="stylesheet" href="../resources/css/css.css" />
<script type="text/javascript" src="../resources/easyui/jquery-1.7.2.min.js"></script>   
<script type="text/javascript" src="../resources/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../resources/js/laydate/laydate.js"></script>
<script type="text/javascript" src="../resources/js/area-init.js"></script>
</head>
<body>
  <form action="">
   <div style="width:900px; margin:auto;">
   
   <div style="width:700px;  margin-left:auto;"><strong>基本信息</strong></div>
  <table class="table table-bordered" >
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*商户编号：</td>
       <td><input type="text" name="MERCHANT_CODE" id="MERCHANT_CODE" class="span2" value="${memberPublic.MERCHANT_CODE}"/></td>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*商户名称：</td>
       <td><input type="text" name="merchant_name" id="merchant_name" class="span2" value="${memberPublic.MERCHANT_NAME}"/></td>  
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*商家联系人：</td>
       <td><input type="text" name="contact_person" id="contact_person" class="span2"  value="${memberPublic.CONTACT_PERSON}"/></td>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*联系电话：</td>
       <td><input type="text" name="contact_phone" id="contact_phone" class="span2"  value="${memberPublic.CONTACT_PHONE}"/></td>
     </tr>
      <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*商家地址：</td>
       <td colspan="3"><input type="text" name="address" id="address" class="span6"  value="${memberPublic.ADDRESS}"/></td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">微信公众号ID：</td>
       <td><input type="text" name="public_id" id="public_id" class="span2" value="${memberPublic.PUBLIC_ID}" /></td>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">微信号：</td>
       <td><input type="text" name="wechat" id="wechat" class="span2"  value="${memberPublic.WECHAT}"/></td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">公众号名称：</td>
       <td><input type="text" name="public_name" id="public_name" class="span2"  value="${memberPublic.PUBLIC_NAME}"/></td>
        <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">当前公众号使用状态：</td>
       <td><select id="status" name="status">
          <option value="00">使用中</option>
          <option value="01">未使用</option>
          <option value="02">已停用</option>
        </select>
</td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">微信公众号Toke：</td>
       <td><input type="text" name="token" id="token" class="span2"  value="${memberPublic.TOKEN}"/></td>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">微信公众号Appid：</td>
       <td><input type="text" name="appid" id="appid" class="span2"  value="${memberPublic.APPID}"/></td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">微信公众号Appsecret：</td>
       <td colspan="3"><input type="text" name="appsecret" id="appsecret" class="span6"  value="${memberPublic.APPSECRET}"/></td>
     </tr>
     </table>
     <div style="width:700px;  margin-left:auto;"><strong>结算信息</strong></div>
     <table class="table table-bordered">
      <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*开户行：</td>
        <td colspan="3">
       <div id="city">
       <select id="bank_address" name="bank_address" class="span1-1">
          <option value="" style="color:#000;font-weight:bold;">-银行-</option>
        </select>
        <select id="bankProvince1" name="prov" class="span1-1"
                onChange="getCityModify(this.options[this.selectedIndex].value)" value="">
            <option value="" style="color:#000;font-weight:bold;">-省份-</option>
        </select>
        <select id="bankCity1" name="city" class="span1-1">
            <option value="" style="color:#000;font-weight:bold;">-城市-</option>
        </select>
		<input type="text" name="bank_street" id="bank_street" class="span2" value="${memberPublic.BANK_STREET}"/>
		<select id="accType" name="accType" class="span1-1">
          <option value="01">对公账户</option>
          <option value="02">对私账户</option>
        </select>
		</div> 
		</td>
       </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*银行账号：</td>
       <td ><input type="text" name="bank_account" id="bank_account" class="span4" value="${memberPublic.BANK_ACCOUNT}"/></td>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*结算方式：T+</td>
       <td><input type="text" name="payPeriod" id="payPeriod" class="span1" value="${memberPublic.PAYPERIOD}"/></td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*POS机押金：</td>
       <td colspan="3"><input type="text" name="pos_deposit" id="pos_deposit" class="span2" value="${memberPublic.POS_DEPOSIT}"/></td>
     </tr>
      <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*银行卡费率：</td>
       <td><input type="text" name="bank_rate" id="bank_rate" class="span2" value="${memberPublic.BANK_RATE}"/></td>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*微信费率：</td>
       <td><input type="text" name="wechat_rate" id="wechat_rate" class="span2" value="${memberPublic.WECHAT_RATE}"/></td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*支付宝费率：</td>
       <td><input type="text" name="pay_rate" id="pay_rate" class="span2" value="${memberPublic.PAY_RATE}"/></td>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*会员卡费率：</td>
       <td><input type="text" name="membercard_rate" id="membercard_rate" class="span2" value="${memberPublic.MEMBERCARD_RATE}"/></td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*钱包费率：</td>
       <td><input type="text" name="wallet_rate" id="wallet_rate" class="span2" value="${memberPublic.WALLET_RATE}"/></td>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*奥斯卡费率：</td>
       <td><input type="text" name="allscore_rate" id="allscore_rate" class="span2" value="${memberPublic.ALLSCORE_RATE}"/></td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*营业执照复印件：</td>
       <td >
       <img src="${request.getContextPath()}/seller/servletSeller?id=${memberPublic.ID}&type=1" />
       </td>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*税务登记证复印件：</td>
       <td><img src="${request.getContextPath()}/seller/servletSeller?id=${memberPublic.ID}&type=2" /></td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*法人身份证复印件：</td>
       <td><img src="${request.getContextPath()}/seller/servletSeller?id=${memberPublic.ID}&type=3" /></td>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*组织结构代码证复印件：</td>
       <td><img src="${request.getContextPath()}/seller/servletSeller?id=${memberPublic.ID}&type=4" /></td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*开户许可证复印件：</td>
       <td><img src="${request.getContextPath()}/seller/servletSeller?id=${memberPublic.ID}&type=5" /></td>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*商家logo：</td>
       <td><img src="${request.getContextPath()}/seller/servletSeller?id=${memberPublic.ID}&type=6" /></td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*商家二维码图片：</td>
       <td colspan="3"><div style="width:100px"><img src="${memberPublic.SHOP_QRCODE}"/></div></td>
     </tr>
   </table>
   <table  class="margin-bottom-20 table  no-border" >
        <tr>
     	<td class="text-center">
     	<input type="button" value="返回" class="btn btn-info " style="width:80px;" onclick="comBack();"/>
     	</td>
     </tr>
 </table>

      
</div> 
</form>
<script type="text/javascript">
$(document).ready(function(){
    getProvinceModify("${memberPublic.BANK_PROVINCE}"); //获取开户银行省份列表
    getCityModify("${memberPublic.BANK_PROVINCE}","${memberPublic.BANK_CITY}");   //获取开户银行省市列表
    getBankModify("${memberPublic.BANK_ADDRESS}"); //获取开户银行
    $(":input").attr("readonly","readonly");//设置输入框为只读
    
    $("#accType").val("${memberPublic.ACC_TYPE}");//账户类型
    $("#status").val("${memberPublic.STATUS}");//当前公众号使用状态
   // var l = $("#accType").options.length;
   // alert(l);
});
	function comBack(){
	   window.location.href="${request.getContextPath()}/seller/sellerInfo";

	}
</script>

</body>
</html>
