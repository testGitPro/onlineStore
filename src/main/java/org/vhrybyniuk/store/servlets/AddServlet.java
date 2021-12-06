package org.vhrybyniuk.store.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.vhrybyniuk.store.dao.jdbc.JdbcProductDao;
import org.vhrybyniuk.store.utils.PageGenerator;

import java.io.IOException;

public class AddServlet extends HttpServlet {
    JdbcProductDao jdbcProductDao = new JdbcProductDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // show form
        response.getWriter().print(PageGenerator.generate().getPage("ftl/add.html"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        jdbcProductDao.add(name, price);
        response.sendRedirect("/products");
    }
}