<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加页面</title>
</head>
<body>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>

<style>
    .error{
        color:red;
    }
</style>

<form action="${ctx}/admin/appointment/examClassroom/op/${examClassroom.id!=null}/${currentPage}" id="form1" method="post">
    实验室名称:<input type="text" name="examClassroomName" value="${examClassroom.examClassroomName}" required/><br>
                         <input value="${examClassroom.id}" type="hidden" name="id">
    容量:
    <input type="text" name="number" value="${examClassroom.number}" required/><br>
    最大容量:<input type="text" name="maxNumber" value="${examClassroom.maxNumber}" required/><br>
    状态:<select  name="state" value="${examClassroom.state}" required/>
        <option value="0" <c:if test="${examClassroom.state==false}">selected</c:if>>停用</option>
        <option value="1" <c:if test="${examClassroom.state==true}">selected</c:if>>启用</option>
    </select>
    <br>
    <input type="submit" value="提交" id="ss"/>
    <input type="button" id="c" value="返回"/><br>
</form>
<script type="text/javascript">

</script>
<script type="text/javascript">
    document.getElementById("c").onclick= function(){
        window.history.back(-1);
    }
</script>
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
</body>
</html>