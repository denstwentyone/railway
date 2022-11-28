package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBManager;
import entities.Train;

import javax.servlet.RequestDispatcher;

@WebServlet("/main")
public class MainServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Train> trains;
        try {
            trains = DBManager.getInstance().getAllTrains();
            req.setAttribute("trains", trains);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/main.jsp");
            requestDispatcher.forward(req, resp);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            resp.getWriter().println("Error Code: " + e.getErrorCode());
        }
    }
    
}
