<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <jsp:include page="../navbar.jsp"/>
    <!-- Bootstrap -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <title>User Login</title>
</head>
<body>
    <div class="container theme-showcase" role="main">
        <form action="login" method="post">
            <div class="jumbotron"><h1>User Login</h1></div>
            <p>
                <label for="username">UserName</label>
                <input id="username" name="username" >
            </p>
            <p>
                <label for="password">Password</label>
                <input id="password" name="password" >
            </p>
            <p>
                <input type="submit" class="btn btn-info">
            </p>
        </form>
        <br/><br/>
        <p>
            <span id="successMessage"><b>${messages.success}</b></span>
            <span id="failureMessage"><b>${messages.failure}</b></span>
        </p>
    </div>
</body>
</html>
