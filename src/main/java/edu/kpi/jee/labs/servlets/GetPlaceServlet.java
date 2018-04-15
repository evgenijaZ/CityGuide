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

/**
 * @author Yevheniia Zubrych on 01.04.2018.
 */
@WebServlet(name = "getPlace", urlPatterns = "/place")
public class GetPlaceServlet extends HttpServlet {
    private PlaceDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new PlaceDAO("attractions", "place");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("place_id"));
        Place place = dao.getByKey(id);
        req.setAttribute("place", place);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("place.jsp");
        requestDispatcher.forward(req, resp);
    }
}
