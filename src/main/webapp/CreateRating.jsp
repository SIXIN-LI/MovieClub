<%--
  Created by IntelliJ IDEA.
  User: lijiaxuan
<%--  Date: 3/19/23--%>
<%--  Time: 18:09--%>
<%--  To change this template use File | Settings | File Templates.--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Hi HI hi</title>
</head>
<body>
    <%
        out.print("Hi JSP");
        out.print("hihihi ");
    %>
    <h1><c:out value="hello" escapeXml="false"/></h1>
    <div class="container theme-showcase" role="main">
        <%--    associate this jsp file with the corresponding servelet --%>
        <form action="createRating" method="post">
            <div class="jumbotron"><h1>Create a new rating!</h1></div>
            <p>
            <h3><label for="score">Add a score</label></h3>
            <input id="score" name="score" value="${fn:escapeXml(param.score)}">
            </p>
            <p>
                <input type="submit" class="btn btn-info" value="create">
                <%--   route back to the findmovieinfo page --%>
             <a href="findmovieinfo?movieid=<c:out value="${movie.getMovieId()}"/>"><c:out value="${movie.getTitle()}"/></a>

            </p>
        </form>
        <div class="alert alert-info" role="alert">
</body>
</html>
