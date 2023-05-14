<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 04.02.2023
  Time: 01:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Form</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/CSS/styles.css"/>"/>
</head>
<body>
<h3>Enter your details</h3>
<br>
<form:form method="post" action="register" modelAttribute="student">
    First Name <form:input path="firstName" /> <form:errors path="firstName" cssClass="error"/>
    <br>
    <br>
    Last Name <form:input path="lastName" /> <form:errors path="lastName" cssClass="error"/>
    <br>
    <br>
    Age <form:input path="age" /> <form:errors path="age" cssClass="error"/>
    <br>
    <br>
    What do you think about our site? <input type="text" name="opinion"/>
    <br>
    <br>
    <input type="submit" value="Submit"/>

</form:form>
</body>
</html>
