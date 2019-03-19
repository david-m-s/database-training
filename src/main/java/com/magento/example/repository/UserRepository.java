package com.magento.example.repository;

import com.magento.example.domain.NamesOnly;
import com.magento.example.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserRepository {

  private JdbcTemplate jdbcTemplate;

  public UserRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<User> findAll() {
    return jdbcTemplate.query("select * from users", new UserRowMapper());
  }

  // TODO
  public long count() {
    return 0;
  }

  // TODO
  public void deleteById(Long id) {

  }

  // TODO
  // get the first name and last name of the users who have username starting with a given string
  List<NamesOnly> findByUsernameStartingWith(String usernameLike) {
    return null;
  }

  public User save(User u) {
    final String sql = "insert into users(username, first_name, last_name) values(?,?,?)";
    KeyHolder holder = new GeneratedKeyHolder();
    jdbcTemplate.update(new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, u.getUsername());
        ps.setString(2, u.getFirstName());
        ps.setString(3, u.getLastName());
        return ps;
      }
    }, holder);

    long newUserId = holder.getKey().longValue();
    return new User(newUserId, u.getUsername(), u.getFirstName(), u.getLastName());
  }
}
