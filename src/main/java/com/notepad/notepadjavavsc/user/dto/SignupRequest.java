package com.notepad.notepadjavavsc.user.dto;

import java.time.LocalDate;

import com.notepad.notepadjavavsc.user.domain.User.Gender;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SignupRequest {

  @Email
  @NotBlank
  private String email;

  @NotBlank
  private String password;

  @NotNull
  private LocalDate birthday;

  @NotNull
  private Gender gender;

  @AssertTrue
  private boolean acceptTerms;
}
