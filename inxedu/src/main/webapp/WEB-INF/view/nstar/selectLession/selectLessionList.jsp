<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: karak
  Date: 16-7-21
  Time: 9:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>课程选择</title>
</head>

<body>
<h1>课程选择</h1>
<ul id="lessionList">
    <c:forEach var="x" items="${list}">
        <li id="${x.courseId}">
            <a href="/selectLession/selectLession/${x.courseId}">
            <td>课程名称：${x.courseName}</td>
            <td>教师名称：${x.teacherName}</td>
            <td>课程容量：${x.maxNumber}</td>
            <td>选课人数：${x.selectCount}</td>
            </a></li>
    </c:forEach>
</ul>
</body>
</html>
