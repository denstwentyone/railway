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
