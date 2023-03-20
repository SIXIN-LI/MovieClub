<%--
  Created by IntelliJ IDEA.
  User: lijiaxuan
<%--  Date: 3/19/23--%>
<%--  Time: 18:09--%>
<%--  To change this template use File | Settings | File Templates.--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <jsp:include page="/navbar.jsp"/>
    <!-- Bootstrap -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <title>Create new rating</title>
<body>
    <div class="container theme-showcase" role="main">
            <%--    associate this jsp file with the corresponding servelet --%>
            <form action = "createRating" method = "post">
                <div class="jumbotron"><h1>Create a new rating!</h1></div>
                Score: <input type = "text" name = "score">
<%--                    <script type="text/javascript">--%>
<%--                        function onsubmit() {--%>
<%--                            // alert("Hello! I am an alert box!!");--%>
<%--                            return confirm("Hello! I am an alert box!!");--%>
<%--                            &lt;%&ndash;document.forms[0].action = "findmovieinfo?movieid=<c:out value="${movie.getMovieId()}"/>";&ndash;%&gt;--%>
<%--                            &lt;%&ndash;document.forms[0].submit();&ndash;%&gt;--%>
<%--                        }--%>
<%--                    </script>--%>
<%--                <script>--%>
<%--                    function onsubmit() {--%>
<%--                        alert("Form submitted successfully!");--%>

<%--                    }--%>
<%--                </script>--%>
                <p> </p>
                <input type = "submit" value = "Submit" class="btn btn-info"/>
                <c:if test="${messages.success == true}">
                    <script>
                        window.addEventListener("load",function(){
                            alert("You have successfully created a new rating!");
                        })
                    </script>
                </c:if>

                <%--   route back to the findmovieinfo page --%>
                <button class="btn btn-light">
                    <a href="findmovieinfo?movieid=<c:out value="${movie.getMovieId()}"/>"><c:out value="Back"/></a>
                </button>
            </form>
    </div>
</body>
</html>
