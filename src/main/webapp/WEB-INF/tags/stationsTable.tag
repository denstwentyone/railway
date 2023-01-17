<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute 
    name="stations" 
    type="java.util.List<db.entities.Station>"
    required="true" %>

<table class="table table-striped">
    <thead>
      <tr>
        <th>City</th>
        <th>Name</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach var="station" items="${stations}">
        <tr>
            <td>${station.getCity()}</td>
            <td>${station.getName()}</td>
        </tr>
        
    </c:forEach>
    </tbody>
  </table>

