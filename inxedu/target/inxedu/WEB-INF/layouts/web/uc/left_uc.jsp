<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<style>
	.cur{
		cursor: pointer;
	}

</style>
<menu class="col-3 fl uMenu">
	<dl>
		<dt> 
			<section class="of">
				<div class="u-face-pic">
					<img src="${ctx }/static/inxweb/img/avatar-boy.gif" alt="" class="userImgPhoto">
					<a href="${ctx}/uc/initUpdateUser/1" title="" class="c-fff">修改头像</a>
				</div>
				<h4 class="mt10"><span class="fsize16 c-666 userNameClass"><!-- 用户名 --></span></h4>
				<div class="hLh30 mt10">
					<a href="${ctx}/uc/initUpdateUser/0" title="" class="c-blue">个人资料设置</a>
				</div>
				<div class="clear"></div>
			</section><!-- /u-face-attr -->
		</dt>
		<dd class="u-m-dd">
			<ul>
				<li>
					<span>我的学习</span>
					<ol>
                	<li><a href="${ctx}/uc/index" title="" >选课中心</a></li>

						<li><a href="${ctx}/uc/myCourse" title="">我的课程</a></li>
					</ol>
				</li>
			</ul>
			<ul>
				<li>
					<span>我的预约</span>
					<ol>
						<li><a onclick="mobileHrefCheckLogin('${ctx}/uc/appointment')" class="cur" title="预约中心">预约中心</a></li>
						<li><a href="${ctx}/uc/appointment/result" title="我的预约">我的预约</a></li>
					</ol>
				</li>
			</ul>
          <ul>
				<li>
					<span>我的资料</span>
					<ol>
						<li><a href="${ctx}/uc/initUpdateUser/0" title="">基本资料</a></li>
						<li><a href="${ctx}/uc/initUpdateUser/1" title="">个人头像</a></li>
						<li><a href="${ctx}/uc/initUpdateUser/2" title="">密码设置</a></li>
					</ol>
				</li>
			</ul>
		</dd>
	</dl>
</menu>
<!-- /公共左侧菜单 结束 -->