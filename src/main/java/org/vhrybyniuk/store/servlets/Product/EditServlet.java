package org.vhrybyniuk.store.servlets.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.vhrybyniuk.store.dao.jdbc.JdbcProductDao;
import org.vhrybyniuk.store.security.SecurityService;
import org.vhrybyniuk.store.utils.PageGenerator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EditServlet extends HttpServlet {
    JdbcProductDao jdbcProductDao = new JdbcProductDao();
    private SecurityService securityService ;

    public EditServlet(SecurityService securityService) {
        this.securityService = securityService;
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userToken = securityService.getUserToken(request);
        if (securityService.isAuth(userToken)) {


            int id = Integer.parseInt(request.getParameter("id"));
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("product", jdbcProductDao.get(id));
            response.getWriter().print(
                    PageGenerator.generate().getPage("ftl/edit.html", parameters));
        } else {
            response.sendRedirect("/registration");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        jdbcProductDao.update(id, name, price);
        response.sendRedirect("/products");
    }
}

