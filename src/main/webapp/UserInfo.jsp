<%--
  Created by IntelliJ IDEA.
  User: sixinli
  Date: 3/15/23
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>${action} user</h1>
    <form action="getUserInfo" method="post">
        <p>
            <label for="username">UserName</label>
            <input id="username" name="username" value="${user.getUserName()}">
        </p>
        <p>
            <label for="password">Password</label>
            <input id="password" name="password" value="${user.getPassword()}">
        </p>
        <p>
            <label for="firstname">FirstName</label>
            <input id="firstname" name="firstname" value="${user.getFirstName()}">
        </p>
        <p>
            <label for="lastname">LastName</label>
            <input id="lastname" name="lastname" value="${user.getLastName()}">
        </p>
        <p>
            <label for="gender">Gender</label>
            <input id="gender" name="gender" value="${user.getGender()}">
        </p>

        <p>
            <input type="submit">
        </p>
    </form>
    <br/><br/>
    <p>
        <span id="successMessage"><b>${messages.success}</b></span>
    </p>
</body>
</html>
