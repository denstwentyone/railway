<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute 
    name="trains" 
    type="java.util.List<db.entities.Train>"
    required="true" %>

<table class="table table-striped">
    <thead>
      <tr>
        <th>From</th>
        <th>To</th>
        <th>Starting Time</th>
        <th>Final Time</th>
        <th>Date</th>
        <th>Cost</th>
        <th>Seats</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach var="train" items="${trains}">
        <tr>
            <td>${train.getRoute().getStartingStation().getCity().concat("-".concat(train.getRoute().getStartingStation().getName()))}</td>
            <td>${train.getRoute().getFinalStation().getCity().concat("-".concat(train.getRoute().getFinalStation().getName()))}</td>
            <td>${train.getRoute().getStartingTime()}</td>
            <td>${train.getRoute().getFinalTime()}</td>
            <td>${train.getDate()}</td>
            <td>${train.getCost()}</td>
            <td>${train.getSeats()}</td>
            <td>
                <c:if test = "${role == 'customer'}">
                <form action="controller?action=order" method="post"> 
                    <input type="hidden" name="trainid" value="${train.getNumber()}">
                    <input type="hidden" name="seats" value="${train.getSeats()}">
                    <input class="btn btn-default bg-secondary text-white m-1" type="submit" value="order">
                </form>
                </c:if>
            </td>

        </tr>
        
    </c:forEach>
    </tbody>
  </table>

