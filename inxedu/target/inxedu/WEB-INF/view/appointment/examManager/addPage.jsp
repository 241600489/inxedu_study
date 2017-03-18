<%--
  Created by IntelliJ IDEA.
  User: karak
  Date: 16-8-5
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Title</title>

    <script type="text/javascript" src="${ctx}/static/common/multilevel.js"></script>

    <script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
    <script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
    <script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
    <script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
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
<form action="${ctx}/admin/appointment/manager/add" id="form1">
    <input name="page.currentPage" type="hidden" id="pageCurrentPage"/>
    考试名称：<input name="examName" type="text"  style="width: 200px" required><br>
    考试科目限制：<input type="radio" value="1" name="isCourseController" checked>开启
    <input type="radio" value="0" name="isCourseController">关闭<br>
    预约状态：<input type="radio" value="1" name="state" checked>开启
    <input type="radio" value="0" name="state">关闭<br>
    预约开始时间：<input id="s0" type="text" name="appointmentBeginTime" value="${beginCourseSignupTime0}" required><br>
    预约结束时间：<input id="e0" type="text" name="appointmentEndTime" value="${endCourseSignupTime0}" required><br>
    <input type="submit" value="提交">
    <input type="button" id="c" value="重置"/>
    <input type="button" id="a" value="返回"/><br><br>
</form>
<script>

        $( "#s0,#e0" ).datetimepicker({//添加日期选择功能
            regional:"zh-CN",
            changeMonth: true,
            dateFormat:"yy-mm-dd",
            timeFormat: "HH:mm:ss"
    });
</script>
<script type="text/javascript" >
    $("#c").click(function () {
        $(":text").val("");
    });
</script>
<script type="text/javascript">
    document.getElementById("a").onclick= function(){
        window.history.back(-1);
    }
</script>
</body>
</html>