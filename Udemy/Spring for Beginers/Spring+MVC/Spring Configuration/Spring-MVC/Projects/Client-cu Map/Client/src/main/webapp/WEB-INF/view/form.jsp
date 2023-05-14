<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 10.11.2022
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
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
<form:form modelAttribute="client" action="processForm">
   First Name <form:input path="firstName"/>
    <br><br>

    Last Name(*) <form:input path="lastName"/>
    <form:errors path="lastName" cssClass="error"/>

    <br><br>
    Country: <form:select path="country">
             <form:options items="${client.countryList}"/>
              </form:select>
    <form:errors path="country" cssClass="error"/>

    <br><br>
    Language <form:checkboxes path="language" items="${client.languageList}"/>
    <form:errors path="language" cssClass="error"/>
    <br><br>
    <input type="submit" value="Submit">
</form:form>
</body>
</html>
