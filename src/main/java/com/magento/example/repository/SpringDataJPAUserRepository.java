package com.magento.example.repository;

import com.magento.example.domain.NamesOnly;
import com.magento.example.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SpringDataJPAUserRepository extends CrudRepository<User, Long> {

  // get the first name and last name of the users who have username starting with a given string
  List<NamesOnly> findByUsernameStartingWith(String usernameLike);

}
