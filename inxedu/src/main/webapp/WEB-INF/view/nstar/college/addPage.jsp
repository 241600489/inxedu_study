<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2016/7/29
  Time: 9:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加页面</title>
    <script type="application/javascript">
        function back(){
            window.history.back(-1);
        }
    </script>
</head>
<body>

<form action="/admin/college/add">
    <table align="center">
        <tr>
            <td>学院代码</td>
            <td><input type="text" name="collegeCode"></td>
        </tr>
        <tr>
            <td>学院名称</td>
            <td><input type="text" name="collegeName"></td>
        </tr>
        <tr>
            <td>学院简介</td>
            <td><input type="text" name="description"></td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" value="提交">

            <a href="#" onclick="back()">返回</a>
            </td>
        </tr>

    </table>

</form>
</body>
</html>
