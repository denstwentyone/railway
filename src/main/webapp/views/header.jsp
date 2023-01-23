<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>


<header class="navbar container-fluid">
    <div class="btn-group page-header">
        <c:if test = "${user == null}">
            <form action="login.jsp" method="get">
                <input class="btn btn-default bg-secondary text-white m-1" type="submit" key="login" value="${rb.getString("login")}">
            </form>
            <form action="signup.jsp" method="get">
                <input class="btn btn-default bg-secondary text-white m-1" type="submit" key="signup" value="${rb.getString("signup")}">
            </form>
        </c:if>
        <c:if test = "${user != null}">
            <p class="conteiner m-1 pt-1">
                ${user}
                ${role}
            </p>
            <form action="controller?action=logout" method="post">
                <input class="btn btn-default bg-secondary text-white m-1" type="submit" value="log out">
            </form>
        </c:if>
        <form action="search.jsp" method="get">
            <input class="btn btn-default bg-secondary text-white m-1" type="submit" value="${rb.getString("search")}">
        </form>
        <form action="controller?action=changelocale&locale=en_US" method="post">
            <input class="btn btn-default bg-secondary text-white m-1" type="submit" value="eng">
        </form>
        <form action="controller?action=changelocale&locale=ua_UA" method="post">
            <input class="btn btn-default bg-secondary text-white m-1" type="submit" value="ua">
        </form>
    </div>
</header>