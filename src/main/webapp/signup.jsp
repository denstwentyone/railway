<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <body>
        <form action="/railway/controller?action=signup" method="post">
            <p>${rb.getString("email")}</p> <input name="email" type="email" /> <br>
            <p>${rb.getString("password")}</p> <input name="password" type="password" /> <br>
            <input type="submit" value="${rb.getString("signup")}">
        </form>
        <form action="/railway" method="get">
            <input type="submit" value="${rb.getString("back")}">
        </form>
    </body>
</html>
