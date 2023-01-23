<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<c:set var="view" scope = "session" value="search.jsp"/>

<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    </head>
    <body>
        <div class="container ml-5 mt-2">
            <jsp:include page="views/header.jsp"/>
            <form action="/railway/controller?action=search" method="post">
                <label>${rb.getString("from")}</label>
                <select name="from">
                    <c:forEach var="station" items="${allstations}">
                        <option value="${station.getId()}">
                            ${station.toString()}
                        </option>
                    </c:forEach>
                </select> 
                <label>${rb.getString("to")}</label>
                <select name="to">
                    <c:forEach var="station" items="${allstations}">
                        <option value="${station.getId()}">
                            ${station.toString()}
                        </option>
                    </c:forEach>
                </select> 
                <input class="btn btn-default bg-secondary text-white m-1" type="submit" value="${rb.getString("search")}">
            </form>

                
            <my:routesTable routes="${foundroutes}" />

            
            <form action="/railway" method="get">
                <input class="btn btn-default bg-secondary text-white m-1" type="submit" value="${rb.getString("back")}">
            </form>
        </div>
    </body>
</html>
