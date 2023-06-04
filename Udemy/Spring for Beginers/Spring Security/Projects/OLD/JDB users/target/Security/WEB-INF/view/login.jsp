<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 16.01.2023
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles.css"/>
</head>
<body>
<h2>Please login</h2>
<br>
<form:form method="post" action="loginProcess">
    <c:if test="${param.error != null}">
        <h4>Invalid username or/and password</h4>
        <br>
    </c:if>
    <c:if test="${param.logout != null}">
        <h4 style="color: green;">Successfully logout</h4>
        <br>
    </c:if>
    Username <input type="text" name="username">
    <br>
    <br>
    Password <input type="password" name="password">
    <br>
    <input type="submit" value="login">
</form:form>
</body>
</html>
