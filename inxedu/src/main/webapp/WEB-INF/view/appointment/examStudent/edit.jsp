<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加页面</title>
</head>
<body>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>

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
</style>
<form action="${ctx}/admin/appointment/student/op/${examStudent.id!=null}">
    学号:<input type="text" name="studentNo" value="${examStudent.studentNo}" required/><br>
    <input value="${examStudent.id}" type="hidden" name="id">
    姓名:
    <input type="text" name="studentName" value="${examStudent.studentName}" required/><br>
    班级名:<input type="text" name="studentClass" value="${examStudent.studentClass}" required/><br>
    课程名称:<select name="courseCode">
        <option>==请选择==</option>
        <c:forEach items="${list}" var="examCourse">
            <option value="${examCourse.courseCode}" <c:if test="${examStudent.courseCode==examCourse.courseCode}">selected</c:if>>${examCourse.courseName}</option>
        </c:forEach>
    </select>
  <%-- 课程代码:<input type="text"  name="courseCode" value="${examStudent.courseCode}"required/><br>
    <input type="text"  name="courseName" value="${examStudent.courseName}"required/><br>--%>
    锁定:<select name="lock" required>
    <option value="0" <c:if test="${examStudent.lock==false}">selected</c:if>>锁定</option>
    <option value="1" <c:if test="${examStudent.lock==true}">selected</c:if>>未锁定</option>
    </select>
    <br>
    备注:<input type="text"  name="memo" value="${examStudent.memo}"/><br>
    <input type="submit" value="提交"/>
    <input type="button" id="c" value="返回"/><br>
</form>
</form>
<script type="text/javascript">
    document.getElementById("c").onclick= function(){
        window.history.back(-1);
    }
</script>
</body>
</html>