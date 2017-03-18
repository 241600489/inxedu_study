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
<form action="${ctx}/admin/appointment/examCourse/op/${examCourse.id!=null}">
    课程代码:<input type="text" name="courseCode" value="${examCourse.courseCode}" required/><br>
    <input type="hidden" name="id" value="${examCourse.id}"/>
    课程名称：
    <input type="text" name="courseName" value="${examCourse.courseName}"  required/><br>
    备注：
    <input type="text" name="memo" value="${examCourse.memo}"  /><br>
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