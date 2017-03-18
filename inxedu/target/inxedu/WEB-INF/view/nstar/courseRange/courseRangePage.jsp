<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: seapig02
  Date: 7/28 0028
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${ctx}/static/common/jquery-1.11.1.min.js"></script>
</head>
<body>
<form action="${ctx}/admin/courseRange/selectableClass.action" method="post">
    请选择班级名称;<input name="className" type="text" style="position: relative;left: 5px;"/>
    <input type="submit" id="buttonId" value="查询">
    </form>



  <form action="${ctx}/admin/courseRange/addCourseTableDo" method="post" style="position: relative;left: 5px">
      请选择课程名称:<select id="courseName" style="width: 300px" name="eduCourseBase.id">
      <c:forEach items="${courseNameList}" var="Name">
          <option value=${Name.id}>${Name.courseName}</option>
      </c:forEach>
  </select>
      </br>
      <table  border="2" width="50%" border="1" style="position: relative;left: 5px">
          <tr>
              <td align="center">id</td>
              <td align="center">班级</td>
              <td align="center">选修课程</td>
              <td align="center">操作</td>
          </tr>
          <c:forEach items="${list}" var="stuClassSelectabel">
              <input type="hidden" name="id" value="${stuClassSelectabel.id}">
              <input type="hidden" name="className" value="${stuClassSelectabel.className}">
              <input type="hidden" name="seletableCourseId" value="${stuClassSelectabel.seletableCourseId}">
              <input type="hidden" name="schoolId" value="${stuClassSelectabel.schoolId}">
              <input type="hidden" name="description" value="${stuClassSelectabel.description}">
                <tr>
                <td align="center">${stuClassSelectabel.id}</td>
                <td align="center">${stuClassSelectabel.className}</td>
                 <td align="center">${stuClassSelectabel.coursName}</td>
                    <td align="center">
               <a href="${ctx}/admin/courseRange/deleteClass.action?id=${stuClassSelectabel.id}&seletableCourseId=${stuClassSelectabel.coursName}" >删除</a>
                    </td>
                </tr>
              </c:forEach>
      </table>
      <input id="saveSelct" type="submit" value="保存选择"/>
  </form>

</body>
</html>
