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
    <title>更新基础课程</title>

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
</head>
<body>
<form action="${ctx}/admin/courseBase/updateCourse">
    <input type="hidden" name="id" value="${eduCourseBase.id}">
    课程名称:<input name="courseName" type="text" value="${eduCourseBase.courseName}" required/><br>

    课程号:<input name="courseNo" type="text" value="${eduCourseBase.courseNo}" required><br>
        <input type="hidden" name="currentPage" value="${currentPage}">
    备注:<%--<input name="memo" type="text"><br>--%><br>
    <textarea name="memo"  style="width: 600px;height: 200px;">${eduCourseBase.memo}</textarea>
    <input type="submit"  value="提交"/>
    <td colspan="2" align="center"><input id="c" type="button" value="返回"></td>
    <script type="text/javascript">
        document.getElementById("c").onclick= function(){
            window.history.back(-1);
        }
    </script>
</form>
</body>
</html>
