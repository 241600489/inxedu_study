<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${ctx}/teacher/searchmyappstudent" id="searchForm">
    <input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1"/>
    <input type="hidden" name="examName" value="${examName}"/>
    学号：<input type="text" name="studentNo" id="studentNo" value="${studentNo}"/>
    姓名：<input type="text" name="studentName" id="studentName" value="${studentName}"/>
    班级：<input type="text" name="stuClass" id="stuClass" value="${stuClass}"/>
    <input type="submit" value="查询"/>
    <input type="button" id="cla" onclick="clear()" value="清空"/>
</form>
<table align="center" style="width: 940px; height: 20px">
  <tr>
      <td align="center">ID</td>
      <td align="center">学号</td>
      <td align="center">姓名</td>
      <td align="center">班级</td>
      <td align="center">批次号</td>
      <td align="center">座位号</td>
      <td align="center">预约日期</td>
      <td align="center">预约时间</td>
  </tr>
    <c:forEach items="${list}" var="result" varStatus="status">
        <tr>
            <td align="center">${status.index+(page.currentPage-1)*page.pageSize+1}</td>
            <td align="center">${result.studentNo}</td>
            <td align="center">${result.studentName}</td>
            <td align="center">${result.studentClass}</td>
            <td align="center">${result.batchNo}</td>
            <td align="center">${result.seatNo}</td>
            <td align="center"><fmt:formatDate value ="${result.examDate}" pattern = "yyyy-MM-dd"/> </td>
            <td align="center">${result.examBeginEndTimes} </td>
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
