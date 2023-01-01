<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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