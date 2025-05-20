package com.notepad.notepadjavavsc.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.notepad.notepadjavavsc.user.dto.UserDto;


@RestController
@RequestMapping("/hello")
public class HelloController {
  @GetMapping()
  public String getHello() {
    return "Hello";
  }

  @PostMapping()
  public String postHello() {
    return "Hello post";
  }

  @GetMapping("/{id}")
  public String getHelloById(@PathVariable("id") Long id) {
    return "ID: " + id;
  }

  @GetMapping("/query")
  public String getHelloByQuery(@RequestParam("page") int page,
      @RequestParam(value = "limit", required = false, defaultValue = "5") int limit) {
    return "Keyword: " + page + ", Limit: " + limit;
  }

  @PostMapping("/user")
  public String postHelloByBody(@RequestBody UserDto user) {
    return "Created user: " + user.getName() + ", age: " + user.getAge();
  }

  @PostMapping("/file")
  public String postHelloByFile(@RequestParam("description") String description,
      @RequestParam("file") MultipartFile file) {
    String fileName = file.getOriginalFilename();
    long size = file.getSize();

    return "Description: " + description + ", Uploaded file: " + fileName + " (" + size + " bytes)";
  }

  @PostMapping("/multi")
  public String postHelloByFiles(
      @RequestParam("files") MultipartFile[] files) {

    int count = files.length;
    return count + " files uploaded.";
  }
}
