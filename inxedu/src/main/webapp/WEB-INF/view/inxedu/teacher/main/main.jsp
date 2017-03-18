<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Meta -->
    <meta charset="utf-8" http-equiv="Content-Type"/>
    <!-- End of Meta -->

    <!-- Page title -->
    <title>${websitemap.web.company}-${websitemap.web.title}</title>
    <!-- End of Page title -->
    <meta name="author" content="${websitemap.web.author}"/>
    <meta name="keywords" content="${websitemap.web.keywords}"/>
    <meta name="description" content="${websitemap.web.description}"/>
    <link rel="shortcut icon" href="${ctx}/static/admin/assets/second.png" type="image/x-icon">


    <script type="text/javascript" src="${ctx}/static/common/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/admin/js/jquery-ui-1.7.2.custom.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/admin/js/jquery.wysiwyg.js"></script>
    <script type="text/javascript" src="${ctx}/static/admin/js/hoverIntent.js"></script>
    <script type="text/javascript" src="${ctx}/static/admin/js/superfish.js"></script>
    <script type="text/javascript" src="${ctx}/static/admin/js/custom.js"></script>
    <script type="text/javascript" src="${ctx}/static/admin/js/easyTooltip.js"></script>
    <!-- End of Libraries -->
    <script type="text/javascript">
        /*sMenu*/
        $(function () {
            $("#ui-sMenu>div").each(function () {
                var _this = $(this),
                        _oT = _this.find("h3"),
                        _oTi = _oT.find(".ui-icon"),
                        _oC = _oT.next("div");
                _oT.click(function () {
                    if (_oC.is(":hidden")) {
                        $("#ui-sMenu>div h3").removeClass("ui-state-active ui-corner-top").addClass("ui-state-default ui-corner-all");
                        $("#ui-sMenu>div .ui-icon").removeClass("ui-icon-triangle-1-s").addClass("ui-icon-triangle-1-e");
                        $("#ui-sMenu>div div").slideUp("fast");

                        _oT.removeClass("ui-state-default ui-corner-all").addClass("ui-state-active ui-corner-top");
                        _oTi.removeClass("ui-icon-triangle-1-e").addClass("ui-icon-triangle-1-s");
                        _oC.slideDown("fast");
                    } else {
                        _oT.removeClass("ui-state-active ui-corner-top").addClass("ui-state-default ui-corner-all");
                        _oTi.removeClass("ui-icon-triangle-1-s").addClass("ui-icon-triangle-1-e");
                        _oC.slideUp("fast");
                    }
                });
            });
        });
    </script>
</head>
<body>
<!-- Container -->
<div class="tHeader headerimg">
    <div id="container">
        <!-- Header -->
        <div id="header">

            <!-- Top -->
            <div id="top">
                <div class="logo">
                    <a href="" target="_parent" title=" " class="tooltip">
                        <img src="${ctx}/static/admin/assets/logo-tt.png" height="60" alt=" "/>
                    </a>
                </div>

                <!-- End of Logo -->

                <!-- Meta information -->
                <div class="meta">
                    <p>欢迎来到${websitemap.web.company}教师管理系统!</p>
                    <ul>
                        <li>
                            <a href="${ctx}/teacher/outlogin" title="退出系统" class="tooltip">
                                <span class="ui-icon ui-icon-power"></span>
                                退出系统
                            </a>
                        </li>
                        <li>
                            <a href="" title="${teacher.name}" class="tooltip">
                                <span class="ui-icon ui-icon-person"></span>${teacher.name}</a>
                        </li>
                    </ul>
                </div>
                <!-- End of Meta information -->
            </div>

            <!-- End of Top-->

            <div id="container">

                <!-- Background wrapper -->
                <div id="bgwrap">
                    <!-- Main Content -->
                    <div id="content">
                        <div id="main">
                            <iframe name="content" scrolling="auto" frameborder="0" src="${ctx}/teacher/main/index"
                                    height="750" width="100%"></iframe>
                        </div>
                    </div>
                    <div id="sidebar">

                        <h2>菜单目录 / MENU</h2>
                        <!-- Accordion -->
                        <div id="ui-sMenu" class="ui-accordion ui-widget ui-helper-reset">
                            <div>
                                <h3 class="ui-accordion-header ui-helper-reset ui-state-default ui-corner-all">
                                    <span class="ui-icon ui-icon-triangle-1-e"></span>
                                    <a href="javascript:void(0)" title="我的学生" class="tooltip">我的学生</a>
                                </h3>
                                <div class="ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom"
                                     style="display: block;">
                                    <dl class="acd-sub-dl">
                                        <dt>
                                            <a href="${ctx}/teacher/mystudent" target="content"
                                               title="学生">学生</a>
                                        </dt>
                                        <%--  <dd>
                                              <a href="javascript:void(0)" title="${ccuf.functionName}">${ccuf.functionName}</a>
                                          </dd>
                                          <dd>
                                              <a href="${ctx}${ccuf.functionUrl}" target="content" title="${ccuf.functionName}">${ccuf.functionName}</a>
                                          </dd>--%>
                                    </dl>
                                </div>
                            </div>
                            <div>
                                <h3 class="ui-accordion-header ui-helper-reset ui-state-default ui-corner-all">
                                    <span class="ui-icon ui-icon-triangle-1-e"></span>
                                    <a href="javascript:void(0)" title="预约结果" class="tooltip">预约结果</a>
                                </h3>
                                <div class="ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom"
                                     style="display: block;">
                                    <dl class="acd-sub-dl">
                                        <c:forEach items="${list}" var="examManager">
                                        <dt>
                                            <a href="${ctx}/teacher/appointresult?examName=${examManager.examName}" target="content"
                                               title="学生预约结果">${examManager.examName}</a>
                                        </dt>
                                        </c:forEach>
                                        <%--  <dd>
                                              <a href="javascript:void(0)" title="${ccuf.functionName}">${ccuf.functionName}</a>
                                          </dd>
                                          <dd>
                                              <a href="${ctx}${ccuf.functionUrl}" target="content" title="${ccuf.functionName}">${ccuf.functionName}</a>
                                          </dd>--%>
                                    </dl>
                                </div>
                            </div>
                            <div>
                                <h3 class="ui-accordion-header ui-helper-reset ui-state-default ui-corner-all">
                                    <span class="ui-icon ui-icon-triangle-1-e"></span>
                                    <a href="javascript:void(0)" title="未预约结果" class="tooltip">未预约结果</a>
                                </h3>
                                <div class="ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom"
                                     style="display: block;">
                                    <dl class="acd-sub-dl">
                                        <c:forEach items="${list}" var="examManager">
                                        <dt>
                                            <a href="${ctx}/teacher/noappointresult?examName=${examManager.examName}" target="content"
                                               title="学生预约结果">${examManager.examName}</a>
                                        </dt>
                                        </c:forEach>
                                        <%--  <dd>
                                              <a href="javascript:void(0)" title="${ccuf.functionName}">${ccuf.functionName}</a>
                                          </dd>
                                          <dd>
                                              <a href="${ctx}${ccuf.functionUrl}" target="content" title="${ccuf.functionName}">${ccuf.functionName}</a>
                                          </dd>--%>
                                    </dl>
                                </div>
                            </div>

                        </div>
                    </div>
                    <!-- End of Sidebar -->

                </div>
                <!-- End of bgwrap -->
            </div>
            <!-- End of Header -->
        </div>
    </div>
</div>
<!-- End of Container -->

<!-- Footer -->

</body>
</html>


