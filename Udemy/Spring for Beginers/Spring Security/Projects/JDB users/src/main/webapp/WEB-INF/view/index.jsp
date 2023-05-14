<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 16.01.2023
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
<h2>Hello <security:authentication property="principal.username"/></h2>
<br>
<h2>You have the next role(s): <security:authentication property="principal.authorities"/></h2>
<br>
<form:form action="logout" method="post">
    <input type="submit" value="logout"/>
</form:form>
<br>
<security:authorize access="hasRole('MANAGER') || hasRole('ADMIN')">
<a href="/forManagers">Only For Managers</a>
</security:authorize>
<br>
<security:authorize access="hasRole('ADMIN')">
<a href="/forAdmins">Only for Admins</a>
</security:authorize>
</body>
</html>
