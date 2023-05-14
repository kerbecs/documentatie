<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 04.02.2023
  Time: 02:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="${pageContext.request.contextPath}/WEB-INF/CSS/styles.css"/>"/>

</head>
<body>
<form:form action="processChoice" method="post" modelAttribute="student">
    Country
    <form:select path="countries">
        <form:options items="${student.country}" cssClass="error"/>
        <form:errors path="countries" cssClass="error"/>
    </form:select>
    <br>

    <br>
    Languages
    <br>
    <form:checkboxes path="langs" items="${student.lang}"/>
    <br>
    <form:errors path="langs" cssClass="error"/>
     <br>
    Job
    <br>
    <form:radiobuttons path="jobs" items="${student.job}"/>
    <br>
    <form:errors path="jobs" cssClass="error"/>
    <br>
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>
