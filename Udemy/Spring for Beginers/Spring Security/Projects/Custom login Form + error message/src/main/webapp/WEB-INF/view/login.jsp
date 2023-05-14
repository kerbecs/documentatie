<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 14.01.2023
  Time: 00:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h3>Login Form</h3>
<br>
<form:form method="post" action="authenticateTheUser">
    <c:if test="${param.error != null}">
        <i style="color: red;">Invalid username or/and password</i>
        <br>
    </c:if>
<label for="user">Username</label> <input id="user" type="text" name="username"/>
<br>


<br>
<label for="pass">Password</label> <input id="pass" type="password" name="password">
<br>
<input type="submit" value="Submit">
</form:form>
</body>
</html>
