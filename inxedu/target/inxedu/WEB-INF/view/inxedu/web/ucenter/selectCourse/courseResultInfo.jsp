<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>选课页</title>
    <link href="${ctx}/static/inxweb/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctximg}/static/common/jquery-ui.css">
    <script src="${ctximg}/static/common/jquery-1.12.4.js"></script>
    <script src="${ctximg}/static/common/jquery-ui.js"></script>
</head>
<body>
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
<article class="col-7 fl">
    <div class="u-r-cont">
        <section>
            <div>
                <section class="c-infor-tabTitle c-tab-title">
                    <a href="${ctx }/uc/showValidCourseLocation" title="选课中心" class="cursor: default;">选课中心</a>
                    <a href="${ctx }/uc/myCourse" title="Wo的课程" class="current">我的课程</a>
                </section>
                <p class="p">请选择上课教室完成选课。说明：灰色表示教室选课人数已满，不可再选。</p>
                <font color="red" size="5px">${msg}</font>
                <div class="u-course-list">
                    <article class="comm-course-list">
                        <ul class="clearfix">
                            <c:forEach items="${list}" var="x" varStatus="status">
                                <li>
                                    <div class="cc-l-wrap">
                                        <section class="course-img">
                                            <img
                                                    src="${x.logo}"
                                                    class="img-responsive"
                                                    alt="${x.courseName}"><br>任课教师：${x.teacherName}

                                        </section>
                                        <h3 class="hLh30 txtOf mt10">
                                            <a href="/uc/play/10" title="${x.courseName}"
                                               class="course-title fsize18 c-333">${x.courseName}
                                                <c:if test="${x.type==false}">
                                                    (实验课)
                                                </c:if>
                                            </a>
                                        </h3>
                                        <section class="mt10 of">
                                                <span class="fr jgTag bg-green">
                                                    <c:if test="${x.type==true}">
                                                        <tt class="c-fff fsize12 f-fA"><a
                                                                onclick="back('${x.courseId}')">退课</a></tt>
                                                    </c:if>
                                                    <tt class="c-fff fsize12 f-fA"> <a
                                                            href="/front/couinfo/${x.courseId}">详细</a></tt>
                                                </span>
                                            <span class="fl jgAttr c-ccc f-fA">
                                                    <c:if test="${x.type==true}">
                                                        <tt class="c-999 f-fA">${x.maxNumber-x.residaulCount}人已选修</tt> |
                                                    </c:if>
                                                 <tt class="c-999 f-fA">容量${x.maxNumber}人</tt>
                                                </span>
                                        </section>
                                    </div>
                                </li>
                                <c:if test="${status.index%3!=0}">
                                    <br/>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </article>
                </div>

            </div>
        </section>
    </div>
    <div id="dialog" title="退课">
        <p>身份证号码：
            <input type="hidden" id="courseId">
            <input type="text" id="idCard">
        </p>
        <p align="center"><input onclick="up()" type="button" value="提交"></p>
    </div>
</article>

<script type="text/javascript">
    function back(id) {
        $("#dialog").dialog("open");
        $("#courseId").val(id);
    }
    function up() {
        var idCard = $("#idCard").val();
        var courseId = $("#courseId").val();
        if (idCard == "") {
            idCard = "0";
        }
        $.get("${ctx}/uc/backCourse/" + courseId + "/" + idCard, null, function (data) {
            alert(data.message);
            window.location.href = "${ctx}/uc/myCourse";
        });
        /*
         $.get("/admin/appointment/batch/updateCourseCode",{id:exanBatchId,courseNo:courseNo},
         function (data) {
         $("#couCode").val("");
         $( "#dialog" ).dialog( "close" );
         alert(data.message);
         window.location.reload();
         }
         );
         */
    }
</script>
</body>
</html>