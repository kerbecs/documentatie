<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 13.01.2023
  Time: 02:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h3>My Custom Login Page</h3>
<form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="post">
<p>
    User name: <input type="text" name="username">
</p>
    <p>
        Password: <input type="password" name="password">
    </p>
    <input type="submit" value="Login">
</form:form>
</body>
</html>
