<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<title>Find fan favorites</title>
</head>
<body>
	<div class="container theme-showcase" role="main">
	
	<div class="jumbotron">
	<h1>Top 10 Fan Favorite Movies</h1>
	</div>
      <table class="table table-striped">
            <thead><tr>
                <th>title</th>
                <th>year</th>
                <th>genre</th>
                <th>runtime_minutes</th>
                <th>is_adult</th>
            </tr></thead>
            <c:forEach items="${fanFavorites}" var="movie" >
                <tbody><tr>
                    <td><<c:out value="${movie.getMovieId()}"/>"><c:out value="${movie.getTitle()}"/></td>
                    <td><c:out value="${movie.getYear()}" /></td>
                    <td><c:out value="${movie.getGenre()}" /></td>
                    <td><c:out value="${movie.getRuntimeMinutes()}"/></td>
                    <td><c:out value="${movie.getIsAdult()?'Yes':'No'}"/></td>
                </tr></tbody>
            </c:forEach>
       </table>  
	<br/><br/>
	</div>
	
	<!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    
</body>
</html>