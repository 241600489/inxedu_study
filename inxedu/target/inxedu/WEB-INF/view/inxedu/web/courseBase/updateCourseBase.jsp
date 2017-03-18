<%--
  Created by IntelliJ IDEA.
  User: seapig02
  Date: 7/29 0029
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${ctx}/selectLession/updateCourse.action">
    <input type="hidden" name="id" value="${eduCourseBase.id}">
    课程名称:<input name="courseName" type="text" value="${eduCourseBase.courseName}" data-rule="required;"/><br>

    课程号:<input name="courseNo" type="text" value="${eduCourseBase.courseNo}" data-rule="required;"><br>

    备注:<%--<input name="memo" type="text"><br>--%><br>
    <textarea name="memo"  style="width: 600px;height: 200px;">${eduCourseBase.memo}</textarea>
    <input type="submit"  value="提交"/>
</form>
</body>
</html>
