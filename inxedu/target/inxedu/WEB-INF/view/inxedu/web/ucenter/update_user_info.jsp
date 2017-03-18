<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=8">
    <meta http-equiv="Expires" content="0">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-control" content="no-cache">
    <meta http-equiv="Cache" content="no-cache">
    <title>我的资料</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/common/jcrop/jquery.Jcrop.min.css" />
    <script type="text/javascript" src="${ctx}/static/common/jcrop/jquery.Jcrop.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/inxweb/user/user.js"></script>
    <script type="text/javascript" src="${ctx}/kindeditor/kindeditor-all.js"></script>
    <script type="text/javascript">
        $(function(){
            showTab('${index}');//选项控制显示
            /*uploadImg('fileupload','uploadfile');*/
            var commitBtn=$("#commitBtn");
            commitBtn.show();
            var nextBtn=$("#nextBtn");
            nextBtn.hide();
        });
    </script>
    <style type="text/css">
        .ke-upload-area {min-width: 54px;width: auto;}
        .ke-upload-area .ke-button-common,.ke-upload-area .ke-button-common input {border: 1px solid #FF5F16;background: #FFF7F3;}
        .ke-upload-area .ke-button-common input {border: none;width: 100px;color: #FF5F16;font-weight: bold;}
        .ke-upload-area .ke-button-common input:hover {background: #FFF2EC;}
    </style>
</head>
<body>
<article class="col-7 fl">
    <div class="u-r-cont">
        <section class="c-infor-tabTitle c-tab-title">
            <a href="${ctx }/uc/showValidCourseLocation" title="选课中心" class="current">选课中心</a>
            <a href="${ctx }/uc/myCourse" title="Wo的课程" style="cursor: default;">我的课程</a>
        </section>
             选课步骤:1、完善资料，手机号码必填，邮箱号可填
                <div class="u-account-box">
                    <form method="post" id="updateForm">
                        <input type="hidden" name="user.userId" value="${user.userId}" />
                        <input type="hidden" name="user.classId" value="${user.classId}"/>

                        <ol class="u-account-li">
                            <li>
                                <label class="u-a-title1">
                                    <span class="fsize16 c-999">姓&nbsp;&nbsp; 名</span>
                                </label>
                                <label class="u-a-title1">
                                <span class="fsize12 c-999">${user.userName}</span>
                                </label>
                                <!-- <span class="u-a-error"><em class="u-a-cw icon16">&nbsp;</em>请输入正确的账 号</span> -->
                            </li>
                            <li>
                                <label class="u-a-title1">
                                    <span class="fsize16 c-999">班&nbsp;&nbsp; 级</span>
                                </label>
                                <label  class="u-a-title1">
                                 <span class="fsize12 c-999">${className}</span>
                                </label>
                            </li>

                            <li>
                                <label class="u-a-title1">
                                    <span class="fsize16 c-999">学&nbsp;&nbsp; 号</span>
                                </label>
                                <label  class="u-a-title1">
                                <span class="fsize12 c-999">${user.no}</span>
                                </label>
                                <!-- <span class="u-a-error"><em class="u-a-zq icon16">&nbsp;</em></span> -->
                            </li>
                            <br>
                            <li>
                                <label class="u-a-title1">
                                    <span class="fsize16 c-999">邮 &nbsp;&nbsp;箱</span>
                                </label>
                                <input type="text" class="u-a-inpt" id="u_email" name="user.email" value="${user.email}" placeholder="">
                                <!-- <span class="u-a-error"><em class="u-a-zq icon16">&nbsp;</em></span> -->
                            </li>
                            <li>
                                <label class="u-a-title1">

                                    <span class="fsize16 c-999"><font color="red">*</font>手机号</span>
                                </label>
                                <input type="text" class="u-a-inpt" id="u_mobile" name="user.mobile" value="${user.mobile}" placeholder="" required >
                                <!-- <span class="u-a-error"><em class="u-a-zq icon16">&nbsp;</em></span> -->
                            </li>
                        </ol>
                    </form>
                    <div class="ml50 mt50 pl50">
                        <a href="javascript:void(0)" title="" class="comm-btn c-btn-7" onclick="updateUserInfo(${user.userId})" id="commitBtn">提 交</a>
                        <a href="${ctx }/uc/showValidCourseLocation" title="" class="comm-btn c-btn-7" id="nextBtn" hidden>下一步</a>
                    </div>
                </div>

        <!-- /我的资料 -->
    </div>
</article>
<!-- /右侧内容区 结束 -->
</body>
</html>