package com.notepad.notepadjavavsc.user.controller;

import com.notepad.notepadjavavsc.common.dto.ApiResponse;
import com.notepad.notepadjavavsc.user.dto.LoginRequest;
import com.notepad.notepadjavavsc.user.dto.SignupRequest;
import com.notepad.notepadjavavsc.user.dto.LoginResponse;
import com.notepad.notepadjavavsc.user.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

  private final AuthService authService;

  @PostMapping("/signup")
  public ResponseEntity<ApiResponse<LoginResponse>> signup(@RequestBody @Valid SignupRequest request) {

    System.out.println("SIGN1");
    LoginResponse response = authService.signup(request);
    System.out.println("SIGN2");
    return ResponseEntity.ok(new ApiResponse<>(response));
  }

  @PostMapping("/login")
  public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody @Valid LoginRequest request) {
    LoginResponse response = authService.login(request);
    return ResponseEntity.ok(new ApiResponse<>(response));
  }
}