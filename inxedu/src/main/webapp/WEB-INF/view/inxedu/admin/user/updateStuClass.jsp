<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2016/8/3
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<title>Title</title>

<input type="hidden" name="userId" value="${user.userId}"/>
<br>
<br>

<h1 align="center">修改班级</h1>
<table>

    <tr>
        <td>用户名</td>
    </tr>
    <td>
        ${user.userId}
    </td>
    <br>

    <script type="application/javascript">
        function back() {
            window.history.back(-1);

        }
    </script>

    <a href="#" onclick="back()">返回</a>
    <script type="application/javascript">
        function selectAll(c) {
            var a = c.checked;
            $(".che").attr("checked", a);
        }

        function unSelect(b) {
            if (b.checked == false)
                $(".sele").attr("checked", false);
        }

    </script>

    <table width="800px">

        <form action="/admin/user/${user.userId}/search" id="searchForm"  >
            <tr>
                <td align="center" align="center" colspan="2">
                    班级名称<input name="className" type="text" value="${stuClass.className}">
                    <input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1"/>
                </td>
                <td align="center" colspan="2">
                    所属学院:<select style="width: 300px" name="schoolId">
                    <option value="">==请选择==</option>
                    <c:forEach items="${list1}" var="college">
                        <option value="${college.id}"
                                <c:if test="${college.id==stuClass.schoolId}">selected</c:if> >${college.collegeName}</option>
                    </c:forEach>
                </select>
                </td>
                <td align="center"><input type="button" value="搜索" onclick="submit()"></td>
            </tr>
        </form>
        <script type="text/javascript">
            function submit() {
                document.searchForm.submit();
            }

        </script>

        <form action="">

            <tr>

                <th>班级名称</th>
                <th>详情</th>

                <th>操作</th>
            </tr>

            <c:forEach items="${stuClassList}" var="x">
                <tr>
                    <input type="hidden" value="${x.id}">
                    <td align="center">${x.className}</td>
                    <td align="center">${x.description}</td>

                    <td align="center"><a  onclick="update('${user.userId}/${x.id}')">修改</a> </td>
                </tr>
            </c:forEach>

        </form>
    </table>
</table>
<script type="text/javascript" src="${ctx}/static/common/jquery-1.7.2.min.js"></script>
<script>
    function update(id) {
        $.get("${ctx}/admin/user/updateStuClass/"+id,null,function (data) {
            if(data=="success"){
                alert("修改成功");
            }else{
                alert("修改失败");
            }
        })
    }
</script>
</body>
</html>
