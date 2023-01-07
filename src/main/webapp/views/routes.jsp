<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test = "${role == 'admin'}">
    <hr>
    <h2>${rb.getString("routes")}</h2>
    <c:forEach var="route" items="${routes}">
        <p>${route.toString()}</p>
        <br/>
    </c:forEach>
    <h3>${rb.getString("addroute")}</h3>
    <form action="/railway/controller?action=addroute" method="post">
        <p>${rb.getString("startingstation")}</p> 
        <select name="startingstation">
            <c:forEach var="station" items="${stations}">
                <%-- <input type="hidden" name="startingstationID" value="${station.getId()}" /> --%>
                <option value="${station.getId()}">
                    ${station.toString()}
                </option>
            </c:forEach>
        </select> 
        <p>${rb.getString("startingtime")}</p> <input name="startingtime" type="time" step="2"/>
        <p>${rb.getString("finalstation")}</p> 
        <select name="finalstation">
            <c:forEach var="station" items="${stations}">
                <option value="${station.getId()}">${station.toString()}</option>
            </c:forEach>
        </select> 
        <p>${rb.getString("finaltime")}</p> <input name="finaltime" type="time" step="2"/>
        <input type="submit" value="${rb.getString("add")}">
    </form>
</c:if>