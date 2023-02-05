package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.LocationService;
import services.TaxRateService;
import services.UserService;

public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String action = request.getParameter("action");

        String userID = request.getParameter("userID");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String country = request.getParameter("country");
        String region = request.getParameter("region");
        String locationcode = request.getParameter("locationcode");
        String taxRate1 = request.getParameter("taxRate1");
        String taxRate2 = request.getParameter("taxRate2");
        String taxRate3 = request.getParameter("taxRate3");
        String taxRateID = request.getParameter("taxRateID");

        UserService us = new UserService();
        TaxRateService trs = new TaxRateService();
        LocationService ls = new LocationService();

        if (action != null) {

            switch (action) {
                case "add":
                    if (country == null || region == null || locationcode == null || taxRate1 == null || taxRate2 == null || taxRate3 == null
                            || country.equals("") || region.equals("") || locationcode.equals("") || taxRate1.equals("") || taxRate2.equals("") || taxRate3.equals("")) {
                        session.setAttribute("message", "Error adding TaxRate");
                        response.sendRedirect("admin");
                        return;
                    }

                    trs.insert(country, region, locationcode, taxRate1, taxRate2, taxRate3);
                    response.sendRedirect("admin");
                    return;

                case "update":
                    if (country == null || region == null || locationcode == null || taxRate1 == null || taxRate2 == null || taxRate3 == null
                            || country.equals("") || region.equals("") || locationcode.equals("") || taxRate1.equals("") || taxRate2.equals("") || taxRate3.equals("")) {
                        session.setAttribute("message", "Error updating tax rate");
                        response.sendRedirect("admin");
                        return;
                    }

                    trs.update(country, region, locationcode, taxRate1, taxRate2, taxRate3);
                    session.setAttribute("message", "Edited Tax Rate!");
                    response.sendRedirect("admin");
                    return;

                case "delete":
                    trs.delete(locationcode);
                    response.sendRedirect("admin");
                    return;

                case "cancel":
                    session.setAttribute("message", "Cancelled Changes");
                    response.sendRedirect("admin");
                    return;

            }

            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
        }
    }
}