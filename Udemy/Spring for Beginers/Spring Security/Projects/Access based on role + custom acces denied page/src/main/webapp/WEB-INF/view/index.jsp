<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 15.01.2023
  Time: 00:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
    <title>Index</title>
</head>
<body>
<h2>Hello!</h2>
<br>
<hr>
Info about you:
<security:authentication property="principal.username"/>
<security:authentication property="principal.authorities"/>
<hr>
<br>
<p>
    <security:authorize access="hasRole('ADMIN') || hasRole('MANAGER')">
    <a href="/leaders">LeaderShip Meeting(ONLY FOR MANAGERS)</a>
    </security:authorize>
    <br>
    <security:authorize access="hasRole('ADMIN')">
    <a href="/systems">Systems Meeting(ONLY FOR ADMINS)</a>
    </security:authorize>
</p>
<br>
<form:form method="post" action="logout">
    <input type="submit" value="Logout"/>
</form:form>
</body>
</html>
