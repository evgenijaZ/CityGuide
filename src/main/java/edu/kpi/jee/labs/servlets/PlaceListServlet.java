package edu.kpi.jee.labs.servlets;


import edu.kpi.jee.labs.dao.PlaceDAO;
import edu.kpi.jee.labs.entities.Place;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Yevheniia Zubrych on 30.03.2018.
 */
@WebServlet(name = "PlaceListServlet", urlPatterns = "/placeList")
public class PlaceListServlet extends HttpServlet {
    private PlaceDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new PlaceDAO("attractions", "place");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List <Place> places = dao.getAll();
        req.setAttribute("places", places);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("placeList.jsp");
        requestDispatcher.forward(req, resp);
    }
}
