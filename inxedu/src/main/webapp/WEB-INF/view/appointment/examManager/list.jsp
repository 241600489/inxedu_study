<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: karak
  Date: 16-8-5
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<script>
    $.validator.setDefaults({
        submitHandler: function() {
            alert("提交事件!");
        }
    });
    $().ready(function() {
        $("#commentForm").validate();
    });
</script>

<style>
    .error{
        color:red;
    }
    .cur{
        cursor:pointer;
    }
</style>
<script type="application/javascript">
    function selectAllItem(obj){
        if(obj.checked){
            $("input[name='ids']").prop('checked',true);
        }else {
            $("input[name='ids']").prop('checked',false);
        }
    }
    function unSelect(b){
        if(b.checked==false)
            $(".sele").attr("checked",false);
    }
</script>
<form  action="${ctx}/admin/appointment/manager/search" id="searchForm">
    <input name="page.currentPage" type="hidden"  id="pageCurrentPage"/>
   预约名称:<input name="examName" type="text" value="${examName}">(支持模糊查询)
    <input type="radio" value="1" name="start" checked>开启
    <input type="radio" value="0" name="start">关闭
    <input type="submit" value="查询">
    <a href="${ctx}/admin/appointment/manager/addPage" type="submit">添加</a>
  <%--  <a href="${ctx}/admin/appointment/manager/delete" type="submit">删除</a>--%>

</form>
<form action="${ctx}/admin/appointment/manager/deleteMore">
    <input type="submit" value="删除所选项">
    <table border="1" width="1000" height="100%">
        <tr>
            <th><input class="sele" type="checkbox" onclick="selectAllItem(this)" >全选</th>
            <td align="center">ID</td>
            <td align="center">预约名称</td>
            <td align="center">预约起止时间</td>
            <td align="center">科目限制</td>
            <td align="center">状态</td>
            <td align="center">操作</td>
        </tr>
        <c:forEach items="${list}" var="examManager" varStatus="status">
            <tr>
                <td align="center"><input class="che" onclick="unSelect(this)" name="ids" type="checkbox" value="${examManager.id}"></td>
                <td align="center">${status.index+1}</td>
                <td align="center">${examManager.examName}</td>
                <td align="center">
                    <c:if test="${not empty examManager.appointmentBeginTime}">
                        <fmt:formatDate value= "${examManager.appointmentBeginTime}" pattern="yyyy-MM-dd HH:mm:ss" />
                    </c:if>
                    -
                    <c:if test="${not empty examManager.appointmentEndTime}">
                        <fmt:formatDate value= "${examManager.appointmentEndTime}" pattern="yyyy-MM-dd HH:mm:ss" />
                    </c:if>
                </td>
                <td align="center">
                        <c:if test="${examManager.isCourseController==true}">
                            <a onclick="startContoller('${examManager.id}/${examManager.isCourseController}')" class="cur">开启</a>
                        </c:if>
                        <c:if test="${examManager.isCourseController==false}">
                            <a onclick="startContoller('${examManager.id}/${examManager.isCourseController}')" class="cur">关闭</a>
                        </c:if>
                </td>
                <td align="center">
                    <c:if test="${examManager.state==true}">
                        <a onclick="se('${examManager.id}/${examManager.state}')" class="cur">开启</a>
                    </c:if>
                    <c:if test="${examManager.state==false}">
                        <a onclick="se('${examManager.id}/${examManager.state}')" class="cur">关闭</a>
                    </c:if>
                </td>
                <td align="center">
                    <a href="${ctx}/admin/examExcel/exportExamStudentAppointmentByExamName?examName=${examManager.examName}">导出预约结果</a>
                    <a href="${ctx}/admin/appointment/manager/updatePage?id=${examManager.id}">编辑</a>
                    <a href="${ctx}/admin/appointment/manager/delete?id=${examManager.id}">删除</a>
                    <a href="${ctx}/admin/appointment/batch?id=${examManager.id}">预约批次</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</form>
<script type="text/javascript">
    function  se(id){
        $.get("${ctx}/admin/appointment/manager/changeOne/"+id,null,
                function (data) {
                    alert(data.message);
                    window.location.reload();
                }
        );
    }
    function startContoller(id) {
        $.get("${ctx}/admin/appointment/manager/changeTWO/"+id,null,
                function (data) {
                    alert(data.message);
                    window.location.reload();
                }
        );
    }
</script>

<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
</body>
</html>
