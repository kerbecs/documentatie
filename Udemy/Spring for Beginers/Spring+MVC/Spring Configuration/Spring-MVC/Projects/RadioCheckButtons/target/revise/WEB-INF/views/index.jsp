<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 04.01.2023
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Form</title>
</head>
<body>
<h1>Hello. Please complete the form</h1>

<form:form action="formProcess" method="post" modelAttribute="student">
    First Name <form:input path="firstName"/>
    <br>
    Last Name <form:input path="lastName"/>
    <br>
    Language <form:checkboxes path="languages" items="${student.languagesList}"/>
    <br>
    Age <form:radiobuttons path="age" items="${student.ageList}"/>

    <br>
    Submit<input type="submit">
</form:form>
</body>
</html>
