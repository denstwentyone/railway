<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test = "${role == 'customer'}">
    <hr>
    <h2>${rb.getString("tickets")}</h2>
    <c:forEach var="ticket" items="${tickets}">
        <p>${ticket.toString()}</p>
        <br/>
    </c:forEach>
</c:if>
