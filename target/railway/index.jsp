<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import ="java.util.List"%>
<html>
    <body>
        <form action="/railway/login" method="get">
            <input type="submit" value="log in">
        </form>
        <form action="/railway/signup" method="get">
            <input type="submit" value="sign up">
        </form>
        <hr>
        <ul>
            <%
                List<String> trains = (List<String>) request.getAttribute("trains");

                for (String train : trains) {
                    out.println("<li>" + train + "</li>");
                }
            %>
        </ul>
    </body>

</html>
