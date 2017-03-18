<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>我要选课</title>
</head>
<style>

</style>
<body>
<div class="i-slide">
<div class="container">
    <div  data-example-id="bordered-table">
<table class="table table-bordered">
    <caption><b>上课时间选择</b></caption>
    <tr>
        <th>&nbsp;&nbsp;</th>
        <th>周一</th>
        <th>周二</th>
        <th>周三</th>
        <th>周四</th>
        <th>周五</th>
        <th>周六</th>
    </tr>
    <tbody >
      ${table}
    </tbody>
</table>
       <a href="${ctx}/selectLession/selectLessionResult"><h1>查看选课结果</h1></a>
    </div>
</div>
</div>
</body>
</html>