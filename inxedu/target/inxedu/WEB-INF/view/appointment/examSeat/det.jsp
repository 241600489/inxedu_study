<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 3
  Date: 2016/8/6
  Time: 8:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <c:forEach items="${table}" var="x">
        <tr>
            <c:forEach items="${x}" var="y">
                <td>
                    <c:if test="${y.seatNo!=null}">
                    <input type="checkbox"  <c:if test="${y.state==true}">checked</c:if>  name>${y.seatNo}
                    </c:if>
                </td>
            </c:forEach>
        </tr>
    </c:forEach>
</table>
<input type="button" id="c" value="返回"/>
<script type="text/javascript">
    document.getElementById("c").onclick= function(){
        window.history.back(-1);
    }
</script>
</body>
</html>
