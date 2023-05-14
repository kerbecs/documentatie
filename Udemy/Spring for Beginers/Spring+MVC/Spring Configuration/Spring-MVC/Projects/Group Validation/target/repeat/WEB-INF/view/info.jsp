<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 04.02.2023
  Time: 02:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="${pageContext.request.contextPath}/WEB-INF/CSS/styles.css"/>"/>

</head>
<body>
First Name ${student.firstName}
<br>
Last Name ${student.lastName}
<br>
Age ${student.age}
<br>
Opinion: ${param.opinion}
<br>
Country: ${student.country}
<br>
Languages:
<br>
<c:forEach var="lang" items="${student.langs}">
  * ${lang}
  <br/>
</c:forEach>
Job: <br>
<c:forEach var="j" items="${student.jobs}">
    * ${j}
    <br/>
</c:forEach>
</body>
</html>
