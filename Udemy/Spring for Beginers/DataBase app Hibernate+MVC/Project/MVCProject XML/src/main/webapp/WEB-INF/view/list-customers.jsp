<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 16.12.2022
  Time: 01:09
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="MyApp.Utility.SortClass" %>

<html>
<head>
    <title>List Customers</title>
    <link rel="stylesheet" type="text/css" href="/resources/CSS/styles.css"/>
</head>
<body>
<div id="wrapper">
    <div id="header">
       <h2>CRM - Customer RelationShip Manager</h2>
        <br>
    </div>
</div>

<div id="container">
    <div id="content">
        <input type="button" value="Add Customer" onclick="window.location.href='showFormForAdd'; return false;" class="add-button"/>
        <div class="searchCustomer">
        <form:form action="searchCustomer" method="get">
            <label for="searchCustomer" style="display: inline;">Search customer</label>
            <input id= "searchCustomer" type="text" name="customerName" placeholder="Name" class="searchBox"/>
            <input type="submit" value="Search"/>
        </form:form>
        </div>
        <table>
            <tr>
                <c:url var="firstName" value="/customer/listSort">
                    <c:param name="sort" value="<%=Integer.toString(SortClass.FIRST_NAME)%>"/>
                </c:url>
                <c:url var="lastName" value="/customer/listSort">
                    <c:param name="sort" value="<%=Integer.toString(SortClass.LAST_NAME)%>"/>
                </c:url>
                <c:url var="email" value="/customer/listSort">
                    <c:param name="sort" value="<%=Integer.toString(SortClass.EMAIL)%>"/>
                </c:url>

                <th><a href="${firstName}">First Name</a></th>
                <th><a href="${lastName}">Last Name</a></th>
                <th><a href="${email}">Email</a></th>
                <th>Action</th>
            </tr>
            <c:forEach var="customer" items="${customers}">

                <c:url var="updateLink" value="/customer/showFormForUpdate">
                    <c:param name="customerId" value="${customer.id}"/>
                </c:url>
                <c:url var="deleteLink" value="/customer/delete">
                    <c:param name="customerId" value="${customer.id}"/>
                </c:url>


                <tr>
                    <td>${customer.firstName}</td>
                    <td>${customer.lastName}</td>
                    <td>${customer.email}</td>
                    <td><a href="${updateLink}">Update</a>
                        |
                        <a href="${deleteLink}"
                        onclick="if(!(confirm('Are you sure?'))) return false;">Delete</a>
                    </td>

                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>
