package org.vhrybyniuk.store.dao;

import jakarta.servlet.http.HttpServletRequest;
import org.vhrybyniuk.store.entity.User;

import java.sql.SQLException;

public interface UserDao {
    boolean isUserExistsInDB(User user) throws SQLException;

    boolean isEmailExistsInDB(User user) throws SQLException;

    void addUser(User user) throws SQLException;


}

