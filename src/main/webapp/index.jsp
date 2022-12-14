<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>

<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="views/header.jsp"/>

        <hr>

        <h1>${rb.getString("greatings")}</h1>

        <jsp:include page="views/trains.jsp"/>

        <jsp:include page="views/tickets.jsp"/>

        <jsp:include page="views/routes.jsp"/>

        <jsp:include page="views/stations.jsp"/>

    </body>

</html>
