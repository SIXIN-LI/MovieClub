<%@ page import="movie.model.Users" %>
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
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <title>User Info</title>
</head>
<body>
    <div class="col-md-6">
      <label for="username">UserName</label>
      <input class="form-control" id="username" name="username" value="${user.getUserName()}" required>
    </div>
    <div class="col-md-6">
      <label for="password">Password</label>
      <input class="form-control" id="password" name="password" value="${user.getPassword()}" required>
    </div>
    <div class="col-md-6">
      <label for="firstname">FirstName</label>
      <input class="form-control" id="firstname" name="firstname" value="${user.getFirstName()}" required>
    </div>
    <div class="col-md-6">
      <label for="lastname">LastName</label>
      <input class="form-control" id="lastname" name="lastname" value="${user.getLastName()}" required>
    </div>
    <div class="col-md-12">
      <label>Gender</label>
        <% Users user = (Users) session.getAttribute("user"); %>
        <% if (user == null || user.getGender().toString().equals("MALE")) {  %>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="gender" id="genderMale1" value="MALE" required checked>
                <label class="form-check-label " for="genderMale">Male</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="gender" id="genderFemale1" value="FEMALE" required
                >
                <label class="form-check-label" for="genderFemale">Female</label>
            </div>
        <% } else { %>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="gender" id="genderMale" value="MALE" required >
                <label class="form-check-label " for="genderMale">Male</label>
              </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="gender" id="genderFemale" value="FEMALE" required checked>
                <label class="form-check-label" for="genderFemale">Female</label>
            </div>

        <% } %>
    </div>

    <p>
      <input type="submit" class="btn btn-info">
    </p>

    <br/><br/>
    <p>
      <span id="successMessage"><b>${messages.success}</b></span>
      <span id="failedMessage"><b>${messages.failure}</b></span>
    </p>


</body>
</html>
