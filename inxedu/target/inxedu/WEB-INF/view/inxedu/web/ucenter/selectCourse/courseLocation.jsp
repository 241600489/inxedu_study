<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>选课中心</title>
    <link  href="${ctx}/static/inxweb/css/bootstrap.min.css" rel="stylesheet" >

</head>
<body>
<article class="col-7 fl">
    <div class="u-r-cont">
        <section>
            <div>
                <section class="c-infor-tabTitle c-tab-title">
                    <a href="${ctx }/uc/showValidCourseLocation" title="选课中心" class="current">选课中心</a>
                    <a href="${ctx }/uc/myCourse" title="Wo的课程" style="cursor: default;">我的课程</a>
                </section>
            </div>
                <div class="i-slide">

                        <h6>选课步骤:2、请根据自己的空余时间，选择课位。鼠标在课表上移动，点击可选课位。</h6>
                        <div class="bs-example" data-example-id="bordered-table">
                            <table class="table table-bordered" >
                                <thead>
                                <tr class="active">
                                    <th>&nbsp;&nbsp;</th>
                                    <th align="center">星期一</th>
                                    <th align="center">星期二</th>
                                    <th align="center">星期三</th>
                                    <th align="center">星期四</th>
                                    <th align="center">星期五</th>
                                    <th align="center">星期六</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${table}" var="x" varStatus="status">
                                    <tr>
                                        <c:if test="${status.index==0}">
                                            <th class="active" scope="row">第一大节</th>
                                        </c:if>
                                        <c:if test="${status.index==1}">
                                            <th class="active" scope="row">第二大节</th>
                                        </c:if>
                                        <c:if test="${status.index==2}">
                                            <th class="active" scope="row">第三大节</th>
                                        </c:if>
                                        <c:if test="${status.index==3}">
                                            <th class="active" scope="row">第四大节</th>
                                        </c:if>
                                        <c:if test="${status.index==4}">
                                            <th class="active" scope="row">第五大节</th>
                                        </c:if>
                                        <c:forEach items="${x}" var="y" varStatus="sta">
                                            <c:if test="${y==false}">
                                                <td class="td2">
                                                    <a class="color" href="#"></a>
                                                </td>
                                            </c:if>
                                            <c:if test="${y!=false}">
                                                <td class="td1">
                                                    <a class="color" href="/uc/showValidCourseInfo/${(sta.index+1)*10+(status.index+1)}">可选课位</a>
                                                </td>
                                            </c:if>
                                        </c:forEach>
                                    </tr>
                                </c:forEach>

                                </tbody>
                            </table>
                        </div>
                    </div>
            </section>
        </div>
    </article>
</body>
</html>