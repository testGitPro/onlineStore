package org.vhrybyniuk.store.dao.jdbc.mapper;

import org.vhrybyniuk.store.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;


public class ProductRowMapper {

    public Product mapRow(ResultSet resultSet) throws SQLException {


          int id = resultSet.getInt("id");
          String name  = resultSet.getString("name");
          Double price = resultSet.getDouble("price");
          Timestamp created = resultSet.getTimestamp("created");

          Product product = Product.builder()
                  .id(id)
                  .name(name)
                  .price(price)
                  .created(created.toLocalDateTime())
                  .build();

        return product;

    }
}
