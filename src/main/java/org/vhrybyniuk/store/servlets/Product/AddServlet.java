package org.vhrybyniuk.store.servlets.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.vhrybyniuk.store.dao.jdbc.JdbcProductDao;
import org.vhrybyniuk.store.security.SecurityService;
import org.vhrybyniuk.store.utils.PageGenerator;

import java.io.IOException;

public class AddServlet extends HttpServlet {
    JdbcProductDao jdbcProductDao = new JdbcProductDao();
    SecurityService securityService ;

    public AddServlet(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // show form
        String userToken = securityService.getUserToken(request);
        if (securityService.isAuth(userToken)) {
            response.getWriter().print(PageGenerator.generate().getPage("ftl/add.html"));
        } else {
            response.sendRedirect("/registration");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        jdbcProductDao.add(name, price);
        response.sendRedirect("/products");
    }
}