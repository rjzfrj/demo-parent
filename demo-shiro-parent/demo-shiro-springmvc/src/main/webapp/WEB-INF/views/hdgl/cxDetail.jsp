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
<div class="modal-body">
   <div style="width:450px; margin:auto">
     
     <table class="table table-bordered table-hover table-striped">
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">商户编号：</td>
       <td>
	   <input type="text" name="MERCHANT_CODE" id="MERCHANT_CODE" class="span2" value="${promotionPlan.MERCHANT_CODE}" readonly="readonly"/></td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">促销类型：</td>
       <td><select name=TYPE id="TYPE" width="100px" readonly="readonly" style="height:25px">
		   <option value ="00">充值</option>
		   <option value ="01">消费</option>
		   <option value ="02">点评</option>
		   <option value ="03">注册</option>
	      </select></td>
     </tr>
      <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">奖励规则：</td>
       <td><input type="text" name="RULE" id="RULE" class="span2" value="${promotionPlan.RULE}" readonly="readonly"/></td>
     </tr>
      <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">奖励内容：</td>
       <td><input type="text" name="REWARD" id="REWARD" class="span2" value="${promotionPlan.REWARD}" readonly="readonly"/></td>
     </tr>
      <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">方案生效时间：</td>
       <td><input type="text" name="START_TIME" id="START_TIME" class="span2" value="${promotionPlan.START_TIME}" readonly="readonly"/></td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">优惠券有效期(天)：</td>
       <td><input type="text" name="VALID_DATE" id="VALID_DATE" class="span2" value="${promotionPlan.VALID_DATE}" readonly="readonly"/></td>
     </tr>
      <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">状态：</td>
       <td>
       <select name=STATUS id="STATUS" width="100px" readonly="readonly" style="height:25px">
		   <option value ="00">有效</option>
		   <option value ="01">无效</option>
	   </select>
       </td>
     </tr>
     <#if promotionPlan.TYPE=='01'>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">最低消费：</td>
       <td><input type="text" name="CONSUME_MIN" id="CONSUME_MIN" class="span2" value="${promotionPlan.CONSUME_MIN}" readonly="readonly"/></td>
     </tr>
     <tr>
       <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">最高消费：</td>
       <td><input type="text" name="CONSUME_MAX" id="CONSUME_MAX" class="span2" value="${promotionPlan.CONSUME_MAX}" readonly="readonly"/></td>
     </tr>
     </#if>
     </table>
   </div>
   </div>
  </form>
  <script type="text/javascript">
	$("#STATUS").val("${promotionPlan.STATUS}");
	$("#TYPE").val("${promotionPlan.TYPE}");
</script>
</body>
</html>
