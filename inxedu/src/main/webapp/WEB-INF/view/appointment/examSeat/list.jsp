
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title> new document </title>

</head>

<body>
<html>
<head>
    <title>Title</title>
    <style>
        .na{
            margin-right: 100px;

        }
    </style>
</head>
<body>
<form  action="${ctx}/admin/appointment/seat/search/${examClassroomName}/${examBatchId}">

    座位号:<input name="seatNo" type="text" class="na">
    <input type="submit" value="查询">
    <a href="${ctx}/admin/appointment/seat/page/add/${examClassroomName}/${examBatchId}">添加</a>
    <input type="button" value="删除 ">
    <input type="button" id="c" value="返回"/>
</form>
<form action="${ctx}/admin/appointment/seat/op/del/${examClassroomName}/${examBatchId}">
    <input type="submit" value="删除所选项">
    <table width="700" border="1" cellpadding="2" cellspacing="0" bordercolor="black" border="5">
        <tr>
            <th><input class="sele" type="checkbox" onclick="selectAllItem(this)" >全选</th>
            <td align="center">ID</td>
            <td align="center">座位号</td>
            <td align="center">所属实验室</td>
            <td align="center">状态</td>
            <td align="center">操作</td>
        </tr>
        <c:forEach items="${list}" var="examSeat" varStatus="status">
            <tr>
                <td align="center"><input class="che" onclick="unSelect(this)" name="ids" type="checkbox" value="${examSeat.id}"></td>
                <td align="center">${status.index+1}</td>
                <td align="center">${examSeat.seatNo}</td>
                <td align="center">${examSeat.examClassroomName}</td>
                <td align="center">
                <c:if test="${examSeat.state==true}">
                    <a onclick="select('${examSeat.state}/${examSeat.id}')">
                    <input id="start" value="启用" type="button">
                        </a>
                </c:if>
                    <c:if test="${examSeat.state==false}"><a onclick="select('${examSeat.state}/${examSeat.id}')">
                        <input type="button" value="停用" id="sttop"></a>
                    </c:if>
                </td>
                <td align="center">
                    <a href="${ctx}/admin/appointment/seat/op/delete/${examSeat.id}/${examClassroomName}/${examBatchId}">删除</a>
                    <a href="${ctx}/admin/appointment/seat/layout/${examClassroomName}">平面图</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</form>
<form action="${ctx}/admin/appointment/seat/${examBatchId}" id="searchForm">
   <input name="examClassroomName" value="${examClassroomName}" hidden/>
    <input name="currentPage" type="hidden"  id="pageCurrentPage"/>
    <jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
</form>
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
<script type="text/javascript">
    function  select(id) {
        $.get("${ctx}/admin/appointment/seat/changeOne/"+id,null,
        function (data) {
            alert(data.message);
            window.location.reload();
        }
        );
    }
</script>
<script type="text/javascript">
    document.getElementById("c").onclick= function(){
        window.history.back(-1);
    }
</script>
</body>
</html>
