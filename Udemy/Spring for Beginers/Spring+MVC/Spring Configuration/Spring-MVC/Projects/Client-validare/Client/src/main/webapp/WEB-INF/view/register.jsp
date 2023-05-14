<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 10.11.2022
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Register</title>
</head>
<body>
<h1>Hello ${client.firstName} ${client.lastName}</h1>
<br>
<h1>You've been registered</h1>

<h1>Country: ${client.country} <br>
Programming Languages:
    <ul>
        <c:forEach items="${client.language}" var="var">
            <li>${var}</li>
        </c:forEach>
    </ul>
    <br>Age: ${client.age}
    <br>Postal Code: ${client.postalCode}
</h1>
</body>
</html>
