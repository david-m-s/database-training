package com.magento.example;

import com.magento.example.domain.User;
import com.magento.example.repository.UserRepository;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DatabaseTrainingApplicationTests {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private UserRepository userRepository;

  @Test
  public void contextLoads() {
  }

  @Test
  public void A_onlyAdmin() {
    Assert.assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "users"));
  }

  @Test
  public void B_addedOneRow() {
    userRepository.save(new User(null, "user1", "John", "Smith"));
    Assert.assertEquals(2, JdbcTestUtils.countRowsInTable(jdbcTemplate, "users"));
  }

}
