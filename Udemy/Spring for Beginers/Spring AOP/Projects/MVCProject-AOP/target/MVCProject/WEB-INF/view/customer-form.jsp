<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 19.12.2022
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Customer Form</title>
    <link rel="stylesheet" type="text/css" href="/resources/CSS/styles.css"/>
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>CRM - Customer Relationship Manager</h2>

    </div>
</div>
<div id="container">
    <h3>Save Customer</h3>
    <form:form action="saveCustomer" modelAttribute="customer" method="POST">
        <form:hidden path="id"/>
    <table>
        <tr>
            <td><label for="firstName">First name:</label></td>
            <td><form:input path="firstName" id="firstName"/></td>
        </tr>
        <tr>
            <td><label for="lastName">Last name:</label></td>
            <td><form:input path="lastName" id="lastName"/></td>
        </tr>
        <tr>
            <td><label for="email">Email:</label></td>
            <td><form:input path="email" id="email"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Save" class="save"></td>
        </tr>
    </table>
    </form:form>
    <div style="clear: both;"></div>
    <p>
        <a href="/customer/list">Back to List</a>
    </p>
</div>
</body>
</html>
