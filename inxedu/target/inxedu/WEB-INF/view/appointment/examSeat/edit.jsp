<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>添加页面</title>
</head>
<body>

<script src="${ctx}/static/common/jquery-1.7.2.min.js"></script>
<script src="${ctx}/static/common/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/static/common/jquery-ui.css" />

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
    .ss{
        display: none;
    }
</style>


<form action="${ctx}/admin/appointment/seat/op/add" id="updateForm">

    <input type="hidden" value="${examClassroomName}" name="examSeat.examClassroomName">
    <input type="hidden" value="${examBatchId}" name="examSeat.examBatchId">
    所属实验室名称:<font color="blue">${examClassroomName}<br></font>
    座位号: 从<input type="text" id="seatNo" name="examSeat.seatNo"  required/>到<input id="seatNo1" type="text" name="seatNo1" required/>
    <input type="checkbox"  name="examSeat.state" value="1" checked/>启用    默认启用状态<br>
    <input type="button"  onclick="InsertSeat()" value="提交"/>
    <input type="button" id="c" value="返回"/>
</form>
<div id="div" class="ss">
    <div id="divv"></div>
<input id="btn" type="button" value="确定"/>
</div>
<script type="text/javascript">
    document.getElementById("c").onclick= function(){
        window.history.back(-1);
    }
    $("#btn").on("click",function () {
        $( "#div" ).dialog( "close" );
        $("#seatNo").val("");
        $("#seatNo1").val("");
        $("#divv").html("");
    });
    function  InsertSeat() {
        var seatNo=$("#seatNo").val();
        var seatNo1=$("#seatNo1").val();
        if(seatNo==''){
            alert("请输入起始座位号");
            return;
        }
        if(seatNo1==''){
            alert("请输入结束座位号");
            return;
        }
        var params='';
        $("#updateForm input").each(function(){
            params+=$(this).serialize()+"&";
        });

        $.ajax({
            url:baselocation+'/admin/appointment/seat/op/add',
            type:'post',
            dataType:'json',
            data:params,
            success:function(result){
                if (result.success==true){
                    alert(result.message);
                    location.href="/admin/appointment/seat/" + result.entity.examBatchId + "?examClassroomName=" + result.entity.examClassroomName;
                }else {
                    $("#divv").html(result.message);
                    $("#div").dialog();
                }
            },
            error:function (result) {
            return false;
            }
        });
    }
</script>
</body>
</html>