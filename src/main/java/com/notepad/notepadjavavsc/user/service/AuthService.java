package com.notepad.notepadjavavsc.user.service;

import com.notepad.notepadjavavsc.user.dto.LoginResponse;

import org.springframework.stereotype.Service;

import com.notepad.notepadjavavsc.global.jwt.JwtProvider;
import com.notepad.notepadjavavsc.user.domain.User;
import com.notepad.notepadjavavsc.user.dto.LoginRequest;
import com.notepad.notepadjavavsc.user.dto.SignupRequest;
import com.notepad.notepadjavavsc.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Service
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtProvider jwtProvider;

  public LoginResponse signup(SignupRequest request) {

    if (userRepository.existsByEmail(request.getEmail())) {
      throw new IllegalArgumentException("Email is Already exist.");
    }

    String encodedPassword = passwordEncoder.encode(request.getPassword());

    System.out.println("PWD: " + encodedPassword);

    User user = User.builder()
        .email(request.getEmail())
        .password(encodedPassword)
        .birthday(request.getBirthday())
        .gender(request.getGender())
        .acceptTerms(request.isAcceptTerms())
        .role(User.UserRole.USER)
        .build();

    userRepository.save(user);
    System.out.println("USER: " + user);

    String accessToken = jwtProvider.createToken(user.getId().toString());

    return new LoginResponse(user.getId(), accessToken);
  }

  public LoginResponse login(LoginRequest request) {
    User user = userRepository.findByEmail(request.getEmail())
        .orElseThrow(() -> new IllegalArgumentException("Not exist user"));

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      throw new IllegalArgumentException("Password not correct");
    }

    String accessToken = jwtProvider.createToken(user.getId().toString());

    return new LoginResponse(user.getId(), accessToken);
  }
}
