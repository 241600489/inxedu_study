<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" href="${ctximg}/static/common/jquery-ui.css">
    <script src="${ctximg}/static/common/jquery-1.12.4.js"></script>
    <script src="${ctximg}/static/common/jquery-ui.js"></script>
    <script  type="text/javascript" language="javascript">
        $( function() {
            $( "#div" ).dialog({
                autoOpen: false,
                show: {
                    effect: "blind",
                    duration: 1000
                },
                hide: {
                    effect: "explode",
                    duration: 1000
                }
            });
            $( "#dialog" ).dialog({
                autoOpen: false,
                show: {
                    effect: "blind",
                    duration: 1000
                },
                hide: {
                    effect: "explode",
                    duration: 1000
                }
            });
        });

    </script>
    <style>
        .cur{
            cursor:pointer;
        }
        </style>
</head>
<body>
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

<form action="${ctx}/admin/appointment/batch/select/${examManager.id}" id="searchForm">
    <input name="page.currentPage" type="hidden"  id="pageCurrentPage"/>
    实验室:<input name="examClassroom" type="text" value="${examClassroom}">
    预约批次:<input name="examBatchNo" type="text" value="${examBatchNo}" >
    <input type="submit" value="查询">
    <a href="${ctx}/admin/appointment/batch/page/0/add/${examManager.id}">添加</a>
    <%--  <a href="${ctx}/admin/appointment/manager/delete" type="submit">删除</a>--%>
    <a href="${ctx}/admin/examExcel/examBatch">导入</a>
    <br>
    预约名称：${examManager.examName}
</form>
<%--</form>--%>
<form action="${ctx}/admin/appointment/batch/deleteMore/${examManager.id}">
    <input type="submit" value="删除所选项">
    <table border="1" width="1000" height="100%">
        <tr>
            <th><input class="sele" type="checkbox" onclick="selectAllItem(this)" >全选</th>
            <td align="center">ID</td>
            <td align="center">实验室</td>
            <td align="center">人数</td>
            <td align="center">预约日期</td>
            <td align="center">预约时间</td>
            <td align="center">
                <div id="dialog" title="科目代码">
                   <p>科目代码：
                       <select class="form-control" id="couCode" >
                       <option>--请选择--</option>
                       <c:forEach items="${List}" var="examCourse">
                     <option value="${examCourse.courseCode}">${examCourse.courseCode}</option>
                       </c:forEach>
                   </select><br>
                       <input name="exanBatchId" type="hidden" id="exanBatchId">
                       <input onclick="up()" type="button" value="提交">
                       <input type="button" id="c" value="重置"/>
                   </p>

               </div>
                课程代码
            </td>
            <td align="center">状态</td>
            <td align="center">操作</td>
        </tr>
        <c:forEach items="${list}" var="examBatch" varStatus="status">
            <tr>
                <td align="center"><input class="che" onclick="unSelect(this)" name="ids" type="checkbox" value="${examBatch.id}"></td>
                <td align="center">${status.index+1}</td>
                <td align="center">${examBatch.examClassroom}</td>
                <td align="center">
               <a onclick="updateCouNumber('${examBatch.id}')"> ${examBatch.number}</a>
                </td>
                <td align="center">
                    <c:if test="${not empty examBatch.examDate}">
                        <fmt:formatDate value = "${examBatch.examDate}" pattern = "yyyy-MM-dd"/>
                    </c:if>
                </td>
                <td align="center">
                ${examBatch.examBeginEndTime}
                </td>
                <td align="center">
                    ${examBatch.courseNo}
                </td>
                <td align="center" >
                    <c:if test="${examBatch.state==true}">
                        <input type="button" onclick="se('${examBatch.id}/${examBatch.state}')" class="cur" value="开启">
                    </c:if>
                    <c:if test="${examBatch.state==false}">
                        <input type="button" onclick="se('${examBatch.id}/${examBatch.state}')" class="cur" value="关闭">
                    </c:if>
                </td>
                <td align="center">
                    <a href="${ctx}/admin/examExcel/exportExamStudentAppointment?id=${examBatch.id}">导出预约结果</a>
                    <a href="${ctx}/admin/appointment/batch/page/${examBatch.id}/edit/${examManager.id}">编辑</a>
                    <a href="${ctx}/admin/appointment/batch/delete/${examManager.id}?id=${examBatch.id}">删除</a>
                    <a href="${ctx}/admin/appointment/seat/${examBatch.id}?examClassroomName=${examBatch.examClassroom}">详细布局</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</form>
<script type="text/javascript">
    function  se(id){
        $.get("${ctx}/admin/appointment/batch/changeOne/"+id,null,
                function (data) {
                    alert(data.message);
                    window.location.reload(true);
                    //window.location="${ctx}/admin/appointment/batch/select/${examManager.id}";
                    //alert(data.message);
                }
        );
    }
    function   up(id)  {
       var courseCode= $("#couCode").val();
    }
</script>
<div id="div" title="人数">
    <p>人数：
      <input name="number" id="number"><br>
        <input name="exanBatchId" type="hidden" id="exanBatchIdNumber">
        <input onclick="upnunber()" type="button" value="提交">
        <input type="button" id="s" onclick="close()" value="取消"/>
    </p>
</div>
<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />

<script type="text/javascript" >
    $("#c").click(function () {
        $(":text").val("");
    });
    function updateCouNumber(id) {
        $( "#div" ).dialog( "open" );
        $("#exanBatchIdNumber").val(id);
    }
    function updateCouCode(id) {
        $( "#dialog" ).dialog( "open" );
        $("#exanBatchIdNumber").val(id);
    }
    function close() {
        $( "#dialog" ).dialog( "close" );
    }
    function up(id) {
       var exanBatchId= $("#exanBatchId").val();
        var courseNo=$("#couCode").val();
        $.get("${ctx}/admin/appointment/batch/updateCourseCode",{id:exanBatchId,courseNo:courseNo},
        function (data) {
            $("#couCode").val("");
            $( "#dialog" ).dialog( "close" );
            alert(data.message);
            window.location.reload(true);
            window.location.href = "?pageSize=" + $pageSize + "&pageIndex=" + $pageIndex;
        }
        );
    }
    function upnunber() {
        var exanBatchId= $("#exanBatchIdNumber").val();
        var number=$("#number").val();
        $.get("${ctx}/admin/appointment/batch/updateNumber",{id:exanBatchId,number:number},
                function (data) {
                    $("#couCode").val("");
                    $( "#dialog" ).dialog( "close" );
                    alert(data.message);
                    window.location.reload(true);
                }
        );
    }
</script>

</body>
</html>
