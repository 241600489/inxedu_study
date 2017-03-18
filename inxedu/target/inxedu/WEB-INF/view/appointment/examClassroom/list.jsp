<%--
  Created by IntelliJ IDEA.
  User: karak
  Date: 16-8-5
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .cur{
         cursor:pointer;
        }
        </style>
</head>
<body>
<form  action="${ctx}/admin/appointment/examClassroom/search" id="searchForm">
    <input name="page.currentPage" type="hidden"  id="pageCurrentPage"/>
  实验室名称:<input name="examClassroomName" type="text" value="${examClassroomName}" >(支持模糊查询)
  <input type="checkbox" onclick="sel(this)" id="start" value="1" name="start" checked>启用
  <input type="checkbox" onclick="sell(this)" id="stop" value="0" name="start">停用
  <input type="submit" value="查询">
  <a href="${ctx}/admin/appointment/examClassroom/page/0/add/${page.currentPage}">添加</a>
  <input type="button" value="删除 ">
</form>
  <form action="${ctx}/admin/appointment/examClassroom/op/del">
      <input type="submit" value="删除所选项">
    <table  align="center" style="width: 940px; height: 20px">
        <tr>
            <th><input class="sele" type="checkbox" onclick="selectAllItem(this)" >全选</th>
            <td align="center">ID</td>
            <td align="center">实验室名称</td>
            <td align="center">容量</td>
            <td align="center">最大容量</td>
            <td align="center">状态</td>
            <td align="center">操作</td>
        </tr>
        <c:forEach items="${list}" var="examClassroom" varStatus="status">
            <tr>
                <td align="center"><input class="che" onclick="unSelect(this)" name="ids" type="checkbox" value="${examClassroom.id}"></td>
                <td align="center">${status.index+1}</td>
                <td align="center">${examClassroom.examClassroomName}</td>
                <td align="center">${examClassroom.number}</td>
                <td align="center">${examClassroom.maxNumber}</td>
                <td align="center">
                <c:if test="${examClassroom.state==true}">
                       <input type="button" onclick="selec('${examClassroom.state}/${examClassroom.id}')"  class="cur" value="启用">
                </c:if>
                    <c:if test="${examClassroom.state==false}">
                        <input type="button" onclick="selec('${examClassroom.state}/${examClassroom.id}')" class="cur" value="停用">
                    </c:if>
                </td>
                <td align="center">
                    <a href="${ctx}/admin/appointment/examClassroom/page/${examClassroom.id}/edit/${page.currentPage}">编辑</a>
                    <a href="${ctx}/admin/appointment/examClassroom/op/delete/${examClassroom.id}">删除</a>

                </td>
            </tr>
        </c:forEach>
    </table>
</form>
  <jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
<script type="text/javascript">
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
    function sel(obj) {
        if(obj.checked){
           /* alert("ss");*/
        $("#stop").prop('checked',false);
        }
        else{
            /*alert("ss");*/
            $("#stop").prop('checked',true);
        }
    }
    function sell(obj) {
        if(obj.checked){
            /* alert("ss");*/
            $("#start").prop('checked',false);
        }
        else{
            /*alert("ss");*/
            $("#start").prop('checked',true);
        }
    }


</script>
<script type="text/javascript">
    function  selec(id) {
        $.get("${ctx}/admin/appointment/examClassroom/changeOne/"+id,null,
                function (data) {
                    alert(data.message);
                    window.location.reload();
                }
        );
    }
</script>
</body>
</html>
