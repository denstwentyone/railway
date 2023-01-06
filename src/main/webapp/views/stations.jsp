<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test = "${role == 'admin'}">
    <hr>
    <h2>${rb.getString("stations")}</h2>
    <c:forEach var="station" items="${stations}">
        <p>${station.toString()}</p>
        <br/>
    </c:forEach>
    <h3>${rb.getString("addstation")}</h3>
    <form action="/railway/controller?action=addstation" method="post">
        <p>${rb.getString("name")}</p> <input name="stationname" /> 
        <p>${rb.getString("city")}</p> <input name="city" /> 
        <input type="submit" value="${rb.getString("add")}">
    </form>
</c:if>