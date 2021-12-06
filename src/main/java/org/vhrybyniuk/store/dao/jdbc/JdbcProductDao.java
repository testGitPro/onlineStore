package org.vhrybyniuk.store.dao.jdbc;

import org.vhrybyniuk.store.dao.ProductDao;
import org.vhrybyniuk.store.dao.jdbc.mapper.ProductRowMapper;
import org.vhrybyniuk.store.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcProductDao implements ProductDao {
    private static final String FIND_ALL_SQL = "SELECT id, name, price,created FROM Product";
    public static final String INSERT_SQL  = "INSERT into product(name, price) VALUES (?, ?);";
    private static final String UPDATE_SQL = "UPDATE product SET name=?, price=? WHERE id=?;";
    private static final String SELECT_BY_ID_SQL ="SELECT id, name, price, created FROM product WHERE id=?;";
    private static final String DELETE_BY_ID_SQL ="DELETE FROM product WHERE id=?;";

    private static final ProductRowMapper PRODUCT_ROW_MAPPER = new ProductRowMapper();

    @Override
    public List<Product> findAll() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = PRODUCT_ROW_MAPPER.mapRow(resultSet);
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;

    }

    @Override
        public void add(String name, double price) {
            try {
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL);
                preparedStatement.setString(1, name);
                preparedStatement.setDouble(2, price);

                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    @Override
    public void update(int id, String name, double price) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL);
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, price);
            preparedStatement.setLong(3, id);


            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Product get(int id) {
        Product product = null;

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_SQL);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            product = PRODUCT_ROW_MAPPER.mapRow(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public void delete(int id) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID_SQL);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5433/store", "postgres", "1111");

    }
}
