
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
<%@ include file="/base.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="${ctx}/static/inxweb/css/bootstrap.min.css" rel="stylesheet"  type="text/css">
</head>
<body>
<form action="${ctx}/admin/humanselect/studentSelect2" name="form1" id="searchForm">
    <input type="hidden" id="pageCurrentPage" name="page.currentPage"/>
    <font color="red">${msg}</font><br/>
所属学院:<select  style="width: 200px" name="schoolId" id="college">
    <option value="">==请选择==</option>

    <c:forEach items="${list1}" var="college">
    <option value="${college.id}" <c:if test="${college.id==collegeId1}">selected</c:if>>${college.collegeName}</option>
    </c:forEach>
    </select>
所属班级:<select  style="width: 200px" name="classId" id="class" value="${classId}">
    <option value="${classId}">==请选择==</option>
</select>
    请输入学号:<input type="text" name="no" id="No" value="">
<input type="button" onClick="javascript:my_submit();"  value="查询">
</form>
<br>

<table border="1" width="1000" height="50">
    <thead>
    <tr>
        <td align="center">学号</td>
        <td align="center">姓名</td>
        <td align="center">班级</td>
        <td  align="center">操作</td>
    </tr>
    <tr>
        <c:forEach items="${list}" var="hyberUser">
        <input value="${hyberUser.userId}" type="hidden" name="userId">
        <input type="hidden" name="classId" value="${hyberUser.classId}"/>

        <td align="center">${hyberUser.no}</td>
        <td align="center">${hyberUser.userName}</td>
        <td align="center">${hyberUser.classname}</td>
        <td align="center">
             <a href="${ctx}/admin/humanselect/showValidLocation?classId=${hyberUser.classId}&userId=${hyberUser.userId}">选课</a>
               <a  href="${ctx}/admin/humanselect/backCoursePage?classId=${hyberUser.classId}&userId=${hyberUser.userId}">退课</a>
            </td>
    </tr>
    </c:forEach>
    </thead>
</table>
<jsp:include page="/WEB-INF/view/common/admin_page.jsp"/>
<script type="text/javascript">
function my_submit(){

    if($("#class").val()==""&&$("#No").val()==null){
        return;
    }else if($("#class").val()==""&&$("#No").val()!=null){
        $("#class").val("0");
        document.form1.submit();
    }
    else {

        document.form1.submit();
    }
}
</script>
<script  type="text/javascript">

    function showWin(){
        /* closedData();*/
        $("#createWin").show();
    }
    $("#close1").click(function () {
        $("#createWin").hide();
    });
    $("#cancel").click(function () {
        $("#createWin").hide();
    });

</script>
<script type="text/javascript">
    //定位省份下拉框，同时添时内容改变事件
    $("#college").change( function(){
        //清空原班级下拉框中的内容，除第一项外
        $("#class option:gt(0)").remove();
        //获取选中的
        var collegeId = $("#college option:selected").val();
        //如果选中的不是""
        if(""!=collegeId){
            $.ajax( {
                type    : "POST",
                url     : "${ctx}/admin/humanselect/studentSelect1?time="+new Date().getTime(),
                data    : {"collegeId":collegeId},
                dataType:'json',
                success : function(data){
                   /* alert(data!=null?"收到":"为收到");*/
                    //alert(ajax.responseText);
                    //解析json文本
                    var size = data.length;
                    for(var i=0;i<size;i++){
                        var stuClass =data[i] ;
                        var id=stuClass.id;
                        var className=stuClass.className;
                        var $option = $("<option value='"+id+"'>"+className+"</option>");
                        $("#class").append($option);
                    }
                }
            } );
        }
    } );
</script>
</body>
</html>
