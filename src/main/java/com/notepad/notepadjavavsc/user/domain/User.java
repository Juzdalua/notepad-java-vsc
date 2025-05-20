package com.notepad.notepadjavavsc.user.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 만들어줌
@EntityListeners(AuditingEntityListener.class) // createdAt, updatedAt을 위해 감시
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private int age;

  @CreatedDate
  @Column(name = "createdAt", updatable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(name = "updatedAt")
  private LocalDateTime updatedAt;

  public User(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public static User of(String name, int age) {
    return new User(name, age);
  }
}
