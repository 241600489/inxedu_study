<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<!-- Meta -->
<meta charset="utf-8" http-equiv="Content-Type" />
<!-- End of Meta -->
<!-- Page title -->
<title>登录 -${websitemap.web.company}-${websitemap.web.title}</title>
<!-- End of Page title -->
<meta name="author" content="${websitemap.web.author}" />
<meta name="keywords" content="${websitemap.web.keywords}" />
<meta name="description" content="${websitemap.web.description}" />
<link rel="shortcut icon" href="${ctx}/static/admin/assets/second.png" type="image/x-icon">
<!-- Libraries -->
<link type="text/css" href="${ctx}/static/admin/css/login.css" rel="stylesheet" />
<script type="text/javascript">
			function enterSubmit(event) {
				var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
				if (keyCode == 13) {
					$("#loginForm").submit();
					return false;
				}
			}
		</script>
</head>
<body>
	<div id="container">
		<div class="logo">
			<a href="#" target="_parent">
				<img src="${ctx}/static/admin/assets/logo-ht.png" width="250px"  />
			</a>
		</div>
	</div>

	<div class="b-box">
		<div id="container">
			<div id="box">
				<h2>现代教育技术中心后台管理系统</h2>
				<form action="${ctx}/admin/main/login" method="POST" id="loginForm">
					<p class="main">
						<label>用户名: </label>
						<input name="sysUser.loginName" onkeyup="enterSubmit(event)" value="${sysUser.loginName}" placeholder="输入用户名" />
						<label>密码: </label>
						<input type="password" onkeyup="enterSubmit(event)" name="sysUser.loginPwd" value="${sysUser.loginPwd}" placeholder="输入密码">
					</p>
					<p class="main">
						<label>验证码: </label>
						<input name="randomCode" onkeyup="enterSubmit(event)" placeholder="验证码" style="width: 105px;"  maxlength="4"/>
						<span class="yzm-pic">
							<img src="${ctx}/ran/random" alt="验证码，点击图片更换" onclick="this.src='${ctx}/ran/random?random='+Math.random();" />
						</span>
					</p>
					<p class="space">
						<input type="submit" value="登录" class="login" />
						<span>${message}</span>
					</p>
				</form>
			</div>
			<div class="login-foot">
				<span>
				<!--	Powered By <a target="_blank" href="http://www.inxedu.com/" style="color: #666;"></a>-->
				</span>
			</div>
		</div>
	</div>
</body>
</html>