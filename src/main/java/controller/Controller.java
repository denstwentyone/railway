package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import actions.Action;
import actions.ActionFactory;

@WebServlet({"/controller", ""})
public class Controller extends HttpServlet {
    
    private static final ActionFactory ACTION_FACTORY = ActionFactory.geActionFactory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(process(req)).forward(req, resp);
        // resp.sendRedirect(process(req));
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(process(req)).forward(req, resp);
        // resp.sendRedirect(process(req));
    }
    
    private String process(HttpServletRequest req) {
        Action action = ACTION_FACTORY.createAction(req.getParameter("action"));
        String path = action.execute(req);
        return path;
        
    }
}