<%@ page import ="java.util.List"%>
<html>
    <body>
        <ul>
            <%
                List<String> trains = (List<String>) request.getAttribute("trains");

                for (String train : trains) {
                    out.println("<li>" + train + "</li>");
                }
            %>
        </ul>
        <form action="/railway" method="get">
            <input type="submit" value="go back">
        </form>
    </body>
</html>
