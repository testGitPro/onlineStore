package org.vhrybyniuk.store.servlets.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.vhrybyniuk.store.dao.jdbc.JdbcProductDao;
import org.vhrybyniuk.store.security.SecurityService;

import java.io.IOException;

public class DeleteServlet extends HttpServlet {
    JdbcProductDao jdbcProductDao = new JdbcProductDao();
    SecurityService securityService ;

    public DeleteServlet(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userToken = securityService.getUserToken(request);
        if (securityService.isAuth(userToken)) {
            int id = Integer.parseInt(request.getParameter("id"));
            jdbcProductDao.delete(id);
            response.sendRedirect("/products");
        } else {
            response.sendRedirect("/registration");
        }
    }
}