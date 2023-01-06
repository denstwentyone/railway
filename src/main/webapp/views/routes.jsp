<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test = "${role == 'admin'}">
    <hr>
    <h2>${rb.getString("routes")}</h2>
    <c:forEach var="route" items="${routes}">
        <p>${route.toString()}</p>
        <br/>
    </c:forEach>
</c:if>