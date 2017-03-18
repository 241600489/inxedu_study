<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>首页</title>
	<%--<link href="${ctx}/static/inxweb/css/bootstrap.min.css" rel="stylesheet" type="text/css">--%>
<script>
	var theme_color = '${theme_color}';
</script>
</head> 
<body>
	<div class="i-slide">
		<section>
			<!-- 如果需要导航按钮 -->
			<a class="arrow-left s-arrow" href="javascript:void(0)"></a>
			<a class="arrow-right s-arrow" href="javascript:void(0)"></a>
			<!-- 图片位置 -->
			<div class="swiper-container">
				<div class="swiper-wrapper">

						<div class="swiper-slide" style="background: #6e6e6e">
							<%--<c:if test="${!empty image.color}">${image.color}</c:if>;">--%>
							<a target="_blank" href="<c:if test='${image.linkAddress!=null&&image.linkAddress!=""}'>${image.linkAddress}</c:if>">
								<img class="imgload" src="/images/upload/image/carousel.jpg" alt="${image.title}">
							</a>
						</div>
						<div class="swiper-slide" style="background: #6e6e6e">
								<%--<c:if test="${!empty image.color}">${image.color}</c:if>;">--%>
							<a target="_blank" href="<c:if test='${image.linkAddress!=null&&image.linkAddress!=""}'>${image.linkAddress}</c:if>">
								<img class="imgload" src="/images/upload/image/carousel1.jpg" alt="${image.title}">
							</a>
						</div>

				</div>
			</div>
			<!-- 如果需要分页器 -->
			<div class="pagination"></div>
		</section>
	</div>
	<!-- /index slide -->
	<div id="aCoursesList">
		<!-- /自选课程 开始 -->
		<div class="bg-fa of">
			<section class="container">
				<header class="comm-title">
					<span class="fr mt5-1">
						<a href="javascript:void(0)" onclick="mobileHrefCheckLogin('${ctx }/front/showcoulist')" title="更多" class="c-master" >更多</a></span>
					<h2 class="fl tac">
						<span class="c-333">推荐课程</span>
					</h2>
					<div class="progress1">
						<div class="progress-bar1" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
							<span class="sr-only">60% Complete</span>
						</div>
					</div>
				</header>
				<div class="mt40">
					<div class="u-course-list">
						<article class="comm-course-list">
							<ul class="clearfix">
								<c:forEach items="${list}" varStatus="status" var="course">
									<c:if test="${status.index<3}">
										<c:if test="${course.logo!=null&&course.type==true}">
								<li>
									<div class="cc-l-wrap">
										<section class="course-img">


											<img
												src="${course.logo}"
												class="img-responsive" alt="${course.courseName}"><br>&nbsp;&nbsp;课时：${course.lessionNum}课时
										</section>
										<section class="aniu">
											<button type="button" class="btn btn-primary btn-lg"  onclick="mobileHrefCheckLogin('${ctx}/front/couinfo/${course.courseId}')" >课程详细</button>

										</section>
									</div>
								</li>
								</c:if>
									</c:if>
								</c:forEach>
							</ul>
						</article>
					</div>
				</div>
			</section>
			<!-- /自选课程 结束 -->
			<!-- /学霸明星 开始 -->
				<section class="container">
					<header class="comm-title">
						<span class="fr mt5-1"><a href="javascript:void(0)" title="更多" class="c-master" >更多</a></span>
						<h2 class="fl tac">
							<span class="c-333">学霸明星</span>
						</h2>
						<div class="progress1">
							<div class="progress-bar1" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
								<span class="sr-only">60% Complete</span>
							</div>
						</div>
					</header>
						<div class="u-course-list">
							<article class="comm-course-list">
								<ul class="clearfix">
									 <li>
										<table class="t-s">
											<section class="ml20 mr15">
													<tr >
															<td class="td-s" onmouseover="personName('陈思豪')">
																<img src="/static/inxweb/img/student/chensihao.jpg" class="img-s" />
															</td>
															<td class="td-s" onmouseover="personName('韩晓雪')">
																<img src="/static/inxweb/img/student/hanxiaoxue.jpg" class="img-s" />
															</td>
															<td class="td-s" onmouseover="personName('张德翼')">
																<img src="/static/inxweb/img/student/zhangdeyi.jpg" class="img-s" />
															</td>
															<td colspan="3" rowspan="2" class="span-t" >
																<p class="t-p">下一个会是你吗？</p>
																<a href="#" class="a-s"><b>查看更多</b></a>
															</td>
													</tr>
													<tr>
															<td class="td-s" onmouseover="personName('李参')">
																<img src="/static/inxweb/img/student/lishen.jpg" class="img-s" />
															</td>
															<td class="td-s" onmouseover="personName('张乐')">
																<img src="/static/inxweb/img/student/zhangle.jpg" class="img-s" />
															</td>
															<td class="td-s" onmouseover="personName('张佐杨')">
																<img src="/static/inxweb/img/student/zhangzuoyang.jpg" class="img-s" />
															</td>
													</tr>
													<tr>
															<td class="td-s" onmouseover="personName('刘浩')">
																<img src="/static/inxweb/img/student/liuhao.jpg" class="img-s" />
															</td>
															<td class="td-s" onmouseover="personName('邰振强')">
																<img src="/static/inxweb/img/student/taizhenqiang.jpg" class="img-s" />
															</td>
															<td class="td-s" onmouseover="personName('肖天宇')">
																<img src="/static/inxweb/img/student/xiaotianyu.jpg" class="img-s" />
															</td>
															<td class="td-s" onmouseover="personName('陈俊文')">
																<img src="/static/inxweb/img/student/chenjunwen.jpg" class="img-s" /></td>
															<td class="td-s" onmouseover="personName('陈来斌')">
																<img src="/static/inxweb/img/student/chenlaibin.jpg" class="img-s" /></td>
														    <td class="td-s" onmouseover="personName('梁自强')">
															    <img src="/static/inxweb/img/student/liangziqiang.jpg" class="img-s" /></td>
														</tr>
														<tr>
															<td class="td-s" onmouseover="personName('排队')">
																<img src="/static/inxweb/img/student/hui(1).png" class="img-s" /></td>
															<td class="td-s" onmouseover="personName('排队')">
																<img src="/static/inxweb/img/student/hui(1).png" class="img-s" /></td>
															<td class="td-s" onmouseover="personName('排队')">
																<img src="/static/inxweb/img/student/hui(1).png" class="img-s" /></td>
															<td class="td-s" onmouseover="personName('排队')">
																<img src="/static/inxweb/img/student/hui(1).png" class="img-s"/></td>
															<td class="td-s" onmouseover="personName('排队')">
																<img src="/static/inxweb/img/student/hui(1).png" class="img-s" /></td>
															<td class="td-s" onmouseover="personName('排队')">
																<img src="/static/inxweb/img/student/hui(1).png" class="img-s"/></td>
														</tr>
											</section>
										</table>

									</li>
									<li>
										<div class="c-attr-wrap-11" >
											<section class="ml20 mr15">
												<h2 class="hLh30 txtOf mt151">
													<span class="c-fff fsize15">明星介绍</span>
												</h2>

											</section>
											<div class="star">
												<div id="studentContent">

												</div>
											</div>

										</div>
									</li>

								</ul>
							</article>
						</div>
				</section>
			<!-- /学霸明星 结束 -->
		</div>




	</div>
	<script type="text/javascript" src="${ctx}/static/inxweb/js/swiper-2.1.0.js"></script>
	<script type="text/javascript" src="${ctx}/static/inxweb/front/index.js"></script>
	<script type="text/javascript" src="${ctx}/static/inxweb/front/index_theme_color.js"></script><!-- 换肤 -->
	<script>
		$(function() {
			sSwiperFun(); //幻灯片调取
			upSlideFun("#iQuestion"); //向上滚动互动
			scrollLoad(); //响应滚动加载课程图片
			$(".boutiqueCourse").click();//网校课程-精品课程
			huanyihuan();
			studentDynamic();//学生动态



			var uri = window.location.search;
			var val = "msg";
			var re = new RegExp("" + val + "=([^&?]*)", "ig");
			var msg=((uri.match(re)) ? (uri.match(re)[0].substr(val.length + 1)) : null);
			if(msg!=null && msg!='' && msg=='LimitLogin'){
				dialog('提示信息',"您的帐号在其他地点登录，请重新登录",1);
			}
		});


		/*学霸明星介绍*/
		var studentContent = document.getElementById("studentContent");

			function personName(type) {

				switch (type) {
						//case '增加字典信息':alert('xxxx');break;
					case '陈思豪':
						studentContent.innerHTML = "姓名：陈思豪" +
								"<br>学院班级：通信学院 电信151班" +
								"<br>来自雾都重庆" +
								"<br>每天Update自己的Database，时刻Get自己的Minutes；" +
								"兴趣,她一直都是我最好的老师;来到这里,我找到了自己的Teachers";
						break;

					case '韩晓雪':
						studentContent.innerHTML = "姓名：韩晓雪" +
								"<br>学院班级：应用技术学院计应131班" +
								"<br>了解JavaWeb前端和后端的开发，熟悉JavaWeb相关的框架内容，并参与了我校现代教育中心选课系统项目。";
						break;

					case '张德翼':
						studentContent.innerHTML = "姓名：张德翼" +
								"<br>学院班级：通信与电子工程学院物联网工程14级" +
								"<br>在第二课堂中，可以自己选择自己感兴趣的方向去学习，不仅有老师的帮助，还有其他志向相同的同学可以相互探讨。在其中，学到了很多东西，很感激第二课堂这个平台。";
						break;

					case '肖天宇':
						studentContent.innerHTML = "姓名：肖天宇" +
								"<br>学院班级：食品与生物工程学院生工141班" +
								"<br>拥有扎实的java基础和良好的编程风格，熟悉javabean和MVC设计模式，熟悉struts2,springmvc,spring,mybatis,hibernate等主流框架，现研究IOS操作系统。";
						break;

					case '张乐':
						studentContent.innerHTML = "姓名：张乐" +
								"<br>学院班级：理学院141班" +
								"<br>擅长JavaWeb后端开发，熟悉相关主流框架，并了解JavaWeb前端开发和安卓开发。参与我校国教院留学生在线申请系统、现代教育技术中心选课系统等项目。	";
						break;

					case '邰振强':
						studentContent.innerHTML = "姓名:邰振强" +
										"<br>学院班级:通信与电子工程学院电子14";
						break;

					case '张佐杨':
						studentContent.innerHTML = "姓名:张佐杨" +
								"<br>学院班级:通信与电子工程学院电子14";
						break;

					case '刘浩':
						studentContent.innerHTML = "姓名:刘浩" +
								"<br>学院班级:通信与电子工程学院电子14";
						break;

					case '李参':
						studentContent.innerHTML = "姓名:李参" +
								"<br>学院班级：应用技术学院计应132" +
								"<br>WEB前端开发，绘画、摄影爱好者，精通HTML、div、css布局，熟练使用JQuery、JQueryUI、JS、Bootstrap、PS，了解一些有关UI设计等.";
						break;
					case '陈俊文':
						studentContent.innerHTML = "姓名：陈俊文" +
								"<br>学院班级：机械133班" +
								"<br>编程语言爱好者，了解编译原理，熟悉网站开发。"+
						"2016.5.22 Vert.x北京用户组沙龙闪电演讲嘉宾，参与我校国教院留学生在线申请系统、电商系统、现在教育技术中心选课系统等项目。"
						break;
					case '陈来斌':
						studentContent.innerHTML = "姓名：陈来斌" +
								"<br>学院班级：理学院信科142" +
						"<br>擅长linux运维，熟悉zabbi等配置；熟练使用shell、python等脚本语言；参与电商项目、现代教育技术中心选课系统等项目。"
						break;
					case '梁自强':
						studentContent.innerHTML = "姓名：梁自强" +
								"<br>学院班级：理学院信科141" +
								"<br>擅长javaWeb后端开发,能够熟练运用五大主流框架,并涉猎web前端开发；参与现代教育技术中心选课系统和预约系统等项目。"
						break;
					case  '排队':
						studentContent.innerHTML = "排队中...";
						break;
				}
			}

	</script>
</body>
</html>