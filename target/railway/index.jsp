<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page import="java.util.List"%>
<html>
    <head>
        <meta charset="utf-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    </head>
    <body>
        <header class="navbar container-fluid">
            <% if(request.getSession().getAttribute("user") == null) {
                %>
                <form action="login.jsp" method="get">
                    <input type="submit" key="login" value="${rb.getString("login")}">
                </form>
                <form action="signup.jsp" method="get">
                    <input type="submit" key="signup" value="${rb.getString("signup")}">
                </form>
                <%
            } else {
                %>
                ${user}
                <form action="controller?action=logout" method="post">
                    <input type="submit" value="log out">
                </form>
                <%
            } %>
            <form action="controller?action=changelocale&locale=en_US" method="post">
                <input type="submit" value="eng">
            </form>
            <form action="controller?action=changelocale&locale=ua_UA" method="post">
                <input type="submit" value="ua">
            </form>
        </header>
        <hr>
        <h1 key="greatings">${rb.getString("greatings")}</h1>
        <c:forEach var="train" items="${trains}">
            ${train.toString()} <br/> <hr/>
        </c:forEach>
        
    </body>

</html>
