<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: karak
  Date: 16-7-22
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>选课结果</title>
</head>
<body>
<c:if test="${msg!=null}">
    <h1>${msg}</h1>
</c:if>
<h1></h1>
<a href=" ${ctx}/selectLession/showTimeTable">选课时间表</a>
<c:forEach var="x" items="${result}">
        <li id="${x.courseId}">
                <td>课程名称：${x.courseName}</td>
                <td>教师名称：${x.teacherName}</td>
                <td>课程容量：${x.maxNumber}</td>
                <td>选课人数：${x.selectCount}</td>
            </li>
</c:forEach>

</body>
</html>
