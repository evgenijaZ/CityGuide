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
 * @author Yevheniia Zubrych on 01.04.2018.
 */
@WebServlet(name = "searchPlace", urlPatterns = "/search")
public class SearchPlaceServlet extends HttpServlet {
    private PlaceDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new PlaceDAO("attractions", "place");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("place_name");
        List<Place> places = dao.getByName(name);
        req.setAttribute("places",places);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("searchPlaces.jsp");
        requestDispatcher.forward(req, resp);
    }
}
