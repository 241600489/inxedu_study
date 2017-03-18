<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>选课页</title>
    <link href="${ctx}/static/inxweb/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/static/common/jcrop/jquery.Jcrop.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/inxweb/user/user.js"></script>
    <script type="text/javascript" src="${ctx}/kindeditor/kindeditor-all.js"></script>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
</head>
<body>
<article class="col-7 fl">
    <div class="u-r-cont">
        <section>
            <div>
                <section class="c-infor-tabTitle c-tab-title">
                    <%--<a href="${ctx }/uc/index" title="完善资料" class="current">完善资料</a>--%>
                    <a href="${ctx }/uc/index" title="选课中心" class="current">选课中心</a>
                    <a href="${ctx }/uc/myCourse" title="Wo的课程" style="cursor: default;">我的课程</a>
                </section>
                <p class="p">请选择上课教室完成选课。说明：灰色表示教室选课人数已满，不可再选。</p>
                <aside class="c-attr-wrap-1">
                    <c:forEach items="${list}" var="x" varStatus="status">
                    <c:if test="${x.residaulCount le 0}">
                    <button type="button" class="btn2">
                        </c:if>
                        <c:if test="${x.residaulCount gt 0}">
                        <a  onclick="select('${x.courseId}')">
                            <button type="button" class="btn1">
                                </c:if>
                                <span class="c-fff">${x.courseName}<br> 已选${x.maxNumber-x.residaulCount}人/容量${x.maxNumber}人<br>任课教师：${x.teacherName}</span>
                            </button>
                            <c:if test="${x.residaulCount gt 0}">
                        </a>
                        </c:if>
                        <c:if test="${status.index%3!=0}">
                        <br/>
                        </c:if>
                        </c:forEach>
                </aside>
            </div>
        </section>
    </div>
</article>

</body>
</html>