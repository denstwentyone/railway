<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute 
    name="routes" 
    type="java.util.List<db.entities.Route>"
    required="true" %>

<table class="table table-striped">
    <thead>
      <tr>
        <th>From</th>
        <th>To</th>
        <th>Starting Time</th>
        <th>Final Time</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach var="route" items="${routes}">
        <tr>
            <td>${route.getStartingStation().getCity().concat("-".concat(route.getStartingStation().getName()))}</td>
            <td>${route.getFinalStation().getCity().concat("-".concat(route.getFinalStation().getName()))}</td>
            <td>${route.getStartingTime()}</td>
            <td>${route.getFinalTime()}</td>
            <td>
                <c:if test = "${role == 'customer'}">
                    <form action="/railway/controller?action=routetrains" method="post">
                        <input type="hidden" name="route" value="${route.getId()}"/>
                        <input class="btn btn-default bg-secondary text-white m-1" type="submit" value="go to trains"/>
                    </form>
                </c:if>
            </td>

        </tr>
        
    </c:forEach>
    </tbody>
  </table>

