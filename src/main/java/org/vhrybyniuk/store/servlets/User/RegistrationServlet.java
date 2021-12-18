package org.vhrybyniuk.store.servlets.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.vhrybyniuk.store.dao.jdbc.JdbcProductDao;
import org.vhrybyniuk.store.dao.jdbc.JdbcUserDao;
import org.vhrybyniuk.store.entity.Product;
import org.vhrybyniuk.store.entity.User;
import org.vhrybyniuk.store.security.SecurityService;
import org.vhrybyniuk.store.utils.PageGenerator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class RegistrationServlet extends HttpServlet {

    JdbcUserDao jdbcUserDao = new JdbcUserDao();
    SecurityService securityService = new SecurityService(jdbcUserDao);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userToken = securityService.getUserToken(request);
        if (!securityService.isAuth(userToken)) {
            Map<String, Object> parameters = new HashMap<>();
            response.getWriter().print(
                    PageGenerator.generate().getPage("ftl/registration.html", parameters));
        } else {
            response.sendRedirect("/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userToken = securityService.getUserToken(request);
        String errorMessage = "";
        if (!securityService.isAuth(userToken)) {

            String user_name = request.getParameter("user_name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String password2 = request.getParameter("password2");

            if (!password2.equals(password)) {
                errorMessage = "Password mismatch";
                response.getWriter().print(
                        PageGenerator.generate().getPageWithMessage("ftl/registration.html", errorMessage));
                return;
            }
            User user = User.builder()
                    .user_name(user_name)
                    .email(email)
                    .password(password)
                    .build();

            try {
                if (!jdbcUserDao.isEmailExistsInDB(user)) {
                    jdbcUserDao.addUser(user);
                    response.sendRedirect("/login");
                } else {
                    errorMessage = "A user with the same email address already exists";
                    response.getWriter().print(
                            PageGenerator.generate().getPageWithMessage("ftl/registration.html", errorMessage));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
