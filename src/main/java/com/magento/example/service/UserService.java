package com.magento.example.service;

import com.magento.example.domain.User;
import com.magento.example.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void addAll(User... users) {
    for (User u : users) {
      userRepository.save(u);
    }
  }
}
