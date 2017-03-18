<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: seapig02
  Date: 7/28 0028
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<script type="text/javascript" src="${ctx}/static/common/multilevel.js"></script>

<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${ctx}/static/common/jquery-1.11.1.min.js"></script>
</head>
<body>

<form action="${ctx}/admin/courseBase/select.action" method="post" id="searchForm">
    课程名称:<input name="courseBase.courseName"  type="text" value="${courseBase.courseName}"/>
    课程号：<input name="courseBase.courseNo" type="text" value="${courseBase.courseNo}"/>
    <input type="hidden" id="pageCurrentPage" name="page.currentPage"/>
    <input type="submit" value="查询" name="select"/>

    <a href="${ctx}/admin/courseBase/toaddCourse.action" >添加</a>
</form>
<form action="${ctx}/admin/courseBase/deleteCourses.action" method="post" id="searchForm">
    <table>
        <tr>
         <td align="center">全选:<input id="checkid" type="checkbox"/></td>
            <td align="center">id</td>
            <td align="center">课程名称</td>
            <td align="center">课程号</td>
            <td align="center">备注</td>
            <td align="center">操作</td>
        </tr>

        <c:forEach items="${list}" var="eduCourseBase">
            <tr>
            <td align="center"><input type="checkbox" name="ids" value="${eduCourseBase.id}"></td>
            <td align="center">${eduCourseBase.id}</td>
            <td align="center">${eduCourseBase.courseName}</td>
            <td align="center">${eduCourseBase.courseNo}</td>
            <td align="center">${eduCourseBase.memo}</td>
                <td align="center">
                    <a href="${ctx}/admin/courseBase/toupadateCourse.action?id=${eduCourseBase.id}&currentPage=${page.currentPage}" class="button tooltip">修改</a>
                    <a href="${ctx}/admin/courseBase/delete?id=${eduCourseBase.id}" class="button tooltip">删除</a>
                </td>
           <tr/>
        </c:forEach>
   <tr align="center"><input id="deletes" type="submit" value="批量删除"/> </tr>
    </table>
    <jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
</form>

<script type="text/javascript">
    $("#checkid").click(
            function () {
                var isChecked=$(this).prop("checked");
                $("input[name='ids']").prop("checked",isChecked);
            }
    );
</script>
<%--<script type="text/javascript">
    $

</script>--%>

</body>
</html>
