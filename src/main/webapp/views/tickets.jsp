<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<c:if test = "${role == 'customer'}">
    <hr>
    <h2>${rb.getString("tickets")}</h2>
    <my:ticketsTable tickets="${tickets}" />
    <div class="row ml-1">
        <div class="col-sm-3">
        <form action="/railway/controller?action=prev&view=${view}&value=tickets" method="post">
            <input class="btn btn-default bg-secondary text-white m-1" type="submit" value="prev">
        </form>
        </div>
        <div class="col-sm-3">
        <a>${ticketspage}</a>
        </div>
        <div class="col-sm-3">
        <form action="/railway/controller?action=next&view=${view}&value=tickets" method="post">
            <input class="btn btn-default bg-secondary text-white m-1" type="submit" value="next">
        </form>
        </div>
    </div>
</c:if>
