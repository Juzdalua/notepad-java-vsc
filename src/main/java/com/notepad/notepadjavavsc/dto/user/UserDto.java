package com.notepad.notepadjavavsc.dto.user;

import lombok.Data;

@Data
public class UserDto {
  private String name;
  private int age;

  public String getUserName() {
    return name;
  }

  public int getUserAge() {
    return age;
  }
}
