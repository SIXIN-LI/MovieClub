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
<jsp:include page="./navbar.jsp"/>
<div class="container theme-showcase" role="main">
	<div class="jumbotron" style="margin-left:150px;margin-top:100px;margin-right:150px;color:black;text-align:left">
		<h2>${messages.success}</h2>
		<ul class="list-group list-group-flush">
			<li class="list-group-item">Title: ${movie.getMovieId()}</li>
			<li class="list-group-item">Year: ${movie.getYear()}</li>
			<li class="list-group-item">Genre: ${movie.getGenre()}</li>
			<li class="list-group-item">runtime_minutes: ${movie.getRuntimeMinutes()}</li>
			<li class="list-group-item">Is adult: ${movie.getIsAdult()?'Yes':'No'}</li>
			<li class="list-group-item">Crews: ${crews}</li>
		</ul>
		<p></p>
		<c:choose>
			<c:when test="${messages.withRatingOrNot == true}">
				<h2>This movie has a rating: </h2>
				<ul class="list-group list-group-flush">
					<li class="list-group-item">Average Rating: ${rating.getAverageRating()}</li>
					<li class="list-group-item">Total number of votes: ${rating.getNumOfVotes()}</li>
				</ul>

				<%-- Use a button to route to a different page and also pass in the movieid parameter!--%>
				<button class="btn btn-light"><a href="createRating?movieid=<c:out value="${movie.getMovieId()}"/>"><c:out value="Add a new rating"/></a></button>
			</c:when>
			<c:when test="${messages.withoutRating == false}">
				<h2>This movie doesn't have a rating: </h2>
				<button class="btn btn-light"><a href="createRating?movieid=<c:out value="${movie.getMovieId()}"/>"><c:out value="Create a new rating"/></a></button>
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