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
<title>Find Movies</title>
</head>
<body>
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark justify-content-end">
		<ul class="navbar-nav mr-auto">
		  <li class="nav-item active">
		    <a class="nav-link" href="#">MovieClub</a>
		  </li>
		  <li class="nav-item active">
		    <a class="nav-link" href="/MovieClub">Home<span class="sr-only">(current)</span></a>
		  </li>
		</ul>
		<ul class="navbar-nav ml-auto">
			<li class="nav-item active">
				<a class="nav-link" href="#"> <button type="button" class="btn btn-light">Membership</button></a>
			</li>
		  <li class="nav-item active">
		    <a class="nav-link" href="#">Login</a>
		  </li>
		  <li class="nav-item active">
		    <a class="nav-link" href="#">SignUp</a>
		  </li>
		</ul>
	</nav>
	<div class="container theme-showcase" role="main">
    <div class="jumbotron" style="margin-left:150px;margin-top:100px;margin-right:150px;color:black;text-align:left">
		<h2>${messages.success}</h2>
		<ul class="list-group list-group-flush">
		  <li class="list-group-item">Title: ${movie.getMovieId()}</li>
		  <li class="list-group-item">Year: ${movie.getYear()}</li>
		  <li class="list-group-item">Genre: ${movie.getGenre()}</li>
		  <li class="list-group-item">runtime_minutes: ${movie.getRuntimeMinutes()}</li>
		  <li class="list-group-item">Is adult: ${movie.getIsAdult()?'Yes':'No'}</li>
		</ul>
		<p></p>
<%--		<h2>${messages.withRatingOrNot}</h2>--%>
		<c:choose>
			<c:when test="${messages.withRatingOrNot == true}">
				<h2>This movie has a rating: </h2>
				<ul class="list-group list-group-flush">
					<li class="list-group-item">Average Rating: ${rating.getAverageRating()}</li>
					<li class="list-group-item">Total number of votes: ${rating.getNumOfVotes()}</li>
				</ul>
<%--				<button type="button" class="btn btn-info">Add a new rating </button>--%>
				<input type="button" class="btn btn-info" value="Add a new rating" name="CreateRating"
					   onclick="window.location='createRating'" />
				<a href="createRating?movieid=<c:out value="${movie.getMovieId()}"/>"><c:out value="${movie.getTitle()}"/></a>
			</c:when>
			<c:when test="${messages.withoutRating == false}">
				<h2>This movie doesn't have a rating: </h2>
				<button type="button" class="btn btn-info">Create a new rating </button>
			</c:when>
		</c:choose>
	</div>
	</div>
	
     
    <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
       
</body>
</html>
