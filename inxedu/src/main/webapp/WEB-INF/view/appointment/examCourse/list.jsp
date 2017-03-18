
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>考试课程</title>
</head>
<body>
<form  action="${ctx}/admin/appointment/examCourse/search" id="searchForm">
    <input name="page.currentPage" type="hidden"  id="pageCurrentPage"/>
    课程代码：<input width="600" name="courseCode" type="text" border="5" cellpadding="8" >
    (支持模糊查询)
    <input type="submit" value="查询">
    <a href="${ctx}/admin/appointment/examCourse/page/0/add">添加</a>
    <input type="button" value="删除 ">
    <a href="/admin/examExcel/examCourse">导入</a>
</form>
<form action="${ctx}/admin/appointment/examCourse/op/del">
    <input type="submit" value="删除所选项">
    <table width="700" border="2" cellpadding="25" cellspacing="5" bordercolor="black"  >
        <tr>
            <th><input class="sele" type="checkbox" onclick="selectAllItem(this)" >全选</th>
            <td align="center">ID</td>
            <td align="center">课程代码</td>
            <td align="center">课程名称</td>
            <td align="center">备注</td>
            <td align="center">操作</td>
        </tr>
        <c:forEach items="${list}" var="examCourse" varStatus="status">
            <tr>
                <td align="center"><input class="che" onclick="unSelect(this)" name="ids" type="checkbox" value="${examCourse.id}"></td>
                <td align="center">${status.index+1}</td>
                <td align="center">${examCourse.courseCode}</td>
                <td align="center">${examCourse.courseName}</td>
                <td align="center">${examCourse.memo}</td>
                <td align="center">
                    <a href="${ctx}/admin/appointment/examCourse/op/delete/${examCourse.id}">删除</a>
                    <a href="${ctx}/admin/appointment/examCourse/page/${examCourse.id}/edit">编辑</a>
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
</body>
</html>

