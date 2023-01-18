<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>


<c:if test = "${role == 'admin'}">
    <hr>
    <h2>${rb.getString("stations")}</h2>
    <my:stationsTable stations="${stations}" />
    <div class="row ml-1">
        <div class="col-sm-3">
        <form action="/railway/controller?action=prev&view=${view}&value=stations" method="post">
            <input class="btn btn-default bg-secondary text-white m-1" type="submit" value="prev">
        </form>
        </div>
        <div class="col-sm-3">
        <a>${stationspage}</a>
        </div>
        <div class="col-sm-3">
        <form action="/railway/controller?action=next&view=${view}&value=stations" method="post">
            <input class="btn btn-default bg-secondary text-white m-1" type="submit" value="next">
        </form>
        </div>
    </div>
    <h3>${rb.getString("addstation")}</h3>
    <form action="/railway/controller?action=addstation" method="post">
        <p>${rb.getString("name")}</p> <input name="stationname" /> 
        <p>${rb.getString("city")}</p> <input name="city" /> 
        <input class="btn btn-default bg-secondary text-white m-1" type="submit" value="${rb.getString("add")}">
    </form>
</c:if>