<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 14.01.2023
  Time: 23:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles.css"/>
</head>
<body>
<h3>Please log in</h3>
<form:form method="post" action="confirmLogin">
    <br>
    <c:if test="${param.error != null}">
        <font>Invalid username and/or password</font>
    </c:if>
    <c:if test="${param.logout != null}">
        <i>Successfully logout</i>
    </c:if>
    <br>
    Username <input type="text" name="username">
    <br><br>
    Password <input type="password" name="password">
    <br><br>
    <input type="submit" value="Login">
</form:form>

</body>
</html>
