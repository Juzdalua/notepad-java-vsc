package com.notepad.notepadjavavsc.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.notepad.notepadjavavsc.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByName(String name);

  @Modifying
  @Query("UPDATE User u SET u.age = :age WHERE u.name = :name")
  int updateUserAgeByName(@Param("name") String name, @Param("age") int age);
}