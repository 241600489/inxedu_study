<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- /global header begin-->
<style>
	.cur{
		cursor: pointer;
	}
</style>
<header id="header">
	<section class="container">
		<h1 id="logo">
			<a href="${ctx }/" title="${websitemap.web.company}">
				<img src="<%=staticImage%>${logomap.logo.url}" width="100%" alt="${websitemap.web.company}">
			</a>
		</h1>
		<div class="h-r-nsl">
			<ul class="nav">
				<c:forEach items="${navigatemap.INDEX}" var="indexNavigate">
					<%--<li>
						<a href="${ctx}${indexNavigate.url}" title="${indexNavigate.name}"
						   <c:if test="${indexNavigate.newPage==0}">target="_blank"</c:if>>${indexNavigate.name}</a></li>--%>
				</c:forEach>
				<li><a href="${ctx}/" title="首页" target="_parent">首页</a></li>
				<li><a href="${ctx}/front/showcoulist" title="课程" target="_parent">课程</a></li>
				<li><a href="${ctx}/front/teacherlist" title="教师" target="_parent">教师</a></li>
				<li><a  onclick="if(isLogin()){ window.location.href='/uc/appointment'} else{lrFun(0);}" class="cur" title="预约" target="_parent">预约</a></li>
				<li><a href="${ctx}/front/helpCenter" title="帮助中心" target="_parent">帮助中心</a></li>
			</ul>
			<!-- / nav -->
			<ul class="h-r-login">
				<li class="undis" id="no-login">
					<a href="javascript:lrFun(1)" title="登录">
						<em class="icon18 login-icon">&nbsp;</em>
						<span class="vam ml5">登录</span>
					</a>
				</li>
				<li class="mr10 undis" id="is-login-one">
					<q class="red-point" style="display: none">&nbsp;</q>
				</li>
				<li class="h-r-user undis" id="is-login-two">
					<a href="${ctx}/uc/index" title="">
					   <img src="${ctx }/static/inxweb/img/avatar-boy.gif" width="30" height="30" class="vam picImg" alt="">
					      <span class="vam disIb" id="userName"></span>
					</a>
					<a href="javascript:void(0)" title="退出" onclick="exit();" class="ml5">退出</a>
				</li>
				<!-- /未登录显示第1 li；登录后显示第2，3 li -->
			</ul>
		</div>
		<aside class="mw-nav-btn">
			<div class="mw-nav-icon"></div>
		</aside>
		<div class="clear"></div>
	</section>
</header>
<!-- /global header end-->
<div class="h-mobile-mask"></div>
<div class="head-mobile">
	<div class="head-mobile-box">
		<section class="clearfix">
			<div class="u-face-pic">
				<img src="${ctx }/static/inxweb/img/avatar-boy.gif" alt="" class="userImgPhoto">
				<a href="${ctx }/uc/initUpdateUser/1" title="" class="c-fff">修改头像</a>
			</div>
			<h4 class="hLh30 txtOf">
				<span class="fsize16 c-ccc userNameClass">
					<span class="vam ml5" style="cursor:pointer;" onclick="lrFun(1)">登录</span>
				</span>
			</h4>
			<div class="hLh30">
				<a href="${ctx}/uc/initUpdateUser/0" title="个人资料设置" class="c-999">个人资料设置</a>
			</div>
			<div class="hLh20 undis" id="mobileExitDiv">
				<a href="javascript:void(0)" title="退出" onclick="exit();" class="c-999">退出</a>
			</div>
		</section>
		<nav class="mw-nav">
			<ul class="clearfix">
				<c:forEach items="${navigatemap.INDEX}" var="indexNavigate">
					<li>
						<a href="${ctx}${indexNavigate.url}" title="${indexNavigate.name}" <c:if test="${indexNavigate.newPage==0}">target="_blank"</c:if>>${indexNavigate.name}
						</a>
					</li>
				</c:forEach>
			</ul>
		</nav>
		<section class="u-m-dd">
			<ul>
				<li>
					<span>我的学习</span>
					<ol>
						<li class="current"><a href="javascript:void(0)" onclick="mobileHrefCheckLogin('${ctx }/uc/showValidCourseLocation')" title="">课程中心</a></li>
						<li><a href="javascript:void(0)" onclick="mobileHrefCheckLogin('${ctx }/uc/myCourse')" title="">我的课程</a></li>
					</ol>
				</li>
			</ul>
			<ul>
				<li>
					<span>我的考试</span>
					<ol>
						<li><a href="javascript:void(0)" onclick="mobileHrefCheckLogin('${ctx}/uc/appointment')" title="">预约中心</a></li>
						<li><a href="javascript:void(0)" onclick="mobileHrefCheckLogin('${ctx}/uc/appointment/result')" title="">我的预约</a></li>
					</ol>
				</li>
			</ul>
			<ul>
				<li>
					<span>我的资料</span>
					<ol>
						<li><a href="javascript:void(0)" onclick="mobileHrefCheckLogin('${ctx }/uc/initUpdateUser/0')" title="">基本资料</a></li>
						<li><a href="javascript:void(0)" onclick="mobileHrefCheckLogin('${ctx }/uc/initUpdateUser/1')" title="">个人头像</a></li>
						<li><a href="javascript:void(0)" onclick="mobileHrefCheckLogin('${ctx }/uc/initUpdateUser/2')" title="">密码设置</a></li>
					</ol>
				</li>
			</ul>
		</section>
	</div>
</div>
<script>
	$(function() {
		wmNavFun(); // 手机端导航方法
		goTopFun(); //返回顶部
	})
</script>