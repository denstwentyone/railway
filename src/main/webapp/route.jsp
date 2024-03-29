<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="view" scope = "session" value="route.jsp"/>
<c:set var="trainsvalue" scope = "session" value="trainsforroute"/>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    </head>
    <body>
        <div class="conteiner">
            <jsp:include page="views/header.jsp"/>

            <hr>

            <jsp:include page="views/trains.jsp"/>
            
            <form action="/railway" method="get">
                <input type="submit" value="${rb.getString("back")}">
            </form>
        </div>
    </body>

</html>