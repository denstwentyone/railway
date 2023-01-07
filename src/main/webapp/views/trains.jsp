<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>${rb.getString("trains")}</h2>
<c:forEach var="train" items="${trains}">
    <form action="controller?action=order" method="post"> 
        <label for="${train.getNumber()}">${train.toString()}</label>
        <c:if test = "${role == 'customer'}">
            <input type="hidden" name="trainid" value="${train.getNumber()}">
            <input type="submit" value="order">
        </c:if>
    </form>
</c:forEach>
<br/>
<c:if test = "${role == 'admin'}">
    <h3>${rb.getString("addtrain")}</h3>
    <form action="/railway/controller?action=addtrain" method="post">
        <p>${rb.getString("route")}</p> 
        <select name="route">
            <c:forEach var="route" items="${routes}">
                <option value="${route.getId()}">${route.toString()}</option>
            </c:forEach>
        </select> 
        <p>${rb.getString("date")}</p> <input name="date" type="date"/>
        <p>${rb.getString("cost")}</p> <input name="cost" type="number"/>
        <input type="submit" value="${rb.getString("add")}">
    </form>
</c:if>
