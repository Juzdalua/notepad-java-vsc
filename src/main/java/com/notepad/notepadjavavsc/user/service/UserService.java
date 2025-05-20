package com.notepad.notepadjavavsc.user.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.notepad.notepadjavavsc.user.domain.User;
import com.notepad.notepadjavavsc.user.dto.UserDto;
import com.notepad.notepadjavavsc.user.repository.UserRepository;

@Service
@Transactional
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  private void validateDuplicateUser(User user) {
    userRepository.findByName(user.getName()).ifPresent(u -> {
      throw new IllegalStateException("User already exists.");
    });
  }

  public Long createUser(UserDto userDto) {
    User user = User.of(userDto.getName(), userDto.getAge());
    validateDuplicateUser(user);
    userRepository.save(user);
    return user.getId();
  }

  @Transactional(readOnly = true)
  public List<User> findUsers() {
    return userRepository.findAll();
  }

  @Transactional
  public void updateUserAge(String name, int newAge) {
    // 1.
    User user = userRepository.findByName(name).orElseThrow(() -> new IllegalArgumentException("Invalid Name."));
    user.changeAge(newAge);

    // 2.
    userRepository.updateUserAgeByName(name, newAge);
  }
}
