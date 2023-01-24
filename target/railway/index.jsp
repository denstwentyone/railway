<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="view" scope = "session" value="index.jsp"/>
<c:set var="trainsvalue" scope = "session" value="trains"/>

<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    </head>
    <body class="p-3 mb-2 ">

        <div class="container">
            <jsp:include page="views/header.jsp"/>
        </div>

        <hr>
        <div class="container">
        
            <h1>${rb.getString("greatings")}</h1>

            <jsp:include page="views/trains.jsp"/>
            
            
            <div class="jumbotron">
                <jsp:include page="views/tickets.jsp"/>
            </div>

            <div class="jumbotron">
                <jsp:include page="views/routes.jsp"/>
            </div>

            <div class="jumbotron">
                <jsp:include page="views/stations.jsp"/>
            </div>

        </div>

    </body>

</html>
