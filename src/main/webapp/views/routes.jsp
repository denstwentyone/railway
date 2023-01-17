<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<hr>
<h2>${rb.getString("routes")}</h2>
<my:routesTable routes="${routes}" />

<div class="row ml-1">
        <div class="col-sm-3">
        <form action="/railway/controller?action=prev&view=${view}&value=routes" method="post">
            <input type="submit" value="prev">
        </form>
        </div>
        <div class="col-sm-3">
        <a>${routespage}</a>
        </div>
        <div class="col-sm-3">
        <form action="/railway/controller?action=next&view=${view}&value=routes" method="post">
            <input type="submit" value="next">
        </form>
        </div>
    </div>
<c:if test = "${role == 'admin'}">
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