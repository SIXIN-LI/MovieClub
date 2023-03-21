<%@ page import="movie.model.Movies" %>
<%@ page import="java.util.List" %>
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
    <div class="jumbotron" style="margin-left:150px;margin-top:100px;margin-right:150px;color:black;text-align:left">
		<h2>${messages.success}</h2>
		<ul class="list-group list-group-flush">
		  <li class="list-group-item">Name: ${crew.getName()}</li>
		  <li class="list-group-item">Crew Id: ${crew.getCrewId()}</li>
		  <li class="list-group-item">Birth Year: ${crew.getBirthYear()}</li>
		  <li class="list-group-item">Death Year: ${crew.getDeathYear()}</li>
		  <li class="list-group-item">Primary Profession: ${crew.getPrimaryProfession()}</li>
			<li class="list-group-item">Known Movies:
				<% for (Movies movie : (List<Movies>) request.getAttribute("knownMovies")
			) {
				out.print(movie.getTitle() + "; ");
			} %> </li>
		</ul>
	</div>
	</div>
	
     
    <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
       
</body>
</html>
