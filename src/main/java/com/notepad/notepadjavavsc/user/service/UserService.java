package com.notepad.notepadjavavsc.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.notepad.notepadjavavsc.user.domain.User;
import com.notepad.notepadjavavsc.user.dto.UserResponse;
import com.notepad.notepadjavavsc.user.repository.UserRepository;

@Service
@Transactional
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserResponse getUserInfoById(Long userId) {
    User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not exist"));

    return UserResponse.builder().email(user.getEmail()).build();
  }

  public UserResponse getUserInfoByEmail(String email) {
    User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("User not exist"));

    return UserResponse.builder().email(user.getEmail()).build();
  }
}
