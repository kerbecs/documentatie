<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 14.11.2022
  Time: 01:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Form</title>
    <style>
        .error{
            color: red;
        }
    </style>
</head>
<body>
<form:form modelAttribute="customer" action="register">
    Course Code <form:input path="courseCode"/>
    <form:errors path="courseCode" cssClass="error"/>
    <br>
    <input type="submit"/>
</form:form>
</body>
</html>
