<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import ="java.util.List"%>
<html>
    <body>
        <% if(request.getSession().getAttribute("user") == null) {
            %>
            <form action="login.jsp" method="get">
                <input type="submit" value="log in">
            </form>
            <form action="signup.jsp" method="get">
                <input type="submit" value="sign up">
            </form>
            <%
        } else {
            out.println(request.getSession().getAttribute("user"));
            %>
            <form action="controller?action=logout" method="post">
                <input type="submit" value="log out">
            </form>
            <%
        } %>
        <hr>
        <h1>RAILWAY TICKET OFFICE, WELCOMES YOU!</h1>
    </body>

</html>
