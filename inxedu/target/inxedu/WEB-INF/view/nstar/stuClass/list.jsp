<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2016/7/27
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
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

  <table width="800px">
    <a href="/admin/stuClass/addClassPage" >添加</a>
    <form action="/admin/stuClass/search" id="searchForm">
      <tr>
        <td align="center" align="center" colspan="2">
          班级名称<input name="className" type="text" value="${stuClass.className}">
          <input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1" />
        </td>
        <td align="center" colspan="2">
          所属学院<select  style="width: 300px" name="schoolId" value="${stuClass.schoolId}">
          <option value="">==请选择==</option>
          <c:forEach items="${list1}" var="college">
          <option value="${college.id}"  <c:if test="${college.id==stuClass.schoolId}">selected</c:if>>${college.collegeName}</option>
          </c:forEach>
        </td>
        <td align="center"><input type="submit" value="搜索"></td>
      </tr>
    </form>


    <form action="/admin/stuClass/deleteMore">

      <tr>
        <th><input class="sele" type="checkbox" onclick="selectAllItem(this)" >全选</th>
        <th>班级名称</th>
        <th>详情</th>
        <th>所属学院名称</th>
        <th>操作</th>
      </tr>

      <c:forEach items="${list}" var="stuClass">
        <tr>
          <td align="center"><input class="che" onclick="unSelect(this)" name="ids" type="checkbox" value="${stuClass.id}"></td>
          <td align="center">${stuClass.className}</td>
          <td align="center">${stuClass.description}</td>
          <td align="center">${stuClass.collename}</td>
          <td align="center"><a href="/admin/stuClass/updatePage?id=${stuClass.id}&currentPage=${page.currentPage}">修改</a>
            <a href="/admin/stuClass/delete?id=${stuClass.id}">删除</a></td>
        </tr>
      </c:forEach>

      <tr>
        <td align="center" colspan="5"><input type="submit" value="删除所选项"></td>
      </tr>
    </form>


  </table>
  <jsp:include page="/WEB-INF/view/common/admin_page.jsp"/>

</body>
</html>
