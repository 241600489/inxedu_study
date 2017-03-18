
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form  action="${ctx}/admin/appointment/student/search" id="searchForm">
    <input name="page.currentPage" type="hidden"  id="pageCurrentPage"/>
    学号:<input name="studentNo" type="text" value="${examStudent.studentNo}">
    姓名:<input name="studentName" type="text" value="${examStudent.studentName}">
    班级:<input name="studentClass" type="text" value="${examStudent.studentClass}">
    (支持模糊查询)
    <input type="submit" value="查询">
    <a href="${ctx}/admin/appointment/student/page/0/add">添加</a>
    <input type="button" value="删除 ">
    <a href="${ctx}/admin/examExcel/examStudent">导入</a>
</form>
<form action="${ctx}/admin/appointment/student/op/del">
    <input type="submit" value="删除所选项">
    <table border="1" width="1000" height="100%">
        <tr>
            <th><input class="sele" type="checkbox" onclick="selectAllItem(this)" >全选</th>
            <td align="center">ID</td>
            <td align="center">学号</td>
            <td align="center">姓名</td>
            <td align="center">班级</td>
            <td align="center">课程代码</td>
            <td align="center">课程名称</td>
            <td align="center">锁定</td>
            <td align="center">操作</td>
        </tr>
        <c:forEach items="${list}" var="examStudent" varStatus="status">
            <tr>
                <td align="center"><input class="che" onclick="unSelect(this)" name="ids" type="checkbox" value="${examStudent.id}"></td>
                <td align="center">${status.index+1}</td>
                <td align="center">${examStudent.studentNo}</td>
                <td align="center">${examStudent.studentName}</td>
                <td align="center">${examStudent.studentClass}</td>
                <td align="center">${examStudent.courseCode}</td>
                <td align="center">${examStudent.courseName}</td>
                <td align="center">

                    <c:if test="${examStudent.lock==false}">
                        <input value="锁定" onclick="selec('${examStudent.lock}/${examStudent.id}')" type="button" >
                    </c:if>
                    <c:if test="${examStudent.lock==true}">
                        <input value="未锁定" onclick="selec('${examStudent.lock}/${examStudent.id}')" type="button" >
                    </c:if>
                </td>
                <td align="center">
                    <a href="${ctx}/admin/appointment/student/page/${examStudent.id}/edit">编辑</a>
                    <a href="${ctx}/admin/appointment/student/op/delete/${examStudent.id}">删除</a>
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
        }
    }
    function unSelect(b){
        if(b.checked==false)
            $(".sele").attr("checked",false);
    }
</script>
<script type="text/javascript">
    function  selec(id) {
        $.get("${ctx}/admin/appointment/student/changeOne/"+id,null,
                function (data) {
                    alert(data.message);
                    window.location.reload();
                }
        );
    }
</script>
</body>
</html>
