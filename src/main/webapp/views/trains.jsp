<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="train" items="${trains}">
    <form action="controller?action=order" method="post"> 
        ${train.toString()}
        <c:set var = "trainId" scope = "session" value = "${train.getId()}"/>
        <c:if test = "${user != null}">
            <input type="submit" value="order">
        </c:if>
    </form>
    <br/>
</c:forEach>