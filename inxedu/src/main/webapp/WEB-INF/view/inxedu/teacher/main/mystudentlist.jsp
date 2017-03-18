<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: liangziqiang
  Date: 2016/11/17
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${ctx}/teacher/searchmystudent" id="searchForm">
    <input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1"/>
    学号：<input type="text" name="studentNo" id="studentNo" value="${studentNo}"/>
    姓名：<input type="text" name="studentName" id="studentName" value="${studentName}"/>
    班级：<input type="text" name="stuClass" id="stuClass" value="${stuClass}"/>
    <input type="submit" value="查询"/>
    <input type="button" id="cla" onclick="clear()" value="清空"/>
</form>
<table align="center" style="width: 940px; height: 20px">
    <tr>
   <td align="center">ID</td>
    <td align="center">姓名</td>
    <td align="center">班级</td>
    <td align="center">学号</td>
    <td align="center">锁定</td>
    </tr>
    <c:forEach items="${list}" var="student" varStatus="status">
    <tr>
        <td align="center">${status.index+(page.currentPage-1)*page.pageSize+1}</td>
        <td align="center">${student.studentName}</td>

        <td align="center">${student.studentClass}</td>
        <td align="center">${student.studentNo}</td>
        <td align="center">
        <c:if test="${student.lock==true}">
            锁定
        </c:if>
            <c:if test="${student.lock==false}">
                未锁定
            </c:if>
        </td>
        </tr>
    </c:forEach>

</table>
    <jsp:include page="/WEB-INF/view/common/admin_page.jsp"/>
<script>
    $("#cla").click(function () {
        $(":text").val("");
    });
</script>
</body>
</html>
