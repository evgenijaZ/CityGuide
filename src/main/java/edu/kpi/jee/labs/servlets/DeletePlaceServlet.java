package edu.kpi.jee.labs.servlets;

import edu.kpi.jee.labs.dao.PlaceDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Yevheniia Zubrych on 01.04.2018.
 */
@WebServlet(name = "deletePlace", urlPatterns = "/delete")
public class DeletePlaceServlet extends HttpServlet {
    private PlaceDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new PlaceDAO("attractions", "place");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("deletePlace.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("place_id"));
        boolean result = dao.deleteByKey(id);
        req.setAttribute("result", result);
        doGet(req, resp);
    }
}
