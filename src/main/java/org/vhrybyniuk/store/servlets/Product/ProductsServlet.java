package org.vhrybyniuk.store.servlets.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.vhrybyniuk.store.dao.jdbc.JdbcProductDao;
import org.vhrybyniuk.store.entity.Product;
import org.vhrybyniuk.store.security.SecurityService;
import org.vhrybyniuk.store.utils.PageGenerator;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsServlet extends HttpServlet {
    SecurityService securityService = new SecurityService();
    JdbcProductDao jdbcProductDao = new JdbcProductDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userToken = securityService.getUserToken(request);
        if (securityService.isAuth(userToken)) {

            List<Product> products = jdbcProductDao.findAll();
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("products", products);
            response.getWriter().print(
                    PageGenerator.generate().getPage("ftl/all.html", parameters));
        } else {
            response.sendRedirect("/registration");
        }
    }
}
