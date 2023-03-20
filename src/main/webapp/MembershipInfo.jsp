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
		<c:choose>
			<c:when test="${messages.userNotLoggedIn == true}">
				<h2> No user logged in yet, please log in or create a new account </h2>
			</c:when>
			<c:when test="${messages.ismembership == true}">
				<h2>This user has a membership: </h2>
				<ul class="list-group list-group-flush">
					<li class="list-group-item">User Name: ${user.getUserName()}</li>
					<li class="list-group-item">First Name: ${user.getFirstName()}</li>
					<li class="list-group-item">User Name: ${user.getLastName()}</li>
<%--					<li class="list-group-item">Membership Id: ${membership.getMembershipId()}</li>--%>
					<li class="list-group-item">Membership Created Time: ${membership.getTimestamp()}</li>
				</ul>
			</c:when>
			<c:when test="${messages.ismembership == false}">
				<h2>This user doesn't have a membership: </h2>
				<p> </p>
<%--				<button type="button" class="btn btn-info">Register as a membership</button>--%>
				<form action = "updateMembership" method = "post">
					<input type = "submit" value = "Register" class="btn btn-light"/>
					<c:if test="${messages.success == true}">
						<script>
							window.addEventListener("load",function(){
								alert("You are now registered as a membership!");
							})
						</script>
					</c:if>
				</form>
			</c:when>
		</c:choose>
		<p></p>

	</div>
	</div>
	
     
    <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
       
</body>
</html>
