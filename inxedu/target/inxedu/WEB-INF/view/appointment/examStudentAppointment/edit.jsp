<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>添加页面</title>
    <script type="text/javascript" src="${ctx}/static/common/multilevel.js"></script>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>

    <script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
    <script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
    <script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
    <script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
</head>
<body>

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
<form action="${ctx}/admin/appointment/studentAppointment/op/add">
    学号:<input type="text" name="studentNo" value="" required/><br>
    姓名:  <input type="text" name="studentName" value="" required/><br>
    班级名:<input type="text" name="studentClass" value="" required/><br>


    预约日期:<input id="s0" type="text" value="" name="examDate" ><br>
    实验室名称:<input type="text"  name="examClassroom" value=""  required/><br>
    批次号:<input type="text"  name="batchNo" value="" required/><br>
    预约名称:<input type="text"  name="examName" value="" required/><br>
    座位号:<input name="seatNo" type="text" value="" required/>
    预约状态:<select name="state" required>
       <option value="0">未预约</option>
        <option value="1">预约</option>
    </select>
    <input type="hidden" name="updateDate">
    <input type="hidden" name="score">
    备注:<input type="text" value=""  name="memo"/><br>
              <input type="submit" value="提交"/>
         <input type="button" id="c" value="返回"/><br>
</form>
<script type="text/javascript">
    document.getElementById("c").onclick= function(){
        window.history.back(-1);
    }
</script>
<script>
    $(function() {
        $( "#s0" ).datepicker({
            regional:"zh-CN",
            changeMonth: true,
            dateFormat:"yy-mm-dd",
            timeFormat: "HH:mm:ss"
        });
    });
</script>
</body>
</html>