<html>
    <body>
        <ul>
            <%
                List<Train> trains = (List<Train>) request.getAttribute("trains");

                for (Train train : trains) {
                    out.println("<li>" + t + "</li>");
                }
            %>
        </ul>
        <form action="/railway" method="get">
            <input type="submit" value="go back">
        </form>
    </body>
</html>
