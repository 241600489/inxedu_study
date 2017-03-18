<%--
  Created by IntelliJ IDEA.
  User: karak
  Date: 16-8-10
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<!--
<td>${result.id}</td>
<td>${result.studentNo}</td>
<td>${result.studentName}</td>
<td>${result.studentClass}</td>
<td>${result.examDate}</td>
<td>${result.examBeginEndTimes}</td>
<td>${result.examClassroom}</td>
<td>${result.seatNo}</td>
<td>${result.batchNo}</td>
<td>${result.state}</td>
<td>${result.examName}</td>
<td>${result.score}</td>
<td>${result.updateDate}</td>
<td>${result.memo}</td>

-->

    <c:if test="${state=='success'}">
        <label >
       <font size="3px"> ${result.studentName}(${result.studentNo})同学你好!<br>
        你已经成功预约  请于<fmt:formatDate value = "${result.examDate}" pattern = "yyyy-MM-dd"/> 日&nbsp;${result.examBeginEndTimes}&nbsp;到教室：${result.examClassroom}&nbsp;座位号：${result.seatNo}&nbsp;&nbsp;考试</font>
</label>
</c:if>
    <c:if test="${state=='fail'}">
        预选失败
    </c:if>
    <c:if test="${state=='reply'}">
        重选
    </c:if>
    <c:if test="${state=='full'}">
        已满
        </c:if>

    <c:if test="${state=='illegal'}">
        非法访问
    </c:if>
    <c:if test="${state=='illegalTime'}">
        不在预选时间
    </c:if>
    <c:if test="${state=='lock'}">
        你已被锁定,请联系管理员
    </c:if>
    <c:if test="${state=='no_seat'}">
        该批次座位数与人数不符合
    </c:if>
</body>
</html>
