package com.magento.example;

import com.magento.example.domain.NamesOnly;
import com.magento.example.domain.User;
import com.magento.example.repository.SpringDataJPAUserRepository;
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
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class JPAUserRepositoryTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private SpringDataJPAUserRepository userRepository;

  @Test
  public void contextLoads() {
  }

  @Test
  public void A_onlyAdmin() {
    Assert.assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "users"));
  }

  @Test
  public void B_addedOneRow() {
    userRepository.save(new User(null, "userJ", "John", "Smith"));
    Assert.assertEquals(2, JdbcTestUtils.countRowsInTable(jdbcTemplate, "users"));
  }

  @Test
  public void testCount() {
    Assert.assertEquals(JdbcTestUtils.countRowsInTable(jdbcTemplate, "users"),
        userRepository.count());
  }

  @Test
  public void testDelete() {
    Long id = userRepository.save(new User(null, "userJ", "John", "Smith")).getId();
    Assert.assertTrue(userRepository.existsById(id));
    userRepository.deleteById(id);
    Assert.assertFalse(userRepository.existsById(id));

  }

  @Test
  public void testProjection() {
    userRepository.save(new User(null, "userJ", "John", "Smith")).getId();
    List<NamesOnly> names = userRepository.findByUsernameStartingWith("a");
    Assert.assertEquals(1, names.size());
    Assert.assertEquals("Alice", names.get(0).getFirstName());
  }


}
