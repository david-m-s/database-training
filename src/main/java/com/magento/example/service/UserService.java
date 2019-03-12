package com.magento.example.service;

import com.magento.example.domain.User;
import com.magento.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public void addAll(User... users) {
    for (User u : users) {
      userRepository.save(u);
    }
  }
}
