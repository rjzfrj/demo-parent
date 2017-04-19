<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/css.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/easyui/themes/icon.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/easyui/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/sdmenu.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/laydate/laydate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/easyui/jquery.validate.js"></script>
<style type="text/css">
body {
	background: #0066A8 url(/resources/img/login_bg.png) no-repeat center 0px;
}

.tit {
	margin: auto;
	margin-top: 170px;
	text-align: center;
	width: 350px;
	padding-bottom: 20px;
}

.login-wrap {
	width: 220px;
	padding: 30px 50px 0 330px;
	height: 220px;
	background: #fff url(/resources/img/20150212154319.jpg) no-repeat 30px
		40px;
	margin: auto;
	overflow: hidden;
}

.login_input {
	display: block;
	width: 210px;
}

.login_user {
	background: url(/resources/img/input_icon_1.png) no-repeat 200px center;
	font-family: "Lucida Sans Unicode", "Lucida Grande", sans-serif
}

.login_password {
	background: url(/resources/img/input_icon_2.png) no-repeat 200px center;
	font-family: "Courier New", Courier, monospace
}

.btn-login {
	background: #40454B;
	box-shadow: none;
	text-shadow: none;
	color: #fff;
	border: none;
	height: 35px;
	line-height: 26px;
	font-size: 14px;
	font-family: "microsoft yahei";
}

.btn-login:hover {
	background: #333;
	color: #fff;
}

.copyright {
	margin: auto;
	margin-top: 10px;
	text-align: center;
	width: 370px;
	color: #CCC
}

@media ( max-height : 700px) {
	.tit {
		margin: auto;
		margin-top: 100px;
	}
}

@media ( max-height : 500px) {
	.tit {
		margin: auto;
		margin-top: 50px;
	}
}
</style> --%>
<!-- 
<html>
<head>
    <title>登录</title>
    <style>.error{color:red;}</style>
</head>
<body>

<div class="error">${error}</div>
<form action="" method="post">
    用户名：<input type="text" name="username" value="<shiro:principal/>"><br/>
    密码：<input type="password" name="password"><br/>
    自动登录：<input type="checkbox" name="rememberMe" value="true"><br/>
    <input type="submit" value="登录">
</form>

</body>
</html> -->
<body>
	<div class="tit">
		<!-- <img src="$/resources/img/seller1.png" alt="" /> -->
	</div>
	<div class="error">${error}</div>
	<form action="" method="post" id="loginForm">
		<div class="login-wrap">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="25" valign="bottom">用户名：</td>
				</tr>
				<tr>
					<td><input type="text" name="username" id="username"
						class="login_input login_user" value="" /></td>
				</tr>
				<tr>
					<td height="35" valign="bottom">密 码：</td>
				</tr>
				<tr>
					<td><input type="password" name="password" id="password"
						class="login_input login_password" value="" /></td>
				</tr>
				<tr>
					<td height="60" valign="bottom">
					记住我：<input type="checkbox" name="rememberMe" value="true">
					<input type="submit" class="btn btn-block btn-login" value="登录" />
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>



