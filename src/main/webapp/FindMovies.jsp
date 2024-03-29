<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <jsp:include page="navbar.jsp"/>
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<title>Find Movies</title>
</head>
<body>
    <div class="container theme-showcase" role="main">
	<form action="findmovies" method="post">
	    <div class="jumbotron"><h1>Welcome to MovieClub!</h1></div>
        <div style="overflow:auto;">
            <style>
              h3 a {
                color: white;
              }
            </style>
            <h3 type="submit" class="btn btn-info" style="float:left; margin-right:250px">
                <a href="http://localhost:8080/MovieClub/fanfavorites">Top 10 Fan Favorite Movies</a>
            </h3>
            <h3 type="submit" class="btn btn-info" style="float:none; margin:0 auto;">
                <a href="http://localhost:8080/MovieClub/surpriseme">Surprise Me</a>
            </h3>
            <h3 type="submit" class="btn btn-info" style="float:right;">
                <a href="http://localhost:8080/MovieClub/customizedSearchMovies">Customized Search</a>
            </h3>
        </div>
        <hr>
        <br>
		<p>
			<h3><label for="moviename">MovieName</label></h3>
			<input id="moviename" name="moviename" value="${fn:escapeXml(param.moviename)}">
		</p>
		<p>
			<input type="submit" class="btn btn-info" value="search">
		</p>

	</form>
	<div class="alert alert-info" role="alert">
	<h2><span id="successMessage"><b>${messages.success}</b></span></h2>
	</div>
	<h1>Matched Movies</h1>
        <table class="table table-striped">
            <thead><tr>
                <th>title</th>
                <th>year</th>
                <th>genre</th>
                <th>runtime_minutes</th>
                <th>is_adult</th>
            </tr></thead>
            <c:forEach items="${movies}" var="movie" >
                <tbody><tr>
                    <td><a href="findmovieinfo?movieid=<c:out value="${movie.getMovieId()}"/>"><c:out value="${movie.getTitle()}"/></a></td>
                    <td><c:out value="${movie.getYear()}" /></td>
                    <td><c:out value="${movie.getGenre()}" /></td>
                    <td><c:out value="${movie.getRuntimeMinutes()}"/></td>
                    <td><c:out value="${movie.getIsAdult()?'Yes':'No'}"/></td>
                </tr></tbody>
            </c:forEach>
       </table>  
    </div>
     
    <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
       
</body>
</html>
