package org.vhrybyniuk.store.dao;

import org.vhrybyniuk.store.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    List<Product> findAll() throws SQLException;

    void add(String name, double price);

    void update(int id, String name, double price);

    Product get(int id);

    void delete(int id) throws SQLException;

}
