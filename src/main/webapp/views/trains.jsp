<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<div class="container">
    <h2>${rb.getString("trains")}</h2>
    <my:trainsTable trains="${trains}" />
    <div class="row ml-1">
        <div class="col-sm-3">
        <form action="/railway/controller?action=prev&view=${view}&value=${trainsvalue}&trainsroute=${trainsroute}" method="post">
            <input class="btn btn-default bg-secondary text-white m-1" type="submit" value="prev">
        </form>
        </div>
        <div class="col-sm-3">
        <a>${trainspage}</a>
        </div>
        <div class="col-sm-3">
        <form action="/railway/controller?action=next&view=${view}&value=${trainsvalue}&trainsroute=${trainsroute}" method="post">
            <input class="btn btn-default bg-secondary text-white m-1" type="submit" value="next">
        </form>
        </div>
    </div>
</div>
<c:if test = "${role == 'admin'}">
    <h3>${rb.getString("addtrain")}</h3>
    <form action="/railway/controller?action=addtrain" method="post">
        <p>${rb.getString("route")}</p> 
        <select name="route">
            <c:forEach var="route" items="${selectroutes}">
                <option value="${route.getId()}">${route.toString()}</option>
            </c:forEach>
        </select> 
        <div class="row ml-1">
            <div class="col-sm-3">
                <a class="btn btn-default bg-secondary text-white m-1" href="/railway/controller?action=prev&view=${view}&value=selectroutes">prev</a>
            </div>
            <div class="col-sm-3">
                <a>${selectroutespage}</a>
            </div>
            <div class="col-sm-3">
                <a class="btn btn-default bg-secondary text-white m-1" href="/railway/controller?action=next&view=${view}&value=selectroutes">next</a>
            </div>
        </div>
        <p>${rb.getString("date")}</p> <input name="date" type="date"/>
        <p>${rb.getString("cost")}</p> <input name="cost" type="number" step="0.01"/>
        <p>${rb.getString("seats")}</p> <input name="seats" type="number"/>
        <input class="btn btn-default bg-secondary text-white m-1" type="submit" value="${rb.getString("add")}">
    </form>
</c:if>
