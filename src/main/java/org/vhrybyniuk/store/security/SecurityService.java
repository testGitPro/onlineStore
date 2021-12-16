package org.vhrybyniuk.store.security;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.vhrybyniuk.store.dao.UserDao;
import org.vhrybyniuk.store.dao.jdbc.JdbcUserDao;
import org.vhrybyniuk.store.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;


public class SecurityService {
    JdbcUserDao jdbcUserDao = new JdbcUserDao();

    private ArrayList<String> tokenList = new ArrayList<>();


    public boolean isAuth(String userToken) {
        return tokenList.contains(userToken) ? true : false;
    }


    public String getUserToken(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user-token")) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }


    public String tokenRegistration(User user) throws SQLException {
        if (jdbcUserDao.isUserExistsInDB(user)) {
            String userToken = UUID.randomUUID().toString();
            tokenList.add(userToken);
            return userToken;
        }
        throw new RuntimeException();
    }

    public boolean dropedCookie(Cookie cookie) throws SQLException {
        if (tokenList.contains(cookie.getValue())) {
            tokenList.remove(cookie.getValue());
            return true;
        }
        return false;
    }
}
