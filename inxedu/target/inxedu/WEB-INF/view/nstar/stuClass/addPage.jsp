<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2016/7/27
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加班级</title>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
    <script>
        $.validator.setDefaults({
            submitHandler: function() {
                alert("提交事件!");
            }
        });
        $().ready(function() {
            $("#commentForm").validate();
        });
    </script>
    <style>
        .error{
            color:red;
        }
    </style>
</head>
<body>
<form action="/admin/stuClass/addClass">
    <table>
        <tr>
            <td>班级名称</td>
            <td><input type="text" name="className" required></td>
        </tr>
        <tr>
            <td>详细信息</td>
            <td><input type="text" name="description" required></td>
        </tr>
        <tr>
            <td>所属学院</td>
            <td><select  style="width: 300px" name="schoolId" required>
                <option value="">==请选择==</option>
              <c:forEach items="${list}" var="college">
              <option value=${college.id}>${college.collegeName}</option>
              </c:forEach>
    </td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" value="提交"></td>
            <td colspan="2" align="center"><input id="c" type="button" value="返回"></td>
        </tr>
    </table>
    <script type="text/javascript">
       document.getElementById("c").onclick= function(){
            window.history.back(-1);
        }
    </script>
</form>
</body>
</html>
