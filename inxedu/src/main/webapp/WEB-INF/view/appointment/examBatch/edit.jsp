<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${ctx}/static/common/multilevel.js"></script>

    <script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
    <script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
    <script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
    <script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>

    <style>
        .error{
            color:red;
        }
    </style>
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
</head>
<body>
预约名称:${examManager.examName}(ID:${examManager.id})

<form action="${ctx}/admin/appointment/batch/op/${examBatch.id!=null}">
    <input type="hidden" name="id" value="${examBatch.id}">
     <input type="hidden" name="appointmentCount" value="${examBatch.appointmentCount}">
    实验室：<select  name="examClassroom" value="${examBatch.examClassroom}" required>
        <option value="">--请选择--</option>
    <c:forEach items="${list}" var="examClassroom">
    <option value="${examClassroom.examClassroomName}" <c:if test="${examClassroom.examClassroomName==examBatch.examClassroom}">selected</c:if>>${examClassroom.examClassroomName}</option>
    </c:forEach>
    </select>
    <br>
    <input type="hidden" value="${examManagerId}" name="examManagerId" >
    批次：<input id="1" type="text" name="examBatchNo" value="${examBatch.examBatchNo}" required><br>
    <input name="examName" value="${examManager.examName}" type="hidden">
    预约日期：<input id="s0" type="text" name="examDate" value="<fmt:formatDate  value="${examBatch.examDate}" pattern="yyyy-MM-dd" />" required><br>
    预约时间:<input name="examBeginEndTime" type="text" value="${examBatch.examBeginEndTime}" required><br>
    科目代码:
    <select  name="courseNo" value="${examBatch.courseNo}" required>
            <option value="">--请选择--</option>
        <c:forEach items="${List}" var="examCourse">
            <option value="${examCourse.courseCode}" <c:if test="${examCourse.courseCode==examBatch.courseNo}">selected</c:if> >${examCourse.courseCode}</option>
        </c:forEach>
    </select>
    <br>
    人数：<input name="number" value="${examBatch.number}"/><br>
    状态:<input type="checkbox" value="1" name="state" id="cc" onclick="unSelect(this)" checked/> 开启<br>
    备注：<textarea name="memo">${examBatch.memo}</textarea>
  <input type="submit" value="提交">
    <input type="button" id="c" value="重置"/>
    <input type="button" id="a" value="返回"/>
</form>
<script>
    $(function() {
        $( "#s0" ).datepicker();
        $( "#e0" ).datepicker();
    });
</script>
<script type="text/javascript">
    document.getElementById("a").onclick= function(){
        window.history.back(-1);
    }
</script>
<script type="text/javascript" >
    $("#c").click(function () {
        $(":text").val("");
        $("select").val("");
    });
    function unSelect(b){
        if(b.checked==false)
            $("#cc").val("0");
        else {
            $("#cc").val("1");
        }
    }
</script>
</body>
</html>