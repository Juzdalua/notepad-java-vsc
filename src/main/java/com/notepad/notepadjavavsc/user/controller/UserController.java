package com.notepad.notepadjavavsc.user.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notepad.notepadjavavsc.user.dto.UserDto;
import com.notepad.notepadjavavsc.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<Long> save(@RequestBody UserDto userDto) {
    Long userId = userService.createUser(userDto);
    return ResponseEntity.ok(userId);
  }

  @GetMapping
  public ResponseEntity<List<UserDto>> list() {
    List<UserDto> users = userService.findUsers().stream().map(user -> new UserDto(user.getName(), user.getAge()))
        .collect(Collectors.toList());

    return ResponseEntity.ok(users);
  }

  @PostMapping("/update")
  public ResponseEntity<?> updateUser(@RequestBody UserDto userDto) {
    userService.updateUserAge(userDto.getName(), userDto.getAge());
    return ResponseEntity.ok().build();
  }
}
