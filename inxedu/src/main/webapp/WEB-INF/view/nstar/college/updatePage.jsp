<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2016/7/29
  Time: 9:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<script type="application/javascript">
    function back(){
        window.history.back(-1);

    }
</script>
    <form action="/admin/college/update">
        <input type="hidden" name="id" value="${college.id}">
        <table align="center">
            <tr>
                <td>学院代码</td>
                <td><input type="text" name="collegeCode" value="${college.collegeCode}"></td>
            </tr>
            <tr>
                <td>学院名称</td>
                <td><input type="text" name="collegeName" value="${college.collegeName}"></td>
            </tr>
            <tr>
                <input name="currentPage" type="hidden" value="${currentPage}"/>
                <td>学院简介</td>
                <td><input type="text" name="description" value="${college.description}"></td>
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
