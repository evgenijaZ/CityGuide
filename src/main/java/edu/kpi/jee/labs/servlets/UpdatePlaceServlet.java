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
@WebServlet(name = "createPlace", urlPatterns = "/update")
public class UpdatePlaceServlet extends HttpServlet {
    private PlaceDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new PlaceDAO("attractions", "place");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("updatePlace.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("place_id"));
        String name = req.getParameter("place_name");
        String address = req.getParameter("place_address");
        float latitude = Float.parseFloat(req.getParameter("place_lat"));
        float longitude = Float.parseFloat(req.getParameter("place_lng"));
        Place place = new Place(id, name, address, latitude, longitude);
        boolean result = dao.create(place);
        req.setAttribute("result", result);
        doGet(req, resp);
    }
}
