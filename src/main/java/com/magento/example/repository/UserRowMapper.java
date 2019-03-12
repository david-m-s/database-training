package com.magento.example.repository;

import com.magento.example.domain.User;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

  @Override
  public User mapRow(ResultSet rs, int rowNum) throws SQLException {
    return new User(rs.getLong("id"), rs.getString("username"), rs.getString("first_name"),
        rs.getString("last_name"));
  }

}
