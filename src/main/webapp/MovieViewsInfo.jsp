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
		    <a class="nav-link" href="/MovieClub">Home <span class="sr-only">(current)</span></a>
		  </li>
		</ul>
	
	</nav>
    <div class="container theme-showcase" role="main">
    
<%--     
 	<form action="movieViewsInfo" method="post">
	    <div class="jumbotron"><h1>Your view history</h1></div>
		<p>
			<h4><label for="userId">UserId</label></h4>
			<input id="userId" name="userId" value="${fn:escapeXml(param.userId)}">
		
		</p>
		<p>
			<input type="submit" class="btn btn-info" value="search">
			
		</p>
	</form>  --%> 
	

	<div class="jumbotron"><h1>Your view history</h1></div>
	<div class="alert alert-info" role="alert">
	<h2><span id="successMessage"><b>${messages.success}</b></span></h2>
	</div>
	 <h2>View History for User ID: <c:out value="${fn:escapeXml(param.userId)}" /></h2>
        <table class="table table-striped">
            <thead><tr>
                <th>userName</th>
                <th>viewId</th>
                <th>movie</th>
                <th>viewTime</th>
   
            </tr></thead>
            <c:forEach items="${movieViews}" var="movieView">
            <tr>
                <td><c:out value="${movieView.getUser().getUserName()}" /></td> <!-- display userId -->
                <td><c:out value="${movieView.getViewId()}" /></td>
                <td><a href="findmovieinfo?movieid=<c:out value="${movieView.getMovie().getMovieId()}"/>">
                <c:out value="${movieView.getMovie().getTitle()}"/>
                </a>
                </td>
                <td><c:out value="${movieView.getViewTime()}" /></td>
                <td>
	          
	            
       			 </td>
            </tr>
        </c:forEach>
        
       </table>  
    </div>
    
     <div class="container">
     <span style="color:">Clear View History</span>
     <form method="POST" action="movieViewsInfo">
	      <input type="hidden" name="_method" value="DELETE" /> 
	      <input type="hidden" name="viewId" value="${movieView.getViewId()}"/>
	      <button class="delete-btn"  data-view-id="${movieView.getViewId()}">Clear</button>
	 </form> 
    </div>
    
    
     
    <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
       
</body>
</html>



