package com.magento.example;

import com.magento.example.domain.User;
import com.magento.example.service.UserService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class UserServiceTest {

  private final static Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

  @Autowired
  private JdbcTemplate jdbcTemplate;


  @Autowired
  private UserService userService;

  @Test
  public void transactions() {
    logger.info("Before transactions test there are {} users in db",
        JdbcTestUtils.countRowsInTable(jdbcTemplate, "users"));
    try {
      userService.addAll(new User(null, "user1", "a", "b"), new User(null, "user2", null, null));
    } catch (RuntimeException re) {
      logger.info("Expected exception because nulls are not allowed in first name.", re);
    }
    logger.info("After transactions test there are {} users in db",
        JdbcTestUtils.countRowsInTable(jdbcTemplate, "users"));

  }


}
