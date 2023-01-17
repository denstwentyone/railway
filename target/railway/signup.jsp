<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    </head>
    <body>
        <div class="container ml-5 mt-2">
            <jsp:include page="views/header.jsp"/>
            <form action="/railway/controller?action=signup" method="post">
                <p>${rb.getString("email")}</p> <input name="email" type="email" /> <br>
                <p>${rb.getString("password")}</p> <input name="password" type="password" /> <br>
                <input type="submit" value="${rb.getString("signup")}">
            </form>
            <form action="/railway" method="get">
                <input type="submit" value="${rb.getString("back")}">
            </form>
        </div>
    </body>
</html>
