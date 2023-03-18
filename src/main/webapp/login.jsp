<%--
  Created by IntelliJ IDEA.
  User: sixinli
  Date: 3/15/23
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session.setAttribute("userId", 1);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="login" method="post">
        <p>
            <label for="username">UserName</label>
            <input id="username" name="username" >
        </p>
        <p>
            <label for="password">Password</label>
            <input id="password" name="password" >
        </p>
        <p>
            <input type="submit">
        </p>
    </form>
    <br/><br/>
    <p>
        <span id="successMessage"><b>${messages.success}</b></span>
        <span id="failureMessage"><b>${messages.failure}</b></span>
    </p>
</body>
</html>
