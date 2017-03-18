<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>个人中心</title>
</head>
<body>
<article class="col-7 fl">
	<div class="u-r-cont">
		<section>
			<div>
				<section class="c-infor-tabTitle c-tab-title">
					<a href="#" title="Wo的学习" style="cursor: default;">我的学习</a>
					<a href="javascript: void(0)" title="Wo的课程" style="cursor: default;">我的课程</a>
					<a href="${ctx }/uc/freeCourseList" title="选课中心" class="current">选课中心</a>
				</section>
			</div>
			<div class="mt40">
				<c:if test="${courseList==null || courseList.size()<=0 }">
					<!-- /无数据提示 开始-->
					<section class="no-data-wrap">
						<em class="icon30 no-data-ico">&nbsp;</em>
						<span class="c-666 fsize14 ml10 vam">暂无免费课程！</span>
					</section>
					<!-- /无数据提示 结束-->
				</c:if>
				<c:if test="${not empty courseList }">
					<div class="u-course-list">
						<article class="comm-course-list">
							<ul class="clearfix">
								<li>
									<div class="cc-l-wrap">
										<section class="course-img"><img
												src="/images/upload/course/20150915/1442295455437.jpg"
												class="img-responsive" alt="零基础入门学习Python课程学习"><br>任课教师：xxx
											<div class="cc-mask">
												<a href="/uc/play/10" title=""
												   class="comm-btn c-btn-1">继续学习</a>
											</div>
										</section>
										<h3 class="hLh30 txtOf mt10">
											<a href="/uc/play/10" title="零基础入门学习Python课程学习"
											   class="course-title fsize18 c-333">零基础入门学习Python课程学习</a>
										</h3>
										<section class="mt10 of">
                                                <span class="fr jgTag bg-green">
                                                    <tt  class="c-fff fsize12 f-fA">详细</tt>  |
                                                    <tt  class="c-fff fsize12 f-fA">选修</tt>
                                                </span>
                                                <span  class="fl jgAttr c-ccc f-fA">
                                                 <tt class="c-999 f-fA">89人已选修</tt> |
                                                 <tt  class="c-999 f-fA">浏览1114次</tt>
                                                </span>
										</section>
									</div>
								</li>
								<li>
									<div class="cc-l-wrap">
										<section class="course-img"><img
												src="/images/upload/course/20150915/1442295472860.jpg"
												class="img-responsive" alt="影想力摄影小课堂"><br>任课教师：xxx
											<div class="cc-mask"><a href="/uc/play/11" title=""
																	class="comm-btn c-btn-1">继续学习</a></div>
										</section>
										<h3 class="hLh30 txtOf mt10"><a href="/uc/play/11" title="影想力摄影小课堂"
																		class="course-title fsize18 c-333">影想力摄影小课堂</a>
										</h3>
										<section class="mt10 of">
                                                 <span class="fr jgTag bg-green">
                                                    <tt  class="c-fff fsize12 f-fA">详细</tt>  |
                                                    <tt  class="c-fff fsize12 f-fA">选修</tt>
                                                </span>
                                                <span  class="fl jgAttr c-ccc f-fA">
                                                 <tt class="c-999 f-fA">89人已选修</tt> |
                                                 <tt  class="c-999 f-fA">浏览1114次</tt>
                                                </span>
										</section>
									</div>
								</li>
								<li>
									<div class="cc-l-wrap">
										<section class="course-img"><img
												src="/images/upload/course/20150915/1442302831779.jpg"
												class="img-responsive" alt="数学给宝宝带来的兴趣"><br>任课教师：xxx
											<div class="cc-mask"><a href="/uc/play/12" title=""
																	class="comm-btn c-btn-1">继续学习</a></div>
										</section>
										<h3 class="hLh30 txtOf mt10"><a href="/uc/play/12" title="数学给宝宝带来的兴趣"
																		class="course-title fsize18 c-333">数学给宝宝带来的兴趣</a>
										</h3>
										<section class="mt10 of">
                                                <span class="fr jgTag bg-green">
                                                    <tt  class="c-fff fsize12 f-fA">详细</tt>  |
                                                    <tt  class="c-fff fsize12 f-fA">选修</tt>
                                                </span>
                                                <span  class="fl jgAttr c-ccc f-fA">
                                                 <tt class="c-999 f-fA">89人已选修</tt> |
                                                 <tt  class="c-999 f-fA">浏览1114次</tt>
                                                </span>
										</section>
									</div>
								</li>
								<li>
									<div class="cc-l-wrap">
										<section class="course-img"><img
												src="/images/upload/course/20150915/1442295506745.jpg"
												class="img-responsive" alt="国家教师资格考试专用"><br>任课教师：xxx
											<div class="cc-mask"><a href="/uc/play/13" title=""
																	class="comm-btn c-btn-1">继续学习</a></div>
										</section>
										<h3 class="hLh30 txtOf mt10"><a href="/uc/play/13" title="国家教师资格考试专用"
																		class="course-title fsize18 c-333">国家教师资格考试专用</a>
										</h3>
										<section class="mt10 of">
                                                 <span class="fr jgTag bg-green">
                                                    <tt  class="c-fff fsize12 f-fA">详细</tt>  |
                                                    <tt  class="c-fff fsize12 f-fA">选修</tt>
                                                </span>
                                                <span  class="fl jgAttr c-ccc f-fA">
                                                 <tt class="c-999 f-fA">89人已选修</tt> |
                                                 <tt  class="c-999 f-fA">浏览1114次</tt>
                                                </span>
										</section>
									</div>
								</li>
								<li>
									<div class="cc-l-wrap">
										<section class="course-img"><img
												src="/images/upload/course/20150915/1442295527931.jpg"
												class="img-responsive" alt="XHTML CSS2 JS整站制作教程课程学习"><br>任课教师：xxx
											<div class="cc-mask"><a href="/uc/play/14" title=""
																	class="comm-btn c-btn-1">继续学习</a></div>
										</section>
										<h3 class="hLh30 txtOf mt10"><a href="/uc/play/14"
																		title="XHTML CSS2 JS整站制作教程课程学习"
																		class="course-title fsize18 c-333">XHTML
											CSS2 JS整站制作教程课程学习</a></h3>
										<section class="mt10 of">
                                                 <span class="fr jgTag bg-green">
                                                    <tt  class="c-fff fsize12 f-fA">详细</tt>  |
                                                    <tt  class="c-fff fsize12 f-fA">选修</tt>
                                                </span>
                                                <span  class="fl jgAttr c-ccc f-fA">
                                                 <tt class="c-999 f-fA">89人已选修</tt> |
                                                 <tt  class="c-999 f-fA">浏览1114次</tt>
                                                </span>
										</section>
									</div>
								</li>
								<li>
									<div class="cc-l-wrap">
										<section class="course-img"><img
												src="/images/upload/course/20150915/1442295556203.jpg"
												class="img-responsive" alt="HTML5入门课程学习"><br>任课教师：xxx
											<div class="cc-mask"><a href="/uc/play/15" title=""
																	class="comm-btn c-btn-1">继续学习</a></div>
										</section>
										<h3 class="hLh30 txtOf mt10"><a href="/uc/play/15" title="HTML5入门课程学习"
																		class="course-title fsize18 c-333">HTML5入门课程学习</a>
										</h3>
										<section class="mt10 of">
                                                 <span class="fr jgTag bg-green">
                                                    <tt  class="c-fff fsize12 f-fA">详细</tt>  |
                                                    <tt  class="c-fff fsize12 f-fA">选修</tt>
                                                </span>
                                                <span  class="fl jgAttr c-ccc f-fA">
                                                 <tt class="c-999 f-fA">89人已选修</tt> |
                                                 <tt  class="c-999 f-fA">浏览1114次</tt>
                                                </span>
										</section>
									</div>
								</li>
								<li>
									<div class="cc-l-wrap">
										<section class="course-img"><img
												src="/images/upload/course/20150915/1442295379715.jpg"
												class="img-responsive" alt="20世纪西方音乐"><br>任课教师：xxx
											<div class="cc-mask"><a href="/uc/play/16" title=""
																	class="comm-btn c-btn-1">继续学习</a></div>
										</section>
										<h3 class="hLh30 txtOf mt10"><a href="/uc/play/16" title="20世纪西方音乐"
																		class="course-title fsize18 c-333">20世纪西方音乐</a>
										</h3>
										<section class="mt10 of">
                                                 <span class="fr jgTag bg-green">
                                                    <tt  class="c-fff fsize12 f-fA">详细</tt>  |
                                                    <tt  class="c-fff fsize12 f-fA">选修</tt>
                                                </span>
                                                <span  class="fl jgAttr c-ccc f-fA">
                                                 <tt class="c-999 f-fA">89人已选修</tt> |
                                                 <tt  class="c-999 f-fA">浏览1114次</tt>
                                                </span>
										</section>
									</div>
								</li>
								<li>
									<div class="cc-l-wrap">
										<section class="course-img"><img
												src="/images/upload/course/20150915/1442295570359.jpg"
												class="img-responsive" alt="MySql从入门到精通"><br>任课教师：xxx
											<div class="cc-mask"><a href="/uc/play/17" title=""
																	class="comm-btn c-btn-1">继续学习</a></div>
										</section>
										<h3 class="hLh30 txtOf mt10"><a href="/uc/play/17" title="MySql从入门到精通"
																		class="course-title fsize18 c-333">MySql从入门到精通</a>
										</h3>
										<section class="mt10 of">
                                                  <span class="fr jgTag bg-green">
                                                    <tt  class="c-fff fsize12 f-fA">详细</tt>  |
                                                    <tt  class="c-fff fsize12 f-fA">选修</tt>
                                                </span>
                                                <span  class="fl jgAttr c-ccc f-fA">
                                                 <tt class="c-999 f-fA">89人已选修</tt> |
                                                 <tt  class="c-999 f-fA">浏览1114次</tt>
                                                </span>
										</section>
									</div>
								</li>
								<li>
									<div class="cc-l-wrap">
										<section class="course-img"><img
												src="/images/upload/course/20150915/1442295581911.jpg"
												class="img-responsive" alt="Java精品课程"><br>任课教师：xxx
											<div class="cc-mask"><a href="/uc/play/18" title=""
																	class="comm-btn c-btn-1">继续学习</a></div>
										</section>
										<h3 class="hLh30 txtOf mt10"><a href="/uc/play/18" title="Java精品课程"
																		class="course-title fsize18 c-333">Java精品课程</a>
										</h3>
										<section class="mt10 of">
                                                 <span class="fr jgTag bg-green">
                                                    <tt  class="c-fff fsize12 f-fA">详细</tt>  |
                                                    <tt  class="c-fff fsize12 f-fA">选修</tt>
                                                </span>
                                                <span  class="fl jgAttr c-ccc f-fA">
                                                 <tt class="c-999 f-fA">89人已选修</tt> |
                                                 <tt  class="c-999 f-fA">浏览1114次</tt>
                                                </span>
										</section>
									</div>
								</li>
							</ul>
						</article>
						<!-- 公共分页 开始 -->
						<jsp:include page="/WEB-INF/view/common/front_page.jsp"/>
						<!-- 公共分页 结束 -->
						<form method="post" id="searchForm" action="${ctx}/uc/freeCourseList">
							<input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1"/>
						</form>
					</div>
				</c:if>
			</div>
		</section>
		<!-- /我的课程 -->
	</div>
</article>
</body>
</html>