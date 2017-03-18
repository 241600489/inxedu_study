<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加页面</title>
</head>
<body>
<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>

<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
//提示必须的字段
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
<form action="${ctx}/admin/appointment/examAppointmentLog/add">
    操作事件:<input type="text" name="events"  required/><br>

    操作对象:
    <input type="text" name="object"  required/><br>
    发生时间:   <input id="s0" type="text" name="eventDate" value="${beginCourseSignupTime0}"><br>
    操作人:<input type="text"  name="eventMan" required/><br>
    <input type="submit" value="提交"/>
    <input type="button" id="c" value="返回"/><br>
</form>
</form>
<script type="text/javascript">
    document.getElementById("c").onclick= function(){
        window.history.back(-1);
    }
</script>
<script>
    $(function() {
        $( "#s0" ).datepicker();
    });
</script>
</body>
</html>