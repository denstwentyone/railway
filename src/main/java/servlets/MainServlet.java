package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.entities.Train;
import db.postgres.PostgresDBManager;

import javax.servlet.RequestDispatcher;

@WebServlet("")
public class MainServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Train> trains;
        try {
            // trains = DBManager.getDManager("postgres").getAllTrains();
            trains = PostgresDBManager.getInstance().getAllTrains();
            List<String> trainsStrings = new ArrayList<>();
            for (Train t : trains) {
                trainsStrings.add(t.toString());
            }
            req.setAttribute("trains", trainsStrings);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(req, resp);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            resp.getWriter().println("Error Code: " + e.getErrorCode());
        }
    }

}
