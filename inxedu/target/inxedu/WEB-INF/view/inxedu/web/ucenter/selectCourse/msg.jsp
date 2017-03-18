<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 3
  Date: 2016/8/18
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

理论课课程名:${t.courseName}
上课教室：${t.classroom}
上课时间：
<c:if test="${t.week1!=null}">
星期一  第${t.week1}节 (${t.weekIds})
</c:if>
<c:if test="${t.week2!=null}">
    星期二   第${t.week2}节 (${t.weekIds})
</c:if>
<c:if test="${t.week3!=null}">
    星期三   第${t.week3}节 (${t.weekIds})
</c:if>
<c:if test="${t.week4!=null}">
    星期四   第${t.week4}节 (${t.weekIds})
</c:if>
<c:if test="${t.week5!=null}">
    星期五  第${t.week5}节 (${t.weekIds})
</c:if>
<c:if test="${t.week6!=null}">
    星期六   第${t.week6}节 (${t.weekIds})
</c:if>

实验课课程名:${l.courseName}
上课教室：${l.classroom}
上课时间：
<c:if test="${l.week1!=null}">
星期一  第${l.week1}节 (${l.weekIds})
 </c:if>
 <c:if test="${l.week2!=null}">
 星期二   第${l.week2}节 (${l.weekIds})
 </c:if>
 <c:if test="${l.week3!=null}">
 星期三   第${l.week3}节 (${l.weekIds})
 </c:if>
 <c:if test="${l.week4!=null}">
 星期四   第${l.week4}节 (${l.weekIds})
 </c:if>
 <c:if test="${l.week5!=null}">
 星期五  第${l.week5}节 (${l.weekIds})
 </c:if>
 <c:if test="${l.week6!=null}">
 星期六   第${l.week6}节 (${l.weekIds})
 </c:if>

