<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: seapig02
  Date: 7/28 0028
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${ctx}/static/common/jquery-1.11.1.min.js"></script>
</head>
<body>

<form action="${ctx}/selectLession/select.action">
    课程名称:<input name="courseName"  type="text"/>
    <input type="submit" value="查询" name="select"/>
    <a href="${ctx}/selectLession/toaddCourse.action" >添加</a>
</form>

<form action="${ctx}/selectLession/deleteCourses.action">
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
                    <a href="${ctx}/selectLession/toupadateCourse.action?id=${eduCourseBase.id}" class="button tooltip">修改</a>
                    <a href="${ctx}/selectLession/delete.action?id=${eduCourseBase.id}" class="button tooltip">删除</a>
                </td>
           <tr/>
        </c:forEach>
   <tralign="center"><input id="deletes" type="submit" value="批量删除"/> </tr>
    </table>
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
