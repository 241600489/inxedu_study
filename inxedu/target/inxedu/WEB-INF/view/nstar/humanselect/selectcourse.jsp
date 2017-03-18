<%--
  Created by IntelliJ IDEA.
  User: 3
  Date: 2016/8/3
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/base.jsp"%>
<html>
<head>
    <title></title>
    <link  href="${ctx}/static/inxweb/css/bootstrap.min.css" rel="stylesheet" >
</head>
<body>

<input type="hidden" name="user.userId" value="${user.userId}">

${user.userName}的可选课程:
<div class="i-slide">
    <h6>请根据自己的情况选择课程。</h6>
    <div class="bs-example" data-example-id="bordered-table">

        <table class="table table-bordered" >
            <thead>
            <tr class="active">
                <th>&nbsp;&nbsp;</th>
                <th align="center">星期一</th>
                <th align="center">星期二</th>
                <th align="center">星期三</th>
                <th align="center">星期四</th>
                <th align="center">星期五</th>
                <th align="center">星期六</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${table}" var="x" varStatus="status">
                <tr>
                    <c:if test="${status.index==0}">
                        <th class="active" scope="row">第一大节</th>
                    </c:if>
                    <c:if test="${status.index==1}">
                        <th class="active" scope="row">第二大节</th>
                    </c:if>
                    <c:if test="${status.index==2}">
                        <th class="active" scope="row">第三大节</th>
                    </c:if>
                    <c:if test="${status.index==3}">
                        <th class="active" scope="row">第四大节</th>
                    </c:if>
                    <c:if test="${status.index==4}">
                        <th class="active" scope="row">第五大节</th>
                    </c:if>
                    <c:forEach items="${x}" var="y" varStatus="sta">
                        <c:if test="${y==false}">
                            <td class="td2">
                                <a class="color" href="#"></a>
                            </td>
                        </c:if>
                        <c:if test="${y!=false}">
                            <td class="td1">
                             <a class="color" href="${ctx}/admin/humanselect/showValidCourseInfo/${user.userId}/${(sta.index+1)*10+(status.index+1)}">可选课位</a>
                            </td>
                        </c:if>
                    </c:forEach>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
</div>


</body>
</html>
