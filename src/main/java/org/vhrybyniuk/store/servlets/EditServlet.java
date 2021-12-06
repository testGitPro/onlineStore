package org.vhrybyniuk.store.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.vhrybyniuk.store.dao.jdbc.JdbcProductDao;
import org.vhrybyniuk.store.utils.PageGenerator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EditServlet extends HttpServlet {
    JdbcProductDao jdbcProductDao = new JdbcProductDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("product", jdbcProductDao.get(id));
        response.getWriter().print(
                PageGenerator.generate().getPage("ftl/edit.html", parameters));
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
