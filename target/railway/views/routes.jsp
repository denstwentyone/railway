<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<hr>
<h2>${rb.getString("routes")}</h2>
<my:routesTable routes="${routes}" />

<div class="row ml-1">
        <div class="col-sm-3">
        <form action="/railway/controller?action=prev&view=${view}&value=routes" method="post">
            <input class="btn btn-default bg-secondary text-white m-1" type="submit" value="prev">
        </form>
        </div>
        <div class="col-sm-3">
        <a>${routespage}</a>
        </div>
        <div class="col-sm-3">
        <form action="/railway/controller?action=next&view=${view}&value=routes" method="post">
            <input class="btn btn-default bg-secondary text-white m-1" type="submit" value="next">
        </form>
        </div>
    </div>
<c:if test = "${role == 'admin'}">
    <h3>${rb.getString("addroute")}</h3>
    <form action="/railway/controller?action=addroute" method="post">
        <p>${rb.getString("startingstation")}</p> 
        <select name="startingstation">
            <c:forEach var="station" items="${allstations}">
                <option value="${station.getId()}">
                    ${station.toString()}
                </option>
            </c:forEach>
        </select> 
        <p>${rb.getString("startingtime")}</p> <input name="startingtime" type="time" step="1"/>
        <p>${rb.getString("finalstation")}</p> 
        <select name="finalstation">
            <c:forEach var="station" items="${allstations}">
                <option value="${station.getId()}">${station.toString()}</option>
            </c:forEach>
        </select> 
        <p>${rb.getString("finaltime")}</p> <input name="finaltime" type="time" step="1"/>
        <input class="btn btn-default bg-secondary text-white m-1" type="submit" value="${rb.getString("add")}">
    </form>
</c:if>