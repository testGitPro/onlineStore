package org.vhrybyniuk.store.servlets.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.vhrybyniuk.store.security.SecurityService;
import org.vhrybyniuk.store.utils.PageGenerator;

import java.io.IOException;

public class LogOutServlet extends HttpServlet {
    SecurityService securityService = new SecurityService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userToken = securityService.getUserToken(request);
        if (!securityService.isAuth(userToken)) {

            response.getWriter().print(
                    PageGenerator.generate().getPage("ftl/login.html"));
        } else {
            response.sendRedirect("/");
        }
    }
}
