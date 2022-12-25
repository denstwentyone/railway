<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <body>
        <h1>Error: <% out.println((String) request.getSession().getAttribute("error")); %></h1>
    </body>

</html>
