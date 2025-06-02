package com.notepad.notepadjavavsc.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notepad.notepadjavavsc.user.dto.UserResponse;
import com.notepad.notepadjavavsc.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/me")
  public ResponseEntity<UserResponse> getMyInfo(Authentication authentication) {
    UserResponse user = (UserResponse) authentication.getPrincipal();
    userService.getUserInfoByEmail(user.getEmail());
    return ResponseEntity.ok(user);
  }
}
