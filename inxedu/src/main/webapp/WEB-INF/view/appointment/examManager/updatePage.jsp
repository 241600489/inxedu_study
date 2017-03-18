<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
</head>
<body>

<form action="${ctx}/admin/appointment/manager/update" id="form1">
    <input name="page.currentPage" type="hidden" id="pageCurrentPage"/>
    <input name="id" value="${examManager.id}" type="hidden">
    预约名称：<input name="examName" type="text" value="${examManager.examName}" style="width: 200px" required><br>
    考试科目限制：<input type="radio" value="1" name="isCourseController"
     <c:if test="${examManager.isCourseController==true}">
      checked
     </c:if>>开启
    <input type="radio" value="0" name="isCourseController"
    <c:if test="${examManager.isCourseController==false}">
           checked
    </c:if>
    >关闭<br>
    预约状态：<input type="radio" value="1" name="state" <c:if test="${examManager.state==true}"> checked </c:if>>开启
    <input type="radio" value="0" name="state"
           <c:if test="${examManager.state==false}">
           checked
    </c:if> >关闭<br>
    预约开始时间：<input id="s0" type="text" name="appointmentBeginTime" value="<fmt:formatDate  value="${examManager.appointmentBeginTime}" pattern="yyyy-MM-dd"/>" required><br>
    预约结束时间：<input id="e0" type="text" name="appointmentEndTime" value="<fmt:formatDate  value="${examManager.appointmentEndTime}"  pattern="yyyy-MM-dd"/>" required><br>
    <input type="submit" value="提交">
    <input type="button" id="c" value="重置"/>
    <input type="button" id="a" value="返回"/><br>
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