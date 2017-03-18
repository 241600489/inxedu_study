
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form  action="${ctx}/admin/appointment/examAppointmentLog/search" id="searchForm">
    <input name="page.currentPage" type="hidden"  id="pageCurrentPage"/>
    操作对象：<input width="600" name="object" type="text" border="5" cellpadding="8" value="${object}">
    (支持模糊查询)
    <input type="submit" value="查询">
    <a href="${ctx}/admin/appointment/examAppointmentLog/op/add">添加</a>
    <input type="button" value="删除 ">
</form>
<input type="button" onclick="verlidate()" value="校验数据库">
<form action="${ctx}/admin/appointment/examAppointmentLog/op/del">
    <input type="submit" value="删除所选项">
    <table width="700" border="2" cellpadding="25" cellspacing="5" bordercolor="black"  >
        <tr>
            <th><input class="sele" type="checkbox" onclick="selectAllItem(this)" >全选</th>
            <td align="center">ID</td>
            <td align="center">事件描述</td>
            <td align="center">操作对象</td>
            <td align="center">发生时间</td>
            <td align="center">操作人</td>
            <td align="center">操作</td>
        </tr>
        <c:forEach items="${list}" var="examAppointmentLog" varStatus="status">
            <tr>
                <td align="center"><input class="che" onclick="unSelect(this)" name="ids" type="checkbox" value="${examAppointmentLog.id}"></td>
                <td align="center">${status.index+1}</td>
                <td align="center">${examAppointmentLog.events}</td>
                <td align="center">${examAppointmentLog.object}</td>
                <td align="center">${examAppointmentLog.eventDate}</td>
                <td align="center">${examAppointmentLog.eventMan}</td>
                <td align="center">
                <a href="${ctx}/admin/appointment/examAppointmentLog/op/delete/${examAppointmentLog.id}">删除</a>
            </td>
            </tr>
        </c:forEach>
    </table>
</form>
<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
<script type="application/javascript">
    function selectAllItem(obj){
        if(obj.checked){
            $("input[name='ids']").prop('checked',true);
        }else {
            $("input[name='ids']").prop('checked',false);
        }}
    function unSelect(b){
        if(b.checked==false)
            $(".sele").attr("checked",false);
    }
</script>
<script>
    function verlidate() {
        $.get("${ctx}/admin/appointment/examAppointmentLog/verlidate",null,function (data) {
            var size=data.length;
            for(var i=0;i<size;i++)
            {
                alert(data[i]);
            }

        });
    }

</script>
</body>
</html>
