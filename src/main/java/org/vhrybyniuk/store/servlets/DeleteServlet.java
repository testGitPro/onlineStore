package org.vhrybyniuk.store.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.vhrybyniuk.store.dao.jdbc.JdbcProductDao;

import java.io.IOException;

public class DeleteServlet extends HttpServlet {
    JdbcProductDao jdbcProductDao = new JdbcProductDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        jdbcProductDao.delete(id);
        response.sendRedirect("/products");
    }
}
