<%--
  Created by IntelliJ IDEA.
  User: karak
  Date: 16-8-1
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <form action="${ctx}/admin/user/updateUserInfo">
        <input name="userId" type="hidden" value="${user.userId}">
        <table>
            <tr>
                <td>用户名</td>
            </tr>
            <td>
                ${user.userName}
            </td>

            <tr>
                <td>身份证号</td>
                <td>
                    <input type="text" name="user.idCard" value="${user.idCard}">
                </td>
            </tr>

            <tr>
                <td>学号</td>
                <td>
                    <input type="text" name="user.no" value="${user.no}">
                </td>
            </tr>

            <tr>
                <td colspan="2" align="center"><input type="submit" value="修改"></td>
            </tr>

        </table>
    </form>
    <script type="application/javascript">
        function back(){
            window.history.back(-1);

        }
    </script>
    <a href="#" onclick="back()">返回</a>
</head>
<body>

</body>
</html>
