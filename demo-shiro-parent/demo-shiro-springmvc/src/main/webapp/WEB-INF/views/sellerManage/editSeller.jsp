<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<title>钱包后台</title>
<link rel="stylesheet" href="../resources/css/bootstrap.css" />
<link rel="stylesheet" href="../resources/css/css.css" />
<link rel="stylesheet" type="text/css" href="../resources/css/main.css" />
<link rel="stylesheet" type="text/css" href="../resources/easyui/themes/default/easyui.css" />
<link  href="../resources/css/common.css" type="text/css" rel="stylesheet" />
<link  href="../resources/css/style.css" type="text/css" rel="stylesheet" />
<link  href="../resources/css/jquery-ui-1.10.1.custom.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../resources/easyui/jquery-1.7.2.min.js"></script>   
<script type="text/javascript" src="../resources/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../resources/js/laydate/laydate.js"></script>
<script type="text/javascript" src="../resources/js/area-init.js"></script>

</head>
<body>
  <form id="editForm" method="post" enctype="multipart/form-data">
   <div style="width:900px; margin:auto;">
   <input type="hidden" name="ID" id="ID" class="span2" value="${memberPublic.ID}"/>
   <div style="width:700px;  margin-left:auto;"><strong>基本信息</strong></div>
   <table class="table table-bordered" >
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*商户编号：</td>
       <td><input type="text" name="MERCHANT_CODE" id="MERCHANT_CODE" class="span2" value="${memberPublic.MERCHANT_CODE}" readonly="readonly"/></td>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*商户名称：</td>
       <td><input type="text" name="MERCHANT_NAME" id="merchant_name" class="span2 easyui-validatebox" required="true"
       missingMessage="商户名称不能为空"  value="${memberPublic.MERCHANT_NAME}"/></td>  
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*商家联系人：</td>
       <td><input type="text" name="CONTACT_PERSON" id="contact_person" class="span2 easyui-validatebox" required="true" 
       missingMessage="商家联系人不能为空"  value="${memberPublic.CONTACT_PERSON}"/></td>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*联系电话：</td>
       <td><input type="text" name="CONTACT_PHONE" id="contact_phone" class="span2 easyui-validatebox" required="true"
       missingMessage="联系电话不能为空"  value="${memberPublic.CONTACT_PHONE}" maxlength="11"/></td>
     </tr>
      <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">商家地址：</td>
       <td colspan="3"><input type="text" name="ADDRESS" id="address" class="span6 easyui-validatebox" required="true"
       missingMessage="商家地址不能为空"  value="${memberPublic.ADDRESS}"/></td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">微信公众号ID：</td>
       <td><input type="text" name="PUBLIC_ID" id="public_id" class="span2"  value="${memberPublic.PUBLIC_ID}"/></td>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">微信号：</td>
       <td><input type="text" name="WECHAT" id="wechat" class="span2"  value="${memberPublic.WECHAT}"/></td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">公众号名称：</td>
       <td><input type="text" name="PUBLIC_NAME" id="public_name" class="span2"  value="${memberPublic.PUBLIC_NAME}"/></td>
        <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">当前公众号使用状态：</td>
       <td><select id="STATUS" name="STATUS">
          <option value="00">使用中</option>
          <option value="01">未使用</option>
          <option value="02">已停用</option>
        </select>
       </td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">微信公众号Toke：</td>
       <td><input type="text" name="TOKEN" id="token" class="span2" value="${memberPublic.TOKEN}"/></td>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">微信公众号Appid：</td>
       <td><input type="text" name="APPID" id="appid" class="span2" value="${memberPublic.APPID}"/></td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">微信公众号Appsecret：</td>
       <td colspan="3"><input type="text" name="APPSECRET" id="appsecret" class="span6" value="${memberPublic.APPSECRET}"/></td>
     </tr>

     </table>
     <div style="width:700px;  margin-left:auto;"><strong>结算信息</strong></div>
     <table class="table table-bordered">
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*开户行：</td>
        <td colspan="3">
       <div id="city">
       <select id="bank_address" name="BANK_ADDRESS" class="span1-1">
          <option value="" style="color:#000;font-weight:bold;">-银行-</option>
        </select>
        <select id="bankProvince1" name="BANK_PROVINCE" class="span1-1"
                onChange="getCityModify(this.options[this.selectedIndex].value)" value="">
            <option value="" style="color:#000;font-weight:bold;">-省份-</option>
        </select>
        <select id="bankCity1" name="BANK_CITY" class="span1-1">
            <option value="" style="color:#000;font-weight:bold;">-城市-</option>
        </select>
		<input type="text" name="BANK_STREET" id="bank_street" class="span2 easyui-validatebox" required="true" 
       missingMessage="请输入支行地址" value="${memberPublic.BANK_STREET}" />
		<select id="accType" name="ACC_TYPE" class="span1-1">
          <option value="01">对公账户</option>
          <option value="02">对私账户</option>
        </select>
		</div> 
		</td>
       </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*银行账号：</td>
       <td ><input type="text" name="BANK_ACCOUNT" id="BANK_ACCOUNT" class="span2-1 easyui-validatebox" required="true"
       missingMessage="银行账号不能为空" maxlength="30"  value="${memberPublic.BANK_ACCOUNT}"/></td>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*结算方式：T+</td>
       <td><input type="text" name="PAYPERIOD" id="PAYPERIOD" class="span1 easyui-numberbox" required="true"
       missingMessage="结算方式必须为整数" precision="0" maxlength="5" value="${memberPublic.PAYPERIOD}"/></td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*POS机押金：</td>
       <td colspan="3"><input type="text" name="POS_DEPOSIT" id="POS_DEPOSIT" class="span2 easyui-numberbox" required="true" 
       missingMessage="POS机押金必须为数字" precision="2" value="${memberPublic.POS_DEPOSIT}"/></td>
     </tr>
      <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*银行卡费率：</td>
       <td><input type="text" name="BANK_RATE" id="BANK_RATE" class="span2 easyui-numberbox" required="true" 
       missingMessage="银行卡费率必须为数字" precision="6" value="${memberPublic.BANK_RATE}"/>(整数位不能超过1位)</td>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*微信费率：</td>
       <td><input type="text" name="WECHAT_RATE" id="WECHAT_RATE" class="span2 easyui-numberbox" required="true" 
       missingMessage="微信费率必须为数字" precision="6" value="${memberPublic.WECHAT_RATE}"/>(整数位不能超过1位)</td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*支付宝费率：</td>
       <td><input type="text" name="PAY_RATE" id="PAY_RATE" class="span2 easyui-numberbox" required="true" 
       missingMessage="支付宝费率必须为数字" precision="6" value="${memberPublic.PAY_RATE}"/>(整数位不能超过1位)</td>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*会员卡费率：</td>
       <td><input type="text" name="MEMBERCARD_RATE" id="MEMBERCARD_RATE" class="span2 easyui-numberbox" required="true" 
       missingMessage="第三方渠道卡费率必须为数字" precision="6" value="${memberPublic.MEMBERCARD_RATE}"/>(整数位不能超过1位)</td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*钱包费率：</td>
       <td><input type="text" name="WALLET_RATE" id="WALLET_RATE" class="span2 easyui-numberbox" required="true" 
       missingMessage="支付宝费率必须为数字" precision="6" value="${memberPublic.WALLET_RATE}"/>(整数位不能超过1位)</td>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*奥斯卡费率：</td>
       <td><input type="text" name="ALLSCORE_RATE" id="ALLSCORE_RATE" class="span2 easyui-numberbox" required="true" 
       missingMessage="第三方渠道卡费率必须为数字" precision="6" value="${memberPublic.ALLSCORE_RATE}"/>(整数位不能超过1位)</td>
     </tr>
	<tr>
		<td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*信用卡费率：</td>
		<td><input type="text" name="CREDIT_CARD_RATE" id="CREDIT_CARD_RATE" class="span2 easyui-numberbox" required="true" 
		missingMessage="信用卡费率必须为数字" precision="6" value="${memberPublic.CREDIT_CARD_RATE}"/>(整数位不能超过1位)</td>
		<td align="right" nowrap="nowrap" bgcolor="#f1f1f1"></td>
		<td></td>
	</tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*营业执照复印件：</td>
       <td colspan="3"><img src="${request.getContextPath()}/seller/servletSeller?id=${memberPublic.ID}&type=1" />
        <input type="file" name="files" id="business_license" class="span3" />(图片大小不能超过10M)</td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*税务登记证复印件：</td>
       <td colspan="3">
       <img src="${request.getContextPath()}/seller/servletSeller?id=${memberPublic.ID}&type=2" />
       <input type="file" name="files" id="tax_registration" class="span3" />(图片大小不能超过10M)</td>
     </tr>
      <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*法人身份证复印件：</td>
       <td colspan="3">
       <img src="${request.getContextPath()}/seller/servletSeller?id=${memberPublic.ID}&type=3" />
       <input type="file" name="files" id="legal_idcard" class="span3" />(图片大小不能超过10M)</td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*组织结构代码证复印件：</td>
       <td colspan="3"><img src="${request.getContextPath()}/seller/servletSeller?id=${memberPublic.ID}&type=4" />
       <input type="file" name="files" id="organization" class="span3" />(图片大小不能超过10M)</td>
     </tr>
      <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*开户许可证复印件：</td>
       <td colspan="3"><img src="${request.getContextPath()}/seller/servletSeller?id=${memberPublic.ID}&type=5" />
       <input type="file" name="files" id="opening_permit" class="span3"/>(图片大小不能超过10M)</td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*商家logo：</td>
       <td colspan="3"><img src="${request.getContextPath()}/seller/servletSeller?id=${memberPublic.ID}&type=6" />
       <input type="file" name="files" id="shop_logo" class="span3"/>(图片大小不能超过10M)</td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">*商家二维码图片：</td>
       <td colspan="3"><div style="width:100px"><img src="${memberPublic.SHOP_QRCODE}"/></div>
       <input type="file" name="files" id="shop_qrcode" class="span3"/>(图片大小不能超过10M)</td>
     </tr>
   </table>
   <table  class="margin-bottom-20 table no-border" >
        <tr>
     	<td class="text-center"><input type="submit" value="修改" class="btn btn-info " style="width:80px;" />
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
    $("#accType").val("${memberPublic.ACC_TYPE}");//账户类型
    $("#status").val("${memberPublic.STATUS}");//当前公众号使用状态
    
    var timer1 = null;
    var timer2 = null;
    var pencert = null;
    $('#editForm').form({
     url:'${request.getContextPath()}/seller/seller_edit',
     onSubmit:function(){
     var credit_card_rate = $('#CREDIT_CARD_RATE').val();
     var bank_card = $('#BANK_ACCOUNT').val();
     var bank_rate = $('#BANK_RATE').val();
     var wechat_rate = $('#WECHAT_RATE').val();
     var pay_rate = $('#PAY_RATE').val();
     var membercard_rate = $('#MEMBERCARD_RATE').val();
     var wallet_rate = $('#WALLET_RATE').val();
     var allscore_rate = $('#ALLSCORE_RATE').val();
     var acc_type = $('#ACC_TYPE').val();
     var regRate =/^((\d{1}\.\d{1,6})|(\d{1}))$/;
     var reg = /^(\d{1,30})$/;
	 var flag = $(this).form('validate');
     if(flag==true){
      if(acc_type =="02"){
         if(!reg.test(bank_card)) 
		 { 
		    alert("银行卡号格式错误！");
		    return false;
		 }
	   }
		 if(!regRate.test(bank_rate)) 
		 { 
		    alert("银行卡费率格式错误！");
		    return false;
		 }
		 if(!regRate.test(wechat_rate)) 
		 { 
		    alert("微信费率格式错误！");
		    return false;
		 }
		 if(!regRate.test(pay_rate)) 
		 { 
		    alert("支付宝费率格式错误！");
		    return false;
		 }
		 if(!regRate.test(membercard_rate)) 
		 { 
		    alert("会员卡费率格式错误！");
		    return false;
		 }
		 if(!regRate.test(wallet_rate)) 
		 { 
		    alert("钱包费率格式错误！");
		    return false;
		 }
		 if(!regRate.test(allscore_rate)) 
		 { 
		    alert("奥斯卡费率格式错误！");
		    return false;
		 }
		 if(!regRate.test(credit_card_rate)) 
		 { 
		    alert("奥斯卡费率格式错误！");
		    return false;
		 }
		 
		 
		 $('#progressBar').show().dialog({
		    title: '上传进度',
		    width: 300,
		    height: 150,
		    closed: false,
		    cache: false,
		    modal: true
          });
          $("#progress_percent").text("0%");
          $("#progress_bar").width("0%");
          //$("#progress_all").show();
		  
		  timer1 = setInterval("getProgress()", 1000);//每隔一秒调用
     }
	 
         return flag;  
     },
     success:function(data){
         if(data=="success"){
         
          //var progress_percent = $("#progress_percent").text();
          timer2 = setInterval("getProgress_percent()", 1000);//每隔一秒调用
          //alert("progress_percent="+pencert);
          
	           
         }else{
            alert("修改商户信息失败");
         }
     }
    });

});
    function getProgress_percent(){
      pencert = $("#progress_percent").text();
      if(pencert == '100%'){
        alert("修改商户信息成功");
        window.location.href="${request.getContextPath()}/seller/sellerInfo";
        clearInterval(timer2);
        clearInterval(timer1);
       }
      // return;
    }
    function comBack(){
	   window.location.href="${request.getContextPath()}/seller/sellerInfo";
	}
	
	function getProgress() {
	
		    $.ajax({
		        type: "post",
		        dataType: "json",
		        url: "${request.getContextPath()}/fileStatus/upfile/progress",
		        data: "",
		        success: function(data) {
		        	$("#progress_percent").text(data.percent);
		            $("#progress_bar").width(data.percent);
		            $("#has_upload").text(data.mbRead);
		            $("#upload_speed").text(data.speed);
		            
		        },
		        error: function(err) {
		        	$("#progress_percent").text("Error");
		        }
		    });
     }
</script>
<div id="progressBar" class="easyui-dialog" title="" closed="true"> 
 <ul>
    <li>
		<div class="process clearfix" id="process">
		<span class="progress-box">
			<span class="progress-bar" style="width: 0%;" id="progress_bar"></span>
		</span>
		<span id="progress_percent">0%</span>
		 </div>
        <div class="info" id="info">已上传：<span id="has_upload">0</span>MB  速度：<span id="upload_speed">0</span>KB/s</div>
        <div class="info" id="success_info" style="display: none;"></div>
    </li>
</ul>
</div>
</body>
</html>
