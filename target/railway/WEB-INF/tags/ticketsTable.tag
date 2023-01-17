<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute 
    name="tickets" 
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
      </tr>
    </thead>
    <tbody>
    <c:forEach var="train" items="${tickets}">
        <tr>
            <td>${train.getRoute().getStartingStation().getCity().concat("-".concat(train.getRoute().getStartingStation().getName()))}</td>
            <td>${train.getRoute().getFinalStation().getCity().concat("-".concat(train.getRoute().getFinalStation().getName()))}</td>
            <td>${train.getRoute().getStartingTime()}</td>
            <td>${train.getRoute().getFinalTime()}</td>
            <td>${train.getDate()}</td>
            <td>${train.getCost()}</td>
            

        </tr>
        
    </c:forEach>
    </tbody>
  </table>

