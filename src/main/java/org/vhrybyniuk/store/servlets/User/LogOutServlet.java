package org.vhrybyniuk.store.servlets.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.vhrybyniuk.store.security.SecurityService;

import java.io.IOException;
import java.sql.SQLException;

public class LogOutServlet extends HttpServlet {
    private SecurityService securityService;

    public LogOutServlet(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userToken = securityService.getUserToken(request);
        if (securityService.isAuth(userToken)) {
            Cookie cookie = new Cookie("user-token", userToken);
            try {
                if (securityService.dropedCookie(cookie)) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    response.sendRedirect("/login");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            response.sendRedirect("/login");
        }
    }
}
