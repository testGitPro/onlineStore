package org.vhrybyniuk.store.servlets.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.vhrybyniuk.store.entity.User;
import org.vhrybyniuk.store.security.SecurityService;
import org.vhrybyniuk.store.utils.PageGenerator;

import java.io.IOException;


public class LogInServlet extends HttpServlet {
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userToken = securityService.getUserToken(request);
        if (!securityService.isAuth(userToken)) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            User user = User.builder()
                    .email(email)
                    .password(password)
                    .build();
            try {
                userToken = securityService.tokenRegistration(user);
                Cookie cookie = new Cookie("user-token", userToken);
                response.addCookie(cookie);
                response.sendRedirect("/");

            } catch (Exception e) {
                String errorMessage = "Incorect email or password!";
                response.getWriter().print(
                        PageGenerator.generate().getPageWithMessage("ftl/registration.html", errorMessage));
            }

        } else {
            response.sendRedirect("ftl/login.html");
        }
    }
}
