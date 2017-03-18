<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>选课页</title>
    <link  href="${ctx}/static/inxweb/css/bootstrap.min.css" rel="stylesheet" >
</head>
<body>
<article class="col-7 fl">
    <div class="u-r-cont">

            <div>
                <section class="c-infor-tabTitle c-tab-title">
                    <a href="${ctx }/uc/showValidCourseLocation" title="选课中心" class="cursor: default;">选课中心</a>
                    <a href="${ctx }/uc/myCourse" title="Wo的课程" class="current">我的课程</a>
                </section>
                <p class="p">请选择上课教室完成选课。说明：灰色表示教室选课人数已满，不可再选。</p>
                ${msg}
                <aside class="c-attr-wrap-1">
                    <c:forEach items="${list}" var="x" varStatus="status">
                            <button type="button" class="btn1">
                                <span class="c-fff">${x.classroom}<br> 已选${x.maxNumber-x.residaulCount}人/容量${x.maxNumber}人<br>任课教师：${x.teacherName}</span>
                            </button>
                        <a onclick="select('${userId}/${x.courseId}')">退课</a>
                        <c:if test="${status.index%3!=0}">
                            <br/>
                        </c:if>
                    </c:forEach>
                </aside>
                <p>排序的规则：1、可选人数多 2、不可选人数放后面</p>
            </div>

    </div>
</article>
<script type="text/javascript">
    function select(id) {
        $.get("${ctx}/admin/humanselect/backCourse/"+id,null,function (data) {
            alert(data.message);
            window.location="${ctx}/admin/humanselect/studentSelect";
        });
    }
</script>
</body>
</html>