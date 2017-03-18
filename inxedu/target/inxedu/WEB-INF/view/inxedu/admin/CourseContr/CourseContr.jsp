<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>选课时间控制</title>
  <script type="text/javascript" src="${ctx}/static/common/multilevel.js"></script>

  <script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
  <script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
  <script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
  <script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
</head>
<body>
  <form action="${ctx}/admin/courseController/update" method="post">
    <input type="hidden" name="id" value="${controller.id}">
    <table>
      <tr>
        <td>
          <label>正选时间</label>
        </td>
        <td>
          <input id="s0" type="text" name="z1" value="${beginCourseSignupTime0}">
        </td>
      </tr>

      <tr>
        <td>
          <label>正选结束时间</label>
        </td>
        <td>
          <input id="e0" type="text" name="z2" value="${endCourseSignupTime0}">
        </td>
      </tr>
      <tr>
        <td>
          <label>补选时间</label>
        </td>
        <td>
          <input id="s1" type="text" name="b1" value="${beginCourseSignupTime1}">
        </td>
      </tr>
      <tr>
        <td>
          <label>补选结束时间</label>
        </td>
        <td>
          <input id="e1" type="text" name="b2" value="${endCourseSignupTime1}">
        </td>
      </tr>
      <tr>
        <td>
          <label>选课模式</label>
        </td>
        <td>
          基础模式: <input type="radio" name="type" value="0" <c:if test="${controller.type==0}">checked</c:if>>
          增强模式:<input type="radio" name="type" value="1" <c:if test="${controller.type==1}">checked</c:if>>
        </td>
      </tr>
      <tr>
        <td>
          <label>备注</label>
        </td>
        <td>
          <input type="text" name="memo" value="${controller.memo}">
        </td>
      </tr>
      <tr>
        <td>
          <label>最后更新时间</label>
        </td>
        <td>
         ${updateDate}
        </td>
      </tr>
      <tr>
        <td>
          <label>最后修改人</label>
        </td>
        <td>
          ${controller.updateMan}
        </td>
      </tr>
      <tr>
        <td colspan="2">
          <input type="submit" value="修改">
        </td>
      </tr>
      </tr>
    </table>
  </form>
  <script>
    $(function() {
      $( "#s0" ).datepicker();
      $( "#e0" ).datepicker();
      $( "#s1" ).datepicker();
      $( "#e1" ).datepicker();
    });

  </script>
</body>
</html>
