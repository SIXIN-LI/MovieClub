<%@ page import="movie.model.Users" %><%--
  Created by IntelliJ IDEA.
  User: sixinli
  Date: 3/17/23
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <title>Nav Bar</title>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark justify-content-end">
    <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
            <a class="nav-link" href="#">MovieClub</a>
        </li>
        <li class="nav-item active">
            <a class="nav-link" href="${pageContext.request.contextPath}">Home <span class="sr-only">(current)</span></a>
        </li>
    </ul>
    <ul class="navbar-nav ml-auto">

        <% Users user = (Users) request.getSession().getAttribute("user"); %>
        <% if (user == null) { %>
        <li class="nav-item active">
            <a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a>
        </li>
        <li class="nav-item active">
            <a class="nav-link" href="${pageContext.request.contextPath}/registerUser">SignUp</a>
        </li>
        <li class="nav-item active">
            <a class="nav-link" href="${pageContext.request.contextPath}/updateMembership">Membership</a>
        </li>
        <% } else { %>
        <li class="nav-item active">
            <a class="nav-link" href="${pageContext.request.contextPath}/updateUser">Profile</a>
        </li>
        <li class="nav-item active">
            <a class="nav-link" href="${pageContext.request.contextPath}/updateMembership">Membership</a>
        </li>
        <%} %>
    </ul>
</nav>

</body>
</html>