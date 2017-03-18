<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>预约中心</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" href="${ctximg}/static/common/jquery-ui.css">
    <script src="${ctximg}/static/common/jquery-1.12.4.js"></script>
    <script src="${ctximg}/static/common/jquery-ui.js"></script>
    <style>
        #progressbar {
            margin-top: 20px;
        }
        .progress-label {
            font-weight: bold;
            text-shadow: 1px 1px 0 #fff;
        }
        .ui-dialog-titlebar-close {
            display: none;
        }
        #downloadButton{cursor:pointer}
    </style>
    <script>
        $(function () {
            $("#dialog").dialog({
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
</head>
<body>
<article class="col-7 fl">
    <div class="u-r-cont">
        <section>
            <div>
                <section class="c-infor-tabTitle c-tab-title">
                    <a href="#" title="预约" style="cursor: default;">预约</a>
                    <a href="javascript: void(0)" title="我的预约"  class="current" >我的预约</a>
                    <a href="${ctx}/uc/appointment" title="预约中心" style="cursor: default;">预约中心</a>
                </section>
            </div>
            <div class="con">
                <h5>说明：</h5>

                <div class="pos1">
                    <table id="customers">
                        <tr class="table-style">
                            <th>ID</th>
                            <th>姓名</th>
                            <th>实验室</th>
                            <th>日期</th>
                            <th>时间</th>
                            <th>座位号</th>
                            <th>预约名称</th>
                            <th>备注</th>
                            <th>操作</th>
                        </tr>
                        <c:forEach items="${list}" var="x">
                           <%-- <tr>
                                <th colspan="7" align="center">${x.memo}</th>
                            </tr>--%>
                            <tr class="table-style">
                                <td class="h-s" align="center">${x.id}</td>

                                <td align="center">${x.studentName}</td>


                                <td align="center">${x.examClassroom}</td>
                                <td align="center">
                                    <c:if test="${x.examName!='Office办公与计算机网络实训及应用'}">  <fmt:formatDate value = "${x.examDate}" pattern = "yyyy-MM-dd"/></c:if>
                                </td>
                                <td align="center">
                                <c:if test="${x.examName!='Office办公与计算机网络实训及应用'}"> ${x.examBeginEndTimes}</c:if>

                                </td>
                                <td align="center"> <c:if test="${x.examName!='Office办公与计算机网络实训及应用'}"> ${x.seatNo}</c:if></td>
                                <td align="center">${x.examName}</td>
                                <td align="center">${x.memo}</td>

                                <td align="center">
                                    <a id="downloadButton" onclick="back(${x.id})">
                                        退约
                                    </a>
                                </td><br>

                            </tr>

                        </c:forEach>
                    </table>
                    <script>
                        function back(id) {
                            if(confirm("你确定退约吗?")){
                                $("#dialog").dialog("open");
                                $("#courseId").val(id);
                            }
                        }

                        function closeDialog() {
                            $("#idCard").val("");
                            $("#dialog").dialog("close");

                        }
                        function up() {
                            var idCard = $("#idCard").val();
                            var examStudentAppointmentId = $("#courseId").val();
                            if (idCard == "") {
                                idCard = "0";
                            }
                            $.get("${ctx}/uc/appointment/backAppointment/" + examStudentAppointmentId+"/"+idCard, null, function (data) {
                                        if (data.message == "success") {
                                            alert("退约成功");
                                            window.location = "${ctx}/uc/appointment/result";
                                        }
                                        else if (data.message == "lock") {
                                            closeDialog();
                                            alert("你已被锁定,请与管理员联系");

                                        }else if (data.message=="请输入正确的身份证号"){
                                            alert(data.message);
                                        }
                                        else {
                                            alert("退约失败");
                                            closeDialog();
                                        }
                                    }
                            );
                        }
                    </script>
                </div>
                <div id="dialog" title="退约" class="form-control" id="couCode">
                    <p>身份证号码：
                        <input type="hidden" id="courseId" style="width:250px">
                        <input type="text" id="idCard">
                    </p>
                    <p align="center"><input onclick="up()" type="button" value="提交">
                        <input onclick="closeDialog()" type="button" value="取消"></p>

                </div>
            </div>
        </section>
    </div>
</article>
<script>
    function searchMemo(id) {
        alert("年后");
        $.get("${ctx}/uc/appointment/searchMemo/"+id,null,function (data) {
                    alert(data.message);
                }
        );
    }
</script>
</body>
</html>
