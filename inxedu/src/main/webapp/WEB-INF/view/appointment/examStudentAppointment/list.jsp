<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
<head>
    <title>学生预约管理</title>

</head>

<body>
<html>
<head>
    <title>Title</title>
    <style>
        .na{
            margin-right: 50px;
        }
    </style>
</head>
<body>
<form  action="${ctx}/admin/appointment/studentAppointment/search">

    学号:<input name="studentNo" type="text" value="${examStudentAppointment.studentNo}" id="noId" class="na" style="width:100px">
    姓名:<input type="text" name="studentName" value="${examStudentAppointment.studentName}" id="nameId" style="width:100px">
    考试名称:<select  name="examName" id="examId">
        <option value="">===请选择===</option>
        <c:forEach  items="${list}" var="studentAppointment">
            <option value="${studentAppointment.examName}">${studentAppointment.examName}</option>
        </c:forEach>
       </select>
     <input name="state" type="checkbox" value="0" id="nobook">未预约
    <input name="state" type="checkbox" value="1" id="book">已预约
    <input type="submit" value="查询">
    <a href="${ctx}/admin/appointment/studentAppointment/page/add">添加</a>
</form>
<form action="${ctx}/admin/appointment/studentAppointment/op/del">
    <input type="submit" value="删除所选项">
    <table width="700" border="1" cellpadding="2" cellspacing="0" bordercolor="black" border="5">
        <tr>
            <th><input class="sele" type="checkbox" onclick="selectAllItem(this)" >全选</th>
            <td align="center">ID</td>
            <td align="center">学号</td>
            <td align="center">姓名</td>
            <td align="center">班级</td>
            <td align="center">实验室</td>
            <td align="center">预约日期</td>
            <td align="center">预约时间</td>
            <td align="center">批次</td>
            <td align="center">座位号</td>
            <td align="center">预约状态</td>
            <td align="center">操作</td>
        </tr>
        <c:forEach items="${list}" var="studentAppointment" varStatus="status">
            <tr>
                <td align="center"><input class="che" onclick="unSelect(this)" name="ids" type="checkbox" value="${studentAppointment.id}"></td>
                <td align="center">${status.index+1}</td>
                <td align="center">${studentAppointment.studentNo}</td>
                <td align="center">${studentAppointment.studentName}</td>
                <td align="center">${studentAppointment.studentClass}</td>
                <td align="center">${studentAppointment.examClassroom}</td>
                <td align="center">
                    <c:if test="${not empty studentAppointment.examDate}">
                     <fmt:formatDate value= "${studentAppointment.examDate}" pattern="yyyy-MM-dd" />
                    </c:if>
                </td>
                <td align="center">
                    ${studentAppointment.examBeginEndTimes}
                </td>
                <td align="center">${studentAppointment.batchNo}</td>
                <td align="center">${studentAppointment.seatNo}</td>
                <td align="center">
                    <c:if test="${studentAppointment.state==true}">
                        <input id="start" value="预约" type="button">
                    </c:if>
                    <c:if test="${studentAppointment.state==false}"><a onclick="select('${studentAppointment.state}/${studentAppointment.id}')">
                        <input type="button" value="未预约" id="sttop"></a>
                    </c:if>
                </td>
                <td align="center">
                    <c:if test="${studentAppointment.state==true}">
                        <a href="${ctx}/admin/appointment/studentAppointment/op/delete/${studentAppointment.id}">取消</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</form>
<form action="${ctx}/admin/appointment/studentAppointment" id="searchForm">
    <input name="currentPage" type="hidden"  id="pageCurrentPage"/>
    <jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
</form>
<script type="application/javascript">
    function selectAllItem(obj){

        if(obj.checked){
            $("input[name='ids']").prop('checked',true);
        }else {
            $("input[name='ids']").prop('checked',false);
        }
    }
    function unSelect(b){
        if(b.checked==false)
            $(".sele").attr("checked",false);
    }
</script>
<script type="text/javascript">
    function  select(id) {
        $.get()
    }
</script>
<script type="text/javascript">
    $("#examId").change(function(){

        var  examName= $("#examId option:selected").val();
        var  studentNo=$("#noId").val();
        var  studentName=$("#nameId").val();

        //如果选中的不是""
        if(""!=examName){
            $.ajax( {
                type    : "POST",
                url     : "${ctx}/admin/appointment/studentAppointment/changge?time="+new Date().getTime(),
                data    : {examName:examName,studentNo:studentNo,studentName:studentName},
                dataType:'json',
                success : function(data){
                    if("true"==data.message){
                        $("#book").attr("checked",true);
                    }
                    else {
                        $("#nobook").attr("checked",true);
                    }
                }
            } );
        }
    } );
</script>
</body>

</html>

