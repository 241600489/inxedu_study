<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/base.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        .cur {
            cursor: pointer;
        }
    </style>
    <title>预约中心</title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <link rel="stylesheet" href="${ctximg}/static/common/jquery-ui.css">
    <%--<script src="${ctximg}/static/common/jquery-1.11.1.min.js"></script>--%>
<%--    <script src="${ctximg}/static/common/jquery-ui.js"></script>--%>
    <script>
        $(function () {
            $("#datepicker").datepicker({
                showOn: "button",
                buttonImage: "/images/upload/image/date1.png",
                buttonImageOnly: true,
                changeMonth: true,
                changeYear: true,
                dateFormat: 'yy-mm-dd'
            });
        });
        $(function () {
            var progressTimer,
                    progressbar = $("#progressbar"),
                    progressLabel = $(".progress-label"),
                    dialogButtons = [{
                        text: "关闭",
                        click: closeDownload
                    }],
                    dialog = $("#dialog").dialog({
                        autoOpen: false,
                        closeOnEscape: false,
                        resizable: false,
                        buttons: dialogButtons,
                        open: function () {
                            progressTimer = setTimeout(progress, 2000);
                        },
                        beforeClose: function () {
                            downloadButton.button("option", {
                                disabled: false,
                                label: "预约"
                            });
                        }
                    }),
                    downloadButton = $("#downloadButton")
                            .button()
                            .on("click", function () {
                                $(this).button("option", {
                                    disabled: true,
                                    label: "..."
                                });
                                dialog.dialog("open");
                            });

            progressbar.progressbar({
                value: false,
                change: function () {
                    progressLabel.text("系统正在处理中，请稍后...");
                },
                complete: function () {
                    progressLabel.text("预约完成!");
                    dialog.dialog("option", "buttons", [{
                        text: "关闭",
                        click: closeDownload
                    }]);
                    $(".ui-dialog button").last().trigger("focus");
                }
            });
            function progress() {
                var val = progressbar.progressbar("value") || 0;

                progressbar.progressbar("value", val + Math.floor(Math.random() * 3));

                if (val <= 99) {
                    progressTimer = setTimeout(progress, 50);
                }
            }

            function closeDownload() {
                clearTimeout(progressTimer);
                dialog.dialog("option", "buttons", dialogButtons).dialog("close");
                progressbar.progressbar("value", false);
                progressLabel.text("系统正在处理中，请稍后...");
                downloadButton.trigger("focus");
            }
        });

    </script>


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
    </style>
</head>
<body>
<article class="col-7 fl">
    <div class="u-r-cont">
        <section>
            <div >
                <section class="c-infor-tabTitle c-tab-title">
                    <a href="#" title="预约" style="cursor: default;">预约</a>
                    <a href="${ctx}/uc/appointment/result" title="Wo的预约" style="cursor: default;">我的预约</a>
                    <a href="#" title="预约中心" class="current">预约中心</a>
                </section>
            </div>
            <div class="con" style="width: 1000px;height:1000px;overflow:auto">
                <h5>说明：</h5>
 <div class="pos1">
                    <form action="${ctx}/uc/appointment/exam" id="searchForm">
                        <input type="hidden" name="page.currentPage" id="pageCurrentPage" value="1">
                        <span><big>预约名称</big></span>
                        <select id="examNameTag" class="form-control" name="examnameId">
                            <option value="">--请选择--</option>
                            <c:forEach var="x" items="${options}">
                                <option value="${x.id}"
                                        <c:if test="${x.id==examNameId}">selected</c:if>>${x.examName}</option>
                            </c:forEach>
                        </select>
                        <span class="span-a">说明：显示当前开启的预约</span><br>
                        <span><big>预约日期</big></span>
                        <div class="table-ss1">
                            <select id="DateTag" class="form-control1" name="date">
                                <option value="">--请选择--</option>
                                <c:forEach var="x" items="${date}">
                                    <option value="<fmt:formatDate value = "${x}" pattern="yyyy-MM-dd"/>"
                                            <c:if test="${x==dateback}">selected</c:if>>
                                        <fmt:formatDate value="${x}" pattern="yyyy-MM-dd"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="table-ss">
                            <span class="s-s"><big>预约时间</big></span>
                        </div>
                        <div class="table-ss1">
                            <select id="timeTag" class="form-control1" name="time">
                                <option value="">--请选择--</option>
                                <c:forEach var="x" items="${time}">
                                    <option value="${x}" <c:if test="${x==timeback}">selected</c:if>>${x}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <label class="label1">
                            <input type="checkbox" onclick="sel(this)" value="${isFull}" id="isfull" name="isfull" <c:if test="${isFull}">checked</c:if>/> 未满
                        </label>
                        <button class="btn" type="submit">查询</button>
                    </form>
                    <script>

                        //定位省份下拉框，同时添时内容改变事件
                        $("#examNameTag").change(function () {
                            //清空原班级下拉框中的内容，除第一项外
                            $("#DateTag option:gt(0)").remove();
                            //获取选中的省份
                            var examNameId = $("#examNameTag option:selected").val();
                            $("#searchForm").action = "${ctx}/uc/appointment/" + examNameId;

                            //如果选中的不是""
                            if ("" != examNameId) {
                                $.ajax({
                                    type: "GET",
                                    url: "${ctx}/uc/appointment/examNameSelectDate/" + examNameId + "?time=" + new Date().getTime(),
                                    data: null,
                                    dataType: 'json',
                                    success: function (data) {
                                        var result = data.entity;
                                        var size = result.length;
                                        /*alert(size);*/
                                        for (var i = 0; i < size; i++) {
                                            var examName = format(result[i], "yyyy-MM-dd");
                                            var $option = $("<option value='" + examName + "'>" + examName + "</option>");
                                            $("#DateTag").append($option);
                                        }
                                    }
                                });
                            }
                        });
                        $("#DateTag").change(function () {
                            //清空原班级下拉框中的内容，除第一项外
                            $("#timeTag option:gt(0)").remove();
                            //获取选中的省份
                            var examNameId = $("#examNameTag option:selected").val();
                            var examDate = $("#DateTag option:selected").val();
                            $("#searchForm").action = "${ctx}/uc/appointment/" + examNameId;
                            if ("" != examDate) {
                                $.ajax({
                                    type: "GET",
                                    url: "${ctx}/uc/appointment/examNameSelect/" + examNameId + "/" + examDate + "/" + "?time=" + new Date().getTime(),
                                    data: null,
                                    dataType: 'json',
                                    success: function (data) {
                                        var result = data.entity;
                                        var size = result.length;
                                        /*alert(size);*/
                                        for (var i = 0; i < size; i++) {
                                            var examName = result[i];
                                            var $option = $("<option value='" + examName + "'>" + examName + "</option>");
                                            $("#timeTag").append($option);
                                        }
                                    }
                                });
                            }
                        });
                        var format = function (time, format) {
                            var t = new Date(time);
                            var tf = function (i) {
                                return (i < 10 ? '0' : '') + i
                            };
                            return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function (a) {
                                switch (a) {
                                    case 'yyyy':
                                        return tf(t.getFullYear());
                                        break;
                                    case 'MM':
                                        return tf(t.getMonth() + 1);
                                        break;
                                    case 'mm':
                                        return tf(t.getMinutes());
                                        break;
                                    case 'dd':
                                        return tf(t.getDate());
                                        break;
                                }
                            })
                        }
                        function sel(obj) {
                            if(obj.checked){
                                /* alert("ss");*/
                                $("#isfull").val("true");
                            }
                            else{
                                /*alert("ss");*/
                                $("#isfull").val("false");
                            }
                        }
                    </script>
                    <%--<form class="form-control2">--%>
                    <table id="customers" >
                        <tr>
                            <th>ID</th>
                            <th>实验室</th>
                            <th>预约</th>
                            <th width="100px">实验室容量</th>
                            <th>日期</th>
                            <th>时间</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                            <c:forEach items="${list}" var="x" varStatus="status">
                        <tr>
                            <td align="center">${status.index+(page.currentPage-1)*page.pageSize+1}</td>
                            <td align="center">${x.examClassroom}</td>
                            <td align="center">${x.appointmentCount}</td>
                            <td align="center" width="100px">${x.number}</td>
                            <td align="center">
                                <fmt:formatDate value="${x.examDate}" pattern = "yyyy-MM-dd"/>
                            </td>
                            <td align="center">${x.examBeginEndTime}</td>
                            <td align="center"><c:if test="${x.number>x.appointmentCount}">未满</c:if>
                                <c:if test="${x.number<=x.appointmentCount}">已满</c:if>
                            </td>
                            <td align="center">
                                <c:if test="${isAppointment==false&&x.number>x.appointmentCount}">
                                    <a onclick="appointment('${examNameId}/${x.id}')" class="cur">预约</a>
                                </c:if>
                            </td>
                        </tr>
                        </c:forEach>
                    </table>
     <jsp:include page="/WEB-INF/view/common/front_page.jsp"></jsp:include>
                </div>
        </section>
    </div>
</article>

<script>
    $("#searchForm").submit(function () {
        $(":submit", this).attr("disabled", "disabled");
    });
    function appointment(id) {
        if(!confirm("你确定要预约吗?")){
            return false;
        }
        window.location.href="${ctx}/uc/appointment/appointment/"+id;
    }
</script>
</body>
</html>
