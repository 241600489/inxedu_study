<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>选课页</title>
    <link href="${ctx}/static/inxweb/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

<input type="hidden" name="user.userId" value="${user.userId}">

${user.userName}的可选课程:
    <div class="u-r-cont">

            <div>
                <p class="p">请选择上课教室完成选课。说明：灰色表示教室选课人数已满，不可再选。</p>
                <aside class="c-attr-wrap-1">
                    <c:forEach items="${list}" var="x" varStatus="status">
                    <c:if test="${x.residaulCount le 0}">
                    <button type="button" class="btn2">
                        </c:if>
                        <c:if test="${x.residaulCount gt 0}">
                        <a onclick="select('${userId}/${x.courseId}')">
                            <button type="button" class="btn1">
                                </c:if>
                                <span class="c-fff">${x.classroom}<br> 已选${x.maxNumber-x.residaulCount}人/容量${x.maxNumber}人<br>任课教师：${x.teacherName}</span>
                            </button>
                            <c:if test="${x.residaulCount gt 0}">
                        </a>
                        </c:if>
                        <c:if test="${status.index%3!=0}">
                        <br/>
                        </c:if>
                        </c:forEach>
                </aside>
                <p class="pc">排序的规则：1、可选人数多 2、不可选人数放后面</p>
            </div>

    </div>
<script>
    function select(id) {
        $.get("${ctx}/admin/humanselect/selectCourse/"+id,null,function (data) {
           alert(data.message);
            window.location="${ctx}/admin/humanselect/studentSelect";
        });
    }
</script>
</body>
</html>