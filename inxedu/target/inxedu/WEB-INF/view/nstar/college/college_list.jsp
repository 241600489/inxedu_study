<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2016/7/28
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>

<script type="text/javascript" src="http://common.cnblogs.com/script/jquery.js"></script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
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
<a href="${ctx}/admin/college/addPage">添加学院</a><br><br>
<form action="${ctx}/admin/college/deleteMore">
    <table  align="center" style="width: 940px; height: 20px">
        <caption style="margin-bottom: 20px; font-size: 24px"><b>学院设置</b></caption>
        <thead>
        <tr>
            <th><input class="sele" type="checkbox" onclick="selectAllItem(this)" >全选</th>
            <th>学院名称</th>
            <th>学号代码</th>
            <th>学院简介</th>
            <th>操作</th>
        </tr>
        </thead>
        <c:forEach items="${list}" var="college">
            <tbody>
            <tr>
                <td align="center"><input class="che" onclick="unSelect(this)" name="ids" type="checkbox" value="${college.id}"></td>
                <td align="center">${college.collegeName}</td>
                <td align="center">${college.collegeCode}</td>
                <td align="center">${college.description}</td>
                <td align="center">
                    <a href="${ctx}/admin/college/updatePage?id=${college.id}&currentPage=${page.currentPage}">修改</a>
                    <a href="javascript:void(0)" onclick="deleteCollege(${college.id},this)" class="button tooltip">删除</a>

                </td>
            </tr>
            </tbody>
        </c:forEach>
        <tr>
            <td align="center" colspan="4"><input id="deleteMore" type="submit" value="删除所选项"></td>
        </tr>
    </table>
</form>

<!-- 页面添加分页 -->
<form action="${ctx}/admin/college/list" id="searchForm">
    <input type="hidden" id="pageCurrentPage" name="currentPage">
    <jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
</form>
<script type="text/javascript">
    function deleteCollege(collegeId,em) {
     if(!confirm("你确定删除吗?")){
         return;
    }
    $.ajax({
        url:baselocation +'/admin/college/delete/'+collegeId,
        type:'post',
        dataType:'json',
        success:function(result){

            if(result.success==false){
                alert(result.message);
            }else{
                alert(result.message);
                location.reload();
            }
        },
        error:function(error){
            alert("系统繁忙，请稍后再操作！");
        }
    });}
</script>
</body>
</html>
